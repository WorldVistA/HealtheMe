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
package com.krminc.phr.api.service.clinical;

import com.krminc.phr.api.converter.clinical.RecordIdentifierConverter;
import com.krminc.phr.api.converter.clinical.RecordIdentifiersConverter;
import com.krminc.phr.api.service.Api;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.clinical.RecordIdentifier;
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
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Record Identifiers API RESTful resource class and mapping.
 *
 * @author Daniel Shaw (dshaw.com)
 */

public class RecordIdentifiersResource {

    final Logger logger = LoggerFactory.getLogger(RecordIdentifiersResource.class);

    @Context
    protected UriInfo uriInfo;
    @Context
    protected ResourceContext resourceContext;

    protected Long healthRecordId;
    protected Long resourceId;


    public RecordIdentifiersResource() {
    }


    public void setHealthRecordId(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * Get method for retrieving a collection of RecordIdentifier instance in XML format.
     *
     * @return an instance of RecordIdentifiersConverter
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public RecordIdentifiersConverter get(
        @QueryParam("start") @DefaultValue("0") int start,
        @QueryParam("max") @DefaultValue("10") int max
    ) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new RecordIdentifiersConverter(getEntities(start, max), uriInfo.getAbsolutePath(), Api.DEFAULT_EXPAND_LEVEL);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of RecordIdentifier using XML as the input format.
     *
     * @param data an RecordIdentifierConverter entity that is deserialized from an XML stream
     * @return an instance of RecordIdentifierConverter
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response post(RecordIdentifierConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            RecordIdentifier entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getId() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of RecordIdentifierResource used for entity navigation.
     *
     * @return an instance of RecordIdentifierResource
     */
    @Path("{identityId}/")
    public RecordIdentifierResource getRecordIdentifierResource(@PathParam("identityId") Long id) {
        RecordIdentifierResource resource = resourceContext.getResource(RecordIdentifierResource.class);
        resource.setId(id);
        return resource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of RecordIdentifier instances
     */
    protected Collection<RecordIdentifier> getEntities(int start, int max) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        Collection<RecordIdentifier> recids = em.createNamedQuery("RecordIdentifier.findByHealthRecordIdAndResourceId")
            .setParameter("healthRecordId", healthRecordId)
            .setParameter("resourceId", resourceId)
            .setFirstResult(start)
            .setMaxResults(max)
            .getResultList();
        logger.debug("recids: {}, {}", recids.size(), recids.toArray());
        return recids;
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(RecordIdentifier entity) {
        entity.setId(null);
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
    }
}
