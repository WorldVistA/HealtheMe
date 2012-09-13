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
package com.krminc.phr.web;

import com.krminc.phr.dao.PersistenceService;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Essentially the controller class for the project.
 * 
 * @author Daniel Shaw (dshaw.com)
 */
public class HealthSummary {

    final Logger logger = LoggerFactory.getLogger(HealthSummary.class);

    private Long healthRecordId;


    public HealthSummary() {
        // default constructor
    }

    public HealthSummary(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }


    public Long getHealthRecordId() {
        return healthRecordId;
    }


    /**
     * Generic resource method for retrieving domain objects.
     *
     * @param patientId
     * @param resourceType
     * @param resourceKey
     * @param resourceId
     * @return Object of the resourceType
     */
    private List<? extends Object> patientResources(
        String resourceType,
        List<? extends Object> entities
    ) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        EntityManager em = persistenceSvc.getEntityManager();
        try {
            //persistenceSvc.beginTx(); -- transaction already exists conflict.
            entities = em.createNamedQuery(resourceType+".findAllForPatient")
                .setParameter("healthRecordId", healthRecordId)
                .getResultList();
        } catch (NoResultException ex) {
            return Collections.EMPTY_LIST;
        } finally {
            persistenceSvc.close();
        }
        if (entities == null) {
            return Collections.EMPTY_LIST;
        }
        return entities;
    }

}
