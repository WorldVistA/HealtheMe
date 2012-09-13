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
    Document   : reset.jsp
    Created on : Nov 9, 2009, 2:04:36 AM
    Author     : Daniel Shaw (dshaw.com)
--%>

<%@ include file="/includes/taglibs.jspf" %>

<c:set var="user" value="${ it.user }"/>
<c:set var="lnavsub1" value="Manage Credentials"/>
<c:set var="sectioninfo" value="Reset password for user."/>
<c:set var="submitButtonText" value="Generate New Password"/>
<c:set var="isReset" value="true"/>

<%@ include file="welcome.jsp" %>
