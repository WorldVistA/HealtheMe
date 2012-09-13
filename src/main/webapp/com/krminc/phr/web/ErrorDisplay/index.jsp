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
    Document   : index
    Created on : Apr 14, 2010, 2:18:47 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>

<c:choose>
    <c:when test="${ param['404'] != null}">
        <c:set scope="page" var="message" value="Unable to find the page you requested. Please check the URL and try again."></c:set>
    </c:when>
    <c:when test="${ param['500'] != null}">
        <c:set scope="page" var="message" value="An unexpected error has occured. Please contact support if the problem persists."></c:set>
    </c:when>
    <c:when test="${ param['403'] != null}">
        <c:set scope="page" var="message" value="Access denied. You are not authorized to view the requested resource or you need to login and try again."></c:set>
    </c:when>
    <c:otherwise>
        <c:set scope="page" var="message" value="Please try again or contact support if the problem persists."></c:set>
    </c:otherwise>
</c:choose>

<head>
    <title>HealtheMe - Error</title>
</head>
<body id="errorpage">

    <h1>An error occurred while processing your request.</h1>

    <div class="def-container" style="width:100%">
        ${ message }
    </div>

</body>