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
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<html>
<head>
    <title>Visits - Edit</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <script type="text/javascript" src="${ctx_static}/js/validate.visit.js"></script>
    <script type="text/javascript">
        var baseuri = '${ctx_patient}';
        var url = "${apipath}/patients/${it.healthRecordId}/visits/${ it.visitId }/";

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

            $("#purpose option[value='${it.purpose}']").attr("selected", true);
        });

    </script>
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/iframe.css" media="screen, print"/>

</head>
<body bgcolor="#ffffff" text="#000000" link="#0000cc" vlink="#551a8b" alink="#ff0000">
    <div id="lightBox">
        <div id="m_lightBox">
            <h1>Edit Visit</h1>
            
            <span class="toolSummary">Note: This visit will appear under 'Self Entered' data.</span>

            <form id="detailform" name="detailform" action="./formhandler/" method="post">
            <input type="hidden" id="sourceurl" name="sourceuri" value="${ctx}/visits/${it.visitId}/edit" />
            <input type="hidden" id="returnurl" name="returnurl" value="${ctx}/visits" />
            <input type="hidden" id="patientid" name="patientid" value="${it.healthRecordId}" />
            <input type="hidden" id="datasourceid" name="datasourceid" value="${it.dataSourceId}" />
            <input type="hidden" id="visitid" name="visitid" value="${it.visitId}" />

            <div class="ie">
                <div class="hv_element">
                    <label for="title">Visit Name<span class="required">*</span></label>
                    <input class="hv_element_systolic_input" maxlength="100" id="title" name="title" value="${ it.title }"/>
                </div>
                <div>
                    <span class="fldgroup">
                        <div class="hv_element hv_element_date">
                            <label for="visitdate">Date of Visit</label>
                            <input class="hv_element_date_input datepicker" maxlength="30" name="visitdate" id="visitdate" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${ it.visitDate }" />"/>
                       	</div>
                    </span>
                    <span class="fldgroup">
                        <div class="hv_element">
                            <label for="visittime">Time of Visit</label>
                            <input class="hv_element_systolic_input" maxlength="30" id="visittime" name="visittime" value="<fmt:formatDate pattern="h:mm a" value="${ it.visitTime }" />"/>
                      	</div>
                    </span>
                </div>
                <div class="clear"> </div>
                <div>
                    <span class="fldgroup">
                        <div class="hv_element">
                            <label for="location">Location</label>
                            <input class="hv_element_systolic_input" maxlength="30" id="location" name="location" value="${ it.location }"/>
                       	</div>
                    </span>
                    <span class="fldgroup">
                        <div class="hv_element">
                            <label for="provider">Provider</label>
                            <input class="hv_element_systolic_input" maxlength="30" id="provider" name="provider" value="${ it.provider }"/>
                        </div>
                    </span>
               	</div>
                <div class="clear"> </div>
                <div class="hv_element">
                    <label for="purpose">Purpose</label>
                    <select name="purpose" id="purpose">
						<option value="">- Select -</option>
						<option value="Acute Care">Acute Care</option>
						<option value="Advice/Health Education">Advice/Health Education</option>
						<option value="After-Hours Care">After-Hours Care</option>
						<option value="Allergy Screening">Allergy Screening</option>
						<option value="Ambulatory Care Services">Ambulatory Care Services</option>
						<option value="Audiology">Audiology</option>
						<option value="Behavior Health Medicine">Behavior Health Medicine</option>
						<option value="Cardiology">Cardiology</option>
						<option value="Care of Intensive Care Unit Patient">Care of Intensive Care Unit Patient</option>
						<option value="Checkup Examination">Checkup Examination</option>
						<option value="Consultation">Consultation</option>
						<option value="Custodial Care">Custodial Care</option>
						<option value="Defensive Medicine">Defensive Medicine</option>
						<option value="Dental Procedures">Dental Procedures</option>
						<option value="Dermatology">Dermatology</option>
						<option value="Developmental Therapy">Developmental Therapy</option>
						<option value="Diagnosis">Diagnosis</option>
						<option value="Diagnostic Services">Diagnostic Services</option>
						<option value="Dietary Services">Dietary Services</option>
						<option value="Disease Management">Disease Management</option>
						<option value="Ear, Nose, and Throat">Ear, Nose, and Throat</option>
						<option value="Emergency Health Services">Emergency Health Services</option>
						<option value="Endocrinology">Endocrinology</option>
						<option value="Follow-up Visit">Follow-up Visit</option>
						<option value="Gastroenterology">Gastroenterology</option>
						<option value="General Medical Treatment">General Medical Treatment</option>
						<option value="General Ophthalmological Services">General Ophthalmological Services</option>
						<option value="Hearing Screening">Hearing Screening</option>
						<option value="Hematology">Hematology</option>
						<option value="Hospitalization">Hospitalization</option>
						<option value="House Call">House Call</option>
						<option value="Immunization Programs">Immunization Programs</option>
						<option value="Immunization Visit">Immunization Visit</option>
						<option value="Intensive Care">Intensive Care</option>
						<option value="Labor and Delivery Admission">Labor and Delivery Admission</option>
						<option value="Long Term Hospitalization">Long Term Hospitalization</option>
						<option value="Medicine, Indigenous">Medicine, Indigenous</option>
						<option value="Nephrology">Nephrology</option>
						<option value="Neurology">Neurology</option>
						<option value="Nursing Assessment">Nursing Assessment</option>
						<option value="Nursing Home Care">Nursing Home Care</option>
						<option value="Nursing Services">Nursing Services</option>
						<option value="Nutrition Counseling">Nutrition Counseling</option>
						<option value="Occupational Therapy">Occupational Therapy</option>
						<option value="Orthopedics">Orthopedics</option>
						<option value="Physical Therapy">Physical Therapy</option>
						<option value="Pulmonology">Pulmonology</option>
						<option value="Radiology">Radiology</option>
						<option value="Referral">Referral</option>
						<option value="Speech Therapy">Speech Therapy</option>
						<option value="Taking Vital Signs">Taking Vital Signs</option>
						<option value="Urology">Urology</option>
						<option value="Well Child Visit">Well Child Visit</option>
						<option value="Well Person Screening">Well Person Screening</option>
                    </select>
                </div>
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