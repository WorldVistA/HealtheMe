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

import com.krminc.phr.api.service.util.ServiceUtils;
import com.krminc.phr.core.AppConfig;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.HealthRecord;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.logging.Level;
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
import com.krminc.phr.domain.carenotebook.*;
import com.sun.jersey.api.core.ResourceContext;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author cmccall
 */
public class CareNotebookFormProcessor {
    final Logger logger = LoggerFactory.getLogger(CareNotebookFormProcessor.class);

    @Context
    protected ResourceContext resourceContext;
    @Context
    protected UriInfo uriInfo;
    @Context
    HttpServletRequest req;

    public CareNotebookFormProcessor() {
    }

    @Path("{healthRecordId}/emergencyinformation/post/")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response alterEmergencyInformation(
        @PathParam("healthRecordId") Long healthRecordId,
        @FormParam("ambulancenumber") String ambulancenumber,
        @FormParam("physicianname") String physicianname,
        @FormParam("physiciannumber") String physiciannumber,
        @FormParam("firenumber") String firenumber,
        @FormParam("policenumber") String policenumber,
        @FormParam("poisonnumber") String poisonnumber,
        @FormParam("fathername") String fathername,
        @FormParam("fathernumber") String fathernumber,
        @FormParam("mothername") String mothername,
        @FormParam("mothernumber") String mothernumber,
        @FormParam("emergencycontact") String emergencycontact,
        @FormParam("contactrelationship") String contactrelationship,
        @FormParam("emergencynumber") String emergencynumber,
        @FormParam("hospitalname") String hospitalname,
        @FormParam("hospitalnumber") String hospitalnumber,
        @FormParam("hospitaladdress") String hospitaladdress,
        @FormParam("hospitaladdress2") String hospitaladdress2,
        @FormParam("hospitalcity") String hospitalcity,
        @FormParam("hospitalstate") String hospitalstate,
        @FormParam("specialtyname") String specialtyname,
        @FormParam("specialtynumber") String specialtynumber,
        @FormParam("specialtytype") String specialtytype,
        @FormParam("emergencydescription") String emergencydescription,
        @FormParam("treatmentdescription") String treatmentdescription,
        @FormParam("comments") String comments
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/emergencyinformation/?status=success";

        if (!ServiceUtils.isPhoneNumberOrEmpty(ambulancenumber)) {
            error = true;
            errorString = "ambulancenumber";
        }

        if (!ServiceUtils.isPhoneNumberOrEmpty(physiciannumber)) {
            error = true;
            errorString = "physiciannumber";
        }

        if (!ServiceUtils.isPhoneNumberOrEmpty(firenumber)) {
            error = true;
            errorString = "firenumber";
        }

        if (!ServiceUtils.isPhoneNumberOrEmpty(policenumber)) {
            error = true;
            errorString = "policenumber";
        }

        if (!ServiceUtils.isPhoneNumberOrEmpty(poisonnumber)) {
            error = true;
            errorString = "poisonnumber";
        }

        if (!ServiceUtils.isPhoneNumberOrEmpty(fathernumber)) {
            error = true;
            errorString = "fathernumber";
        }

        if (!ServiceUtils.isPhoneNumberOrEmpty(mothernumber)) {
            error = true;
            errorString = "mothernumber";
        }

        if (!ServiceUtils.isPhoneNumberOrEmpty(emergencynumber)) {
            error = true;
            errorString = "emergencynumber";
        }

        if (!ServiceUtils.isPhoneNumberOrEmpty(hospitalnumber)) {
            error = true;
            errorString = "hospitalnumber";
        }

        if (!ServiceUtils.isPhoneNumberOrEmpty(specialtynumber)) {
            error = true;
            errorString = "specialtynumber";
        }

        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                persistenceSvc.beginTx();
                EntityManager em = persistenceSvc.getEntityManager();

                EmergencyInformation emergencyInfo = em.find(EmergencyInformation.class, healthRecordId);

                if (emergencyInfo == null) {
                    emergencyInfo = new EmergencyInformation(healthRecordId);
                }

                emergencyInfo.setAmbulanceNumber(ambulancenumber);
                //mi2.getCareDocumentId();
                emergencyInfo.setComments(comments);
                emergencyInfo.setDataSourceId(healthRecordId);
                //mi2.setDateAdded(null);
                emergencyInfo.setEmergencyContactName(emergencycontact);
                emergencyInfo.setEmergencyContactNumber(emergencynumber);
                emergencyInfo.setEmergencyContactRelationship(contactrelationship);
                emergencyInfo.setEmergencyDescription(emergencydescription);
                emergencyInfo.setFatherName(fathername);
                emergencyInfo.setFatherNumber(fathernumber);
                emergencyInfo.setFireNumber(firenumber);
                emergencyInfo.setHospitalAddress(hospitaladdress);
                emergencyInfo.setHospitalAddress2(hospitaladdress2);
                emergencyInfo.setHospitalCity(hospitalcity);
                emergencyInfo.setHospitalName(hospitalname);
                emergencyInfo.setHospitalNumber(hospitalnumber);
                emergencyInfo.setHospitalState(hospitalstate);
                //mi2.setMask();
                emergencyInfo.setMotherName(mothername);
                emergencyInfo.setMotherNumber(mothernumber);
                emergencyInfo.setPhysicianName(physicianname);
                emergencyInfo.setPhysicianNumber(physiciannumber);
                emergencyInfo.setPoisonNumber(poisonnumber);
                emergencyInfo.setPoliceNumber(policenumber);
                //mi2.setSourceId();
                emergencyInfo.setSpecialtyName(specialtyname);
                emergencyInfo.setSpecialtyNumber(specialtynumber);
                emergencyInfo.setSpecialtyType(specialtytype);
                emergencyInfo.setTreatmentDescription(treatmentdescription);

                em.merge(emergencyInfo);
                persistenceSvc.commitTx();
            } catch (Exception e) {
                logger.error("Emergency Information Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/emergencyinformation/?status=" + errorString;
            logger.error("redirecting to error URI: {}", redirectUri);
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }

   @Path("{healthRecordId}/insuranceinformation/post/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterInsuranceInformation(
        MultivaluedMap<String,String> formParams,
        @PathParam("healthRecordId") Long healthRecordId
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/insuranceinformation/?status=success";

        List<InsuranceInformation> insuranceInformationList = remap(formParams, InsuranceInformation.class, healthRecordId);

        //validate it all
        for (InsuranceInformation ii : insuranceInformationList) {

            if (!ServiceUtils.isPhoneNumberOrEmpty(ii.getBenefitsNumber())) {
                error = true;
                errorString = "benefitsnumber";
            }

            if (!ServiceUtils.isPhoneNumberOrEmpty(ii.getPreauthorizationNumber())) {
                error = true;
                errorString = "preauthorizationnumber";
            }

            if (!ServiceUtils.isPhoneNumberOrEmpty(ii.getPreadmissionNumber())) {
                error = true;
                errorString = "preadmissionnumber";
            }

            if (!ServiceUtils.isPhoneNumberOrEmpty(ii.getFaxNumber())) {
                error = true;
                errorString = "faxnumber";
            }
        }

        //all valid, commit it all
        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                EntityManager em = persistenceSvc.getEntityManager();

                for (InsuranceInformation ii : insuranceInformationList) {
                    if (res) {
                        persistenceSvc.beginTx();
                        if (ii.getInsuranceinformationId() != null) {
                            InsuranceInformation existingInsuranceInfo = em.find(InsuranceInformation.class, ii.getInsuranceinformationId());
                            if (existingInsuranceInfo.getHealthRecordId().compareTo(healthRecordId) == 0) {
                                ii.setDataSourceId(healthRecordId);
                                em.merge(ii);
                                persistenceSvc.commitTx();
                            } else {
                                logger.error("Non-matching HRID on merge attempt");
                                res = false;
                            }
                        } else {
                            ii.setDataSourceId(healthRecordId);
                            em.merge(ii);
                            persistenceSvc.commitTx();
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("Insurance Information Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/insuranceinformation/?status=" + errorString;
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }

    @Path("{healthRecordId}/specialtyclinics/post/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterSpecialtyClinics(
        MultivaluedMap<String,String> formParams,
        @PathParam("healthRecordId") Long healthRecordId
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/specialtyclinics/?status=success";

        List<SpecialtyClinic> clinicList = remap(formParams, SpecialtyClinic.class, healthRecordId);

        //validate it all
        for (SpecialtyClinic ii : clinicList) {

            if (!ServiceUtils.isPhoneNumberOrEmpty(ii.getPhoneNumber())) {
                error = true;
                errorString = "phonenumber";
            }

            if (!ServiceUtils.isPhoneNumberOrEmpty(ii.getFaxNumber())) {
                error = true;
                errorString = "faxnumber";
            }
        }

        //all valid, commit it all
        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                EntityManager em = persistenceSvc.getEntityManager();

                for (SpecialtyClinic ii : clinicList) {
                    if (res) {
                        persistenceSvc.beginTx();
                        if (ii.getSpecialtyclinicId() != null) {
                            SpecialtyClinic existingClinicInfo = em.find(SpecialtyClinic.class, ii.getSpecialtyclinicId());
                            if (existingClinicInfo.getHealthRecordId().compareTo(healthRecordId) == 0) {
                                ii.setDataSourceId(healthRecordId);
                                em.merge(ii);
                                persistenceSvc.commitTx();
                            } else {
                                logger.error("Non-matching HRID on merge attempt");
                                res = false;
                            }
                        } else {
                            ii.setDataSourceId(healthRecordId);
                            em.merge(ii);
                            persistenceSvc.commitTx();
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("Specialty Clinic Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/specialtyclinics/?status=" + errorString;
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }

   @Path("{healthRecordId}/pharmacies/post/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterPharmacies(
        MultivaluedMap<String,String> formParams,
        @PathParam("healthRecordId") Long healthRecordId
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/pharmacies/?status=success";

        List<Pharmacy> pharmacyList = remap(formParams, Pharmacy.class, healthRecordId);

        //validate it all
        for (Pharmacy i : pharmacyList) {

            if (!ServiceUtils.isPhoneNumberOrEmpty(i.getPhoneNumber())) {
                error = true;
                errorString = "phonenumber";
            }

            if (!ServiceUtils.isPhoneNumberOrEmpty(i.getFaxNumber())) {
                error = true;
                errorString = "faxnumber";
            }
        }

        //all valid, commit it all
        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                EntityManager em = persistenceSvc.getEntityManager();

                for (Pharmacy i : pharmacyList) {
                    if (res) {
                        persistenceSvc.beginTx();
                        if (i.getPharmacyId() != null) {
                            Pharmacy existingEntry = em.find(Pharmacy.class, i.getPharmacyId());
                            if (existingEntry.getHealthRecordId().compareTo(healthRecordId) == 0) {
                                i.setDataSourceId(healthRecordId);
                                em.merge(i);
                                persistenceSvc.commitTx();
                            } else {
                                logger.error("Non-matching HRID on merge attempt");
                                res = false;
                            }
                        } else {
                            i.setDataSourceId(healthRecordId);
                            em.merge(i);
                            persistenceSvc.commitTx();
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("Pharmacy Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/pharmacies/?status=" + errorString;
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }

   @Path("{healthRecordId}/careproviders/post/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterCareProviders(
        MultivaluedMap<String,String> formParams,
        @PathParam("healthRecordId") Long healthRecordId
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/careproviders/?status=success";

        List<CareProvider> providerList = remap(formParams, CareProvider.class, healthRecordId);

        //validate it all
        for (CareProvider i : providerList) {

            if (!ServiceUtils.isPhoneNumberOrEmpty(i.getPhoneNumber())) {
                error = true;
                errorString = "phonenumber";
            }

            if (!ServiceUtils.isPhoneNumberOrEmpty(i.getFaxNumber())) {
                error = true;
                errorString = "faxnumber";
            }
        }

        //all valid, commit it all
        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                EntityManager em = persistenceSvc.getEntityManager();

                for (CareProvider i : providerList) {
                    if (res) {
                        persistenceSvc.beginTx();
                        if (i.getProviderId() != null) {
                            CareProvider existingEntry = em.find(CareProvider.class, i.getProviderId());
                            if (existingEntry.getHealthRecordId().compareTo(healthRecordId) == 0) {
                                i.setDataSourceId(healthRecordId);
                                em.merge(i);
                                persistenceSvc.commitTx();
                            } else {
                                logger.error("Non-matching HRID on merge attempt");
                                res = false;
                            }
                        } else {
                            i.setDataSourceId(healthRecordId);
                            em.merge(i);
                            persistenceSvc.commitTx();
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("Care Provider Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/careproviders/?status=" + errorString;
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }
   
    @Path("{healthRecordId}/familymembers/post/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterFamilyMembers(
        MultivaluedMap<String,String> formParams,
        @PathParam("healthRecordId") Long healthRecordId
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/familymembers/?status=success";

        List<FamilyMember> memberList = remap(formParams, FamilyMember.class, healthRecordId);

        //validate it all
        for (FamilyMember i : memberList) {

            if (!ServiceUtils.isPhoneNumberOrEmpty(i.getDaytimePhoneNumber())) {
                error = true;
                errorString = "daytimenumber";
            }
            
            if (!ServiceUtils.isPhoneNumberOrEmpty(i.getEveningPhoneNumber())) {
                error = true;
                errorString = "eveningnumber";
            }

            if (!ServiceUtils.isPhoneNumberOrEmpty(i.getFaxNumber())) {
                error = true;
                errorString = "faxnumber";
            }
        }

        //all valid, commit it all
        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                EntityManager em = persistenceSvc.getEntityManager();

                for (FamilyMember i : memberList) {
                    if (res) {
                        persistenceSvc.beginTx();
                        if (i.getFamilymemberId() != null) {
                            FamilyMember existingEntry = em.find(FamilyMember.class, i.getFamilymemberId());
                            if (existingEntry.getHealthRecordId().compareTo(healthRecordId) == 0) {
                                i.setDataSourceId(healthRecordId);
                                em.merge(i);
                                persistenceSvc.commitTx();
                            } else {
                                logger.error("Non-matching HRID on merge attempt");
                                res = false;
                            }
                        } else {
                            i.setDataSourceId(healthRecordId);
                            em.merge(i);
                            persistenceSvc.commitTx();
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("Family Member Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/familymembers/?status=" + errorString;
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }

    @Path("{healthRecordId}/familyhistory/post/")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response alterFamilyHistory(
        @PathParam("healthRecordId") Long healthRecordId,
        @FormParam("mentalIllness") Boolean mentalIllness,
        @FormParam("whoMentalIllness") String whoMentalIllness,
        @FormParam("cerebralPalsy") Boolean cerebralPalsy,
        @FormParam("whoCerebralPalsy") String whoCerebralPalsy,
        @FormParam("muscularDystrophy") Boolean muscularDystrophy,
        @FormParam("whoMuscularDystrophy") String whoMuscularDystrophy,
        @FormParam("epilepsy") Boolean epilepsy,
        @FormParam("whoEpilepsy") String whoEpilepsy,
        @FormParam("heartDisease") Boolean heartDisease,
        @FormParam("whoHeartDisease") String whoHeartDisease,
        @FormParam("diabetes") Boolean diabetes,
        @FormParam("whoDiabetes") String whoDiabetes,
        @FormParam("kidneyDisease") Boolean kidneyDisease,
        @FormParam("whoKidneyDisease") String whoKidneyDisease,
        @FormParam("cancer") Boolean cancer,
        @FormParam("whoCancer") String whoCancer,
        @FormParam("thyroidDisease") Boolean thyroidDisease,
        @FormParam("whoThyroidDisease") String whoThyroidDisease,
        @FormParam("highBloodPressure") Boolean highBloodPressure,
        @FormParam("whoHighBloodPressure") String whoHighBloodPressure,
        @FormParam("deceasedSiblings") Boolean deceasedSiblings,
        @FormParam("whoDeceasedSiblings") String whoDeceasedSiblings,
        @FormParam("behaviorDisorder") Boolean behaviorDisorder,
        @FormParam("whoBehaviorDisorder") String whoBehaviorDisorder,
        @FormParam("tuberculosis") Boolean tuberculosis,
        @FormParam("whoTuberculosis") String whoTuberculosis,
        @FormParam("hepatitis") Boolean hepatitis,
        @FormParam("whoHepatitis") String whoHepatitis,
        @FormParam("metabolicDisease") Boolean metabolicDisease,
        @FormParam("whoMetabolicDisease") String whoMetabolicDisease,
        @FormParam("allergies") Boolean allergies,
        @FormParam("whoAllergies") String whoAllergies,
        @FormParam("developmentalDisabilities") Boolean developmentalDisabilities,
        @FormParam("whoDevelopmentalDisabilities") String whoDevelopmentalDisabilities,
        @FormParam("traumaticBrainInjury") Boolean traumaticBrainInjury,
        @FormParam("whoTraumaticBrainInjury") String whoTraumaticBrainInjury,
        @FormParam("other") String other,
        @FormParam("comments") String comments
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/familyhistory/?status=success";

        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                persistenceSvc.beginTx();
                EntityManager em = persistenceSvc.getEntityManager();

                FamilyHistory familyHistory = em.find(FamilyHistory.class, healthRecordId);

                if (familyHistory == null) {
                    familyHistory = new FamilyHistory(healthRecordId);
                }
                familyHistory.setAllergies(allergies);
                familyHistory.setBehaviorDisorder(behaviorDisorder);
                familyHistory.setCancer(cancer);
                familyHistory.setCerebralPalsy(cerebralPalsy);
                familyHistory.setComments(comments);
                familyHistory.setDeceasedSiblings(deceasedSiblings);
                familyHistory.setDevelopmentalDisabilities(developmentalDisabilities);
                familyHistory.setDiabetes(diabetes);
                familyHistory.setEpilepsy(epilepsy);
                familyHistory.setHeartDisease(heartDisease);
                familyHistory.setHepatitis(hepatitis);
                familyHistory.setHighBloodPressure(highBloodPressure);
                familyHistory.setKidneyDisease(kidneyDisease);
                familyHistory.setMentalIllness(mentalIllness);
                familyHistory.setMetabolicDisease(metabolicDisease);
                familyHistory.setMuscularDystrophy(muscularDystrophy);
                familyHistory.setOther(other);
                familyHistory.setThyroidDisease(thyroidDisease);
                familyHistory.setTraumaticBrainInjury(traumaticBrainInjury);
                familyHistory.setTuberculosis(tuberculosis);
                
                familyHistory.setWhoAllergies(whoAllergies);
                familyHistory.setWhoBehaviorDisorder(whoBehaviorDisorder);
                familyHistory.setWhoCancer(whoCancer);
                familyHistory.setWhoCerebralPalsy(whoCerebralPalsy);
                familyHistory.setWhoDeceasedSiblings(whoDeceasedSiblings);
                familyHistory.setWhoDevelopmentalDisabilities(whoDevelopmentalDisabilities);
                familyHistory.setWhoDiabetes(whoDiabetes);
                familyHistory.setWhoEpilepsy(whoEpilepsy);
                familyHistory.setWhoHeartDisease(whoHeartDisease);
                familyHistory.setWhoHepatitis(whoHepatitis);
                familyHistory.setWhoHighBloodPressure(whoHighBloodPressure);
                familyHistory.setWhoKidneyDisease(whoKidneyDisease);
                familyHistory.setWhoMentalIllness(whoMentalIllness);
                familyHistory.setWhoMetabolicDisease(whoMetabolicDisease);
                familyHistory.setWhoMuscularDystrophy(whoMuscularDystrophy);
                familyHistory.setWhoThyroidDisease(whoThyroidDisease);
                familyHistory.setWhoTraumaticBrainInjury(whoTraumaticBrainInjury);
                familyHistory.setWhoTuberculosis(whoTuberculosis);
                
                familyHistory.setDataSourceId(healthRecordId);

                em.merge(familyHistory);
                persistenceSvc.commitTx();
            } catch (Exception e) {
                logger.error("familyhistory Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/familyhistory/?status=" + errorString;
            logger.error("redirecting to error URI: {}", redirectUri);
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }
    
    @Path("{healthRecordId}/nutrition/post/")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response alterNutrition(
        @PathParam("healthRecordId") Long healthRecordId,
        @FormParam("feedingSchedule") String feedingSchedule,
        @FormParam("foodLikes") String foodLikes,
        @FormParam("foodDislikes") String foodDislikes,
        @FormParam("feedingModifications") String feedingModifications,
        @FormParam("foodAllergies") String foodAllergies,
        @FormParam("comments") String comments
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/nutrition/?status=success";

        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                persistenceSvc.beginTx();
                EntityManager em = persistenceSvc.getEntityManager();

                Nutrition nutritionInfo = em.find(Nutrition.class, healthRecordId);

                if (nutritionInfo == null) {
                    nutritionInfo = new Nutrition(healthRecordId);
                }

                nutritionInfo.setFeedingSchedule(feedingSchedule);
                nutritionInfo.setFoodLikes(foodLikes);
                nutritionInfo.setFoodDislikes(foodDislikes);
                nutritionInfo.setFeedingModifications(feedingModifications);
                nutritionInfo.setFoodAllergies(foodAllergies);
                nutritionInfo.setComments(comments);
                nutritionInfo.setDataSourceId(healthRecordId);

                em.merge(nutritionInfo);
                persistenceSvc.commitTx();
            } catch (Exception e) {
                logger.error("Nutrition Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/nutrition/?status=" + errorString;
            logger.error("redirecting to error URI: {}", redirectUri);
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }
    
    @Path("{healthRecordId}/respiratory/post/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterRespiratoryLog(
        MultivaluedMap<String,String> formParams,
        @PathParam("healthRecordId") Long healthRecordId
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/respiratory/?status=success";

        List<RespiratoryCare> log = remap(formParams, RespiratoryCare.class, healthRecordId);

        //validate it all
        for (RespiratoryCare l : log) {
            if (l.getObservedDate() == null) {
                error = true;
                errorString = "observedDate";
            }
        }

        //all valid, commit it all
        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                EntityManager em = persistenceSvc.getEntityManager();

                for (RespiratoryCare ii : log) {
                    if (res) {
                        persistenceSvc.beginTx();
                        if (ii.getRespiratorycareId() != null) {
                            RespiratoryCare existingLog = em.find(RespiratoryCare.class, ii.getRespiratorycareId());
                            if (existingLog.getHealthRecordId().compareTo(healthRecordId) == 0) {
                                ii.setDataSourceId(healthRecordId);
                                em.merge(ii);
                                persistenceSvc.commitTx();
                            } else {
                                logger.error("Non-matching HRID on merge attempt");
                                res = false;
                            }
                        } else {
                            ii.setDataSourceId(healthRecordId);
                            em.merge(ii);
                            persistenceSvc.commitTx();
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("RespiratoryCare Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/respiratory/?status=" + errorString;
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }

    @Path("{healthRecordId}/communication/post/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterCommunicationLog(
        MultivaluedMap<String,String> formParams,
        @PathParam("healthRecordId") Long healthRecordId
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/communication/?status=success";

        List<Communication> log = remap(formParams, Communication.class, healthRecordId);

        //validate it all
        for (Communication l : log) {
            if (l.getObservedDate() == null) {
                error = true;
                errorString = "observedDate";
            }
        }

        //all valid, commit it all
        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                EntityManager em = persistenceSvc.getEntityManager();

                for (Communication ii : log) {
                    if (res) {
                        persistenceSvc.beginTx();
                        if (ii.getCommunicationId() != null) {
                            Communication existingLog = em.find(Communication.class, ii.getCommunicationId());
                            if (existingLog.getHealthRecordId().compareTo(healthRecordId) == 0) {
                                ii.setDataSourceId(healthRecordId);
                                em.merge(ii);
                                persistenceSvc.commitTx();
                            } else {
                                logger.error("Non-matching HRID on merge attempt");
                                res = false;
                            }
                        } else {
                            ii.setDataSourceId(healthRecordId);
                            em.merge(ii);
                            persistenceSvc.commitTx();
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("Communication Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/communication/?status=" + errorString;
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }
    
    @Path("{healthRecordId}/mobility/post/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterMobilityLog(
        MultivaluedMap<String,String> formParams,
        @PathParam("healthRecordId") Long healthRecordId
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/mobility/?status=success";

        List<Mobility> log = remap(formParams, Mobility.class, healthRecordId);

        //validate it all
        for (Mobility l : log) {
            if (l.getObservedDate() == null) {
                error = true;
                errorString = "observedDate";
            }
        }

        //all valid, commit it all
        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                EntityManager em = persistenceSvc.getEntityManager();

                for (Mobility ii : log) {
                    if (res) {
                        persistenceSvc.beginTx();
                        if (ii.getMobilityId() != null) {
                            Mobility existingLog = em.find(Mobility.class, ii.getMobilityId());
                            if (existingLog.getHealthRecordId().compareTo(healthRecordId) == 0) {
                                ii.setDataSourceId(healthRecordId);
                                em.merge(ii);
                                persistenceSvc.commitTx();
                            } else {
                                logger.error("Non-matching HRID on merge attempt");
                                res = false;
                            }
                        } else {
                            ii.setDataSourceId(healthRecordId);
                            em.merge(ii);
                            persistenceSvc.commitTx();
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("Mobility Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/mobility/?status=" + errorString;
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }
    
    @Path("{healthRecordId}/rest/post/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterRestLog(
        MultivaluedMap<String,String> formParams,
        @PathParam("healthRecordId") Long healthRecordId
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/rest/?status=success";

        List<Rest> log = remap(formParams, Rest.class, healthRecordId);

        //validate it all
        for (Rest l : log) {
            if (l.getObservedDate() == null) {
                error = true;
                errorString = "observedDate";
            }
        }

        //all valid, commit it all
        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                EntityManager em = persistenceSvc.getEntityManager();

                for (Rest ii : log) {
                    if (res) {
                        persistenceSvc.beginTx();
                        if (ii.getRestId() != null) {
                            Rest existingLog = em.find(Rest.class, ii.getRestId());
                            if (existingLog.getHealthRecordId().compareTo(healthRecordId) == 0) {
                                ii.setDataSourceId(healthRecordId);
                                em.merge(ii);
                                persistenceSvc.commitTx();
                            } else {
                                logger.error("Non-matching HRID on merge attempt");
                                res = false;
                            }
                        } else {
                            ii.setDataSourceId(healthRecordId);
                            em.merge(ii);
                            persistenceSvc.commitTx();
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("Rest Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/rest/?status=" + errorString;
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }
    
    @Path("{healthRecordId}/social/post/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterSocialLog(
        MultivaluedMap<String,String> formParams,
        @PathParam("healthRecordId") Long healthRecordId
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/social/?status=success";

        List<Social> log = remap(formParams, Social.class, healthRecordId);

        //validate it all
        for (Social l : log) {
            if (l.getObservedDate() == null) {
                error = true;
                errorString = "observedDate";
            }
        }

        //all valid, commit it all
        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                EntityManager em = persistenceSvc.getEntityManager();

                for (Social ii : log) {
                    if (res) {
                        persistenceSvc.beginTx();
                        if (ii.getSocialId() != null) {
                            Social existingLog = em.find(Social.class, ii.getSocialId());
                            if (existingLog.getHealthRecordId().compareTo(healthRecordId) == 0) {
                                ii.setDataSourceId(healthRecordId);
                                em.merge(ii);
                                persistenceSvc.commitTx();
                            } else {
                                logger.error("Non-matching HRID on merge attempt");
                                res = false;
                            }
                        } else {
                            ii.setDataSourceId(healthRecordId);
                            em.merge(ii);
                            persistenceSvc.commitTx();
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("Social Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/social/?status=" + errorString;
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }
    
    @Path("{healthRecordId}/stress/post/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterStressLog(
        MultivaluedMap<String,String> formParams,
        @PathParam("healthRecordId") Long healthRecordId
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/stress/?status=success";

        List<Stress> log = remap(formParams, Stress.class, healthRecordId);

        //validate it all
        for (Stress l : log) {
            if (l.getObservedDate() == null) {
                error = true;
                errorString = "observedDate";
            }
        }

        //all valid, commit it all
        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                EntityManager em = persistenceSvc.getEntityManager();

                for (Stress ii : log) {
                    if (res) {
                        persistenceSvc.beginTx();
                        if (ii.getStressId() != null) {
                            Stress existingLog = em.find(Stress.class, ii.getStressId());
                            if (existingLog.getHealthRecordId().compareTo(healthRecordId) == 0) {
                                ii.setDataSourceId(healthRecordId);
                                em.merge(ii);
                                persistenceSvc.commitTx();
                            } else {
                                logger.error("Non-matching HRID on merge attempt");
                                res = false;
                            }
                        } else {
                            ii.setDataSourceId(healthRecordId);
                            em.merge(ii);
                            persistenceSvc.commitTx();
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("Stress Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/stress/?status=" + errorString;
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }

    @Path("{healthRecordId}/transitions/post/")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response alterTransitions(
        @PathParam("healthRecordId") Long healthRecordId,
        @FormParam("transitionText") String transitionText,
        @FormParam("hopesText") String hopesText,
        @FormParam("comments") String comments
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/transitions/?status=success";

        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                persistenceSvc.beginTx();
                EntityManager em = persistenceSvc.getEntityManager();

                Transition transitionInfo = em.find(Transition.class, healthRecordId);

                if (transitionInfo == null) {
                    transitionInfo = new Transition(healthRecordId);
                }

                transitionInfo.setTransitionText(transitionText);
                transitionInfo.setHopesText(hopesText);
                transitionInfo.setComments(comments);
                transitionInfo.setDataSourceId(healthRecordId);

                em.merge(transitionInfo);
                persistenceSvc.commitTx();
            } catch (Exception e) {
                logger.error("Transition Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/transitions/?status=" + errorString;
            logger.error("redirecting to error URI: {}", redirectUri);
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }
    
    @Path("{healthRecordId}/employment/post/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterEmployments(
        MultivaluedMap<String,String> formParams,
        @PathParam("healthRecordId") Long healthRecordId
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/employment/?status=success";

        List<Employment> employmentList = remap(formParams, Employment.class, healthRecordId);

        //validate it all
        for (Employment i : employmentList) {

            if (!ServiceUtils.isPhoneNumberOrEmpty(i.getPhoneNumber())) {
                error = true;
                errorString = "phonenumber";
            }

            if (!ServiceUtils.isPhoneNumberOrEmpty(i.getFaxNumber())) {
                error = true;
                errorString = "faxnumber";
            }
        }

        //all valid, commit it all
        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                EntityManager em = persistenceSvc.getEntityManager();

                for (Employment i : employmentList) {
                    if (res) {
                        persistenceSvc.beginTx();
                        if (i.getEmploymentId() != null) {
                            Employment existingEntry = em.find(Employment.class, i.getEmploymentId());
                            if (existingEntry.getHealthRecordId().compareTo(healthRecordId) == 0) {
                                i.setDataSourceId(healthRecordId);
                                em.merge(i);
                                persistenceSvc.commitTx();
                            } else {
                                logger.error("Non-matching HRID on merge attempt");
                                res = false;
                            }
                        } else {
                            i.setDataSourceId(healthRecordId);
                            em.merge(i);
                            persistenceSvc.commitTx();
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("Employment Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/employment/?status=" + errorString;
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }
    
    @Path("{healthRecordId}/schools/post/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterSchools(
        MultivaluedMap<String,String> formParams,
        @PathParam("healthRecordId") Long healthRecordId
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/school/?status=success";

        List<School> schoolList = remap(formParams, School.class, healthRecordId);

        //validate it all
        for (School i : schoolList) {

            if (!ServiceUtils.isPhoneNumberOrEmpty(i.getSchoolPhoneNumber())) {
                error = true;
                errorString = "schoolphonenumber";
            }
            
            if (!ServiceUtils.isPhoneNumberOrEmpty(i.getSchoolFaxNumber())) {
                error = true;
                errorString = "schoolfaxnumber";
            }
        }

        //all valid, commit it all
        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                EntityManager em = persistenceSvc.getEntityManager();

                for (School i : schoolList) {
                    if (res) {
                        persistenceSvc.beginTx();
                        if (i.getSchoolId() != null) {
                            School existingEntry = em.find(School.class, i.getSchoolId());
                            if (existingEntry.getHealthRecordId().compareTo(healthRecordId) == 0) {
                                i.setDataSourceId(healthRecordId);
                                em.merge(i);
                                persistenceSvc.commitTx();
                            } else {
                                logger.error("Non-matching HRID on merge attempt");
                                res = false;
                            }
                        } else {
                            i.setDataSourceId(healthRecordId);
                            em.merge(i);
                            persistenceSvc.commitTx();
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("School Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/school/?status=" + errorString;
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }
    
    @Path("{healthRecordId}/schoolpersonnel/post/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterSchoolPersonnel(
        MultivaluedMap<String,String> formParams,
        @PathParam("healthRecordId") Long healthRecordId
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/schoolpersonnel/?status=success";

        List<SchoolPersonnel> personnelList = remap(formParams, SchoolPersonnel.class, healthRecordId);

        //validate it all
        for (SchoolPersonnel i : personnelList) {

            if (!ServiceUtils.isPhoneNumberOrEmpty(i.getDaytimePhoneNumber())) {
                error = true;
                errorString = "daytimenumber";
            }
            
            if (!ServiceUtils.isPhoneNumberOrEmpty(i.getEveningPhoneNumber())) {
                error = true;
                errorString = "eveningnumber";
            }

            if (!ServiceUtils.isPhoneNumberOrEmpty(i.getFaxNumber())) {
                error = true;
                errorString = "faxnumber";
            }
        }

        //all valid, commit it all
        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                EntityManager em = persistenceSvc.getEntityManager();

                for (SchoolPersonnel i : personnelList) {
                    if (res) {
                        persistenceSvc.beginTx();
                        if (i.getPersonnelId() != null) {
                            SchoolPersonnel existingEntry = em.find(SchoolPersonnel.class, i.getPersonnelId());
                            if (existingEntry.getHealthRecordId().compareTo(healthRecordId) == 0) {
                                i.setDataSourceId(healthRecordId);
                                em.merge(i);
                                persistenceSvc.commitTx();
                            } else {
                                logger.error("Non-matching HRID on merge attempt");
                                res = false;
                            }
                        } else {
                            i.setDataSourceId(healthRecordId);
                            em.merge(i);
                            persistenceSvc.commitTx();
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("School Personnel Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/schoolpersonnel/?status=" + errorString;
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }
    
    @Path("{healthRecordId}/meetings/post/")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response alterMeetings(
        MultivaluedMap<String,String> formParams,
        @PathParam("healthRecordId") Long healthRecordId
    ) {
        Boolean error = false;
        String errorString  = new String();
        String redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/meetings/?status=success";

        List<Meeting> meetingList = remap(formParams, Meeting.class, healthRecordId);

        //validate it all
//        for (Meeting i : meetingList) {
//        }

        //all valid, commit it all
        boolean res = true;

        if (!error) {

            PersistenceService persistenceSvc = PersistenceService.getInstance();
            try {
                EntityManager em = persistenceSvc.getEntityManager();

                for (Meeting i : meetingList) {
                    if (res) {
                        persistenceSvc.beginTx();
                        if (i.getMeetingId() != null) {
                            Meeting existingEntry = em.find(Meeting.class, i.getMeetingId());
                            if (existingEntry.getHealthRecordId().compareTo(healthRecordId) == 0) {
                                i.setDataSourceId(healthRecordId);
                                em.merge(i);
                                persistenceSvc.commitTx();
                            } else {
                                logger.error("Non-matching HRID on merge attempt");
                                res = false;
                            }
                        } else {
                            i.setDataSourceId(healthRecordId);
                            em.merge(i);
                            persistenceSvc.commitTx();
                        }
                    }

                }
            } catch (Exception e) {
                logger.error("Meeting Save Failed", e);
                res = false;
            } finally {
                persistenceSvc.close();
            }

        }

        if (!res) {
            error = true;
            errorString = "saveerror";
        }

        if (error) {
            redirectUri = "." + AppConfig.PATH_PATIENT_ROOT + "/" + healthRecordId + "/meetings/?status=" + errorString;
        }

        return Response.seeOther(uriInfo.getBaseUri().resolve(redirectUri)).build();
    }
    
   private <T> List<T> remap (MultivaluedMap<String, String> map, Class<T> type, Long healthRecordId) {
       HashMap<Long, T> pairsBySuffix = new HashMap<Long, T>();
       Pattern numberRx = Pattern.compile("_(\\d+)$");

       for (Entry<String, List<String>> entry: map.entrySet()) {
           Matcher matcher = numberRx.matcher(entry.getKey());
           
           if (matcher.find()) {
               T i;
               Long matchedVal = new Long(matcher.group(1));
               if (pairsBySuffix.containsKey(matchedVal)) {
                   i = pairsBySuffix.get(matchedVal);
               } else {
                        try {
                            Constructor c = type.getConstructor(Long.class);
                            i = (T) c.newInstance(healthRecordId);
                        }
                        catch (Exception ex) {
                            logger.error("Error constructing new carenotebook entity of type {} for healthrecord {}", type, healthRecordId);
                            logger.error("Stacktrace: {}" , ex);
                            i = null;
                        }
               }

               Class paramTypes[] = new Class[1];
               paramTypes[0] = String.class;
               Boolean continueVal = false;
               Boolean wasSet = true;
               Method m = null;
               String trueKey = entry.getKey().substring(0, entry.getKey().length() - (matcher.group().length()));
               if (i != null) {
                    try {
                        Method m2[] = i.getClass().getDeclaredMethods();
                        for (Method meth : m2) {
                            if (meth.getName().equalsIgnoreCase("set".concat(trueKey))
                                    && meth.getParameterTypes()[0].getName().compareTo(String.class.getName()) == 0
                                    ) {
                                m = meth;
                                continueVal = true;
                                break;
                            }
                        }
                    }

                    catch (SecurityException ex) {
                        continueVal = false;
                        logger.error("Security exception in remap function: {} {}", entry.getKey(), entry.getValue());
                    }
               }
               
               if (continueVal) {
                   String arglist[] = new String[1];
                   arglist[0] = entry.getValue().get(0); //this might be unsafe?
                    try {
                        m.invoke(i, (Object[]) arglist);
                    }
                    catch (Exception ex) {
                        //IllegalAccessException
                        //IllegalArgumentException
                        //InvocationTargetException
                        wasSet = false;
                    }
               }

               if (continueVal && wasSet) {
                   pairsBySuffix.put(matchedVal, i);
               } else {
                   logger.error("Unable to save form value in remap function: {} {}", entry.getKey(), entry.getValue());
               }
            }
       }
       
       //put our map in order
       Long[] a = new Long[1];

       List<Long> keys = Arrays.asList(pairsBySuffix.keySet().toArray(a));
       Collections.sort(keys);

       List<T> sortedSet = new ArrayList<T>();
       for (Long key : keys) {
           sortedSet.add(pairsBySuffix.get(key));
       }

       return sortedSet;
   }
}
