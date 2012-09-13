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
import com.krminc.phr.api.converter.clinical.ResourcesConverter;
import com.krminc.phr.api.service.Api;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.clinical.Resource;
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


/**
 * Resouces (External Service "Endpoint") API RESTful resource class and mapping.
 *
 * @author Daniel Shaw (dshaw.com)
 */

//@Path("/resources/")
public class ResourcesResource {

    @Context
    protected UriInfo uriInfo;
    @Context
    protected ResourceContext resourceContext;
  
    /** Creates a new instance of ResourcesResource */
    public ResourcesResource() {
    }

    /**
     * Get method for retrieving a collection of Resource instance in XML format.
     *
     * @return an instance of ResourcesConverter
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ResourcesConverter get(
        @QueryParam("start") @DefaultValue("0") int start,
        @QueryParam("max") @DefaultValue("10") int max
    ) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new ResourcesConverter(getEntities(start, max), uriInfo.getAbsolutePath(), Api.DEFAULT_EXPAND_LEVEL);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of Resource using XML as the input format.
     *
     * @param data an ResourceConverter entity that is deserialized from an XML stream
     * @return an instance of ResourceConverter
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response post(ResourceConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            Resource entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getId() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of ResourceResource used for entity navigation.
     *
     * @return an instance of ResourceResource
     */
    @Path("{id}/")
    public ResourceResource getResourceResource(@PathParam("id") Long id) {
        ResourceResource resource = resourceContext.getResource(ResourceResource.class);
        resource.setResourceId(id);
        return resource;
    }

    /**
     * @return all active resources
     */
    protected Collection<Resource> getEntities(int start, int max) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createNamedQuery("Resource.findByActive")
            .setParameter("active", Boolean.TRUE)
            .setFirstResult(start)
            .setMaxResults(max)
            .getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(Resource entity) {
        entity.setId(null);
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
    }
}
