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
$.validator.methods.regexMatch = function(value, element, param) {
        var re = new RegExp(param);
        return value.match(re);
};

$.validator.methods.notEqualTo = function(value, element, param) {
        return value != $(param).val();
};

$.validator.methods.regexMatchOrEmpty = function(value, element, param) {
    if (value.length > 0) {
        var re = new RegExp(param);
        return value.match(re);
    } else {
        return true;
    }
};

$.validator.methods.notFutureDate = function(value, element, param) {
    if (value.length > 0) {
        var attemptedDate = new Date(value);
        if (attemptedDate > new Date()){
            return false && param;
        }
    }
    return true && param;
};

$().ready(function() {
        $(":input").bind("onBlur" ,function(){
            $(this).valid()}
        );
        $("#emergencyinformationform").validate({
            submitHandler: function(form) {
                form.submit();
            },
        errorPlacement: function(error, element) {
            if(error.text().length > 1){
                var found=false;
                function foundFunction (bool) {
                    found = bool;
                }
                $(element).nextAll().each(function(){
                    if ($(this).hasClass("errorText")) {
                        $(this).text(error.text());
                        foundFunction(true);
                    }
                });

                if (!found) {
                    element
                    .append('<div class="errorText error">' + error.text() + '</div>')
                    .slideDown();
                }
            } else {
                $(element).nextAll().each(function(){
                    if ($(this).hasClass("errorText")) {
                        $(this).remove();
                    }
                });
            }
        },
        success: function(label) {
        },
        highlight: function(element, errorClass) {
             $(element).addClass("error");
        },
        unhighlight: function(element, errorClass) {
            $(element).removeClass("error");
        },
        rules: {
            ambulancenumber: {
                regexMatchOrEmpty: /^\(?([0-9]{3})(\)|-)?(\s)?([0-9]{3})(-|\s)?[0-9]{4}$/i
            },
//            physicianname: {
//                regexMatchOrEmpty: /^[a-zA-Z0-9\s@_\.]+$/i
//            },
            physiciannumber: {
                regexMatchOrEmpty: /^\(?([0-9]{3})(\)|-)?(\s)?([0-9]{3})(-|\s)?[0-9]{4}$/i
            },
            firenumber: {
                regexMatchOrEmpty: /^\(?([0-9]{3})(\)|-)?(\s)?([0-9]{3})(-|\s)?[0-9]{4}$/i
            },
            policenumber: {
                regexMatchOrEmpty: /^\(?([0-9]{3})(\)|-)?(\s)?([0-9]{3})(-|\s)?[0-9]{4}$/i
            },
            poisonnumber: {
                regexMatchOrEmpty: /^\(?([0-9]{3})(\)|-)?(\s)?([0-9]{3})(-|\s)?[0-9]{4}$/i
            },
//            fathername: {
//                regexMatchOrEmpty: /^[a-zA-Z0-9\s@_\.]+$/i
//            },
            fathernumber: {
                regexMatchOrEmpty: /^\(?([0-9]{3})(\)|-)?(\s)?([0-9]{3})(-|\s)?[0-9]{4}$/i
            },
//            mothername: {
//                regexMatchOrEmpty: /^[a-zA-Z0-9\s@_\.]+$/i
//            },
            mothernumber: {
                regexMatchOrEmpty: /^\(?([0-9]{3})(\)|-)?(\s)?([0-9]{3})(-|\s)?[0-9]{4}$/i
            },
//            emergencycontact: {
//                regexMatchOrEmpty: /^[a-zA-Z0-9\s@_\.]+$/i
//            },
//            contactrelationship: {
//                regexMatchOrEmpty: /^[a-zA-Z0-9\s@_\.]+$/i
//            },
            emergencynumber: {
                regexMatchOrEmpty: /^\(?([0-9]{3})(\)|-)?(\s)?([0-9]{3})(-|\s)?[0-9]{4}$/i
            },
//            hospitalname: {
//                regexMatchOrEmpty: /^[a-zA-Z0-9\s@_\.]+$/i
//            },
            hospitalnumber: {
                regexMatchOrEmpty: /^\(?([0-9]{3})(\)|-)?(\s)?([0-9]{3})(-|\s)?[0-9]{4}$/i
            },
//            hospitaladdress: {
//                regexMatchOrEmpty: /^[a-zA-Z0-9\s@_\.]+$/i
//            },
//            hospitaladdress2: {
//                regexMatchOrEmpty: /^[a-zA-Z0-9\s@_\.]+$/i
//            },
//            hospitalcity: {
//                regexMatchOrEmpty: /^[a-zA-Z0-9\s@_\.]+$/i
//            },
//            hospitalstate: {
//                regexMatchOrEmpty: /^([a-zA-Z]?)[\sa-zA-Z]*$/i
//            },
//            specialtyname: {
//                regexMatchOrEmpty: /^[a-zA-Z0-9\s@_\.]+$/i
//            },
            specialtynumber: {
                regexMatchOrEmpty: /^\(?([0-9]{3})(\)|-)?(\s)?([0-9]{3})(-|\s)?[0-9]{4}$/i
            }//,
//            specialtytype: {
//                regexMatchOrEmpty: /^[a-zA-Z0-9\s@_\.]+$/i
//            },
//            emergencydescription: {
//                regexMatchOrEmpty: /^[a-zA-Z0-9\s@_\.]+$/i
//            },
//            treatmentdescription: {
//                regexMatchOrEmpty: /^[a-zA-Z0-9\s@_\.]+$/i
//            }
        },
        messages: {
            ambulancenumber: {
                regexMatchOrEmpty: 'Ambulance number is invalid.'
            },
//            physicianname: {
//                regexMatchOrEmpty: 'Form is invalid, please refresh and try again.'
//            },
            physiciannumber: {
                regexMatchOrEmpty: 'Physician number is invalid.'
            },
            firenumber: {
                regexMatchOrEmpty: 'Fire Department number is invalid.'
            },
            policenumber: {
                regexMatchOrEmpty: 'Police Department number is invalid.'
            },
            poisonnumber: {
                regexMatchOrEmpty: 'Form is invalid, please refresh and try again.'
            },
//            fathername: {
//                regexMatchOrEmpty: 'Form is invalid, please refresh and try again.'
//            },
            fathernumber: {
                regexMatchOrEmpty: 'Father\'s number is invalid.'
            },
//            mothername: {
//                regexMatchOrEmpty: 'Form is invalid, please refresh and try again.'
//            },
            mothernumber: {
                regexMatchOrEmpty: 'Father\'s number is invalid.'
            },
//            emergencycontact: {
//                regexMatchOrEmpty: 'Form is invalid, please refresh and try again.'
//            },
//            contactrelationship: {
//                regexMatchOrEmpty: 'Form is invalid, please refresh and try again.'
//            },
            emergencynumber: {
                regexMatchOrEmpty: 'Emergency Contact\'s number is invalid.'
            },
//            hospitalname: {
//                regexMatchOrEmpty: 'Form is invalid, please refresh and try again.'
//            },
            hospitalnumber: {
                regexMatchOrEmpty: 'Primary Hospital\'s number is invalid.'
            },
//            hospitaladdress: {
//                regexMatchOrEmpty: 'Form is invalid, please refresh and try again.'
//            },
//            hospitaladdress2: {
//                regexMatchOrEmpty: 'Form is invalid, please refresh and try again.'
//            },
//            hospitalcity: {
//                regexMatchOrEmpty: 'Form is invalid, please refresh and try again.'
//            },
//            hospitalstate: {
//                regexMatchOrEmpty: 'Form is invalid, please refresh and try again.'
//            },
//            specialtyname: {
//                regexMatchOrEmpty: 'Form is invalid, please refresh and try again.'
//            },
            specialtynumber: {
                regexMatchOrEmpty: 'Specialty Physician\'s number is invalid.'
            }//,
//            specialtytype: {
//                regexMatchOrEmpty: 'Form is invalid, please refresh and try again.'
//            },
//            emergencydescription: {
//                regexMatchOrEmpty: 'Form is invalid, please refresh and try again.'
//            },
//            treatmentdescription: {
//                regexMatchOrEmpty: 'Form is invalid, please refresh and try again.'
//            }
        }
    });
});