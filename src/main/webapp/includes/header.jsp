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
    Document   : header.jsp
    Created on : May 25, 2009, 3:45:05 PM
    Author     : Daniel Shaw (dshaw.com)
--%>

<div id="header2">
    <div id="header-inset" style="position:relative;">
        <!-- use a "real image" instead of a background to print properly -->
        <a id="header-logo" href="${ctx}/"><img src="${ctx_static}/images/logo.png" alt="HealtheMe"></a>
        <ul id="tabnav">
            <li id="tabnav-home" class="firsttab"><a href="${ctx}/default" title="Home">Home</a></li>
            <li id="tabnav-about"><a href="${ctx}/about" title="About HealtheMe">About HeM</a></li>
            <li id="tabnav-faq"><a href="${ctx}/faq" title="Frequently Asked Questions">FAQs</a></li>
            <li id="tabnav-contact"><a href="${ctx}/contact" title="Contact HealtheMe">Contact HeM</a></li>
            <li id="tabnav-help"><a href="${ctx}/help" title="Get help with HealtheMe">Help</a></li>
            <li id="tabnav-search">
                <form action="#" method="post">
                    <input type="text" name="search" value="Search" disabled />
                    <input type="image" src="${ctx_static}/images/search-button.gif" value="Go" disabled />
                </form>
            </li>
        </ul>
    </div>
</div><!-- /#header2 -->