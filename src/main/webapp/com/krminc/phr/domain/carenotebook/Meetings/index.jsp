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
    Document   : index
    Created on : Mar 27, 2012, 9:01:44 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>

<html>
<head>
    <meta name="requiresJQUI" content="true">
    <title>HealtheMe - Care Notebook - Counselor Meetings</title>
    <meta name="requiresValidation" content="true">
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/carenotebook.css" />
<!--    <script type="text/javascript" src="${ctx_static}/js/validate.meetings.js"></script>-->
    <script language="javascript">
        //set globals
        var ctx_static = "${ctx_static}";
        var patientId = ${it.healthRecordId};
        
        $(document).ready(function() {
            $("input.datepicker").datepicker({showOn: 'button', buttonImage: '${ctx_static}/images/cal.gif', buttonImageOnly: true, onSelect:function(){$(this).valid();}});
        });
    </script>
</head>

<body id="education-cn" class="education">

        <h1>Meeting Notes</h1>

        <div class="def-container">
            <div class="def-title">Purpose</div>
            This section is intended to provide you with a space to record plans and results of meetings and conferences you may have with counselors concerning your child. Typically, this space will accomodate IFSP/IEP/IPP notes but may be used for other purposes as well.
        </div>
        <br />


    <form class="meetingform carenotebook" action="${ctx}/form/care/${ it.healthRecordId }/meetings/post/" method="post" enctype="application/x-www-form-urlencoded">
        

    <c:if test="${it.class.simpleName == 'Meetings'}">
        <c:forEach items="${it.meetings}" var="meeting" varStatus="status">

            <c:choose>
            <c:when test="${status.count == 1 }">
                <div id="formfieldcontainer" class="addedDiv">
            </c:when>
            <c:otherwise>
                <hr /><br /><div class="addedDiv">
            </c:otherwise>
            </c:choose>

            <input type="hidden" id="meetingId_${status.count}" name="meetingId_${status.count}" value="${ meeting.meetingId }" />

            <label for="meetingDate_${status.count}">Meeting Date</label>
            <input type="text" class="hv_element_date_input datepicker" id="meetingDate_${status.count}" name="meetingDate_${status.count}" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${ meeting.meetingDate }"/>" title="Meeting Date" maxlength="10" size="20">
            <br />
            
            <label for="meetingPurpose_${status.count}">Meeting Purpose</label>
            <input type="text" id="meetingPurpose_${status.count}" name="meetingPurpose_${status.count}" value="${ meeting.meetingPurpose }" title="Meeting Purpose" maxlength="50" size="20">
            <br />
            
            <label for="meetingIssues_${status.count}">Issues, Concerns, or Questions</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="meetingIssues_${status.count}" name="meetingIssues_${status.count}" rows="4" cols="20">${ meeting.meetingIssues }</textarea>
            <br />
            
            <label for="meetingResponses_${status.count}">Responses, Solutions, and Answers (to above)</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="meetingResponses_${status.count}" name="meetingResponses_${status.count}" rows="4" cols="20">${ meeting.meetingResponses }</textarea>
            <br />
            
            <label for="meetingOutcome_${status.count}">Outcome of the meeting</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="meetingOutcome_${status.count}" name="meetingOutcome_${status.count}" rows="4" cols="20">${ meeting.meetingOutcome }</textarea>
            <br />
            
            <label for="meetingSteps_${status.count}">Next steps</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="meetingSteps_${status.count}" name="meetingSteps_${status.count}" rows="4" cols="20">${ meeting.meetingSteps }</textarea>
            <br />
            
            <label for="meetingRemember_${status.count}">Things to Do or Remember</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="meetingRemember_${status.count}" name="meetingRemember_${status.count}" rows="4" cols="20">${ meeting.meetingRemember }</textarea>
            <br />
            
            <br />
            <label for="nextMeetingDate_${status.count}">Next Meeting Date</label>
            <input type="text" class="hv_element_date_input datepicker" id="nextMeetingDate_${status.count}" name="nextMeetingDate_${status.count}" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${ meeting.nextMeetingDate }"/>" title="Next Meeting Date" maxlength="10" size="20">
            <br />
            
            <label for="comments_${status.count}">Additional comments or notes:</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="comments_${status.count}" name="comments_${status.count}" rows="4" cols="20">${ meeting.comments }</textarea>
            <br />

            <br />
            <br />

            </div>
        </c:forEach>
    </c:if>
        
        <div class="add">
            <a title="Add Another Meeting" id="addForm" href="#">Add Another Meeting</a>
        </div>
        <div style="clear: both"></div><br />
        
        <input type="submit" value="Save" title="Save" name="submitform" id="submitform" />
        <input type="reset" value="Reset" />
    </form>
            
<%@ include file="/includes/form_duplicator.jspf" %>
</body>