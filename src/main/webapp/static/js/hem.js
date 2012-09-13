/*
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
/**
 * hem.js - HealtheMe core js.
 *
 * @see http://www.javascripttoolbox.com/bestpractices/#namespace
 * @see http://javascript.crockford.com/prototypal.html
 */

// Prototype inheritance function ( http://javascript.crockford.com/prototypal.html )
if (typeof Object.create !== 'function') {
    Object.create = function (o) {
        function F() {}
        F.prototype = o;
        return new F();
    };
}

// Define hem object.
var HEM = {
    version : '0.0.3',
    debug : false, // default to false - opt-in
    // API functionality
    api : {},
    // Environment variables - Most of these are defined in /includes/inc_js_hem.jspf
    // since they'll be pulling configuration from jsp variables.
    env : {
        defaultTimeout : 20000 // 20 second timeout
    },
    // JSON functionality.
    json : {
        user : {}
    },
    // User Manager specific functionality.
    um : {},
    // User functionality.
    user : {},
    // Form validation functionality, including validation error messages.
    validation : {},
    //Utility functions
    util : {}
};

// --- Top level functionality --- //
//
// Wrapper for console log.
// Only fires log if debugging is enabled and console.log is available.
HEM.log = function(input) {
    if (HEM.debug && window.console && console.log) console.log(input);
};


// --- High level categories (alphabetical order) --- //

/* -------- json -------- */

/*
 * Handle formatting of email coming out of json.
 */
// TODO: this function might belong at a higher level.
// TODO: convert to mailto: link?
HEM.json.user.formatEmail = function(email) {
    if (!email || email == 'undefined') {
        return '&nbsp;';
    }
    return email;
};

/*
 * Convert role string to a more "user friendly" form.
 */
HEM.json.user.formatRole = function(role) {
    switch(role) {
        case "ROLE_PATIENT":
            return "Patient";
            break;
        case "ROLE_PATIENTADMIN":
            return "Patient Admin";
            break;
        case "ROLE_ADMIN":
            return "Admin";
            break;
        case "ROLE_CARETAKER":
            return "Care Coordinator";
            break;
        default:
            return role;
    }
};


/* -------- um (user manager) -------- */

/*
 * Toggle loading animation.
 */
HEM.um.load = function(msg) {
    $("#ajax-container").hide();
    if (msg) $("#progress-msg").html(msg);
    $("#progress").slideDown();
};

HEM.um.loadComplete = function() {
    $("#progress").hide();
    $("#ajax-container").slideDown();
};

// -------- user -------- //

// Editable User - Aggregates primary user properties.
HEM.user.EditableUser = {
    username : null,
    firstName : null,
    middleName : null,
    lastName : null,
    address1 : null,
    address2 : null,
    address3 : null,
    city : null,
    state : null,
    zip : null,
    dob : null,
    homePhone : null,
    workPhone : null,
    mobilePhone : null,
    fax : null,
    fullName : function () {
        var fn;
        if (firstName) fn += firstName;
        if (lastName) fn += ' '+firstName;
        return fn;
    }
}

// Editable Patient - Adds properties to EditableUser.
// Adds gender, which is defined in the patient's Health Record.
HEM.user.EditablePatient = Object.create(HEM.user.EditableUser);
HEM.user.EditablePatient.gender;


// -------- validation -------- //

// Validation error messages.
HEM.validation.msgs = {
    firstname : {
        required : 'Enter a First Name.'
    },
    lastname : {
        required : 'Enter a Last Name.'
    },
    city : {
        required : 'Enter a City.'
    },
    state : {
        required : 'Enter a State.'
    },
    zip : {
        required : 'Enter a Zip.'
    },
    dob : {
        required : 'Enter full Date of Birth.',
        relies: 'Enter full Date of Birth.'
    },
    gender : {
        required : 'Enter a Gender.'
    },
    username : {
        required : 'Enter a Username.',
        minlength : 'Enter a Username of at least 6 characters.',
        regexMatch : 'Invalid Username. Use values: A-Z, a-z, 0-9.',
        unique : 'Username already exists. Please try another one.'
    },
    roles : {
        required : 'Select a Role.'
    },
    email : {
        required : 'Enter an Email Address.',
        valid : 'Enter a valid Email Address.'
    },
    userimage : {
        accept: 'Please use a jpeg file input format.'
    }
}

// -------- Utilities -------- //

// Prepend a number with 0 if 1digit length
HEM.util.pad = function (n) {
    return ("0" + n).slice(-2);
}
