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
            <input type="text" id="ambulancenumber" name="ambulancenumber" value="${ it.ambulanceNumber }" title="Ambulance Phone Number" size="20" maxlength="32" />
            <br />

            <label for="physicianname">Primary Care Physician Name</label>
            <input type="text" id="physicianname" name="physicianname" value="${ it.physicianName }" title="Primary Care Physician Name" maxlength="64" size="20">
            <br />

            <label class="indent" for="physiciannumber">Phone Number</label>
            <input type="text" id="physiciannumber" name="physiciannumber" value="${ it.physicianNumber }" title="Physician Phone Number" size="20" maxlength="32" />
            <br />

            <label for="firenumber">Fire Dept. Phone Number</label>
            <input type="text" id="firenumber" name="firenumber" value="${ it.fireNumber }" title="Fire Dept Phone Number" size="20" maxlength="32" />
            <br />

            <label for="policenumber">Police Dept. Phone Number</label>
            <input type="text" id="policenumber" name="policenumber" value="${ it.policeNumber }" title="Police Dept Phone Number" size="20" maxlength="32" />
            <br />

            <label for="poisonnumber">Poison Control Phone Number</label>
            <input type="text" id="poisonnumber" name="poisonnumber" value="${ it.poisonNumber }" title="Poison Control Phone Number" size="20" maxlength="32" />
            <br />
            <br />

        <h2>Contacts</h2>
            <br />

            <label for="fathername">Father Name</label>
            <input type="text" id="fathername" name="fathername" value="${ it.fatherName }" title="Father Name" maxlength="64" size="20">
            <br />

            <label class="indent" for="fathernumber">Phone Number</label>
            <input type="text" id="fathernumber" name="fathernumber" value="${ it.fatherNumber }" title="Father Phone Number" size="20" maxlength="32" />
            <br />

            <label for="mothername">Mother Name</label>
            <input type="text" id="mothername" name="mothername" value="${ it.motherName }" title="Mother Name" maxlength="64" size="20">
            <br />

            <label class="indent" for="mothernumber">Phone Number</label>
            <input type="text" id="mothernumber" name="mothernumber" value="${ it.motherNumber }" title="Mother Phone Number" size="20" maxlength="32" />
            <br />

            <label for="emergencycontact">Emergency Contact Name</label>
            <input type="text" id="emergencycontact" name="emergencycontact" value="${ it.emergencyContactName }" title="Emergency Contact Name" maxlength="64" size="20">
            <br />

            <label class="indent" for="contactrelationship">Relationship</label>
            <input type="text" id="contactrelationship" name="contactrelationship" value="${ it.emergencyContactRelationship }" title="Emergency Contact Relationship" maxlength="64" size="20">
            <br />

            <label class="indent" for="emergencynumber">Phone Number</label>
            <input type="text" id="emergencynumber" name="emergencynumber" value="${ it.emergencyContactNumber }" title="Emergency Phone Number" size="20" maxlength="32" />
            <br />

            <label for="hospitalname">Primary Hospital Name</label>
            <input type="text" id="hospitalname" name="hospitalname" value="${ it.hospitalName }" title="Hospital Name" maxlength="64" size="20">
            <br />

            <label class="indent" for="hospitalnumber">Phone Number</label>
            <input type="text" id="hospitalnumber" name="hospitalnumber" value="${ it.hospitalNumber }" title="Hospital Phone Number" size="20" maxlength="32" />
            <br />

            <label class="indent" for="hospitaladdress">Address</label>
            <input type="text" id="hospitaladdress" name="hospitaladdress" value="${ it.hospitalAddress }" title="Hospital Address" maxlength="64" size="20">
            <br />

            <label class="indent" for="hospitaladdress2">Address 2</label>
            <input type="text" id="hospitaladdress2" name="hospitaladdress2" value="${ it.hospitalAddress2 }" title="Address Line 2" maxlength="64" size="20">
            <br />

            <label class="indent" for="hospitalcity">City</label>
            <input type="text" id="hospitalcity" name="hospitalcity" value="${ it.hospitalCity }" title="City" maxlength="64" size="20">
            <br />

            <label class="indent" for="hospitalstate">State</label>
            <select id="hospitalstate" name="hospitalstate" title="State">
                <option value="">- Select One -</option>
                <option value="AL" <c:if test="${ it.hospitalState == 'AL' }">selected</c:if> >Alabama</option>
                <option value="AK" <c:if test="${ it.hospitalState == 'AK' }">selected</c:if> >Alaska</option>
                <option value="AZ" <c:if test="${ it.hospitalState == 'AZ' }">selected</c:if> >Arizona</option>
                <option value="AR" <c:if test="${ it.hospitalState == 'AR' }">selected</c:if> >Arkansas</option>
                <option value="CA" <c:if test="${ it.hospitalState == 'CA' }">selected</c:if> >California</option>
                <option value="CO" <c:if test="${ it.hospitalState == 'CO' }">selected</c:if> >Colorado</option>
                <option value="CT" <c:if test="${ it.hospitalState == 'CT' }">selected</c:if> >Connecticut</option>
                <option value="DE" <c:if test="${ it.hospitalState == 'DE' }">selected</c:if> >Delaware</option>
                <option value="DC" <c:if test="${ it.hospitalState == 'DC' }">selected</c:if> >District of Columbia</option>
                <option value="FL" <c:if test="${ it.hospitalState == 'FL' }">selected</c:if> >Florida</option>
                <option value="GA" <c:if test="${ it.hospitalState == 'GA' }">selected</c:if> >Georgia</option>
                <option value="HI" <c:if test="${ it.hospitalState == 'HI' }">selected</c:if> >Hawaii</option>
                <option value="ID" <c:if test="${ it.hospitalState == 'ID' }">selected</c:if> >Idaho</option>
                <option value="IL" <c:if test="${ it.hospitalState == 'IL' }">selected</c:if> >Illinois</option>
                <option value="IN" <c:if test="${ it.hospitalState == 'IN' }">selected</c:if> >Indiana</option>
                <option value="IA" <c:if test="${ it.hospitalState == 'IA' }">selected</c:if> >Iowa</option>
                <option value="KS" <c:if test="${ it.hospitalState == 'KS' }">selected</c:if> >Kansas</option>
                <option value="KY" <c:if test="${ it.hospitalState == 'KY' }">selected</c:if> >Kentucky</option>
                <option value="LA" <c:if test="${ it.hospitalState == 'LA' }">selected</c:if> >Louisiana</option>
                <option value="ME" <c:if test="${ it.hospitalState == 'ME' }">selected</c:if> >Maine</option>
                <option value="MD" <c:if test="${ it.hospitalState == 'MD' }">selected</c:if> >Maryland</option>
                <option value="MA" <c:if test="${ it.hospitalState == 'MA' }">selected</c:if> >Massachusetts</option>
                <option value="MI" <c:if test="${ it.hospitalState == 'MI' }">selected</c:if> >Michigan</option>
                <option value="MN" <c:if test="${ it.hospitalState == 'MN' }">selected</c:if> >Minnesota</option>
                <option value="MS" <c:if test="${ it.hospitalState == 'MS' }">selected</c:if> >Mississippi</option>
                <option value="MO" <c:if test="${ it.hospitalState == 'MO' }">selected</c:if> >Missouri</option>
                <option value="MT" <c:if test="${ it.hospitalState == 'MT' }">selected</c:if> >Montana</option>
                <option value="NE" <c:if test="${ it.hospitalState == 'NE' }">selected</c:if> >Nebraska</option>
                <option value="NV" <c:if test="${ it.hospitalState == 'NV' }">selected</c:if> >Nevada</option>
                <option value="NH" <c:if test="${ it.hospitalState == 'NH' }">selected</c:if> >New Hampshire</option>
                <option value="NJ" <c:if test="${ it.hospitalState == 'NJ' }">selected</c:if> >New Jersey</option>
                <option value="NM" <c:if test="${ it.hospitalState == 'NM' }">selected</c:if> >New Mexico</option>
                <option value="NY" <c:if test="${ it.hospitalState == 'NY' }">selected</c:if> >New York</option>
                <option value="NC" <c:if test="${ it.hospitalState == 'NC' }">selected</c:if> >North Carolina</option>
                <option value="ND" <c:if test="${ it.hospitalState == 'ND' }">selected</c:if> >North Dakota</option>
                <option value="OH" <c:if test="${ it.hospitalState == 'OH' }">selected</c:if> >Ohio</option>
                <option value="OK" <c:if test="${ it.hospitalState == 'OK' }">selected</c:if> >Oklahoma</option>
                <option value="OR" <c:if test="${ it.hospitalState == 'OR' }">selected</c:if> >Oregon</option>
                <option value="PA" <c:if test="${ it.hospitalState == 'PA' }">selected</c:if> >Pennsylvania</option>
                <option value="RI" <c:if test="${ it.hospitalState == 'RI' }">selected</c:if> >Rhode Island</option>
                <option value="SC" <c:if test="${ it.hospitalState == 'SC' }">selected</c:if> >South Carolina</option>
                <option value="SD" <c:if test="${ it.hospitalState == 'SD' }">selected</c:if> >South Dakota</option>
                <option value="TN" <c:if test="${ it.hospitalState == 'TN' }">selected</c:if> >Tennessee</option>
                <option value="TX" <c:if test="${ it.hospitalState == 'TX' }">selected</c:if> >Texas</option>
                <option value="UT" <c:if test="${ it.hospitalState == 'UT' }">selected</c:if> >Utah</option>
                <option value="VT" <c:if test="${ it.hospitalState == 'VT' }">selected</c:if> >Vermont</option>
                <option value="VA" <c:if test="${ it.hospitalState == 'VA' }">selected</c:if> >Virginia</option>
                <option value="WA" <c:if test="${ it.hospitalState == 'WA' }">selected</c:if> >Washington</option>
                <option value="WV" <c:if test="${ it.hospitalState == 'WV' }">selected</c:if> >West Virginia</option>
                <option value="WI" <c:if test="${ it.hospitalState == 'WI' }">selected</c:if> >Wisconsin</option>
                <option value="WY" <c:if test="${ it.hospitalState == 'WY' }">selected</c:if> >Wyoming</option>
            </select>
            <br />

            <label for="specialtyname">Specialty Doctor Name</label>
            <input type="text" id="specialtyname" name="specialtyname" value="${ it.specialtyName }" title="Specialty Doctor Name" maxlength="64" size="20">
            <br />

            <label class="indent" for="specialtynumber">Phone Number</label>
            <input type="text" id="specialtynumber" name="specialtynumber" value="${ it.specialtyNumber }" title="Specialty Doctor Phone Number" size="20" maxlength="32" />
            <br />

            <label class="indent" for="specialtytype">Specialty</label>
            <input type="text" id="specialtytype" name="specialtytype" value="${ it.specialtyType }" title="Specialty Type" maxlength="64" size="20">
<%--            <br />
            <div class="add">
                <a href="#" title="Add Specialty Doctor">Add another Specialty Doctor</a>
            </div>
            <br />--%>
            <br />
            <br />

            <h2>If your child has had a medical emergency in the past</h2>
            <br />

            <label for="emergencydescription">What was the emergency?</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="emergencydescription" name="emergencydescription" rows="4" cols="20">${ it.emergencyDescription }</textarea>
            <br />
            <br />

            <label for="treatmentdescription">What worked best to treat it?</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="treatmentdescription" name="treatmentdescription" rows="4" cols="20">${ it.treatmentDescription }</textarea>
            <br />

            <label for="comments">Additional comments or notes:</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="comments" name="comments" rows="4" cols="20">${ it.comments }</textarea>
            <br />


            <br />
            <br />
            <input type="submit" value="Save" title="Save" name="submitform" id="submitform" />
            <input type="reset" value="Reset" />
        </form>
</body>