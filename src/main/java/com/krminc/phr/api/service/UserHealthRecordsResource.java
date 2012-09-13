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
package com.krminc.phr.api.service;

import com.krminc.phr.api.converter.HealthRecordConverter;
import com.krminc.phr.api.converter.HealthRecordsConverter;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.HealthRecord;
import com.krminc.phr.domain.User;
import com.sun.jersey.api.core.ResourceContext;
import java.util.Collection;
import javax.persistence.EntityManager;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;

/**
 * Heath Records API RESTful resource class and mapping accessible via the user branch of the API.
 *
 * @author Daniel Shaw (dshaw.com)
 */
public class UserHealthRecordsResource {

    final Logger logger = LoggerFactory.getLogger(UserHealthRecordsResource.class);

    @Context
    protected UriInfo uriInfo;
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected transient HttpServletRequest servletRequest;

    protected Long userId;


    public UserHealthRecordsResource() {
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Get method for retrieving a collection of HealthRecord instance in XML format.
     *
     * @return an instance of HealthRecordsConverter
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public HealthRecordsConverter get(
        @QueryParam("start") @DefaultValue("0") int start,
        @QueryParam("max") @DefaultValue("10") int max
    ) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new HealthRecordsConverter(getEntities(start, max), uriInfo.getAbsolutePath(), Api.DEFAULT_EXPAND_LEVEL);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of HealthRecord using XML as the input format.
     *
     * @param data an HealthRecordConverter entity that is deserialized from an XML stream
     * @return an instance of HealthRecordConverter
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response post(HealthRecordConverter data) {
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
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            HealthRecord entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getHealthRecordId() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of HealthRecordResource used for entity navigation.
     *
     * @return an instance of HealthRecordResource
     */
    @Path("{healthRecordId}/")
    public UserHealthRecordResource getHealthRecordResource(@PathParam("healthRecordId") Long healthRecordId) {
        UserHealthRecordResource resource = resourceContext.getResource(UserHealthRecordResource.class);
        resource.setId(healthRecordId);
        return resource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of HealthRecord instances
     */
    protected Collection<HealthRecord> getEntities(int start, int max) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createNamedQuery("HealthRecord.findByUserId")
            .setParameter("userId", userId)
            .setFirstResult(start)
            .setMaxResults(max)
            .getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(HealthRecord entity) {
        entity.setHealthRecordId(null);
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        User user = entity.getUser();
        if (user != null) {
            user.getHealthRecords().add(entity);
        }
    }

}
