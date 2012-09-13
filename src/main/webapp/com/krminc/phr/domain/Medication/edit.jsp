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
    Document   : edit.jsp
    Created on : Jun 14, 2009, 4:26:50 PM
    Author     : Daniel Shaw (dshaw.com)
--%>

<%@ include file="/includes/taglibs.jspf" %>
<html>
<head>
    <title>Medication - Edit</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <script type="text/javascript" src="${ctx_static}/js/validate.medication.js"></script>
    <script type="text/javascript">
        var baseuri = '${ctx_patient}';
        var url = "${apipath}/patients/${it.healthRecordId}/medications/${ it.medicationId }/";

        $(document).ready(function() {
            $("input.datepicker").datepicker({showOn: 'button', buttonImage: '${ctx_static}/images/cal.gif', buttonImageOnly: true, onSelect:function(){$(this).valid();}});

            $("#submit").click(function() {
                var _form = $("#detailform");
                if (_form.valid()) {
                    $(this).attr("disabled","disabled");
                    _form.submit();
                }
                return false;
            });

            $("#cancel").click(function(cancelClick) {
                cancelClick.preventDefault();
                if (confirm("Are you sure you wish to cancel?")) parent.doClose();
            });
        });

    </script>
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/iframe.css" media="screen, print"/>

</head>
<body bgcolor="#ffffff" text="#000000" link="#0000cc" vlink="#551a8b" alink="#ff0000">
    <div id="lightBox">
        <div id="m_lightBox">
            <h1>Edit Medication</h1>

            <span class="toolSummary">Note: This Medication will appear under 'Self Entered' data.</span>

            <form id="detailform" name="detailform" action="./formhandler/" method="post">
            <input type="hidden" id="sourceurl" name="sourceuri" value="${ctx}/medications/${it.medicationId}/edit" />
            <input type="hidden" id="returnurl" name="returnurl" value="${ctx}/medications" />
            <input type="hidden" id="patientid" name="patientid" value="${it.healthRecordId}" />
            <input type="hidden" id="datasourceid" name="datasourceid" value="${it.dataSourceId}" />
            <input type="hidden" id="medicationid" name="medicationid" value="${it.medicationId}" />

            <div class="ie">
                <div class="hv_element">
                    <label for="medicationtext">Medication Name<span class="required">*</span></label>
                    <input class="hv_element_systolic_input" maxlength="100" id="medicationtext" name="medicationtext" value="${ it.medicationText }" />
                </div>
                <div class="hv_element">
                    <label for="status">Status</label>
                    <select id="status" name="status">
                        <option>- Select -</option>
                        <option ${ 'Active' == it.status ? 'selected="selected"' : ''}>Active</option>
                        <option ${ 'Inactive' == it.status ? 'selected="selected"' : ''}>Inactive</option>
                    </select>
                </div>
                <div>
                    <span class="fldgroup">
                        <div class="hv_element hv_element_date">
                            <label for="startdate">Start Date</label>
                            <input class="hv_element_date_input datepicker" maxlength="30" name="startdate" id="startdate" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${ it.startDate }"/>"/>
                      	</div>
                    </span>
                    <span class="fldgroup">
                        <div class="hv_element hv_element_date">
                            <label for="enddate">End Date</label>
                            <input class="hv_element_date_input datepicker" maxlength="30" name="enddate" id="enddate" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${ it.endDate }"/>" />
                       	</div>
                    </span>
              	</div>
                <div class="clear"> </div>
                <div>
                    <span class="fldgroup">
                        <div class="hv_element">
                            <label for="category">Category</label>
                            <select name="category" id="category">
                                <option value="">- Select -</option>
                                <option value="1" <c:if test="${it.category==1}">selected="selected"</c:if> >Prescription</option>
                                <option value="2" <c:if test="${it.category==2}">selected="selected"</c:if> >Over-the-counter</option>
                                <option value="3" <c:if test="${it.category==3}">selected="selected"</c:if> >Herbal</option>
                                <option value="4" <c:if test="${it.category==4}">selected="selected"</c:if> >Supplement</option>
                                <option value="5" <c:if test="${it.category==5}">selected="selected"</c:if> >Other</option>
                            </select>
                        </div>
                  </span>
                    <span class="fldgroup">
                    	<div class="hv_element">
                            <label for="reason">Reason for Taking</label>
                            <input class="hv_element_systolic_input" maxlength="30" id="reason" name="reason" value="${ it.reason }" />
                       	</div>
                   </span>
                </div>
                <div class="clear"> </div>
                <div>
                    <span class="fldgroup">
                        <div class="hv_element">
                            <label for="dose">Dose</label>
                            <input class="hv_element_systolic_input" maxlength="30" id="dose" name="dose" value="${ it.dose }"/>
                       	</div>
                    </span>
                    <span class="fldgroup">
                        <div class="hv_element">
                            <label for="frequency">Frequency</label>
                            <input class="hv_element_systolic_input" maxlength="30" id="frequency" name="frequency" value="${ it.frequency }"/>
                       	</div>
                    </span>
                    <span class="fldgroup">
                    	<div class="hv_element">
                            <label for="rxid">Prescription Number</label>
                            <input class="hv_element_systolic_input" maxlength="30" id="rxid" name="rxid" value="${ it.rxid }"/>
                      	</div>
                    </span>
             	</div>
                <div class="clear"> </div>
                <div class="hv_element_comments">
                    <label for="comments">Comments</label>
                    <textarea id="comments" name="comments" rows="3">${ it.comments }</textarea>
                </div>
            </div>
            <div id="lightBoxActions">
                <div class="req-marker">
                    <span class="required">*</span> Required field
                </div>
                <div class="phr-input-button-align-right">
                    <input id="cancel" type="button" class="" value="Cancel" />
                    <input id="submit" type="submit" class="" value="Save" />
                </div>
            </div>

            </form>

        </div>
    </div>
    </body>
</html>