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
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;

/**
 *
 * @author dshaw
 */

@XmlRootElement(name = "medications")
public class MedicationsConverter {
    private Collection<Medication> entities;
    private Collection<MedicationConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of MedicationsConverter */
    public MedicationsConverter() {
    }

    /**
     * Creates a new instance of MedicationsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public MedicationsConverter(Collection<Medication> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getMedication();
    }

    /**
     * Returns a collection of MedicationConverter.
     *
     * @return a collection of MedicationConverter
     */
    @XmlElement
    public Collection<MedicationConverter> getMedication() {
        if (items == null) {
            items = new ArrayList<MedicationConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Medication entity : entities) {
                items.add(new MedicationConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of MedicationConverter.
     *
     * @param a collection of MedicationConverter to set
     */
    public void setMedication(Collection<MedicationConverter> items) {
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
     * Returns a collection Medication entities.
     *
     * @return a collection of Medication entities
     */
    @XmlTransient
    public Collection<Medication> getEntities() {
        entities = new ArrayList<Medication>();
        if (items != null) {
            for (MedicationConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
