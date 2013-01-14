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
    Created on : Mar 23, 2012, 3:08:36 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>

<html>
<head>
    <meta name="requiresJQUI" content="true">
    <title>HealtheMe - Care Notebook - Contacts</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/carenotebook.css" />
    <script type="text/javascript" src="${ctx_static}/js/validate.contact.js"></script>
    <script language="javascript">
        //set globals
        var ctx_static = "${ctx_static}";
        var patientId = ${it.healthRecordId};
    </script>
</head>

<body id="contacts" class="contacts">

        <h1>Contacts</h1>

        <div class="def-container">
            <div class="def-title">Purpose</div>
            This section is meant to provide you with a place to record all of the members of your family or those who may be contacted in case of emergency. Updated medical information that you record will help to make sure that your child receives proper medical treatment.
        </div>
        <br />
        <h2>Contacts</h2>
        <br />


    <form class="contactform carenotebook" action="${ctx}/form/care/${ it.healthRecordId }/contacts/post/" method="post" enctype="application/x-www-form-urlencoded">
        

    <c:if test="${it.class.simpleName == 'FamilyMembers'}">
        <c:forEach items="${it.members}" var="member" varStatus="status">

            <c:choose>
            <c:when test="${status.count == 1 }">
                <div id="formfieldcontainer" class="addedDiv">
            </c:when>
            <c:otherwise>
                <hr /><br /><div class="addedDiv">
            </c:otherwise>
            </c:choose>

            <input type="hidden" id="familymemberId_${status.count}" name="familymemberId_${status.count}" value="${ member.familymemberId }" />
            
            <label for="familymemberType_${status.count}">Relationship</label>
            <select id="familymemberType_${status.count}" name="familymemberType_${status.count}" value="${ member.familymemberType }" title="Relationship">
                <option value="">- Select One -</option>
                <option value="Mother" <c:if test="${ member.familymemberType == 'Mother' }">selected</c:if> >Mother</option>
                <option value="Father" <c:if test="${ member.familymemberType == 'Father' }">selected</c:if> >Father</option>
                <option value="Brother" <c:if test="${ member.familymemberType == 'Brother' }">selected</c:if> >Brother</option>
                <option value="Sister" <c:if test="${ member.familymemberType == 'Sister' }">selected</c:if> >Sister</option>
                <option value="Grandparent" <c:if test="${ member.familymemberType == 'Grandparent' }">selected</c:if> >Grandparent</option>
                <option value="Guardian" <c:if test="${ member.familymemberType == 'Guardian' }">selected</c:if> >Guardian</option>
                <option value="Spouse" <c:if test="${ member.familymemberType == 'Spouse' }">selected</c:if> >Spouse</option>
                <option value="Other" <c:if test="${ member.familymemberType == 'Other' }">selected</c:if> >Other</option>
            </select>
            <br />

            <label for="familymemberName_${status.count}">Name</label>
            <input type="text" id="familymemberName_${status.count}" name="familymemberName_${status.count}" value="${ member.familymemberName }" title="Name" maxlength="50" size="20">
            <br />
            
            <label for="familymemberAddress_${status.count}">Address</label>
            <input type="text" id="familymemberAddress_${status.count}" name="familymemberAddress_${status.count}" value="${ member.familymemberAddress }" title="Address" maxlength="64" size="20">
            <br />

            <label for="familymemberAddress2_${status.count}">Address 2</label>
            <input type="text" id="familymemberAddress2_${status.count}" name="familymemberAddress2_${status.count}" value="${ member.familymemberAddress2 }" title="Address Line 2" maxlength="64" size="20">
            <br />

            <label for="familymemberCity_${status.count}">City</label>
            <input type="text" id="familymemberCity_${status.count}" name="familymemberCity_${status.count}" value="${ member.familymemberCity }" title="City" maxlength="64" size="20">
            <br />

            <label for="familymemberState_${status.count}">State</label>
            <select id="familymemberState_${status.count}" name="familymemberState_${status.count}" title="State">
                <option value="">- Select One -</option>
                <option value="AL" <c:if test="${ member.familymemberState == 'AL' }">selected</c:if> >Alabama</option>
                <option value="AK" <c:if test="${ member.familymemberState == 'AK' }">selected</c:if> >Alaska</option>
                <option value="AZ" <c:if test="${ member.familymemberState == 'AZ' }">selected</c:if> >Arizona</option>
                <option value="AR" <c:if test="${ member.familymemberState == 'AR' }">selected</c:if> >Arkansas</option>
                <option value="CA" <c:if test="${ member.familymemberState == 'CA' }">selected</c:if> >California</option>
                <option value="CO" <c:if test="${ member.familymemberState == 'CO' }">selected</c:if> >Colorado</option>
                <option value="CT" <c:if test="${ member.familymemberState == 'CT' }">selected</c:if> >Connecticut</option>
                <option value="DE" <c:if test="${ member.familymemberState == 'DE' }">selected</c:if> >Delaware</option>
                <option value="DC" <c:if test="${ member.familymemberState == 'DC' }">selected</c:if> >District of Columbia</option>
                <option value="FL" <c:if test="${ member.familymemberState == 'FL' }">selected</c:if> >Florida</option>
                <option value="GA" <c:if test="${ member.familymemberState == 'GA' }">selected</c:if> >Georgia</option>
                <option value="HI" <c:if test="${ member.familymemberState == 'HI' }">selected</c:if> >Hawaii</option>
                <option value="ID" <c:if test="${ member.familymemberState == 'ID' }">selected</c:if> >Idaho</option>
                <option value="IL" <c:if test="${ member.familymemberState == 'IL' }">selected</c:if> >Illinois</option>
                <option value="IN" <c:if test="${ member.familymemberState == 'IN' }">selected</c:if> >Indiana</option>
                <option value="IA" <c:if test="${ member.familymemberState == 'IA' }">selected</c:if> >Iowa</option>
                <option value="KS" <c:if test="${ member.familymemberState == 'KS' }">selected</c:if> >Kansas</option>
                <option value="KY" <c:if test="${ member.familymemberState == 'KY' }">selected</c:if> >Kentucky</option>
                <option value="LA" <c:if test="${ member.familymemberState == 'LA' }">selected</c:if> >Louisiana</option>
                <option value="ME" <c:if test="${ member.familymemberState == 'ME' }">selected</c:if> >Maine</option>
                <option value="MD" <c:if test="${ member.familymemberState == 'MD' }">selected</c:if> >Maryland</option>
                <option value="MA" <c:if test="${ member.familymemberState == 'MA' }">selected</c:if> >Massachusetts</option>
                <option value="MI" <c:if test="${ member.familymemberState == 'MI' }">selected</c:if> >Michigan</option>
                <option value="MN" <c:if test="${ member.familymemberState == 'MN' }">selected</c:if> >Minnesota</option>
                <option value="MS" <c:if test="${ member.familymemberState == 'MS' }">selected</c:if> >Mississippi</option>
                <option value="MO" <c:if test="${ member.familymemberState == 'MO' }">selected</c:if> >Missouri</option>
                <option value="MT" <c:if test="${ member.familymemberState == 'MT' }">selected</c:if> >Montana</option>
                <option value="NE" <c:if test="${ member.familymemberState == 'NE' }">selected</c:if> >Nebraska</option>
                <option value="NV" <c:if test="${ member.familymemberState == 'NV' }">selected</c:if> >Nevada</option>
                <option value="NH" <c:if test="${ member.familymemberState == 'NH' }">selected</c:if> >New Hampshire</option>
                <option value="NJ" <c:if test="${ member.familymemberState == 'NJ' }">selected</c:if> >New Jersey</option>
                <option value="NM" <c:if test="${ member.familymemberState == 'NM' }">selected</c:if> >New Mexico</option>
                <option value="NY" <c:if test="${ member.familymemberState == 'NY' }">selected</c:if> >New York</option>
                <option value="NC" <c:if test="${ member.familymemberState == 'NC' }">selected</c:if> >North Carolina</option>
                <option value="ND" <c:if test="${ member.familymemberState == 'ND' }">selected</c:if> >North Dakota</option>
                <option value="OH" <c:if test="${ member.familymemberState == 'OH' }">selected</c:if> >Ohio</option>
                <option value="OK" <c:if test="${ member.familymemberState == 'OK' }">selected</c:if> >Oklahoma</option>
                <option value="OR" <c:if test="${ member.familymemberState == 'OR' }">selected</c:if> >Oregon</option>
                <option value="PA" <c:if test="${ member.familymemberState == 'PA' }">selected</c:if> >Pennsylvania</option>
                <option value="RI" <c:if test="${ member.familymemberState == 'RI' }">selected</c:if> >Rhode Island</option>
                <option value="SC" <c:if test="${ member.familymemberState == 'SC' }">selected</c:if> >South Carolina</option>
                <option value="SD" <c:if test="${ member.familymemberState == 'SD' }">selected</c:if> >South Dakota</option>
                <option value="TN" <c:if test="${ member.familymemberState == 'TN' }">selected</c:if> >Tennessee</option>
                <option value="TX" <c:if test="${ member.familymemberState == 'TX' }">selected</c:if> >Texas</option>
                <option value="UT" <c:if test="${ member.familymemberState == 'UT' }">selected</c:if> >Utah</option>
                <option value="VT" <c:if test="${ member.familymemberState == 'VT' }">selected</c:if> >Vermont</option>
                <option value="VA" <c:if test="${ member.familymemberState == 'VA' }">selected</c:if> >Virginia</option>
                <option value="WA" <c:if test="${ member.familymemberState == 'WA' }">selected</c:if> >Washington</option>
                <option value="WV" <c:if test="${ member.familymemberState == 'WV' }">selected</c:if> >West Virginia</option>
                <option value="WI" <c:if test="${ member.familymemberState == 'WI' }">selected</c:if> >Wisconsin</option>
                <option value="WY" <c:if test="${ member.familymemberState == 'WY' }">selected</c:if> >Wyoming</option>
            </select>
            <br />

            <label for="daytimePhoneNumber_${status.count}">Daytime Phone Number</label>
            <input type="text" id="daytimePhoneNumber_${status.count}" name="daytimePhoneNumber_${status.count}" value="${ member.daytimePhoneNumber }" title="Daytime Phone Number" maxlength="25" size="20" class="phonenumber">
            <br />
            
            <label for="eveningPhoneNumber_${status.count}">Evening Phone Number</label>
            <input type="text" id="eveningPhoneNumber_${status.count}" name="eveningPhoneNumber_${status.count}" value="${ member.eveningPhoneNumber }" title="Evening Phone Number" maxlength="25" size="20" class="phonenumber">
            <br />

            <label for="faxnumber_${status.count}">Fax Number</label>
            <input type="text" id="faxnumber_${status.count}" name="faxnumber_${status.count}" value="${ member.faxNumber }" title="Fax Number" maxlength="25" size="20" class="faxnumber">
            <br />

            <label for="familymemberEmail_${status.count}">Email Address</label>
            <input type="text" id="familymemberEmail_${status.count}" name="familymemberEmail_${status.count}" value="${ member.familymemberEmail }" title="Email Address" maxlength="100" size="20">
            <br />

            <label for="familymemberPrimary_${status.count}">Primary emergency contact?</label>
            <input type="radio" class="isPrimary" name="isPrimary_${status.count}" value="true" id="isPrimary_${status.count}" <c:if test="${ member.isPrimary == 'true'}">checked</c:if> title="Primary emergency contact?">Yes</input>
            <input type="radio" class="isPrimary" name="isPrimary_${status.count}" value="false" id="isPrimary_${status.count}" <c:if test="${ member.isPrimary == 'false' || empty member.isPrimary }">checked</c:if> title="Primary emergency contact?">No</input>
            <br />
            <br />

            <label for="comments_${status.count}">Additional comments or notes:</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="comments_${status.count}" name="comments_${status.count}" rows="4" cols="20">${ member.comments }</textarea>
            <br />

            <br />
            <br />

            </div>
        </c:forEach>
    </c:if>
        
        <div class="add">
            <a title="Add Another Contact" id="addForm" href="#">Add Another Contact</a>
        </div>
        <div style="clear: both"></div><br />
        
        <input type="submit" value="Save" title="Save" name="submitform" id="submitform" />
        <input type="reset" value="Reset" />
    </form>
            
 <%@ include file="/includes/form_duplicator.jspf" %>
</body>