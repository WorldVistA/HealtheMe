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

package com.krminc.phr.api.converter;

import com.krminc.phr.domain.Medication;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import com.krminc.phr.domain.User;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.krminc.phr.api.converter.util.ConverterUtils;
import com.krminc.phr.domain.DataSource;
import com.krminc.phr.domain.HealthRecord;

/**
 *
 * @author dshaw
 */

@XmlRootElement(name = "medication")
public class MedicationConverter {
    private Medication entity;
    private URI uri;
    private int expandLevel;

    final Logger logger = LoggerFactory.getLogger(ImmunizationConverter.class);
    public boolean hasError = false;
    
    /** Creates a new instance of MedicationConverter */
    public MedicationConverter() {
        entity = new Medication();
    }

    /**
     * Creates a new instance of MedicationConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public MedicationConverter(Medication entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getMedicationId() + "/").build() : uri;
        this.expandLevel = expandLevel;
    }

    /**
     * Creates a new instance of MedicationConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public MedicationConverter(Medication entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for medicationId.
     *
     * @return value for medicationId
     */
    @XmlElement
    public Long getMedicationId() {
        return (expandLevel > 0) ? entity.getMedicationId() : null;
    }

    /**
     * Setter for medicationId.
     *
     * @param value the value to set
     */
    public void setMedicationId(Long value) {
        try {
            entity.setMedicationId(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for medicationText.
     *
     * @return value for medicationText
     */
    @XmlElement
    public String getMedicationText() {
        return (expandLevel > 0) ? entity.getMedicationText() : null;
    }

    /**
     * Setter for medicationText.
     *
     * @param value the value to set
     */
    public void setMedicationText(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            entity.setMedicationText(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for dose.
     *
     * @return value for dose
     */
    @XmlElement
    public String getDose() {
        return (expandLevel > 0) ? entity.getDose() : null;
    }

    /**
     * Setter for dose.
     *
     * @param value the value to set
     */
    public void setDose(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            entity.setDose(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for dataSourceId.
     *
     * @return value for dataSourceId
     */
    @XmlElement
    public Long getDataSourceId() {
        return (expandLevel > 0) ? entity.getDataSourceId() : null;
    }

    /**
     * Setter for dataSourceId.
     *
     * @param value the value to set
     */
    public void setDataSourceId(Long value) {
        try {
            if (value != 1) {
                throw new Exception();
            }
            entity.setDataSourceId(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for frequency.
     *
     * @return value for frequency
     */
    @XmlElement
    public String getFrequency() {
        return (expandLevel > 0) ? entity.getFrequency() : null;
    }

    /**
     * Setter for dose.
     *
     * @param value the value to set
     */
    public void setFrequency(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            entity.setFrequency(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for rxid.
     *
     * @return value for rxid
     */
    @XmlElement
    public String getRxid() {
        return (expandLevel > 0) ? entity.getRxid() : null;
    }

    /**
     * Setter for rxid.
     *
     * @param value the value to set
     */
    public void setRxid(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            entity.setRxid(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

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
     * Setter for status.
     *
     * @param value the value to set
     */
    public void setStatus(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            entity.setStatus(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for startDateExact.
     *
     * @return value for startdate
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getStartDate() {
        return (expandLevel > 0) ? entity.getStartDate() : null;
    }


    /**
     * Setter for startDateExact value represented as a Date object.
     *
     * @param value the value to set
     */
    public void setStartDate(Date value) {
        try {
            entity.setStartDate(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for endDateExact.
     *
     * @return value for enddate
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getEndDate() {
        return (expandLevel > 0) ? entity.getEndDate() : null;
    }

    /**
     * Setter for endDateExact value represented as a Date object.
     *
     * @param value the value to set
     */
    public void setEndDate(Date value) {
        try {
            entity.setEndDate(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for addedDate.
     *
     * @return value for addedDate
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getDateAdded() {
        return (expandLevel > 0) ? entity.getDateAdded() : null;
    }

    
    /**
     * Getter for reason.
     *
     * @return value for reason
     */
    @XmlElement
    public String getReason() {
        return (expandLevel > 0) ? entity.getReason() : null;
    }

    /**
     * Setter for reason.
     *
     * @param value the value to set
     */
    public void setReason(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            entity.setReason(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for category.
     *
     * @return value for category
     */
    @XmlElement
    public Integer getCategory() {
        return (expandLevel > 0) ? entity.getCategory() : null;
    }

    /**
     * Setter for category.
     *
     * @param value the value to set
     */
    public void setCategory(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            if (!value.isEmpty()) {
                Integer category = Integer.parseInt(value);
                this.setCategory(category);
            }
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for category.
     *
     * @return value for category
     */
    @XmlElement
    public String getFullCategory() {
        return (expandLevel > 0) ? entity.getFullCategory() : null;
    }

    /**
     * Setter for method.
     *
     * @param value the value to set
     */
    public void setCategory(Integer value) {
        try {
            if (value!=null && value>0) {
                if (value <= 5) {
                    entity.setCategory(value);
                } else {
                    throw new Exception();
                }
            }
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for comments.
     *
     * @return value for comments
     */
    @XmlElement
    public String getComments() {
        return (expandLevel > 0) ? entity.getComments() : null;
    }

    /**
     * Setter for comments.
     *
     * @param value the value to set
     */
    public void setComments(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            entity.setComments(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for HealthRecordId.
     *
     * @return value for HealthRecordId
     */
    @XmlElement
    public Long getHealthRecordId() {
        return (expandLevel > 0) ? entity.getHealthRecordId() : null;
    }

    /**
     * Setter for HealthRecordId.
     *
     * @param value the value to set
     */
    public void setHealthRecordId(Long value) {
        try {
            entity.setHealthRecordId(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for sourceId.
     *
     * @return value for sourceId
     */
    @XmlElement
    public Long getSourceId() {
        return (expandLevel > 0) ? entity.getSourceId() : null;
    }

    /**
     * Setter for sourceId.
     *
     * @param value the value to set
     */
    public void setSourceId(Long value) {
        try {
            if (value != 1) {
                throw new Exception();
            }
            entity.setSourceId(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for mask.
     *
     * @return value for mask
     */
    @XmlElement
    public String getMask() {
        return (expandLevel > 0) ? entity.getMask() : null;
    }

    /**
     * Setter for mask.
     *
     * @param value the value to set
     */
    public void setMask(String value) {
        try {
            if (ConverterUtils.isValidMask(value)) {
             entity.setMask(value.trim());
            } else {
                throw new Exception();
            }
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Returns the URI associated with this converter.
     *
     * @return the uri
     */
    @XmlAttribute
    public URI getUri() {
        return uri;
    }

    /**
     * Sets the URI for this reference converter.
     *
     */
    public void setUri(URI uri) {
        try {
            this.uri = uri;
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Returns the Medication entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Medication getEntity() {
        if (entity.getMedicationId() == null) {
            MedicationConverter converter = UriResolver.getInstance().resolve(MedicationConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved Medication entity.
     *
     * @return an resolved entity
     */
    public Medication resolveEntity(EntityManager em) {
        HealthRecord healthRecord = entity.getHealthRecord();
        if (healthRecord != null) {
            entity.setHealthRecord(em.getReference(HealthRecord.class, healthRecord.getHealthRecordId()));
        }
        return entity;
    }
}
