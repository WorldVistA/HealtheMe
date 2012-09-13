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

package com.krminc.phr.web.form;

import com.krminc.phr.api.service.util.ServiceUtils;
import com.krminc.phr.core.AppConfig;
import com.krminc.phr.core.UserConfig;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.HealthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import com.krminc.phr.domain.User;
import com.krminc.phr.domain.UserPreferences;
import com.sun.jersey.api.core.ResourceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author cmccall
 */
public class SelfFormProcessor {
    final Logger logger = LoggerFactory.getLogger(SelfFormProcessor.class);

    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;
    @Context
    HttpServletRequest req;

    public SelfFormProcessor() {
    }

    /**
     * Alter User Password.
     * Security on this method is handled at controller level via authorizeFor
     * Use asHealthRecord to interpret the path param as a healthRecord Id instead of user ID for contexts where uID unavailable
     *
     * @return redirect
     */
    @Path("{userId}/password/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterUserPassword(
        @PathParam("userId") Long userId,
        @FormParam("currentPassword") String currentPassword,
        @FormParam("newPassword") String newPassword,
        @FormParam("newPassword2") String newPassword2,
        @FormParam("redirectUri") String redirectUri,
        @FormParam("asHealthRecord") Boolean asHR
    ) {
        String status = "unknown";
        String hrid = null;
        User user = null;
        
        if (!newPassword.equals(newPassword2)) {
            //passwords do not match
            status = "nomatch";
        } else if (!ServiceUtils.isPassword(newPassword)) {
            //new password invalid
            status = "invalid";
        } else {
            PersistenceService persistenceSvc = PersistenceService.getInstance();
            if (asHR == null) asHR = false;
            try {
                persistenceSvc.beginTx();
                EntityManager em = persistenceSvc.getEntityManager();

                if (asHR) {
                    user = em.find(HealthRecord.class, userId).getUser();
                } else {
                    user = em.find(User.class, userId);
                }

                if (user == null) throw new Exception("Unable to locate user entity");

                if (user.isPatient()) {
                    if (hrid == null) {
                        if (asHR) {
                            hrid = userId.toString();
                        } else {
                            hrid = user.getPrimaryHealthRecord().getHealthRecordId().toString();
                            //hrid = user.getHealthRecords().get(0).getHealthRecordId().toString();
                        }
                    }

                    // patients will pass in their own redirect URL using own HRID generally
                    if (redirectUri == null || redirectUri.isEmpty()) {
                        redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + hrid + "/account";
                    }
                }

                if (user.testPassword(currentPassword) == false) {
                    throw new Exception("Incorrect password");
                } else if (user.getUsername().equalsIgnoreCase(newPassword)) {
                    //error if setting password with username
                    throw new Exception("Password matches username");
                }
                user.setPassword(newPassword);
                user.setRequiresReset(Boolean.FALSE);
                em.merge(user);
                persistenceSvc.commitTx();
                status = "updated";
            } catch (Exception e) {
                logger.error("Password Update Failed - " + e.getMessage());
                status = "failed";
            } finally {
                persistenceSvc.close();
            }
        }
        //mirror getFirstLogin() in Main.java here
        if ("updated".equals(status) && redirectUri.contains("passwordchange")) {
            //password has been updated on first login/forced change
            HttpSession session = req.getSession(); //create session if none exists
            if (session != null)
                session.setAttribute("needsreset", "false");
                if (req.isUserInRole(UserConfig.ROLE_PATIENT) || req.isUserInRole(UserConfig.ROLE_CARETAKER)) {
                    if (hrid != null) {
                        session.setAttribute("healthRecordId", hrid);
                        if (req.isUserInRole(UserConfig.ROLE_PATIENT)) {
                            UserPreferences pref = user.getPrimaryHealthRecord().getPreferences();
                            boolean showCN;
                            if (pref != null) {
                                showCN = (pref.getShowCarenotebook() == true) ? true : false;
                            } else {
                                showCN = false;
                            }
                            session.setAttribute("showCareNotebook", showCN);
                        }
                    } else {
                        //needs health record id in session to perform data manipulation
                        if (req.isUserInRole(UserConfig.ROLE_PATIENT)) {
                            logger.error("Found null healthrecord id for patient.");
                            status = "failed";
                        }
                    }
                }
        }
        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri + "?pwStatus=" + status)).build();
    }

    /**
     * Alter User Account Info
     * Security on this method is handled at controller level via authorizeFor
     *
     * @return redirect
     */
    @Path("{healthRecordId}/account/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterUserAccount(
        @PathParam("healthRecordId") Long healthRecordId,
        @FormParam("email") String email,
        @FormParam("preferredName") String preferredName
    ) {
        String status = "unknown";
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/account";

        if (!ServiceUtils.isEmail(email)) {
            status = "badmail";
            return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri + "?acctStatus=" + status)).build();
        }

        if (!ServiceUtils.hasAlphaCharacters(preferredName)) {
            status = "badname";
            return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri + "?acctStatus=" + status)).build();
        }

        if (email.isEmpty()) {
            status = "nomail";
            return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri + "?acctStatus=" + status)).build();
        }

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            User user = em.find(HealthRecord.class, healthRecordId).getUser();

            if (!user.getEmail().equals(email)) {
                user.setEmail(email);
            }

            if (!preferredName.isEmpty() && !user.getPreferredName().equals(preferredName)) {
                user.setPreferredName(preferredName);
            }

            em.merge(user);
            persistenceSvc.commitTx();
            status = "updated";
            return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri + "?acctStatus=" + status)).build();
        } catch (Exception e) {
            logger.error("Account Update Failed", e);
            status = "failed";
            return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri + "?acctStatus=" + status)).build();
        } finally {
            persistenceSvc.close();
        }
    }

    /**
     * Alter User Preference Info
     * Security on this method is handled at controller level via authorizeFor
     *
     * @return redirect
     */
    @Path("{healthRecordId}/preferences/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterHealthRecordPreferences(
        @PathParam("healthRecordId") Long healthRecordId,
        @FormParam("carenotebook") String careNotebook
    ) {
        String status = "unknown";
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/account";
        boolean careNotebookVal = (careNotebook != null ? true : false);
        
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            EntityManager em = persistenceSvc.getEntityManager();
            HealthRecord hr = em.find(HealthRecord.class, healthRecordId);
            UserPreferences preferences = hr.getPreferences();
            
            String username = req.getRemoteUser();
            boolean updateHRisRequired = false;
            
            if (preferences == null) {
                preferences = new UserPreferences(username);
                preferences.setShowCarenotebook(careNotebookVal);
                hr.setPreferences(preferences);
                updateHRisRequired = true;
            } else {
                preferences.setLastUpdatedUser(username);
                preferences.setShowCarenotebook(careNotebookVal);
            }

            if (updateHRisRequired) em.merge(hr);
            em.merge(preferences);
            persistenceSvc.commitTx();
            status = "updated";
            
            //update session value
            req.getSession(Boolean.FALSE).setAttribute("showCareNotebook", careNotebookVal);
            
            return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri + "?preferenceStatus=" + status)).build();
        } catch (Exception e) {
            logger.error("Account Preference Update Failed", e);
            status = "failed";
            return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri + "?preferenceStatus=" + status)).build();
        } finally {
            persistenceSvc.close();
        }
    }
    
}
