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
    <title>HealtheMe - Care Notebook - Emergency Information</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/carenotebook.css" />
    <script type="text/javascript" src="${ctx_static}/js/validate.emergencyinformation.js"></script>
    <script language="javascript">
        //set globals
        var ctx_static = "${ctx_static}";
        var patientId = ${it.healthRecordId};
    </script>
</head>
<c:set var="ei" value="${it.emergencyInformation}" />
<c:set var="fm" value="${it.familyMembers}" />
<c:set var="cp" value="${it.careProviders}" />
<body id="medicalinformation-em" class="medicalinformation">

    <form class="carenotebook" name="emergencyinformation" id="emergencyinformationform" action="${ctx}/form/care/${ it.healthRecordId }/emergencyinformation/post/" method="post" enctype="multipart/form-data">

        <h1>Medical Information</h1>

        <div class="def-container">
            <div class="def-title">Purpose</div>
            This section is meant to provide you with a place to record the number that you or someone else might need to call in case of an emergency. Updated medical information that you record will help to make sure that your child receives proper medical treatment.
        </div>
        <br />
        <h2>Emergency Telephone Numbers</h2>
            <br />

            <label for="ambulancenumber">Ambulance Phone Number</label>
            <input type="text" id="ambulancenumber" name="ambulancenumber" value="${ ei.ambulanceNumber }" title="Ambulance Phone Number" size="20" maxlength="32" />
            <br />

            <label for="firenumber">Fire Dept. Phone Number</label>
            <input type="text" id="firenumber" name="firenumber" value="${ ei.fireNumber }" title="Fire Dept Phone Number" size="20" maxlength="32" />
            <br />

            <label for="policenumber">Police Dept. Phone Number</label>
            <input type="text" id="policenumber" name="policenumber" value="${ ei.policeNumber }" title="Police Dept Phone Number" size="20" maxlength="32" />
            <br />

            <label for="poisonnumber">Poison Control Phone Number</label>
            <input type="text" id="poisonnumber" name="poisonnumber" value="${ ei.poisonNumber }" title="Poison Control Phone Number" size="20" maxlength="32" />
            <br />

            <label for="physicianname">Primary Care Physician Name</label>
            <input type="text" id="physicianname" name="physicianname" value="${ cp.primaryCareProviderName }" title="Primary Care Physician Name" maxlength="64" size="20" disabled>
            <br />

            <label class="indent" for="physiciannumber">Phone Number</label>
            <input type="text" id="physiciannumber" name="physiciannumber" value="${ cp.primaryCareProviderNumber }" title="Primary Care Physician Phone Number" size="20" maxlength="32" disabled/>

            <br />
            <br />

        <h2>Contacts</h2>
            <br />

            <label for="fathername">Father Name</label>
            <input type="text" id="fathername" name="fathername" value="${ fm.fatherName }" title="Father Name" maxlength="64" size="20" disabled>
            <br />

            <label class="indent" for="fathernumber">Phone Number</label>
            <input type="text" id="fathernumber" name="fathernumber" value="${ fm.fatherNumber }" title="Father Phone Number" size="20" maxlength="32" disabled/>
            <br />

            <label for="mothername">Mother Name</label>
            <input type="text" id="mothername" name="mothername" value="${ fm.motherName }" title="Mother Name" maxlength="64" size="20" disabled>
            <br />

            <label class="indent" for="mothernumber">Phone Number</label>
            <input type="text" id="mothernumber" name="mothernumber" value="${ fm.motherNumber }" title="Mother Phone Number" size="20" maxlength="32" disabled/>
            <br />

            <label for="emergencycontact">Emergency Contact Name</label>
            <input type="text" id="emergencycontact" name="emergencycontact" value="${ fm.emergencyContactName }" title="Emergency Contact Name" maxlength="64" size="20" disabled>
            <br />

            <label class="indent" for="contactrelationship">Relationship</label>
            <input type="text" id="contactrelationship" name="contactrelationship" value="${ fm.emergencyContactRelationship }" title="Emergency Contact Relationship" maxlength="64" size="20" disabled>
            <br />

            <label class="indent" for="emergencydaytimenumber">Daytime Phone Number</label>
            <input type="text" id="emergencynumber" name="emergencynumber" value="${ fm.emergencyContactDaytimeNumber }" title="Daytime Emergency Phone Number" size="20" maxlength="32" disabled/>
            <br />

            <label class="indent" for="emergencyeveningnumber">Evening Phone Number</label>
            <input type="text" id="emergencynumber" name="emergencynumber" value="${ fm.emergencyContactEveningNumber }" title="Evening Emergency Phone Number" size="20" maxlength="32" disabled/>
            <br />

            <label for="hospitalname">Primary Hospital Name</label>
            <input type="text" id="hospitalname" name="hospitalname" value="${ ei.hospitalName }" title="Hospital Name" maxlength="64" size="20">
            <br />

            <label class="indent" for="hospitalnumber">Phone Number</label>
            <input type="text" id="hospitalnumber" name="hospitalnumber" value="${ ei.hospitalNumber }" title="Hospital Phone Number" size="20" maxlength="32" />
            <br />

            <label class="indent" for="hospitaladdress">Address</label>
            <input type="text" id="hospitaladdress" name="hospitaladdress" value="${ ei.hospitalAddress }" title="Hospital Address" maxlength="64" size="20">
            <br />

            <label class="indent" for="hospitaladdress2">Address 2</label>
            <input type="text" id="hospitaladdress2" name="hospitaladdress2" value="${ ei.hospitalAddress2 }" title="Address Line 2" maxlength="64" size="20">
            <br />

            <label class="indent" for="hospitalcity">City</label>
            <input type="text" id="hospitalcity" name="hospitalcity" value="${ ei.hospitalCity }" title="City" maxlength="64" size="20">
            <br />

            <label class="indent" for="hospitalstate">State</label>
            <select id="hospitalstate" name="hospitalstate" title="State">
                <option value="">- Select One -</option>
                <option value="AL" <c:if test="${ ei.hospitalState == 'AL' }">selected</c:if> >Alabama</option>
                <option value="AK" <c:if test="${ ei.hospitalState == 'AK' }">selected</c:if> >Alaska</option>
                <option value="AZ" <c:if test="${ ei.hospitalState == 'AZ' }">selected</c:if> >Arizona</option>
                <option value="AR" <c:if test="${ ei.hospitalState == 'AR' }">selected</c:if> >Arkansas</option>
                <option value="CA" <c:if test="${ ei.hospitalState == 'CA' }">selected</c:if> >California</option>
                <option value="CO" <c:if test="${ ei.hospitalState == 'CO' }">selected</c:if> >Colorado</option>
                <option value="CT" <c:if test="${ ei.hospitalState == 'CT' }">selected</c:if> >Connecticut</option>
                <option value="DE" <c:if test="${ ei.hospitalState == 'DE' }">selected</c:if> >Delaware</option>
                <option value="DC" <c:if test="${ ei.hospitalState == 'DC' }">selected</c:if> >District of Columbia</option>
                <option value="FL" <c:if test="${ ei.hospitalState == 'FL' }">selected</c:if> >Florida</option>
                <option value="GA" <c:if test="${ ei.hospitalState == 'GA' }">selected</c:if> >Georgia</option>
                <option value="HI" <c:if test="${ ei.hospitalState == 'HI' }">selected</c:if> >Hawaii</option>
                <option value="ID" <c:if test="${ ei.hospitalState == 'ID' }">selected</c:if> >Idaho</option>
                <option value="IL" <c:if test="${ ei.hospitalState == 'IL' }">selected</c:if> >Illinois</option>
                <option value="IN" <c:if test="${ ei.hospitalState == 'IN' }">selected</c:if> >Indiana</option>
                <option value="IA" <c:if test="${ ei.hospitalState == 'IA' }">selected</c:if> >Iowa</option>
                <option value="KS" <c:if test="${ ei.hospitalState == 'KS' }">selected</c:if> >Kansas</option>
                <option value="KY" <c:if test="${ ei.hospitalState == 'KY' }">selected</c:if> >Kentucky</option>
                <option value="LA" <c:if test="${ ei.hospitalState == 'LA' }">selected</c:if> >Louisiana</option>
                <option value="ME" <c:if test="${ ei.hospitalState == 'ME' }">selected</c:if> >Maine</option>
                <option value="MD" <c:if test="${ ei.hospitalState == 'MD' }">selected</c:if> >Maryland</option>
                <option value="MA" <c:if test="${ ei.hospitalState == 'MA' }">selected</c:if> >Massachusetts</option>
                <option value="MI" <c:if test="${ ei.hospitalState == 'MI' }">selected</c:if> >Michigan</option>
                <option value="MN" <c:if test="${ ei.hospitalState == 'MN' }">selected</c:if> >Minnesota</option>
                <option value="MS" <c:if test="${ ei.hospitalState == 'MS' }">selected</c:if> >Mississippi</option>
                <option value="MO" <c:if test="${ ei.hospitalState == 'MO' }">selected</c:if> >Missouri</option>
                <option value="MT" <c:if test="${ ei.hospitalState == 'MT' }">selected</c:if> >Montana</option>
                <option value="NE" <c:if test="${ ei.hospitalState == 'NE' }">selected</c:if> >Nebraska</option>
                <option value="NV" <c:if test="${ ei.hospitalState == 'NV' }">selected</c:if> >Nevada</option>
                <option value="NH" <c:if test="${ ei.hospitalState == 'NH' }">selected</c:if> >New Hampshire</option>
                <option value="NJ" <c:if test="${ ei.hospitalState == 'NJ' }">selected</c:if> >New Jersey</option>
                <option value="NM" <c:if test="${ ei.hospitalState == 'NM' }">selected</c:if> >New Mexico</option>
                <option value="NY" <c:if test="${ ei.hospitalState == 'NY' }">selected</c:if> >New York</option>
                <option value="NC" <c:if test="${ ei.hospitalState == 'NC' }">selected</c:if> >North Carolina</option>
                <option value="ND" <c:if test="${ ei.hospitalState == 'ND' }">selected</c:if> >North Dakota</option>
                <option value="OH" <c:if test="${ ei.hospitalState == 'OH' }">selected</c:if> >Ohio</option>
                <option value="OK" <c:if test="${ ei.hospitalState == 'OK' }">selected</c:if> >Oklahoma</option>
                <option value="OR" <c:if test="${ ei.hospitalState == 'OR' }">selected</c:if> >Oregon</option>
                <option value="PA" <c:if test="${ ei.hospitalState == 'PA' }">selected</c:if> >Pennsylvania</option>
                <option value="RI" <c:if test="${ ei.hospitalState == 'RI' }">selected</c:if> >Rhode Island</option>
                <option value="SC" <c:if test="${ ei.hospitalState == 'SC' }">selected</c:if> >South Carolina</option>
                <option value="SD" <c:if test="${ ei.hospitalState == 'SD' }">selected</c:if> >South Dakota</option>
                <option value="TN" <c:if test="${ ei.hospitalState == 'TN' }">selected</c:if> >Tennessee</option>
                <option value="TX" <c:if test="${ ei.hospitalState == 'TX' }">selected</c:if> >Texas</option>
                <option value="UT" <c:if test="${ ei.hospitalState == 'UT' }">selected</c:if> >Utah</option>
                <option value="VT" <c:if test="${ ei.hospitalState == 'VT' }">selected</c:if> >Vermont</option>
                <option value="VA" <c:if test="${ ei.hospitalState == 'VA' }">selected</c:if> >Virginia</option>
                <option value="WA" <c:if test="${ ei.hospitalState == 'WA' }">selected</c:if> >Washington</option>
                <option value="WV" <c:if test="${ ei.hospitalState == 'WV' }">selected</c:if> >West Virginia</option>
                <option value="WI" <c:if test="${ ei.hospitalState == 'WI' }">selected</c:if> >Wisconsin</option>
                <option value="WY" <c:if test="${ ei.hospitalState == 'WY' }">selected</c:if> >Wyoming</option>
            </select>
            <br />

			<c:forEach items="${cp.providers}" var="provider" varStatus="status">
            <label for="specialtyname${status.count}">Specialty Doctor Name</label>
            <input type="text" class="specialtyname" name="specialtyname${status.count}" value="${ provider.providerName }" title="Specialty Doctor Name" maxlength="64" size="20" disabled>
            <br />

            <label class="indent" for="specialtynumber${status.count}">Phone Number</label>
            <input type="text" class="specialtynumber" name="specialtynumber${status.count}" value="${ provider.phoneNumber }" title="Specialty Doctor Phone Number" size="20" maxlength="32" disabled/>
            <br />

            <label class="indent" for="specialtytype${status.count}">Specialty</label>
            <input type="text" class="specialtytype" name="specialtytype${status.count}" value="${ provider.providerType }" title="Specialty Type" maxlength="64" size="20" disabled>
            <br />
			</c:forEach>
            <br />

			<h2>Medical Emergency Information</h2><br />
			Please use the <a href="${ctx_patient}/${ it.healthRecordId }/medicalevents">Medical Events</a> section to record a history of previous medical emergencies.<br />
			Be sure to save any changes made to this section by clicking the Save button below before leaving.<br />

            <br />
            <br />

            <label for="comments">Additional comments or notes:</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="comments" name="comments" rows="4" cols="20">${ ei.comments }</textarea>
            <br />

            <br />
            <br />
            <input type="submit" value="Save" title="Save" name="submitform" id="submitform" />
            <input type="reset" value="Reset" />
        </form>
</body>