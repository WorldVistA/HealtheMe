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
    Document   : default
    Created on : May 17, 2009, 2:41:25 PM
    Author     : Daniel Shaw (dshaw.com)
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/components/inc_firstlogin.jspf" %>

<head>
    <title>HealtheMe - Home</title>
    <link rel="stylesheet" href="${ctx_static}/css/landing.css" type="text/css" media="screen" />    
    <link rel="stylesheet" href="${ctx_static}/3rdParty/nivo-slider/nivo-slider.css" type="text/css" media="screen" />                
    <link rel="stylesheet" href="${ctx_static}/3rdParty/nivo-slider/themes/light/light.css" type="text/css" media="screen" />  
    <%@ include file="/includes/components/inc_redirector.jspf" %>
</head>
<body>
    <% if (request.isUserInRole("ROLE_PATIENT")) {%>
        <h1>Welcome ${ pageContext.request.remoteUser }!</h1>
        <h2>Go to your <a href="${ctx_patient}/${ it.healthRecordId }/dashboard">Dashboard</a> to get started.</h2>
    <% } else if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_PATIENTADMIN")) {%>
        <h1>Welcome ${ pageContext.request.remoteUser }!</h1>
        <h2>Go to the <a href="${ctx_patientadmin}/">Admin Page</a> to get started.</h2>
    <% } else if (request.isUserInRole("ROLE_CARETAKER")) {%>
        <h1>Welcome ${ pageContext.request.remoteUser }!</h1>
        <h2>Go to the <a href="${ctx_caretaker}/">Care Taker Page</a> to get started.</h2>
    <% } else {%>
        <%@ include file="/WEB-INF/jspf/custom/landing/maincontent.jspf" %>
    <% } %>
</body>