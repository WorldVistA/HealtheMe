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
    Document   : results
    Created on : Jun 8, 2009, 4:11:39 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>
<c:choose>
    <c:when test="${empty param.results}">
        <c:set scope="page" var="numResults" value="10"></c:set>
    </c:when>
    <c:otherwise>
        <c:catch var="e">
            <fmt:formatNumber minIntegerDigits="1" maxIntegerDigits="2" type="number" value="${param.results}" var="numResults" scope="page"></fmt:formatNumber>
        </c:catch>
        <c:if test="${e != null}">
            <c:set scope="page" var="numResults" value="10"></c:set>
        </c:if>
        <c:if test="${numResults < 1}">
            <c:set scope="page" var="numResults" value="10"></c:set>
        </c:if>
    </c:otherwise>
</c:choose>

<head>
    <meta name="requiresJQUI" content="true">
    <title>HealtheMe - Health Record - Lab &amp; Test Results</title>
    <script type="text/javascript" src="${ctx_static}/js/jquery.DOMWindow.js"></script>
    <script type="text/javascript" src="${ctx_static}/js/jquery.pager.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/paging.css" />
    
    <script type="text/javascript">
    //set globals
    var ctx_static = "${ctx_static}";
    var patientId = ${it.healthRecordId};
    var max = ${numResults}; //num per page
    var orderBy = "ordereddate"; //must match first item in Sort DDL
    var midPath = "/patients/${it.healthRecordId}/clinical/results/";
    var subPath = "/resultTests/"
    </script>
    <script type="text/javascript" src="${ctx_static}/js/widget_results.js"></script>
</head>
<body id="labsandtests">

    <h1>Lab &amp; Test Results</h1>
    
    <div class="def-container">
    	<div class="def-title">Definition</div>
        The outcome of any number of measurable medical evaluations performed.  Some examples include blood tests, vision tests, and biopsies.
    </div>
    
    <%@ include file="/includes/components/phr/healthrecord/inc_tip_hrec_clinical.jspf" %>

    <div id="notifier">
        <span class="notificationtext"></span>
        <span class="closebutton">
        	<a href="#">X</a>
        </span>
    </div>
    
        <div id="ajax-container">

        <div id="widget-container">
            <div id="results-widget" class="widget">
               <div class="expander">
                    <form id="expand" action="#" method="post">
                       <input type="checkbox" id="chkexpand" /><label for="chkexpand">Show All Details</label>
                    </form>
                </div>
                <ul id="tabs">
                    <li><a id="clinical" href="#" class="fakelink">Clinical Records</a></li>
                </ul>
                <div id="controls">
                  	<div class="sorter">
                        <form id="sorting" action="#" method="post">
                            <label for="sort">Sort by</label>
                            <select name="sort" id="sort">
                                <option value="ordereddate" selected="selected">Date Ordered: recent first</option>
                                <option value="name">Description: A-Z</option>
                                <option value="dateadded">Date Added: recent first</option>
                            </select>
                        </form>
                    </div>
                    <div class="clear"> </div>
                </div>
                <div id="tabs-content">
                    <div id="emptyresults" class="hiddenwidget">No records found.</div>

                    <table id="labs" class="grs1">
                        <tbody>
                            <tr>
                                <th class="col1">
                                	<div class="d">&nbsp;</div>
                                </th>
                                <th class="col2">
                               		<div class="d">Description</div>
                                </th>
                                <th class="col3">
                                	<div class="d">Date Ordered</div>
                                </th>
                                <th class="col4">
                                	<div class="d">Date Added</div>
                                </th>
                            </tr>
                            <tr class="rowtemplate ui-helper-hidden">
                                <td class="col1">
                                    <span title="Show Details" class="toggle action closed"></span>
                                </td>
                                <td class="col2">
                                    <div class="d">
                                    	<span class="descriptionText"></span>
                                   	</div>
                                </td>
                                <td class="col3">
                                    <div class="d">
                                    	<span class="dateOrdered"></span>
                                    </div>
                                </td>
                                <td class="col4">
                                    <div class="d">
                                    	<span class="dateAdded"></span>
                                   	</div>
                                </td>
                            </tr>
                            <tr class="detailtemplate ui-helper-hidden">
                                <td colspan="4" class="testContainerRow">
                                    <div class="loader">
                                        <span class="loading">Loading...</span>
                                    </div>
                                    <table id="tests" class="testTable grs2">
                                        <tbody>
                                            <tr class="testHeading ui-helper-hidden">
                                                <th class="col1">
                                                	<div class="d">
                                                    	Test Performed
                                                    </div>
                                                </th>
                                                <th class="col2">
                                                	<div class="d">Result</div>
                                                </th>
                                                <th class="col3">
                                                	<div class="d">Reference Range</div>
                                                </th>
                                                <th class="col4">
                                                	<div class="d">Lab Interpretation</div>
                                                </th>
                                            </tr>
                                            <tr class="testtemplate ui-helper-hidden">
                                                <td class="col1">
                                                    <div class="d">
                                                        <span class="testDescriptionText"></span>
                                                        <span class="dateAdded"></span>
                                                    </div>
                                                </td>
                                                <td class="col2">
                                                    <div class="d">
                                                        <span class="testResultValue"></span>
                                                        <span class="testResultUnit"></span>
                                                    </div>
                                                </td>
                                                <td class="col3">
                                                	<div class="d">
                                                    	<span class="normalResultDescription">N/A</span>
                                                    </div>
                                                </td>
                                                <td class="col4">
                                                    <div class="d">
                                                    	<span class="flagText">-</span>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr class="ordertemplate ui-helper-hidden">
                                                <td class="coln" colspan="4">
                                                    <div class="d">
                                                        <span class="orderedByHeading lbl">Ordered By</span>
                                                        <span class="orderedBy"></span>
                                                    	<span class="orderedByTitle"></span>
                                                    </div>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                            	</td>
                            </tr>
                        </tbody>
                    </table>
                    <div id="paging">
                        <div id="pager"></div>
                        <div id="full-results">
                            Records <span id="results-min">0</span> to <span id="results-max">0</span> of <span id="results-total">0</span>
                        </div>
                  	</div>
                </div><!-- /tab content-->
            </div><!-- /widget-->
        </div><!-- /widget container-->
        
        <div class="clear"></div>
    </div><!-- /ajax container-->
    <div id="progress">
    	<span class="ajaxload">
        	<img src="${ctx_static}/css/img/ajax-loader.gif" class="ajaxload"/>
        </span>
   	</div>
</body>