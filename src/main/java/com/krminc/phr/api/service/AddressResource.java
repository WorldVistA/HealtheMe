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
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.Address;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import com.sun.jersey.api.core.ResourceContext;
import javax.ws.rs.WebApplicationException;
import javax.persistence.NoResultException;
import javax.persistence.EntityManager;
import com.krminc.phr.domain.User;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Adress API RESTful resource class.
 *
 * @author Daniel Shaw (dshaw.com)
 */
public class AddressResource {

    final Logger logger = LoggerFactory.getLogger(AddressResource.class);

    @Context
    protected UriInfo uriInfo;
    @Context
    protected ResourceContext resourceContext;

    protected Long id;
  

    public AddressResource() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get method for retrieving an instance of Address identified by id in XML format.
     *
     * @return an instance of AddressConverter
     */
    @GET
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public AddressConverter get() {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new AddressConverter(getEntity(), uriInfo.getAbsolutePath(), Api.DEFAULT_EXPAND_LEVEL);
        } finally {
            PersistenceService.getInstance().close();
        }
    }

    /**
     * Put method for updating an instance of Address identified by id using XML as the input format.
     *
     * @param data an AddressConverter entity that is deserialized from a XML stream
     */
    @PUT
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public void put(AddressConverter data) {
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
     * Delete method for deleting an instance of Address identified by id.
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
     * Returns an instance of Address identified by id.
     *
     * @return an instance of Address
     */
    protected Address getEntity() {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            return (Address) em.find(Address.class, id);
        } catch (NoResultException ex) {
            throw new WebApplicationException(
                new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."),
                404
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
    protected Address updateEntity(Address entity, Address newEntity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        User user = entity.getUser();
        User userNew = newEntity.getUser();
        entity = em.merge(newEntity);
        if (user != null && !user.equals(userNew)) {
            user.getAddresses().remove(entity);
        }
        if (userNew != null && !userNew.equals(user)) {
            userNew.getAddresses().add(entity);
        }
        return entity;
    }

    /**
     * Deletes the entity.
     *
     * @param entity the entity to deletle
     */
    protected void deleteEntity(Address entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        User user = entity.getUser();
        if (user != null) {
            user.getAddresses().remove(entity);
        }
        em.remove(entity);
    }
}
