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
 * Validate and submit identifiers.
 *
 * @author Daniel Shaw (dshaw.com)
 */

var hasTouched = new Array();
$.validator.methods.relies = function(value, element, param) {
    var retVal = $(element).val() > 0;
    hasTouched[element.name] = true;
    if (typeof param == 'object'){
        for (i=0; i<param.length; i++){
            if (hasTouched[param[i]]==true) {
                retVal = retVal && ($("#" + param[i]).val() > 0);
            }
        }
    }
    return retVal;
};

$.validator.methods.regexMatch = function(value, element, param) {
        var re = new RegExp(param);
        return value.match(re);
};

var initValidation = function() {

    HEM.log(HEM.page.form.validationRules);

    // fail fast. Require validation messages.
    if (!HEM.validation.msgs && HEM.log) {
        HEM.log('ERROR: HEM.validation.msgs undefined.');
        return;
    }
    if (!HEM.page.form.submitForm && HEM.log) {
        HEM.log('ERROR: HEM.page.form.submitForm undefined.');
        return;
    }

    // Alias validation messages to make them more readable.
    var msgs = HEM.validation.msgs;

    $('#userform').validate({
        beforeSubmit: HEM.page.form.checkUsername,
        submitHandler: HEM.page.form.submitForm,
        rules: HEM.page.form.validationRules,
        messages: {
            firstname: { required : msgs.firstname.required },
            lastname: { required : msgs.lastname.required },
            city: { required : msgs.city.required },
            state: { required : msgs.state.required },
            zip: { required : msgs.zip.required },
            month: { required : msgs.dob.required, relies: msgs.dob.relies },
            day: { required : msgs.dob.required, relies: msgs.dob.relies },
            year: { required : msgs.dob.required, relies: msgs.dob.relies },
            gender: { required : msgs.gender.required },
            username: {
                required : msgs.username.required,
                minlength : msgs.username.minlength,
                regexMatch : msgs.username.regexMatch
            },
            userimage: { accept: msgs.userimage.accept },
            roles: { required : msgs.roles.required }
        },
        errorPlacement: function(error, element) {
            if(error.text().length > 1){
                HEM.page.form.placeError(error.text(), element)
            } else {
                $(element).nextAll().each(function(){
                    if ($(this).hasClass("errorText")) {
                        $(this).remove();
                    }
                });
            }
        },
        success: function(label) {},
        highlight: function(element, errorClass) {
            $(element).parent().addClass(errorClass);
        },
        unhighlight: function(element, errorClass) {
            $(element).parent().removeClass(errorClass);
        },
        onsubmit: true
    });
}
