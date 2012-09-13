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

import com.krminc.phr.domain.clinical.RecordIdentifier;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Record Identifiers JAXB parser.
 *
 * @author Daniel Shaw (dshaw.com)
 */
@XmlRootElement(name = "recordIdentifiers")
public class RecordIdentifiersConverter {
    private Collection<RecordIdentifier> entities;
    private Collection<RecordIdentifierConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of RecordIdentifiersConverter */
    public RecordIdentifiersConverter() {
    }

    /**
     * Creates a new instance of RecordIdentifiersConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public RecordIdentifiersConverter(Collection<RecordIdentifier> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getRecordIdentifier();
    }

    /**
     * Returns a collection of RecordIdentifierConverter.
     *
     * @return a collection of RecordIdentifierConverter
     */
    @XmlElement
    public Collection<RecordIdentifierConverter> getRecordIdentifier() {
        if (items == null) {
            items = new ArrayList<RecordIdentifierConverter>();
        }
        if (entities != null) {
            items.clear();
            for (RecordIdentifier entity : entities) {
                items.add(new RecordIdentifierConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of RecordIdentifierConverter.
     *
     * @param a collection of RecordIdentifierConverter to set
     */
    public void setRecordIdentifier(Collection<RecordIdentifierConverter> items) {
        this.items = items;
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
     * Returns a collection RecordIdentifier entities.
     *
     * @return a collection of RecordIdentifier entities
     */
    @XmlTransient
    public Collection<RecordIdentifier> getEntities() {
        entities = new ArrayList<RecordIdentifier>();
        if (items != null) {
            for (RecordIdentifierConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
