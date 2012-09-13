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

$.validator.addMethod("isPhoneNumber",
    function(value, element, params){
        if (value.length > 0) {
            var re = new RegExp(/^\(?([0-9]{3})(\)|-)?(\s)?([0-9]{3})(-|\s)?[0-9]{4}$/i);
            return value.match(re);
        } else {
            return true;
        }
    },
    $.format("{0} phone number is invalid")
);

$.validator.addClassRules("phonenumber", { isPhoneNumber: '' });
$.validator.addClassRules("faxnumber", { isPhoneNumber: 'Fax' });

$().ready(function() {
        $(":input").bind("onBlur" ,function(){
            $(this).valid()}
        );
        $(".familymemberform").validate({
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
        }
    });
});
