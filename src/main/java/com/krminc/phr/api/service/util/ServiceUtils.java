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

package com.krminc.phr.api.service.util;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author cmccall
 */
public class ServiceUtils {
    // Suppress default constructor for noninstantiability
    private ServiceUtils() {
        throw new AssertionError();
    }
    /* Utility Methods */

    //validate zip code
    public static boolean isZip(String zip) {
        final String ZIP_REGEX = "^\\d{5}(-\\d{4})?$";

        Pattern pattern =
            Pattern.compile(ZIP_REGEX);
        Matcher matcher =
            pattern.matcher(zip);
        return matcher.find();
    }

    //validate country
    //ote that this could check an official list of supported countries but is here as a sanity check
    public static boolean isCountry(String country) {
        final String COUNTRY_REGEX = "^[a-zA-Z]{2}$"; //2 alpha chars

        Pattern pattern =
            Pattern.compile(COUNTRY_REGEX);
        Matcher matcher =
            pattern.matcher(country);
        return matcher.find();
    }

    //validate state
    //note that this could check an official list of states/provinces but is here as a sanity check
    public static boolean isStateOrProvince(String state) {
        final String STATE_REGEX = "^([a-zA-Z]?)[\\sa-zA-Z]*$"; //alpha chars and spaces

        Pattern pattern =
            Pattern.compile(STATE_REGEX);
        Matcher matcher =
            pattern.matcher(state);
        return matcher.find();
    }

    //validate ssn
    public static boolean isSSN(String ssn) {
        final String SSN_REGEX = "^\\d{9}$";

        Pattern pattern =
            Pattern.compile(SSN_REGEX);
        Matcher matcher =
            pattern.matcher(ssn);
        return matcher.find();
    }

    //validate medicaid id
    public static boolean isMedicaidId (String medicaidId) {
        final String MEDICAID_REGEX = "^\\d{9}$";

        Pattern pattern =
            Pattern.compile(MEDICAID_REGEX);
        Matcher matcher =
            pattern.matcher(medicaidId);
        return matcher.find();
    }

    //validate username
    public static boolean isUsername (String username) {
        final String USERNAME_REGEX = "^[a-zA-Z0-9]+$";

        Pattern pattern =
            Pattern.compile(USERNAME_REGEX);
        Matcher matcher =
            pattern.matcher(username);
        return matcher.find();
    }

    public static boolean hasAlphaCharacters (String characters) {
        final String VALID_REGEX = "^[a-zA-Z]+$";

        Pattern pattern =
            Pattern.compile(VALID_REGEX);
        Matcher matcher =
            pattern.matcher(characters);
        return matcher.find();
    }

    public static boolean hasValidCharacters (String characters) {
        final String VALID_REGEX = "^[a-zA-Z0-9\\s@_\\.]+$";

        Pattern pattern =
            Pattern.compile(VALID_REGEX);
        Matcher matcher =
            pattern.matcher(characters);
        return matcher.find();
    }

    //validate name
    public static boolean isName (String name) {
//        final String NAME_REGEX = "^[a-zA-Z\\s\\W]+$";
        final String NAME_REGEX = "^((?:[A-Z](?:('|(?:[a-z]{1,3}))[A-Z])?[a-z]+)|(?:[A-Z]\\.))(?:([ -])((?:[A-Z](?:('|(?:[a-z]{1,3}))[A-Z])?[a-z]+)|(?:[A-Z]\\.)))?$";

        Pattern pattern =
            Pattern.compile(NAME_REGEX);
        Matcher matcher =
            pattern.matcher(name);
        return matcher.find();
    }

    //validate title
    public static boolean isTitle (String title) {
        if (title.equals("Mr.") || title.equals("Mrs.") || title.equals("Ms.")) {
            return true;
        } else {
            return false;
        }
    }

    //validate suffix
    public static boolean isSuffix (String suffix) {
        if (suffix.equals("Sr.") || suffix.equals("Jr.") || suffix.equals("III")) {
            return true;
        } else {
            return false;
        }
    }

    //validate gender
    public static boolean isGender (String gender) {
        if (gender.equals("M") || gender.equals("F")) {
            return true;
        } else {
            return false;
        }
    }

    //validate marital status
    public static boolean isMaritalStatus (String maritalStatus) {
        if (maritalStatus.equals("S") || maritalStatus.equals("M") || maritalStatus.equals("D")) {
            return true;
        } else {
            return false;
        }
    }

    //TODO validate occupation
    public static boolean isOccupation (String occupation) {
        final String OCCUPATION_REGEX = "^[a-zA-Z\\s\\W]+$";
        Pattern pattern =
                Pattern.compile(OCCUPATION_REGEX);
        Matcher matcher =
                pattern.matcher(occupation);
        return matcher.find();
    }

    //validate blood type
    public static boolean isBloodType (String bloodType) {
        if (bloodType.equals("A") || bloodType.equals("B") || bloodType.equals("O") || bloodType.equals("AB")) {
            return true;
        } else {
            return false;
        }
    }

    //validate email
    public static boolean isEmail (String emailAddress) {
        final String EMAIL_REGEX = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern pattern =
                Pattern.compile(EMAIL_REGEX);
        Matcher matcher =
                pattern.matcher(emailAddress);
        return matcher.find();
    }

    //validate phone number
    public static boolean isPhoneNumber (String phoneNumber) {
        final String PHONE_REGEX = "^\\(?([0-9]{3})(\\)|-)?(\\s)?([0-9]{3})(-|\\s)?[0-9]{4}$";
        Pattern pattern =
                Pattern.compile(PHONE_REGEX);
        Matcher matcher =
                pattern.matcher(phoneNumber);
        return matcher.find();
    }

    public static boolean isPhoneNumberOrEmpty(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return true;
        } else {
            return isPhoneNumber(phoneNumber);
        }
    }

    public static boolean hasValidCharactersOrEmpty(String string) {
        if (string == null || string.isEmpty()) {
            return true;
        } else {
            return hasValidCharacters(string);
        }
    }

    public static boolean isAlphaOrEmpty(String string) {
        if (string == null || string.isEmpty()) {
            return true;
        } else {
            final String VALID_REGEX = "^[a-zA-Z\\s]+$";

            Pattern pattern =
                Pattern.compile(VALID_REGEX);
            Matcher matcher =
                pattern.matcher(string);
            return matcher.find();
        }
    }

    //validate password
    public static boolean isPassword(String password) {
        //password vars
        final int MIN_LENGTH = 8;
        final int MAX_LENGTH = 20;
        final String NUMBER_REGEX = "\\d";
        final String CHAR_REGEX = "[a-zA-Z]";
        final String SPECIALCHAR_REGEX = "[^A-Za-z\\d]";

        //check length
        if ((password.length() < MIN_LENGTH) || (password.length() > MAX_LENGTH)) return false;

        String[] regexes = {NUMBER_REGEX,CHAR_REGEX,SPECIALCHAR_REGEX};
        //ensure 1 of each: digit, character, non-character
        for (String rule : regexes)
        {
        Pattern pattern =
            Pattern.compile(rule);
        Matcher matcher =
            pattern.matcher(password);
        if (!(matcher.find())) return false;
        }

        return true;
    }

}
