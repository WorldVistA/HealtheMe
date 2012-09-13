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

import com.krminc.phr.api.converter.util.ConverterUtils;
import com.krminc.phr.domain.DataSource;
import com.krminc.phr.domain.HealthRecord;
import com.krminc.phr.domain.MedicalEvent;
import java.math.BigInteger;
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

@XmlRootElement(name = "medicalEvent")
public class MedicalEventConverter {
    private MedicalEvent entity;
    private URI uri;
    private int expandLevel;

    public boolean hasError = false;

    /** Creates a new instance of MedicalEventConverter */
    public MedicalEventConverter() {
        entity = new MedicalEvent();
    }

    /**
     * Creates a new instance of MedicalEventConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public MedicalEventConverter(MedicalEvent entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getMedicalEventId() + "/").build() : uri;
        this.expandLevel = expandLevel;
    }

    /**
     * Creates a new instance of MedicalEventConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public MedicalEventConverter(MedicalEvent entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for medicalEventId.
     *
     * @return value for medicalEventId
     */
    @XmlElement
    public Long getMedicalEventId() {
        return (expandLevel > 0) ? entity.getMedicalEventId() : null;
    }

    /**
     * Setter for medicalEventId.
     *
     * @param value the value to set
     */
    public void setMedicalEventId(Long value) {
        try {
            entity.setMedicalEventId(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for event.
     *
     * @return value for event
     */
    @XmlElement
    public String getEvent() {
        return (expandLevel > 0) ? entity.getEvent() : null;
    }

    /**
     * Setter for event.
     *
     * @param value the value to set
     */
    public void setEvent(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            entity.setEvent(value);
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
     * Getter for howEnded.
     *
     * @return value for status
     */
    @XmlElement
    public String getHowEnded() {
        return (expandLevel > 0) ? entity.getHowEnded() : null;
    }

    /**
     * Setter for howEnded.
     *
     * @param value the value to set
     */
    public void setHowEnded(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            entity.setHowEnded(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for observedDate.
     *
     * @return value for observedDate
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getObservedDate() {
        return (expandLevel > 0) ? entity.getObservedDate() : null;
    }

    /**
     * Setter for observedDate.
     *
     * @param value the value to set
     */
    public void setObservedDate(Date value) {
        try {
            entity.setObservedDate(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for resolvedDate.
     *
     * @return value for resolvedDate
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getResolvedDate() {
        return (expandLevel > 0) ? entity.getResolvedDate() : null;
    }

    /**
     * Setter for resolvedDate.
     *
     * @param value the value to set
     */
    public void setResolvedDate(Date value) {
        try {
            entity.setResolvedDate(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

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
        entity.setDataSourceId(value);
    }

    /**
     * Getter for careDocumentId.
     *
     * @return value for careDocumentId
     */
    @XmlElement
    public BigInteger getCareDocumentId() {
        return (expandLevel > 0) ? entity.getCareDocumentId() : null;
    }

    /**
     * Setter for careDocumentId.
     *
     * @param value the value to set
     */
    public void setCareDocumentId(BigInteger value) {
        try {
            entity.setCareDocumentId(value);
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
     * Returns the MedicalEvent entity.
     *
     * @return an entity
     */
    @XmlTransient
    public MedicalEvent getEntity() {
        if (entity.getMedicalEventId() == null) {
            MedicalEventConverter converter = UriResolver.getInstance().resolve(MedicalEventConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved MedicalEvent entity.
     *
     * @return an resolved entity
     */
    public MedicalEvent resolveEntity(EntityManager em) {
        HealthRecord healthRecord = entity.getHealthRecord();
        if (healthRecord != null) {
            entity.setHealthRecord(em.getReference(HealthRecord.class, healthRecord.getHealthRecordId()));
        }
        DataSource dataSource = entity.getDataSource();
        if (dataSource != null) {
            entity.setDataSource(em.getReference(DataSource.class, dataSource.getDataSourceId()));
        }
        return entity;
    }
}
