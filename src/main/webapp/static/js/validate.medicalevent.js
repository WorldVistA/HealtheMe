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
                var medicaleventid = $("input#medicaleventid").val();
                var observeddate = $("input#observeddate").val();
                var comments = $("#comments").val();
                var event = $("input#event").val();
                var resolveddate = $("#resolveddate").val();
                var howended = $("input#howended").val();
                var status = $("#status").val();

                jsonObject = JSON.stringify({
                    "@uri":baseuri +'/medicalevents/' + medicaleventid,
                    "healthRecordId":patientid,
                    "dataSourceId":datasourceid,
                    "sourceId":sourceid,
                    "medicalEventId":medicaleventid,
                    "observedDate":observeddate,
                    "resolvedDate":resolveddate,
                    "howEnded":howended,
                    "event":event,
                    "status":status,
                    "comments":comments},null,1);

                /**  Update (PUT) using REST API **/
                $.ajax({
                    type: "PUT",
                    url: url,
                    data: jsonObject,
                    contentType: "application/json",
                    success: function() {
                        parent.doClose();
                        parent.updateRecord(medicaleventid);
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
            event: {
                required:true,
                maxlength:512
            },
            howended: {
                maxlength:512
            },
            observeddate: {
                regexMatchOrEmpty: /^(([0]?[1-9])|(1[0-2]))\/(([0]?[1-9])|([1,2]\d{1})|([3][0,1]))\/[12]\d{3}$/,
                notFutureDate: true
            },
            resolveddate: {
                regexMatchOrEmpty: /^(([0]?[1-9])|(1[0-2]))\/(([0]?[1-9])|([1,2]\d{1})|([3][0,1]))\/[12]\d{3}$/,
                notFutureDate: true
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
            event: {
                required:'Enter a medical event title.',
                maxlength:'Medical event title is too long'
            },
            howended: {
                maxlength:'How event Ended input is too long'
            },
            observeddate: {
                regexMatchOrEmpty: 'Selected date is invalid. Please use MM/DD/YYYY format.',
                notFutureDate: 'Dates in the future cannot be entered for this item'
            },
            resolveddate: {
                regexMatchOrEmpty: 'Selected date is invalid. Please use MM/DD/YYYY format.',
                notFutureDate: 'Dates in the future cannot be entered for this item'
            },
            comments: {
                maxlength:'Comments input is too long'
            }
        }
    });
});