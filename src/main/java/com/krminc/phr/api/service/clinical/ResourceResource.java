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

import com.krminc.phr.api.converter.clinical.ResourceConverter;
import com.krminc.phr.api.service.Api;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.clinical.Resource;
import com.sun.jersey.api.core.ResourceContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Resouce (External Service "Endpoint") API RESTful resource class.
 *
 * @author Daniel Shaw (dshaw.com)
 */
public class ResourceResource {

    final Logger logger = LoggerFactory.getLogger(ResourceResource.class);

    @Context
    protected UriInfo uriInfo;
    @Context
    protected ResourceContext resourceContext;

    protected Long resourceId;
  

    public ResourceResource() {
    }


    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * Get method for retrieving an instance of Resource identified by id in XML format.
     *
     * @param id identifier for the entity
     * @return an instance of ResourceConverter
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ResourceConverter get() {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new ResourceConverter(getEntity(), uriInfo.getAbsolutePath(), Api.DEFAULT_EXPAND_LEVEL);
        } finally {
            PersistenceService.getInstance().close();
        }
    }

    /**
     * Put method for updating an instance of Resource identified by id using XML as the input format.
     *
     * @param id identifier for the entity
     * @param data an ResourceConverter entity that is deserialized from a XML stream
     */
    @PUT
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void put(ResourceConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            updateEntity(getEntity(), data.resolveEntity(em));
            persistenceSvc.commitTx();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Delete method for deleting an instance of Resource identified by id.
     *
     * @param id identifier for the entity
     */
    @DELETE
    public void delete() {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            deleteEntity(getEntity());
            persistenceSvc.commitTx();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns an instance of Resource identified by id.
     *
     * @param id identifier for the entity
     * @return an instance of Resource
     */
    protected Resource getEntity() {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            return (Resource) em.find(Resource.class, resourceId);
        } catch (NoResultException ex) {
            throw new WebApplicationException(
                new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."),
                Status.NOT_FOUND
            );
        }
    }

    /**
     * Updates entity using data from newEntity.
     *
     * @param entity the entity to update
     * @param newEntity the entity containing the new data
     * @return the updated entity
     */
    protected Resource updateEntity(Resource entity, Resource newEntity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        entity = em.merge(newEntity);
        return entity;
    }

    /**
     * Deletes the entity.
     *
     * @param entity the entity to deletle
     */
    protected void deleteEntity(Resource entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.remove(entity);
    }

}
