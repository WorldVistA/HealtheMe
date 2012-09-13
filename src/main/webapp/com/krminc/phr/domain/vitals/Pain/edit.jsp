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
    Created on : Oct 20, 2009, 10:25:38 AM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<html>
<head>
    <title>Vitals - Pain - Edit</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <script type="text/javascript" src="${ctx_static}/js/validate.vital.pain.js"></script>
    <script type="text/javascript">
        var baseuri = '${ctx_patient}';
        var url = "${apipath}/patients/${it.healthRecordId}/vitals/pain/${ it.painId }/";

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

            $("#value option[value='${it.value}']").attr("selected", true);
        });

    </script>
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/iframe.css" media="screen, print"/>

</head>
<body bgcolor="#ffffff" text="#000000" link="#0000cc" vlink="#551a8b" alink="#ff0000">
    <div id="lightBox">
        <div id="m_lightBox">
            <div class="midSolidBlueBox">
                <h2 id="hr-header-title">Edit Pain Measurement</h2>
                <h2 id="hr-header-info""></h2>
                <span class="s_arts">
                    <span class="tl"> </span>

                    <span class="tr"> </span>
                    <span class="bl"> </span>
                    <span class="br"> </span>
                    <span class="sp1"> </span>
                </span>
            </div>
            <span class="toolSummary">Note: This pain measurement reading will appear under 'Self Entered' data.</span>

            <form id="detailform" name="detailform" action="./formhandler/" method="post">
            <input type="hidden" id="sourceurl" name="sourceuri" value="${ctx}/vitals/pain/${it.painId}/edit" />
            <input type="hidden" id="returnurl" name="returnurl" value="${ctx}/vitals/pain" />
            <input type="hidden" id="patientid" name="patientid" value="${it.healthRecordId}" />
            <input type="hidden" id="datasourceid" name="datasourceid" value="${it.dataSourceId}" />
            <input type="hidden" id="painid" name="painid" value="${it.painId}" />

            <div class="ie">
                <div class="hv_element">
                        <div class="hv_element_title">
                            <span>Pain Level</span><span class="required">*</span>
                        </div>
                        <div class="hv_element2">
                            <select name="value" id="value">
                                <option value=""></option>
                                <option value="0">0 - Pain free</option>
                                <option value="1">1</option>
                                <option value="2">2 - Mild</option>
                                <option value="3">3</option>
                                <option value="4">4 - Tolerable</option>
                                <option value="5">5</option>
                                <option value="6">6 - Distressful</option>
                                <option value="7">7</option>
                                <option value="8">8 - Severe</option>
                                <option value="9">9</option>
                                <option value="10">10 - Totally disabling</option>
                            </select>
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
