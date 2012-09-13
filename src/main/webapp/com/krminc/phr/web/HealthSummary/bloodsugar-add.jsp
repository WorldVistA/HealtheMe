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
    Document   : bloodsugar-add
    Created on : Oct 15, 2009, 10:54:13 AM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Vitals - Blood Sugar - Add</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <script type="text/javascript">
        var url = "${apipath}/patients/${it.healthRecordId}/vitals/bloodsugar/";
        var baseuri = '${ctx_patient}';

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
    <script type="text/javascript" src="${ctx_static}/js/validate.vital.bloodsugar.new.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/iframe.css" media="screen, print"/>
</head>
<body bgcolor="#ffffff" text="#000000" link="#0000cc" vlink="#551a8b" alink="#ff0000">
    <div id="lightBox">
        <div id="m_lightBox">
            <h1>Add Fingerstick Blood Glucose</h1>
            
            <span class="toolSummary">Note: This Fingerstick Blood Glucose reading will appear under 'Self Entered' data.</span>

            <form id="detailform" name="detailform" action="./formhandler/" method="post">
            <input type="hidden" id="sourceurl" name="sourceuri" value="${ctx_patient}/${it.healthRecordId}/vitals/bloodsugar-add/" />
            <input type="hidden" id="returnurl" name="returnurl" value="${ctx_patient}/${it.healthRecordId}/vitals/bloodsugar" />
            <input type="hidden" id="patientid" name="patientid" value="${it.healthRecordId}" />
            <input type="hidden" id="datasourceid" name="datasourceid" value="1" />

            <div class="ie">
                <div>
                    <span class="fldgroup">
                        <div class="hv_element">
                            <label for="level">Blood Glucose Value<span class="required">*</span></label>
                            <input class="hv_element_level_input" maxlength="30" id="level" name="level" />
                        </div>
                    </span>
                    <span class="fldgroup">
                        <div class="hv_element">
                            <label for="unit">Unit<span class="required">*</span></label>
                            <select name="unit" id="unit">
                                <option value="" selected="selected">- Select -</option>
                                <option value="mg/dL">mg/dL</option>
                                <option value="mmol/L">mmol/L</option>
                                <option value="Other">Other</option>
                            </select>
                        </div>
                    </span>
                </div>
                 <div class="clear"> </div>
                <div class="hv_element hv_element_date">
                    <label for="observeddate">Date Observed<span class="required">*</span></label>
                    <input class="hv_element_date_input datepicker" maxlength="30" name="observeddate" id="observeddate" />
                </div>
                <div class="hv_element">
                    <label for="method">Reading Method</label>
                    <input class="hv_element_method_input" maxlength="30" id="method" name="method"/>
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