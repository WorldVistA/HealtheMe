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
    Created on : Jun 14, 2009, 4:26:50 PM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<html>
<head>
    <title>Immunizations - Edit</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <script type="text/javascript" src="${ctx_static}/js/validate.immunization.js"></script>
    <script type="text/javascript">
        var baseuri = '${ctx_patient}';
        var url = "${apipath}/patients/${it.healthRecordId}/immunizations/${ it.immunizationId }/";

        $(document).ready(function() {
            $("input.datepicker").datepicker({showOn: 'button', buttonImage: '${ctx_static}/images/cal.gif', buttonImageOnly: true, onSelect:function(){$(this).valid();}});

            $("#submit").click(function() {
                var _form = $("#detailform");
                if (_form.valid()) {
                    $(this).attr("disabled","disabled");
                    _form.submit();
                }
                return false;
            });

            $("#cancel").click(function(cancelClick) {
                cancelClick.preventDefault();
                if (confirm("Are you sure you wish to cancel?")) parent.doClose();
            });

            $("#reaction option[value='${it.reaction}']").attr("selected", true);
        });

    </script>
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/iframe.css" media="screen, print"/>

</head>
<body bgcolor="#ffffff" text="#000000" link="#0000cc" vlink="#551a8b" alink="#ff0000">
    <div id="lightBox">
        <div id="m_lightBox">
            <h1>Edit Immunization Record</h1>
               
            <span class="toolSummary">Note: This Immunization record will appear under 'Self Entered' data.</span>

            <form id="detailform" name="detailform" action="./formhandler/" method="post">
            <input type="hidden" id="sourceurl" name="sourceuri" value="${ctx}/immunizations/${it.immunizationId}/edit" />
            <input type="hidden" id="returnurl" name="returnurl" value="${ctx}/immunizations" />
            <input type="hidden" id="patientid" name="patientid" value="${it.healthRecordId}" />
            <input type="hidden" id="datasourceid" name="datasourceid" value="${it.dataSourceId}" />
            <input type="hidden" id="immunizationid" name="immunizationid" value="${it.immunizationId}" />

            <div class="ie">
                <div class="hv_element">
                    <label for="immunizationtype">Immunization Name<span class="required">*</span></label>
                    <input class="hv_element_immunizationtype_input" maxlength="100" id="immunizationtype" name="immunizationtype" value="${it.immunizationType}"/>
                </div>
                <div class="hv_element hv_element_date">
                    <label for="datereceived">Date Received</label>
                    <input class="hv_element_date_input datepicker" maxlength="30" name="datereceived" id="datereceived" value="<fmt:formatDate pattern="MM/dd/yyyy" value="${ it.dateReceived }"/>"/>
                </div>
                <div class="hv_element">
                	<label for="method">Method</label>
                    <select name="method" id="method">
                        <option value="0">- Select -</option>
                        <option value="1" <c:if test="${it.method==1}">selected="selected"</c:if> >Injection</option>
                        <option value="2" <c:if test="${it.method==2}">selected="selected"</c:if> >Inhalant</option>
                        <option value="3" <c:if test="${it.method==3}">selected="selected"</c:if> >By Mouth</option>
                    </select>
                </div>
                <div class="hv_element">
                    <label for="reaction">Reaction</label>
                    <select name="reaction" id="reaction">
                        <option value="">- Select -</option>
                        <option value="Agitation">Agitation</option>
                        <option value="Agranulocytosis">Agranulocytosis</option>
                        <option value="Alopecia">Alopecia</option>
                        <option value="Anaphylaxis">Anaphylaxis</option>
                        <option value="Anemia">Anemia</option>
                        <option value="Anorexia">Anorexia</option>
                        <option value="Anxiety">Anxiety</option>
                        <option value="Apnea">Apnea</option>
                        <option value="Appetite, Increased">Appetite, Increased</option>
                        <option value="Arrhythmia">Arrhythmia</option>
                        <option value="Asthenia">Asthenia</option>
                        <option value="Asthma">Asthma</option>
                        <option value="Ataxia">Ataxia</option>
                        <option value="Athetosis">Athetosis</option>
                        <option value="Brachycardia">Brachycardia</option>
                        <option value="Breast Engorgement">Breast Engorgement</option>
                        <option value="Bronchospasm">Bronchospasm</option>
                        <option value="Cardiac Arrest">Cardiac Arrest</option>
                        <option value="Chest Pain">Chest Pain</option>
                        <option value="Chills">Chills</option>
                        <option value="Coma">Coma</option>
                        <option value="Confusion">Confusion</option>
                        <option value="Congestion,Nasal">Congestion,Nasal</option>
                        <option value="Conjunctival Congestion">Conjunctival Congestion</option>
                        <option value="Constipation">Constipation</option>
                        <option value="Coughing">Coughing</option>
                        <option value="Deafness">Deafness</option>
                        <option value="Delerium">Delerium</option>
                        <option value="Delusion">Delusion</option>
                        <option value="Depression, Mental">Depression, Mental</option>
                        <option value="Depression, Postictal">Depression, Postictal</option>
                        <option value="Depression">Depression</option>
                        <option value="Dermatitis, Contact">Dermatitis, Contact</option>
                        <option value="Dermatitis, Photoallergenic">Dermatitis, Photoallergenic</option>
                        <option value="Dermatitis">Dermatitis</option>
                        <option value="Diaphoresis">Diaphoresis</option>
                        <option value="Diarrhea">Diarrhea</option>
                        <option value="Diplopia">Diplopia</option>
                        <option value="Disturbed Coordination">Disturbed Coordination</option>
                        <option value="Dizziness">Dizziness</option>
                        <option value="Dreaming, Increased">Dreaming, Increased</option>
                        <option value="Drowsiness">Drowsiness</option>
                        <option value="Dry Mouth">Dry Mouth</option>
                        <option value="Dry Nose">Dry Nose</option>
                        <option value="Dry Throat">Dry Throat</option>
                        <option value="Dyspnea">Dyspnea</option>
                        <option value="Dysuria">Dysuria</option>
                        <option value="Ecchymosis">Ecchymosis</option>
                        <option value="Ecg Changes">Ecg Changes</option>
                        <option value="Eczema">Eczema</option>
                        <option value="Edema">Edema</option>
                        <option value="Epigastric Distress">Epigastric Distress</option>
                        <option value="Epistaxis">Epistaxis</option>
                        <option value="Erythema">Erythema</option>
                        <option value="Euphoria">Euphoria</option>
                        <option value="Excitation">Excitation</option>
                        <option value="Extrasystole">Extrasystole</option>
                        <option value="Face Flushed">Face Flushed</option>
                        <option value="Facial Dyskinesia">Facial Dyskinesia</option>
                        <option value="Faintness">Faintness</option>
                        <option value="Fatigue">Fatigue</option>
                        <option value="Feeling Of Warmth">Feeling Of Warmth</option>
                        <option value="Fever">Fever</option>
                        <option value="GI Reaction">GI Reaction</option>
                        <option value="Galactorrhea">Galactorrhea</option>
                        <option value="Generalized Rash">Generalized Rash</option>
                        <option value="Glaucoma">Glaucoma</option>
                        <option value="Gynecomastia">Gynecomastia</option>
                        <option value="Hallucinations">Hallucinations</option>
                        <option value="Headache">Headache</option>
                        <option value="Heart Block">Heart Block</option>
                        <option value="Hematuria">Hematuria</option>
                        <option value="Hemoglobin, Increased">Hemoglobin, Increased</option>
                        <option value="Hives">Hives</option>
                        <option value="Hypersensitivity">Hypersensitivity</option>
                        <option value="Hypertension">Hypertension</option>
                        <option value="Hypotension">Hypotension</option>
                        <option value="Impairment Of Erection">Impairment Of Erection</option>
                        <option value="Impotence">Impotence</option>
                        <option value="Inappropriate Penile Erection">Inappropriate Penile Erection</option>
                        <option value="Insomnia">Insomnia</option>
                        <option value="Irritability">Irritability</option>
                        <option value="Itching, Watering Eyes">Itching, Watering Eyes</option>
                        <option value="Junctional Rhythm">Junctional Rhythm</option>
                        <option value="Labyrinthitis, Acute">Labyrinthitis, Acute</option>
                        <option value="Lacrimation">Lacrimation</option>
                        <option value="Ldh,Increased">Ldh,Increased</option>
                        <option value="Lethargy">Lethargy</option>
                        <option value="Leukocyte Count,Decreased">Leukocyte Count,Decreased</option>
                        <option value="Libido, Decreased">Libido, Decreased</option>
                        <option value="Libido, Increased">Libido, Increased</option>
                        <option value="Miosis">Miosis</option>
                        <option value="Myalgia">Myalgia</option>
                        <option value="Myocardial Infarction">Myocardial Infarction</option>
                        <option value="Nausea, Vomiting">Nausea, Vomiting</option>
                        <option value="Nervousness, Agitation">Nervousness, Agitation</option>
                        <option value="Neutrophil Count, Decreased">Neutrophil Count, Decreased</option>
                        <option value="Nightmares">Nightmares</option>
                        <option value="Optic Atrophy">Optic Atrophy</option>
                        <option value="Orgasm,Inhibited">Orgasm,Inhibited</option>
                        <option value="Oronasalpharyngeal Irritation">Oronasalpharyngeal Irritation</option>
                        <option value="Pain, Joint">Pain, Joint</option>
                        <option value="Palpitations">Palpitations</option>
                        <option value="Pancytopenia">Pancytopenia</option>
                        <option value="Paresthesia">Paresthesia</option>
                        <option value="Parkinsonian-Like Syndrome">Parkinsonian-Like Syndrome</option>
                        <option value="Photosensitivity">Photosensitivity</option>
                        <option value="Possible Reaction">Possible Reaction</option>
                        <option value="Priapism">Priapism</option>
                        <option value="Prolonged Penile Erection">Prolonged Penile Erection</option>
                        <option value="Pruritis">Pruritis</option>
                        <option value="Ptosis">Ptosis</option>
                        <option value="Purpura">Purpura</option>
                        <option value="Rales">Rales</option>
                        <option value="Rash,Papular">Rash,Papular</option>
                        <option value="Rash">Rash</option>
                        <option value="Respiratory Distress">Respiratory Distress</option>
                        <option value="Retrograde Ejaculation">Retrograde Ejaculation</option>
                        <option value="Rhinitis">Rhinitis</option>
                        <option value="Rhinorrhea">Rhinorrhea</option>
                        <option value="Rhonchus">Rhonchus</option>
                        <option value="S-T Changes,Transient">S-T Changes,Transient</option>
                        <option value="Seizures, Tonic-Clonic">Seizures, Tonic-Clonic</option>
                        <option value="Seizures">Seizures</option>
                        <option value="Self-Deprecation">Self-Deprecation</option>
                        <option value="Severe Rash">Severe Rash</option>
                        <option value="Shortness Of Breath">Shortness Of Breath</option>
                        <option value="Sinus Brachycardia">Sinus Brachycardia</option>
                        <option value="Sneezing">Sneezing</option>
                        <option value="Somnolence">Somnolence</option>
                        <option value="Speech Disorder">Speech Disorder</option>
                        <option value="Swelling (Non-Specific)">Swelling (Non-Specific)</option>
                        <option value="Swelling-Eyes">Swelling-Eyes</option>
                        <option value="Swelling-Lips">Swelling-Lips</option>
                        <option value="Swelling-Throat">Swelling-Throat</option>
                        <option value="Syncope">Syncope</option>
                        <option value="Tachycardia">Tachycardia</option>
                        <option value="Thrombocytopenia">Thrombocytopenia</option>
                        <option value="Tremors">Tremors</option>
                        <option value="Unconsciousness">Unconsciousness</option>
                        <option value="Urinary Flow, Delayed">Urinary Flow, Delayed</option>
                        <option value="Urinary Frequency, Increased">Urinary Frequency, Increased</option>
                        <option value="Urinary Frequency">Urinary Frequency</option>
                        <option value="Urinary Retention">Urinary Retention</option>
                        <option value="Urticaria">Urticaria</option>
                        <option value="Uveitis">Uveitis</option>
                        <option value="Vertigo">Vertigo</option>
                        <option value="Vision, Blurred">Vision, Blurred</option>
                        <option value="Visual Disturbances">Visual Disturbances</option>
                        <option value="Vomiting">Vomiting</option>
                        <option value="Weakness">Weakness</option>
                        <option value="Weight Gain">Weight Gain</option>
                        <option value="Wheezing">Wheezing</option>
                    </select>
                </div>
                <div class="hv_element_comments">
                    <label for="comments">Comments</label>
                    <textarea id="comments" name="comments" rows="3">${ it.comments }</textarea>
                </div>
            </div>
            <div id="lightBoxActions">
                <div class="req-marker">
                    <span class="required">*</span> Required field
                </div>
                <div class="phr-input-button-align-right">
                    <input id="cancel" type="button" class="" value="Cancel" />
                    <input id="submit" type="submit" class="" value="Save" />
                </div>
            </div>

            </form>

        </div>
    </div>
    </body>
</html>