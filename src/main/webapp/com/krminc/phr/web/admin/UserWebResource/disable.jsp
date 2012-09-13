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
    Document   : disable.jsp
    Created on : Mar 14, 2010, 2:31:33 PM
    Author     : Daniel Shaw (dshaw.com)
--%>
<%@ include file="/includes/taglibs.jspf" %>

<c:set var="manage_area" value="${ it.applicationArea }" />
<c:set var="user" value="${ it.user }" />
<%@ include file="/includes/components/admin/inc_user_disable.jspf" %>
