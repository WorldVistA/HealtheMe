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
        $("#detailform").bind("invalid-form.validate", function(e, validator) {
                if (validator.numberOfInvalids()) {
                    $("#notifier").html("<strong class=\"error\">Please fix any fields highlighted in red before continuing.<\/strong>").slideDown();
                } else {
                    $("#notifier").html("").slideUp();
                }
        }).validate({
            submitHandler: function() {
                var sourceid = $("input#sourceid").val();
                var testname = $("input#testname").val();
                var result = $("input#result").val();
                var resultunit = $("input#resultunit").val();
                var resultdate = $("input#resultdate").val();
                var patientid = $("input#patientid").val();

                jsonObject = JSON.stringify({
                    "@uri":ctx +'/results/',
                    "healthRecordId":patientid,
                    "dataSourceId":"",
                    "sourceId":sourceid,
                    "resultId":"",
                    "testName":testname,
                    "resultDateExact":resultdate,
                    "result":result,
                    "resultUnit":resultunit},null,1);

                /**  Add using REST API **/
                $.ajax({
                    type: "POST",
                    url: apipath + "/results/",
                    data: jsonObject,
                    contentType: "application/json",
                    success: function() {
                        $('#notifier')
                        .html('Created record.')
                        .removeClass('ui-state-error').addClass('ui-state-highlight')
                        .slideDown();
                        document.forms['detailform'].reset();
                    },
                    error: function() {
                        $('#notifier').html('Unable to create new record.')
                            .removeClass('ui-state-highlight').addClass('ui-state-error')
                            .slideDown();
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
                            .append('<div class="errorText">' + error.text() + '</p>')
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
                $(element).parent().addClass(errorClass);
            },
            unhighlight: function(element, errorClass) {
                $(element).parent().removeClass(errorClass);
            },
            rules: {
                sourceid: {
                    required:true
                },
                action: {
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
                patientid: {
                    required:true,
                    digits:true
                },
                testname: {
                    required:true,
                    minlength:1
                },
                resultdate: {
                    regexMatchOrEmpty: /^(([0]?[1-9])|(1[0-2]))\/(([0]?[1-9])|([1,2]\d{1})|([3][0,1]))\/[12]\d{3}$/
                }
            },
            messages: {
                sourceid: {
                    required:'Form is invalid, please refresh and try again.'
                },
                action: {
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
                patientid: {
                    required:'Form is invalid, please refresh and try again.',
                    digits:'Form is invalid, please refresh and try again.'
                },
                testname: {
                    required:'Please enter a test name.',
                    minlength:'Please enter a longer test name.'
                },
                resultdate: {
                    regexMatchOrEmpty:'Selected date is invalid. Please use MM/DD/YYYY format.'
                }
            }
        });
});