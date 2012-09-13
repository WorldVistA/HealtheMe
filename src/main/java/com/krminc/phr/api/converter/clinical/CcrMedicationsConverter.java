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

import com.krminc.phr.domain.clinical.CcrMedication;
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

@XmlRootElement(name = "ccrMedications")
public class CcrMedicationsConverter {
    private Collection<CcrMedication> entities;
    private Collection<CcrMedicationConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of CcrMedicationsConverter */
    public CcrMedicationsConverter() {
    }

    /**
     * Creates a new instance of CcrMedicationsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public CcrMedicationsConverter(Collection<CcrMedication> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getCcrMedication();
    }

    /**
     * Returns a collection of CcrMedicationConverter.
     *
     * @return a collection of CcrMedicationConverter
     */
    @XmlElement
    public Collection<CcrMedicationConverter> getCcrMedication() {
        if (items == null) {
            items = new ArrayList<CcrMedicationConverter>();
        }
        if (entities != null) {
            items.clear();
            for (CcrMedication entity : entities) {
                items.add(new CcrMedicationConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of CcrMedicationConverter.
     *
     * @param a collection of CcrMedicationConverter to set
     */
    public void setCcrMedication(Collection<CcrMedicationConverter> items) {
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
     * Returns a collection CcrMedication entities.
     *
     * @return a collection of CcrMedication entities
     */
    @XmlTransient
    public Collection<CcrMedication> getEntities() {
        entities = new ArrayList<CcrMedication>();
        if (items != null) {
            for (CcrMedicationConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
