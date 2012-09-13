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
    Document   : allergies-add.jsp
    Created on : Jun 16, 2009, 2:20:44 AM
    Author     : Daniel Shaw (dshaw.com)
--%>

<%@ include file="/includes/taglibs.jspf" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Allergies - Add</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <script type="text/javascript">
        var url = "${apipath}/patients/${it.healthRecordId}/allergies/";
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
    <script type="text/javascript" src="${ctx_static}/js/validate.allergy.new.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/iframe.css" media="screen, print"/>
</head>
<body bgcolor="#ffffff" text="#000000" link="#0000cc" vlink="#551a8b" alink="#ff0000">
    <div id="lightBox">
        <div id="m_lightBox">
            <h1>Add Allergy</h1>
           
            <span class="toolSummary">Note: This Allergy will appear under 'Self Entered' data.</span>

            <form id="detailform" name="detailform" action="./formhandler/" method="post">
            <input type="hidden" id="sourceurl" name="sourceuri" value="${ctx_patient}/${it.healthRecordId}/allergies-add/" />
            <input type="hidden" id="returnurl" name="returnurl" value="${ctx_patient}/${it.healthRecordId}/allergies" />
            <input type="hidden" id="patientid" name="patientid" value="${it.healthRecordId}" />
            <input type="hidden" id="datasourceid" name="datasourceid" value="1" />

            <div class="ie">
                <div class="hv_element">
                    <label for="allergytext">Allergy Name<span class="required">*</span></label>
                    <input class="hv_element_allergy_input" maxlength="100" id="allergytext" name="allergytext" />
                </div>
                <div class="hv_element hv_element_date">
                    <label for="observeddate">Date Observed<span class="required">*</span></label>
                    <input class="hv_element_date_input datepicker" maxlength="30" name="observeddate" id="observeddate" />
                </div>
              	<div>
                    <span class="fldgroup">
                        <div class="hv_element">
                            <label for="severity">Severity</label>
                            <input class="hv_element_diastolic_input" maxlength="30" id="severity" name="severity"/>
                        </div>
                    </span>
                    <span class="fldgroup">
                        <div class="hv_element">
                            <label for="reaction">Reaction</label>
                            <input class="hv_element_reaction_input" maxlength="30" id="reaction" name="reaction"/>
                        </div>
                    </span>
               	</div>
                <div class="clear"> </div>
                <div class="hv_element_comments">
                    <label for="comments">Comments</label>
                    <textarea id="comments" name="comments" rows="3"></textarea>
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