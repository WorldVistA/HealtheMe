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

package com.krminc.phr.api.service.clinical;

import com.krminc.phr.api.converter.clinical.CcrResultsConverter;
import com.krminc.phr.api.service.Api;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.clinical.CcrResult;
import java.util.Collection;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import com.sun.jersey.api.core.ResourceContext;
import javax.persistence.EntityManager;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author cmccall
 */

public class CcrResultsResource {
    @Context
    protected UriInfo uriInfo;
    @Context
    protected ResourceContext resourceContext;

    protected Long healthRecordId;
  
    /** Creates a new instance of CcrResultsResource */
    public CcrResultsResource() {
    }

    public void setHealthRecordId(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    /**
     * Get method for retrieving a collection of CcrResult instance in XML format.
     *
     * @return an instance of CcrResultsConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public CcrResultsConverter get(
        @QueryParam("start")
        @DefaultValue("0")
        int start,
        @QueryParam("max")
        @DefaultValue("10")
        int max,
        @QueryParam("orderBy")
        @DefaultValue("dateadded")
        String orderBy,
        @QueryParam("desc")
        @DefaultValue("1")
        int desc
    ) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new CcrResultsConverter(getEntities(start, max, orderBy, desc), uriInfo.getAbsolutePath(), Api.DEFAULT_EXPAND_LEVEL);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    @Path("count/")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject getCount() {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        Long result = 0L;

        result = (Long) em.createNamedQuery("CcrResult.countByHealthRecordId")
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
     * Returns a dynamic instance of CcrResultResource used for entity navigation.
     *
     * @return an instance of CcrResultResource
     */
    @Path("{id}/")
    public CcrResultResource getCcrResultResource(@PathParam("id")
    Long id) {
        CcrResultResource resource = resourceContext.getResource(CcrResultResource.class);
        resource.setId(id);
        return resource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of CcrResult instances
     */
    protected Collection<CcrResult> getEntities(int start, int max, String orderBy, int desc) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        //  orderBy :
        //    name
        //    dateadded
        //    ordereddate
        //  desc:
        //    0 -- ascending (default)
        //    >0 -- descending
        if(start<0) start=0;
        if(max<1) max=1;
        if(max>100) max=100;
        StringBuilder query;

        Boolean wasOrdered = false;
        if(orderBy.equalsIgnoreCase("name")){
            query = new StringBuilder("SELECT x FROM CcrResult x JOIN x.ccrDocument c JOIN c.healthRecords h WHERE h.id = :healthRecordId ORDER BY x.descriptionText");
            wasOrdered = true;
        } else if(orderBy.equalsIgnoreCase("dateadded")){
            query = new StringBuilder("SELECT x FROM CcrResult x JOIN x.ccrDocument c JOIN c.healthRecords h WHERE h.id = :healthRecordId ORDER BY c.addedDateTime");
            wasOrdered = true;
        } else if(orderBy.equalsIgnoreCase("ordereddate")){
            query = new StringBuilder("SELECT x FROM CcrResult x JOIN x.dateTime y JOIN x.ccrDocument c JOIN c.healthRecords h WHERE h.id = :healthRecordId ORDER BY y.exactDateTime");
            wasOrdered = true;
        } else {
            query = new StringBuilder("SELECT x FROM CcrResult x JOIN x.ccrDocument c JOIN c.healthRecords h WHERE h.id = :healthRecordId");
        }

        if (wasOrdered) {
            if (desc>0) {
                query.append(" DESC");
            } else {
                query.append(" ASC");
            }
        }

        return em.createQuery(query.toString())
            .setParameter("healthRecordId", healthRecordId)
            .setFirstResult(start)
            .setMaxResults(max)
            .getResultList();
    }
}
