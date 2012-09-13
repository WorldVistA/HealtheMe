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

package com.krminc.phr.core;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 *
 * @author cmccall
 */
public class VitalConfig {

    public final static String VITAL_PAIN = "PAIN";
    public final static String VITAL_TEMPERATURE = "TEMPERATURE";
    public final static String VITAL_BMI = "BODY MASS INDEX";
    public final static String VITAL_HEIGHT = "HEIGHT";
    public final static String VITAL_WEIGHT = "WEIGHT";
    public final static String VITAL_BLOODPRESSURE = "BLOOD PRESSURE";
    public final static String VITAL_PULSE = "PULSE";
    public final static String VITAL_PEAKFLOW = "PEAK FLOW";
    public final static String VITAL_GLUCOSE = "GLUCOSE";

    public static ArrayList<String> getVitalTypes() {
        ArrayList<String> vitals = new ArrayList<String>();
        vitals.add(VITAL_PAIN);
        vitals.add(VITAL_TEMPERATURE);
        vitals.add(VITAL_BMI);
        vitals.add(VITAL_HEIGHT);
        vitals.add(VITAL_WEIGHT);
        vitals.add(VITAL_BLOODPRESSURE);
        vitals.add(VITAL_PULSE);
        vitals.add(VITAL_PEAKFLOW);
        vitals.add(VITAL_GLUCOSE);
        return vitals;
    }

    public static boolean isValidVitalType(String vitalType){
        ArrayList<String> vitals = getVitalTypes();
        ListIterator vitalsIterator = vitals.listIterator();
        while (vitalsIterator.hasNext()){
            if (vitalType.equalsIgnoreCase(vitalsIterator.next().toString()))
                return true;
        }
        return false;
    }

}
