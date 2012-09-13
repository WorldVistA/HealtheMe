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
$(function() {
    $('input.text-input').css({backgroundColor:"#FFFFFF"});
    $('input.text-input').focus(function(){
        $(this).css({backgroundColor:"#FFDDAA"});
    });
    $('input.text-input').blur(function(){
        $(this).css({backgroundColor:"#FFFFFF"});
    });

    $('#loginbutton').click(function() {

        // validate and process form

        // first hide any error messages
        $('#message').hide();

        var username = $("input#username").val();
        var password = $("input#password").val();

        if (username.length == 0) {
            $('#message').text('Username is required.').show();
            $("input#username").focus();
            return false;
        }

        if (password.length == 0) {
            $('#message').text('Password is required.').show();
            $("input#password").focus();
            return false;
        }

        var dataString = 'username='+ username + '&password=' + password;
        //alert (dataString);return false;
    });
});