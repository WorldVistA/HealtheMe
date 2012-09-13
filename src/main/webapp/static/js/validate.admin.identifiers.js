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
/*
 * Validate and submit identifiers.
 *
 * @author Daniel Shaw (dshaw.com)
 */
function initValidation() {
    $(":input").bind("onBlur", function(){
        $(this).valid()}
    );
    $(".identifierform").validate({
        rules: {
            hrn: {
                required:true
            }
        },
        messages: {
            hrn: {
                required:'Please enter a valid HRN.'
            }
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
            $(element).parent().addClass(errorClass);
        },
        unhighlight: function(element, errorClass) {
            $(element).parent().removeClass(errorClass);
        }
    });
}

$(document).ready(function() {
    initValidation();
});