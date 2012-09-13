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
    Document   : vitals-bs.jsp
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
    <title>HealtheMe - Health Record - Vitals - Fingerstick Blood Glucose</title>
    <script type="text/javascript" src="${ctx_static}/js/jquery.DOMWindow.js"></script>
    <script language="javascript" src="${ctx_static}/js/jquery.pager.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/paging.css" />
    <script language="javascript">
    //set globals
    var ctx_static = "${ctx_static}";
    var pagePath = "${ctx_patient}" + "/${it.healthRecordId}/vitals/bloodsugar/";
    var patientId = ${it.healthRecordId};
    var max = ${numResults}; //num per page
    var orderBy = "observeddate"; //must match first item in Sort DDL
    var midPath = "/patients/${it.healthRecordId}/vitals/bloodsugar/";
    var clinPath = "/clinical/vitals/bs/";
    </script>
    <script language="javascript" src="${ctx_static}/js/widget2.js"></script>

</head>
<body id="vitals-bs" class="vitals">

    <h1>Vital Measurements</h1>
    
    <h2>FINGERSTICK BLOOD GLUCOSE</h2>
    
    <div class="def-container">
    	<div class="def-title">Definition</div>
        The measure of the glucose in your blood. Excessively high or low levels, or variations therein may show many conditions, the most notable being Diabetes.
    </div>
    
    <%@ include file="/includes/components/phr/healthrecord/inc_tip_hrec_self.jspf" %>

    <%@ include file="/includes/components/phr/notifiers/inc_notifier.jspf" %>

    <div id="ajax-container">
        <div id="widget-container">
            <div id="bloodsugar-widget" class="widget">
                <div class="expander">
                    <form id="expand" action="#" method="post">
                       <input type="checkbox" id="chkexpand" /><label for="chkexpand">Show All Details</label>
                    </form>
                </div>
                <ul id="tabs">
                    <li><a id="self" href="#" class="fakelink">Self Entered Records</a></li>
                </ul>
                <div id="controls">
               		<div class="add">
                    	<a class="addlink" href="${ctx_patient}/${it.healthRecordId}/bloodsugar-add" title="Add Record">Add Record</a>
                    </div>
                  	<div class="sorter">
                        <form id="sorting" action="#" method="post">
                            <label for="sort">Sort by</label>
                            <select name="sort" id="sort">
                                <option value="value" class="selfSort clinicalSort asc">Blood Glucose: ascending</option>
                                <option value="value" class="selfSort clinicalSort desc">Blood Glucose: descending</option>
                                <option value="addeddate" class="selfSort clinicalSort desc">Date Added: recent first</option>
                                <option value="addeddate" class="selfSort clinicalSort asc">Date Added: oldest first</option>
                                <option value="observeddate" selected="selected" class="selfSort clinicalSort desc">Date Observed: recent first</option>
                                <option value="observeddate" class="selfSort clinicalSort asc">Date Observed: oldest first</option>
                            </select>
                        </form>
                    </div>
                    <div class="clear"> </div>
                </div>

                <div id="tabs-content">
                    <div id="emptyresults" class="ui-helper-hidden">No records found.</div>

                    <table id="vtls-bs" class="grs1">
                        <tbody>
                            <tr class="titletemplate selfentered ui-helper-hidden">
                                <th class="selfentered col1">
                                	<div class="d">&nbsp;</div>
                                </th>
                                <th class="selfentered col2">
                                	<div class="d">Date Observed</div>
                                </th>
                                <th class="selfentered col3">
                                	<div class="d">Blood Glucose</div>
                                </th>
                                <th class="selfentered col4">
                                	<div class="d">&nbsp;</div>
                                </th>
                                <th class="selfentered col5">
                                	<div class="d">&nbsp;</div>
                                </th>
                            </tr>
                            <tr class="rowtemplate selfentered ui-helper-hidden">
                                <td class="col1">
                                    <span title="Show Details" class="toggle action closed"></span>
                                </td>
                                <td class="col2">
                                	<div class="d">
                                    	<span class="observedDate"></span>
                                	</div>
                                </td>
                                <td class="col3">
                                	<div class="d b">
                                    	<span class="bloodSugarLevel"></span>&nbsp;<span class="unit" ></span>
                                    </div>
                                </td>
                                <td class="col4">
                                    <div class="d">&nbsp;</div>
                                </td>
                                <td class="col5">
                                    <div class="d">
                                        <ul class="actionButtons">
                                            <li class="edit">
                                                <a href="#" class="changelink" title="Edit Blood Glucose Reading"></a>
                                            </li>
                                            <li class="delete">
                                                <a href="#" title="Delete Blood Glucose Reading" class="deletelink"></a>
                                            </li>
                                        </ul>
                                   </div>
                                </td>
                            </tr>
                            <tr class="detailtemplate selfentered ui-helper-hidden">
                                <td colspan="5" class="testContainerRow">
                                    <div class="detail-content">
                                    	<div class="detail-content-container">
                                            <ul class="details-2col">
                                                <li class="method d">
                                                    <h3>Reading Method</h3>
                                                    <span class="method"></span>
                                                </li>
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
    <div id="progress" style="display:none"><span class="ajaxload"><img src="${ctx_static}/css/img/ajax-loader.gif" class="ajaxload"/></span></div>
</body>