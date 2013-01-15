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
    Created on : Mar 22, 2012, 3:23:01 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>

<html>
<head>
    <title>HealtheMe - Care Notebook - Care Providers</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/carenotebook.css" />
    <script type="text/javascript" src="${ctx_static}/js/validate.careprovider.js"></script>
    <script language="javascript">
        //set globals
        var ctx_static = "${ctx_static}";
        var patientId = ${it.healthRecordId};
    </script>
</head>
<body id="providers">

        <h1>Care Providers</h1>

        <div class="def-container">
            <div class="def-title">Purpose</div>
            This section is meant to provide you with a place to record all of the specialty care providers' contact information for which you may be a patient. Updated medical information that you record will help to make sure that your child receives proper medical treatment.
        </div>
        <br />

    <form class="careproviderform carenotebook" action="${ctx}/form/care/${ it.healthRecordId }/careproviders/post/" method="post" enctype="application/x-www-form-urlencoded">
        

    <c:if test="${it.class.simpleName == 'CareProviders'}">
        <c:forEach items="${it.providers}" var="provider" varStatus="status">

            <c:choose>
            <c:when test="${status.count == 1 }">
                <div id="formfieldcontainer" class="addedDiv">
            </c:when>
            <c:otherwise>
                <hr /><br /><div class="addedDiv">
            </c:otherwise>
            </c:choose>

            <input type="hidden" id="providerId_${status.count}" name="providerId_${status.count}" value="${ provider.providerId }" />
            
<!--            <label for="providerType_${status.count}">Provider Type</label>
            <input type="text" id="providerType_${status.count}" name="providerType_${status.count}" value="${ provider.providerType }" title="Provider Type" maxlength="50" size="20">
            <br />-->
            
            <label for="providerType_${status.count}">Provider Type</label>
            <select id="providerType_${status.count}" name="providerType_${status.count}" value="${ provider.providerType }" title="Provider Type">
                <option value="">- Select One -</option>
				<option value="AI" <c:if test="${ provider.providerType == 'AI' }">selected</c:if> >Allergy and Immunology</option>
				<option value="CAR" <c:if test="${ provider.providerType == 'CAR' }">selected</c:if> >Cardiology</option>
				<option value="CAPP" <c:if test="${ provider.providerType == 'CAPP' }">selected</c:if> >Child and Adolescent Psychiatry and Psychotherapy</option>
				<option value="DCP" <c:if test="${ provider.providerType == 'DCP' }">selected</c:if> >Day Care Provider</option>
				<option value="DERM" <c:if test="${ provider.providerType == 'DERM' }">selected</c:if> >Dermatology</option>
				<option value="DT" <c:if test="${ provider.providerType == 'DT' }">selected</c:if> >Developmental Therapy</option>
				<option value="EM" <c:if test="${ provider.providerType == 'EM' }">selected</c:if> >Emergency Medicine</option>
				<option value="EC" <c:if test="${ provider.providerType == 'EC' }">selected</c:if> >Endocrinology</option>
				<option value="GE" <c:if test="${ provider.providerType == 'GE' }">selected</c:if> >Gastroenterology</option>
				<option value="GS" <c:if test="${ provider.providerType == 'GS' }">selected</c:if> >General Surgery</option>
				<option value="GER" <c:if test="${ provider.providerType == 'GER' }">selected</c:if> >Geriatrics</option>
				<option value="HHP" <c:if test="${ provider.providerType == 'HHP' }">selected</c:if> >Home Health Provider</option>
				<option value="ID" <c:if test="${ provider.providerType == 'ID' }">selected</c:if> >Infectious Diseases</option>
				<option value="IM" <c:if test="${ provider.providerType == 'IM' }">selected</c:if> >Internal Medicine</option>
				<option value="IVR" <c:if test="${ provider.providerType == 'IVR' }">selected</c:if> >Interventional Radiology</option>
				<option value="IVT" <c:if test="${ provider.providerType == 'IVT' }">selected</c:if> >IV Therapy</option>
				<option value="MB" <c:if test="${ provider.providerType == 'MB' }">selected</c:if> >Microbiology</option>
				<option value="NEO" <c:if test="${ provider.providerType == 'NEO' }">selected</c:if> >Neonatology</option>
				<option value="NEP" <c:if test="${ provider.providerType == 'NEP' }">selected</c:if> >Nephrology</option>
				<option value="NEU" <c:if test="${ provider.providerType == 'NEU' }">selected</c:if> >Neurology</option>
				<option value="NER" <c:if test="${ provider.providerType == 'NER' }">selected</c:if> >Neuroradiology</option>
				<option value="NS" <c:if test="${ provider.providerType == 'NS' }">selected</c:if> >Neurosurgery</option>
				<option value="NM" <c:if test="${ provider.providerType == 'NM' }">selected</c:if> >Nuclear Medicine</option>
				<option value="OT" <c:if test="${ provider.providerType == 'OT' }">selected</c:if> >Occupational Therapist</option>
				<option value="OPH" <c:if test="${ provider.providerType == 'OPH' }">selected</c:if> >Ophthalmology</option>
				<option value="OMFS" <c:if test="${ provider.providerType == 'OMFS' }">selected</c:if> >Oro-Maxillo-Facial Surgery</option>
				<option value="ORT" <c:if test="${ provider.providerType == 'ORT' }">selected</c:if> >Orthopedics</option>
				<option value="PAT" <c:if test="${ provider.providerType == 'PAT' }">selected</c:if> >Pathology</option>
				<option value="PEDC" <c:if test="${ provider.providerType == 'PEDC' }">selected</c:if> >Pediatric Cardiology</option>
				<option value="PEDED" <c:if test="${ provider.providerType == 'PEDED' }">selected</c:if> >Pediatric Endocrinology and Diabetes</option>
				<option value="PEDGHN" <c:if test="${ provider.providerType == 'PEDGHN' }">selected</c:if> >Pediatric Gastroenterology, Hepatology, and Nutrition</option>
				<option value="PEDHO" <c:if test="${ provider.providerType == 'PEDHO' }">selected</c:if> >Pediatric Hematology and Oncology</option>
				<option value="PEDID" <c:if test="${ provider.providerType == 'PEDID' }">selected</c:if> >Pediatric Infectious Diseases</option>
				<option value="PEDN" <c:if test="${ provider.providerType == 'PEDN' }">selected</c:if> >Pediatric Nephrology</option>
				<option value="PEDRM" <c:if test="${ provider.providerType == 'PEDRM' }">selected</c:if> >Pediatric Respiratory Medicine</option>
				<option value="PEDRH" <c:if test="${ provider.providerType == 'PEDRH' }">selected</c:if> >Pediatric Rheumatology</option>
				<option value="PEDS" <c:if test="${ provider.providerType == 'PEDS' }">selected</c:if> >Pediatric Surgery</option>
				<option value="PED" <c:if test="${ provider.providerType == 'PED' }">selected</c:if> >Pediatrics</option>
				<option value="PT" <c:if test="${ provider.providerType == 'PT' }">selected</c:if> >Physical Therapy and Rehabilitation</option>
				<option value="PRAS" <c:if test="${ provider.providerType == 'PRAS' }">selected</c:if> >Plastic, Reconstructive, and Aesthetic Surgery</option>
				<option value="PRI" <c:if test="${ provider.providerType == 'PRI' }">selected</c:if> >Primary Care</option>
				<option value="PSY" <c:if test="${ provider.providerType == 'PSY' }">selected</c:if> >Psychiatry</option>
				<option value="PH" <c:if test="${ provider.providerType == 'PH' }">selected</c:if> >Public Health</option>
				<option value="PUL" <c:if test="${ provider.providerType == 'PUL' }">selected</c:if> >Pulmonology</option>
				<option value="RAD" <c:if test="${ provider.providerType == 'RAD' }">selected</c:if> >Radiology</option>
				<option value="RADT" <c:if test="${ provider.providerType == 'RADT' }">selected</c:if> >Radiotherapy</option>
				<option value="REC" <c:if test="${ provider.providerType == 'REC' }">selected</c:if> >Respiratory Care</option>
				<option value="RCP" <c:if test="${ provider.providerType == 'RCP' }">selected</c:if> >Respite Care Provider</option>
				<option value="SLP" <c:if test="${ provider.providerType == 'SLP' }">selected</c:if> >Speech Language Pathologist</option>
				<option value="TRC" <c:if test="${ provider.providerType == 'TRC' }">selected</c:if> >Transplant Care</option>
				<option value="UR" <c:if test="${ provider.providerType == 'UR' }">selected</c:if> >Urology</option>
				<option value="VM" <c:if test="${ provider.providerType == 'VM' }">selected</c:if> >Vascular Medicine</option>
				<option value="VS" <c:if test="${ provider.providerType == 'VS' }">selected</c:if> >Vascular Surgery</option>
				<option value="Other" <c:if test="${ provider.providerType == 'Other' }">selected</c:if> >Other</option>
            </select>
            <br />

            <label for="providerName_${status.count}">Provider Name</label>
            <input type="text" id="providerName_${status.count}" name="providerName_${status.count}" value="${ provider.providerName }" title="Provider Name" maxlength="50" size="20">
            <br />

            <label for="providerAgency_${status.count}">Agency (if available)</label>
            <input type="text" id="providerAgency_${status.count}" name="providerAgency_${status.count}" value="${ provider.providerAgency }" title="Provider Agency" maxlength="25" size="20">
            <br />
            
            <label for="providerContact_${status.count}">Contact Person</label>
            <input type="text" id="providerContact_${status.count}" name="providerContact_${status.count}" value="${ provider.providerContact }" title="Contact Person" maxlength="50" size="20">
            <br />
            
            <label for="providerAddress_${status.count}">Address</label>
            <input type="text" id="providerAddress_${status.count}" name="providerAddress_${status.count}" value="${ provider.providerAddress }" title="Address" maxlength="64" size="20">
            <br />

            <label for="providerAddress2_${status.count}">Address 2</label>
            <input type="text" id="providerAddress2_${status.count}" name="providerAddress2_${status.count}" value="${ provider.providerAddress2 }" title="Address Line 2" maxlength="64" size="20">
            <br />

            <label for="providerCity_${status.count}">City</label>
            <input type="text" id="providerCity_${status.count}" name="providerCity_${status.count}" value="${ provider.providerCity }" title="City" maxlength="64" size="20">
            <br />

            <label for="providerState_${status.count}">State</label>
            <select id="providerState_${status.count}" name="providerState_${status.count}" title="State">
                <option value="">- Select One -</option>
                <option value="AL" <c:if test="${ provider.providerState == 'AL' }">selected</c:if> >Alabama</option>
                <option value="AK" <c:if test="${ provider.providerState == 'AK' }">selected</c:if> >Alaska</option>
                <option value="AZ" <c:if test="${ provider.providerState == 'AZ' }">selected</c:if> >Arizona</option>
                <option value="AR" <c:if test="${ provider.providerState == 'AR' }">selected</c:if> >Arkansas</option>
                <option value="CA" <c:if test="${ provider.providerState == 'CA' }">selected</c:if> >California</option>
                <option value="CO" <c:if test="${ provider.providerState == 'CO' }">selected</c:if> >Colorado</option>
                <option value="CT" <c:if test="${ provider.providerState == 'CT' }">selected</c:if> >Connecticut</option>
                <option value="DE" <c:if test="${ provider.providerState == 'DE' }">selected</c:if> >Delaware</option>
                <option value="DC" <c:if test="${ provider.providerState == 'DC' }">selected</c:if> >District of Columbia</option>
                <option value="FL" <c:if test="${ provider.providerState == 'FL' }">selected</c:if> >Florida</option>
                <option value="GA" <c:if test="${ provider.providerState == 'GA' }">selected</c:if> >Georgia</option>
                <option value="HI" <c:if test="${ provider.providerState == 'HI' }">selected</c:if> >Hawaii</option>
                <option value="ID" <c:if test="${ provider.providerState == 'ID' }">selected</c:if> >Idaho</option>
                <option value="IL" <c:if test="${ provider.providerState == 'IL' }">selected</c:if> >Illinois</option>
                <option value="IN" <c:if test="${ provider.providerState == 'IN' }">selected</c:if> >Indiana</option>
                <option value="IA" <c:if test="${ provider.providerState == 'IA' }">selected</c:if> >Iowa</option>
                <option value="KS" <c:if test="${ provider.providerState == 'KS' }">selected</c:if> >Kansas</option>
                <option value="KY" <c:if test="${ provider.providerState == 'KY' }">selected</c:if> >Kentucky</option>
                <option value="LA" <c:if test="${ provider.providerState == 'LA' }">selected</c:if> >Louisiana</option>
                <option value="ME" <c:if test="${ provider.providerState == 'ME' }">selected</c:if> >Maine</option>
                <option value="MD" <c:if test="${ provider.providerState == 'MD' }">selected</c:if> >Maryland</option>
                <option value="MA" <c:if test="${ provider.providerState == 'MA' }">selected</c:if> >Massachusetts</option>
                <option value="MI" <c:if test="${ provider.providerState == 'MI' }">selected</c:if> >Michigan</option>
                <option value="MN" <c:if test="${ provider.providerState == 'MN' }">selected</c:if> >Minnesota</option>
                <option value="MS" <c:if test="${ provider.providerState == 'MS' }">selected</c:if> >Mississippi</option>
                <option value="MO" <c:if test="${ provider.providerState == 'MO' }">selected</c:if> >Missouri</option>
                <option value="MT" <c:if test="${ provider.providerState == 'MT' }">selected</c:if> >Montana</option>
                <option value="NE" <c:if test="${ provider.providerState == 'NE' }">selected</c:if> >Nebraska</option>
                <option value="NV" <c:if test="${ provider.providerState == 'NV' }">selected</c:if> >Nevada</option>
                <option value="NH" <c:if test="${ provider.providerState == 'NH' }">selected</c:if> >New Hampshire</option>
                <option value="NJ" <c:if test="${ provider.providerState == 'NJ' }">selected</c:if> >New Jersey</option>
                <option value="NM" <c:if test="${ provider.providerState == 'NM' }">selected</c:if> >New Mexico</option>
                <option value="NY" <c:if test="${ provider.providerState == 'NY' }">selected</c:if> >New York</option>
                <option value="NC" <c:if test="${ provider.providerState == 'NC' }">selected</c:if> >North Carolina</option>
                <option value="ND" <c:if test="${ provider.providerState == 'ND' }">selected</c:if> >North Dakota</option>
                <option value="OH" <c:if test="${ provider.providerState == 'OH' }">selected</c:if> >Ohio</option>
                <option value="OK" <c:if test="${ provider.providerState == 'OK' }">selected</c:if> >Oklahoma</option>
                <option value="OR" <c:if test="${ provider.providerState == 'OR' }">selected</c:if> >Oregon</option>
                <option value="PA" <c:if test="${ provider.providerState == 'PA' }">selected</c:if> >Pennsylvania</option>
                <option value="RI" <c:if test="${ provider.providerState == 'RI' }">selected</c:if> >Rhode Island</option>
                <option value="SC" <c:if test="${ provider.providerState == 'SC' }">selected</c:if> >South Carolina</option>
                <option value="SD" <c:if test="${ provider.providerState == 'SD' }">selected</c:if> >South Dakota</option>
                <option value="TN" <c:if test="${ provider.providerState == 'TN' }">selected</c:if> >Tennessee</option>
                <option value="TX" <c:if test="${ provider.providerState == 'TX' }">selected</c:if> >Texas</option>
                <option value="UT" <c:if test="${ provider.providerState == 'UT' }">selected</c:if> >Utah</option>
                <option value="VT" <c:if test="${ provider.providerState == 'VT' }">selected</c:if> >Vermont</option>
                <option value="VA" <c:if test="${ provider.providerState == 'VA' }">selected</c:if> >Virginia</option>
                <option value="WA" <c:if test="${ provider.providerState == 'WA' }">selected</c:if> >Washington</option>
                <option value="WV" <c:if test="${ provider.providerState == 'WV' }">selected</c:if> >West Virginia</option>
                <option value="WI" <c:if test="${ provider.providerState == 'WI' }">selected</c:if> >Wisconsin</option>
                <option value="WY" <c:if test="${ provider.providerState == 'WY' }">selected</c:if> >Wyoming</option>
            </select>
            <br />


            <label for="phonenumber_${status.count}">Phone Number</label>
            <input type="text" id="phonenumber_${status.count}" name="phonenumber_${status.count}" value="${ provider.phoneNumber }" title="Phone Number" maxlength="25" size="20" class="phonenumber">
            <br />

            <label for="faxnumber_${status.count}">Fax Number</label>
            <input type="text" id="faxnumber_${status.count}" name="faxnumber_${status.count}" value="${ provider.faxNumber }" title="Fax Number" maxlength="25" size="20" class="faxnumber">
            <br />

            <label for="providerEmail_${status.count}">Email Address</label>
            <input type="text" id="providerEmail_${status.count}" name="providerEmail_${status.count}" value="${ provider.providerEmail }" title="Email Address" maxlength="100" size="20">
            <br />

            <label for="comments_${status.count}">Additional comments or notes:</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="comments_${status.count}" name="comments_${status.count}" rows="4" cols="20">${ provider.comments }</textarea>
            <br />

            <br />
            <br />

            </div>
        </c:forEach>
    </c:if>
        
        <div class="add">
            <a title="Add Another Care Provider" id="addForm" href="#">Add Another Care Provider</a>
        </div>
        <div style="clear: both"></div><br />
        
        <input type="submit" value="Save" title="Save" name="submitform" id="submitform" />
        <input type="reset" value="Reset" />
    </form>
            
 <%@ include file="/includes/form_duplicator.jspf" %>
</body>