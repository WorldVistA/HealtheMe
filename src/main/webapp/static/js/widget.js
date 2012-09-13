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
//set globals
var countItems = 0;
var start = 0;
var source = "self";
var descending = 1;
var resultType = "";
var countPath = "count/";
var reqURL;
var currentPage;
var didHidden = false;
var showDetails = false;
var initialOrder = orderBy;

//execute actions on page load
//  default to self entered information
$(function() {
    loaderIcon();

    // show self entered data (default)
    showSelfEntered();
    $('ul#tabs li:first').addClass("ui-tabs-selected");


    //setup click handler for Show Details links
    for(i=1; i<=max; i++){
        $("#test" + i).children().children().click(function(showhide){
            showhide.preventDefault();
            doToggle(this);
        });
    }

    //tab click - self
    $('#self').click(function(self){
        self.preventDefault();
        loaderIcon();
        $('ul#tabs li').removeClass("ui-tabs-selected");
        $('ul#tabs li:first').addClass("ui-tabs-selected");
        $("div#tabs-content div#emptyresults").hide();
        resetSort();
        showSelfEntered();
    });

    //tab click - clinical
    $('#clinical').click(function(clin){
        clin.preventDefault();
        loaderIcon();
        $('ul#tabs li').removeClass("ui-tabs-selected");
        $('ul#tabs li:nth-child(2)').addClass("ui-tabs-selected");
        $("div#tabs-content div#emptyresults").hide();
        resetSort();
        showClinical();
        toggleDetails();
    });

    //notifier dialog button click
    $(".closebutton").click(function(x){
        x.preventDefault();
        $(this).parent().fadeOut();
    });

    //handle change in sorting
    var changeHandler = function(){
        loaderIcon();
        orderBy = $("#sort option:selected").attr("value");
        if ($("#sort option:selected").hasClass("asc")) {
            descending = 0;
        } else if ($("#sort option:selected").hasClass("desc")){
            descending = 1;
        }
        currentPage = 1;
        page(currentPage);
    }
    $("#sort").change(changeHandler).keypress(changeHandler);

    //setup modal for Add/Change links
    $(".opensFrame").click(function(event) {
        $.closeDOMWindow({closeNow:true});
        $.openDOMWindow({
            modal:1,
            height:500,
            width:500,
            windowSource:'iframe',
            windowSourceId:"",
            windowSourceURL:$(this).attr('href'),
            windowPadding:10,
            overlay:1
        })
        return false;
    });
	
    $("#chkexpand").click( function() {
        if ($("#chkexpand").attr('checked')) {
            showDetails = true;
            toggleDetails();
        } else {
            showDetails = false;
            toggleDetails();
        }
    });
});

//define function to call when a page is clicked
PageClick = function(pageclickednumber) {
    loaderIcon();
    page(pageclickednumber);
};

//correctly display item (null or not) with strName being part of the id of the dom element
function setDisplay(item,strName,position){
    if(item==null || item.length<1) {
        item = "No data";
        $("#target-" + strName + "-" + position).addClass("ntav");
    }
    $("#target-" + strName + "-" + position).text(item);
}

function disableSort() {
    $("#tabs #sort").attr("disabled","disabled");
}

function enableSort() {
    $("#tabs #sort").attr("disabled","");
}

function resetSort() {
    descending = 1;
    orderBy = initialOrder;
    $("#sort option.desc[value='"+orderBy+"']").attr('selected', 'selected');
}

//show or hide details
//if force param exists: we want to revert to unexpanded state regardless (when changing tabs)
function doToggle(elem, force){
    var shown =  false;

    if (force == undefined) {
        //traverse back to targetN level, find toggle div and toggle it
          $(elem).parent().parent().parent().find("div[id^=content-toggle-]").each(function(){
           if ($(this).css("display") == "none"){
               shown = true;
               $(this).show();
               $(this).parent().parent().find("a[id^=toggle-]").removeClass("closed").addClass("open").attr('title','Hide Details');

           } else {
               $(this).hide();
               $(this).parent().parent().find("a[id^=toggle-]").removeClass("open").addClass("closed").attr('title',' Show Details');

           }
           
        });
    } else {
        //traverse back to targetN level, find toggle div and hide it
        $(elem).parent().parent().parent().find("div[id^=content-toggle-]").each(function(){
           $(this).hide();
        });
    }
}

//display notification to user
function notify(message) {
    $(".notificationtext").text(message);
    $("#notifier").show();
    window.setTimeout( function(){$("#notifier").hide();} , 2200);
}

//retrieve number of pages based on count of items
function getPages(){
    //caution- countItems is ajax-effected asynch variable
    return (Math.ceil(countItems/max));
}

//called from iFrame child element to trigger close of modal
function doClose(message){
    ajaxIconHide();
    if (message != undefined) {
        notify(message);
    }
}

//wrapper for behavior after new record is added
function showNewRecord(order) {
    orderBy = order;
    descending = 1;
    $("select#sort option[selected]").removeAttr("selected");
    $("select#sort option[value=" + orderBy + "]").attr("selected","selected");
    $("div#tabs-content div#emptyresults").hide();
    showSelfEntered(true);
}

//lookup id and delete corresponding element
function doDelete(item) {
    if (confirm("Are you sure you wish to delete this record?")) {
        var itemid = parseInt($("#target-id-" + item).text());
        $.ajax({
            type: "DELETE",
            url: apipath + midPath + itemid + "/",
            contentType: "application/json",
            timeout:5000,
            success: function() {
                $("#target" + item).hide();
                notify("Record successfully deleted");
                setTotalCount(currentPage);
            },
            error: function() {
                pagingError("Error deleting record");
            }
        });
    }
    return false;
}

//clean up and display message to handle errors
function pagingError(message) {
    loaderIconHide();
    if (message != undefined) {
        notify(message);
    } else {
        notify("An error has occurred. Please reload the page");
    }
}

//updates min, max, total, + type in paging text section
function doPagingText(){
    var minResults = start;
    if (countItems>0) {
        minResults +=1;
    }
    var maxResults = start+max;
    if (countItems < maxResults){
        maxResults = countItems;
    }
    $("#results-max").text(maxResults);
    $("#results-min").text(minResults);
    $("#results-total").text(countItems);
    $("#results-type").text(resultType);
}

//hides spinning icon in center of screen (or any modal)
function ajaxIconHide() {
    $.closeDOMWindow({closeNow:true});
}

//show first page of self entered results
function showSelfEntered(hasAdded) {
    cleanupAll();
    $(".clinicalSort").hide();
    $(".selfSort").show();
    $(".add").show();
    source = "self";
    setResultType();
    currentPage = 1;
    setTotalCount(currentPage);
    page(currentPage);
}

//show first page of clinical results
function showClinical() {
    $(".selfSort").hide();
    $(".clinicalSort").show();
    $(".add").hide();
    source = "clinical";
    setResultType();
    currentPage = 1;
    setTotalCount(currentPage);
    page(currentPage);
}

//populates resultType variable based on source
function setResultType(passedSource) {
    if (passedSource==undefined) passedSource = source;
    if (passedSource=="self") { resultType = "Self Entered";}
    else if (passedSource=="clinical") { resultType = "Clinical";}
//    else { resultType = "total"; }
}

//this function requests the count of items for the current type of data being displayed
//  once received, the paging and paging text are updated, the empty result notifier is
//  displayed if necessary, and the countItems variable is updated
function setTotalCount(page) {
    var countPath = apipath+midPath+"count";
    if (source=="clinical") countPath = apipath+"/patients/"+patientId+clinPath+"count/";
    $.getJSON(countPath, function(data, textStatus){
        if (textStatus=="success"){
            if (data.count != undefined) {
                countItems = parseInt(data.count);
                if (page != undefined) {
                    $("#pager").pager({ pagenumber: page, pagecount: getPages(), buttonClickCallback: PageClick });
                }
                doPagingText();

                if (countItems < 1) {
                    //no results
                    $("#tabs-content #emptyresults").slideDown();
                }
            }
        } else {
            //unsuccessful retrieval of count
            pagingError("Unable to retrieve record count.");
        }
    });
}

//populates the reqURL param for subsequent ajax requests
function setRequestURL() {
    if (source !="clinical") {
        reqURL = apipath + midPath + "?";
    } else {
        reqURL = apipath + "/patients/" + patientId + clinPath + "?";
    }
    var boolParamSet = false;
    if (start !=undefined && start > -1) {
        reqURL += "start=" + start;
        boolParamSet = true;
    }

    if (max != undefined && max > -1) {
        if(boolParamSet) {
            reqURL += "&";
        } else {
            boolParamSet = true;
        }

        reqURL += "max=" + max;
    }

    if (source!=undefined) {
        if(boolParamSet) {
            reqURL += "&";
        } else {
            boolParamSet = true;
        }

        reqURL += "source=" + source;
    }

    if (descending!=undefined) {
        if(boolParamSet) {
            reqURL += "&";
        } else {
            boolParamSet = true;
        }

        reqURL += "desc=" + descending;
    }

    if (orderBy!=undefined) {
        if(boolParamSet) {
            reqURL += "&";
        }

        reqURL += "orderBy=" + orderBy;
    }
}

//shows spinning icon in center of screen for loading animation
function loaderIcon() {
    $("div#ajax-container").hide();
    var pad = ($("div#ajax-container").height() /2) - 10;
    $("div#progress").css({'padding-top' : pad, 'padding-bottom' : pad});
    $("div#progress").show();
}

function loaderIconHide() {
    $("div#progress").hide();
    $("div#ajax-container").show();
}

var locationIndex = 1;
var locationArray = new Array();
function handleClinical(data, dataCategory ) {
    if (typeof data=="object") {
        for (var indexor in data) {
            handleClinical(data[indexor], indexor); //recurse on object
        }
        if (data.id != undefined) {
            locationIndex++;
        }
    } else {
        if (typeof data=="string") {
            if (/^[a-zA-Z]*$/i.test(dataCategory)) { //test for alpha characters [ignore @uri, etc]
                if (locationArray[locationIndex] == undefined) {
                    locationArray[locationIndex] = locationIndex;
                    prepLocation(locationIndex);
                }
                doClinicalParse(dataCategory, data, locationIndex);
            }
        }
    }
}

function prepLocation(location) {
    $("span#target-title-"+location).html("&nbsp;");
    $("#target"+location).removeClass("self").addClass("clinical").show();
    $("#self-buttons-"+location).hide();
    $("#clinical-buttons-"+location).show();
    $("#content-display-"+location+" .content2").hide();
    $("#content-display-"+location+" .content").prepend("<ul class='content2 clinicalinput'></ul>").show();

    if (!didHidden) {
        $("#content-toggle-"+location+" ul.content2").prepend('<li><div class="w-label">Source</div><span id="target-source-'+location+'">External System</span></li>');
        if (typeof(numhidden) == "undefined") numhidden = 2;
        $("div#content-toggle-"+ location +" .content2 li:nth-child("+ numhidden +") ~ li").hide();
    }

    //toggleDetails takes care of the defaults and user overrides
    toggleDetails();
}

function cleanupAll() {
    $("div[id^=content-toggle-] span[id^=target-source-]").parent().remove();
    $("div[id^=content-toggle-] .content2 li").show();
    $(".clinicalinput").remove();
    $("#target-source-"+location).hide();
    didHidden = false;
}

function doClinicalParse(title, data, location){
    if (clinParse[title] != undefined) {
        if (clinParse[title][0] != undefined) {
            //special attribute - id, title, or dateadded
            switch (clinParse[title][0]) {
                case "id":
                    setDisplay(data,"id",location);
                    break;
                case "dateadded":
                    setDisplay(data,"dateadded",location);
                    break;
                case "source":
                    setDisplay(data,"source",location);
                    break;
                case "title":
                    setDisplay(data,"title",location);
                    $("#target-title-"+location).css({fontWeight:"bold"});
                    break;
                default:
                    setDisplay(data,clinParse[title][0],location);
                    break;
            }

        } else {
            //regular attribute
            if(clinParse[title][1] != undefined) {
                //has human-readable title
                title = clinParse[title][1];
            }
            $("#content-display-"+location+" .clinicalinput").prepend("<li><div class='w-label'>"+ title +"</div><div class='clindata'>"+ data +"</div></li>");
        }
    } else {
        //not defined in clinParse array - spit out raw data
        if (title != null && data != null && location != null) {
            $("#content-display-"+location+" .clinicalinput").prepend("<li><div class='w-label'>"+ title +"</div><div class='clindata'>"+ data +"</div></li>");
        }
    }

}

//switches to a specific page based on status of global variables
function page(page){
    start = (page-1)*max;
    setRequestURL();
    $("#pager").hide();

    $.ajax({
        url:reqURL,
        type:"GET",
        timeout:5000,
        dataType:"json",
        error:function(obj,strError){
            pagingError();
        },
        success:function(data){
            if(typeof(data)!="object") {
                window.location="/login";
                return;
            }
            doPagingText();
            for (i=1; i<=max; i++) {
                $("#target" + i).hide();
            }
            if (source=="clinical") {
                handleClinical(data);
                //cleanup globals
                didHidden = true;
                locationIndex=1;
                locationArray = new Array();
            } else {
                handleData(data);
            }
            //show result list
            currentPage = page;
            $("#pager").pager({ pagenumber: page, pagecount: getPages(), buttonClickCallback: PageClick });
    },
        complete:function(data,strSuccessType){
            $("#pager").show();
            switch (strSuccessType)
            {
                case "timeout":
                    pagingError("The request timed out. Please reload the page and try again");
                    break;
                case "error":
                    pagingError(); //default msg
                    break;
                case "parsererror":
                    if (data.status == 200 || data.status == 302) {
                        document.location.reload();
                    }
                    break;
                //  do nothing on successful page change
                case "notmodified":
                case "success":
                    loaderIconHide();
                    break;
            }
        }
    });
}

//reflect changed data for a specific record
//id refers to data id, not target position
function updateRecord(id){
    var updatedPosition;
    $("#tabs-content").find("span[id^=target-id-]").each(function(){
       if ($(this).text() == id)  {
          var idstring = $(this).attr("id") ;
          updatedPosition = parseInt(idstring.substring(idstring.lastIndexOf('-')+1));
       }
    });

    if (updatedPosition > 0 && updatedPosition <= max) {
        $.ajax({
            url:apipath + midPath + id + "/",
            type:"GET",
            cache:false,
            timeout:5000,
            dataType:"json",
            error:function(obj,strError){
                pagingError();
            },
            success:function(data){
                if(typeof(data)!="object") {
                    window.location="/login";
                    return;
                }
                handleData(data,updatedPosition);
        },
            complete:function(data,strSuccessType){
                $("#pager").show();
                switch (strSuccessType)
                {
                    case "timeout":
                        pagingError("The request timed out. Please reload the page and try again");
                        break;
                    case "error":
                        pagingError(); //default msg
                        break;
                    case "parsererror":
                        if (data.status == 200 || data.status == 302) {
                            document.location.reload();
                        }
                        break;
                    //  do nothing on successful page change
                    case "notmodified":
                    case "success":
                        break;
                }
            }
        });
    } else {
        pagingError();
    }

}

//handle detail expansion on records
function toggleDetails() {
    if (showDetails) {
        $("#widget-container").find("div[id^=content-toggle-]").each(function(){
           $(this).show();
           $(this).parent().parent().find("a[id^=toggle-]").removeClass("closed").addClass("open");

        });
    } else {
        $("#widget-container").find("div[id^=content-toggle-]").each(function(){
           $(this).hide();
           $(this).parent().parent().find("a[id^=toggle-]").removeClass("open").addClass("closed");
        });
    }
}