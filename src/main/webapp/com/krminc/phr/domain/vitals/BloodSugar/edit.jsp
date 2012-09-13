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
    Document   : edit
    Created on : Sep 2, 2009, 2:58:21 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<html>
<head>
    <title>Vitals - Fingerstick Blood Glucose Reading - Edit</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <script type="text/javascript" src="${ctx_static}/js/validate.vital.bloodsugar.js"></script>
    <script type="text/javascript">
        var baseuri = '${ctx_patient}';
        var url = "${apipath}/patients/${it.healthRecordId}/vitals/bloodsugar/${ it.bloodSugarId }/";

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
            <div class="midSolidBlueBox">
                <h2 id="hr-header-title">Edit Fingerstick Blood Glucose Reading</h2>
                <h2 id="hr-header-info""></h2>
                <span class="s_arts">
                    <span class="tl"> </span>

                    <span class="tr"> </span>
                    <span class="bl"> </span>
                    <span class="br"> </span>
                    <span class="sp1"> </span>
                </span>
            </div>
            <span class="toolSummary">Note: This Blood Sugar reading will appear under 'Self Entered' data.</span>

            <form id="detailform" name="detailform" action="./formhandler/" method="post">
            <input type="hidden" id="sourceurl" name="sourceuri" value="${ctx}/vitals/bloodsugar/${it.bloodSugarId}/edit" />
            <input type="hidden" id="returnurl" name="returnurl" value="${ctx}/vitals/bloodsugar" />
            <input type="hidden" id="patientid" name="patientid" value="${it.healthRecordId}" />
            <input type="hidden" id="datasourceid" name="datasourceid" value="${it.dataSourceId}" />
            <input type="hidden" id="bloodsugarid" name="bloodsugarid" value="${it.bloodSugarId}" />

            <div class="ie">
                <div class="hv_element">
                        <div class="hv_element_title">
                            <span>Fingerstick Blood Glucose Reading</span><span class="required">*</span>
                        </div>
                        <div class="hv_element2">
                            <input class="hv_element_level_input" maxlength="30" id="level" name="level" value="${it.bloodSugarLevel}"/>
                        </div>
                </div>
                <div class="hv_element">
                        <div class="hv_element_title">
                            <span>Unit</span><span class="required">*</span>
                        </div>
                        <div class="hv_element2">
                            <select name="unit" id="unit">
                                <option value="mg/dL" <c:if test="${it.unit == 'mg/dL'}">selected="selected"</c:if>>mg/dL</option>
                                <option value="mmol/L" <c:if test="${it.unit == 'mmol/L'}">selected="selected"</c:if>>mmol/L</option>
                                <option value="Other" <c:if test="${it.unit == 'Other'}">selected="selected"</c:if>>Other</option>
                            </select>
                        </div>
                </div>
                <div class="hv_element">
                        <div class="hv_element_title">
                            <span>Reading Method</span>
                        </div>
                        <div class="hv_element3">
                            <input class="hv_element_method_input" maxlength="30" id="method" name="method" value="${it.method}"/>
                        </div>
                </div>
                <div class="hv_element hv_element_date">
                    <div class="hv_element_title">
                        <span>Date Observed</span><span class="required">*</span>
                    </div>
                    <div class="hv_element_title">
                        <input class="hv_element_date_input datepicker" maxlength="30" name="observeddate" id="observeddate" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${ it.observedDate }"/>"/>
                    </div>
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