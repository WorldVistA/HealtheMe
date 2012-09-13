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
    Document   : medicalinformation
    Created on : Oct 12, 2011, 1:22:14 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>

<html>
<head>
    <meta name="requiresJQUI" content="true">
    <title>HealtheMe - Care Notebook - Pharmacies</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/carenotebook.css" />
    <script type="text/javascript" src="${ctx_static}/js/validate.pharmacy.js"></script>
    <script language="javascript">
        //set globals
        var ctx_static = "${ctx_static}";
        var patientId = ${it.healthRecordId};
    </script>
</head>
<body id="medicalinformation-ph" class="medicalinformation">

        <h1>Medical Information</h1>

        <div class="def-container">
            <div class="def-title">Purpose</div>
            This section is meant to provide you with a place to record all of the pharmacy contact information for which you may be a customer. Updated medical information that you record will help to make sure that your child receives proper medical treatment.
        </div>
        <br />
        <h2>Pharmacies</h2>
        <br />

    <form class="pharmacyform carenotebook" action="${ctx}/form/care/${ it.healthRecordId }/pharmacies/post/" method="post" enctype="application/x-www-form-urlencoded">
        

    <c:if test="${it.class.simpleName == 'Pharmacies'}">
        <c:forEach items="${it.pharmacies}" var="pharmacy" varStatus="status">

            <c:choose>
            <c:when test="${status.count == 1 }">
                <div id="formfieldcontainer" class="addedDiv">
            </c:when>
            <c:otherwise>
                <hr /><br /><div class="addedDiv">
            </c:otherwise>
            </c:choose>

            <input type="hidden" id="pharmacyid_${status.count}" name="pharmacyid_${status.count}" value="${ pharmacy.pharmacyId }" />

            <label for="pharmacyname_${status.count}">Pharmacy Name</label>
            <input type="text" id="pharmacyname_${status.count}" name="pharmacyname_${status.count}" value="${ pharmacy.pharmacyName }" title="Pharmacy Name" maxlength="50" size="20">
            <br />

            <label for="pharmacycontact_${status.count}">Contact Person/Title</label>
            <input type="text" id="pharmacycontact_${status.count}" name="pharmacycontact_${status.count}" value="${ pharmacy.pharmacyContact }" title="Contact Person/Title" maxlength="25" size="20">
            <br />

            <label for="phonenumber_${status.count}">Phone Number</label>
            <input type="text" id="phonenumber_${status.count}" name="phonenumber_${status.count}" value="${ pharmacy.phoneNumber }" title="Phone Number" maxlength="25" size="20" class="phonenumber">
            <br />

            <label for="faxnumber_${status.count}">Fax Number</label>
            <input type="text" id="faxnumber_${status.count}" name="faxnumber_${status.count}" value="${ pharmacy.faxNumber }" title="Fax Number" maxlength="25" size="20" class="faxnumber">
            <br />

            <label for="specialtyclinicemail_${status.count}">Email Address</label>
            <input type="text" id="pharmacyemail_${status.count}" name="pharmacyemail_${status.count}" value="${ pharmacy.pharmacyEmail }" title="Email Address" maxlength="100" size="20">
            <br />

            <label for="comments_${status.count}">Medication Notes:</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="pharmacymedications_${status.count}" name="pharmacymedications_${status.count}" rows="4" cols="20">${ pharmacy.pharmacyMedications }</textarea>
            <br />

            <label for="comments_${status.count}">Additional comments or notes:</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="comments_${status.count}" name="comments_${status.count}" rows="4" cols="20">${ pharmacy.comments }</textarea>
            <br />

            <br />
            <br />

            </div>
        </c:forEach>
    </c:if>
        
        <div class="add">
            <a title="Add Another Pharmacy" id="addForm" href="#">Add Another Pharmacy</a>
        </div>
        <div style="clear: both"></div><br />
        
        <input type="submit" value="Save" title="Save" name="submitform" id="submitform" />
        <input type="reset" value="Reset" />
    </form>
            
 <%@ include file="/includes/form_duplicator.jspf" %>
</body>