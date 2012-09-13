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
    Document   : providers
    Created on : Oct 30, 2009, 1:40:34 PM
    Author     : cmccall
--%>


<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>

<head>
    <meta name="requiresJQUI" content="true">
    <title>Healthcare Providers</title>
<%--    <script type="text/javascript" src="${ctx_static}/js/jquery.DOMWindow.js"></script>
    <script language="javascript" src="${ctx_static}/js/jquery.pager.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/paging.css" />
    <%--<script language="javascript" src="${ctx_static}/js/widget.js"></script>--%>
</head>
<body id="providers">
    <div id="sec-image"></div>

    <h3>Healthcare Providers</h3>

   <%-- <span class="inline-subtext">(Self entered &amp; trusted clinical data from <b>linked</b> health partners)</span>--%>

    <div class="intro-definition">
        <b>Healthcare Providers</b> - This section contains a list of your healthcare providers and information associated with them. Use this section to view and add doctors and other medical professionals whom you use for receiving care.
    </div>

    <div id="notifier">
        <span class="notificationtext"></span>
        <span class="closebutton"><a href="">X</a></span>
    </div>

 <div id="widget-container">
        <div id="-widget" class="widget">
            <div id="tabs-content">
                <!-- data elements begin -->
                The Healthcare Providers section is currently under development!
            </div><!-- /tab content-->
        </div><!--/widget-->
    </div><!--/widget container-->
    <div class="clear"></div>
    <%--<div id="progress" style="display:none"><span class="ajaxload"><img src="${ctx_static}/css/img/ajax-loader.gif" class="ajaxload"/></span></div>--%>
</body>