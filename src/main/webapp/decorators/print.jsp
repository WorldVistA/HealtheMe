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
    Document   : print
    Created on : Jun 16, 2009, 3:47:47 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <%@ include file="/includes/inc_content-type.jsp" %>
        <title><decorator:title default="" />${ projectname }</title>

        <%@ include file="/includes/inc_css.jsp" %>
        <!-- use specialized set of js includes for printing -->
        <%@ include file="/includes/inc_js_print.jsp" %>
        <decorator:head />
        <!-- use specialized stylesheet for printing  -->
        <link rel="stylesheet" type="text/css" href="${ctx_static}/css/print.css" media="screen, print"/>

        <!-- remove link functionality on printable pages -->
        <script type="text/javascript">
            $().ready(function() {
                $("a").each(function(){
                    $(this).attr('href', '#');
                    $(this).unbind('click');
                    $(this).click(function(){
                        return false;
                    });
                });
            });
        </script>
    </head>
    <body  id="<decorator:getProperty property="body.id"/>" class="<decorator:getProperty property="body.class"/>" bgcolor="#ffffff" text="#000000" link="#000000" vlink="#000000" alink="#000000" onLoad="printFunction()">
                <div id="content">
                    <div class="clear"></div><!-- needed by IE -->
                            <decorator:body />
                    <div class="clear"> </div>
                </div><!-- /#content -->
    </body>
</html>
