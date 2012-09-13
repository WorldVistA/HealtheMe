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

$().ready(function() {
        $(":input").bind("onBlur" ,function(){
            $(this).valid()}
        );
        $("#detailform").validate({
            submitHandler: function(form) {
                var patientid = $("input#patientid").val();
                var datasourceid = $("input#datasourceid").val();
                var sourceid = datasourceid;
                var title = $("input#title").val();
                var exercisedate = $("input#exercisedate").val();
                var exercisetime = $("input#exercisetime").val();
                var durationhours = $("input#durationhours").val();
                var durationminutes = $("input#durationminutes").val();
                var comments = $("#comments").val();

                if (durationminutes.length==0) durationminutes = "0";
                if (durationhours.length==0) durationhours = "0";

            jsonObject = JSON.stringify({
                "@uri":ctx +'/exercises/',
                "healthRecordId":patientid,
                "dataSourceId":datasourceid,
                "sourceId":sourceid,
                "exerciseId":"",
                "title":title,
                "exerciseDate":exercisedate,
                "exerciseTime":exercisetime,
                "exerciseDurationHours":durationhours,
                "exerciseDurationMinutes":durationminutes,
                "comments":comments},null,1);

            /**  Add using REST API **/
            $.ajax({
                type: "POST",
                url: url,
                data: jsonObject,
                contentType: "application/json",
                success: function() {
                    parent.doClose("added record");
                    parent.showNewRecord("dateadded");
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
            title: {
                required:true
            },
            exercisedate: {
                regexMatch: /^(([0]?[1-9])|(1[0-2]))\/(([0]?[1-9])|([1,2]\d{1})|([3][0,1]))\/[12]\d{3}$/
            },
            exercisetime: {
                regexMatch: /^((0?[1-9])|(1[0-2])):[0-5][0-9]\s[ap]m$/i
            },
            durationhours: {
                digits: true
            },
            durationminutes: {
                required: function() {
                    if ($("input#durationhours").val().length<1) return true;
                    return false;
                },
                digits: true
            },
            comments: {
                maxlength:512
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
            title: {
                required:'Please enter an exercise description.'
            },
            exercisedate: {
                regexMatch:'Selected date is invalid. Please use MM/DD/YYYY format.'
            },
            exercisetime: {
                regexMatch: 'Selected time is invalid. Please use 00:00 am/pm format, eg: "6:45 pm".'
            },
            durationhours: {
                digits: 'Please use numbers for duration.'
            },
            durationminutes: {
                required: 'Please enter a duration.',
                digits: 'Please use numbers for duration.'
            },
            comments: {
                maxlength:'Comments input is too long.'
            }
        }
    });
});