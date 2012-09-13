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
import com.krminc.phr.api.service.clinical.UserResourcesResource;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.HealthRecord;
import com.krminc.phr.domain.User;
import com.sun.jersey.api.core.ResourceContext;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.WebApplicationException;
import javax.persistence.NoResultException;
import javax.persistence.EntityManager;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Heath Record API RESTful resource class accessible via the user branch of the API.
 *
 * @author Daniel Shaw (dshaw.com)
 */
public class UserHealthRecordResource {

    final Logger logger = LoggerFactory.getLogger(UserHealthRecordResource.class);

    @Context
    protected UriInfo uriInfo;
    @Context
    protected ResourceContext resourceContext;

    protected Long id;


    public UserHealthRecordResource() {
    }


    public void setId(Long healthRecordId) {
        this.id = healthRecordId;
    }

    /**
     * Get method for retrieving an instance of HealthRecord identified by id in XML format.
     *
     * @return an instance of HealthRecordConverter
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public HealthRecordConverter get() {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new HealthRecordConverter(getEntity(), uriInfo.getAbsolutePath(), Api.DEFAULT_EXPAND_LEVEL);
        } finally {
            PersistenceService.getInstance().close();
        }
    }

    //Removed PUT and DELETE options from API

    /**
     * Returns an instance of HealthRecord identified by id.
     *
     * @return an instance of HealthRecord
     */
    protected HealthRecord getEntity() {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            return (HealthRecord) em.find(HealthRecord.class, id);
        } catch (NoResultException ex) {
            throw new WebApplicationException(
                new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."),
                Response.Status.NOT_FOUND
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
    protected HealthRecord updateEntity(HealthRecord entity, HealthRecord newEntity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        User user = entity.getUser();
        User userNew = newEntity.getUser();
        entity = em.merge(newEntity);
        if (user != null && !user.equals(userNew)) {
            user.getHealthRecords().remove(entity);
        }
        if (userNew != null && !userNew.equals(user)) {
            userNew.getHealthRecords().add(entity);
        }
        return entity;
    }

    //custom resources

    /**
     * Get method for retrieving Patient info identified by id in JSON format.
     *
     * @return an JSONObject
     */
    @Path("info/")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject getPatientInfo() {

        HealthRecord hr = null;
        JSONObject jSONObject = new JSONObject();

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            hr = getEntity();
            jSONObject.put("name", hr.getUser().getFullName());
            jSONObject.put("gender", hr.getFullGender());
            jSONObject.put("age", hr.getAge());
        } catch (JSONException ex) {
        } finally {
            PersistenceService.getInstance().close();
        }

        return jSONObject;
    }

    /**
     * @return a dynamic instance of UserResourcesResource used for entity navigation.
     */
    @Path("resources/")
    public UserResourcesResource getUserResourcesResource() {
        UserResourcesResource resource = resourceContext.getResource(UserResourcesResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * @return a dynamic instance of RecordIdentifiersResource used for entity navigation.
     */
    // MOVED TO USERRESOURCERESOURCE.JAVA
//    @Path("recordidentifiers/")
//    public RecordIdentifiersResource getRecordIdentifiersResource() {
//        RecordIdentifiersResource resource = resourceContext.getResource(RecordIdentifiersResource.class);
//        resource.setHealthRecordId(id);
//        return resource;
//    }

}
