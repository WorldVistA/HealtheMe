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
    Document   : vitals.jsp
    Created on : Aug 26, 2009, 10:31:20 AM
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
    <title>HealtheMe - Health Record - Vitals</title>
    <script type="text/javascript" src="${ctx_static}/js/jquery.DOMWindow.js"></script>
    <script type="text/javascript" src="${ctx_static}/js/jquery.pager.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/paging.css" />
    <script type="text/javascript">
    //set globals
    var ctx_static = "${ctx_static}";
    var pagePath = "${ctx_patient}" + "/${it.healthRecordId}/vitals/um/";
    var patientId = ${it.healthRecordId};
    var max = ${numResults}; //num per page
    var orderBy = "observeddate"; //must match first item in Sort DDL
    var midPath = "/patients/${it.healthRecordId}/vitals/";
    var clinPath = "/clinical/vitals/um/";
    </script>
    <script type="text/javascript" src="${ctx_static}/js/widget_vitals.js"></script>
    
</head>
<body id="vitals">

    <h1>Vital Measurements</h1>

    <div class="def-container">
    	<div class="def-title">Definition</div>
        The basic measurements of your overall well being; these are the most essential indicators of general health.
    </div>
    
    <%@ include file="/includes/components/phr/healthrecord/inc_tip_hrec_clinical.jspf" %>

    <%@ include file="/includes/components/phr/notifiers/inc_notifier.jspf" %>
    
    <div id="ajax-container">
        <div id="widget-container">
            <div id="bloodpressure-widget" class="widget">
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
                                <option value="observeddate" class="clinicalSort desc">Date Observed: recent first</option>
                                <option value="dateadded" class="clinicalSort desc">Date Added: recent first</option>
                                <option value="description" class="clinicalSort asc">Vital: ascending</option>
                                <option value="description" class="clinicalSort desc">Vital: descending</option>
                            </select>
                        </form>
                    </div>
                    <div class="clear"> </div>
                </div>

                <div id="tabs-content">
                    <div id="emptyresults" class="ui-helper-hidden">No records found.</div>

                    <table id="vtls" class="grs1">
                        <tbody>
                            <tr class="titletemplate clinical ui-helper-hidden">
                                <th class="clinical col1">
                                	<div class="d">&nbsp;</div>
                                </th>
                                <th class="clinical col2">
                                	<div class="d">Date Observed</div>
                                </th>
                                <th class="clinical col3">
                                	<div class="d">Vital Name</div>
                                </th>
                                <th class="clinical col4">
                                	<div class="d">Result</div>
                                </th>
                            </tr>
                            <tr class="rowtemplate clinical ui-helper-hidden">
                                <td class="col1">
                                    <span title="Show Details" class="toggle action closed"></span>
                                </td>
                                <td class="col2">
                                    <div class="d">
                                    	<span class="exactDateTime"></span>
                                    </div>
                                </td>
                                <td class="col3">
                                	<div class="d">
                                    	<span class="testDescriptionText"></span>
                                    </div>
                                </td>
                                <td class="col4">
                                	<div class="d">
                                    	<span class="testResultValue"></span>&nbsp;<span class="testResultUnit"></span>
                                    </div>
                                </td>
                            </tr>
                            <tr class="detailtemplate clinical ui-helper-hidden">
                                <td colspan="4" class="testContainerRow">
                                    <div class="detail-content">
                                    	<div class="detail-content-container">
                                            <ul class="details-2col">
                                                <li class="dateAdded d">
                                                    <h3>Date Added</h3>
                                                    <span class="dateAdded"></span>
                                                </li>
                                                <li class="source d">
                                                    <h3>Source</h3>
                                                    <span class="source"></span>
                                                </li>
                                            </ul>
                                            <div class="clear"> </div>
                                    	</div>
                                    </div>
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
            </div><!--/widget-->
        </div><!--/widget container-->
        
        <div class="clear"></div>
    </div> <!--ajax container-->
    <div id="progress">
    	<span class="ajaxload">
        	<img src="${ctx_static}/css/img/ajax-loader.gif" class="ajaxload"/>
        </span>
   	</div>
</body>