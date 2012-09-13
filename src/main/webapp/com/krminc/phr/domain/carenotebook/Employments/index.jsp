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
    Created on : Mar 27, 2012, 5:00:06 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>

<html>
<head>
    <meta name="requiresJQUI" content="true">
    <title>HealtheMe - Care Notebook - Employment</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/carenotebook.css" />
    <script type="text/javascript" src="${ctx_static}/js/validate.employment.js"></script>
    <script language="javascript">
        //set globals
        var ctx_static = "${ctx_static}";
        var patientId = ${it.healthRecordId};
        
        $(document).ready(function() {
            $("input.datepicker").datepicker({showOn: 'button', buttonImage: '${ctx_static}/images/cal.gif', buttonImageOnly: true, onSelect:function(){$(this).valid();}});
        });
    </script>
</head>

<body id="employment" class="employment">

        <h1>Employment History</h1>

        <div class="def-container">
            <div class="def-title">Purpose</div>
            This section is meant to provide you with a place to record all current and previous employment contacts and details.
        </div>
        <br />
        <h2>Employers</h2>
        <br />


    <form class="employmentform carenotebook" action="${ctx}/form/care/${ it.healthRecordId }/employment/post/" method="post" enctype="application/x-www-form-urlencoded">
        

    <c:if test="${it.class.simpleName == 'Employments'}">
        <c:forEach items="${it.employments}" var="employment" varStatus="status">

            <c:choose>
            <c:when test="${status.count == 1 }">
                <div id="formfieldcontainer" class="addedDiv">
            </c:when>
            <c:otherwise>
                <hr /><br /><div class="addedDiv">
            </c:otherwise>
            </c:choose>

            <input type="hidden" id="employmentId_${status.count}" name="employmentId_${status.count}" value="${ employment.employmentId }" />

            <label for="employmentPlace_${status.count}">Place of Employment</label>
            <input type="text" id="employmentPlace_${status.count}" name="employmentPlace_${status.count}" value="${ employment.employmentPlace }" title="Place of Employment" maxlength="50" size="20">
            <br />
            
            <label for="employmentStart_${status.count}">Start Date</label>
            <input type="text" class="hv_element_date_input datepicker" id="employmentStart_${status.count}" name="employmentStart_${status.count}" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${ employment.employmentStart }"/>" title="Start Date" maxlength="10" size="20">
            <br />
            
            <label for="employmentEnd_${status.count}">Last Day</label>
            <input type="text" class="hv_element_date_input datepicker" id="employmentEnd_${status.count}" name="employmentEnd_${status.count}" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${ employment.employmentEnd }"/>" title="Last Day" maxlength="10" size="20">
            <br />
            
            <label for="employmentSupervisor_${status.count}">Supervisor</label>
            <input type="text" id="employmentSupervisor_${status.count}" name="employmentSupervisor_${status.count}" value="${ employment.employmentSupervisor }" title="Supervisor" maxlength="50" size="20">
            <br />
            
            <label for="employmentAddress_${status.count}">Address</label>
            <input type="text" id="employmentAddress_${status.count}" name="employmentAddress_${status.count}" value="${ employment.employmentAddress }" title="Address" maxlength="64" size="20">
            <br />

            <label for="employmentAddress2_${status.count}">Address 2</label>
            <input type="text" id="employmentAddress2_${status.count}" name="employmentAddress2_${status.count}" value="${ employment.employmentAddress2 }" title="Address Line 2" maxlength="64" size="20">
            <br />

            <label for="employmentCity_${status.count}">City</label>
            <input type="text" id="employmentCity_${status.count}" name="employmentCity_${status.count}" value="${ employment.employmentCity }" title="City" maxlength="64" size="20">
            <br />

            <label for="employmentState_${status.count}">State</label>
            <select id="employmentState_${status.count}" name="employmentState_${status.count}" title="State">
                <option value="">- Select One -</option>
                <option value="AL" <c:if test="${ employment.employmentState == 'AL' }">selected</c:if> >Alabama</option>
                <option value="AK" <c:if test="${ employment.employmentState == 'AK' }">selected</c:if> >Alaska</option>
                <option value="AZ" <c:if test="${ employment.employmentState == 'AZ' }">selected</c:if> >Arizona</option>
                <option value="AR" <c:if test="${ employment.employmentState == 'AR' }">selected</c:if> >Arkansas</option>
                <option value="CA" <c:if test="${ employment.employmentState == 'CA' }">selected</c:if> >California</option>
                <option value="CO" <c:if test="${ employment.employmentState == 'CO' }">selected</c:if> >Colorado</option>
                <option value="CT" <c:if test="${ employment.employmentState == 'CT' }">selected</c:if> >Connecticut</option>
                <option value="DE" <c:if test="${ employment.employmentState == 'DE' }">selected</c:if> >Delaware</option>
                <option value="DC" <c:if test="${ employment.employmentState == 'DC' }">selected</c:if> >District of Columbia</option>
                <option value="FL" <c:if test="${ employment.employmentState == 'FL' }">selected</c:if> >Florida</option>
                <option value="GA" <c:if test="${ employment.employmentState == 'GA' }">selected</c:if> >Georgia</option>
                <option value="HI" <c:if test="${ employment.employmentState == 'HI' }">selected</c:if> >Hawaii</option>
                <option value="ID" <c:if test="${ employment.employmentState == 'ID' }">selected</c:if> >Idaho</option>
                <option value="IL" <c:if test="${ employment.employmentState == 'IL' }">selected</c:if> >Illinois</option>
                <option value="IN" <c:if test="${ employment.employmentState == 'IN' }">selected</c:if> >Indiana</option>
                <option value="IA" <c:if test="${ employment.employmentState == 'IA' }">selected</c:if> >Iowa</option>
                <option value="KS" <c:if test="${ employment.employmentState == 'KS' }">selected</c:if> >Kansas</option>
                <option value="KY" <c:if test="${ employment.employmentState == 'KY' }">selected</c:if> >Kentucky</option>
                <option value="LA" <c:if test="${ employment.employmentState == 'LA' }">selected</c:if> >Louisiana</option>
                <option value="ME" <c:if test="${ employment.employmentState == 'ME' }">selected</c:if> >Maine</option>
                <option value="MD" <c:if test="${ employment.employmentState == 'MD' }">selected</c:if> >Maryland</option>
                <option value="MA" <c:if test="${ employment.employmentState == 'MA' }">selected</c:if> >Massachusetts</option>
                <option value="MI" <c:if test="${ employment.employmentState == 'MI' }">selected</c:if> >Michigan</option>
                <option value="MN" <c:if test="${ employment.employmentState == 'MN' }">selected</c:if> >Minnesota</option>
                <option value="MS" <c:if test="${ employment.employmentState == 'MS' }">selected</c:if> >Mississippi</option>
                <option value="MO" <c:if test="${ employment.employmentState == 'MO' }">selected</c:if> >Missouri</option>
                <option value="MT" <c:if test="${ employment.employmentState == 'MT' }">selected</c:if> >Montana</option>
                <option value="NE" <c:if test="${ employment.employmentState == 'NE' }">selected</c:if> >Nebraska</option>
                <option value="NV" <c:if test="${ employment.employmentState == 'NV' }">selected</c:if> >Nevada</option>
                <option value="NH" <c:if test="${ employment.employmentState == 'NH' }">selected</c:if> >New Hampshire</option>
                <option value="NJ" <c:if test="${ employment.employmentState == 'NJ' }">selected</c:if> >New Jersey</option>
                <option value="NM" <c:if test="${ employment.employmentState == 'NM' }">selected</c:if> >New Mexico</option>
                <option value="NY" <c:if test="${ employment.employmentState == 'NY' }">selected</c:if> >New York</option>
                <option value="NC" <c:if test="${ employment.employmentState == 'NC' }">selected</c:if> >North Carolina</option>
                <option value="ND" <c:if test="${ employment.employmentState == 'ND' }">selected</c:if> >North Dakota</option>
                <option value="OH" <c:if test="${ employment.employmentState == 'OH' }">selected</c:if> >Ohio</option>
                <option value="OK" <c:if test="${ employment.employmentState == 'OK' }">selected</c:if> >Oklahoma</option>
                <option value="OR" <c:if test="${ employment.employmentState == 'OR' }">selected</c:if> >Oregon</option>
                <option value="PA" <c:if test="${ employment.employmentState == 'PA' }">selected</c:if> >Pennsylvania</option>
                <option value="RI" <c:if test="${ employment.employmentState == 'RI' }">selected</c:if> >Rhode Island</option>
                <option value="SC" <c:if test="${ employment.employmentState == 'SC' }">selected</c:if> >South Carolina</option>
                <option value="SD" <c:if test="${ employment.employmentState == 'SD' }">selected</c:if> >South Dakota</option>
                <option value="TN" <c:if test="${ employment.employmentState == 'TN' }">selected</c:if> >Tennessee</option>
                <option value="TX" <c:if test="${ employment.employmentState == 'TX' }">selected</c:if> >Texas</option>
                <option value="UT" <c:if test="${ employment.employmentState == 'UT' }">selected</c:if> >Utah</option>
                <option value="VT" <c:if test="${ employment.employmentState == 'VT' }">selected</c:if> >Vermont</option>
                <option value="VA" <c:if test="${ employment.employmentState == 'VA' }">selected</c:if> >Virginia</option>
                <option value="WA" <c:if test="${ employment.employmentState == 'WA' }">selected</c:if> >Washington</option>
                <option value="WV" <c:if test="${ employment.employmentState == 'WV' }">selected</c:if> >West Virginia</option>
                <option value="WI" <c:if test="${ employment.employmentState == 'WI' }">selected</c:if> >Wisconsin</option>
                <option value="WY" <c:if test="${ employment.employmentState == 'WY' }">selected</c:if> >Wyoming</option>
            </select>
            <br />

            <label for="phoneNumber_${status.count}">Phone Number</label>
            <input type="text" id="phoneNumber_${status.count}" name="phoneNumber_${status.count}" value="${ employment.phoneNumber }" title="Phone Number" maxlength="25" size="20" class="phonenumber">
            <br />


            <label for="faxnumber_${status.count}">Fax Number</label>
            <input type="text" id="faxnumber_${status.count}" name="faxnumber_${status.count}" value="${ employment.faxNumber }" title="Fax Number" maxlength="25" size="20" class="faxnumber">
            <br />

            <label for="employmentEmail_${status.count}">Email Address</label>
            <input type="text" id="employmentEmail_${status.count}" name="employmentEmail_${status.count}" value="${ employment.employmentEmail }" title="Email Address" maxlength="100" size="20">
            <br />

            <label for="comments_${status.count}">Additional comments or notes:</label>
            <div style="clear: both"></div>
            <br />
            <textarea id="comments_${status.count}" name="comments_${status.count}" rows="4" cols="20">${ employment.comments }</textarea>
            <br />

            <br />
            <br />

            </div>
        </c:forEach>
    </c:if>
        
        <div class="add">
            <a title="Add Another Employment" id="addForm" href="#">Add Another Employment</a>
        </div>
        <div style="clear: both"></div><br />
        
        <input type="submit" value="Save" title="Save" name="submitform" id="submitform" />
        <input type="reset" value="Reset" />
    </form>
            
 <%@ include file="/includes/form_duplicator.jspf" %>
</body>