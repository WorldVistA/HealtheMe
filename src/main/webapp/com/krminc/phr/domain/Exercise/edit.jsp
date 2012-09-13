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
    Created on : Aug 26, 2010, 9:02:12 AM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<html>
<head>
    <title>Exercises - Edit</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <script type="text/javascript" src="${ctx_static}/js/validate.exercise.js"></script>
    <script type="text/javascript">
        var baseuri = '${ctx_patient}';
        var url = "${apipath}/patients/${it.healthRecordId}/exercises/${ it.exerciseId }/";

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

            var availableTimes = ["7:00 am", "7:30 am", "8:00 am", "8:30 am", "9:00 am", "9:30 am", "10:00 am", "10:30 am", "11:00 am", "11:30 am", "12:00 pm", "12:30 pm", "1:00 pm", "1:30 pm", "2:00 pm", "2:30 pm", "3:00 pm", "3:30 pm", "4:00 pm", "4:30 pm", "5:00 pm", "5:30 pm", "6:00 pm", "6:30 pm", "7:00 pm", "7:30 pm", "8:00 pm", "8:30 pm", "9:00 pm", "9:30 pm", "10:00 pm", "10:30 pm", "11:00 pm", "11:30 pm", "12:00 am", "12:30 am", "1:00 am", "1:30 am", "2:00 am", "2:30 am", "3:00 am", "3:30 am", "4:00 am", "4:30 am", "5:00 am", "5:30 am", "6:00 am", "6:30 am"];
            $("#exercisetime").autocomplete(availableTimes, {
                minChars: -1,
                delay: 5,
                max: 50
            });

            var availableHours = ["0","1","2","3","4","5","6"];
            $("#durationhours").autocomplete(availableHours, {
                minChars: -1,
                delay: 5,
                max: 50
            });

            var availableMinutes = ["0","5","10","15","20","25","30","35","40","45","50","55"];
            $("#durationminutes").autocomplete(availableMinutes, {
                minChars: -1,
                delay: 5,
                max: 50,
                width: 45
            });

            $("#title").autocomplete("${apipath}/patients/${it.healthRecordId}/exercises/list", {
                minChars: 1,
                delay: 5,
                max: 50
            });
        });

    </script>
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/iframe.css" media="screen, print"/>
    <script type="text/javascript" src="${ctx_static}/js/jquery.autocomplete.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/jquery.autocomplete.css" media="screen"/>
</head>
<body bgcolor="#ffffff" text="#000000" link="#0000cc" vlink="#551a8b" alink="#ff0000">
    <div id="lightBox">
        <div id="m_lightBox">
            <h1>Edit Exercise</h1>

            <span class="toolSummary">Note: This exercise will appear under 'Self Entered' data.</span>

            <form id="detailform" name="detailform" action="./formhandler/" method="post">
            <input type="hidden" id="sourceurl" name="sourceuri" value="${ctx}/exercises/${it.exerciseId}/edit" />
            <input type="hidden" id="returnurl" name="returnurl" value="${ctx}/exercises" />
            <input type="hidden" id="patientid" name="patientid" value="${it.healthRecordId}" />
            <input type="hidden" id="datasourceid" name="datasourceid" value="${it.dataSourceId}" />
            <input type="hidden" id="exerciseid" name="exerciseid" value="${it.exerciseId}" />

            <div class="ie">
                <div class="hv_element">
                    <label for="title">Exercise Description<span class="required">*</span></label>
                    <input class="hv_element_systolic_input" maxlength="100" id="title" name="title" value="${ it.title }"/>
                </div>
                <div>
                    <span class="fldgroup">
                        <div class="hv_element hv_element_date">
                            <label for="exercisedate">Date of Exercise<span class="required">*</span></label>
                            <input class="hv_element_date_input datepicker" maxlength="30" name="exercisedate" id="exercisedate" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${ it.exerciseDate }" />"/>
                       	</div>
                    </span>
                    <span class="fldgroup">
                        <div class="hv_element">
                            <label for="exercisetime">Time of Exercise<span class="required">*</span></label>
                            <input class="hv_element_systolic_input" maxlength="30" id="exercisetime" name="exercisetime" value="<fmt:formatDate pattern="h:mm a" value="${ it.exerciseTime }" />"/>
                      	</div>
                    </span>
                </div>
                <div class="clear"> </div>

                <div>
                    <span class="fldgroup">
                        <div class="hv_element_comments">
                            <label for="durationhours">Duration of Exercise<span class="required">*</span></label>
                            <input class="hv_element_duration" maxlength="1" name="durationhours" id="durationhours" style="width: 30px;" value="${ it.exerciseDurationHours }"/>&nbsp;hours

                            <input class="hv_element_duration" maxlength="2" id="durationminutes" name="durationminutes" style="width: 30px;" value="${ it.exerciseDurationMinutes }"/>&nbsp;minutes
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