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

import com.krminc.phr.domain.Immunization;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
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

@XmlRootElement(name = "immunization")
public class ImmunizationConverter {

    final Logger logger = LoggerFactory.getLogger(ImmunizationConverter.class);

    private Immunization entity;
    private URI uri;
    private int expandLevel;

    public boolean hasError = false;
  
    /** Creates a new instance of ImmunizationConverter */
    public ImmunizationConverter() {
        entity = new Immunization();
    }

    /**
     * Creates a new instance of ImmunizationConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public ImmunizationConverter(Immunization entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getImmunizationId() + "/").build() : uri;
        this.expandLevel = expandLevel;
    }

    /**
     * Creates a new instance of ImmunizationConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ImmunizationConverter(Immunization entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for immunizationId.
     *
     * @return value for immunizationId
     */
    @XmlElement
    public Long getImmunizationId() {
        return (expandLevel > 0) ? entity.getImmunizationId() : null;
    }

    /**
     * Setter for immunizationId.
     *
     * @param value the value to set
     */
    public void setImmunizationId(Long value) {
        try {
            entity.setImmunizationId(value);
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
     * Getter for DateReceived.
     *
     * @return value for DateReceived
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getDateReceived() {
        return (expandLevel > 0) ? entity.getDateReceived() : null;
    }

    /**
     * Setter for DateReceived.
     *
     * @param value the value to set
     */
    public void setDateReceived(Date value) {
        try {
            entity.setDateReceived(value);
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
     * Getter for immunizationType.
     *
     * @return value for immunizationType
     */
    @XmlElement
    public String getImmunizationType() {
        return (expandLevel > 0) ? entity.getImmunizationType() : null;
    }

    /**
     * Setter for immunizationType.
     *
     * @param value the value to set
     */
    public void setImmunizationType(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            entity.setImmunizationType(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for method.
     *
     * @return value for method
     */
    @XmlElement
    public Integer getMethod() {
        return (expandLevel > 0) ? entity.getMethod() : null;
    }

    /**
     * Setter for method.
     *
     * @param value the value to set
     */
    public void setMethod(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            this.setMethod(Integer.parseInt(value));
        }
        catch(Exception ex) {
            hasError = true;
        }
    }
    
    /**
     * Setter for method.
     *
     * @param value the value to set
     */
    public void setMethod(Integer value) {
        try {
            if (value >=0 && value <=4) {
                entity.setMethod(value);
            } else {
                throw new Exception();
            }
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for method.
     *
     * @return value for method
     */
    @XmlElement
    public String getFullMethod() {
        return (expandLevel > 0) ? entity.getFullMethod() : null;
    }
    
    /**
     * Getter for reaction.
     *
     * @return value for reaction
     */
    @XmlElement
    public String getReaction() {
        return (expandLevel > 0) ? entity.getReaction() : null;
    }

    /**
     * Setter for reaction.
     *
     * @param value the value to set
     */
    public void setReaction(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            entity.setReaction(value);
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
     * Returns the Immunization entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Immunization getEntity() {
        if (entity.getImmunizationId() == null) {
            ImmunizationConverter converter = UriResolver.getInstance().resolve(ImmunizationConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved Immunization entity.
     *
     * @return an resolved entity
     */
    public Immunization resolveEntity(EntityManager em) {
        HealthRecord healthRecord = entity.getHealthRecord();
        if (healthRecord != null) {
            entity.setHealthRecord(em.getReference(HealthRecord.class, healthRecord.getHealthRecordId()));
        }
        return entity;
    }
}
