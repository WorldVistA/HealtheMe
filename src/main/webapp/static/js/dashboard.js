/*
 * Copyright (C) 2012 KRM Associates, Inc. healtheme@krminc.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
var doHideCount = true;
$(function() {
    //structure of sub arrays:
    //  API path piece, display name, isVital boolean, path to view item
    var apiSelfSections = new Array(
        new Array("medicalevents","Medical Events",false,"medicalevents"),
        new Array("allergies","Allergies & Reactions",false,"allergies"),
        new Array("medications","Medications",false,"medications"),
        new Array("immunizations","Immunizations",false,"immunizations"),
        new Array("visits","Appointments and Visits",false,"visits"),
        new Array("vitals/bloodpressure","Blood Pressure Readings", true,"vitals-bp"),
        new Array("vitals/heartrate","Pulse Readings", true, "vitals-hr"),
        new Array("vitals/temperature","Body Temperature Readings", true, "vitals-bt"),
        new Array("vitals/pain","Pain Level Readings", true, "vitals-p"),
        new Array("vitals/peakflow","Peak Flow Readings", true, "vitals-pf"),
        new Array("vitals/height","Height Readings", true, "vitals-ht"),
        new Array("vitals/weight","Body Weight Readings", true, "vitals-bw"),
        new Array("vitals/bloodsugar","Fingerstick Blood Glucose Readings", true, "vitals-bs")
    );
    var apiClinicalSections = new Array(
        new Array("clinical/medicalevents","Medical Events", false, "medicalevents"),
        new Array("clinical/allergies","Allergies & Reactions", false, "allergies"),
        new Array("clinical/medications","Medications", false, "medications"),
        new Array("clinical/results","Lab & Test Results", false, "results"),
        new Array("clinical/immunizations","Immunizations", false, "immunizations"),
        new Array("clinical/vitals/um","Uncategorized Vital Readings", true, "vitals"),
        new Array("clinical/vitals/bp","Blood Pressure Readings", true,"vitals-bp"),
        new Array("clinical/vitals/hr","Pulse Readings", true, "vitals-hr"),
        new Array("clinical/vitals/bt","Body Temperature Readings", true, "vitals-bt"),
        new Array("clinical/vitals/pn","Pain Level Readings", true, "vitals-p"),
        new Array("clinical/vitals/pf","Peak Flow Readings", true, "vitals-pf"),
        new Array("clinical/vitals/ht","Height Readings", true, "vitals-ht"),
        new Array("clinical/vitals/bw","Body Weight Readings", true, "vitals-bw"),
        new Array("clinical/vitals/bmi","Body Mass Index Readings", true, "vitals-bmi")
    );

    for (section in apiSelfSections) {
        getCount(apiSelfSections[section][0],apiSelfSections[section][1], false, apiSelfSections[section][2], apiSelfSections[section][3]);
    }

    for (section in apiClinicalSections) {
        getCount(apiClinicalSections[section][0],apiClinicalSections[section][1], true, apiClinicalSections[section][2], apiClinicalSections[section][3]);
    }

    $("a.approve").live("click", function(){
            approveRequest(this.hash.slice(1));
        }
    );
    $("a.deny").live("click", function(){
            denyRequest(this.hash.slice(1));
        }
    );
    
    checkRequests();
});

function appendSelfEnteredStat(intCount, countType, isVital, path) {
    if (isVital) {
        $("#health-record-stats .self-entered-stat #self-entered-stats-vitals").append("<li><a href='" + path + "'>" + countType + "</a> <span class='count'>" + intCount + "</span></li>");
    } else {
        $("#health-record-stats .self-entered-stat #self-entered-stats").append("<li><a href='" + path + "'>" + countType + "</a> <span class='count'>" + intCount + "</span></li>");
    }


}

function appendClinicalStat(intCount, countType, isVital, path) {
    if (isVital) {
        $("#health-record-stats .clinical-stat #clinical-stats-vitals").append("<li><a href='" + path + "'>" + countType + "</a> <span class='count'>" + intCount + "</span></li>");
    } else {
        $("#health-record-stats .clinical-stat #clinical-stats").append("<li><a href='" + path + "'>" + countType + "</a> <span class='count'>" + intCount + "</span></li>");
    }
}

function getCount(apiCountPath,countType,isClinical, isVital, path) {
    $.ajax({
        url:apipath+"/patients/"+patientId+"/"+apiCountPath+"/count/",
        type:"GET",
        timeout:5000,
        dataType:"json",
        error:function(obj,strError){
            if (isClinical) {
                appendClinicalStat(0,countType, isVital, path)
            } else {
                appendSelfEnteredStat(0, countType, isVital, path)
            }
        },
        success:function(data){
            if(typeof(data)!="object") {
                window.location="/login";
                return;
            }

            if (data.count != undefined) {
                var intCount = parseInt(data.count);
                if (isClinical) {
                    appendClinicalStat(intCount,countType, isVital, path)
                } else {
                    appendSelfEnteredStat(intCount, countType, isVital, path)
                }

                if (doHideCount) {
                    $("span.ajaxload").hide();
                    $("#health-record-stats").show();
                    doHideCount=false;
                }
            }
    },
        complete:function(data,strSuccessType){
            switch (strSuccessType)
            {
                case "timeout":
                case "error":
                case "parsererror":
                    if (data.status == 200 || data.status == 302) {
                        //document.location.reload();
                    }
                    break;
                //  do nothing on successful page change
                case "notmodified":
                case "success":
                    break;
            }
        }
    });
}

function checkRequests() {
    $.ajax({
        url:apipath+"/patients/"+patientId+"/checkRequests/",
        type:"GET",
        timeout:5000,
        dataType:"json",
        error:function(obj,strError){
            //do nothing
        },
        success:function(data){
            if(typeof(data)!="object") {
                window.location="/login";
                return;
            }

            if (data.requests == true) {
                if (data.users instanceof Array) {
                    var html = '<div id="dataRequests"><h2>New Data Access Requests Found</h2>';
                    for (user in data.users) {
                        if (data.ids[user] > 0) {
                            var req = "<div id='request" + data.ids[user] + "'>" + data.users[user] + " " + "<a class='approve' href='#" + data.ids[user] +"'>Approve</a>" + " " + "<a class='deny' href='#" + data.ids[user] +"'>Deny</a><br/></div>";
                            html += req;
                        }
                    }
                    html += "</div>";
                    $("#accessrequests").append(html);
                }
            }
        }
    });
}

function approveRequest(reqId){
    $.ajax({
        url:apipath + "/patients/" + patientId + "/approveRequest/" + reqId,
        type:"GET",
        timeout:5000,
        dataType:"json",
        error:function(obj,strError){
            //do nothing
        },
        success:function(data){
            if(typeof(data)!="object") {
                window.location="/login";
                return;
            }
            if (data.status == "success") {
                alert("The request has been approved.");
                $("div#request" + reqId).remove();
                if ($("div#dataRequests").children().length == 1) $("div#dataRequests").remove();
            } else {
                //error
                alert("An error occurred approving the request. Try again or contact an administrator.");
            }
        }
    });
}

function denyRequest(reqId){
    $.ajax({
        url:apipath + "/patients/" + patientId + "/denyRequest/" + reqId,
        type:"GET",
        timeout:5000,
        dataType:"json",
        error:function(obj,strError){
            //do nothing
        },
        success:function(data){
            if(typeof(data)!="object") {
                window.location="/login";
                return;
            }
            if (data.status == "success") {
                alert("The request has been denied.");
                $("div#request" + reqId).remove();
                if ($("div#dataRequests").children().length == 1) $("div#dataRequests").remove();
            } else {
                //error
                alert("An error occurred denying the request. Try again or contact an administrator.");
            }
        }
    });

}