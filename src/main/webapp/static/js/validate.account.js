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

// with regards to http://blog.stevenlevithan.com/archives/javascript-password-validator
$.validator.methods.passwordValidCheck = function(value, element, param) {
    var	re = {
                    alpha:   /[A-Z]/gi,
                    numeric: /[0-9]/g,
                    special: /[^A-Z0-9]/gi
            }
    // enforce alpha/numeric/special rules - 1 of each
    for (rule in re) {
            if ((value.match(re[rule]) || []).length < 1)
                    return false;
    }

    //check if equal to username, expecting param to hold jQuery selector string
    return !(value == $(param).val());
};

$().ready(function() {
    $(":input").bind("onBlur" ,function(){
        $(this).valid()}
    );
        
    $("form#passwordform").validate({
        submitHandler: function(form) {
                return form.submit();
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
                    .append('<div class="errorText">' + error.text() + '</div>')
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
           // $(element).parent().addClass(errorClass);
        },
        unhighlight: function(element, errorClass) {
            //$(element).parent().removeClass(errorClass);
        },
        rules: {
            currentPassword: {
                required: true
            },
            newPassword: {
                required: true,
                minlength: 8,
                maxlength: 20,
                passwordValidCheck: "#username" //ensure pw is valid, doesnt match username
            },
            newPassword2: {
                required: true,
                equalTo: "#newPassword"
            }
        },
        messages: {
            currentPassword: {
                required: 'Please enter current password.'
            },
            newPassword: {
                required: 'Please enter new password.',
                minlength: 'Password is 8-20 characters.',
                maxlength: 'Password is 8-20 characters.',
                passwordValidCheck: 'Password does not meet requirements.'
            },
            newPassword2: {
                required: 'Please re-enter new password.',
                equalTo: 'New passwords do not match.'
            }
        }
    });
});