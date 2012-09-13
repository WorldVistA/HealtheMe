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
    Document   : edit.jsp
    Created on : Nov 9, 2009, 2:04:28 AM
    Author     : Daniel Shaw (dshaw.com)
--%>
<%@ include file="/includes/taglibs.jspf" %>

<%@ include file="/includes/components/admin/inc_ctx_base_config.jspf" %>
<c:set var="pagetitle" value="${ it.fullName } - Edit User"/>
<c:set var="userId" value="${ it.userId }"/>
<c:set var="title" value="${ it.title }"/>
<c:set var="firstName" value="${ it.firstName }"/>
<c:set var="middleName" value="${ it.middleName }"/>
<c:set var="lastName" value="${ it.lastName }"/>
<c:set var="suffix" value="${ it.suffix }"/>
<c:set var="preferredName" value="${ it.preferredName }"/>
<c:set var="isPatient" value="${ it.isPatient }"/>
<c:set var="isLockedOut" value="${ it.isLockedOut }"/>

<%-- contact info --%>
<c:set var="email" value="${ it.email }"/>
<c:set var="phoneHome" value="${ it.telnumHome }"/>
<c:set var="phoneWork" value="${ it.telnumWork }"/>
<c:set var="phoneMobile" value="${ it.telnumMobile }"/>
<c:set var="fax" value="${ it.faxnum }"/>

<%-- addresss --%>
<c:set var="address1" value="${ it.primaryAddress.address1 }"/>
<c:set var="address2" value="${ it.primaryAddress.address2 }"/>
<c:set var="address3" value="${ it.primaryAddress.address3 }"/>
<c:set var="city" value="${ it.primaryAddress.city }"/>
<c:set var="state" value="${ it.primaryAddress.state }"/>
<c:set var="zip" value="${ it.primaryAddress.zip }"/>

<%-- data from Health Record --%>
<c:set var="gender" value="${ it.primaryHealthRecord.fullGender }"/>
<c:set var="year">
    <fmt:formatDate pattern="yyyy" value="${ it.primaryHealthRecord.dateOfBirthAsDate }" />
</c:set>
<c:set var="month">
    <fmt:formatDate pattern="M" value="${ it.primaryHealthRecord.dateOfBirthAsDate }" />
</c:set>
<c:set var="day">
    <fmt:formatDate pattern="d" value="${ it.primaryHealthRecord.dateOfBirthAsDate }" />
</c:set>
<c:set var="username" value="${ it.username }"/>

<%-- preferences --%>
<c:set var="carenotebook" value="${ it.primaryHealthRecord.preferences.showCarenotebookString }" />

<%@ include file="/includes/components/admin/inc_user_form.jspf" %>
