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
public class SpecialtyClinics {

    private Vector<SpecialtyClinic> clinics;
    private Long healthRecordId;

    public Long getHealthRecordId() {
        return healthRecordId;
    }

    public SpecialtyClinics(Vector<SpecialtyClinic> clinics, Long healthRecordId) {
        if (clinics.isEmpty() ) {
            clinics.add (new SpecialtyClinic(healthRecordId));
        }

        this.clinics = clinics;
        this.healthRecordId = healthRecordId;
    }


    public List<SpecialtyClinic> getClinics() {
        return clinics;
    }

    public void setClinics(Vector<SpecialtyClinic> clinics) {
        this.clinics = clinics;
    }


}
