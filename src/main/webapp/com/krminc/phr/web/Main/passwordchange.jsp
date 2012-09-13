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
    Document   : passwordchange
    Created on : Mar 2, 2010, 4:10:34 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>

<head>

<c:choose>
    <c:when test="${empty param.pwStatus}">
        <c:set scope="page" var="status" value=""></c:set>
    </c:when>
    <c:otherwise>
        <c:set scope="page" var="status" value="${param.pwStatus}"></c:set>
        <c:if test="${status == 'updated'}">
            <%@ include file="/includes/components/inc_redirector.jspf" %>
        </c:if>
    </c:otherwise>
</c:choose>

    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <title>HealtheMe - Change Password</title>
    <style type="text/css">
        .strengthLabel {
            text-align:     center;
            color:          white;
            font-size:      .9em;
            font-weight:    bold;
            width:          100px;
            display:        inline-block;
        }
    </style>
    <style type="text/css">
        input { float: right; }
        .hv_element { width:290px !important; }
    </style>

    <script type="text/javascript" src="${ctx_static}/js/jquery.strengthindicator.js"></script>
    <script type="text/javascript">
        $(function() {
            $('#newPassword').strengthindicator();

            var pwStatus = "${fn:escapeXml(status)}";
            var statusStr = "";
            
            //notifier dialog button click
            $(".closebutton").click(function(x){
                x.preventDefault();
                $(this).parent().fadeOut();
            });

            if (pwStatus.length) {
                switch(pwStatus) {
                    case "updated":
                        statusStr = "Password successfully updated. Redirecting to your Dashboard.";
                        break;
                    case "null":
                        statusStr = "";
                        break;
                    case "failed":
                        statusStr = "An error occurred updating your password.";
                        break;
                    case "invalid":
                        statusStr = "Password does not meet all requirements, please try again.";
                        break;
                    case "nomatch":
                        statusStr = "Old password does not match what is on file.";
                        break;
                    case "forceReset":
                        statusStr = "You must update your password.";
                        break;
                    default:
                        statusStr = "An error occurred updating your password.";
                        break;
                }
                if (statusStr.length) notify(statusStr);
            }
        });

        //display notification to user
        function notify(message) {
            $(".notificationtext").text(message);
            $("#notifier").fadeIn();
        }

        function cancelPassword() {
            $("#passwordform .errorText").remove();
            $("#newPassword").val("");
            $("#newPassword2").val("");
            $("#currentPassword").val("").focus();
        }
    </script>
    <script type="text/javascript" src="${ctx_static}/js/validate.account.js"></script>
    <%--<script type="text/javascript" src="${ctx_static}/js/passwordstrength.js"></script>--%>

</head>
<body id="credentials">

    <h1>Change Password</h1>

	<div class="def-container">
        <p>For security purposes - You must change your password to proceed.</p>
    </div>

    <div id="notifier">
        <span class="notificationtext"></span>
        <span class="closebutton"><a href="">X</a></span>
    </div>

    <form id="passwordform" name="passwordform" class="account" action="${ctx}/form/self/${ it.authenticatedUserId }/password/" method="post">
        <input name="redirectUri" type="hidden" value="${requestScope['javax.servlet.forward.request_uri']}" />

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
                    <input id="newPassword" type="password" name="newPassword" maxlength="20"/>
                    <div class="clear"> </div>
                </div><!-- /#newPassword-fld -->
                <div id="newPassword2-fld" class="hv_element">
                    <label for="newPassword2">Re-enter New Password</label>
                    <input id="newPassword2" type="password" name="newPassword2" maxlength="20" />
                    <div class="clear"> </div>
                </div><!-- /#newPassword2-fld -->
                <div class="clear"> </div>
                <input type="submit" id="submit" value="Submit" class="submit phr-input-button" />
            </fieldset><!-- /#fldsetPassword -->

        </div><!-- /.section-panel -->
    </form>
</body>