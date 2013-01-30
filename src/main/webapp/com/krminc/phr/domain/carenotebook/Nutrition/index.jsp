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
    Created on : Mar 26, 2012, 9:33:28 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>
<html>
<head>
    <title>HealtheMe - Care Notebook - Nutrition Information</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/carenotebook.css" />
<!--    <script type="text/javascript" src="${ctx_static}/js/validate.emergencyinformation.js"></script>-->
    <script language="javascript">
        //set globals
        var ctx_static = "${ctx_static}";
        var patientId = ${it.healthRecordId};
    </script>
</head>
<body id="nutrition-np" class="nutrition">

    <form class="carenotebook" name="nutrition" id="nutrition" action="${ctx}/form/care/${ it.healthRecordId }/nutrition/post/" method="post" enctype="multipart/form-data">

        <h1>Nutrition</h1>

        <div class="def-container">
            <div class="def-title">Purpose</div>
            This section is meant to provide you with a place to record the nutritional habits and information. Updated medical information that you record will help to make sure that your child receives proper medical treatment.
        </div>
        <br />

        <label for="feedingSchedule">Feeding Schedule:</label>
        <div style="clear: both"></div>
        <br />
        <textarea id="feedingSchedule" name="feedingSchedule" rows="4" cols="20">${ it.feedingSchedule }</textarea>
        <br />
        <br />

        <label for="foodLikes">Food Likes</label>
        <div style="clear: both"></div>
        <br />
        <textarea id="foodLikes" name="foodLikes" rows="4" cols="20">${ it.foodLikes }</textarea>
        <br />
        
        <label for="foodDislikes">Food Dislikes</label>
        <div style="clear: both"></div>
        <br />
        <textarea id="foodDislikes" name="foodDislikes" rows="4" cols="20">${ it.foodDislikes }</textarea>
        <br />

        <label for="comments">Additional comments or notes:</label>
        <div style="clear: both"></div>
        <br />
        <textarea id="comments" name="comments" rows="4" cols="20">${ it.comments }</textarea>
        <br />
        <br />
		<h2>Food Allergy Information</h2><br />
		Please use the <a href="${ctx_patient}/${ it.healthRecordId}/allergies">Allergies</a> section to record a history of food allergies.<br />
		Be sure to save any changes made to this section by clicking the Save button
		 below before leaving.<br />
        <br />
        <br />
        <input type="submit" value="Save" title="Save" name="submitform" id="submitform" />
        <input type="reset" value="Reset" />
    </form>
</body>