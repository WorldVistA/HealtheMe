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
    Document   : identifiers.jsp
    Created on : Nov 28, 2009, 1:28:45 AM
    Author     : Daniel Shaw (dshaw.com)
--%>

<%@ include file="/includes/taglibs.jspf" %>

<%@ include file="/includes/components/admin/inc_ctx_base_config.jspf" %>
<c:set var="lnavsub1" value="User Identifiers"/>
<c:set var="pagetitle" value="${ it.fullName }${tsep}${ lnavsub1 }"/>
<%-- variable(s) bottomnav --%>
<c:set var="isPatient" value="${ it.isPatient }"/>
<c:set var="isLockedOut" value="${ it.isLockedOut }"/>


<head>
    <title>${ manageTitle }${tsep}${ pagetitle }</title>

    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">

    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/manage.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/manage-additions.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/paging.css" />
    <style type="text/css">
        /*.manage-identifiers #user-photo { top: 20px; right: 20px }*/
        
        .identifier-container { height: 500px }
		.ui-widget-content { 
			background:#F9F9F1;
			border-color:#DBD4AB;
			
		}
		.ui-state-active, .ui-widget-content .ui-state-active {
			background:#F9F9F1;
			border-color:#DBD4AB;
		}
        .datatable { background-color: #FFFFFF }
        /* configure ajax elements. both start hidden. */
        .errors { display: none; margin: .6em 0 1.2em; }
        #ajax-container,
        #progress { height: 220px }
        .progress { display: none; }
        #progress, .progress { margin: .6em 0 1.2em; }
        #progress-msg, .progress-msg  { font-size: 1.1em; font-weight: bold; }

        #patient-data, .patient-data { padding: 20px 0 10px }
        #patient-data .submitted, .patient-data .submitted td { background-color: teal }

        #resources { padding: 10px 0 }
        #resources fieldset { margin: 0 0 25px; border:1px solid #DBD4AB; }
        #resources legend { margin: 0 10px; padding: 0 10px; }
        #resources .resource-data { padding: 10px }

        .mini-button { margin: 2px 10px; background-color: #AAA !important; }
        .match { background-color: #060 !important; }
        .warn { background-color: #FC0 !important; }
        .stop { background-color: #C00 !important; }
    </style>

    <%-- hem.js defines core functionality for health-e-me --%>
    <script type="text/javascript" src="${ctx_static}/js/hem.js"></script>
    <%@ include file="/includes/inc_hem_js.jspf" %>

    <%-- NOTE: temporarily removed validation preprocessing. Doesn't work will with multiple forms.
    <script type="text/javascript" src="${ctx_static}/js/validate.admin.identifiers.js"></script>--%>
    <script  type="text/javascript">
        var user = {};
        user.id = '${ it.userId }';
        user.name = '${ it.fullName }';
        user.nameEmrFormat = '${fn:toUpperCase(it.lastName)},${fn:toUpperCase(it.firstName)} ${fn:toUpperCase(it.middleName)}';
        user.nameEmrFormat = $.trim(user.nameEmrFormat);
        user.primaryHealthRecord = '${ it.primaryHealthRecord.healthRecordId }';
        user.gender = '${ it.primaryHealthRecord.fullGender }';
        user.dob = '${ it.primaryHealthRecord.dateOfBirthString }';
        user.address1 = '${ it.primaryAddress.address1 }';
        user.address2 = '${ it.primaryAddress.address2 }';
        user.address3 = '${ it.primaryAddress.address3 }';
        user.city = '${ it.primaryAddress.city }';
        user.state = '${ it.primaryAddress.state }';
        user.zip = '${ it.primaryAddress.zip }';

        user.userUriSegment = '/users/'+user.id+'/';
        user.resourcesApiUri = HEM.env.apipath + user.userUriSegment + 'resources/';
        user.healthrecordApiUri = HEM.env.apipath + user.userUriSegment + 'healthrecords/' + user.primaryHealthRecord;
        //user.identifiersApiUri =  user.healthrecordApiUri+'/recordidentifiers/';

        var errorMessages = {
            identifierStopCondition : 'Unable associate identifiers. Gender and/or Date of Birth do not match.'
        }
    </script>
    <script type="text/javascript">
        // Check to make sure indexOf is implemented (!!IE6).
        if (!Array.indexOf) {
          Array.prototype.indexOf = function(elem) {
            var i=0,
                length=this.length;
            for (; i < length; i++) {
                if (this[i] === elem ) {
                  return i;
                }
            }
            return -1;
          };
        }

        /*
         * Progress content used by both all ajax loading.
         */
        var progressContent = '\
            <span class="ajaxload"><img src="'+HEM.env.ctx_static+'/css/img/ajax-loader.gif" class="ajaxload"/></span>\
            <span class="progress-msg">NOTE: It may take up to a minute to retrieve this data.</span><!--/#progress-msg-->';
        /*
         * Resource form fields.
         */
        var resourceFormFields = '\
            <input type="text" class="lookup-value" name="value" maxlength="32" tabindex="1" title=""/>\
            <input type="submit" value="Lookup Identifier" class="submit phr-input-button"/>';
        /*
         * Array of resource ids.
         */
        var resourceList = [];
        /*
         * Remote data cache to store loaded json data in accessible location.
         */
        var remoteDataCache;

        /*
         * Load Available Resource Data.
         */
        function loadResources() {
            // show loader
            $('#progress').html(progressContent).show();
            // setup uri
            var uri = user.resourcesApiUri;
            /*
             * Ajax call to API to get active Resources.
             */
            $.getJSON(uri, function(data) {
                var resources = '';
                if (data.resource) {
                    resources += '<div id="accordion" class="ui-accordion ui-widget ui-helper-reset" role="tablist">\n';
                    if(data.resource.length && data.resource.length > 1) {
                        $.each(data.resource, function(i,resource) {
                            resources += outputResource(resource);
                        });
                    } else {
                        resources += outputResource(data.resource);
                    }
                    resources += '</div><!--/#accordian-->\n';
                } else {
                    resources = '<b>No active resources configured for this system.</b>';
                }
                $("#resources").append(resources);
                $('#progress').hide();
                loadIdentifiers();
                /*
                 * Activate accordian
                 */
                $("#accordion").accordion();
                /*
                 * Focus on form input.
                 */
                $(".lookup-value:first").focus();
            });
        }

        /*
         * Prepare resource containers and output.
         */
        function outputResource(res) {
            var resourceOutput = '';
            if (res.id) {
                // load id in array
                resourceList.push(res.id);
                var helpText = 'Identifier Lookup is currently disabled.</div><!--/#resource-'+res.id+'-help-->';
                // build fieldset for resource
                // TODO: remove Hard Coded resource identifier help description.
                resourceOutput += '<h3 class="ui-accordion-header ui-helper-reset \
                    ui-state-default ui-corner-all" role="tab" aria-expanded="false" \
                    tabindex="-1"><span class="ui-icon ui-icon-triangle-1-e"/>\
                    <a href="#" tabindex="-1">'+res.displayName+'</a></h3>\n\
                    <div id="resource-'+res.id+'-container"\
                        class="identifier-container ui-accordion-content \
                        ui-helper-reset ui-widget-content ui-corner-bottom \
                        ui-accordion-content-active" role="tabpanel">\n\
                    <div id="resource-'+res.id+'-help" class="resource-help">\
                    '+helpText+'\
                    <form id="resource-'+res.id+'-assigned-identifiers-form" class="assigned-identifiers-form">\
                    <fieldset id="resource-'+res.id+'-assigned-identifiers" class="assigned-identifiers">\n\
                    <legend>Assigned Identifiers</legend>\
                    <div id="resource-'+res.id+'-identifiers" class="resource-data"></div>\
                    </fieldset></form><!--/.assigned-identifiers-form-->\n\
                    <form id="resource-'+res.id+'-form" class="identifierform">\
                    <input type="hidden" name="resource" value="'+res.id+'"/>\
                    '+resourceFormFields+'</form><!--/.identifierform-->\n\
                    <div id="errors-'+res.id+'" class="errors"></div><!--/#errors-'+res.id+'-->\
                    <div id="progress-'+res.id+'" class="progress">\
                    '+progressContent+'</div><!--/#progress-'+res.id+'-->\
                    <div id="patient-data-'+res.id+'" class="patient-data">\
                    </div><!--/#patient-data-'+res.id+'-->\
                    </div><!--/.ui-accordion-content-->\n';
            }
            return resourceOutput;
        }

        /*
         * Load identifiers.
         */
        function loadIdentifiers() {
            $.each(resourceList, function(i, resId) {
                loadResourceIdentifiers(resId);
            });
        }

        /*
         * Load resource identifiers.
         */
        function loadResourceIdentifiers(resourceId) {
            // setup uri
            var uri = user.healthrecordApiUri+'/resources/'+resourceId+'/recordidentifiers/';
            // get existing resource identifiers
            $.getJSON(uri, function(data) {
                var identifiers = '';
                if (data.recordIdentifier) {
                    if(data.recordIdentifier.length && data.recordIdentifier.length > 1) {
                        $.each(data.recordIdentifier, function(i,thisIdentifier) {
                            identifiers += outputIdentifiers(thisIdentifier);
                        });
                    } else {
                        identifiers += outputIdentifiers(data.recordIdentifier);
                    }
                }
                if (identifiers.length == 0) {
                    identifiers = '<span class="no-identifiers">No identifiers registered for this user from this source.</span>';
                } else {
                    // load identifiers
                }
                $('#resource-'+resourceId+'-identifiers').html(identifiers);
            });
        }

        /*
         * Prepare identifier output.
         */
        function outputIdentifiers(identifier) {
            var identifiers = '';
            if (identifier.id) {
                identifiers += '<b>'+identifier.identifierName+'</b>\
                    <span class="resource-identifier">'+identifier.identifierValue+'</span>\
                    <input type="hidden" name="identifier" value="'+identifier.id+'"/>\
                    <br/>';
            }
            return identifiers;
        }

        /*
         * Display error messages within a specific selector.
         */
        function displayError(errorText, selector) {
            if(!selector) {
               var selector = '#resources';
            }
            $(selector).append('<div class="error">'+errorText+'</div>');
        }

        function reformatDob(dob) {
            var a = dob.split('-');
            return a[1]+'/'+a[2]+'/'+a[0];
        }

        // TODO: Not used.
        function errorHandler(xhr) {
            if (xhr.responseText) {
                var err = JSON.parse(xhr.responseText);
                if (err) {
                    error(err);
                } else {
                    error({ Message: "Unknown server error." })
                }
            }
            return;
        }

        // TODO: Not used.
        function detectLogout(dataState) {
            if(typeof(dataState) != "object") {
                window.location = ctx + "/login";
                return;
            }
        }
    </script>
    <script type="text/javascript">
        /**
         * Patient list form submit handler.
         */
        function patientConfirmationFormSubmit() {
            $('#patient-confirmation-form').live("submit", function() {
                /*
                 * Configure key DOM elements.
                 */
                var $thisForm = $(this);
                /*
                 * Clear errors.
                 */
                $thisForm.children('.errors').empty().hide();
                /*
                 * Set resource identifier.
                 */
                var resourceId = $thisForm.find('[name=resource]').val()
                /*
                 * API URI.
                 */
                var identifiersApiUri = user.healthrecordApiUri+'/resources/'+resourceId+'/recordidentifiers/';
                /*
                 * Create holding array for HRN and IEN.
                 */
                var identifiers = [];
                /*
                 * Build hrn JSON object and insert into array.
                 */
                var hrnIdentifier = JSON.stringify({
                    "@uri": identifiersApiUri,
                    "healthRecordId" : user.primaryHealthRecord,
                    "identifierName" : 'HRN',
                    "identifierValue" : $thisForm.find('[name=hrn]').val(),
                    "resourceId" : resourceId
                }, null, 1);
                identifiers.push(hrnIdentifier);
                /*
                 * Build ien JSON object and insert into array.
                 */
                var ienIdentifier = JSON.stringify({
                    "@uri": identifiersApiUri,
                    "healthRecordId" : user.primaryHealthRecord,
                    "identifierName" : 'IEN',
                    "identifierValue" : $thisForm.find('[name=ien]').val(),
                    "resourceId" : resourceId
                }, null, 1);
                identifiers.push(ienIdentifier);
                /*
                 * Loop through array of identifiers and POST to server.
                 */
                $.each(identifiers, function(i,identifier) {
                    $.ajax({
                        type: "POST",
                        url: identifiersApiUri,
                        data: identifier,
                        contentType: "application/json",
                        error: function(obj,strError) {
                            displayError(
                                "An error occurred. Unable to save identifier.",
                                $thisForm.children('.errors')
                            );
                            $thisForm.children('.errors').show();
                        },
                        success: function() {
                            loadResourceIdentifiers(resourceId);
                            $('#patient-confirmation-form').hide();
                            $('#resource-'+resourceId+'-container').focus();
                        }
                    });
                });
                return false;
            });
        }

        /**
         * Patient list form submit handler.
         */
        function patientListFormSubmit() {
            $('.patient-list-form').live("submit", function() {
                /*
                 * Configure key DOM elements.
                 */
                var $thisForm = $(this);
                var $input = $thisForm.find('input[name=hrn]:checked');
                /*
                 * If nothing selected, notfify user.
                 */
                if (!$input) {
                    alert("Please select a patient first.");
                    return false;
                };
                /*
                 * Clear errors.
                 */
                $thisForm.children('.errors').empty().hide();
                /*
                 * Select parent row (tr).
                 */
                var $row = $input.parent();
                /*
                 * Set resource identifier.
                 */
                var resourceId = $row.find('[name=resource]').val()

                renderPatientConfirmationTable(resourceId, $thisForm);
            });
        }

        /**
         *
         */
        function renderPatientConfirmationTable(resourceId, $patientForm) {

            var patient = remoteDataCache;
            
            if (!$patientForm || !patient || !patient.hrn) {
                alert('Error: unknown form.')
                return false;
            }
            var confirmationTable = '<form id="patient-confirmation-form">\
                <table id="patienttable" class="grid stripe table">\
                <tr><td width="120">&nbsp;</td><th width="20">&nbsp;</th>\
                <th>Patient Demographics</th>\
                <th>Resource Patient Data</th></tr>\
                </table>\
                <div class="errors" style="margin:auto"></div><!--/.errors-->\
                </form>';

            $patientForm.replaceWith(confirmationTable);

            var $thisForm = $('#patient-confirmation-form'),
                row, rows = [],
                enableConfirmation = true,
                gender_status,
                dob_status,
                stopErrorMessage = errorMessages.identifierStopCondition;

            // hrn (this is purely cosmetic - always matches)
            rows.push('<tr><th class="hrn">HRN</th>\
                <td class="\
                '+(patient.hrn == patient.hrn ? 'match': 'stop')+'\
                ">&nbsp;</td>\
                <td class="hrn">'+patient.hrn+'</td>\
                <td class="hrn">'+patient.hrn+'</td></tr>');
            // name
            rows.push('<tr><th class="name">Name</th>\
                <td class="\
                '+(user.nameEmrFormat.toUpperCase() == patient.name.toUpperCase() ? 'match': 'warn')+'\
                ">&nbsp;</td>\
                <td class="name">'+user.nameEmrFormat+'</td>\
                <td class="name">'+patient.name+'</td></tr>');
            // gender
            if (user.gender.toUpperCase() == patient.gender.toUpperCase()) {
                gender_status = 'match';
            } else {
                gender_status = 'stop';
                enableConfirmation = false;
            }
            rows.push('<tr><th class="gender">Gender</th>\
                <td class="'+gender_status+'">&nbsp;</td>\
                <td class="gender">'+user.gender.toUpperCase()+'</td>\
                <td class="gender">'+patient.gender+'</td></tr>');
            // dob
            if (user.dob === patient.dob) {
                dob_status = 'match';
            } else {
                dob_status = 'stop';
                enableConfirmation = false;
            }
            rows.push('<tr><th class="dob">Date of Birth</th>\
                <td class="\
                '+dob_status+'\
                ">&nbsp;</td>\
                <td class="dob">'+user.dob+'</td>\
                <td class="dob">'+patient.dob+'</td></tr>');
            // address
            var userAddress = user.city+', '+user.state+' '+user.zip;
            var patientAddress = patient.city+', '+patient.state+' '+patient.zip;
            userAddress = userAddress.toUpperCase();
            patientAddress = patientAddress.toUpperCase();
            rows.push('<tr><th class="address">Address</th>\
                <td class="\
                '+(userAddress == patientAddress ? 'match': 'warn')+'\
                ">&nbsp;</td>\
                <td class="address">'+userAddress+'</td>\
                <td class="address">'+patientAddress+'</td></tr>');
            // submit
            if (enableConfirmation) {
                rows.push('<tr><td align="right" colspan="4">\
                    <input type="hidden" value="'+resourceId+'" name="resource">\
                    <input type="hidden" value="'+patient.hrn+'" name="hrn">\
                    <input type="hidden" value="'+patient.ien+'" name="ien">\
                    <input type="submit" class="submit phr-input-button" \
                        value="Save Identifier(s)"></td></tr>');
            } else {
                rows.push('<tr><td align="right" colspan="4" class="error">\
                    '+stopErrorMessage+'</td></tr>');
            }
            $.each(rows, function(i,output) {
                $('#patienttable').append(output).show();
            });
            $('#patient-confirmation-form .errors').width($('#patienttable').width());
        }

        /**
         * Identifier form submit handler.
         */
        function identifierFormSubmit() {
            $('.identifierform').live("submit", function() {
                var $thisForm = $(this),
                    thisContainer = $(this).parent(),
                    existing = thisContainer.find('.resource-identifier'),
                    existingIdentifiers = [],
                    resource = $thisForm.children('[name=resource]').val(),
                    identifier = $thisForm.children('[name=identifier]').val(),
                    value = $thisForm.children('[name=value]').val();

                // put identifiers in a array
                existing.each(function(i,id) { existingIdentifiers.push($(id).text()) });

                // make sure value was entered
                if (!value || value.length == 0) {
                    $thisForm.children('[name=value]').focus();
                    return false;
                } else {
                    // Clear errors.
                    $(thisContainer).children('.errors').empty().hide();
                    // Remove existing patient list.
                    $(thisContainer).children('.patient-data').empty().hide();
                }

                // mae sure value has not already been assigned
                if (existingIdentifiers.indexOf(value) > -1) {
                    // Remove existing patient list.
                    displayError('NOTE: "'+value+'" already defined for this patient.', $(thisContainer).children('.errors'));
                    $(thisContainer).children('.errors').show();
                    return false;
                } else {
                    // Display loader.
                    $(thisContainer).children('.progress').show();
                    // Disable submit.
                    $thisForm.children('input[type=submit]').attr("disabled","disabled");
                }

                var url = user.healthrecordApiUri+'/resources/'+resource+'/lookup/'+identifier+'/'+value+'/';

                $.ajax({
                    url: url,
                    type: "GET",
                    timeout: HEM.env.defaultTimeout,
                    dataType: "json",
                    error: function(obj,strError){
                        $(thisContainer).children('.progress').hide();
                        displayError("Data request timed out.", $(thisContainer).children('.errors'));
                        $(thisContainer).children('.errors').show();
                    },
                    success: function (data) {
                        var patientlist = '';
                        var patienttable = '<form \
                                id="resource-'+resource+'-patient-list-form" \
                                class="patient-list-form">\
                            <table id="patienttable" class="grid stripe table">\
                            <thead>\
                            <tr>\
                            <th class="hrn">HRN</th>\
                            <th class="name">Name</th>\
                            <th class="gender">Gender</th>\
                            <th class="dob">Date of Birth</th>\
                            </tr>\
                            </thead>\
                            <tbody id="patientlistings">';

                        // *** loop through user listings
                        $.each(data.patients, function(i,patient) {
                            patient.dob = reformatDob(patient.dob);
                            remoteDataCache = patient;
                            if (patient.hrn) {
                                patientlist += '<tr id="patientlistings-'+patient.ien+'">\
                                    <td class="hrn">\
                                    <input type="radio" name="hrn" value="'+patient.hrn+'" id="'+patient.hrn+'"\n\
                                    '+(value.toUpperCase() == patient.hrn.toUpperCase() ? 'checked' : '')+' />\
                                    <label for="'+patient.hrn+'"> '+patient.hrn+'</label>\
                                    <input type="hidden" name="resource" value="'+resource+'"/>\
                                    <input type="hidden" name="hrn" value="'+patient.hrn+'"/>\
                                    <input type="hidden" name="ien" value="'+patient.ien+'"/>\
                                    </td>\
                                    <td class="name">'+patient.name+'</td>\
                                    <td class="gender">'+patient.gender+'</td>\
                                    <td class="dob">'+patient.dob+'</td>\
                                    </tr>';
                            }
                        });
                        if (patientlist && patientlist.length > 0) {
                            patienttable += patientlist;
                            patienttable += '<tr><td colspan="4" align="right">\
                            <input type="submit" value="Select" class="submit phr-input-button"/>';
                        } else {
                            patienttable += '<tr><td colspan="4" align="center">\
                            No results found for this identifier. Try again.';
                        }
                        patienttable += '</td></tr></tbody></table>\
                            <div id="resource-'+resource+'-patient-list-form-errors" \
                            class="errors" style="margin:auto"></div><!--/.errors-->\
                            </form>';
                        $(thisContainer).children('.patient-data').html(patienttable);
                        $(thisContainer).children('.progress').hide();
                        $(thisContainer).children('.patient-data').show();
                        $('#resource-'+resource+'-patient-list-form-errors')
                            .width($('#patienttable').width());
                    },
                    complete: function(data,strSuccessType){
                        // Hide progress bar on completion.
                        $thisForm.children('.progress').hide();
                        // Renable submit button.
                        $thisForm.children('input[type=submit]').removeAttr("disabled");
                        switch (strSuccessType)
                        {
                            case "timeout":
                                displayError('Request took too long, please try again.', $thisForm.children('.errors'));
                                $thisForm.children('.errors').show();
                                break;
                            case "error":
                                displayError("Error requesting data.", $thisForm.children('.errors'));
                                $thisForm.children('.errors').show();
                                break;
                            case "parsererror":
                                displayError("Malformed data returned.", $thisForm.children('.errors'));
                                $thisForm.children('.errors').show();
                                break;
                            case "notmodified":
                            case "success":
                                break;
                        }
                    }
                });

                return false;
            });
        }

        /**
         * Initialize event bindings.
         *
         * These are (@link: Events/live http://docs.jquery.com/Events/live) bindings.
         */
        function initEventBindings() {
            identifierFormSubmit();
            patientListFormSubmit();
            patientConfirmationFormSubmit();
        }
    </script>
    <script type="text/javascript">
        $(document).ready(function() {
            HEM.user.image(user.gender);
            initEventBindings();
            loadResources();
        });
    </script>
</head>
<body id="manage-users" class="manage manage-identifiers">

    <%@ include file="/includes/components/admin/inc_manage_leftnav.jspf" %>

    <div id="user-display-panel">

        <h1>Identifiers: <span class="user-fullname">${ it.fullName }</span></h1>

        <div class="section-info">
            Assign EMR identifiers for user.
        </div>


        <div id="user-info" class="section-panel">

            <div id="user-photo">
                <div id="profile-photo"></div>
                <p class="user-photo-text">${ it.firstName }</p>
            </div>

            <h2 class="profile-section-heading">${ it.fullName }</h2>

            <div class="gender"><b>Gender:</b> <span class="user-gender">${ it.primaryHealthRecord.fullGender }</span></div>
            <div class="dob"><b>Date of Birth:</b> ${ it.primaryHealthRecord.dateOfBirthString }</div>
            
            <div class="clear"> </div>

        </div><!-- /.section-panel -->

        <div id="profile-container">

            <div id="resources"></div><!-- /#resources -->
            <div id="progress"></div><!-- /#progress -->

            <div class="clear"></div>
        </div><!-- /#profile-container -->

        <%@ include file="/includes/components/admin/inc_manage_bottomnav.jspf" %>

    </div><!-- /#user-display-panel -->

</body>
