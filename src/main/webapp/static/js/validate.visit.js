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
                var visitid = $("input#visitid").val();
                var title = $("input#title").val();
                var status = $("#status").val();
                var visitdate = $("input#visitdate").val();
                var visittime = $("input#visittime").val();
                var purpose = $("#purpose").val();
                var location = $("input#location").val();
                var provider = $("input#provider").val();
                var comments = $("#comments").val();

                jsonObject = JSON.stringify({
                    "@uri":baseuri +'/visits/' + visitid,
                    "healthRecordId":patientid,
                    "dataSourceId":datasourceid,
                    "sourceId":sourceid,
                    "visitId":visitid,
                    "title":title,
                    "status":status,
                    "visitDate":visitdate,
                    "visitTime":visittime,
                    "purpose":purpose,
                    "location":location,
                    "provider":provider,
                    "comments":comments},null,1);

                /**  Update (PUT) using REST API **/
                $.ajax({
                    type: "PUT",
                    url: url,
                    data: jsonObject,
                    contentType: "application/json",
                    success: function() {
                        parent.doClose();
                        parent.updateRecord(visitid);
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
                title: {
                    required:true
                },
                visitdate: {
                    regexMatchOrEmpty: /^(([0]?[1-9])|(1[0-2]))\/(([0]?[1-9])|([1,2]\d{1})|([3][0,1]))\/[12]\d{3}$/
                },
                visittime: {
                    regexMatchOrEmpty: /^((0?[1-9])|(1[0-2])):[0-5][0-9]\s[ap]m$/i
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
                    required:'Please enter a visit title.'
                },
                visitdate: {
                    regexMatchOrEmpty:'Selected date is invalid. Please use MM/DD/YYYY format.'
                },
                visittime: {
                    regexMatchOrEmpty: 'Selected time is invalid. Please use 00:00 am/pm format, eg: "6:45 pm".'
                },
                comments: {
                    maxlength:'Comments input is too long'
                }
            }
        });
});