/**
 * Copyright (C) 2012 KRM Associates, Inc. healtheme@krminc.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.krminc.phr.api.service;

import com.krminc.phr.domain.Exercise;
import java.util.Collection;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.persistence.EntityManager;
import com.krminc.phr.api.converter.ExercisesConverter;
import com.krminc.phr.api.converter.ExerciseConverter;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.ExerciseList;
import com.sun.jersey.api.core.ResourceContext;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import oracle.toplink.essentials.config.CascadePolicy;
import oracle.toplink.essentials.config.HintValues;
import oracle.toplink.essentials.config.TopLinkQueryHints;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author cmccall
 */

public class ExercisesResource {
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;
    @Context
    protected transient HttpServletRequest servletRequest;

    protected Long healthRecordId;
    
    final Logger logger = LoggerFactory.getLogger(ExercisesResource.class);
  
    /** Creates a new instance of ExercisesResource */
    public ExercisesResource() {
    }

    public void setHealthRecordId(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    /**
     * Get method for retrieving a collection of Exercise instance in XML format.
     *
     * @return an instance of ExercisesConverter
     */
    @GET
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public ExercisesConverter get(
            @QueryParam("start") @DefaultValue("0") int start,
            @QueryParam("max") @DefaultValue("10") int max,
            @QueryParam("source") @DefaultValue("self") String source,
            @QueryParam("orderBy") @DefaultValue("dateadded") String orderBy,
            @QueryParam("desc") @DefaultValue("1") int desc)
    {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new ExercisesConverter(
                getEntities(start, max, source, orderBy, desc),
                uriInfo.getAbsolutePath(),
                Api.DEFAULT_EXPAND_LEVEL
            );
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    //API complement to autocomplete jQuery plugin:
    // http://docs.jquery.com/Plugins/Autocomplete/autocomplete#url_or_dataoptions
    //return a text list of exercises matching a description
    @Path("/list")
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String getExercisesList(
            @QueryParam("q") String query,
            @QueryParam("limit") int limit
            ) {

        List<ExerciseList> exerciseList = null;

        try {
        EntityManager em = PersistenceService.getInstance().getEntityManager();

        String sqlQuery = new String("SELECT e FROM ExerciseList e WHERE e.display = TRUE AND e.exerciseDescription LIKE :pattern ORDER BY e.exerciseDescription");

        exerciseList = em.createQuery(sqlQuery)
            .setParameter("pattern", query + "%")
            .setFirstResult(0)
            .setMaxResults(limit)
            .getResultList();
        }
        catch (Exception ex) {
            //error pulling data - return empty list
            logger.error("Unable to find matching list item for query. {}", ex);
            return "";
        }

        StringBuilder exercises = new StringBuilder();

        if (exerciseList != null) {
            for (ExerciseList item : exerciseList) {
                exercises.append(item.getExerciseDescription() + "\n");
            }
        }

        return exercises.toString();
    }

    /**
     * Post method for creating an instance of Exercise using XML as the input format.
     *
     * @param data an ExerciseConverter entity that is deserialized from an XML stream
     * @return an instance of ExerciseConverter
     */
    @POST
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response post(ExerciseConverter data) {
        //check healthrecord is genuine
        try {
            Long session = new Long(servletRequest.getSession().getAttribute("healthRecordId").toString());
            if (session!=null) { 
                data.setHealthRecordId(session);
            } else {
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
        }
        catch(NullPointerException ex) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            if (data.hasError) {
                throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
            }

            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            Exercise entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getExerciseId() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of ExerciseResource used for entity navigation.
     *
     * @return an instance of ExerciseResource
     */
    @Path("{exerciseId: \\d+}/")
    public ExerciseResource getExerciseResource(@PathParam("exerciseId") Long id) {
        ExerciseResource exerciseResource = resourceContext.getResource(ExerciseResource.class);
        exerciseResource.setId(id);
        return exerciseResource;
    }

    @Path("count/")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public JSONObject getCount(
            @QueryParam("source")
            @DefaultValue("self")
            String source){
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        Long result = 0L;

        result = (Long) em.createNamedQuery("Exercise.countByHealthRecordId")
            .setParameter("healthRecordId" , this.healthRecordId)
            .getSingleResult();

        JSONObject jsonResult = new JSONObject();
        try {
            jsonResult.put("count", result);
        }
        catch (JSONException ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return jsonResult;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of Exercise instances
     */
    protected Collection<Exercise> getEntities(int start, int max, String source, String orderBy, int desc) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        //build query based on source.
        //  orderBy :
        //    visitdate
        //    addeddate (default)
        //  desc:
        //    0 -- ascending (default)
        //    >0 -- descending
        if(start<0) start=0;
        if(max<1) max=1;
        if(max>100) max=100;

        StringBuilder query = new StringBuilder("SELECT e FROM Exercise e WHERE e.healthRecordId = :healthRecordId");

        if(!orderBy.equalsIgnoreCase("dateadded")){
            query.append(" ORDER BY e.exerciseDate");
        } else {
            query.append(" ORDER BY e.dateAdded");
        }

        if (desc>0) {
            query.append(" DESC");
        } else {
            query.append(" ASC");
        }

        return em.createQuery(query.toString())
            .setParameter("healthRecordId", healthRecordId)
            .setHint(TopLinkQueryHints.REFRESH, HintValues.TRUE)
            .setHint(TopLinkQueryHints.REFRESH_CASCADE, CascadePolicy.CascadeAllParts)
            .setFirstResult(start)
            .setMaxResults(max)
            .getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(Exercise entity) {
        entity.setExerciseId(null);
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
    }
}
