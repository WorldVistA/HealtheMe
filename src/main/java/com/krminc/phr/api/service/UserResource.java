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

import com.krminc.phr.api.converter.UserConverter;
import com.krminc.phr.api.service.clinical.ResourcesResource;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.Address;
import com.krminc.phr.domain.User;
import com.sun.jersey.api.core.ResourceContext;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User API RESTful resource class and mapping.
 *
 * @author Daniel Shaw (dshaw.com)
 */
public class UserResource {

    final Logger logger = LoggerFactory.getLogger(UserResource.class);

    @Context
    protected UriInfo uriInfo;
    @Context
    protected ResourceContext resourceContext;

    protected Long userId;
    protected String username;


    public UserResource() {
    }


    public void setId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get method for retrieving an instance of User identified by id in XML format.
     *
     * @return an instance of UserConverter
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public UserConverter get() {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            User user = null;
            if (username != null) {
                user = getEntityByUsername();
            } else {
                user = getEntity();
            }
            return new UserConverter(user, uriInfo.getAbsolutePath(), Api.DEFAULT_EXPAND_LEVEL);
        } finally {
            PersistenceService.getInstance().close();
        }
    }

    /**
     * Put method for updating an instance of User identified by id using XML as the input format.
     *
     * @param userId identifier for the entity
     * @param data an UserConverter entity that is deserialized from a XML stream
     */
    @PUT
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void put(UserConverter data) {
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
     * Delete method for deleting an instance of User identified by id.
     *
     * @param userId identifier for the entity
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
     * @return a users Login Count (total number of logins).
     */
    @Path("logincount/")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject getLoginCount() {
        JSONObject jsonResult = new JSONObject();
        try {
            jsonResult.put("logincount", getEntity().getTotalLogin());
        }
        catch (JSONException ex) {
            throw new WebApplicationException(Status.NOT_FOUND);
        }
        return jsonResult;
    }

    /**
     * @return a users Login Count (total number of logins).
     */
    @Path("available/")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject getUsernameAvailable() {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            try {
                Long uId = (Long) em.createNamedQuery("User.findUserIdByUsername")
                    .setParameter("username", username)
                    .getSingleResult();
                /*
                 * Username is already assigned to a user.
                 */
                throw new WebApplicationException(Status.CONFLICT);
            } catch (NoResultException ex) {
                /*
                 * Username available.
                 */
                try {
                    JSONObject jsonResult = new JSONObject();
                    jsonResult.put("available", true);
                    return jsonResult;
                } catch (JSONException je) {
                    throw new WebApplicationException(new Throwable("JSON Error"),
                            Status.INTERNAL_SERVER_ERROR);
                }
            }
        } finally {
            PersistenceService.getInstance().close();
        }
    }

    @Path("password/")
    @PUT
    @Consumes({ MediaType.APPLICATION_JSON })
    public void setPassword(JSONObject json) throws JSONException {
        String password = (String) json.get("password");
        if (password == null || password.length() == 0) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            User updatedUser = getEntity();
            updatedUser.setPassword(password);
            updatedUser.setRequiresReset(Boolean.TRUE);
            updateEntity(getEntity(), updatedUser);
            persistenceSvc.commitTx();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * @return a dynamic instance of UserHealthRecordsResource used for entity navigation.
     */
    @Path("healthrecords/")
    public UserHealthRecordsResource getUserHealthRecordsResource() {
        UserHealthRecordsResource resource = resourceContext.getResource(UserHealthRecordsResource.class);
        resource.setUserId(userId);
        return resource;
    }

    /**
     * @return a dynamic instance of ResourcesResource used for entity navigation.
     */
    @Path("resources/")
    public ResourcesResource getResourcesResource() {
        ResourcesResource resource = resourceContext.getResource(ResourcesResource.class);
        return resource;
    }

    /**
     * Returns an instance of User identified by id.
     *
     * @param userId identifier for the entity
     * @return an instance of User
     */
    protected User getEntity() {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            return (User) em.find(User.class, userId);
        } catch (NoResultException ex) {
            throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
        }
    }

    /**
     * @return an instance of User identified by username.
     */
    protected User getEntityByUsername() {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            return (User) em.createNamedQuery("User.findByUsername")
                .setParameter("username", username)
                .getSingleResult();
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
    protected User updateEntity(User entity, User newEntity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        List<Address> addresses = entity.getAddresses();
        List<Address> addressesNew = newEntity.getAddresses();
        entity = em.merge(newEntity);
        for (Address value : addresses) {
            if (!addressesNew.contains(value)) {
                throw new WebApplicationException(new Throwable("Cannot remove items from addresses"));
            }
        }
        for (Address value : addressesNew) {
            if (!addresses.contains(value)) {
                User oldEntity = value.getUser();
                value.setUser(entity);
                if (oldEntity != null && !oldEntity.equals(entity)) {
                    oldEntity.getAddresses().remove(value);
                }
            }
        }
        return entity;
    }

    /**
     * Deletes the entity.
     *
     * @param entity the entity to deletle
     */
    protected void deleteEntity(User entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        if (!entity.getAddresses().isEmpty()) {
            throw new WebApplicationException(new Throwable("Cannot delete entity because addresses is not empty."));
        }
        em.remove(entity);
    }
}
