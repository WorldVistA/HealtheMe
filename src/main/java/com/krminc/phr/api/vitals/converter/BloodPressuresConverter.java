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

import com.krminc.phr.domain.vitals.BloodPressure;
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

@XmlRootElement(name = "bloodPressures")
public class BloodPressuresConverter {
    private Collection<BloodPressure> entities;
    private Collection<BloodPressureConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of BloodPressuresConverter */
    public BloodPressuresConverter() {
    }

    /**
     * Creates a new instance of BloodPressuresConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public BloodPressuresConverter(Collection<BloodPressure> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getBloodPressure();
    }

    /**
     * Returns a collection of BloodPressureConverter.
     *
     * @return a collection of BloodPressureConverter
     */
    @XmlElement
    public Collection<BloodPressureConverter> getBloodPressure() {
        if (items == null) {
            items = new ArrayList<BloodPressureConverter>();
        }
        if (entities != null) {
            items.clear();
            for (BloodPressure entity : entities) {
                items.add(new BloodPressureConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of BloodPressureConverter.
     *
     * @param a collection of BloodPressureConverter to set
     */
    public void setBloodPressure(Collection<BloodPressureConverter> items) {
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
     * Returns a collection BloodPressure entities.
     *
     * @return a collection of BloodPressure entities
     */
    @XmlTransient
    public Collection<BloodPressure> getEntities() {
        entities = new ArrayList<BloodPressure>();
        if (items != null) {
            for (BloodPressureConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
