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
    <title>HealtheMe - Care Notebook - Nutrition Events</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/carenotebook.css" />
<!--    <script type="text/javascript" src="${ctx_static}/js/validate.nutritionevents.js"></script>-->
    <script language="javascript">
        //set globals
        var ctx_static = "${ctx_static}";
        var patientId = ${it.healthRecordId};

        $(document).ready(function() {
            $("input.datepicker").datepicker({showOn: 'button', buttonImage: '${ctx_static}/images/cal.gif', buttonImageOnly: true, onSelect:function(){$(this).valid();}});
        });
    </script>
</head>

<body id="nutrition-ne" class="nutrition">

        <h1>Nutrition Events</h1>

        <div class="def-container">
            <div class="def-title">Purpose</div>
            This section is meant to provide you with a place to record nutritional changes and transitions. This includes changes to feeding methods, diets, or apparatus. Updated medical information that you record will help to make sure that your child receives proper medical treatment.
        </div>
        <br />

    <form class="nutritioneventsform carenotebook" action="${ctx}/form/care/${ it.healthRecordId }/nutritionevents/post/" method="post" enctype="application/x-www-form-urlencoded">

    <c:if test="${it.class.simpleName == 'NutritionEvents'}">
        <c:forEach items="${it.nutritionEvents}" var="event" varStatus="status">

            <c:choose>
            <c:when test="${status.count == 1 }">
                <div id="formfieldcontainer" class="addedDiv">
            </c:when>
            <c:otherwise>
                <hr /><br /><div class="addedDiv">
            </c:otherwise>
            </c:choose>

            <input type="hidden" id="meetingId_${status.count}" name="eventId_${status.count}" value="${ event.eventId }" />

            <label for="eventDate_${status.count}">Event Date</label>
            <input type="text" class="hv_element_date_input datepicker" id="eventDate_${status.count}" name="eventDate_${status.count}" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${ event.eventDate }"/>" title="Event Date" maxlength="10" size="20">
            <br />
            <label for="Event_${status.count}">Event</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="event_${status.count}" name="event_${status.count}" rows="4" cols="20">${ event.event }</textarea>
            <br />
            <label for="comments_${status.count}">Additional comments or notes:</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="comments_${status.count}" name="comments_${status.count}" rows="4" cols="20">${ event.comments }</textarea>
            <br />

            <br />
            <br />

            </div>
        </c:forEach>
    </c:if>

        <div class="add">
            <a title="Add Another Evemt" id="addForm" href="#">Add Another Event</a>
        </div>
        <div style="clear: both"></div><br />
        <input type="submit" value="Save" title="Save" name="submitform" id="submitform" />
        <input type="reset" value="Reset" />
    </form>
<%@ include file="/includes/form_duplicator.jspf" %>

</body>