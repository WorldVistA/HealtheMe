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
package com.krminc.phr.api.converter;

import com.krminc.phr.domain.Allergy;
import com.krminc.phr.domain.DataSource;
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
import com.krminc.phr.domain.HealthRecord;

/**
 * Allergy JAXB parser.
 *
 * @author Daniel Shaw (dshaw.com)
 */
@XmlRootElement(name = "allergy")
public class AllergyConverter {

    final Logger logger = LoggerFactory.getLogger(AllergyConverter.class);
    private Allergy entity;
    private URI uri;
    private int expandLevel;

    public boolean hasError = false;

    /** Creates a new instance of AllergyConverter */
    public AllergyConverter() {
        entity = new Allergy();
    }

    /**
     * Creates a new instance of AllergyConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public AllergyConverter(Allergy entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getAllergyId() + "/").build() : uri;
        this.expandLevel = expandLevel;
    }

    /**
     * Creates a new instance of AllergyConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public AllergyConverter(Allergy entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for allergyId.
     *
     * @return value for allergyId
     */
    @XmlElement
    public Long getAllergyId() {
        return (expandLevel > 0) ? entity.getAllergyId() : null;
    }

    /**
     * Setter for allergyId.
     *
     * @param value the value to set
     */
    public void setAllergyId(Long value) {
        try {
            entity.setAllergyId(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for userId.
     *
     * @return value for userId
     */
    @XmlElement
    public Long getHealthRecordId() {
        return (expandLevel > 0) ? entity.getHealthRecordId() : null;
    }

    /**
     * Setter for userId.
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
     * Getter for careDocumentId.
     *
     * @return value for careDocumentId
     */
    @XmlElement
    public Long getCareDocumentId() {
        return (expandLevel > 0) ? entity.getCareDocumentId() : null;
    }

    /**
     * Setter for careDocumentId.
     *
     * @param value the value to set
     */
    public void setCareDocumentId(Long value) {
        try {
            entity.setCareDocumentId(value);
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
     * Getter for allergyText.
     *
     * @return value for allergyText
     */
    @XmlElement
    public String getAllergyText() {
        return (expandLevel > 0) ? entity.getAllergyText() : null;
    }

    /**
     * Setter for allergyText.
     *
     * @param value the value to set
     */
    public void setAllergyText(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            //length 0-75
            if (value.length() > 0 && value.length() <= 75) {
                entity.setAllergyText(value);
            } else {
                throw new Exception();
            }
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
     * Getter for severity.
     *
     * @return value for severity
     */
    @XmlElement
    public String getSeverity() {
        return (expandLevel > 0) ? entity.getSeverity() : null;
    }

    /**
     * Setter for severity.
     *
     * @param value the value to set
     */
    public void setSeverity(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            if (value.length() > 0 && value.length() <= 255) {
                entity.setSeverity(value);
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
            if (value.length() > 0 && value.length() <= 512) {
                entity.setComments(value);
            }
        }
        catch(Exception ex) {
            hasError = true;
        }
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
            if (value.length() > 0 && value.length() <= 255) {
                entity.setReaction(value);
            }
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
     * Returns the Allergy entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Allergy getEntity() {
        if (entity.getAllergyId() == null) {
            AllergyConverter converter = UriResolver.getInstance().resolve(AllergyConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved Allergy entity.
     *
     * @return an resolved entity
     */
    public Allergy resolveEntity(EntityManager em) {
        HealthRecord healthRecord = entity.getHealthRecord();
        if (healthRecord != null) {
            entity.setHealthRecord(em.getReference(HealthRecord.class, healthRecord.getHealthRecordId()));
        }
        return entity;
    }
}
