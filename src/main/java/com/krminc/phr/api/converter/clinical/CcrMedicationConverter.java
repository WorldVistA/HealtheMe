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

package com.krminc.phr.api.converter.clinical;

import com.krminc.phr.api.converter.DateAdapter;
import com.krminc.phr.api.converter.UriResolver;
import com.krminc.phr.domain.clinical.CcrMedication;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author cmccall
 */

@XmlRootElement(name = "ccrMedication")
public class CcrMedicationConverter {
    private CcrMedication entity;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of CcrMedicationConverter */
    public CcrMedicationConverter() {
        entity = new CcrMedication();
    }

    /**
     * Creates a new instance of CcrMedicationConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public CcrMedicationConverter(CcrMedication entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getId() + "/").build() : uri;
        this.expandLevel = expandLevel;
    }

    /**
     * Creates a new instance of CcrMedicationConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public CcrMedicationConverter(CcrMedication entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for id.
     *
     * @return value for id
     */
    @XmlElement
    public Long getId() {
        return (expandLevel > 0) ? entity.getId() : null;
    }

    /**
     * Getter for productName.
     *
     * @return value for productName
     */
    @XmlElement
    public String getProductName() {
        return (expandLevel > 0) ? entity.getProductName() : null;
    }

    /**
     * Getter for type.
     *
     * @return value for type
     */
//    @XmlElement
//    public String getType() {
//        return (expandLevel > 0) ? entity.getType() : null;
//    }

    /**
     * Getter for productStrengthValue.
     *
     * @return value for productStrengthValue
     */
//    @XmlElement
//    public String getProductStrengthValue() {
//        return (expandLevel > 0) ? entity.getProductStrengthValue() : null;
//    }

    /**
     * Getter for productStrengthUnits.
     *
     * @return value for productStrengthUnits
     */
//    @XmlElement
//    public String getProductStrengthUnits() {
//        return (expandLevel > 0) ? entity.getProductStrengthUnits() : null;
//    }

    /**
     * Getter for productStrengthUnits.
     *
     * @return value for productStrengthUnits
     */
    @XmlElement
    public String getProductStrength() {
        return (expandLevel > 0) ? (entity.getProductStrengthValue() + " " + entity.getProductStrengthUnits()).trim() : null;
    }

    /**
     * Getter for directionRouteText.
     *
     * @return value for directionRouteText
     */
//    @XmlElement
//    public String getDirectionRouteText() {
//        return (expandLevel > 0) ? entity.getDirectionRouteText() : null;
//    }

    /**
     * Getter for dateTimeStarted.
     *
     * @return value for dateTimeStarted
     */
    @XmlElement
    public Date getDateTimeStarted() {
        return (expandLevel > 0) ? entity.getDateTimeStarted() : null;
    }

    /**
     * Getter for dateTimeStopped.
     *
     * @return value for dateTimeStopped
     */
//    @XmlElement
//    public Date getDateTimeStopped() {
//        return (expandLevel > 0) ? entity.getDateTimeStopped() : null;
//    }

    /**
     * Getter for directionDose.
     *
     * @return value for directionDose
     */
    @XmlElement
    public String getDirectionDose() {
        return (expandLevel > 0) ? entity.getDirectionDose() : null;
    }

    /**
     * Getter for dataObjectId.
     *
     * @return value for dataObjectId
     */
//    @XmlElement
//    public String getDataObjectId() {
//        return (expandLevel > 0) ? entity.getDataObjectId() : null;
//    }

    /**
     * Getter for status.
     *
     * @return value for status
     */
    @XmlElement
    public String getStatus() {
        return (expandLevel > 0) ? entity.getStatus() : null;
    }

    /**
     * Getter for form.
     *
     * @return value for form
     */
//    @XmlElement
//    public String getForm() {
//        return (expandLevel > 0) ? entity.getForm() : null;
//    }

    /**
     * Getter for concentrationValue.
     *
     * @return value for concentrationValue
     */
//    @XmlElement
//    public String getConcentrationValue() {
//        return (expandLevel > 0) ? entity.getConcentrationValue() : null;
//    }

    /**
     * Getter for concentrationUnitsUnit.
     *
     * @return value for concentrationUnitsUnit
     */
//    @XmlElement
//    public String getConcentrationUnitsUnit() {
//        return (expandLevel > 0) ? entity.getConcentrationUnitsUnit() : null;
//    }

    /**
     * Getter for quantity.
     *
     * @return value for quantity
     */
//    @XmlElement
//    public String getQuantity() {
//        return (expandLevel > 0) ? entity.getQuantity() : null;
//    }

    /**
     * Getter for quantityUnit.
     *
     * @return value for quantityUnit
     */
//    @XmlElement
//    public String getQuantityUnit() {
//        return (expandLevel > 0) ? entity.getQuantityUnit() : null;
//    }

    /**
     * Getter for refillNumber.
     *
     * @return value for refillNumber
     */
//    @XmlElement
//    public String getRefillNumber() {
//        return (expandLevel > 0) ? entity.getRefillNumber() : null;
//    }

    /**
     * Getter for notes.
     *
     * @return value for notes
     */
//    @XmlElement
//    public String getNotes() {
//        return (expandLevel > 0) ? entity.getNotes() : null;
//    }

    /**
     * Returns the URI associated with this converter.
     *
     * @return the uri
     */
    @XmlAttribute
    public URI getUri() {
        return uri;
    }

    /** Custom getters (not autogenerated) **/

    /**
     * Getter for dateAdded.
     *
     * @return value for dateAdded
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getDateAdded() {
        return (expandLevel > 0) ? entity.getDateAdded() : null;
    }

    /**
     * Getter for source.
     *
     * @return value for source
     */
    @XmlElement
    public String getSource() {
        return (expandLevel > 0) ? entity.getSource() : null;
    }

    /**
     * Getter for exactDateTime.
     *
     * @return value for exactDateTime
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getExactDateTime() {
        return (expandLevel > 0) ? entity.getExactDateTime() : null;
    }

    /** End Custom getters **/

    /**
     * Returns the CcrMedication entity.
     *
     * @return an entity
     */
    @XmlTransient
    public CcrMedication getEntity() {
        if (entity.getId() == null) {
            CcrMedicationConverter converter = UriResolver.getInstance().resolve(CcrMedicationConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved CcrMedication entity.
     *
     * @return an resolved entity
     */
    public CcrMedication resolveEntity(EntityManager em) {
        return entity;
    }
}
