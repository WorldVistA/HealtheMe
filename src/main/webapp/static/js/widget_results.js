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
var descending = 1;
var countPath = "count/";
var reqURL;
var currentPage;
var showDetails = false;

//execute actions on page load
//  default to self entered information
$(function() {
    loaderIcon();

    // show clinical data (default)
    showClinical();
    $('ul#tabs li:first').addClass("ui-tabs-selected");

    //tab click - clinical
    $('#clinical').click(function(clin){
        clin.preventDefault();
        loaderIcon();
        $("div#tabs-content div#emptyresults").hide();
        showClinical();
    });

    //notifier dialog button click
    $(".closebutton").click(function(x){
        x.preventDefault();
        $(this).parent().fadeOut();
    });

    //handle change in sorting
    var sortHandler = function(){
        loaderIcon();
        orderBy = $("#sort option:selected").attr("value");
        if ($("#sort option:selected").hasClass("asc")) {
            descending = 0;
        } else if ($("#sort option:selected").hasClass("desc")){
            descending = 1
        }
        currentPage = 1;
        page(currentPage);
    }
    
    $("#sort").change(sortHandler).keypress(sortHandler);

    //setup event propagation
    $("tr.resultrow").live("mouseover", function(){$(this).addClass("hover");});
    $("tr.resultrow").live("mouseout", function(){$(this).removeClass("hover");});
    $("span.action").live("click",function(eventObject){eventObject.preventDefault();doToggle(this);});

    $("#chkexpand").click( function() {
        if ($("#chkexpand").attr('checked')) {
            showDetails = true;
        } else {
            showDetails = false;
        }
        toggleDetails();
    });

});

//define function to call when a page is clicked
PageClick = function(pageclickednumber) {
    loaderIcon();
    page(pageclickednumber);
}

//show or hide details
function doToggle(elem, force){
    var show =  false;
    var toggleImg;

    //find toggle div and toggle it
    $(elem).closest("tr").next("tr").each(function(){
       if ($(this).css("display") == "none" || force){
           show = true;
           if (!$(this).hasClass("renderedChildren")) {
                $(this).find(".testContainerRow").show();
                $(this).find("div.loader").show();
                getChildren($(this));
           }
           $(this).show();
       } else {
           $(this).hide();
       }
    });

    //flip image
    if (show) {
        $(elem).closest("tr").addClass("active");
        $(elem).closest("span.toggle").removeClass("closed").addClass("open");
        $(elem).attr("title", "Hide Details");
    } else {
        $(elem).closest("tr").removeClass("active");
        $(elem).closest("span.toggle").removeClass("open").addClass("closed");
        $(elem).attr("title", "Show Details");
    }
}

function getChildren(elem){
    $.ajax({
        url:apipath + midPath + elem.attr("id") + subPath,
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
            $(elem).find("div.loader").remove();
            renderTestJSON(data, elem);
            $(elem).addClass("renderedChildren");
            $(elem).find(".testHeading").show();
            $(elem).find(".ordertemplate").show();
    },
        complete:function(data,strSuccessType){
            switch (strSuccessType)
            {
                case "timeout":
                    pagingError("The request timed out. Please reload the page and try again.");
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

function renderTestJSON(obj,elem){
    for (var key in obj) {
        if(typeof obj[key] == 'object') {
            if (obj[key].id != null) {
                var row = $("table.testTable tr.testtemplate:first").clone().removeClass().addClass("testrow test"+ obj[key].id);
                row.insertBefore($(elem).find("table.testTable tr.testtemplate"));
                renderTestJSON(obj[key], obj[key].id)
            } else {
                renderTestJSON(obj[key], elem)
            }
        }
        else {
            $(".test" + elem + " span[class='" + key + "']").text(obj[key]);
        }
    }
}

//display notification to user
function notify(message) {
    $(".notificationtext").text(message);
    $("#notifier").show();
}

//retrieve number of pages based on count of items
function getPages(){
    //caution- countItems is ajax-effected asynch variable
    return (Math.ceil(countItems/max));
}

//clean up and display message to handle errors
function pagingError(message) {
    loaderIconHide();
    if (message != undefined) {
        notify(message);
    } else {
        notify("An error has occurred. Please reload the page.");
    }
}

//updates min, max, total in paging text section
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
}

//hides spinning icon in center of screen (or any modal)
function ajaxIconHide() {
    $.closeDOMWindow({closeNow:true});
}

//show first page of clinical results
function showClinical() {
    currentPage = 1;
    setTotalCount(currentPage);
    page(currentPage);
}

//this function requests the count of items for the current type of data being displayed
//  once received, the paging and paging text are updated, the empty result notifier is
//  displayed if necessary, and the countItems variable is updated
function setTotalCount(page) {
    var countPath = apipath+midPath+"count";
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
    reqURL = apipath + midPath + "?";
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

function renderResultJSON(obj) {
    for (var key in obj) {
        if(typeof obj[key] == 'object') {
            if (obj[key].id != null) {
                var row = $("tr.rowtemplate").clone().removeClass().addClass("resultrow row" + key).show();
                var row2 = $("tr.detailtemplate").clone().attr("id",obj[key].id).removeClass().addClass("resultdetailrow row" + key + "detail ui-helper-hidden");
                $("tr.rowtemplate").parent().append(row).append(row2);
            }
            renderResultJSON(obj[key]);
        }
        else {
            switch(key){
                case "orderedBy":
                case "orderedByTitle":
                    $("tr:last span[class='" + key + "']").text(obj[key]);
                    break;
                default:
                    $("tr:visible:last span[class='" + key + "']").text(obj[key]);
                    break;
            }
        }
    }
}

function clearResults(){
    $("tr.resultrow").remove();
    $("tr.resultdetailrow").remove();
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
            clearResults();
            doPagingText();
            $("div#tabs-content div#emptyresults").hide();
            renderResultJSON(data);

            //show result list
            if (showDetails) toggleDetails();
            currentPage = page;
            $("#pager").pager({ pagenumber: page, pagecount: getPages(), buttonClickCallback: PageClick });
    },
        complete:function(data,strSuccessType){
            $("#pager").show();
            switch (strSuccessType)
            {
                case "timeout":
                    pagingError("The request timed out. Please reload the page and try again.");
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

//handle detail expansion on records
function toggleDetails() {
    $("tr.resultrow").find("span.action").each(function(){
       doToggle(this,showDetails);
    });
}