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
    Document   : staticerror
    Created on : Jun 1, 2010, 4:19:55 PM
    Author     : cmccall
--%>

<%--DO NOT directly use this page as an error page. Include it from another error JSP that contains the appropriate taglibs--%>

<c:choose>
    <c:when test="${ message == null}">
        <c:set scope="page" var="message" value="Please try again or contact support if the problem persists."></c:set>
    </c:when>
</c:choose>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title>HealtheMe - Error</title>
        <%@ include file="/includes/inc_css.jsp" %>
        <%@ include file="/includes/inc_js.jsp" %>.
    </head>
    <body  id="locked" class="" bgcolor="#ffffff" text="#000000" link="#0000cc" vlink="#551a8b" alink="#ff0000">
    <div id="outer-wrapper">
        <div id="container">
            <div id="header2">
                <div id="header-inset" style="position:relative;">
                    <!-- use a "real image" instead of a background to print properly -->
                    <a id="header-logo" href="${ctx}/default"><img src="${ctx_static}/images/logo.png" alt="HealtheMe"></a>
                    <ul id="tabnav">
                        <li id="tabnav-home" class="firsttab"><a href="${ctx}/" title="Home">Home</a></li>
                        <li id="tabnav-about"><a href="${ctx}/about" title="About HealtheMe">About HeM</a></li>
                        <li id="tabnav-faq"><a href="${ctx}/faq" title="Frequently Asked Questions">FAQs</a></li>
                        <li id="tabnav-contact"><a href="${ctx}/contact" title="Contact HealtheMe">Contact HeM</a></li>
                        <li id="tabnav-help"><a href="${ctx}/help" title="Get help with HealtheMe">Help</a></li>
                        <li id="tabnav-search">
                            <form action="#" method="post">
                                <input type="text" name="search" value="Search" disabled />
                                <input type="image" src="${ctx_static}/images/search-button.gif" value="Go" disabled />
                            </form>
                        </li>
                    </ul>
                </div>
            </div><!-- /#header2 -->
            <div id="content">
                <div class="clear"></div><!-- needed by IE -->
                    <div id="infolayer">
                        <div id="breadcrumb"></div>

                        <div id="infoactions">
                            &nbsp;
                        </div><!-- /#infoactions -->
                        <div class="clear"></div>
                    </div><!-- /#infolayer -->
                    <div id="main">
                    <div id="main-display-panel">
                        <h1>An error occurred while processing your request.</h1>
                        <p>${message}</p>
                    </div><!-- /#main-display-panel -->
                    </div><!-- /#main -->
                    <div class="clear"> </div>
                </div><!-- /#content -->
                <div id="footer-bottom" />
            </div><!-- /#container -->
            <%@ include file="/WEB-INF/jspf/layout/footer/footer.jspf" %>
        </div><!-- /#outer-wrapper -->
    </body>
</html>