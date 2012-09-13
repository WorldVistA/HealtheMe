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
    Document   : caredocuments.jsp
    Created on : Jul 3, 2009, 9:06:43 PM
    Author     : Daniel Shaw (dshaw.com)
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>

<head>
    <title>HealtheMe - Documents &amp; Forms - Continuity of Care Records</title>

    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <script type="text/javascript">
        var numItems = 0;
        $(function() {
            $.ajax({
                url:'${apipath}/patients/${it.healthRecordId}/clinical/ccrs/',
                type:'GET',
                timeout:5000,
                dataType:'json',
                error:function(obj,strError){
                    $('#returned-data').text( strError );
                },
                success:function(data){
                    handleData(data);
                    if (numItems == 0) {
                        $("tr.rowtemplate").parent().append("<tr><td colspan='4'>No Continuity of Care Records found.</td></tr>");
                    }
                }
            });
        });

        function handleData(data){
            for (var subData in data){
                if ((typeof data[subData]=="object") && (data[subData].addedDateTime != null)) {
                    numItems++;
                    var row = $("tr.rowtemplate").clone().removeClass().addClass("row" + numItems).show();
                    $("tr.rowtemplate").parent().append(row);
                    handleData(data[subData]);
                } else if (typeof data[subData]=="object") {
                    handleData(data[subData]);
                } else {
                    showData(subData, data[subData], numItems);
                }
            }
        }

        function showData(dataType, dataValue, rowNumber) {
            switch (dataType) {
                case "addedDateTime":
                    $("tr.row"+ rowNumber +" .dataStatus").html("Yes <img alt='yes' src='${ctx_static}/css/img/greencheck.gif' />");
                    break;
                case "createdDateTime":
                    $("tr.row"+ rowNumber +" .dataShownDetails").append("<b>Document Created Date:</b> " + dataValue + "<br />")
                    break;
                case "lastUpdatedDateTime":
                    $("tr.row"+ rowNumber +" .dataLastUpdated").text(dataValue);
                    break;
                case "description":
                    $("tr.row"+ rowNumber +" .dataFrom").text(dataValue);
                    break;
                case "version":
                    $("tr.row"+ rowNumber +" .dataDescription").text(dataValue);
                    break;
                case "ccrDocumentObjectId":
                    $("tr.row"+ rowNumber +" .dataShownDetails").append("<b>Document Object ID:</b> " + dataValue + "<br />")
                    break;
            }
        }

        function showDetails(rowNumber) {
            $("tr.row" + rowNumber + " .dataShownDetails").slideDown();
            $("tr.row" + rowNumber + " .dataExpand").attr("href", "javascript:hideDetails(1);");
        }

        function hideDetails(rowNumber) {
            $("tr.row" + rowNumber + " .dataShownDetails").slideUp();
            $("tr.row" + rowNumber + ".dataExpand").attr("href", "javascript:showDetails(1);");
        }
    </script>

</head>
<body id="caredocs">

    <h1>Continuity of Care Records</h1>

    <div class="def-container">
    	<div class="def-title">Definition</div>
        A <b>Continuity of Care Record (CCR)</b> is information automatically pulled from your clinical health record and displayed in an organized way. It may contain your allergies, immunizations, medical conditions, lab results and more.<br />
    </div>

    <table id="phr-hr-container" cellpadding="1" cellspacing="0">
        <tr>
            <td class="phr-hr-container-focus">

                <!-- caredocs (ccr/ccd) -->
                <div id="m-caredocs">

                    <style type="text/css">
						#ccr_records { margin:20px 0; }
						#ccr_records .col1 { font-weight:bold; width:200px; }
						#ccr_records .col2 { width:100px; }
						#ccr_records .col3 { width:140px; }
						#ccr_records .col4 { width:126px; }

						#ccr_records .col1 .d { padding-left:5px; width:195px; }
						#ccr_records .col2 .d { width:95px; }
						#ccr_records .col3 .d { width:135px; }
						#ccr_records .col4 .d { width:121px; }

					</style>

                    <table id="ccr_records" class="grs1">
                        <tbody>
                            <tr>
                                <th class="col1">
                                    <div class="d">From</div>
                                </th>
                                <th class="col2">
                                    <div class="d">Description</div>
                                </th>
                                <th>
                                    <div class="d">Last Updated</div>
                                </th>
                                <th>
                                    <div class="d"><span>Added to H<span class="e">e</span>M?</span></div>
                                </th>
                            </tr>
                            <tr class="rowtemplate ui-helper-hidden">
                                <td class="col1">
                                    <div class="d">
                                    	<span class="dataFrom"></span>
                                    </div>
                                </td>
                                <td class="col2">
                                	<div class="d">
                                    	<span class="dataDescription"></span>
                                    </div>
                                </td>
                                <td class="dataLastUpdated col3"></td>
                                <td class="col4">
                                    <div class="d">
                                    	<span class="statusWrapper">
                                        	<div class="dataStatus">No</div>
                                    	</span>
                                   	</div>
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </div><!-- /#m-caredocs -->

            </td>
        </tr>
    </table>
</body>
