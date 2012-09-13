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
    Document   : welcome.jsp
    Created on : Jan 5, 2010, 12:33:33 AM
    Author     : Daniel Shaw (dshaw.com)
--%>

<%@ include file="/includes/taglibs.jspf" %>

<c:if test="${ empty lnavsub1 }">
    <c:set var="lnavsub1" value="Welcome Letter"/>
    <c:set var="sectioninfo" value="Generate and print password."/>
    <c:set var="submitButtonText" value="Generate Password"/>
</c:if>

<c:set var="pagetitle" value="${ user.fullName } - ${ lnavsub1 }"/>
<c:set var="headingtitle" value="${ lnavsub1 }"/>
<%-- variable(s) bottomnav --%>
<c:set var="isPatient" value="${ user.isPatient }"/>
<c:set var="isLockedOut" value="${ user.isLockedOut }"/>
<c:set var="manage_area" value="${ it.applicationArea }" />
<%@ include file="/includes/components/admin/inc_ctx_base_config.jspf" %>

<head>
    <title>${ pagetitle }</title>

    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/manage.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/manage-additions.css" />
    <style type="text/css">
        h3 { color:#333; }
        #credentials {
                border:1px dotted #CCC;
                /*padding:10px*/
        }
        #p, #u { font-size: 1.4em; }
        #password {
                font-family:monospace;
                font-size: 1.8em;
        }
        #welcomeletter {
                background-color:#FFF;
                border:1px dotted #CCC;
                padding:10px;
        }
        #printCredentials { margin:5px; }
        #wl p { font-size:1.125em; }
    </style>

    <%-- hem.js defines core functionality for health-e-Me --%>
    <script type="text/javascript" src="${ctx_static}/js/hem.js"></script>
    <script type="text/javascript" src="${ctx_static}/js/json2.js"></script>
    <%@ include file="/includes/inc_hem_js.jspf" %>

    <script type="text/javascript">
        var user = {};
        user.id = '${ user.userId }';
        user.userUriSegment = '/users/'+user.id+'/';
        user.passwordUri = HEM.env.apipath + user.userUriSegment + 'password/';
    </script>

    <script type="text/javascript" src="${ctx_static}/js/jquery.password.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#submit').click(function() {
                $('.error').remove();
                var password = $.password(8);

                jsonObject = JSON.stringify({"password": password},null,1);

                $.ajax({
                        type: "PUT",
                        url: user.passwordUri,
                        data: jsonObject,
                        contentType: "application/json",
                        success: function() {
                            var now  = new Date();
                            $('#password').text(password);
                            $('#password-info').text("Generated on " + now.getMonth() + "/" + now.getDate() + " at " + HEM.util.pad(now.getHours()) + ":" + HEM.util.pad(now.getMinutes()));
                        },
                        error: function() {
                            displayError("An error occurred. Please try again.", $('#password'))
                        }
                    });
                return false;
            });

            $('#printCredentials').click(function() {
                $('.error').remove();
                $("link[media=print]").remove();
				$('head').append('<link href="${ctx_static}/css/print-um-1.css" rel="stylesheet" media="print">')
                window.print();
                return false;
            });

            $('#printWelcome').click(function() {
                $('.error').remove();
				$("link[media=print]").remove();
				$('head').append('<link href="${ctx_static}/css/print-um-2.css" rel="stylesheet" media="print">')
                window.print();
                return false;
            });

        });

        function displayError(errorText, selector) {
            if(!selector) {
               var selector = '#userform';
            }
            $(selector).prepend('<div class="error">'+errorText+'</div>');
        }
    </script>
</head>
<body id="manage-users" class="manage">

    <%@ include file="/includes/components/admin/inc_manage_leftnav.jspf" %>

    <div id="user-display-panel">

    	<form id="userform" action="" method="post">
        <input type="hidden" id="by" value="${ pageContext.request.remoteUser }" />

        <h1><span class="headingtitle">${ headingtitle }: </span><span class="user-fullname">${ user.fullName }</span></h1>

        <div class="section-info">
        	<div style="float:right;">
            	<input type="submit" id="submit" value="Step 1 - Click to Generate New Password" class="submit phr-input-button" />
            </div>
            ${ sectioninfo }
            <div class="clear"> </div>
        </div>

        <div id="profile-container" class="section-panel">

            <div id="wl">
                <div id="welcomeletter">

                    <%--
                    <div class="action_print" style="float:right; padding:5px;">
                        <input type="submit" id="printWelcome" value="Step 2A - Print Welcome Letter" class="submit phr-input-button" />
                    </div>
                    <div class="clear"> </div>
                    --%>

                    <div id="c">
                        <div id="credentials">

                            <div class="action_print" style="float:right;">
                                <input type="submit" id="printCredentials" value="Step 2 - Print Credentials" class="submit phr-input-button" />
                            </div>

                            <img alt="HealtheMe" src="/hem/static/images/logo.png">

                            <h1>Welcome to Health<span class="e">e</span>Me!</h1>

                            <p>You will find your <i>temporary</i> credentials below. Please be sure to reset your password upon initial login.</p>

                            <div id="u">Username: <span>${ user.username }</span></div>
                            <div id="p">Password: <span id="password"></span></div>
                            <div id="password-info" style="font-style:italic;"></div>
                            <div class="clear"> </div>

                        </div>
                    </div>
                </div>
            </div>



        </div><!-- /#profile-container,.section-panel -->

        </form>

        <%@ include file="/includes/components/admin/inc_manage_bottomnav.jspf" %>

    </div><!-- /#user-display-panel -->

</body>
