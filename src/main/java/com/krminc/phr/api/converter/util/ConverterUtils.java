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

package com.krminc.phr.api.converter.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author cmccall
 */
public class ConverterUtils {
    // Suppress default constructor for noninstantiability
    private ConverterUtils() {
        throw new AssertionError();
    }

    public static final String prepareInput(String input){
        return StringEscapeUtils.escapeHtml(input.trim());
    }

    public static final Boolean isValidMask(String mask) {
        mask = prepareInput(mask);
        //mask is binary string
        final String MASK_REGEX = "^[01]+$";
        Pattern pattern =
                Pattern.compile(MASK_REGEX);
        Matcher matcher =
                pattern.matcher(mask);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
    
    public static final Boolean isValidPhoneNumber(String phonenumber) {
        phonenumber = prepareInput(phonenumber);
        //number is optional 1 followed by 3 4 4 digits with optional non-digits surrounding
        final String CONTACT_REGEX = "^1?\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$";
        Pattern pattern =
                Pattern.compile(CONTACT_REGEX);
        Matcher matcher =
                pattern.matcher(phonenumber);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
}
