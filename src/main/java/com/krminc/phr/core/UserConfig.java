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
package com.krminc.phr.core;

import java.util.Map;
import java.util.TreeMap;

/**
 * Statically defined Roles for this application.
 *
 * @author Daniel Shaw (dshaw.com)
 */
public class UserConfig {

    /** "ROLE_ADMIN" */
    public final static String ROLE_ADMIN = "ROLE_ADMIN";

    /** "ROLE_PATIENTADMIN" */
    public final static String ROLE_PATIENTADMIN = "ROLE_PATIENTADMIN";

    /** "ROLE_PATIENT" */
    public final static String ROLE_PATIENT = "ROLE_PATIENT";

    /** "ROLE_CARETAKER" */
    public final static String ROLE_CARETAKER = "ROLE_CARETAKER";

    /** "ROLE_RESET" */
    public final static String ROLE_RESET = "ROLE_RESET";


    public enum SecurityRole { ROLE_ADMIN, ROLE_PATIENTADMIN, ROLE_PATIENT, ROLE_CARETAKER, ROLE_RESET }


    private static final Map<String, String> roles = new TreeMap<String, String>();

    // Initialize prototype deck
    static {
        for (SecurityRole role : SecurityRole.values())
            roles.put(role.toString(), getRoleDescription(role));
    }

    public static Map<String, String> getRoles() {
        return new TreeMap<String, String>(roles); // Return copy of roles list
    }

    public static String getRoleDescription(SecurityRole role) {
        switch (role) {
            case ROLE_ADMIN:
                return "System Administrator is a super user. Creates Patient Administrators.";
            case ROLE_PATIENTADMIN:
                return "Patient Administrator can enroll patients and access patient patient demographic data.";
            case ROLE_PATIENT:
                return "An individual patient. A patient only has access to their own data.";
            case ROLE_CARETAKER:
                return "A caretaker of an individual patient, responsible for their record.";
            case ROLE_RESET:
                return "An individual user who needs to reset their password before using the system.";
            default:
                return "";
        }
    }

}
