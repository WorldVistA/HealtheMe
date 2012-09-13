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
    Document   : dashboard
    Created on : Nov 3, 2009, 12:57:01 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>

<head>
    <meta name="requiresJQUI" content="true">
    <title>HealtheMe - Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/dashboard.css" />
    <script language="javascript">
        var patientId = ${it.healthRecordId};
    </script>
    <script language="javascript" src="${ctx_static}/js/dashboard.js"></script>  
</head>
<body id="dashboard">		
    <h1>Health Dashboard</h1>
    <div id="accessrequests"></div>
    <div id="common-tasks">
        <h2>Interact with your record</h2>
        <p>Navigate using the menu at left or choose a common task from the list below.</p>
        <ul>
            <li><a href="medications">Manage Medications</a></li>
            <li><a href="immunizations">Update Immunizations</a></li>
            <li><a href="visits">Track Appointments &amp; Visits</a></li>
        </ul>
    </div>
    <h2>Health Record Statistics</h2>
    <div id="progress"><span class="ajaxload"><img src="${ctx_static}/css/img/ajax-loader.gif" class="ajaxload"/></span></div>
    <div id="health-record-stats" class="clear" style="display:none;">
        <div class="clinical-stat">
            <div class="header-box">Clinical Records</div>
            <ul id="clinical-stats"></ul>
            <p>Vitals Measurements</p>
            <ul id="clinical-stats-vitals"></ul>
        </div>
        <div class="self-entered-stat">
            <div class="header-box">Self Entered Records</div>
            <ul id="self-entered-stats"></ul>
            <p>Vitals Measurements</p>
            <ul id="self-entered-stats-vitals"></ul>
        </div>
    </div>
    <div id="progress" style="display:none"><span class="ajaxload"><img src="${ctx_static}/css/img/ajax-loader.gif" class="ajaxload"/></span></div>
</body>