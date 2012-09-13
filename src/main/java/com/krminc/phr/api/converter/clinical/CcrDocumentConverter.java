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

import com.krminc.phr.api.converter.DateTimeAdapter;
import com.krminc.phr.api.converter.UriResolver;
import com.krminc.phr.domain.clinical.CcrDocument;
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

@XmlRootElement(name = "ccrDocument")
public class CcrDocumentConverter {
    private CcrDocument entity;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of CcrDocumentConverter */
    public CcrDocumentConverter() {
        entity = new CcrDocument();
    }

    /**
     * Creates a new instance of CcrDocumentConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public CcrDocumentConverter(CcrDocument entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getId() + "/").build() : uri;
        this.expandLevel = expandLevel;
    }

    /**
     * Creates a new instance of CcrDocumentConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public CcrDocumentConverter(CcrDocument entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for id.
     *
     * @return value for id
     */
//    @XmlElement
//    public Long getId() {
//        return (expandLevel > 0) ? entity.getId() : null;
//    }

    /**
     * Getter for ccrDocumentObjectId.
     *
     * @return value for ccrDocumentObjectId
     */
    @XmlElement
    public String getCcrDocumentObjectId() {
        return (expandLevel > 0) ? entity.getCcrDocumentObjectId() : null;
    }

    /**
     * Getter for description.
     *
     * @return value for description
     */
    @XmlElement
    public String getDescription() {
        return (expandLevel > 0) ? entity.getDescription() : null;
    }

    /**
     * Getter for purposeDescriptionText.
     *
     * @return value for purposeDescriptionText
     */
    @XmlElement
    public String getPurposeDescriptionText() {
        return (expandLevel > 0) ? entity.getPurposeDescriptionText() : null;
    }

    /**
     * Getter for createdDateTime.
     *
     * @return value for createdDateTime
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    public Date getCreatedDateTime() {
        return (expandLevel > 0) ? entity.getCreatedDateTime() : null;
    }

    /**
     * Getter for body.
     *
     * @return value for body
     */
//    @XmlElement
//    public byte[] getBody() {
//        return (expandLevel > 0) ? entity.getBody() : null;
//    }

    /**
     * Getter for addedDateTime.
     *
     * @return value for addedDateTime
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    public Date getAddedDateTime() {
        return (expandLevel > 0) ? entity.getAddedDateTime() : null;
    }

    /**
     * Getter for lastUpdatedDateTime.
     *
     * @return value for lastUpdatedDateTime
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    public Date getLastUpdatedDateTime() {
        return (expandLevel > 0) ? entity.getLastUpdatedDateTime() : null;
    }

    /**
     * Getter for notes.
     *
     * @return value for notes
     */
    @XmlElement
    public String getNotes() {
        return (expandLevel > 0) ? entity.getNotes() : null;
    }

    /**
     * Getter for version.
     *
     * @return value for version
     */
    @XmlElement
    public String getVersion() {
        return (expandLevel > 0) ? entity.getVersion() : null;
    }

    /**
     * Getter for healthRecordId.
     *
     * @return value for healthRecordId
     */
//    @XmlElement
//    public Long getHealthRecordId() {
//        return (expandLevel > 0) ? entity.getHealthRecordId() : null;
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

    /**
     * Returns the CcrDocument entity.
     *
     * @return an entity
     */
    @XmlTransient
    public CcrDocument getEntity() {
        if (entity.getId() == null) {
            CcrDocumentConverter converter = UriResolver.getInstance().resolve(CcrDocumentConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved CcrDocument entity.
     *
     * @return an resolved entity
     */
    public CcrDocument resolveEntity(EntityManager em) {
        return entity;
    }
}
