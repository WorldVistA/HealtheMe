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
package com.krminc.phr.api.service.clinical;

import com.krminc.phr.ws.FMPatient;
import com.krminc.phr.ws.client.RpmsService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 * User Specific interactions with Resouce (External Service "Endpoint")
 * API RESTful resource class.
 *
 * @author Daniel Shaw (dshaw.com)
 */
public class UserResourceResource extends ResourceResource {
    
    protected Long healthRecordId;

    
    public void setHealthRecordId(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    /**
     * Lookup a IEN Id from the EMR web service given a HRN.
     *
     * @param resourceId Resource ID of the EMR Web Service.
     * @param hrn (Health Record Number)
     * @return FMPatient Object which encapsulates a HRN, IEN, Name, Gender and Date of Birth.
     */
    @Path("lookup/{identifier}/{value}/")
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public JSONObject getResourceLookupResults(
        @PathParam("resourceId") Long resourceId,
        @PathParam("identifier") String identifier,
        @PathParam("value") String value
    ) {
        JSONObject jsonResult = new JSONObject();
        JSONArray jsonPatients = new JSONArray();
        try {
            // TODO: Need to refactor how service locator and instantiation is handled. DI?
            RpmsService rpmsService = new RpmsService(resourceId);
            List<FMPatient> patients = rpmsService.getPatientList(value);
            logger.debug("patients size: {}", patients.size());
            for (FMPatient patient : patients) {
                if(value.equalsIgnoreCase(patient.getID())) {
                    JSONObject jsonPatient = new JSONObject();
                    jsonPatient.put("hrn", patient.getID());
                    jsonPatient.put("ien", patient.getIEN());
                    jsonPatient.put("name", patient.getName());
                    jsonPatient.put("gender", patient.getGender());
                    jsonPatient.put("dob", patient.getDOB());
                    jsonPatient.put("address1", patient.getAddressLine1());
                    jsonPatient.put("address2", patient.getAddressLine2());
                    jsonPatient.put("address3", patient.getAddressLine3());
                    jsonPatient.put("city", patient.getCity());
                    jsonPatient.put("state", patient.getState());
                    jsonPatient.put("zip", patient.getZip());
                    jsonPatients.put(jsonPatient);
                }
            }
            jsonResult.put("patients", jsonPatients);
        }
        catch (Exception ex) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return jsonResult;
    }

    /**
     * @return a dynamic instance of RecordIdentifiersResource used for entity navigation.
     */
    @Path("recordidentifiers/")
    public RecordIdentifiersResource getRecordIdentifiersResource() {
        RecordIdentifiersResource resource = resourceContext.getResource(RecordIdentifiersResource.class);
        resource.setHealthRecordId(healthRecordId);
        resource.setResourceId(resourceId);
        return resource;
    }

}
