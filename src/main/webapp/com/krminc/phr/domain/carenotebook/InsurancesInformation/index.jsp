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
    <title>HealtheMe - Care Notebook - Insurance Information</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/carenotebook.css" />
    <script type="text/javascript" src="${ctx_static}/js/validate.insuranceinformation.js"></script>
    <script language="javascript">
        //set globals
        var ctx_static = "${ctx_static}";
        var patientId = ${it.healthRecordId};
    </script>
</head>
<body id="medicalinformation-in" class="medicalinformation">

        <h1>Medical Information</h1>

        <div class="def-container">
            <div class="def-title">Purpose</div>
            This section is meant to provide you with a place to record all insurances you may need to help pay for medical care. Updated medical information that you record will help to make sure that your child receives proper medical treatment.
        </div>
        <br />
        <h2>Insurance Information</h2>
        <br />

    <form class="insuranceinformationform carenotebook" action="${ctx}/form/care/${ it.healthRecordId }/insuranceinformation/post/" method="post" enctype="application/x-www-form-urlencoded">
        

    <c:if test="${it.class.simpleName == 'InsurancesInformation'}">
        <c:forEach items="${it.insurances}" var="insurance" varStatus="status">

            <c:choose>
            <c:when test="${status.count == 1 }">
                <div id="formfieldcontainer" class="addedDiv">
            </c:when>
            <c:otherwise>
                <hr /><br /><div class="addedDiv">
            </c:otherwise>
            </c:choose>

            <input type="hidden" id="insuranceinformationid_${status.count}" name="insuranceinformationid_${status.count}" value="${ insurance.insuranceinformationId }" />

            <label for="insurancename_${status.count}">Insurance Name</label>
            <input type="text" id="insurancename_${status.count}" name="insurancename_${status.count}" value="${ insurance.insuranceName }" title="Insurance Name" maxlength="50" size="20">
            <br />

            <label for="insuranceidentification_${status.count}">Membership or ID Number</label>
            <input type="text" id="insuranceidentification_${status.count}" name="insuranceidentification_${status.count}" value="${ insurance.insuranceIdentification }" title="Insurance Identification" maxlength="50" size="20">
            <br />

            <label for="groupnumber_${status.count}">Group Number</label>
            <input type="text" id="groupnumber_${status.count}" name="groupnumber_${status.count}" value="${ insurance.groupNumber }" title="Group Number" maxlength="50" size="20">
            <br />

            <label for="claimsaddress_${status.count}">Claims Address</label>
            <input type="text" id="claimsaddress_${status.count}" name="claimsaddress_${status.count}" value="${ insurance.claimsAddress }" title="Claims Address" maxlength="200" size="20">
            <br />

            <label class="indent" for="claimsaddress2_${status.count}">Claims Address 2</label>
            <input type="text" id="claimsaddress2_${status.count}" name="claimsaddress2_${status.count}" value="${ insurance.claimsAddress2 }" title="Address Line 2" maxlength="200" size="20">
            <br />

            <label class="indent" for="claimscity_${status.count}">City</label>
            <input type="text" id="claimscity_${status.count}" name="claimscity_${status.count}" value="${ insurance.claimsCity }" title="City" maxlength="100" size="20">
            <br />

            <label class="indent" for="claimsstate_${status.count}">State</label>
            <select id="claimsstate_${status.count}" name="claimsstate_${status.count}" title="State">
                <option value="">- Select One -</option>
                <option value="AL" <c:if test="${ insurance.claimsState == 'AL' }">selected</c:if> >Alabama</option>
                <option value="AK" <c:if test="${ insurance.claimsState == 'AK' }">selected</c:if> >Alaska</option>
                <option value="AZ" <c:if test="${ insurance.claimsState == 'AZ' }">selected</c:if> >Arizona</option>
                <option value="AR" <c:if test="${ insurance.claimsState == 'AR' }">selected</c:if> >Arkansas</option>
                <option value="CA" <c:if test="${ insurance.claimsState == 'CA' }">selected</c:if> >California</option>
                <option value="CO" <c:if test="${ insurance.claimsState == 'CO' }">selected</c:if> >Colorado</option>
                <option value="CT" <c:if test="${ insurance.claimsState == 'CT' }">selected</c:if> >Connecticut</option>
                <option value="DE" <c:if test="${ insurance.claimsState == 'DE' }">selected</c:if> >Delaware</option>
                <option value="DC" <c:if test="${ insurance.claimsState == 'DC' }">selected</c:if> >District of Columbia</option>
                <option value="FL" <c:if test="${ insurance.claimsState == 'FL' }">selected</c:if> >Florida</option>
                <option value="GA" <c:if test="${ insurance.claimsState == 'GA' }">selected</c:if> >Georgia</option>
                <option value="HI" <c:if test="${ insurance.claimsState == 'HI' }">selected</c:if> >Hawaii</option>
                <option value="ID" <c:if test="${ insurance.claimsState == 'ID' }">selected</c:if> >Idaho</option>
                <option value="IL" <c:if test="${ insurance.claimsState == 'IL' }">selected</c:if> >Illinois</option>
                <option value="IN" <c:if test="${ insurance.claimsState == 'IN' }">selected</c:if> >Indiana</option>
                <option value="IA" <c:if test="${ insurance.claimsState == 'IA' }">selected</c:if> >Iowa</option>
                <option value="KS" <c:if test="${ insurance.claimsState == 'KS' }">selected</c:if> >Kansas</option>
                <option value="KY" <c:if test="${ insurance.claimsState == 'KY' }">selected</c:if> >Kentucky</option>
                <option value="LA" <c:if test="${ insurance.claimsState == 'LA' }">selected</c:if> >Louisiana</option>
                <option value="ME" <c:if test="${ insurance.claimsState == 'ME' }">selected</c:if> >Maine</option>
                <option value="MD" <c:if test="${ insurance.claimsState == 'MD' }">selected</c:if> >Maryland</option>
                <option value="MA" <c:if test="${ insurance.claimsState == 'MA' }">selected</c:if> >Massachusetts</option>
                <option value="MI" <c:if test="${ insurance.claimsState == 'MI' }">selected</c:if> >Michigan</option>
                <option value="MN" <c:if test="${ insurance.claimsState == 'MN' }">selected</c:if> >Minnesota</option>
                <option value="MS" <c:if test="${ insurance.claimsState == 'MS' }">selected</c:if> >Mississippi</option>
                <option value="MO" <c:if test="${ insurance.claimsState == 'MO' }">selected</c:if> >Missouri</option>
                <option value="MT" <c:if test="${ insurance.claimsState == 'MT' }">selected</c:if> >Montana</option>
                <option value="NE" <c:if test="${ insurance.claimsState == 'NE' }">selected</c:if> >Nebraska</option>
                <option value="NV" <c:if test="${ insurance.claimsState == 'NV' }">selected</c:if> >Nevada</option>
                <option value="NH" <c:if test="${ insurance.claimsState == 'NH' }">selected</c:if> >New Hampshire</option>
                <option value="NJ" <c:if test="${ insurance.claimsState == 'NJ' }">selected</c:if> >New Jersey</option>
                <option value="NM" <c:if test="${ insurance.claimsState == 'NM' }">selected</c:if> >New Mexico</option>
                <option value="NY" <c:if test="${ insurance.claimsState == 'NY' }">selected</c:if> >New York</option>
                <option value="NC" <c:if test="${ insurance.claimsState == 'NC' }">selected</c:if> >North Carolina</option>
                <option value="ND" <c:if test="${ insurance.claimsState == 'ND' }">selected</c:if> >North Dakota</option>
                <option value="OH" <c:if test="${ insurance.claimsState == 'OH' }">selected</c:if> >Ohio</option>
                <option value="OK" <c:if test="${ insurance.claimsState == 'OK' }">selected</c:if> >Oklahoma</option>
                <option value="OR" <c:if test="${ insurance.claimsState == 'OR' }">selected</c:if> >Oregon</option>
                <option value="PA" <c:if test="${ insurance.claimsState == 'PA' }">selected</c:if> >Pennsylvania</option>
                <option value="RI" <c:if test="${ insurance.claimsState == 'RI' }">selected</c:if> >Rhode Island</option>
                <option value="SC" <c:if test="${ insurance.claimsState == 'SC' }">selected</c:if> >South Carolina</option>
                <option value="SD" <c:if test="${ insurance.claimsState == 'SD' }">selected</c:if> >South Dakota</option>
                <option value="TN" <c:if test="${ insurance.claimsState == 'TN' }">selected</c:if> >Tennessee</option>
                <option value="TX" <c:if test="${ insurance.claimsState == 'TX' }">selected</c:if> >Texas</option>
                <option value="UT" <c:if test="${ insurance.claimsState == 'UT' }">selected</c:if> >Utah</option>
                <option value="VT" <c:if test="${ insurance.claimsState == 'VT' }">selected</c:if> >Vermont</option>
                <option value="VA" <c:if test="${ insurance.claimsState == 'VA' }">selected</c:if> >Virginia</option>
                <option value="WA" <c:if test="${ insurance.claimsState == 'WA' }">selected</c:if> >Washington</option>
                <option value="WV" <c:if test="${ insurance.claimsState == 'WV' }">selected</c:if> >West Virginia</option>
                <option value="WI" <c:if test="${ insurance.claimsState == 'WI' }">selected</c:if> >Wisconsin</option>
                <option value="WY" <c:if test="${ insurance.claimsState == 'WY' }">selected</c:if> >Wyoming</option>
            </select>
            <br />

            <label for="benefitsnumber_${status.count}">Benefits Phone Number</label>
            <input type="text" id="benefitsnumber_${status.count}" name="benefitsnumber_${status.count}" value="${ insurance.benefitsNumber }" title="Benefits Phone Number" maxlength="25" size="20" class="benefitsnumber">
            <br />

            <label for="preauthorizationnumber_${status.count}">Preauthorization Phone Number</label>
            <input type="text" id="preauthorizationnumber_${status.count}" name="preauthorizationnumber_${status.count}" value="${ insurance.preauthorizationNumber }" title="Preauthorization Phone Number" maxlength="25" size="20" class="preauthorizationnumber">
            <br />

            <label for="preadmissionnumber_${status.count}">Preadmission Phone Number</label>
            <input type="text" id="preadmissionnumber_${status.count}" name="preadmissionnumber_${status.count}" value="${ insurance.preadmissionNumber }" title="Preadmission Phone Number" maxlength="25" size="20" class="preadmissionnumber">
            <br />

            <label for="faxnumber_${status.count}">Fax Number</label>
            <input type="text" id="faxnumber_${status.count}" name="faxnumber_${status.count}" value="${ insurance.faxNumber }" title="Fax Number" maxlength="25" size="20" class="faxnumber">
            <br />

            <label for="insuranceemail_${status.count}">Insurance Email Address</label>
            <input type="text" id="insuranceemail_${status.count}" name="insuranceemail_${status.count}" value="${ insurance.insuranceEmail }" title="Insurance Email Address" maxlength="100" size="20">
            <br />

            <label for="comments_${status.count}">Additional comments or notes:</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="comments_${status.count}" name="comments_${status.count}" rows="4" cols="20">${ insurance.comments }</textarea>
            <br />

            <br />
            <br />

            </div>
        </c:forEach>
    </c:if>
        
        <div class="add">
            <a title="Add Another Insurance" id="addForm" href="#">Add Another Insurance</a>
        </div>
        <div style="clear: both"></div><br />
        
        <input type="submit" value="Save" title="Save" name="submitform" id="submitform" />
        <input type="reset" value="Reset" />
    </form>
            
 <%@ include file="/includes/form_duplicator.jspf" %>
</body>