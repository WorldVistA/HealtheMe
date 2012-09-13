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
    Document   : healthcard
    Created on : Oct 30, 2009, 11:42:25 AM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>

<head>
    <meta name="requiresJQUI" content="true">
    <title>HealtheMe - Personal Information - Health Information Card</title>

    <script type="text/javascript">
        $(function() {
            //pull patient health record
            $.ajax({
                url:'${apipath}/patients/${it.healthRecordId}/',
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

            //pull patient info
            $.ajax({
                url:'${apipath}/patients/${it.healthRecordId}/info/',
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

            //pull self-entered allergies
            $.ajax({
                url:'${apipath}/patients/${it.healthRecordId}/allergies/',
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

            //pull clinical allergies
            $.ajax({
                url:'${apipath}/patients/${it.healthRecordId}/clinical/allergies/',
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

            //pull self-entered medications
            $.ajax({
                url:'${apipath}/patients/${it.healthRecordId}/medications/',
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
            
            //pull clinical medications
            $.ajax({
                url:'${apipath}/patients/${it.healthRecordId}/clinical/medications/',
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
                case "bloodType":
                case "dateOfBirth":
                case "organDonor":
                case "name":
                case "allergyText":
                case "alertTitle":
                case "medicationText":
                case "productName":
                    var targetSpan = $("span." +dataType);
                    if (targetSpan.text().length > 0) {
                        targetSpan.append(", " + dataValue)
                    } else {
                        targetSpan.append(dataValue)
                    }
                    break;
            }
        }
    </script>
</head>
<body id="healthcard">

    <h1>Health Information Card</h1>
    
    <div class="def-container">
        This card contains information important to healthcare providers in an emergency situation. If you ever find yourself in an emergency medical situation, this card could be vital in the ability for the doctor to quickly diagnose and treat your emergency.
    </div>
 	
    <style type="text/css">
		hc-section {
			padding:5px;	
		}
		.label {
			color:#333;
			font-size:1em;
			font-weight:bold;
		}
		
	</style>
    
    <div id="healthcard">
        <h2><span class="name"></span></h2>
        <div class="hc-section">
        	<span class="label">Birth Date:</span>
            <span class="dateOfBirth"></span>
        </div>
        <div class="hc-section">
        	<span class="label">Blood Type:</span>
            <span class="bloodType"></span>
        </div>
        <div class="hc-section">
        	<span class="label">Donor:</span>
            <span class="organDonor"></span>
        </div>
        <div class="hc-section">
        	<span class="label">Medications:</span>
            <span class="productName medicationText"></span>
        </div>
        <div class="hc-section">
        	<span class="label">Allergies:</span>
            <span class="allergyText alertTitle"></span>
        </div>
    </div>
            
</body>