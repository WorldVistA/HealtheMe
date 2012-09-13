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
import com.krminc.phr.api.service.clinical.*;
import com.krminc.phr.api.vitals.service.*;
import com.krminc.phr.core.VitalConfig;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.Address;
import com.krminc.phr.domain.HealthRecord;
import com.krminc.phr.domain.User;
import com.krminc.phr.domain.vitals.Height;
import com.krminc.phr.domain.vitals.Weight;
import com.krminc.phr.core.AppConfig;
import com.krminc.phr.core.UserConfig;
import com.krminc.phr.domain.*;
import com.sun.jersey.api.core.ResourceContext;
import java.net.URI;
import java.text.DateFormat;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.WebApplicationException;
import javax.persistence.NoResultException;
import javax.persistence.EntityManager;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Health Record API RESTful resource class.
 *
 * @author cmccall
 * @author Daniel Shaw (dshaw.com)
 */
public class HealthRecordResource {

    final Logger logger = LoggerFactory.getLogger(HealthRecordResource.class);

    @Context
    protected UriInfo uriInfo;
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected SecurityContext securityContext;
    
    protected Long id;


    public HealthRecordResource() {
    }


    public void setId(Long id) {
        this.id = id;
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
     * @param id identifier for the entity
     * @return an instance of HealthRecord
     */
    protected HealthRecord getEntity() {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            return (HealthRecord) em.find(HealthRecord.class, id);
            //return (HealthRecord) em.createQuery("SELECT e FROM HealthRecord e where e.recId = :recId").setParameter("recId", id).getSingleResult();
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

    @Path("image/")
    @GET
    @Produces("image/jpeg")
    public Response getImage() {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        byte[] image = null;
        final String staticDirectory = "./static/images/avatars/";
        final String maleAvatar = "default_avatar_male_126x126.jpg";
        final String femaleAvatar = "default_avatar_female_126x126.jpg";
        User user = null;
        HealthRecord hr = null;

       //logger.error("Attempting to serve user image!");

        try {
            persistenceSvc.beginTx();
            hr = getEntity();
            user = hr.getUser();
            image = user.getUserImage();
        } finally {
            persistenceSvc.close();
        }

        if (image != null && image.length > 0) {
            return Response.ok(image, "image/jpeg").build();
        } else {
            try {
            URI location = null;
            if (user != null) {
                if (hr.getGender().equalsIgnoreCase("F")) {
                    location = new URI(staticDirectory + femaleAvatar);
                } else {
                    location = new URI(staticDirectory + maleAvatar);
                }
            }
            //logger.error("location: " + location.toString());
            return Response.seeOther(location).build();
            }
            catch (Exception ex) {
                logger.error("Error building image path");
                return Response.serverError().build();
            }
        }
    }

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
     * Get method for retrieving Patient info identified by id in JSON format.
     *
     * @return an JSONObject of gender, DOB, marriage status, blood type, organ donor status, first/middle/last name, telephone numbers, and address
     */
    @Path("profile/")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject getPatientProfile() {

        HealthRecord hr = null;
        User usr = null;
        Address adrs = null;
        JSONObject jSONObject = new JSONObject();

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            hr = getEntity();
            usr =  hr.getUser();
            adrs = usr.getAddresses().get(0);
            jSONObject.put("firstName",usr.getFirstName());
            jSONObject.put("middleName", usr.getMiddleName());
            jSONObject.put("lastName", usr.getLastName());
            jSONObject.put("gender", hr.getFullGender());
            jSONObject.put("dateOfBirth", hr.getDateOfBirthString());
            jSONObject.put("maritalStatus", hr.getMaritalStatus());
            jSONObject.put("email", usr.getEmail());
            jSONObject.put("faxNum", usr.getFaxnum());
            jSONObject.put("telnumHome", usr.getTelnumHome());
            jSONObject.put("telnumMobile", usr.getTelnumMobile());
            jSONObject.put("telnumWork", usr.getTelnumWork());
            jSONObject.put("address1", adrs.getAddress1());
            jSONObject.put("address2", adrs.getAddress2());
            jSONObject.put("address3", adrs.getAddress3());
            jSONObject.put("city", adrs.getCity());
            jSONObject.put("state", adrs.getState());
            jSONObject.put("zip", adrs.getZip());
        } catch (JSONException ex) {
        } finally {
            PersistenceService.getInstance().close();
        }

        return jSONObject;
    }

    /**
     * Get method for retrieving Patient info identified by id in JSON format.
     *
     * @return an JSONObject of gender, DOB, marriage status, blood type, organ donor status, first/middle/last name, telephone numbers, and address
     */
    @Path("account/")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject getUserAccount() {

        HealthRecord hr = null;
        User usr = null;
        JSONObject jSONObject = new JSONObject();

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            hr = getEntity();
            usr =  hr.getUser();
            //username, last login, number of logins, preferred name, date account created, and email address
            jSONObject.put("username",usr.getUsername());
            jSONObject.put("lastLogin", usr.getLastLogin());
            jSONObject.put("numLogins", usr.getTotalLogin());
            jSONObject.put("preferredName", usr.getPreferredName());
            jSONObject.put("dateCreated", usr.getDateCreated());
            jSONObject.put("email", usr.getEmail());
        } catch (JSONException ex) {
        } finally {
            PersistenceService.getInstance().close();
        }

        return jSONObject;
    }
    
    /**
     * Get method for retrieving Patient info identified by id in JSON format.
     *
     * @return an JSONObject of gender, DOB, marriage status, blood type, organ donor status, first/middle/last name, telephone numbers, and address
     */
    @Path("preferences/")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject getPreferences() {

        HealthRecord hr = null;
        JSONObject jSONObject = new JSONObject();

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            hr = getEntity();
            UserPreferences pref = hr.getPreferences();
            String careNotebookString = "";
            if (pref != null) {
                careNotebookString = pref.getShowCarenotebookString();
            }
            jSONObject.put("careNotebook", careNotebookString);
        } catch (JSONException ex) {
        } finally {
            PersistenceService.getInstance().close();
        }

        return jSONObject;
    }

    //get list of those with access to a health record
    @Path("access/")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject getUsersWithAccess() {

        HealthRecord hr = null;
        JSONObject jSONObject = new JSONObject();

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            hr = getEntity();
            List<String> names = new ArrayList<String>();
            List<String> emails = new ArrayList<String>();
            List<String> phones = new ArrayList<String>();
            List<String> faxes = new ArrayList<String>();
            List<Long> ids = new ArrayList<Long>();
            List<User> users = hr.getUserList();
            for (User u : users) {
                if (u.getUserId() != getAuthenticatedUser().getUserId()) { //keep self out of access list
                    ids.add(u.getUserId());
                    names.add(u.getFullName());
                    emails.add(u.getEmail());
                    faxes.add(u.getFaxnum());

                    String phone = (u.getTelnumWork() == null)? u.getTelnumMobile() : u.getTelnumWork();
                    phones.add(phone);
                }
            }
            
            if (!ids.isEmpty()) {
                jSONObject.put("users", names);
                jSONObject.put("ids", ids);
                jSONObject.put("phones", phones);
                jSONObject.put("faxes", faxes);
                jSONObject.put("emails", emails);
            }
        } catch (JSONException ex) {
        } finally {
            PersistenceService.getInstance().close();
        }

        return jSONObject;
    }

    //remove access to a healthrecord
    @Path("removeAccess/{userId}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject removeAccess(
            @PathParam("userId") Long userId
            ) {

        HealthRecord localHR = getEntity();
        Long healthRecordIdToRemove = localHR.getHealthRecordId();

        JSONObject jsonResult = new JSONObject();
        Boolean returnType = false;

        if (healthRecordIdToRemove != null)
        {

            PersistenceService persistenceSvc = PersistenceService.getInstance();

            try {
                if (! securityContext.isUserInRole(UserConfig.ROLE_PATIENT)) throw new Exception("Not in patient role for removing access");
                
                EntityManager em = PersistenceService.getInstance().getEntityManager();
                User userToDisallow = null;
                User removingUser = null;

                try {
                    persistenceSvc.beginTx();

                    //get user
                     userToDisallow = getUserById(userId);
                     removingUser = getAuthenticatedUser();

                     //TODO check we are owner of HR
                     //if HR owner == removingUser

                     //find the correct hr
                     List<HealthRecord> healthRecords = userToDisallow.getHealthRecords();
                     boolean shouldRemove = false;
                     HealthRecord toRemove = null;

                    //make sure we aren't removing ourself
                    if (removingUser.getUserId().compareTo(userId) == 0) {
                        shouldRemove = false;
                        logger.debug("Preventing self-removal attempt id1 {} id2 {}", userToDisallow.getUserId(), userId);
                    } else {
                        for (HealthRecord hr : healthRecords) {
                            //prepare to remove link
                            if (healthRecordIdToRemove.compareTo(hr.getHealthRecordId()) == 0) {
                                toRemove = hr;
                                logger.debug("Ready to remove healthRecord {} from user {}", hr, userToDisallow);
                                shouldRemove = true;
                            }
                        }

                        if ( toRemove == null) {
                            logger.error("Unable to find matching hr to remove");
                        }
                    }

                     if (shouldRemove) {
                        healthRecords.remove(toRemove);
                        userToDisallow.setHealthRecords(healthRecords);
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
            jsonResult.put("status", returnType);
        }
        catch (JSONException ex) {
            throw new WebApplicationException(Response.Status.PRECONDITION_FAILED);
        }
        return jsonResult;

    }


    @Path("checkRequests/")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject getHealthRecordRequests() {
        JSONObject jSONObject = new JSONObject();
        List<HealthrecordRequest> requests = new ArrayList<HealthrecordRequest>();
        boolean foundRequests = false;
        List<String> requestingUsers = new ArrayList<String>();
        List<Integer> requestIds = new ArrayList<Integer>();

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            EntityManager em = PersistenceService.getInstance().getEntityManager();

            persistenceSvc.beginTx();
            Long healthRecordId = getEntity().getHealthRecordId();
            try {
                requests = em.createNamedQuery("HealthrecordRequest.findByRecIdRequested")
                    .setParameter("recIdRequested", healthRecordId)
                    .getResultList();

                if (!requests.isEmpty()) foundRequests = true;
            }
            catch (NoResultException ex) {
                foundRequests = false;
                logger.error("No requests found for HealthrecordRequest.findByRecIdRequested: {}", ex);
            }

            if (foundRequests) {
                for (HealthrecordRequest request : requests) {
                    try {
                        User u = (User) em.createNamedQuery("User.findByUserId")
                            .setParameter("userId", request.getUserIdRequestor())
                            .setMaxResults(1)
                            .getSingleResult();

                        if (u != null) {
                            requestingUsers.add(u.getFullName());
                            requestIds.add(request.getRequestId());
                        }
                    }
                    catch (NoResultException ex) {
                        logger.error("Unable to find a user who requested hrid access");
                    }
                }
            }

        }
        catch (Exception ex) {
            foundRequests = false;
        } finally {
            persistenceSvc.close();
        }

        try {
            jSONObject.put("requests", foundRequests);

            if (foundRequests) {
                if (!requestingUsers.isEmpty()) jSONObject.put("users", requestingUsers);
                if (!requestIds.isEmpty()) jSONObject.put("ids", requestIds);
            }
        } catch (JSONException ex) {
            logger.error( "Error creating checkRequests JSONObject", ex);
        }

        return jSONObject;
    }

    //approve request to access a health record
    @Path("approveRequest/{requestId}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject approveRequest(
            @PathParam("requestId") Integer requestId
            ) {
        JSONObject jSONObject = new JSONObject();

        //Add the link to hr->user linkage table

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        EntityManager em = persistenceSvc.getEntityManager();
        String approvalRequest = new String();

        try {
            if (! securityContext.isUserInRole(UserConfig.ROLE_PATIENT)) throw new Exception("Not in patient role for approval request");
            
            persistenceSvc.beginTx();
            HealthrecordRequest approvedRequest = em.find(HealthrecordRequest.class, requestId);

            //ensure we are linking a request that is of our own record
            if (approvedRequest.getRecIdRequested() == getEntity().getHealthRecordId()) {

                //find the user we are giving access to hr for
                User approvedUser = em.find(
                        User.class,
                        new Long(approvedRequest.getUserIdRequestor())
                    );
                
                //find the hrid we want to give access to
                HealthRecord approvedRecord = em.find(
                        HealthRecord.class,
                        new Long(approvedRequest.getRecIdRequested())
                    );

                //add the healthrecord to user object
                List<HealthRecord> currentRecords = approvedUser.getHealthRecords();
                currentRecords.add(approvedRecord);
                approvedUser.setHealthRecords(currentRecords);

                //delete the request now that it is satisfied
                em.remove(approvedRequest);
            }

            persistenceSvc.commitTx();
            approvalRequest = "success";
        } catch (Exception ex) {
            logger.error("Exception encountered processing approval request: {}", ex);
            approvalRequest = "error";
        } finally {
            persistenceSvc.close();
        }

        try {
            jSONObject.put("status", approvalRequest);
        } catch (JSONException ex) {
            logger.error( "Error creating approveRequest JSONObject", ex);
        }

        return jSONObject;
    }

    //deny request to access a health record
    @Path("denyRequest/{requestId}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject denyRequest(
            @PathParam("requestId") Integer requestId
            ) {
        
        JSONObject jSONObject = new JSONObject();
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        EntityManager em = persistenceSvc.getEntityManager();
        String denyRequest = new String();

        try {
            if (! securityContext.isUserInRole(UserConfig.ROLE_PATIENT)) throw new Exception("Not in patient role for denial request");
            
            persistenceSvc.beginTx();
            HealthrecordRequest toDelete = em.find(HealthrecordRequest.class, requestId);

            //ensure we are deleting a request that is of our own record
            if (toDelete.getRecIdRequested() == getEntity().getHealthRecordId()) {
                em.remove(toDelete);
            }

            persistenceSvc.commitTx();
            denyRequest = "success";
        } catch (Exception ex) {
            logger.error("Exception encountered processing approval request: {}", ex);
            denyRequest = "error";
        } finally {
            persistenceSvc.close();
        }

        try {
            jSONObject.put("status", denyRequest);
        } catch (JSONException ex) {
            logger.error( "Error creating denyRequest JSONObject", ex);
        }

        return jSONObject;
    }

    /**
     * Get method for retrieving Patient health stats identified by id in JSON format.
     *
     * @return an JSONObject
     */
    //TODO implement stats healthRecordId-based namedQueries in height+weight classes
    @Path("stats/")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject getPatientStats() {
        JSONObject jSONObject = new JSONObject();

        PersistenceService persistenceSvc = PersistenceService.getInstance();
        Weight latestWeight = null;
        Height latestHeight = null;
        Boolean calculateBMI = true;
        try {
            EntityManager em = PersistenceService.getInstance().getEntityManager();

            persistenceSvc.beginTx();
            Long healthRecordId = getEntity().getHealthRecordId();
            try {
                latestHeight = (Height)em.createNamedQuery("Height.getLatestByHealthRecordId")
                    .setParameter("healthRecordId", healthRecordId)
                    .setMaxResults(1)
                    .getSingleResult();
            }
            catch (NoResultException ex) {
                calculateBMI = false;
            }

            try {
                latestWeight = (Weight)em.createNamedQuery("Weight.getLatestByHealthRecordId")
                    .setParameter("healthRecordId", healthRecordId)
                    .setMaxResults(1)
                    .getSingleResult();
            }
            catch (NoResultException ex) {
                calculateBMI = false;
            }

        }
        catch (Exception ex) {
            calculateBMI = false;
        } finally {
            PersistenceService.getInstance().close();
        }

        double bmi = 0;
        if (calculateBMI) {
            int inches = 1; //avoid div by 0
            double pounds = 0;
            try {
                inches = (latestHeight.getFeet() * 12) + latestHeight.getInches();
            }
            catch(Exception ex) {
                inches = 1;
            }
            try {
                if (latestWeight.getUnit().equalsIgnoreCase("lbs")) {
                    pounds = latestWeight.getWeight().intValue();
                } else if (latestWeight.getUnit().equalsIgnoreCase("kgs")) {
                    //convert kgs to pounds
                    pounds = latestWeight.getWeight().doubleValue() * 2.20462262;
                } else {
                    throw new Exception();
                }
            }
            catch(Exception ex) {
                pounds = 0;
            }

            if (inches>0 && pounds>0) {
                //bmi formula http://www.whathealth.com/bmi/formula.html
                bmi = pounds * 703;
                bmi = bmi / (inches * inches);
            }
        }

        try {
            if (bmi>0) {
                DecimalFormat twoDForm = new DecimalFormat("#.##");
                jSONObject.put("bmi", Double.valueOf(twoDForm.format(bmi)));
            }
        } catch (JSONException ex) {
            logger.warn( "Error creating BMI JSONObject", ex);
        }

        try {
            if (latestWeight != null) {
                jSONObject.put("weight", latestWeight.getWeight().toString().concat(" ").concat(latestWeight.getUnit().toLowerCase()));
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                jSONObject.put("weightDate", df.format(latestWeight.getObservedDate()));
            }
        } catch (JSONException ex) {
            logger.warn( "Error creating Weight JSONObject", ex);
        }

        try {
            if (latestHeight != null) {
                String strHeight = latestHeight.getFeet().toString().concat("' ");
                if (latestHeight.getInches() > 0) {
                    strHeight = strHeight.concat(latestHeight.getInches().toString().concat("\""));
                }
                jSONObject.put("height", strHeight);
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                jSONObject.put("heightDate", df.format(latestHeight.getObservedDate()));
            }
        } catch (JSONException ex) {
            logger.warn( "Error creating Height JSONObject", ex);
        }

        return jSONObject;
    }


    //CLINICAL Data Item Mappings
    /**
     * Returns a dynamic instance of CcrDocumentsResource used for entity navigation.
     *
     * @return an instance of CcrDocumentsResource
     */
    @Path("clinical/ccrs/")
    public CcrDocumentsResource getCcrDocumentsResource() {
        CcrDocumentsResource resource = resourceContext.getResource(CcrDocumentsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of CcrProblemsResource used for entity navigation.
     *
     * @return an instance of CcrProblemsResource
     */
    @Path("clinical/medicalevents/")
    public CcrProblemsResource getCcrProblemsResource() {
        CcrProblemsResource resource = resourceContext.getResource(CcrProblemsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of CcrAlertsResource used for entity navigation.
     *
     * @return an instance of CcrAlertsResource
     */
    @Path("clinical/allergies/")
    public CcrAlertsResource getCcrAlertsResource() {
        CcrAlertsResource resource = resourceContext.getResource(CcrAlertsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of CcrImmunizationsResource used for entity navigation.
     *
     * @return an instance of CcrImmunizationsResource
     */
    @Path("clinical/immunizations/")
    public CcrImmunizationsResource getCcrImmunizationsResource() {
        CcrImmunizationsResource resource = resourceContext.getResource(CcrImmunizationsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of CcrMedicationsResource used for entity navigation.
     *
     * @return an instance of CcrMedicationsResource
     */
    @Path("clinical/medications/")
    public CcrMedicationsResource getCcrMedicationsResource() {
        CcrMedicationsResource resource = resourceContext.getResource(CcrMedicationsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of CcrResultsResource used for entity navigation.
     *
     * @return an instance of CcrResultsResource
     */
    @Path("clinical/results/")
    public CcrResultsResource getCcrResultsResource() {
        CcrResultsResource resource = resourceContext.getResource(CcrResultsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of CcrResultsResource used for entity navigation.
     *
     * @return an instance of CcrResultsResource
     */
    @Path("clinical/results/{resultId}/resultTests/")
    public CcrResultTestsResource getCcrResultTestsResource(@PathParam("resultId") Long resultId) {
        CcrResultTestsResource resource = resourceContext.getResource(CcrResultTestsResource.class);
        resource.setHealthRecordId(id);

        CcrResultResource result = resourceContext.getResource(CcrResultResource.class);
        result.setId(resultId);

        resource.setCcrResultResource(result);
        return resource;
    }

    /**
     * Returns a dynamic instance of CcrVitalSignResultsResource used for entity navigation.
     *
     * @return an instance of CcrVitalSignResultsResource
     */
    @Path("clinical/vitals/")
    public CcrVitalSignResultsResource getCcrVitalSignResultsResource() {
        CcrVitalSignResultsResource resource = resourceContext.getResource(CcrVitalSignResultsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    @Path("clinical/vitals/{vitalType: .{2,3}?}/")
    public CcrVitalSignTestsResource getCcrVitalSignTestsResource(@PathParam("vitalType") String vitalType) {
        CcrVitalSignTestsResource resource = resourceContext.getResource(CcrVitalSignTestsResource.class);
        resource.setHealthRecordId(id);

        //translate vital type path param to DB param
        if (vitalType.equalsIgnoreCase("bp")) {
            vitalType = VitalConfig.VITAL_BLOODPRESSURE;
        } else if (vitalType.equalsIgnoreCase("bs")) {
            vitalType = VitalConfig.VITAL_GLUCOSE; //no clinical BS -- this is a lab
        } else if (vitalType.equalsIgnoreCase("bt")) {
            vitalType = VitalConfig.VITAL_TEMPERATURE;
        } else if (vitalType.equalsIgnoreCase("bw")) {
            vitalType = VitalConfig.VITAL_WEIGHT;
        } else if (vitalType.equalsIgnoreCase("hr")) {
            vitalType = VitalConfig.VITAL_PULSE;
        } else if (vitalType.equalsIgnoreCase("ht")) {
            vitalType = VitalConfig.VITAL_HEIGHT;
        } else if (vitalType.equalsIgnoreCase("pn")) {
            vitalType = VitalConfig.VITAL_PAIN;
        } else if (vitalType.equalsIgnoreCase("pf")) {
            vitalType = VitalConfig.VITAL_PEAKFLOW;
        } else if (vitalType.equalsIgnoreCase("bmi")) {
            vitalType = VitalConfig.VITAL_BMI;
        } else {
            vitalType = "NONMATCHED";
        }

        resource.setVitalType(vitalType);
        return resource;
    }

    /**
     * Returns a dynamic instance of AddressesResource used for entity navigation.
     *
     * @return an instance of AddressesResource
     */
    // TODO: Fix addresses resources.
    @Path("addresses/")
    public AddressesResource getAddressesResource() {
        AddressesResource resource = resourceContext.getResource(AddressesResource.class);
        //resource.setParent(getEntity().getUser());
        return resource;
    }

    /**
     * Returns a dynamic instance of AllergiesResource used for entity navigation.
     *
     * @return an instance of AllergiesResource
     */
    @Path("allergies/")
    public AllergiesResource getAllergiesResource() {
        AllergiesResource resource = resourceContext.getResource(AllergiesResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of ImmunizationsResource used for entity navigation.
     *
     * @return an instance of ImmunizationsResource
     */
    @Path("immunizations/")
    public ImmunizationsResource getImmunizationsResource() {
        ImmunizationsResource resource = resourceContext.getResource(ImmunizationsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of MedicationsResource used for entity navigation.
     *
     * @return an instance of MedicationsResource
     */
    @Path("medications/")
    public MedicationsResource getMedicationsResource() {
        MedicationsResource resource = resourceContext.getResource(MedicationsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of MedicalEventsResource used for entity navigation.
     *
     * @return an instance of MedicalEventsResource
     */
    @Path("medicalevents/")
    public MedicalEventsResource getMedicalEventsResource() {
        MedicalEventsResource resource = resourceContext.getResource(MedicalEventsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * @return a dynamic instance of RecordIdentifiersResource used for entity navigation.
     */
    @Path("recordidentifiers/")
    public RecordIdentifiersResource getRecordIdentifiersResource() {
        RecordIdentifiersResource resource = resourceContext.getResource(RecordIdentifiersResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of VisitsResource used for entity navigation.
     *
     * @return an instance of VisitsResource
     */
    @Path("visits/")
    public VisitsResource getVisitsResource() {
        VisitsResource resource = resourceContext.getResource(VisitsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    @Path("exercises/")
    public ExercisesResource getExercisesResource() {
        ExercisesResource resource = resourceContext.getResource(ExercisesResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of BloodPressuresResource.
     *
     * @return an instance of BloodPressuresResource
     */
    @Path("vitals/bloodpressure/")
    public BloodPressuresResource getBloodPressuresResource() {
        BloodPressuresResource resource = resourceContext.getResource(BloodPressuresResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of BloodSugarsResource.
     *
     * @return an instance of BloodSugarsResource
     */
    @Path("vitals/bloodsugar/")
    public BloodSugarsResource getBloodSugarResource() {
        BloodSugarsResource resource = resourceContext.getResource(BloodSugarsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of HeartRatesResource.
     *
     * @return an instance of HeartRatesResource
     */
    @Path("vitals/heartrate/")
    public HeartRatesResource getHeartRatesResource() {
        HeartRatesResource resource = resourceContext.getResource(HeartRatesResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of PainsResource.
     *
     * @return an instance of PainsResource
     */
    @Path("vitals/pain/")
    public PainsResource getPainsResource() {
        PainsResource resource = resourceContext.getResource(PainsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of PeakFlowsResource.
     *
     * @return an instance of PeakFlowsResource
     */
    @Path("vitals/peakflow/")
    public PeakFlowsResource getPeakFlowsResource() {
        PeakFlowsResource resource = resourceContext.getResource(PeakFlowsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of PulseOximetriesResource.
     *
     * @return an instance of PulseOximetriesResource
     */
    @Path("vitals/temperature/")
    public TemperaturesResource getTemperaturesResource() {
        TemperaturesResource resource = resourceContext.getResource(TemperaturesResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of WeightsResource.
     *
     * @return an instance of WeightsResource
     */
    @Path("vitals/weight/")
    public WeightsResource getWeightsResource() {
        WeightsResource resource = resourceContext.getResource(WeightsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * Returns a dynamic instance of HeightsResource.
     *
     * @return an instance of HeightsResource
     */
    @Path("vitals/height/")
    public HeightsResource getHeightsResource() {
        HeightsResource resource = resourceContext.getResource(HeightsResource.class);
        resource.setHealthRecordId(id);
        return resource;
    }

    /**
     * @return a User.
     */
    private User getUserById(Long userId) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        EntityManager em = persistenceSvc.getEntityManager();
        try {
            // persistenceSvc.beginTx(); -- Transaction already exists in *some* callers.
            return (User) em.createNamedQuery("User.findByUserId")
                .setParameter("userId", userId)
                .getSingleResult();
        } finally {
            PersistenceService.getInstance().close();
        }
    }

    /**
     * @return a User.
     */
    private User getAuthenticatedUser() {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        EntityManager em = persistenceSvc.getEntityManager();
        try {
            // persistenceSvc.beginTx(); -- Transaction already exists in *some* callers.
            return (User) em.createNamedQuery("User.findByUsername")
                .setParameter("username", securityContext.getUserPrincipal().getName())
                .getSingleResult();
        } finally {
            PersistenceService.getInstance().close();
        }
    }

}
