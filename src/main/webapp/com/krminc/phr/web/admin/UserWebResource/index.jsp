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
    Document   : index.jsp
    Created on : Feb 24, 2010, 10:44:00 PM
    Author     : Daniel Shaw (dshaw.com)
--%>
<%@ include file="/includes/taglibs.jspf" %>

<c:set var="manage_area" value="${ it.applicationArea }" />
<%@ include file="/includes/components/admin/inc_ctx_base_config.jspf" %>

<c:set var="lnavsub1" value="User Detail"/>
<c:set var="pagetitle" value="${ it.user.fullName } - ${ lnavsub1 }"/>
<c:set var="headingtitle" value="${ lnavsub1 }"/>
<%-- variable(s) bottomnav --%>
<c:set var="isPatient" value="${ it.user.isPatient }"/>
<c:set var="isLockedOut" value="${ it.user.isLockedOut }"/>

<head>
    <title>${ pagetitle }</title>

    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/manage.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/manage-additions.css" />
    <%--<link rel="stylesheet" type="text/css" href="${ctx_static}/css/paging.css" />--%>
    <style type="text/css">
        .left-column { float: left; margin: 0 40px 20px 0 }
        .right-column { float: left }
    </style>

    <%-- hem.js defines core functionality for health-e-Me --%>
    <script type="text/javascript" src="${ctx_static}/js/hem.js"></script>
    <%@ include file="/includes/inc_hem_js.jspf" %>

    <script type="text/javascript">
        $(document).ready(function() {
            HEM.user.image('${ it.user.primaryHealthRecord.fullGender }');
        });
    </script>
</head>
<body id="manage-users" class="manage">

    <%@ include file="/includes/components/admin/inc_manage_leftnav.jspf" %>

    <div id="user-display-panel">

        <h1>${ headingtitle }: <span class="user-fullname">${ it.user.fullName }</span></h1>

        <div id="profile-container" class="section-panel">

            <div id="user-photo">
                <div id="profile-photo"></div>
                <p class="user-photo-text">${ it.user.firstName }</p>
            </div>

            <div id="demographics">
                <div id="user-demographic-details">

                    <div class="left-column">
                        <h2 class="profile-section-heading">Demographics</h2>

                        <div><b>Full Name:</b> <span class="user-fullname">${ it.user.firstName } ${ it.user.middleName } ${ it.user.lastName }</span></div>
                        <div><b>Address 1:</b> <span class="user-address1">${ it.user.primaryAddress.address1 }</span></div>
                        <div><b>Address 2:</b> <span class="user-address2">${ it.user.primaryAddress.address2 }</span></div>
                        <div><b>Address 3:</b> <span class="user-address2">${ it.user.primaryAddress.address3 }</span></div>
                        <span class="city"><b>City:</b>  <span class="user-city">${ it.user.primaryAddress.city }</span></span>
                        <span class="state"><b>State:</b> <span class="user-state">${ it.user.primaryAddress.state }</span></span><br />
                        <span class="zip"><b>Zip:</b> <span class="user-address-zip">${ it.user.primaryAddress.zip }</span></span>
                        <br /><br />
                        <div class="gender"><b>Gender:</b> <span class="user-gender">${ it.user.primaryHealthRecord.fullGender }</span></div>
                        <div class="dob"><b>Date of Birth:</b> ${ it.user.primaryHealthRecord.dateOfBirthString }</div>

                    </div><!-- /.left-column -->

                    <div class="right-column">
                        <h2 class="profile-section-heading">Contact Information</h2>

                        <div style="float:left;"><b>Phone Numbers:</b></div>
                        <div style="float:left; margin-left:3px;">
                            <span class="user-phone-home">${ not empty it.user.telnumHome ? it.user.telnumHome : "N/A" }</span> (Home)<br />
                            <span class="user-phone-work">${ not empty it.user.telnumWork ? it.user.telnumWork : "N/A" }</span> (Work)<br />
                            <span class="user-phone-mobile">${ not empty it.user.telnumMobile ? it.user.telnumMobile : "N/A" }</span> (Mobile)<br />
                            <span class="user-fax">${ not empty it.user.faxnum ? it.user.faxnum : "N/A" }</span> (Fax)<br />
                        </div>

                        <div class="clear"></div>
                        <br />

                        <b>Email Address:</b> <span id="user-email"><a href="mailto:${ it.user.email }">${ it.user.email }</a></span><br />

                    </div><!-- /.right-column -->

                    <div class="clear"></div>

                    <div class="user-profile-section">
                        <h2 class="profile-section-heading">Account Details</h2>
                        <b>Username:</b> <span id="user-username">${ it.user.username }</span><br />
                        <b>Enabled:</b> <span id="user-active">${ it.user.active }</span><c:if test="${ it.user.active == false }">&nbsp;[ <a href="./disable"  title="enable">enable account</a> ]</c:if><br />
                        <b>Locked:</b> <span id="user-locked">${ it.user.isLockedOut }</span><c:if test="${ it.user.isLockedOut == true }">&nbsp;[ <a href="./unlock"  title="unlock">unlock account</a> ]</c:if><br />
                        <b>Login Count:</b> <span id="user-logincount">${ it.user.totalLogin }</span><br />
                        <c:if test="${not empty it.user.lastLogin}"><b>Last Login:</b> <span id="user-lastLogin"><fmt:formatDate pattern="MM/dd/yyyy hh:mm aa" value="${ it.user.lastLogin }"/></span><br /></c:if>
                        <br />
                        <b>Roles:</b>
                        <c:set var="userRoles" value="${ it.user.roles }"/>
                        <%@ include file="/includes/components/admin/inc_userroles_list.jspf" %>
                        <br />

                    </div><!-- /.user-profile-section -->
                </div><!-- /#user-demographic-details -->
            </div><!-- /#demographics -->

            <div class="clear"></div>
        </div><!-- /#profile-container -->

        <%@ include file="/includes/components/admin/inc_manage_bottomnav.jspf" %>

    </div><!-- /#user-display-panel -->

</body>
