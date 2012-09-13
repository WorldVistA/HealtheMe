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
public  class GenericResult implements Serializable {


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CcrDocument.class)
    @JoinColumn(name = "ccr_id", nullable = false)
    protected CcrDocument ccrDocument;
    @Column(name = "data_object_id")
    protected String dataObjectId;
    @Column(name = "descriptionCode")
    protected String descriptionCode;
    @Column(name = "description_coding_system")
    protected String descriptionCodingSystem;
    @Column(name = "description_text")
    protected String descriptionText;

    @Transient
    private Date dateAdded;

    public GenericResult() {
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
