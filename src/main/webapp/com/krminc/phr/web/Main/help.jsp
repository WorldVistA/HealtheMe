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
    Document   : help
    Created on : May 27, 2010, 11:17:19 AM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>

<head>
    <title>HealtheMe - Help</title>
    <style type="text/css">
        #help h2 {
            border-bottom: 1px dotted #A6A6A6;
            font-size:1.3em;
            padding: 4px 0;
        }
        #help p { padding:10px 6px 4px; }
    </style>
</head>
<body id="help">
    
    <h1>Health<span class="e">e</span>Me Help</h1>

    <h2>Documentation</h2>
    
    <% if (request.isUserInRole("ROLE_PATIENT")) {%>
        <ul class="docs">
            <li><a href="${ctx}/assets/users/docs/UserGuide.pdf" title="HealtheMe User Guide Download" target="new">HealtheMe User Guide</a></li>
        </ul>
    <% }%>
    
    <p>Please visit <a href="http://wiki.osehra.org/display/HealtheMe" title="HealtheMe Documentation" target="new">http://wiki.osehra.org/display/HealtheMe</a> for the latest online version
</body>
