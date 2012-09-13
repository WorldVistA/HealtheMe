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
    Document   : help.jsp
    Created on : Feb 22, 2009, 10:35:00 PM
    Author     : Jamie Doyle
--%>

<%@ include file="/includes/taglibs.jspf" %>
<c:set var="pagetitle" value="Help" />
<c:set var="manage_area" value="admin" />

<%-- TODO: Remove displaytag decorator when displaytag has been removed. --%>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>
<%@ include file="/includes/components/admin/inc_ctx_base_config.jspf" %>

<head>
    <title>HealtheMe - ${ pagetitle }</title>

    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/manage.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/manage-additions.css" />
    
</head>
<body id="manage-help" class="manage">

    <%@ include file="/includes/components/admin/inc_manage_leftnav.jspf" %>

    <div id="user-display-panel">

        <h1>${ pagetitle }</h1>
        
        <p>Please visit <a href="http://wiki.osehra.org/display/HealtheMe" title="HealtheMe Documentation" target="new">http://wiki.osehra.org/display/HealtheMe</a> for the latest online documentation.</p>
        
    </div>
</body>
