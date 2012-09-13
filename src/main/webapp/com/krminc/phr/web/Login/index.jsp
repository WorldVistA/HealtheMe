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
    Created on : Aug 30, 2009, 5:12:15 PM
    Author     : Daniel Shaw (dshaw.com)
--%>

<%@ include file="/includes/taglibs.jspf" %>

<head>
    <title>HealtheMe - Login</title>
    <style type="text/css">
        #auth { width: 280px; }
    </style>
</head>
<body id="login">
    <div id="login-container">
        <c:if test="${loginerror}">
            <div class="container-gen center">
                <p class="error"><span class="i excl"></span>Invalid username or password</p>
                <p class="error">Login failed. Please try again or contact support for assistance.</p>
            </div>
        </c:if>
        <%@ include file="/includes/components/inc_login.jspf" %>
    </div>
</body>