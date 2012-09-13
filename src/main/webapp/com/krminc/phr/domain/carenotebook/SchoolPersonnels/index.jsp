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
    Created on : Mar 27, 2012, 8:09:45 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>

<html>
<head>
    <title>HealtheMe - Care Notebook - School Personnel</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/carenotebook.css" />
    <script type="text/javascript" src="${ctx_static}/js/validate.schoolpersonnel.js"></script>
    <script language="javascript">
        //set globals
        var ctx_static = "${ctx_static}";
        var patientId = ${it.healthRecordId};
    </script>
</head>

<body id="education-sp" class="education">

        <h1>School Personnel</h1>

        <div class="def-container">
            <div class="def-title">Purpose</div>
            This section is meant to provide you with a place to record all of the contacts who may be in charge or care for your child throughout their course of study at school, day camp, or other program.
        </div>
        <br />


    <form class="schoolpersonnelform carenotebook" action="${ctx}/form/care/${ it.healthRecordId }/schoolpersonnel/post/" method="post" enctype="application/x-www-form-urlencoded">
        

    <c:if test="${it.class.simpleName == 'SchoolPersonnels'}">
        <c:forEach items="${it.schoolPersonnels}" var="personnel" varStatus="status">

            <c:choose>
            <c:when test="${status.count == 1 }">
                <div id="formfieldcontainer" class="addedDiv">
            </c:when>
            <c:otherwise>
                <hr /><br /><div class="addedDiv">
            </c:otherwise>
            </c:choose>

            <input type="hidden" id="personnelId_${status.count}" name="personnelId_${status.count}" value="${ personnel.personnelId }" />
            
            <label for="personnelType_${status.count}">Responsibility</label>
            <select id="personnelType_${status.count}" name="personnelType_${status.count}" value="${ personnel.personnelType }" title="Responsibility">
                <option value="">- Select One -</option>
                <option value="School Nurse" <c:if test="${ personnel.personnelType == 'School Nurse' }">selected</c:if> >School Nurse</option>
                <option value="Teacher" <c:if test="${ personnel.personnelType == 'Teacher' }">selected</c:if> >Teacher</option>
                <option value="Principal" <c:if test="${ personnel.personnelType == 'Principal' }">selected</c:if> >Principal</option>
                <option value="Bus Driver" <c:if test="${ personnel.personnelType == 'Bus Driver' }">selected</c:if> >Bus Driver</option>
                <option value="Bus Aide" <c:if test="${ personnel.personnelType == 'Bus Aide' }">selected</c:if> >Bus Aide</option>
                <option value="Nurse" <c:if test="${ personnel.personnelType == 'Nurse' }">selected</c:if> >Nurse</option>
                <option value="Program Supervisor" <c:if test="${ personnel.personnelType == 'Program Supervisor' }">selected</c:if> >Program Supervisor</option>
                <option value="Program Director" <c:if test="${ personnel.personnelType == 'Program Director' }">selected</c:if> >Program Director</option>
                <option value="Aide" <c:if test="${ personnel.personnelType == 'Aide' }">selected</c:if> >Aide</option>
                <option value="Other" <c:if test="${ personnel.personnelType == 'Other' }">selected</c:if> >Other</option>
            </select>
            <br />

            <label for="personnelName_${status.count}">Name</label>
            <input type="text" id="personnelName_${status.count}" name="personnelName_${status.count}" value="${ personnel.personnelName }" title="Name" maxlength="50" size="20">
            <br />
            
            <label for="personnelAddress_${status.count}">Address</label>
            <input type="text" id="personnelAddress_${status.count}" name="personnelAddress_${status.count}" value="${ personnel.personnelAddress }" title="Address" maxlength="64" size="20">
            <br />

            <label for="personnelAddress2_${status.count}">Address 2</label>
            <input type="text" id="personnelAddress2_${status.count}" name="personnelAddress2_${status.count}" value="${ personnel.personnelAddress2 }" title="Address Line 2" maxlength="64" size="20">
            <br />

            <label for="personnelCity_${status.count}">City</label>
            <input type="text" id="personnelCity_${status.count}" name="personnelCity_${status.count}" value="${ personnel.personnelCity }" title="City" maxlength="64" size="20">
            <br />

            <label for="personnelState_${status.count}">State</label>
            <select id="personnelState_${status.count}" name="personnelState_${status.count}" title="State">
                <option value="">- Select One -</option>
                <option value="AL" <c:if test="${ personnel.personnelState == 'AL' }">selected</c:if> >Alabama</option>
                <option value="AK" <c:if test="${ personnel.personnelState == 'AK' }">selected</c:if> >Alaska</option>
                <option value="AZ" <c:if test="${ personnel.personnelState == 'AZ' }">selected</c:if> >Arizona</option>
                <option value="AR" <c:if test="${ personnel.personnelState == 'AR' }">selected</c:if> >Arkansas</option>
                <option value="CA" <c:if test="${ personnel.personnelState == 'CA' }">selected</c:if> >California</option>
                <option value="CO" <c:if test="${ personnel.personnelState == 'CO' }">selected</c:if> >Colorado</option>
                <option value="CT" <c:if test="${ personnel.personnelState == 'CT' }">selected</c:if> >Connecticut</option>
                <option value="DE" <c:if test="${ personnel.personnelState == 'DE' }">selected</c:if> >Delaware</option>
                <option value="DC" <c:if test="${ personnel.personnelState == 'DC' }">selected</c:if> >District of Columbia</option>
                <option value="FL" <c:if test="${ personnel.personnelState == 'FL' }">selected</c:if> >Florida</option>
                <option value="GA" <c:if test="${ personnel.personnelState == 'GA' }">selected</c:if> >Georgia</option>
                <option value="HI" <c:if test="${ personnel.personnelState == 'HI' }">selected</c:if> >Hawaii</option>
                <option value="ID" <c:if test="${ personnel.personnelState == 'ID' }">selected</c:if> >Idaho</option>
                <option value="IL" <c:if test="${ personnel.personnelState == 'IL' }">selected</c:if> >Illinois</option>
                <option value="IN" <c:if test="${ personnel.personnelState == 'IN' }">selected</c:if> >Indiana</option>
                <option value="IA" <c:if test="${ personnel.personnelState == 'IA' }">selected</c:if> >Iowa</option>
                <option value="KS" <c:if test="${ personnel.personnelState == 'KS' }">selected</c:if> >Kansas</option>
                <option value="KY" <c:if test="${ personnel.personnelState == 'KY' }">selected</c:if> >Kentucky</option>
                <option value="LA" <c:if test="${ personnel.personnelState == 'LA' }">selected</c:if> >Louisiana</option>
                <option value="ME" <c:if test="${ personnel.personnelState == 'ME' }">selected</c:if> >Maine</option>
                <option value="MD" <c:if test="${ personnel.personnelState == 'MD' }">selected</c:if> >Maryland</option>
                <option value="MA" <c:if test="${ personnel.personnelState == 'MA' }">selected</c:if> >Massachusetts</option>
                <option value="MI" <c:if test="${ personnel.personnelState == 'MI' }">selected</c:if> >Michigan</option>
                <option value="MN" <c:if test="${ personnel.personnelState == 'MN' }">selected</c:if> >Minnesota</option>
                <option value="MS" <c:if test="${ personnel.personnelState == 'MS' }">selected</c:if> >Mississippi</option>
                <option value="MO" <c:if test="${ personnel.personnelState == 'MO' }">selected</c:if> >Missouri</option>
                <option value="MT" <c:if test="${ personnel.personnelState == 'MT' }">selected</c:if> >Montana</option>
                <option value="NE" <c:if test="${ personnel.personnelState == 'NE' }">selected</c:if> >Nebraska</option>
                <option value="NV" <c:if test="${ personnel.personnelState == 'NV' }">selected</c:if> >Nevada</option>
                <option value="NH" <c:if test="${ personnel.personnelState == 'NH' }">selected</c:if> >New Hampshire</option>
                <option value="NJ" <c:if test="${ personnel.personnelState == 'NJ' }">selected</c:if> >New Jersey</option>
                <option value="NM" <c:if test="${ personnel.personnelState == 'NM' }">selected</c:if> >New Mexico</option>
                <option value="NY" <c:if test="${ personnel.personnelState == 'NY' }">selected</c:if> >New York</option>
                <option value="NC" <c:if test="${ personnel.personnelState == 'NC' }">selected</c:if> >North Carolina</option>
                <option value="ND" <c:if test="${ personnel.personnelState == 'ND' }">selected</c:if> >North Dakota</option>
                <option value="OH" <c:if test="${ personnel.personnelState == 'OH' }">selected</c:if> >Ohio</option>
                <option value="OK" <c:if test="${ personnel.personnelState == 'OK' }">selected</c:if> >Oklahoma</option>
                <option value="OR" <c:if test="${ personnel.personnelState == 'OR' }">selected</c:if> >Oregon</option>
                <option value="PA" <c:if test="${ personnel.personnelState == 'PA' }">selected</c:if> >Pennsylvania</option>
                <option value="RI" <c:if test="${ personnel.personnelState == 'RI' }">selected</c:if> >Rhode Island</option>
                <option value="SC" <c:if test="${ personnel.personnelState == 'SC' }">selected</c:if> >South Carolina</option>
                <option value="SD" <c:if test="${ personnel.personnelState == 'SD' }">selected</c:if> >South Dakota</option>
                <option value="TN" <c:if test="${ personnel.personnelState == 'TN' }">selected</c:if> >Tennessee</option>
                <option value="TX" <c:if test="${ personnel.personnelState == 'TX' }">selected</c:if> >Texas</option>
                <option value="UT" <c:if test="${ personnel.personnelState == 'UT' }">selected</c:if> >Utah</option>
                <option value="VT" <c:if test="${ personnel.personnelState == 'VT' }">selected</c:if> >Vermont</option>
                <option value="VA" <c:if test="${ personnel.personnelState == 'VA' }">selected</c:if> >Virginia</option>
                <option value="WA" <c:if test="${ personnel.personnelState == 'WA' }">selected</c:if> >Washington</option>
                <option value="WV" <c:if test="${ personnel.personnelState == 'WV' }">selected</c:if> >West Virginia</option>
                <option value="WI" <c:if test="${ personnel.personnelState == 'WI' }">selected</c:if> >Wisconsin</option>
                <option value="WY" <c:if test="${ personnel.personnelState == 'WY' }">selected</c:if> >Wyoming</option>
            </select>
            <br />

            <label for="daytimePhoneNumber_${status.count}">Daytime Phone Number</label>
            <input type="text" id="daytimePhoneNumber_${status.count}" name="daytimePhoneNumber_${status.count}" value="${ personnel.daytimePhoneNumber }" title="Daytime Phone Number" maxlength="25" size="20" class="phonenumber">
            <br />
            
            <label for="eveningPhoneNumber_${status.count}">Evening Phone Number</label>
            <input type="text" id="eveningPhoneNumber_${status.count}" name="eveningPhoneNumber_${status.count}" value="${ personnel.eveningPhoneNumber }" title="Evening Phone Number" maxlength="25" size="20" class="phonenumber">
            <br />

            <label for="faxnumber_${status.count}">Fax Number</label>
            <input type="text" id="faxnumber_${status.count}" name="faxnumber_${status.count}" value="${ personnel.faxNumber }" title="Fax Number" maxlength="25" size="20" class="faxnumber">
            <br />

            <label for="personnelEmail_${status.count}">Email Address</label>
            <input type="text" id="personnelEmail_${status.count}" name="personnelEmail_${status.count}" value="${ personnel.personnelEmail }" title="Email Address" maxlength="100" size="20">
            <br />

            <label for="comments_${status.count}">Additional comments or notes:</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="comments_${status.count}" name="comments_${status.count}" rows="4" cols="20">${ personnel.comments }</textarea>
            <br />

            <br />
            <br />

            </div>
        </c:forEach>
    </c:if>
        
        <div class="add">
            <a title="Add Another School Personnel" id="addForm" href="#">Add Another School Personnel</a>
        </div>
        <div style="clear: both"></div><br />
        
        <input type="submit" value="Save" title="Save" name="submitform" id="submitform" />
        <input type="reset" value="Reset" />
    </form>
    
 <%@ include file="/includes/form_duplicator.jspf" %>
</body>