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
 * Dummy class used to back the display of multiple insurance information entities
 */

package com.krminc.phr.domain.carenotebook;

import java.util.Vector;
import java.util.List;

/**
 *
 * @author cmccall
 */
public class InsurancesInformation {

    private Vector<InsuranceInformation> insurances;
    private Long healthRecordId;

    public Long getHealthRecordId() {
        return healthRecordId;
    }

    public InsurancesInformation(Vector<InsuranceInformation> insurances, Long healthRecordId) {
        if (insurances.isEmpty() ) {
            insurances.add (new InsuranceInformation(healthRecordId));
        }
        
        this.insurances = insurances;
        this.healthRecordId = healthRecordId;
    }


    public List<InsuranceInformation> getInsurances() {
        return insurances;
    }

    public void setInsurances(Vector<InsuranceInformation> insurances) {
        this.insurances = insurances;
    }

}
