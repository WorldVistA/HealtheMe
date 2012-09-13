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

package com.krminc.phr.api.vitals.converter;

import com.krminc.phr.api.converter.DateAdapter;
import com.krminc.phr.api.converter.util.ConverterUtils;
import com.krminc.phr.domain.HealthRecord;
import com.krminc.phr.domain.vitals.PeakFlow;
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

@XmlRootElement(name = "peakFlow")
public class PeakFlowConverter {
    private PeakFlow entity;
    private URI uri;
    private int expandLevel;

    public boolean hasError = false;
  
    /** Creates a new instance of PeakFlowConverter */
    public PeakFlowConverter() {
        entity = new PeakFlow();
    }

    /**
     * Creates a new instance of PeakFlowConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public PeakFlowConverter(PeakFlow entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getPeakFlowId() + "/").build() : uri;
        this.expandLevel = expandLevel;
    }

    /**
     * Creates a new instance of PeakFlowConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public PeakFlowConverter(PeakFlow entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for peakflowId.
     *
     * @return value for peakFlowId
     */
    @XmlElement
    public Long getId() {
        return (expandLevel > 0) ? entity.getPeakFlowId() : null;
    }

    /**
     * Setter for peakflowId.
     *
     * @param value the value to set
     */
    public void setId(Long value) {
        try {
            entity.setPeakFlowId(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for peakFlow.
     *
     * @return value for peakFlow
     */
    @XmlElement
    public Long getPeakFlow() {
        return (expandLevel > 0) ? entity.getPeakFlow() : null;
    }

    /**
     * Setter for peakFlow.
     *
     * @param value the value to set
     */
    public void setPeakFlow(Long value) {
        try {
            entity.setPeakFlow(value);
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
     * Returns the PeakFlow entity.
     *
     * @return an entity
     */
    @XmlTransient
    public PeakFlow getEntity() {
        if (entity.getPeakFlowId() == null) {
            PeakFlowConverter converter = UriResolver.getInstance().resolve(PeakFlowConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved PeakFlow entity.
     *
     * @return an resolved entity
     */
    public PeakFlow resolveEntity(EntityManager em) {
        HealthRecord healthRecord = entity.getHealthRecord();
        if (healthRecord != null) {
            entity.setHealthRecord(em.getReference(HealthRecord.class, healthRecord.getHealthRecordId()));
        }
        return entity;
    }
}
