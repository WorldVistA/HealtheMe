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
    Document   : profile
    Created on : Oct 30, 2009, 11:41:57 AM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>

<head>
    <meta name="requiresJQUI" content="true">
    <title>HealtheMe - Personal Information - My Profile</title>

    <script type="text/javascript">
        $(function() {
            //pull patient info
            $.ajax({
                url:'${apipath}/patients/${it.healthRecordId}/profile/',
                type:'GET',
                timeout:5000,
                dataType:'json',
                error:function(obj,strError){
                    $('#returned-data').text( strError );
                },
                success:function(data){
                    handleData(data);
                }
            });
        });

        function handleData(data){
            for (var subData in data){
                if (typeof data[subData]=="object") {
                    handleData(data[subData]);
                } else {
                    showData(subData, data[subData]);
                }
            }
        }

        function showData(dataType, dataValue) {
            switch (dataType) {
                default:
                    if(dataValue.length) $("#" +dataType).text(dataValue);
                    break;
            }
            
        }
    </script>
    <style type="text/css">
        .left-column { float: left; margin: 0 40px 20px 0 }
        .right-column { float: left }
		h2.profile-section-heading { padding-bottom:10px; }
    </style>
</head>
<body id="myprofile">

    <h1>My Profile</h1>
    
    <div class="def-container">
        Review your personal information below to ensure it is accurate and up to date. Any discrepancies should be reported to the Registration Attendant.
    </div>
    
     <div id="profile-container">
        <div id="demographics">
            <div id="user-demographic-details">

                <h2 class="profile-section-heading">Demographics</h2>

                <div><b>Name:</b> <span id="firstName">&nbsp;</span> <span id="middleName">&nbsp;</span> <span id="lastName">&nbsp;</span></div>
                <div><b>Address 1:</b> <span id="address1">&nbsp;</span></div>
                <div><b>Address 2:</b> <span id="address2">&nbsp;</span></div>
                <div><b>Address 3:</b> <span id="address3">&nbsp;</span></div>
                <span class="city"><b>City:</b>  <span id="city">&nbsp;</span></span>
                <span class="state"><b>State:</b> <span id="state">&nbsp;</span></span>
                <span class="zip"><b>Zip:</b> <span id="zip">&nbsp;</span></span>
                <br /><br />
                <div class="gender"><b>Gender:</b> <span id="gender">&nbsp;</span></div>
                <div class="dob"><b>Date of Birth:</b> <span id="dateOfBirth">&nbsp;</span></div>

                <br />

                <h2 class="profile-section-heading">Contact Information</h2>

                <div class="left-column">
                    <div style="float:left;"><b>Phone Numbers:</b></div>
                    <div style="float:left; margin-left:3px;">
                        <span id="telnumHome">&nbsp;</span> (Home)<br />
                        <span id="telnumWork">&nbsp;</span> (Work)<br />
                        <span id="telnumMobile">&nbsp;</span> (Mobile)<br />
                        <span id="faxNum">&nbsp;</span> (Fax)<br />
                    </div>
                    </div>
                <div class="right-column">

                    <b>Email Address:</b> <span id="email">&nbsp;</span>

                </div>

            </div><!-- /#user-demographic-details -->
        </div><!-- /#demographics -->

        <div class="clear"></div>
    </div><!-- /#profile-container -->
    
</body>