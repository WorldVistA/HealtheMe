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
    Created on : Mar 27, 2012, 3:51:44 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>
<html>
<head>
    <meta name="requiresJQUI" content="true">
    <title>HealtheMe - Care Notebook - Transitions, Goals, Hopes, and Dreams</title>
    <meta name="requiresValidation" content="true">
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/carenotebook.css" />
<!--    <script type="text/javascript" src="${ctx_static}/js/validate.transitions.js"></script>-->
    <script language="javascript">
        //set globals
        var ctx_static = "${ctx_static}";
        var patientId = ${it.healthRecordId};
    </script>
</head>
<body id="transitions" class="transitions">

    <form class="carenotebook" name="transitions" id="transitions" action="${ctx}/form/care/${ it.healthRecordId }/transitions/post/" method="post" enctype="multipart/form-data">

        <h1>Transitions, Goals, Hopes, and Dreams</h1>

        <div class="def-container">
            <div class="def-title">Purpose</div>
            Your child and family will experience many transitions, small and large, over the years. Three predictable transitions occur: when your child reaches school age, when he or she approaches adolescence and when your child moves from adolescence into adulthood. Other transitions may involve moving into new programs, working with new agencies and care providers, or making new friends. Transitions involve changes such as adding new expectations, responsibilities or resources, and letting go of others.<br /><br />
            List questions and concerns you have about your child's various transitions as they happen.
        </div>
        <br />

        <label for="transitionText">Transitions:</label>
        <div style="clear: both"></div>
        <br />
        <textarea id="transitionText" name="transitionText" rows="10" cols="20">${ it.transitionText }</textarea>
        <br />
        <br />
        
        <div class="def-container">
            <div class="def-title">Purpose</div>
            It's not always easy to think about the future. There may be many things, including what has to be done today, that keep you from looking ahead. It may be helpful to take some time to jot down a few ideas about your child's and family's future. You might start by thinking about your child's and family's strengths. How can these strengths help you plan for "what's next" and for reaching long-term goals? What are your dreams and your fears about your child's and family's future?<br /><br />
            Use this area to reflect on your child's hopes, dreams, growth and progress.
        </div>
        <br />

        <label for="hopesText">Hopes/Dreams:</label>
        <div style="clear: both"></div>
        <br />
        <textarea id="hopesText" name="hopesText" rows="10" cols="20">${ it.hopesText }</textarea>
        <br />
        
<!--        <label for="comments">Additional comments or notes:</label>
        <div style="clear: both"></div>
        <br />
        <textarea id="comments" name="comments" rows="4" cols="20">${ it.comments }</textarea>
        <br />-->

        <br />
        <br />
        <input type="submit" value="Save" title="Save" name="submitform" id="submitform" />
        <input type="reset" value="Reset" />
    </form>
</body>