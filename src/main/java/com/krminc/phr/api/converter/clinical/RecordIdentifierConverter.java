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
package com.krminc.phr.api.converter.clinical;

import com.krminc.phr.api.converter.UriResolver;
import com.krminc.phr.domain.clinical.RecordIdentifier;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;

/**
 * Record Identifier JAXB parser.
 *
 * @author Daniel Shaw (dshaw.com)
 */
@XmlRootElement(name = "recordIdentifier")
public class RecordIdentifierConverter {
    private RecordIdentifier entity;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of RecordIdentifierConverter */
    public RecordIdentifierConverter() {
        entity = new RecordIdentifier();
    }

    /**
     * Creates a new instance of RecordIdentifierConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public RecordIdentifierConverter(RecordIdentifier entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getId() + "/").build() : uri;
        this.expandLevel = expandLevel;
    }

    /**
     * Creates a new instance of RecordIdentifierConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public RecordIdentifierConverter(RecordIdentifier entity, URI uri, int expandLevel) {
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
     * Setter for identityId.
     *
     * @param value the value to set
     */
    public void setId(Long value) {
        entity.setId(value);
    }

    /**
     * Getter for healthRecordId.
     *
     * @return value for healthRecordId
     */
    @XmlElement
    public Long getHealthRecordId() {
        return (expandLevel > 0) ? entity.getHealthRecordId() : null;
    }

    /**
     * Setter for healthRecordId.
     *
     * @param value the value to set
     */
    public void setHealthRecordId(Long value) {
        entity.setHealthRecordId(value);
    }

    /**
     * Getter for identifierName.
     *
     * @return value for identifierName
     */
    @XmlElement
    public String getIdentifierName() {
        return (expandLevel > 0) ? entity.getIdentifierName() : null;
    }

    /**
     * Setter for identifierName.
     *
     * @param value the value to set
     */
    public void setIdentifierName(String value) {
        entity.setIdentifierName(value);
    }

    /**
     * Getter for identifierValue.
     *
     * @return value for identifierValue
     */
    @XmlElement
    public String getIdentifierValue() {
        return (expandLevel > 0) ? entity.getIdentifierValue() : null;
    }

    /**
     * Setter for identifierValue.
     *
     * @param value the value to set
     */
    public void setIdentifierValue(String value) {
        entity.setIdentifierValue(value);
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
     * Setter for notes.
     *
     * @param value the value to set
     */
    public void setNotes(String value) {
        entity.setNotes(value);
    }

    /**
     * Getter for resourceId.
     *
     * @return value for resourceId
     */
    @XmlElement
    public Long getResourceId() {
        return (expandLevel > 0) ? entity.getResourceId() : null;
    }

    /**
     * Setter for resourceId.
     *
     * @param value the value to set
     */
    public void setResourceId(Long value) {
        entity.setResourceId(value);
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
        this.uri = uri;
    }

    /**
     * Returns the RecordIdentifier entity.
     *
     * @return an entity
     */
    @XmlTransient
    public RecordIdentifier getEntity() {
        if (entity.getId() == null) {
            RecordIdentifierConverter converter = UriResolver.getInstance().resolve(RecordIdentifierConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved RecordIdentifier entity.
     *
     * @return an resolved entity
     */
    public RecordIdentifier resolveEntity(EntityManager em) {
        return entity;
    }
}
