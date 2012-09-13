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

import com.krminc.phr.domain.Visit;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.krminc.phr.api.converter.util.ConverterUtils;
import com.krminc.phr.domain.HealthRecord;

/**
 *
 * @author cmccall
 */

@XmlRootElement(name = "visit")
public class VisitConverter {
    private Visit entity;
    private URI uri;
    private int expandLevel;

    final Logger logger = LoggerFactory.getLogger(VisitConverter.class);
    public boolean hasError = false;

    /** Creates a new instance of VisitConverter */
    public VisitConverter() {
        entity = new Visit();
    }

    /**
     * Creates a new instance of VisitConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public VisitConverter(Visit entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getVisitId() + "/").build() : uri;
        this.expandLevel = expandLevel;
    }

    /**
     * Creates a new instance of VisitConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public VisitConverter(Visit entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for visitId.
     *
     * @return value for visitId
     */
    @XmlElement
    public Long getVisitId() {
        return (expandLevel > 0) ? entity.getVisitId() : null;
    }

    /**
     * Setter for visitId.
     *
     * @param value the value to set
     */
    public void setVisitId(Long value) {
        try {
            entity.setVisitId(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for title.
     *
     * @return value for title
     */
    @XmlElement
    public String getTitle() {
        return (expandLevel > 0) ? entity.getTitle() : null;
    }

    /**
     * Setter for title.
     *
     * @param value the value to set
     */
    public void setTitle(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            if (value.length() > 0 && value.length() <= 255) {
                entity.setTitle(value);
            } else {
                throw new Exception();
            }
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for visitDate.
     *
     * @return value for visitDate
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getVisitDate() {
        return (expandLevel > 0) ? entity.getVisitDate() : null;
    }

    /**
     * Setter for visitDate.
     *
     * @param value the value to set
     */
    public void setVisitDate(Date value) {
        try {
            entity.setVisitDate(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Setter for ObservedDate.
     *
     * @param value the value to set
     */
    public void setVisitDate(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            DateTime date = new DateTime(value);
            this.setVisitDate(date.toDate());
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for visitTime.
     *
     * @return value for visitTime
     */
    @XmlElement
    @XmlJavaTypeAdapter(TimeAdapter.class)
    public Date getVisitTime() {
        return (expandLevel > 0) ? entity.getVisitTime() : null;
    }

    /**
     * Setter for visitTime.
     *
     * @param value the value to set
     */
    public void setVisitTime(Date value) {
        try {
            entity.setVisitTime(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Setter for visitTime.
     *
     * @param value the value to set
     */
    public void setVisitTime(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            DateTime date = new DateTime(value);
            this.setVisitTime(date.toDate());
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for purpose.
     *
     * @return value for purpose
     */
    @XmlElement
    public String getPurpose() {
        return (expandLevel > 0) ? entity.getPurpose() : null;
    }

    /**
     * Setter for purpose.
     *
     * @param value the value to set
     */
    public void setPurpose(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            if (value.length() > 0) {
                if (value.length() <=255) {
                    entity.setPurpose(value);
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
     * Getter for location.
     *
     * @return value for location
     */
    @XmlElement
    public String getLocation() {
        return (expandLevel > 0) ? entity.getLocation() : null;
    }

    /**
     * Setter for location.
     *
     * @param value the value to set
     */
    public void setLocation(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            if (value.length() > 0) {
                if (value.length() <=255) {
                    entity.setLocation(value);
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
     * Getter for provider.
     *
     * @return value for provider
     */
    @XmlElement
    public String getProvider() {
        return (expandLevel > 0) ? entity.getProvider() : null;
    }

    /**
     * Setter for provider.
     *
     * @param value the value to set
     */
    public void setProvider(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            if (value.length() > 0) {
                if (value.length() <=255) {
                    entity.setProvider(value);
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
     * Returns the Visit entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Visit getEntity() {
        if (entity.getVisitId() == null) {
            VisitConverter converter = UriResolver.getInstance().resolve(VisitConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved Visit entity.
     *
     * @return an resolved entity
     */
    public Visit resolveEntity(EntityManager em) {
        HealthRecord healthRecord = entity.getHealthRecord();
        if (healthRecord != null) {
            entity.setHealthRecord(em.getReference(HealthRecord.class, healthRecord.getHealthRecordId()));
        }
        return entity;
    }
}
