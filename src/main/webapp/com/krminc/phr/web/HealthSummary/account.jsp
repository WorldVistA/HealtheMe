<%--

    Copyright (C) 2012 KRM Associates, Inc. healtheme@krminc.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

            http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

--%>
<%-- 
    Document   : account
    Created on : Oct 30, 2009, 11:42:06 AM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>
<c:choose>
    <c:when test="${empty param.pwStatus}">
        <c:set scope="page" var="status" value=""></c:set>
    </c:when>
    <c:otherwise>
        <c:set scope="page" var="status" value="${param.pwStatus}"></c:set>
    </c:otherwise>
</c:choose>
<head>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <title>HealtheMe - Personal Information - My Account</title>
    <style type="text/css">
        .strengthLabel {
            text-align:     center;
            color:          white;
            font-size:      .9em;
            font-weight:    bold;
            width:          100px;
            display:        inline-block;
        }
        th.accessheader {
            font-weight:    bold;
            text-align:     center;
        }
        td.accesscell {
            padding:        4px;
            border:         1px solid #DBD4AB;
        }
    </style>

    <script type="text/javascript">
        $(function() {
            $('#newPassword').strengthindicator();

            var pwStatus = "${fn:escapeXml(status)}";
            var statusStr = "";
            
            //pull patient info
            $.ajax({
                url:'${apipath}/patients/${it.healthRecordId}/account/',
                type:'GET',
                timeout:5000,
                dataType:'json',
                error:function(obj,strError){
                    $('#returned-data').text( strError );
                },
                success:function(data){
                    handleData(data);
                }
            });

            //pull access data
            $.ajax({
                url:'${apipath}/patients/${it.healthRecordId}/access/',
                type:'GET',
                timeout:5000,
                dataType:'json',
                error:function(obj,strError){
                    $('#returned-data').text( strError );
                },
                success:function(data){
                    if(typeof(data)!="object") {
                        window.location="/login";
                        return;
                    }

                    if (data != null) {
                        var html = '';
                        if (data.users instanceof Array) {
                            html += '<table id="accesstable">';
                            html += '<th class="accessheader">User</th><th class="accessheader">Phone</th><th class="accessheader">Fax</th><th class="accessheader">Email</th><th class="accessheader">Actions</th>';
                            for (user in data.users) {
                                if (data.ids[user] > 0) {
                                    html += '<tr id="useraccess' + data.ids[user] +'">';
                                    html += "<td class='accesscell'>" + detectNull(data.users[user]) + "</td><td class='accesscell'>" + detectNull(data.phones[user]) + "</td><td class='accesscell'>" + detectNull(data.faxes[user]) + "</td><td class='accesscell'>" + detectNull(data.emails[user]) + "</td><td class='accesscell'><a class='removeaccess' href='#" + data.ids[user] +"'>Remove</a></td>";
                                    html += '</tr>';
                                }
                            }
                            html += '</table>';
                        } else {
                            html = "<div>You are the only user with access to this health record.</div>";
                        }
                        $("div#access").append(html);
                    }
                }
            });
            
            //pull preference info
            $.ajax({
                url:'${apipath}/patients/${it.healthRecordId}/preferences/',
                type:'GET',
                timeout:5000,
                dataType:'json',
                error:function(obj,strError){
                    $('#returned-data').text( strError );
                },
                success:function(data){
                    handlePreferences(data);
                }
            });

            //notifier dialog button click
            $(".closebutton").click(function(x){
                x.preventDefault();
                $(this).parent().fadeOut();
            });

            if (pwStatus.length) {
                switch(pwStatus) {
                    case "updated":
                        statusStr = "Password successfully updated.";
                        break;
                    case "null":
                        statusStr = "";
                        break;
                    case "failed":
                        statusStr = "ERROR: Password could not be updated.";
                        break;
                    case "invalid":
                        statusStr = "Password does not meet requirements. Yry again.";
                        break;
                    case "nomatch":
                        statusStr = "Current password is incorrect. Try again.";
                        break;
                    case "forceReset":
                        statusStr = "Your curent password must be updated for security purposes.";
                        break;
                    default:
                        statusStr = "ERROR: Password could not be updated.";
                        break;
                }
                if (statusStr.length) notify(statusStr);
            }

            $("a.removeaccess").live("click", function(){
                removeAccess(this.hash.slice(1));
            });
        });

        //display notification to user
        function notify(message) {
            $(".notificationtext").text(message);
            $("#notifier").show();
        }
        
        function detectNull(message) {
            if (message == null || message == "null") {
                message = '-';
            }
            return message;
        }

        function handleData(data){
            for (var subData in data){
                if (typeof data[subData]=="object") {
                    handleData(data[subData]);
                } else {
                    showData(subData, data[subData]);
                }
            }
        }

        function showData(dataType, dataValue) {
            switch (dataType) {
                case "username":
                case "dateCreated":
                case "lastLogin":
                case "numLogins":
                    $("span#" +dataType).append(dataValue);
                    break;
                default:
                    $("input#" +dataType).val(dataValue);
                    break;
            }
        }

        function cancelPassword() {
            $("#passwordform .errorText").remove();
            $("#newPassword").val("");
            $("#newPassword2").val("");
            $("#currentPassword").val("").focus();
        }

        function removeAccess(reqId){
            $.ajax({
                url:apipath + "/patients/" + ${it.healthRecordId} + "/removeAccess/" + reqId,
                type:"GET",
                timeout:5000,
                dataType:"json",
                error:function(obj,strError){
                    //do nothing
                },
                success:function(data){
                    if(typeof(data)!="object") {
                        window.location="/login";
                        return;
                    }
                    if (data.status == true) {
                        alert("Access to this health record for the user has been revoked.");
                        $("tr#useraccess" + reqId).remove();
                        if (! $("table#accesstable .accesscell").length > 0) {
                            var html = "<div>You are the only user with access to this health record.</div>";
                            $("div#access").html(html);
                        }
                    } else {
                        //error
                        alert("An error occurred removing access. Try again or contact an administrator.");
                    }
                }
            });
        }
        
        function handlePreferences(data){
            if (data != null) {
                if (data.careNotebook == 'true') {
                    $('input#carenotebook').attr('checked','checked');
                }
            }
        }
    </script>
    <script type="text/javascript" src="${ctx_static}/js/validate.account.js"></script>
    <script type="text/javascript" src="${ctx_static}/js/jquery.strengthindicator.js"></script>
    <style type="text/css">
        input { float: right; }
        .hv_element { width:290px !important; }
    </style>
</head>
<body id="myaccount">

    <h1>My Account</h1>

    <h2>Change Password</h2>

    <div class="def-container">
        <p>Update your password to ensure the integrity of your account.</p>
    </div>
    
    <div id="notifier">
        <span class="notificationtext"></span>
        <span class="closebutton"><a href="">X</a></span>
    </div>
    
    <form id="passwordform" name="passwordform" class="account" action="${ctx}/form/self/${ it.healthRecordId }/password/" method="post">
        <input name="redirectUri" type="hidden" value="${requestScope['javax.servlet.forward.request_uri']}" />
        <input name="asHealthRecord" type="hidden" value="true" />

        <div class="section-panel">
            <div class="container rt" style="float:right; padding:5px; width:200px;">
                <p style="font-weight:bold">Password Requirements</p>

                <ul style="list-style-type:disc; padding-left:14px; width:190px;">
                    <li>Length - 8 to 20 characters</li>
                    <li>Must contain 1 or more numbers</li>
                    <li>Must contain 1 or more special characters</li>
                    <li>Must not be your username</li>
                </ul>
            </div>

            <fieldset id="fldsetPassword" style="width:300px;">

                <div id="currentPassword-fld" class="hv_element">
                    <label for="currentPassword">Current Password</label>
                    <input id="currentPassword" type="password" name="currentPassword" maxlength="20" />
                    <div class="clear"> </div>
                </div><!-- /#currentPassword-fld -->
                <div class="hv_element" style="text-align:right">
                   <span id="passwordStrength" class="strengthLabel"></span>
                </div>
                <div id="newPassword-fld" class="hv_element">
                    <label for="newPassword">New Password</label>
                    <input id="newPassword" type="password" name="newPassword" maxlength="20" />
                    <div class="clear"> </div>
                </div><!-- /#newPassword-fld -->
                <div id="newPassword2-fld" class="hv_element">
                    <label for="newPassword2">New Password again</label>
                    <input id="newPassword2" type="password" name="newPassword2" maxlength="20" />
                    <div class="clear"> </div>
                </div><!-- /#newPassword2-fld -->
                <div class="clear"> </div>
                <div class="phr-input-button-align-right">
                    <%--<input type="button" class="phr-input-button" value="Cancel" onClick="cancelPassword()"/>--%>
                    <input type="submit" id="submit" value="Submit" class="submit phr-input-button" />
                </div><!-- /.phr-input-button-align-right -->
            </fieldset><!-- /#fldsetPassword -->

        </div><!-- /.section-panel -->
    </form>
    <br />

    <h2>Revoke Access</h2>

    <div class="def-container">
        <p>Use this section to check or remove access others have to your data.</p>
    </div>
                    
    <div class="section-panel">
        <div id="access">
        </div>
    </div><!-- /.section-panel -->

    <div class="clear"> </div>
    <br />
    
    <h2>Preferences</h2>

    <div class="def-container">
        <p>Use this section to alter your account-related preferences.</p>
    </div>
           
    <form id="preferenceform" name="preferenceform" class="account" action="${ctx}/form/self/${ it.healthRecordId }/preferences/" method="post">
        <div class="section-panel">
            <div class="container rt" style="float:right; padding:5px; width:200px;">
                <p style="font-weight:bold">Care Notebook</p>
                <ul style="list-style-type:disc; padding-left:14px; width:190px;">
                    <li>Add or remove access to Care Notebook data sections, intended for use for children with special needs.</li>
                </ul>
            </div>
            <fieldset style="width:300px;">
                <div id="preference">
                    <div id="careNotebook-fld" class="hv_element">
                        <label for="carenotebook">Use Care Notebook?</label>
                        <input type="checkbox" id="carenotebook" name="carenotebook" title="Care Notebook" size="20" maxlength="32" />
                        <div class="clear"> </div>
                    </div><!-- /#careNotebook-fld -->
                    <div class="clear"> </div>
                    <div class="phr-input-button-align-right">
                        <%--<input type="button" class="phr-input-button" value="Cancel" onClick="cancelPassword()"/>--%>
                        <input type="submit" id="submit" value="Submit" class="submit phr-input-button" />
                    </div><!-- /.phr-input-button-align-right -->
                </div>
            </fieldset>
        </div><!-- /.section-panel -->
    </form>

</body>