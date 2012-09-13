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
    Created on : ?
    Author     : Daniel Shaw (dshaw.com)
--%>

<%@ include file="/includes/taglibs.jspf" %>
<c:set var="pagetitle" value="Health Summary" />

<title>${ pagetitle }</title>

<body id="healthsummary">

<div id="sec-image"></div>

<h3>${ pagetitle }</h3>

<span class="inline-subtext">(Self entered &amp; trusted clinical data from your provider)</span>

<div>&nbsp;</div>

<div class="intro-definition">
    Health<span class="e">e</span>Me has provided nine ways you can monitor your health measures. Each one shows a graph of your progress. Enter your blood sugar readings. Record your heart rate and pain levels, and much more. When you're done you can print them out and take them to your doctor. Start tracking your health statistics online with Health<span class="e">e</span>Me today!
</div>

<table id="phr-hr-container" cellpadding="1" cellspacing="0">
    <tr>
        <td class="phr-hr-container-td">

            <div id="m-problemlist" class="covermodule">
                <div class="m-header"><a href="${ctx_patient}/${ it.healthRecordId }/problems">Problem List (<span id="problemlist-count">${fn:length(it.problems)}</span>)</a></div>
                <table class="datatable" cellpadding="4" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Problem</th>
                            <th>Start Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% int pcount=0; %>
                        <c:forEach var="p" items="${it.problems}" begin="0" end="4" varStatus="loop">
                            <tr>
                                <td><a href="${ctx_patient}/${ it.healthRecordId }/problems/${p.problemId}">${p.description}</a></td>
                                <td>${ p.startDate }</td>
                            </tr>
                            <% pcount++; %>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr class="tablefooter">
                            <td colspan="4"><div class="pageinfo">Displaying Items 1 to <%= pcount %>${pcount} of ${fn:length( it.problems )}</div></td>
                        </tr>
                    </tfoot>
                </table><!-- /.datatable -->
                <div class="phr-input-button-align-right">
                    <a class="phr-input-button" href="${ctx_patient}/${ it.healthRecordId }/problems">View More</a>
                    <a class="phr-input-button" href="${ctx_patient}/${ it.healthRecordId }/problems-add">Add Record</a>
                </div>
            </div><!-- /#m-problemlist -->

        </td>
        <td class="phr-hr-container-td">

            <%--<div id="m-allergies" class="covermodule">
                <div class="m-header"><a href="${ctx_patient}/${ it.healthRecordId }/allergies">Allergies &amp; Reactions (<span id="allergies-count">${fn:length( it.allergies )}</span>)</a></div>
                <table class="datatable" cellpadding="4" cellspacing="0">
                    <thead>
                        <tr>
                            <!--<th class="cdd" style="width:24px;">&nbsp;</th>-->
                            <th>Allergy</th>
                            <th>Date Observed</th>
                            <th>Severity</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% int acount=0; %>
                        <c:forEach var="i" items="${ it.allergies }" begin="0" end="4" varStatus="loop">
                            <tr>
                                <td><a href="${ctx_patient}/${ it.healthRecordId }/allergies/${ i.allergyId }">${ i.allergyText }</a></td>
                                <td>${ i.observedDate }</td>
                                <td class="<c:out value="${fn:toLowerCase( i.severity )}" />"><div class="<c:out value="${fn:toLowerCase( i.severity )}" />t"></div>${ i.severity }</td>
                            </tr>
                            <% acount++; %>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr class="tablefooter">
                            <td colspan="3"><div class="pageinfo">Displaying Items 1 to <%= acount %>${ acount } of ${fn:length( it.allergies )}</div></td>
                        </tr>
                    </tfoot>
                </table>
                <div class="phr-input-button-align-right">
                    <a class="phr-input-button" href="${ctx_patient}/${ it.healthRecordId }/allergies">View More</a>
                    <a class="phr-input-button" href="${ctx_patient}/${ it.healthRecordId }/allergies-add">Add Record</a>
                </div>
            </div><!-- /#m-allergies -->--%>

        </td>
    </tr>
    <tr>
        <td class="phr-hr-container-td">

<%--            <div id="m-medications" class="covermodule">
                <div class="m-header"><a href="${ctx_patient}/${ it.healthRecordId }/medications">Medications (<span id="medications-count">${fn:length( it.medications )}</span>)</a></div>
                <table class="datatable" cellpadding="4" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Medication</th>
                            <th>Rx ID</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                       <% int mcount=0; %>
                        <c:forEach var="m" items="${ it.medications }" begin="0" end="4" varStatus="loop">
                            <tr>
                                <td><a href="${ctx_patient}/${ it.healthRecordId }/medications/${ m.medicationId }">${ m.medicationText }</a></td>
                                <td>${ m.rxid }</td>
                                <td class="<c:out value="${fn:toLowerCase( m.status )}" />">${ m.status }</td>
                            </tr>
                            <% mcount++; %>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr class="tablefooter">
                            <td colspan="4"><div class="pageinfo">Displaying Items 1 to <%= mcount %>${ mcount } of ${fn:length( it.medications )}</div></td>
                        </tr>
                    </tfoot>
                </table>
                <div class="phr-input-button-align-right">
                    <a class="phr-input-button" href="${ctx_patient}/${ it.healthRecordId }/medications">View More</a>
                    <a class="phr-input-button" href="${ctx_patient}/${ it.healthRecordId }/medications-add">Add Record</a>
                </div>
            </div><!-- /#m-medications -->--%>

        </td>
        <td class="phr-hr-container-td">

            <div id="m-labresults" class="covermodule">
                <div class="m-header"><a href="${ctx_patient}/${ it.healthRecordId }/results">Results - Labs & Tests (<span id="labresults-count">${fn:length( it.results )}</span>)</a></div>
                <table class="datatable" cellpadding="4" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Test Name</th>
                            <th>Result</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                       <% int rcount=0; %>
                        <c:forEach var="r" items="${ it.results }" begin="0" end="4" varStatus="loop">
                            <tr>
                                <td><a href="${ctx_patient}/${ it.healthRecordId }/results/${ r.resultId }">${ r.testName }</a></td>
                                <td>${ r.result } ${r.resultUnit}</td>
                                <td>${ r.resultDate }</td>
                            </tr>
                            <% rcount++; %>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr class="tablefooter">
                            <td colspan="3"><div class="pageinfo">Displaying Items 1 to <%= rcount %>${ rcount } of ${fn:length( it.results )}</div></td>
                        </tr>
                    </tfoot>
                </table>
                <div class="phr-input-button-align-right">
                    <a class="phr-input-button" href="${ctx_patient}/${ it.healthRecordId }/results">View More</a>
                    <a class="phr-input-button" href="${ctx_patient}/${ it.healthRecordId }/results-add">Add Record</a>
                </div>
            </div><!-- /#m-labresults -->

        </td>
    </tr>
    <tr>
        <td class="phr-hr-container-td">

<%--            <div id="m-vitals" class="covermodule">
                <div class="m-header"><a href="${ctx_patient}/${ it.healthRecordId }/vitals">Vital Measurements (<span id="vitals-count">${fn:length( it.vitals )}</span>)</a></div>
                <table class="datatable" cellpadding="4" cellspacing="0">
                    <thead>
                        <tr>
                            <!-- <th class="cdd" style="width:24px;">&nbsp;</th>-->
                            <th>Vital</th>
                            <th>Value</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                       <% int vcount=0; %>
                        <c:forEach var="v" items="${ it.vitals }" begin="0" end="4" varStatus="loop">
                            <tr>
                                <td><a href="${ctx_patient}/${ it.healthRecordId }/vitals/${ v.vitalTypeId }/${ v.vitalId }"><str:upperCase>${ v.vitalTypeId }</str:upperCase></a></td>
                                <td>${ v.value }</td>
                                <td>${ v.observedDate }</td>
                            </tr>
                            <% vcount++; %>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr class="tablefooter">
                            <td colspan="3"><div class="pageinfo">Displaying Items 1 to <%= vcount %>${ vcount } of ${fn:length( it.vitals )}</div></td>
                        </tr>
                    </tfoot>
                </table>
                <div class="phr-input-button-align-right">
                    <a class="phr-input-button" href="${ctx_patient}/${ it.healthRecordId }/vitals">View More</a>
                    <a class="phr-input-button" href="${ctx_patient}/${ it.healthRecordId }/vitals-add">Add Record</a>
                </div>
            </div><!-- /#m-vitals -->--%>

        </td>
        <td class="phr-hr-container-td">

           <%-- <div id="m-immunizations" class="covermodule">
                <div class="m-header"><a href="${ctx_patient}/${ it.healthRecordId }/immunizations">Immunizations (<span id="immunizations-count">${fn:length( it.immunizations )}</span>)</a></div>
                <table class="datatable" cellpadding="4" cellspacing="0">
                    <thead>
                        <tr>
                            <!--<th class="cdd" style="width:24px;">&nbsp;</th>-->
                            <th>Immunization</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% int icount=0; %>
                        <c:forEach var="i" items="${ it.immunizations }" begin="0" end="4" varStatus="loop">
                            <tr>
                                <td><a href="${ctx_patient}/${ it.healthRecordId }/immunizations/${ i.immunizationId }">${ i.immunizationType }</a></td>
                                <td>${ i.dateReceived }</td>
                            </tr>
                            <% icount++; %>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr class="tablefooter">
                            <td colspan="2"><div class="pageinfo">Displaying Items 1 to <%= icount %>${ icount } of ${fn:length( it.immunizations )}</div></td>
                        </tr>
                    </tfoot>
                </table>
                <div class="phr-input-button-align-right">
                    <a class="phr-input-button" href="${ctx_patient}/${ it.healthRecordId }/immunizations">View More</a>
                    <a class="phr-input-button" href="${ctx_patient}/${ it.healthRecordId }/immunizations-add">Add Record</a>
                </div>
            </div><!-- /#m-immunizations -->--%>

        </td>
    </tr>
    <tr>
        <td class="phr-hr-container-td">
<%--
            <div id="m-visits" class="covermodule">
                <div class="m-header"><a href="${ctx_patient}/${ it.healthRecordId }/calendar">Appointments &amp; Visits (<span id="visits-count">${fn:length( it.visits )}</span>)</a></div>
                <table class="datatable" cellpadding="4" cellspacing="0">
                    <thead>
                        <tr>
                            <!-- <th class="cdd" style="width:24px;">&nbsp;</th>-->
                            <th>Title</th>
                            <th>Date</th>
                            <th>Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% int viscount=0; %>
                        <c:forEach var="v" items="${ it.visits }" begin="0" end="4" varStatus="loop">
                            <tr>
                                <td><a href="${ctx_patient}/${ it.healthRecordId }/visits/${ v.visitId }">${ v.title }</a></td>
                                <td><fmt:formatDate pattern="MM/dd/yyyy" value="${ v.visitDate }" /></td>
                                <td><fmt:formatDate pattern="h:mm a" value="${ v.visitTime }" /></td>
                            </tr>
                            <% viscount++; %>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr class="tablefooter">
                            <td colspan="3"><div class="pageinfo">Displaying Items 1 to <%= viscount %>${ viscount } of ${fn:length( it.visits )}</div></td>
                        </tr>
                    </tfoot>
                </table>
                <div class="phr-input-button-align-right">
                    <a href="${ctx_patient}/${ it.healthRecordId }/calendar/<fmt:formatDate pattern="yyyy/M/" value="${now}" />" class="phr-input-button">Calendar</a>
                    <a class="phr-input-button" href="${ctx_patient}/${ it.healthRecordId }/visits">View List</a>
                    <a class="phr-input-button" href="${ctx_patient}/${ it.healthRecordId }/visits-add">Add Record</a>
                </div>
            </div><!-- /#m-visits -->--%>

        </td>
        <td class="phr-hr-container-td">
            &nbsp;
        </td>
    </tr>
</table>

</body>
