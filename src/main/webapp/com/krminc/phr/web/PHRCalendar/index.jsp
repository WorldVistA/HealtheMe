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
<%!
// JSP Calendar:--
// Created by Jason Benassi
// jbenassi@lime-light.com
// http://www.wakeboardutah.com
// 7-2002

//Modified for adoption and use in 
%>
<%@ include file="/includes/taglibs.jspf" %>
<%@ page import="java.util.*" %>
<head>
    <meta name="requiresJQUI" content="true">
    <title>HealtheMe - Track Health - Health Calendar</title>
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <style type="text/css">
		#calcenter { font-size:1.2em; }
		#calheader {
			margin:20px 0 10px 0;
		}
	</style>
</head>

<body id="health-calendar">

    <h1>Health Calendar</h1>
    
    <div class="def-container">
        This feature allows you to see what you have scheduled in a calendar view. All data is self entered under 'Appointments &amp; Visits'.
    </div>

    <div id="notifier"></div>

                
<c:set var="currMonth" value="${it.month}" scope="page"/>
<c:set var="currYear" value="${it.year}" scope="page"/>
<%
//Global Vars
String boxSize = "70";  // how big to make the box for the calendar
int currYear = 0; // if it is not retrieved from incoming URL (month=) then it is set to current year
int currMonth = 0; // same as year
Calendar cal = Calendar.getInstance();

Object m = pageContext.getAttribute("currMonth", PageContext.PAGE_SCOPE);
currMonth = Integer.parseInt(m.toString()) - 1; //adjust for 0-based Calendar obj
Object y = pageContext.getAttribute("currYear", PageContext.PAGE_SCOPE);
currYear = Integer.parseInt(y.toString());

cal.set(currYear, currMonth, 1);
%>
<%!
public boolean isDate(int m, int d, int y) // This method is used to check for a VALID date
{
    m -= 1;
    Calendar c = Calendar.getInstance();
    c.setLenient(false);

    try{
        c.set(y,m,d);
        Date dt = c.getTime();
    }
    catch (IllegalArgumentException e){
        return false;
    }
    return true;
}
%>
<%!
public String getDateName (int monthNumber){
    switch (monthNumber){
        case 0:
            return"January";
        case 1:
            return "February";
        case 2:
            return "March";
        case 3:
            return "April";
        case 4:
            return "May";
        case 5:
            return "June";
        case 6:
            return "July";
        case 7:
            return "August";
        case 8:
            return "September";
        case 9:
            return "October";
        case 10:
            return "November";
        case 11:
            return "December";
    }
    return "January";
}
%>
<div id="calheader">
    <div id="calleft" style="float:left; width:150px;text-align:center;"><a href="${ctx_patient}/${it.healthRecordId}/calendar/${it.prevMonthsYear}/${it.prevMonthsMonth}"><< Previous Month</a></div>
    <div id="calright" style="float:right; width:150px; text-align:center;"><a href="${ctx_patient}/${it.healthRecordId}/calendar/${it.nextMonthsYear}/${it.nextMonthsMonth}">Next Month >></a></div>
    <div id="calcenter" style="padding:0px 160px 5px 160px; margin:0px;text-align:center;"><b><%=getDateName (cal.get(cal.MONTH)) + " " + cal.get(cal.YEAR)%></b></div>
</div>

<div align="center">
    <table border="1" width="519" style="border-top:2px; border-color:black; border-collapse: collapse" cellpadding="0" cellspacing="0" bgcolor="#DFDCD8">
  	<tr>
    		<td width="<%=boxSize%>" align="center" nowrap bgcolor="#666666">
    		<font color="#FFFFFF"><b>Sun</b></font></td>
    		<td width="<%=boxSize%>" align="center" nowrap bgcolor="#666666">
    		<font color="#FFFFFF"><b>Mon</b></font></td>
    		<td width="<%=boxSize%>" align="center" nowrap bgcolor="#666666">
    		<font color="#FFFFFF"><b>Tues</b></font></td>
    		<td width="<%=boxSize%>" align="center" nowrap bgcolor="#666666">
   		<font color="#FFFFFF"><b>Wed</b></font></td>
    		<td width="<%=boxSize%>" align="center" nowrap bgcolor="#666666">
    		<font color="#FFFFFF"><b>Thurs</b></font></td>
    		<td width="<%=boxSize%>" align="center" nowrap bgcolor="#666666">
    		<font color="#FFFFFF"><b>Fri</b></font></td>
    		<td width="<%=boxSize%>" align="center" nowrap bgcolor="#666666">
    		<font color="#FFFFFF"><b>Sat</b></font></td>
  	</tr>
<c:set var="allVisits" value="${it.visits}"/>
<c:set var="allExercises" value="${it.exercises}"/>
<%
Calendar c = Calendar.getInstance();

//'Calendar loop
int currDay;
String todayColor;
String dateColor;
int count = 1;
int dispDay = 1;

for (int w = 1; w < 7; w++)
{
%>
<tr>
<%
    for (int d = 1; d < 8; d++)
    {
        if (! (count >= cal.get(c.DAY_OF_WEEK)))
        {

%>
<td width="<%=boxSize%>" height="<%=boxSize%>" valign="top" align="left">&nbsp;</td>
<%
            count += 1;
        }
        else
        {
            if (isDate ( currMonth + 1, dispDay, currYear) ) // use the isDate method
            {
                if ( dispDay == c.get(c.DAY_OF_MONTH) && c.get(c.MONTH) == cal.get(cal.MONTH) && c.get(c.YEAR) == cal.get(cal.YEAR)) // Here we check to see if the current day is today
                {
                    todayColor = "#FFFFCC";
                    dateColor = "#000000";
                } else {
                    todayColor = "#ffffff";
                    dateColor = "#000000";
                }
%>
<td bgcolor="<%=todayColor%>" width="<%=boxSize%>" align="left" height="<%=boxSize%>" valign="top"><font color="<%=dateColor%>"><%=dispDay%></font><br>
    <c:set var="day" value="<%=dispDay%>"/>
    <c:if test="${allVisits[day] != null}">
        <c:forEach var="v" items="${allVisits[day]}">
            <div class="visit" id="${ v.visitId }" style="color:<%=dateColor%>; font-size: .7em;">
                <a href="${ctx_patient}/${it.healthRecordId}/visits" style="color:<%=dateColor%>; font-weight: bold;">${ v.title }</a><br />
                <span style="color:#666666;"><fmt:formatDate pattern="h:mm a" value="${ v.visitTime }" /></span>
            </div>
       </c:forEach>
   </c:if>

    <c:if test="${allExercises[day] != null}">
        <c:forEach var="e" items="${allExercises[day]}">
            <div class="exercise" id="${ e.exerciseId }" style="color:<%=dateColor%>; font-size: .7em;">
                <a href="${ctx_patient}/${it.healthRecordId}/exercise" style="color:<%=dateColor%>; font-weight: bold;">${ e.title }</a><br />
                <span style="color:#666666;"><fmt:formatDate pattern="h:mm a" value="${ e.exerciseTime }" /></span>
            </div>
       </c:forEach>
   </c:if>
</td>
<%
                count += 1;
                dispDay += 1;
            } else {
%>
<td width="<%=boxSize%>" align="left" height="<%=boxSize%>" valign="top">&nbsp;</td>
<%
            }
        }
    }
%>
</tr>
<%
}
%>
</table>
</div>
