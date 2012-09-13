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

package com.krminc.phr.domain.carenotebook;

import java.util.Vector;
import java.util.List;

/**
 *
 * @author cmccall
 */
public class Pharmacies {

    private Vector<Pharmacy> pharmacies;
    private Long healthRecordId;

    public Long getHealthRecordId() {
        return healthRecordId;
    }

    public Pharmacies(Vector<Pharmacy> pharmacies, Long healthRecordId) {
        if (pharmacies.isEmpty() ) {
            pharmacies.add (new Pharmacy(healthRecordId));
        }

        this.pharmacies = pharmacies;
        this.healthRecordId = healthRecordId;
    }


    public List<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(Vector<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

}
