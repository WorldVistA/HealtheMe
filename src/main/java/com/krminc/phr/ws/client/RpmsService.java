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
package com.krminc.phr.ws.client;

import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.clinical.Resource;
import com.krminc.phr.ws.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.xml.namespace.QName;
import org.apache.log4j.Logger;

/**
 * RPMS Soap Service Client
 *
 * @author Daniel Shaw (dshaw.com)
 */
public class RpmsService {

    private static final Logger logger = Logger.getLogger(RpmsService.class);

    private Long resourceId;


    public RpmsService(Long resourceId) {
        this.resourceId = resourceId;
    }


    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public List<FMPatient> getPatientList(String hrn) {
        Resource resource = null;
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            resource = em.find(Resource.class, resourceId);
        } catch (NoResultException ex) {
            logger.warn(ex);
        }

        // TODO: Why are credentials a list
        logger.debug("hrn " + hrn);
        logger.debug(resource.getResourceLocationPath());
        logger.debug(resource.getResourceCredentials().get(0).getUsername());
        logger.debug(resource.getResourceNamespace());
        logger.debug(resource.getResourceLocalPart());
        long startTime, endTime;
        startTime = System.currentTimeMillis();

        PhrCcrService service = null;
        QName qName = null;
        try {
            service = new PhrCcrService(
                new URL(resource.getResourceLocationPath()),
                new QName(resource.getResourceNamespace(), resource.getResourceLocalPart())
            );
        } catch (MalformedURLException ex) {
            logger.warn(ex);
        }        
//        boolean response = false;
//        try {
//            ((BindingProvider) serviceProxy).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
//                response = serviceProxy.ccrServiceLogin(
//                    resource.getResourceCredentials().get(0).getUsername(),
//                    resource.getResourceCredentials().get(0).getPassword()
//                );
//            logger.debug("login response:" + response);
//        } catch (AuthenticationServiceException_Exception ex) {
//            logger.debug(ex);
//        }
        endTime = System.currentTimeMillis();
        logger.debug("Time to login:" + (endTime - startTime) + " milliseconds");
        try {
            CCRServiceSoap serviceProxy = service.getCCRServiceSoap();
            startTime = System.currentTimeMillis();
            // Christopher says pick a number between 1 and 50000
            // `100 is a "programmers trick" to get by DFN
            // so this will fetch record for IEN=100, not by the ID
            // if you put in 100, you will get a list of records where the ID starts with 100
            List<FMPatient> patients = serviceProxy.lookupPatientsByID(resource.getResourceCredentials().get(0).getUsername(), resource.getResourceCredentials().get(0).getPassword(), hrn);

            if (patients == null) {
                return Collections.EMPTY_LIST;
            }

            logger.info("patient count:" + patients.size());
            endTime = System.currentTimeMillis();
            for (FMPatient patient : patients) {
                logger.debug("patient:" + patient);
                logger.debug("patient name:" + patient.getName());
                logger.debug("patient gender:" + patient.getGender());
                //logger.debug("patient age:" + patient.getAge());
                logger.debug("patient dob:" + patient.getDOB());
                logger.debug("patient Address Line 1:" + patient.getAddressLine1());
                logger.debug("patient Address Line 2:" + patient.getAddressLine2());
                logger.debug("patient Address Line 3:" + patient.getAddressLine3());
                logger.debug("patient Address Line 3:" + patient.getAddressLine3());
                logger.debug("patient City, State Zip:" + patient.getCity() + ", " + 
                        patient.getState() + " " + patient.getZip());
                logger.debug("patient id (hrn):" + patient.getID());
                logger.debug("patient ien:" + patient.getIEN());
                logger.debug("patient iens?:" + patient.getIENS());
                if (patient.getParent() != null )
                    logger.debug("parent record:" + patient.getParent().toString());
                System.out.println("fetch time:" + (endTime - startTime) + " milliseconds");

                return patients;
            }
        } catch (Exception ex) {
            logger.warn(ex);
        } /*finally {
        endTime = System.currentTimeMillis();
        System.out.println("<======================== time:" + (endTime - startTime) + " milliseconds");
        System.out.println("<======================== End Processing");

        }*/
        return Collections.EMPTY_LIST;
    }
}
