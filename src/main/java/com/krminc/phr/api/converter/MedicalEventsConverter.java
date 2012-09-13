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

import com.krminc.phr.domain.MedicalEvent;
import java.net.URI;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;

/**
 *
 * @author cmccall
 */

@XmlRootElement(name = "medicalEvents")
public class MedicalEventsConverter {
    private Collection<MedicalEvent> entities;
    private Collection<MedicalEventConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of MedicalEventsConverter */
    public MedicalEventsConverter() {
    }

    /**
     * Creates a new instance of MedicalEventsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public MedicalEventsConverter(Collection<MedicalEvent> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getMedicalEvent();
    }

    /**
     * Returns a collection of MedicalEventConverter.
     *
     * @return a collection of MedicalEventConverter
     */
    @XmlElement
    public Collection<MedicalEventConverter> getMedicalEvent() {
        if (items == null) {
            items = new ArrayList<MedicalEventConverter>();
        }
        if (entities != null) {
            items.clear();
            for (MedicalEvent entity : entities) {
                items.add(new MedicalEventConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of MedicalEventConverter.
     *
     * @param a collection of MedicalEventConverter to set
     */
    public void setMedicalEvent(Collection<MedicalEventConverter> items) {
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
     * Returns a collection MedicalEvent entities.
     *
     * @return a collection of MedicalEvent entities
     */
    @XmlTransient
    public Collection<MedicalEvent> getEntities() {
        entities = new ArrayList<MedicalEvent>();
        if (items != null) {
            for (MedicalEventConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
