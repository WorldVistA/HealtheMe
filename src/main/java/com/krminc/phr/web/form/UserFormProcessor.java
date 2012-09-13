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
package com.krminc.phr.web.form;

import com.krminc.phr.core.AppConfig;
import com.krminc.phr.core.UserConfig;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.*;
import com.sun.jersey.api.core.ResourceContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles User form.
 *
 * @author Daniel Shaw (dshaw.com)
 */
public class UserFormProcessor {

    final Logger logger = LoggerFactory.getLogger(UserFormProcessor.class);

    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;

    public UserFormProcessor() {
    }

    

//    @HttpMethod(value="POST")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response post(
            @FormParam("username") String username,
//            @FormParam("userimage") java.io.InputStream userImage,
            @FormParam("password") String password,
            @FormParam("firstname") String firstName,
            @FormParam("middlename") String middleName,
            @FormParam("lastname") String lastName,
            // contact parameters
            @FormParam("email") String email,
            @FormParam("phonehome") String telnumHome,
            @FormParam("phonework") String telnumWork,
            @FormParam("phonemobile") String telnumMobile,
            @FormParam("fax") String faxnum,
            // address parameters
            @FormParam("address1") String address1,
            @FormParam("address2") String address2,
            @FormParam("address3") String address3,
            @FormParam("city") String city,
            @FormParam("state") String state,
            @FormParam("zip") String zip,
            // health record parameters
            @FormParam("year") int year,
            @FormParam("month") int month,
            @FormParam("day") int day,
            @FormParam("gender") String gender,
            // other parameters
            @FormParam("by") String createdByUsername,
            @FormParam("roles")  @DefaultValue(UserConfig.ROLE_PATIENT) List<String> roles,
            @FormParam("carenotebook") String careNotebook,
            @QueryParam("ajax") String ajax,
            @QueryParam("area") String area) {
        logger.debug("NEW USER: username {} role {}", username, roles);
        logger.debug("CREATED BY: username {}", createdByUsername);

        String status = "unknown";
        ArrayList<String> errors = new ArrayList<String>();
        String responseBaseUri = getResponseBaseUri(area);
        boolean ajaxResponse = (ajax != null ? true : false);
        logger.debug("USE AJAX RESPONSE: {}", ajaxResponse);
        boolean careNotebookVal = (careNotebook != null ? true : false);
        
        // Default user-related object parameters
        boolean isPatient = false;
        Boolean primary = Boolean.TRUE; // for primary address and health record.
        Date dateCreated = new Date();

        // Create user object.
        // UserId defined when persisted.
        User user = new User(username, firstName, middleName, lastName, dateCreated);
//
//        if (userImage != null)
//            user.setUserImage(userImage);

        user.setEmail(email);
        user.setTelnumHome(telnumHome);
        user.setTelnumWork(telnumWork);
        user.setTelnumMobile(telnumMobile);
        user.setFaxnum(faxnum);
        user.setTotalLogin(0);

        // Generate user roles. 
        // @DefaultValue parameter replacement
        if (roles == null || roles.isEmpty()) {
            roles = new ArrayList<String>();
            roles.add(UserConfig.ROLE_PATIENT);
        }

        List<UserRole> userRoles = new ArrayList<UserRole>();
        for (String role : roles) {
            userRoles.add(new UserRole(username, role));
            if (role.equalsIgnoreCase(UserConfig.ROLE_PATIENT)) {
                isPatient = true;
            }
        }

        //Create primary address record and health record associated with patient.
        //UserId assigned after User is generated.
        Address address = new Address(primary, address1, address2, address3, city, state, zip, dateCreated);
        logger.debug("Address {}", address);

        HealthRecord healthRecord = null;
        UserPreferences preferences = null;

        if (isPatient) {
            // User date of birth.
            // NOTE: remember Gregorian Calendar MONTH is 0-based, so subtract 1
            Calendar dob = null;
            if (isPatient) {
                try {
                    dob = new GregorianCalendar(year, month - 1, day);
                } catch (Exception e) {
                    logger.error("ERROR: invalid dob", e);
                    errors.add("dob");
                }
            }
            healthRecord = new HealthRecord(primary, gender, dob, dateCreated);
            
            preferences = new UserPreferences(createdByUsername);
            preferences.setShowCarenotebook(careNotebookVal);
            
            healthRecord.setPreferences(preferences);
        }

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            EntityManager em = PersistenceService.getInstance().getEntityManager();

            // Server-side username uniqueness check
            if (!em.createNamedQuery("User.findByUsername").setParameter("username", username).getResultList().isEmpty()) {
                //user with same username already exists
                logger.debug("Create User Failed due to duplicate username {}", username);
                status = "duplicate";
                errors.add(status);
                if (ajaxResponse) {
                    return Response.status(Status.CONFLICT).build();
                } else {
                    return Response.seeOther(uriInfo.getBaseUri()
                            .resolve(responseBaseUri+"/enroll?status="+status))
                            .build();
                }
            }

            persistenceSvc.beginTx();

            Long id = createUser(user, userRoles, address, preferences, healthRecord, em);
            String responsePath = "/users/" + id + "/";
            persistenceSvc.commitTx();
            status = "success";
            if (ajaxResponse) {
                return Response.created(uriInfo.getBaseUri()
                        .resolve(responseBaseUri+responsePath))
                        .build();
            } else {
                return Response.seeOther(uriInfo.getBaseUri()
                        .resolve(responseBaseUri+responsePath+"?status="+status))
                        .build();
            }
        } catch (Exception e) {
            logger.error("Create User Failed", e);
            errors.add("failed");
            status = "failed";
            if (ajaxResponse) {
                return Response.status(Status.BAD_REQUEST).build();
            } else {
                return Response.seeOther(uriInfo.getBaseUri()
                        .resolve(responseBaseUri+"/enroll?status="+status))
                        .build();
            }
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Persist User, Role, Address and HealthRecord info.
     *
     * @param user
     * @param role
     * @param address
     * @param healthRecord
     * @return userId
     */
    protected Long createUser(User user, List<UserRole> roles, Address address, UserPreferences preferences, HealthRecord healthRecord, EntityManager em) {
        ArrayList<HealthRecord> hrList = new ArrayList<HealthRecord>();

        hrList.add(healthRecord);
        user.setHealthRecords(hrList);
        
        user = em.merge(user);

        for (UserRole role : roles) {
            em.persist(role);
        }

        //Flush user record to ensure user id is assigned
        em.flush();

        //Assign user id to address and health record.
        Long userId = user.getUserId();
        logger.debug("{} User ID set {}", user, userId);
        
        address.setUserId(userId);
        em.persist(address);

        return userId;
    }

    /**
     * Lookup user by user id.
     *
     * @param userId
     * @return user
     */
    private User getUser(Long userId) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.find(User.class, userId);
    }

    /**
     * Ajax submit handler.
     */
    /**
     * Disable User Account.
     *
     * @return redirect
     */
    @Path("{userId}/disable/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response disableUserAccount(
            @PathParam("userId") Long userId,
            @FormParam("active") boolean active,
            @FormParam("by") String updatedByUsername,
            @QueryParam("area") String area) {
        String status = "unknown";
        String responseBaseUri = getResponseBaseUri(area);
        String redirectUri = responseBaseUri + "/users/" + userId + "/";

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            User user = em.find(User.class, userId);
            user.setActive(active);
            em.merge(user);
            persistenceSvc.commitTx();
            status = "updated&i=" + user.getUserId();
        } catch (Exception e) {
            logger.error("Disable User Failed", e);
            status = "failed";
        } finally {
            persistenceSvc.close();
        }
        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri + "?status=" + status)).build();
    }

    /**
     * Unlock User Account.
     *
     * @return redirect
     */
    @Path("{userId}/unlock/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response unlockUserAccount(
            @PathParam("userId") Long userId,
            @FormParam("by") String updatedByUsername,
            @QueryParam("area") String area) {
        String status = "unknown";
        String responseBaseUri = getResponseBaseUri(area);
        String redirectUri = responseBaseUri + "/users/" + userId + "/";

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            User user = em.find(User.class, userId);
            user.setIsLockedOut(false);

            //reset security mechanism fields
            user.setFailedPasswordAttempts(0);
            user.setFailedPasswordWindowStart(null);
            em.merge(user);
            persistenceSvc.commitTx();
            status = "updated&i=" + user.getUserId();
        } catch (Exception e) {
            logger.error("Unlock User Failed", e);
            status = "failed";
        } finally {
            persistenceSvc.close();
        }
        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri + "?status=" + status)).build();
    }

    @Path("{userId}/update/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postPatientUpdate(
            @PathParam("userId") Long userId,
            /* NOTE: username is not updateable */
            @FormParam("password") String password,
            @FormParam("firstname") String firstName,
            @FormParam("middlename") String middleName,
            @FormParam("lastname") String lastName,
            // contact parameters
            @FormParam("email") String email,
            @FormParam("phonehome") String telnumHome,
            @FormParam("phonework") String telnumWork,
            @FormParam("phonemobile") String telnumMobile,
            @FormParam("fax") String faxnum,
            // address parameters
            @FormParam("address1") String address1,
            @FormParam("address2") String address2,
            @FormParam("address3") String address3,
            @FormParam("city") String city,
            @FormParam("state") String state,
            @FormParam("zip") String zip,
            // health record parameters
            @FormParam("year") int year,
            @FormParam("month") int month,
            @FormParam("day") int day,
            @FormParam("gender") String gender,
            // other parameters
            @FormParam("by") String updatedByUsername,
            @FormParam("carenotebook") String careNotebook,
            @QueryParam("ajax") String ajax,
            @QueryParam("area") String area) {
        logger.debug("UPDATED USER: userId {} BY: username {}", userId, updatedByUsername);

        String status = "unknown";
        ArrayList<String> errors = new ArrayList<String>();

        boolean ajaxResponse = (ajax != null ? true : false);
        boolean careNotebookVal = (careNotebook != null ? true : false);
        logger.debug("AJAX RESPONSE: {}", ajaxResponse);

        String redirectUri = getResponseBaseUri(area)+"/users/"+userId+"/";

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = PersistenceService.getInstance().getEntityManager();

            User user = getUser(userId);
            user.setFirstName(firstName);
            user.setMiddleName(middleName);
            user.setLastName(lastName);

            user.setEmail(email);
            user.setTelnumHome(telnumHome);
            user.setTelnumWork(telnumWork);
            user.setTelnumMobile(telnumMobile);
            user.setFaxnum(faxnum);

            Address address = user.getPrimaryAddress();
            if (address == null || address.getUserId() == null) {
                address = new Address(null, userId, Boolean.TRUE, new Date());
            }

            address.setAddress1(address1);
            address.setAddress2(address2);
            address.setAddress3(address3);
            address.setCity(city);
            address.setState(state);
            address.setZip(zip);

            if (user.isPatient()) {
                HealthRecord healthRecord = user.getPrimaryHealthRecord();
                healthRecord.setGender(gender);
                try {
                    healthRecord.setDateOfBirth(new GregorianCalendar(year, month - 1, day));
                } catch (Exception e) {
                    logger.error("ERROR: invalid dob");
                    errors.add("dob");
                }
                
                UserPreferences preferences = healthRecord.getPreferences();
                if (preferences == null || preferences.getPreferenceId() == null) {
                    preferences = new UserPreferences(updatedByUsername);
                }
                preferences.setShowCarenotebook(careNotebookVal);
                healthRecord.setPreferences(preferences);
            }

            em.merge(user);

            persistenceSvc.commitTx();
            status = "updated";
            if (ajaxResponse) {
                return Response.status(Status.ACCEPTED).build();
            } else {
                return Response.seeOther(uriInfo.getBaseUri()
                        .resolve(redirectUri + "?status=" + status))
                        .build();
            }
        } catch (Exception e) {
            logger.error("Update User Failed", e);
            status = "failed";
            if (ajaxResponse) {
                return Response.status(Status.BAD_REQUEST).build();
            } else {
                return Response.seeOther(uriInfo.getBaseUri()
                        .resolve(redirectUri + "edit?status=" + status))
                        .build();
            }
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Reset User Password.
     *
     * @return redirect
     */
    @Path("{userId}/reset/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response resetUserPassword(
            @PathParam("userId") Long userId,
            @FormParam("password") String password,
            @FormParam("by") String updatedByUsername,
            @QueryParam("ajax") String ajax,
            @QueryParam("area") String area) {
        String status = "unknown";
        String redirectUri = getResponseBaseUri(area)+"/users/"+userId+"/";

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            User newUser = em.find(User.class, userId);
            newUser.setPassword(password);
            newUser.setRequiresReset(Boolean.TRUE);
            em.merge(newUser);
            persistenceSvc.commitTx();
            status = "updated";
            return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri + "?status=" + status)).build();
        } catch (Exception e) {
            logger.error("Password Reset Failed", e);
            status = "failed";
            return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri + "reset?status=" + status)).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Build response URI.
     */
    private String getResponseBaseUri(String area) {
        return "."+(area != null && area.equals("admin") ? AppConfig.PATH_ADMIN_ROOT : AppConfig.PATH_USERMANAGER_ROOT);
    }
}
