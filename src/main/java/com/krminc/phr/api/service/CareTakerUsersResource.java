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

import com.krminc.phr.api.converter.UsersConverter;
import com.krminc.phr.api.service.util.ServiceUtils;
import com.krminc.phr.core.AppConfig;
import com.krminc.phr.core.UserConfig;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.HealthRecord;
import com.krminc.phr.domain.HealthrecordRequest;
import com.krminc.phr.domain.User;
import com.sun.jersey.api.core.ResourceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cmccall
 */
public class CareTakerUsersResource {

    final Logger logger = LoggerFactory.getLogger(CareTakerUsersResource.class);

    /**
     *
     */
    @Context
    protected UriInfo uriInfo;
    /**
     *
     */
    @Context
    protected ResourceContext resourceContext;
    /**
     *
     */
    @Context
    protected SecurityContext securityContext;
    /**
     *
     */
    @Context
    protected transient HttpServletRequest servletRequest;

    private User localUser;

    /**
     *
     */
    public CareTakerUsersResource() {
    }


    /**
     * Get method for retrieving a collection of User instance in XML format.
     *
     * @param start 
     * @param desc
     * @param max
     * @param orderBy
     * @param username
     * @param name
     * @return an instance of UsersConverter
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public UsersConverter get(
        @QueryParam("start") @DefaultValue("0") int start,
        @QueryParam("max") @DefaultValue("10") int max,
        @QueryParam("orderBy") @DefaultValue("name") String orderBy,
        @QueryParam("desc") @DefaultValue("0") int desc,
        @QueryParam("u") @DefaultValue("") String username,
        @QueryParam("e") @DefaultValue("") String name
    ) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new UsersConverter(
                    getEntities(start, max, orderBy, desc, username, name),
                    uriInfo.getAbsolutePath(),
                    Api.DEFAULT_EXPAND_LEVEL + 2); //expand deep to pull in healthrecordID
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of UserResource used for entity navigation.
     *
     * @param id
     * @return an instance of UserResource
     */
    @Path("{userId}/")
    public UserResource getUserResource(@PathParam("userId") Long id) {
        UserResource resource = resourceContext.getResource(UserResource.class);
        resource.setId(id);
        return resource;
    }

    /**
     * Act as in logged in as user
     *
     * @param healthRecord
     * @return
     */
    @Path("access/{hrid}/")
    @GET
    public Response getAccess (@PathParam ("hrid") Long healthRecord) {
        if (healthRecord != null)
        {
            HttpSession session = servletRequest.getSession(true);
            session.setAttribute("healthRecordId", healthRecord);

            return Response.seeOther(uriInfo.getBaseUri().resolve("." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecord + "/dashboard")).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }

    /**
     * Remove a user from your care
     *
     * @param healthRecordIdToRemove
     * @return
     */
    @Path("remove/{hrid}/")
    @GET
    public JSONObject removeAccess (@PathParam ("hrid") Long healthRecordIdToRemove) {
        JSONObject jsonResult = new JSONObject();
        Boolean returnType = false;

        if (healthRecordIdToRemove != null)
        {

            PersistenceService persistenceSvc = PersistenceService.getInstance();

            try {
                EntityManager em = PersistenceService.getInstance().getEntityManager();
                User self = null;

                try {
                    persistenceSvc.beginTx();

                    //get user
                     self = getLocalUser(); //requires tx started

                     //find the correct hr
                     List<HealthRecord> healthRecords = self.getHealthRecords();
                     boolean shouldRemove = false;
                     HealthRecord toRemove = null;

                     for (HealthRecord hr : healthRecords) {
                         //prepare to remove link
                         if (healthRecordIdToRemove.compareTo(hr.getHealthRecordId()) == 0) {
                             toRemove = hr;
                             logger.debug("Ready to remove healthRecord {} from user {}", hr, self);
                             shouldRemove = true;
                         }
                     }

                     if (shouldRemove) {
                        healthRecords.remove(toRemove);
                        self.setHealthRecords(healthRecords);
                        em.flush();
                        persistenceSvc.commitTx();
                        returnType = true;
                     } else {
                         returnType = false;
                         persistenceSvc.rollbackTx();
                     }
                }
                catch (NoResultException ex) {
                    logger.error("Unable to find remove access for HRID: {}", healthRecordIdToRemove);
                    returnType = false;
                }

            }
            catch (Exception ex) {
                logger.error("removeAccess encountered exception: {}", ex);
                returnType = false;
            } finally {
                persistenceSvc.close();
            }
        } else {
            returnType = false;
        }

        try {
            if (returnType) {
                jsonResult.put("success", "Successfully removed patient from your care.");
            } else {
                jsonResult.put("error", "Unable to find that user. Please try again or contact an administrator if the problem persists.");
            }
        }
        catch (JSONException ex) {
            throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
        }
        return jsonResult;
    }

    /**
     * Ask for access to a patient's record
     *
     * @param username
     * @param email
     * @return
     */
    @Path("add/")
    @GET
    public JSONObject requestAccess(
        @QueryParam("u") @DefaultValue("") String username,
        @QueryParam("e") @DefaultValue("") String email
    ) {
        JSONObject jsonResult = new JSONObject();

        User patient = null;
        boolean error = false;
        if (!username.isEmpty()) {

            //query on username
            PersistenceService persistenceSvc = PersistenceService.getInstance();

            try {
                EntityManager em = PersistenceService.getInstance().getEntityManager();

                try {
                    persistenceSvc.beginTx();

                     patient = (User) em.createNamedQuery("User.findByUsername")
                        .setParameter("username", username)
                        .setMaxResults(1)
                        .getSingleResult();

                     persistenceSvc.commitTx();
                     logger.debug("Found User by username: {}", patient);
                }
                catch (NoResultException ex) {
                    logger.error("Unable to find User object for username: {}", username);
                    error = true;
                    jsonResult.put("error", "Unable to find user with that name.");
                }

            }
            catch (Exception ex) {
                logger.error("requestAccess encountered exception: {}", ex);
                error = true;
                try {
                    jsonResult.put("error", "Unable to find user with that name.");
                }
                catch (JSONException ex2) {
                    throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
                }
            } finally {
                persistenceSvc.close();
            }

        } else if (!email.isEmpty()) {

            //query on email
            PersistenceService persistenceSvc = PersistenceService.getInstance();

            try {
                EntityManager em = persistenceSvc.getEntityManager();

                try {
                    persistenceSvc.beginTx();

                     patient = (User) em.createNamedQuery("User.findByEmail")
                        .setParameter("email", email)
                        .setMaxResults(1)
                        .getSingleResult();

                     persistenceSvc.commitTx();
                     logger.debug("Found User by email: {}", patient);
                }
                catch (NoResultException ex) {
                    error = true;
                    logger.error("Unable to find User object for email: {}", email);
                    jsonResult.put("error", "Unable to find user with that email address.");
                }

            }
            catch (Exception ex) {
                error = true;
                logger.error("requestAccess encountered exception: {}", ex);
                try {
                    jsonResult.put("error", "Unable to find user with that email address.");
                }
                catch (JSONException ex2) {
                    throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
                }
            } finally {
                persistenceSvc.close();
            }

        } else {
            //no params -- cant do anything
            error = true;
            try {
                jsonResult.put("error", "Invalid search parameters.");
            }
            catch (JSONException ex) {
                throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
            }
        }
        
        //check that we don't already have an outstanding requests for this patient
        if (!error) { //dont bother if we've already got issues
            PersistenceService persistenceSvc = PersistenceService.getInstance();
            List<HealthrecordRequest> requests = null;
            
            try {
                EntityManager em = persistenceSvc.getEntityManager();
                try {
                    //grab all existing requests made by this caretaker
                    requests = (List<HealthrecordRequest>) em.createNamedQuery("HealthrecordRequest.findByUserIdRequestor")
                        .setParameter("userIdRequestor", getLocalUser().getUserId())
                        .getResultList();
                }
                catch (NoResultException ex) {
                    //ignore -- this is fine
                }

            }
            catch (Exception ex) {
                error = true;
                logger.error("requestAccess encountered exception: {}", ex);
                try {
                    jsonResult.put("error", "Unable to lookup current outstanding requests.");
                }
                catch (JSONException ex2) {
                    throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
                }
            } finally {
                persistenceSvc.close();
            }
            
            if ((requests != null) && (!error)) {
                for (HealthrecordRequest hrr : requests) {
                    //check each request's requested record id - does it match the current request attempt?
                    if (hrr.getRecIdRequested() == patient.getPrimaryHealthRecord().getHealthRecordId()) {
                        error = true;
                        try {
                            jsonResult.put("error", "A request for this patient's data already exists.");
                        }
                        catch (JSONException ex2) {
                            throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
                        }
                    }
                }
            }
        }
        
        //check patient is not already under our care
        if (!error) {
            for (User u : patient.getPrimaryHealthRecord().getUserList()) {
                if (u.getUserId().compareTo(getLocalUser().getUserId()) == 0) {
                    error = true;
                    try {
                        jsonResult.put("error", "Patient is already under your care.");
                    }
                    catch (JSONException ex) {
                        throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
                    }
                }
            }
        }

        //createDbRequest does the real work
        if (!error && createDbRequest(patient.getPrimaryHealthRecord().getHealthRecordId() )) {
            try {
                jsonResult.put("success", "Your request has been sent.");
            }
            catch (JSONException ex) {
                throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
            }
        }

        return jsonResult;
    }
    
    /**
     * List requests made by current user
     *
     * @return JSONArray of requests, or empty JSONArray
     */
    @Path("listRequests/")
    @GET
    public JSONArray listAccessRequests() {
        List<String> users = getUsernamesRequestedByUser();
        
        if ((users != null) && (! users.isEmpty() )) {
            return new JSONArray(users);
        } else {
            return new JSONArray();
        }
    }

    //helper method for requestAccess
    private boolean createDbRequest(Long requestedHealthrecordId) {
        HealthrecordRequest request = null;
        try {
            request = new HealthrecordRequest(requestedHealthrecordId.intValue(), getLocalUser().getUserId().intValue());
        }
        catch (Exception ex){
            throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
        }

        //persist request here via EM
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            request = em.merge(request);
            persistenceSvc.commitTx();
        } catch (Exception ex) {
            //ignored
        } finally {
            persistenceSvc.close();
        }

        return (request.getRequestId() > 0);
    }
    
    //helper method for listAccessRequests
    private List<String> getUsernamesRequestedByUser() {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        List<HealthrecordRequest> requests = null;
        List<String> usernames = new ArrayList<String>();
        try {
            EntityManager em = persistenceSvc.getEntityManager();
            requests = em.createNamedQuery("HealthrecordRequest.findByUserIdRequestor")
                    .setParameter("userIdRequestor", getLocalUser().getUserId())
                    .getResultList();
            
            for (HealthrecordRequest req : requests) {
                HealthRecord rec = (HealthRecord) em.createNamedQuery("HealthRecord.findByHealthRecordId")
                            .setParameter("healthRecordId", req.getRecIdRequested())
                            .getSingleResult();
                usernames.add(rec.getUser()
                                .getUsername()
                        ); //this is not optimized at all
            }
            
        } catch (Exception ex) {
            requests = null;
            logger.error("ex: {}", ex);
        } finally {
            persistenceSvc.close();
        }
        
        return usernames;
    }

    /**
     * Returns count of users belonging to the care taker
     * 
     * @param username
     * @param name
     * @return
     */
    @Path("count/")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject getCount(
        @QueryParam("u") @DefaultValue("") String username,
        @QueryParam("e") @DefaultValue("") String name
    ) {
//SELECT COUNT(DISTINCT u.user_id)
//FROM user_users_rec_healthrecord h JOIN user_users u ON h.user_id = u.user_id JOIN user_roles r ON UPPER(LTRIM(RTRIM(u.username))) = UPPER(LTRIM(RTRIM(r.username)))
//WHERE h.rec_id IN (SELECT rec_id FROM user_users_rec_healthrecord WHERE user_id = 3)
//AND r.role = "ROLE_PATIENT";

        StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT u.user_id) ");
        query.append("FROM user_users_rec_healthrecord h JOIN user_users u ON h.user_id = u.user_id ");
        query.append("JOIN user_roles r ON UPPER(LTRIM(RTRIM(u.username))) = UPPER(LTRIM(RTRIM(r.username))) ");
        query.append("WHERE h.rec_id IN (SELECT rec_id FROM user_users_rec_healthrecord WHERE user_id = ");

        Long result = 0L;
        Boolean hasFilterClause = true;

        if (this.getLocalUser() != null) {
            query.append("'" + this.getLocalUser().getUserId() + "' )");
        } else {
            throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
        }

        if (!username.isEmpty()){
            if (ServiceUtils.hasValidCharacters(username)) {
                if (!hasFilterClause){
                    query.append(" WHERE");
                    hasFilterClause = true;
                } else {
                    query.append(" AND");
                }
                username = "%" + username + "%";
                query.append(" u.username LIKE '" +username+"'");
            } else {
                throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
            }
        }

        if (!name.isEmpty()){
            if (ServiceUtils.hasValidCharacters(name)) {
                if (!hasFilterClause){
                    query.append(" WHERE");
                    hasFilterClause = true;
                } else {
                    query.append(" AND");
                }
                name = "%" + name + "%";
                query.append(" (u.first_name LIKE '" +name+"'");
                query.append(" OR");
                query.append(" u.last_name LIKE '" +name+"')");
            } else {
                throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
            }
        }

        //force only able to select PATIENT role users
        if (!hasFilterClause){
            query.append(" WHERE");
            hasFilterClause = true;
        } else {
            query.append(" AND");
        }
        query.append(" r.role IN ('"+UserConfig.ROLE_PATIENT+"')");

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();

//            logger.debug("Using query caretaker patient count query: {}", query);

            List resultD = (List)em.createNativeQuery(query.toString()).getSingleResult();
            result = (Long)resultD.get(0);
        } finally {
            persistenceSvc.close();
        }

        JSONObject jsonResult = new JSONObject();
        try {
            jsonResult.put("count", result);
        }
        catch (JSONException ex) {
            throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
        }
        return jsonResult;
    }

    /**
     * Returns all the entities associated with this care taker
     *
     * @param start 
     * @param desc
     * @param max
     * @param username
     * @param orderBy
     * @param name
     * @return a collection of User instances
     */
    protected Collection<User> getEntities(
            int start, int max, String orderBy, int desc, String username, String name) {

//SELECT DISTINCT u.*
//FROM user_users_rec_healthrecord h JOIN user_users u ON h.user_id = u.user_id JOIN user_roles r ON UPPER(LTRIM(RTRIM(u.username))) = UPPER(LTRIM(RTRIM(r.username)))
//WHERE h.rec_id IN (SELECT rec_id FROM user_users_rec_healthrecord WHERE user_id = 3)
//AND r.role = "ROLE_PATIENT";

        StringBuilder query = new StringBuilder("SELECT DISTINCT u.* ");
        query.append("FROM user_users_rec_healthrecord h JOIN user_users u ON h.user_id = u.user_id ");
        query.append("JOIN user_roles r ON UPPER(LTRIM(RTRIM(u.username))) = UPPER(LTRIM(RTRIM(r.username))) ");
        query.append("WHERE h.rec_id IN (SELECT rec_id FROM user_users_rec_healthrecord WHERE user_id = ");

        Long result = 0L;
        Boolean hasFilterClause = true;

        if (this.getLocalUser() != null) {
            query.append("'" + this.getLocalUser().getUserId() + "' )");
        } else {
            throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
        }

        if(start<0) start=0;

        Boolean hasMax = true;
        if( max < 1 ){
            hasMax = false;
            start = 0;
        }

        if (!username.isEmpty()){
            if (ServiceUtils.hasValidCharacters(username)) {
                if (!hasFilterClause){
                    query.append(" WHERE");
                    hasFilterClause = true;
                } else {
                    query.append(" AND");
                }
                username = "%" + username + "%";
                query.append(" u.username LIKE '" +username+"'");
            } else {
                throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
            }
        }

        if (!name.isEmpty()){
            if (ServiceUtils.hasValidCharacters(name)) {
                if (!hasFilterClause){
                    query.append(" WHERE");
                    hasFilterClause = true;
                } else {
                    query.append(" AND");
                }
                name = "%" + name + "%";
                query.append(" (u.first_name LIKE '" +name+"'");
                query.append(" OR");
                query.append(" u.last_name LIKE '" +name+"')");
            } else {
                throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
            }
        }
        
        //force only able to select PATIENT role users
        if (!hasFilterClause){
            query.append(" WHERE");
            hasFilterClause = true;
        } else {
            query.append(" AND");
        }
        query.append(" r.role IN ('"+UserConfig.ROLE_PATIENT+"')");

        Boolean wasOrdered = false;
        if(orderBy.equalsIgnoreCase("username")){
            query.append(" ORDER BY u.username");
            wasOrdered = true;
        } else if(orderBy.equalsIgnoreCase("name")){
            query.append(" ORDER BY u.last_name, u.first_name, u.middle_name");
            wasOrdered = true;
        }

        if (wasOrdered) {
            if (desc>0) {
                query.append(" DESC");
            } else {
                query.append(" ASC");
            }
        }

//        logger.error("Using query caretaker patient lookup query: {}", query);

        EntityManager em = PersistenceService.getInstance().getEntityManager();

        Query filterQuery = em.createNativeQuery(query.toString(), User.class);

        if (hasMax) filterQuery.setMaxResults(max);

        List<User> users = (List<User>)filterQuery.setFirstResult(start).getResultList();
        
        return users;

    }

    private User getLocalUser() {
        if (localUser == null) {
            //get user id from db
            String localUserName = securityContext.getUserPrincipal().getName();

            PersistenceService persistenceSvc = PersistenceService.getInstance();

            if (localUserName != null && !localUserName.isEmpty()) {
                try {
                    EntityManager em = PersistenceService.getInstance().getEntityManager();

                    //persistenceSvc.beginTx(); //not needed -- called from transaction envt
                    try {
                         localUser = (User) em.createNamedQuery("User.findByUsername")
                            .setParameter("username", localUserName)
                            .setMaxResults(1)
                            .getSingleResult();

                         logger.debug("Found User by username: {}", localUser);
                    }
                    catch (NoResultException ex) {
                        localUser = null;
                        logger.debug("Unable to find User object for: {}", localUserName);
                    }

                }
                catch (Exception ex) {
                    localUser = null;
                    logger.error("getUserId encountered exception: {}", ex);
                } finally {
                    PersistenceService.getInstance().close();
                }

            } else {
                //no user in security context -- should not happen
                logger.error("Unable to find username from security context");
                localUser = null;
            }

            logger.debug("Successfully returning user: {}", localUser);

            return localUser;

        } else {
            return localUser;
        }
    }

}
