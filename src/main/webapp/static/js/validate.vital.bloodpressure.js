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
        $("#detailform").validate({
            submitHandler: function(form) {
                var patientid = $("input#patientid").val();
                var datasourceid = $("input#datasourceid").val();
                var sourceid = datasourceid;
                var bloodpressureid = $("input#bloodpressureid").val();
                var observeddate = $("input#observeddate").val();
                var systolic = $("input#systolic").val();
                var diastolic = $("input#diastolic").val();

                jsonObject = JSON.stringify({
                    "@uri":baseuri +'/vitals/bloodpressure/' + bloodpressureid,
                    "healthRecordId":patientid,
                    "dataSourceId":datasourceid,
                    "sourceId":sourceid,
                    "id":bloodpressureid,
                    "observedDate":observeddate,
                    "systolic":systolic,
                    "diastolic":diastolic},null,1);

                /**  Update (PUT) using REST API **/
                $.ajax({
                    type: "PUT",
                    url: url,
                    data: jsonObject,
                    contentType: "application/json",
                    success: function() {
                        parent.doClose("Record updated");
                        parent.updateRecord(bloodpressureid);
                    },
                    error: function() {
                        alert("Error adding record.");
                        $("#submit").removeAttr("disabled");
                    }
                });
                return false;
            },
            errorPlacement: function(error, element) {
                if(error.text().length > 1){
                    var found=false;
                    function foundFunction (bool) { found = bool;}
                    $(element).nextAll().each(function(){
                        if ($(this).hasClass("errorText")) {
                            $(this).text(error.text());
                            foundFunction(true);
                        }
                    });

                    if (!found) {
                        element.parent()
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
            },
            unhighlight: function(element, errorClass) {
            },
            rules: {
                patientid: {
                    required:true,
                    digits: true
                },
                sourceid: {
                    required:true
                },
                sourceurl: {
                    required:true,
                    minlength:1
                },
                returnurl: {
                    required:true,
                    minlength:1
                },
                systolic: {
                    required:true,
                    digits: true,
                    range: [40, 240]

                },
                diastolic: {
                    required:true,
                    digits: true,
                    range: [30, 140]
                },
                observeddate: {
                    regexMatch: /^(([0]?[1-9])|(1[0-2]))\/(([0]?[1-9])|([1,2]\d{1})|([3][0,1]))\/[12]\d{3}$/,
                    notFutureDate: true
                }
            },
            messages: {
                patientid: {
                    required:'Form is invalid, please refresh and try again.',
                    digits:'Form is invalid, please refresh and try again.'
                },
                sourceid: {
                    required:'Form is invalid, please refresh and try again.'
                },
                sourceurl: {
                    required:'Form is invalid, please refresh and try again.',
                    minlength:'Form is invalid, please refresh and try again.'
                },
                returnurl: {
                    required:'Form is invalid, please refresh and try again.',
                    minlength:'Form is invalid, please refresh and try again.'
                },
                systolic: {
                    required:'Enter a systolic blood pressure',
                    digits:'Please only enter numbers for systolic blood pressure',
                    range: 'Please enter a value in the valid range (40-240).'
                },
                diastolic: {
                    required:'Enter a diastolic blood pressure',
                    digits:'Please only enter numbers for diastolic blood pressure',
                    range: 'Please enter a value in the valid range (30-140).'
                },
                observeddate: {
                    regexMatch: 'Selected date is invalid. Please use MM/DD/YYYY format.',
                    notFutureDate: 'Dates in the future cannot be entered for this item'
                }
            }
        });
});