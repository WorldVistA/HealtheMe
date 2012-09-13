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
    Created on : Mar 27, 2012, 7:13:47 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>

<html>
<head>
    <meta name="requiresJQUI" content="true">
    <title>HealtheMe - Care Notebook - Schools</title>
    <meta name="requiresValidation" content="true">
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/carenotebook.css" />
    <script type="text/javascript" src="${ctx_static}/js/validate.school.js"></script>
    <script language="javascript">
        //set globals
        var ctx_static = "${ctx_static}";
        var patientId = ${it.healthRecordId};
    </script>
</head>

<body id="education-si" class="education">

        <h1>School Information</h1>

        <div class="def-container">
            <div class="def-title">Purpose</div>
            This section is meant to provide you with a place to record all of the schools and their related contact and location information that your child may attend.
        </div>
        <br />


    <form class="schoolform carenotebook" action="${ctx}/form/care/${ it.healthRecordId }/schools/post/" method="post" enctype="application/x-www-form-urlencoded">
        

    <c:if test="${it.class.simpleName == 'Schools'}">
        <c:forEach items="${it.schools}" var="school" varStatus="status">

            <c:choose>
            <c:when test="${status.count == 1 }">
                <div id="formfieldcontainer" class="addedDiv">
            </c:when>
            <c:otherwise>
                <hr /><br /><div class="addedDiv">
            </c:otherwise>
            </c:choose>

            <input type="hidden" id="schoolId_${status.count}" name="schoolId_${status.count}" value="${ school.schoolId }" />

            <label for="schoolName_${status.count}">School Name</label>
            <input type="text" id="schoolName_${status.count}" name="schoolName_${status.count}" value="${ school.schoolName }" title="School Name" maxlength="50" size="20">
            <br />
            
            <label for="schoolAddress_${status.count}">Address</label>
            <input type="text" id="schoolAddress_${status.count}" name="schoolAddress_${status.count}" value="${ school.schoolAddress }" title="Address" maxlength="64" size="20">
            <br />

            <label for="schoolAddress2_${status.count}">Address 2</label>
            <input type="text" id="schoolAddress2_${status.count}" name="schoolAddress2_${status.count}" value="${ school.schoolAddress2 }" title="Address Line 2" maxlength="64" size="20">
            <br />

            <label for="schoolCity_${status.count}">City</label>
            <input type="text" id="schoolCity_${status.count}" name="schoolCity_${status.count}" value="${ school.schoolCity }" title="City" maxlength="64" size="20">
            <br />

            <label for="schoolState_${status.count}">State</label>
            <select id="schoolState_${status.count}" name="schoolState_${status.count}" title="State">
                <option value="">- Select One -</option>
                <option value="AL" <c:if test="${ school.schoolState == 'AL' }">selected</c:if> >Alabama</option>
                <option value="AK" <c:if test="${ school.schoolState == 'AK' }">selected</c:if> >Alaska</option>
                <option value="AZ" <c:if test="${ school.schoolState == 'AZ' }">selected</c:if> >Arizona</option>
                <option value="AR" <c:if test="${ school.schoolState == 'AR' }">selected</c:if> >Arkansas</option>
                <option value="CA" <c:if test="${ school.schoolState == 'CA' }">selected</c:if> >California</option>
                <option value="CO" <c:if test="${ school.schoolState == 'CO' }">selected</c:if> >Colorado</option>
                <option value="CT" <c:if test="${ school.schoolState == 'CT' }">selected</c:if> >Connecticut</option>
                <option value="DE" <c:if test="${ school.schoolState == 'DE' }">selected</c:if> >Delaware</option>
                <option value="DC" <c:if test="${ school.schoolState == 'DC' }">selected</c:if> >District of Columbia</option>
                <option value="FL" <c:if test="${ school.schoolState == 'FL' }">selected</c:if> >Florida</option>
                <option value="GA" <c:if test="${ school.schoolState == 'GA' }">selected</c:if> >Georgia</option>
                <option value="HI" <c:if test="${ school.schoolState == 'HI' }">selected</c:if> >Hawaii</option>
                <option value="ID" <c:if test="${ school.schoolState == 'ID' }">selected</c:if> >Idaho</option>
                <option value="IL" <c:if test="${ school.schoolState == 'IL' }">selected</c:if> >Illinois</option>
                <option value="IN" <c:if test="${ school.schoolState == 'IN' }">selected</c:if> >Indiana</option>
                <option value="IA" <c:if test="${ school.schoolState == 'IA' }">selected</c:if> >Iowa</option>
                <option value="KS" <c:if test="${ school.schoolState == 'KS' }">selected</c:if> >Kansas</option>
                <option value="KY" <c:if test="${ school.schoolState == 'KY' }">selected</c:if> >Kentucky</option>
                <option value="LA" <c:if test="${ school.schoolState == 'LA' }">selected</c:if> >Louisiana</option>
                <option value="ME" <c:if test="${ school.schoolState == 'ME' }">selected</c:if> >Maine</option>
                <option value="MD" <c:if test="${ school.schoolState == 'MD' }">selected</c:if> >Maryland</option>
                <option value="MA" <c:if test="${ school.schoolState == 'MA' }">selected</c:if> >Massachusetts</option>
                <option value="MI" <c:if test="${ school.schoolState == 'MI' }">selected</c:if> >Michigan</option>
                <option value="MN" <c:if test="${ school.schoolState == 'MN' }">selected</c:if> >Minnesota</option>
                <option value="MS" <c:if test="${ school.schoolState == 'MS' }">selected</c:if> >Mississippi</option>
                <option value="MO" <c:if test="${ school.schoolState == 'MO' }">selected</c:if> >Missouri</option>
                <option value="MT" <c:if test="${ school.schoolState == 'MT' }">selected</c:if> >Montana</option>
                <option value="NE" <c:if test="${ school.schoolState == 'NE' }">selected</c:if> >Nebraska</option>
                <option value="NV" <c:if test="${ school.schoolState == 'NV' }">selected</c:if> >Nevada</option>
                <option value="NH" <c:if test="${ school.schoolState == 'NH' }">selected</c:if> >New Hampshire</option>
                <option value="NJ" <c:if test="${ school.schoolState == 'NJ' }">selected</c:if> >New Jersey</option>
                <option value="NM" <c:if test="${ school.schoolState == 'NM' }">selected</c:if> >New Mexico</option>
                <option value="NY" <c:if test="${ school.schoolState == 'NY' }">selected</c:if> >New York</option>
                <option value="NC" <c:if test="${ school.schoolState == 'NC' }">selected</c:if> >North Carolina</option>
                <option value="ND" <c:if test="${ school.schoolState == 'ND' }">selected</c:if> >North Dakota</option>
                <option value="OH" <c:if test="${ school.schoolState == 'OH' }">selected</c:if> >Ohio</option>
                <option value="OK" <c:if test="${ school.schoolState == 'OK' }">selected</c:if> >Oklahoma</option>
                <option value="OR" <c:if test="${ school.schoolState == 'OR' }">selected</c:if> >Oregon</option>
                <option value="PA" <c:if test="${ school.schoolState == 'PA' }">selected</c:if> >Pennsylvania</option>
                <option value="RI" <c:if test="${ school.schoolState == 'RI' }">selected</c:if> >Rhode Island</option>
                <option value="SC" <c:if test="${ school.schoolState == 'SC' }">selected</c:if> >South Carolina</option>
                <option value="SD" <c:if test="${ school.schoolState == 'SD' }">selected</c:if> >South Dakota</option>
                <option value="TN" <c:if test="${ school.schoolState == 'TN' }">selected</c:if> >Tennessee</option>
                <option value="TX" <c:if test="${ school.schoolState == 'TX' }">selected</c:if> >Texas</option>
                <option value="UT" <c:if test="${ school.schoolState == 'UT' }">selected</c:if> >Utah</option>
                <option value="VT" <c:if test="${ school.schoolState == 'VT' }">selected</c:if> >Vermont</option>
                <option value="VA" <c:if test="${ school.schoolState == 'VA' }">selected</c:if> >Virginia</option>
                <option value="WA" <c:if test="${ school.schoolState == 'WA' }">selected</c:if> >Washington</option>
                <option value="WV" <c:if test="${ school.schoolState == 'WV' }">selected</c:if> >West Virginia</option>
                <option value="WI" <c:if test="${ school.schoolState == 'WI' }">selected</c:if> >Wisconsin</option>
                <option value="WY" <c:if test="${ school.schoolState == 'WY' }">selected</c:if> >Wyoming</option>
            </select>
            <br />

            <label for="schoolPhoneNumber_${status.count}">Phone Number</label>
            <input type="text" id="schoolPhoneNumber_${status.count}" name="schoolPhoneNumber_${status.count}" value="${ school.schoolPhoneNumber }" title="Phone Number" maxlength="25" size="20" class="phonenumber">
            <br />
            
            <label for="schoolFaxNumber_${status.count}">Fax Number</label>
            <input type="text" id="schoolFaxNumber_${status.count}" name="schoolFaxNumber_${status.count}" value="${ school.schoolFaxNumber }" title="Fax Number" maxlength="25" size="20" class="faxnumber">
            <br />

            <label for="schoolEmail_${status.count}">Email Address</label>
            <input type="text" id="schoolEmail_${status.count}" name="schoolEmail_${status.count}" value="${ school.schoolEmail }" title="Email Address" maxlength="100" size="20">
            <br />
            
            <label for="busGarageNumber_${status.count}">Bus Garage Number</label>
            <input type="text" id="busGarageNumber_${status.count}" name="busGarageNumber_${status.count}" value="${ school.busGarageNumber }" title="Bus Garage Number" maxlength="100" size="20">
            <br />
            
            <label for="busIdNumber_${status.count}">Bus ID Number</label>
            <input type="text" id="busIdNumber_${status.count}" name="busIdNumber_${status.count}" value="${ school.busIdNumber }" title="Bus ID Number" maxlength="100" size="20">
            <br />

            <label for="districtName_${status.count}">District Name</label>
            <input type="text" id="districtName_${status.count}" name="districtName_${status.count}" value="${ school.districtName }" title="District Name" maxlength="50" size="20">
            <br />
            
            <label for="comments_${status.count}">Additional comments or notes:</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="comments_${status.count}" name="comments_${status.count}" rows="4" cols="20">${ school.comments }</textarea>
            <br />

            <br />
            <br />

            </div>
        </c:forEach>
    </c:if>
        
        <div class="add">
            <a title="Add Another School" id="addForm" href="#">Add Another School</a>
        </div>
        <div style="clear: both"></div><br />
        
        <input type="submit" value="Save" title="Save" name="submitform" id="submitform" />
        <input type="reset" value="Reset" />
    </form>

 <%@ include file="/includes/form_duplicator.jspf" %>
</body>