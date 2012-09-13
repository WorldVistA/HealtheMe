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

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import com.sun.jersey.api.core.ResourceContext;
import javax.persistence.EntityManager;
import com.krminc.phr.domain.User;
import com.krminc.phr.core.UserConfig;
import com.krminc.phr.dao.PersistenceService;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cmccall
 */

public class HealthRecordsResource {

    final Logger logger = LoggerFactory.getLogger(HealthRecordsResource.class);

    @Context
    protected UriInfo uriInfo;
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected SecurityContext securityContext;
  
    /** Creates a new instance of HealthRecordsResource */
    public HealthRecordsResource() {
    }

    /**
     * Get method for retrieving a collection of HealthRecord instance in XML format.
     *
     * @return an instance of HealthRecordsConverter
     */
//    @GET
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public HealthRecordsConverter get(
//        @QueryParam("start") @DefaultValue("0") int start,
//        @QueryParam("max") @DefaultValue("10") int max,
//        @QueryParam("expandLevel") @DefaultValue("1") int expandLevel,
//        @QueryParam("query") @DefaultValue("SELECT e FROM HealthRecord e") String query
//    ) {
//        PersistenceService persistenceSvc = PersistenceService.getInstance();
//        try {
//            persistenceSvc.beginTx();
//            return new HealthRecordsConverter(getEntities(start, max, query), uriInfo.getAbsolutePath(), expandLevel);
//        } finally {
//            persistenceSvc.commitTx();
//            persistenceSvc.close();
//        }
//    }

    /**
     * Post method for creating an instance of HealthRecord using XML as the input format.
     *
     * @param data an HealthRecordConverter entity that is deserialized from an XML stream
     * @return an instance of HealthRecordConverter
     */
//    @POST
//    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    public Response post(HealthRecordConverter data) {
//        PersistenceService persistenceSvc = PersistenceService.getInstance();
//        try {
//            persistenceSvc.beginTx();
//            EntityManager em = persistenceSvc.getEntityManager();
//            HealthRecord entity = data.resolveEntity(em);
//            createEntity(data.resolveEntity(em));
//            persistenceSvc.commitTx();
//            return Response.created(uriInfo.getAbsolutePath().resolve(entity.getHealthRecordId() + "/")).build();
//        } finally {
//            persistenceSvc.close();
//        }
//    }

    /**
     * Returns a dynamic instance of HealthRecordResource used for entity navigation.
     *
     * @return an instance of HealthRecordResource
     */
    @Path("{healthRecordId}/")
    public HealthRecordResource getHealthRecordResource(@PathParam("healthRecordId") Long healthRecordId) {
        // check user has access right to this resource before forwarding.
        authorizeFor(healthRecordId);
        HealthRecordResource resource = resourceContext.getResource(HealthRecordResource.class);
        resource.setId(healthRecordId);
        return resource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of HealthRecord instances
     */
//    protected Collection<HealthRecord> getEntities(int start, int max, String query) {
//        EntityManager em = PersistenceService.getInstance().getEntityManager();
//        return em.createQuery(query).setFirstResult(start).setMaxResults(max).getResultList();
//    }

    /**
     * Persist the given entity.
     *
     * @param entity the entity to persist
     */
//    protected void createEntity(HealthRecord entity) {
//        entity.setHealthRecordId(null);
//        EntityManager em = PersistenceService.getInstance().getEntityManager();
//        em.persist(entity);
//        User user = entity.getUser();
//        if (user != null) {
//            user.getHealthRecords().add(entity);
//        }
//    }


    /**
     * Authorization Check
     *
     * <p>If a patient is logged on viewing their own record, make sure they are
     * accessing their own records. Otherwise, throw 403 Forbidden.
     *
     *
     * @param healthRecordId
     */
    public void authorizeFor(Long healthRecordId) {
        if (securityContext.isUserInRole(UserConfig.ROLE_PATIENT) ||
            securityContext.isUserInRole(UserConfig.ROLE_CARETAKER))
        {
            /* PATIENT and CARETAKER users only authorized for health data resources */
            if ( !getAuthenticatedUser().authorizedToAccessHealthRecord(healthRecordId) ) {
                logger.error("Attempted disallowed patient data access by {}", securityContext.getUserPrincipal());
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            } /* else allowed to access the record */
        } else {
            logger.error("Invalid patient access attempt without Patient role by {}", securityContext.getUserPrincipal());
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
    }

    /**
     * @return an Authenticated User.
     */
    public User getAuthenticatedUser() {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        EntityManager em = persistenceSvc.getEntityManager();
        try {
            // persistenceSvc.beginTx(); -- Transaction already exists in *some* callers.
            return (User) em.createNamedQuery("User.findByUsername")
                .setParameter("username", getUsername())
                .getSingleResult();
        } finally {
            PersistenceService.getInstance().close();
        }
    }

    /**
     * @return Username from Security Context if authenticated, otherwise null.
     */
    public String getUsername() {
        if (securityContext != null && securityContext.getUserPrincipal() != null) {
            return securityContext.getUserPrincipal().getName();
        }
        return null;
    }

}
