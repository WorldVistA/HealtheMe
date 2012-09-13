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
    Document   : iframe
    Created on : Sep 23, 2009, 9:58:21 AM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jsp" %>

<decorator:usePage id="thePage" />

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/inc_content-type.jsp" %>
        <title><decorator:title default="" />${ projectname }</title>
        <%-- TODO: set projectname from Maven --%>

        <%@ include file="/includes/inc_css.jsp" %>
        <%@ include file="/includes/inc_js.jsp" %>
        <%@ include file="/includes/inc_validation.jspf" %>
        <%@ include file="/includes/inc_jquery-ui.jspf" %>

        <decorator:head />
    </head>
    <body  id="<decorator:getProperty property="body.id"/>" class="<decorator:getProperty property="body.class"/>" bgcolor="#ffffff" text="#000000" link="#0000cc" vlink="#551a8b" alink="#ff0000">
        <decorator:body />
    </body>
</html>