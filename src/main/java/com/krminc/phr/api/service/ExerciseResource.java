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
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.WebApplicationException;
import javax.persistence.NoResultException;
import javax.persistence.EntityManager;
import com.krminc.phr.api.converter.ExerciseConverter;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.HealthRecord;
import com.sun.jersey.api.core.ResourceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cmccall
 */

public class ExerciseResource {
    final Logger logger = LoggerFactory.getLogger(ExerciseResource.class);

    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    @Context
    protected transient HttpServletRequest servletRequest;
    
    protected Long id;
  
    /** Creates a new instance of ExerciseResource */
    public ExerciseResource() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get method for retrieving an instance of ExerciseResource identified by id in XML format.
     *
     * @return an instance of ExerciseConverter
     */
    @GET
    @Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public ExerciseConverter get() {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new ExerciseConverter(getEntity(), uriInfo.getAbsolutePath(), Api.DEFAULT_EXPAND_LEVEL);
        } finally {
            PersistenceService.getInstance().close();
        }
    }

    /**
     * Put method for updating an instance of Exercise identified by id using XML as the input format.
     *
     * @param id identifier for the entity
     * @param data an ExerciseConverter entity that is deserialized from a XML stream
     */
    @PUT
    @Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
    public void put(ExerciseConverter data) {
        //check healthrecord is genuine
        try {
            Long local = data.getHealthRecordId();
            Long session = new Long(servletRequest.getSession().getAttribute("healthRecordId").toString());
            if (session != null) {
                if (local != null) {
                    if (session.compareTo(local) != 0) {
                        data.setHealthRecordId(session); //Passed value not equal, using session value
                    } // else values are equal, leave as is
                } else {
                    data.setHealthRecordId(session); //No local value passed
                }
            }
        }
        catch(NullPointerException ex) {
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }


        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            //check updateable
            if (getEntity().getDataSourceId() != 1) {
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }

            if (data.hasError) {
                throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
            }

            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            updateEntity(getEntity(), data.resolveEntity(em));
            persistenceSvc.commitTx();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Delete method for deleting an instance of Exercise identified by id.
     *
     * @param id identifier for the entity
     */
    @DELETE
    public void delete() {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            //check updateable
            if (getEntity().getDataSourceId() != 1) {
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }

            //check healthrecord is genuine
            try {
                Long local = new Long(getEntity().getHealthRecordId());
                Long session = new Long(servletRequest.getSession().getAttribute("healthRecordId").toString());
                if ( local.compareTo(session) != 0) {
                    throw new WebApplicationException(Response.Status.FORBIDDEN);
                }
            }
            catch(NullPointerException ex) {
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }

            persistenceSvc.beginTx();
            deleteEntity(getEntity());
            persistenceSvc.commitTx();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns an instance of Exercise identified by id.
     *
     * @param id identifier for the entity
     * @return an instance of Exercise
     */
    protected Exercise getEntity() {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            return (Exercise) em.find(Exercise.class, id);
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
    private Exercise updateEntity(Exercise entity, Exercise newEntity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        HealthRecord hr = entity.getHealthRecord();
        HealthRecord hrNew = newEntity.getHealthRecord();
        entity = em.merge(newEntity);
        return entity;
    }

    /**
     * Deletes the entity.
     *
     * @param entity the entity to deletle
     */
    private void deleteEntity(Exercise entity) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        em.remove(entity);
    }
}
