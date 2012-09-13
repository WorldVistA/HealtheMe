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
    Document   : index
    Created on : Mar 26, 2012, 11:31:46 AM
    Author     : cmccall
--%>

<%@ include file="/includes/taglibs.jspf" %>
<%@ include file="/includes/inc_displaytag_decorators.jspf" %>
<html>
<head>
    <meta name="requiresJQUI" content="true">
    <title>HealtheMe - Care Notebook - Medical History</title>
    <meta name="requiresJQUI" content="true">
    <meta name="requiresValidation" content="true">
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/widget.css" />
    <link rel="stylesheet" type="text/css" href="${ctx_static}/css/carenotebook.css" />
<!--    <script type="text/javascript" src="${ctx_static}/js/validate.familyhistory.js"></script>-->
    <script language="javascript">
        //set globals
        var ctx_static = "${ctx_static}";
        var patientId = ${it.healthRecordId};
    </script>
    
    <style type="text/css">
        #family_table { margin:20px 0; }
        #family_table .col1 { font-weight:bold; width:200px; }
        #family_table .col2 { width:40px; }
        #family_table .col3 { width:40px; }
        #family_table .col4 { width:126px; }
    </style>
    
</head>
<body id="familyhistory-mh" class="familyhistory">

    <form class="carenotebook" name="familyhistory" id="familyhistory" action="${ctx}/form/care/${ it.healthRecordId }/familyhistory/post/" method="post" enctype="multipart/form-data">

        <h1>Family History</h1>

        <div class="def-container">
            <div class="def-title">Purpose</div>
            This section is meant to provide you with a place to any major historical family illnesses. Updated medical information that you record will help to make sure that your child receives proper medical treatment.
        </div>
        <br />
        <h2>Family Medical History</h2>
            <br />
            <table id="family_table" class="grs1">
                <tbody>
                    <tr>
                        <th class="col1">
                            <div class="d">Family History</div>
                        </th>
                        <th class="col2">
                            <div class="d">Yes</div>
                        </th>
                        <th class="col3">
                            <div class="d">No</div>
                        </th>
                        <th>
                            <div class="d">If Yes, Who</div>
                        </th>
                    </tr>
                    <tr>
                        <td class="col1"><label for="mentalIllness">Mental Illness</label></td>
                        <td class="col2"><input type="radio" name="mentalIllness" value="True" <c:if test="${ it.mentalIllness == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="mentalIllness" value="False" <c:if test="${ it.mentalIllness == 'False' || empty it.mentalIllness }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoMentalIllness" name="whoMentalIllness" value="${ it.whoMentalIllness }" title="whoMentalIllness" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="cerebralPalsy">Cerebral Palsy</label></td>
                        <td class="col2"><input type="radio" name="cerebralPalsy" value="True" <c:if test="${ it.cerebralPalsy == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="cerebralPalsy" value="False" <c:if test="${ it.cerebralPalsy == 'False' || empty it.cerebralPalsy }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoCerebralPalsy" name="whoCerebralPalsy" value="${ it.whoCerebralPalsy }" title="whoCerebralPalsy" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="muscularDystrophy">Muscular Dystrophy</label></td>
                        <td class="col2"><input type="radio" name="muscularDystrophy" value="True" <c:if test="${ it.muscularDystrophy == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="muscularDystrophy" value="False" <c:if test="${ it.muscularDystrophy == 'False' || empty it.muscularDystrophy }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoMuscularDystrophy" name="whoMuscularDystrophy" value="${ it.whoMuscularDystrophy }" title="whoMuscularDystrophy" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="epilepsy">Epilepsy</label></td>
                        <td class="col2"><input type="radio" name="epilepsy" value="True" <c:if test="${ it.epilepsy == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="epilepsy" value="False" <c:if test="${ it.epilepsy == 'False' || empty it.epilepsy }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoEpilepsy" name="whoEpilepsy" value="${ it.whoEpilepsy }" title="whoEpilepsy" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="heartDisease">Heart Disease</label></td>
                        <td class="col2"><input type="radio" name="heartDisease" value="True" <c:if test="${ it.heartDisease == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="heartDisease" value="False" <c:if test="${ it.heartDisease == 'False' || empty it.heartDisease }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoHeartDisease" name="whoHeartDisease" value="${ it.whoHeartDisease }" title="whoHeartDisease" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="diabetes">Diabetes</label></td>
                        <td class="col2"><input type="radio" name="diabetes" value="True" <c:if test="${ it.diabetes == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="diabetes" value="False" <c:if test="${ it.diabetes == 'False' || empty it.diabetes }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoDiabetes" name="whoDiabetes" value="${ it.whoDiabetes }" title="whoDiabetes" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="kidneyDisease">Kidney Disease</label></td>
                        <td class="col2"><input type="radio" name="kidneyDisease" value="True" <c:if test="${ it.kidneyDisease == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="kidneyDisease" value="False" <c:if test="${ it.kidneyDisease == 'False' || empty it.kidneyDisease }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoKidneyDisease" name="whoKidneyDisease" value="${ it.whoKidneyDisease }" title="whoKidneyDisease" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="cancer">Cancer</label></td>
                        <td class="col2"><input type="radio" name="cancer" value="True" <c:if test="${ it.cancer == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="cancer" value="False" <c:if test="${ it.cancer == 'False' || empty it.cancer }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoCancer" name="whoCancer" value="${ it.whoCancer }" title="whoCancer" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="thyroidDisease">Thyroid Disease</label></td>
                        <td class="col2"><input type="radio" name="thyroidDisease" value="True" <c:if test="${ it.thyroidDisease == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="thyroidDisease" value="False" <c:if test="${ it.thyroidDisease == 'False' || empty it.thyroidDisease }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoThyroidDisease" name="whoThyroidDisease" value="${ it.whoThyroidDisease }" title="whoThyroidDisease" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="highBloodPressure">High Blood Pressure</label></td>
                        <td class="col2"><input type="radio" name="highBloodPressure" value="True" <c:if test="${ it.highBloodPressure == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="highBloodPressure" value="False" <c:if test="${ it.highBloodPressure == 'False' || empty it.highBloodPressure }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoHighBloodPressure" name="whoHighBloodPressure" value="${ it.whoHighBloodPressure }" title="whoHighBloodPressure" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="deceasedSiblings">Deceased Siblings</label></td>
                        <td class="col2"><input type="radio" name="deceasedSiblings" value="True" <c:if test="${ it.deceasedSiblings == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="deceasedSiblings" value="False" <c:if test="${ it.deceasedSiblings == 'False' || empty it.deceasedSiblings }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoDeceasedSiblings" name="whoDeceasedSiblings" value="${ it.whoDeceasedSiblings }" title="whoDeceasedSiblings" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="behaviorDisorder">Behavior Disorder</label></td>
                        <td class="col2"><input type="radio" name="behaviorDisorder" value="True" <c:if test="${ it.behaviorDisorder == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="behaviorDisorder" value="False" <c:if test="${ it.behaviorDisorder == 'False' || empty it.behaviorDisorder }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoBehaviorDisorder" name="whoBehaviorDisorder" value="${ it.whoBehaviorDisorder }" title="whoBehaviorDisorder" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="tuberculosis">Tuberculosis</label></td>
                        <td class="col2"><input type="radio" name="tuberculosis" value="True" <c:if test="${ it.tuberculosis == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="tuberculosis" value="False" <c:if test="${ it.tuberculosis == 'False' || empty it.tuberculosis }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoTuberculosis" name="whoTuberculosis" value="${ it.whoTuberculosis }" title="whoTuberculosis" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="hepatitis">Hepatitis</label></td>
                        <td class="col2"><input type="radio" name="hepatitis" value="True" <c:if test="${ it.hepatitis == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="hepatitis" value="False" <c:if test="${ it.hepatitis == 'False' || empty it.hepatitis }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoHepatitis" name="whoHepatitis" value="${ it.whoHepatitis }" title="whoHepatitis" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="metabolicDisease">Metabolic Disease</label></td>
                        <td class="col2"><input type="radio" name="metabolicDisease" value="True" <c:if test="${ it.metabolicDisease == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="metabolicDisease" value="False" <c:if test="${ it.metabolicDisease == 'False' || empty it.metabolicDisease }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoMetabolicDisease" name="whoMetabolicDisease" value="${ it.whoMetabolicDisease }" title="whoMetabolicDisease" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="allergies">Allergies</label></td>
                        <td class="col2"><input type="radio" name="allergies" value="True" <c:if test="${ it.allergies == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="allergies" value="False" <c:if test="${ it.allergies == 'False' || empty it.allergies }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoAllergies" name="whoAllergies" value="${ it.whoAllergies }" title="whoAllergies" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="developmentalDisabilities">Developmental Disabilities</label></td>
                        <td class="col2"><input type="radio" name="developmentalDisabilities" value="True" <c:if test="${ it.developmentalDisabilities == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="developmentalDisabilities" value="False" <c:if test="${ it.developmentalDisabilities == 'False' || empty it.developmentalDisabilities }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoDevelopmentalDisabilities" name="whoDevelopmentalDisabilities" value="${ it.whoDevelopmentalDisabilities }" title="whoDevelopmentalDisabilities" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td class="col1"><label for="traumaticBrainInjury">Traumatic Brain Injury</label></td>
                        <td class="col2"><input type="radio" name="traumaticBrainInjury" value="True" <c:if test="${ it.traumaticBrainInjury == 'True'  }">checked</c:if>></input></td>
                        <td class="col3"><input type="radio" name="traumaticBrainInjury" value="False" <c:if test="${ it.traumaticBrainInjury == 'False' || empty it.traumaticBrainInjury }">checked</c:if>></input></td>
                        <td class="col4"><input type="text" id="whoTraumaticBrainInjury" name="whoTraumaticBrainInjury" value="${ it.whoTraumaticBrainInjury }" title="whoTraumaticBrainInjury" maxlength="50" size="20"></td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <label for="treatmentdescription">Other medical history items:</label>
                            <div style="clear: both"></div>
                            <br />
                            <textarea id="other" name="other" rows="4" cols="20">${ it.other }</textarea>
                        </td>
                    </tr>
                        <td colspan="4">
                            <label for="comments">Additional comments or notes:</label>
                            <div style="clear: both"></div>
                            <br />
                            <textarea id="comments" name="comments" rows="4" cols="20">${ it.comments }</textarea>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br />
            <br />
            <input type="submit" value="Save" title="Save" name="submitform" id="submitform" />
            <input type="reset" value="Reset" />
        </form>
</body>