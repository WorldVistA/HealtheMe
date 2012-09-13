/**
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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krminc.phr.domain.clinical;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Generic class for all result type objects
 * contains common attributes which can be overridden by
 * class that extend this class
 * @author chaz
 */
@MappedSuperclass
public  class GenericResultTest implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CcrDocument.class)
    @JoinColumn(name = "ccr_id", nullable = false)
    protected CcrDocument ccrDocument;

    @Column(name = "data_object_id")
    protected String dataObjectId;

    @Column(name = "description_code_value")
    protected String descriptionCode;

    @Column(name = "description_coding_system")
    protected String descriptionCodingSystem;

    @Column(name = "description_text")
    protected String descriptionText;

    @Column(name="display_text")
    protected String displayText;

    @Column(name="internal_result_name")
    protected String internalResultName;

    @Column(name="testresult_value")
    protected String testResultValue;

    @Column(name="testresult_units_unit")
    protected String testResultUnit;

    @Column(name="normalresult_normal_description_text")
    protected String normalResultDescription;

    @Column(name="flag_text")
    protected String flagText;

    @Column(name="notes")
    protected String notes;

    @Transient
    private Date dateAdded;

    public GenericResultTest() {
    }

    public CcrDocument getCcrDocument() {
        return ccrDocument;
    }

    public String getDataObjectId() {
        return dataObjectId;
    }

    public String getDescriptionCode() {
        return descriptionCode;
    }

    public String getDescriptionCodingSystem() {
        return descriptionCodingSystem;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setCcrDocument(CcrDocument ccrDocument) {
        this.ccrDocument = ccrDocument;
    }

    public void setDataObjectId(String dataObjectId) {
        this.dataObjectId = dataObjectId;
    }

    public void setDescriptionCode(String descriptionCode) {
        this.descriptionCode = descriptionCode;
    }

    public void setDescriptionCodingSystem(String descriptionCodingSystem) {
        this.descriptionCodingSystem = descriptionCodingSystem;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

   public String getDisplayName() {
        return displayText;
    }

    public void setDisplayName(String displayName) {
        this.displayText = displayName;
    }

    public String getFlagText() {
        return flagText;
    }

    public void setFlagText(String flagText) {
        this.flagText = flagText;
    }

    public String getInternalResultName() {
        return internalResultName;
    }

    public void setInternalResultName(String internalResultName) {
        this.internalResultName = internalResultName;
    }

    public String getNormalResultDescription() {
        return normalResultDescription;
    }

    public void setNormalResultDescription(String normalResultDescription) {
        this.normalResultDescription = normalResultDescription;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTestResultUnit() {
        return testResultUnit;
    }

    public void setTestResultUnit(String testResultUnit) {
        this.testResultUnit = testResultUnit;
    }

    public String getTestResultValue() {
        return testResultValue;
    }

    public void setTestResultValue(String testResultValue) {
        this.testResultValue = testResultValue;
    }

    //custom getters
    public Date getDateAdded() {
        if (dateAdded == null) {
            try {
                dateAdded = ccrDocument.getAddedDateTime();
            }
            catch (Exception ex) {
                dateAdded = null;
            }
        }

        return dateAdded;
    }

}
