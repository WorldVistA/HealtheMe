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

import com.krminc.phr.api.converter.AddressConverter;
import com.krminc.phr.api.converter.AddressesConverter;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.Address;
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

/**
 * Adresses API RESTful resource class and mapping.
 *
 * @author Daniel Shaw (dshaw.com)
 */
public class AddressesResource {

    final Logger logger = LoggerFactory.getLogger(AddressesResource.class);

    @Context
    protected UriInfo uriInfo;
    @Context
    protected ResourceContext resourceContext;

    private Long patientId;


    public AddressesResource() {
    }


    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    /**
     * Get method for retrieving a collection of Address instance in XML format.
     *
     * @return an instance of AddressesConverter
     */
    @GET
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public AddressesConverter get(
        @QueryParam("start") @DefaultValue("0") int start,
        @QueryParam("max") @DefaultValue("10") int max
    ) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            String query = "SELECT e FROM Address e";
            return new AddressesConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), Api.DEFAULT_EXPAND_LEVEL);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Post method for creating an instance of Address using XML as the input format.
     *
     * @param data an AddressConverter entity that is deserialized from an XML stream
     * @return an instance of AddressConverter
     */
    @POST
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public Response post(AddressConverter data) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            Address entity = data.resolveEntity(em);
            createEntity(data.resolveEntity(em));
            persistenceSvc.commitTx();
            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getAddressId() + "/")).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of AddressResource used for entity navigation.
     *
     * @param id addressId
     * @return an instance of AddressResource
     */
    @Path("{addressId}/")
    public AddressResource getAddressResource(@PathParam("addressId") Long id) {
        AddressResource resource = resourceContext.getResource(AddressResource.class);
        resource.setId(id);
        return resource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of Address instances
     */
    protected Collection<Address> getEntities(int start, int max, String query) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
    protected void createEntity(Address entity) {
        entity.setAddressId(null);
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.persist(entity);
        User user = entity.getUser();
        if (user != null) {
            user.getAddresses().add(entity);
        }
    }
}
