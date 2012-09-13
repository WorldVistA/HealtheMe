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
    Created on : Mar 27, 2012, 2:35:25 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>

<html>
<head>
    <meta name="requiresJQUI" content="true">
    <title>HealtheMe - Care Notebook - Coping, Stress, & Tolerance Log</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/carenotebook.css" />
<!--    <script type="text/javascript" src="${ctx_static}/js/validate.stress.js"></script>-->
    <script language="javascript">
        //set globals
        var ctx_static = "${ctx_static}";
        var patientId = ${it.healthRecordId};
        
        $(document).ready(function() {
            $("input.datepicker").datepicker({showOn: 'button', buttonImage: '${ctx_static}/images/cal.gif', buttonImageOnly: true, onSelect:function(){$(this).valid();}});
        });
    </script>
</head>
<body id="logs-cs" class="logs">

        <h1>Coping, Stress, & Tolerance Log</h1>

        <div class="def-container">
            <div class="def-title">Purpose</div>
            Use this page to talk about how your child copes with stress. Stressful levents might include new people or situations, a hospital stay, or procedures such as having blood drawn. Describe what things upset your child and what your child does when upset or when he or she has "had enough." Describe your child's way of asking for help and things to do or say to comfort your child.
        </div>
        <br />

    <form class="stressform carenotebook" action="${ctx}/form/care/${ it.healthRecordId }/stress/post/" method="post" enctype="application/x-www-form-urlencoded">
        

    <c:if test="${it.class.simpleName == 'StressLog'}">
        <c:forEach items="${it.log}" var="log" varStatus="status">

            <c:choose>
            <c:when test="${status.count == 1 }">
                <div id="formfieldcontainer" class="addedDiv">
            </c:when>
            <c:otherwise>
                <hr /><br /><div class="addedDiv">
            </c:otherwise>
            </c:choose>

            <input type="hidden" id="stressId_${status.count}" name="stressId_${status.count}" value="${ log.stressId }" />

            <label for="observedDate_${status.count}">Date Observed:</label>
            <input type="text" class="hv_element_date_input datepicker" id="observedDate_${status.count}" name="observedDate_${status.count}" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${ log.observedDate }"/>" title="Observed Date" maxlength="10" size="20">
            <br />

            <label for="stressText_${status.count}"></label>
            <div style="clear: both"></div>
            <br />
            <textarea id="stressText_${status.count}" name="stressText_${status.count}" rows="10" cols="20">${ log.stressText }</textarea>
            <br />

            <br />
            <br />

            </div>
        </c:forEach>
    </c:if>
        
        <div class="add">
            <a title="Add Another Stress Log Entry" id="addForm" href="#">Add Another Stress Log Entry</a>
        </div>
        <div style="clear: both"></div><br />
        
        <input type="submit" value="Save" title="Save" name="submitform" id="submitform" />
        <input type="reset" value="Reset" />
    </form>
            
 <%@ include file="/includes/form_duplicator.jspf" %>
</body>