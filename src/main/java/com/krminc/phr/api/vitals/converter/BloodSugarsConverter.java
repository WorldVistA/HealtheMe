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

import com.krminc.phr.domain.vitals.BloodSugar;
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

@XmlRootElement(name = "bloodSugars")
public class BloodSugarsConverter {
    private Collection<BloodSugar> entities;
    private Collection<BloodSugarConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of BloodSugarsConverter */
    public BloodSugarsConverter() {
    }

    /**
     * Creates a new instance of BloodSugarsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public BloodSugarsConverter(Collection<BloodSugar> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getBloodSugar();
    }

    /**
     * Returns a collection of BloodSugarConverter.
     *
     * @return a collection of BloodSugarConverter
     */
    @XmlElement
    public Collection<BloodSugarConverter> getBloodSugar() {
        if (items == null) {
            items = new ArrayList<BloodSugarConverter>();
        }
        if (entities != null) {
            items.clear();
            for (BloodSugar entity : entities) {
                items.add(new BloodSugarConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of BloodSugarConverter.
     *
     * @param a collection of BloodSugarConverter to set
     */
    public void setBloodSugar(Collection<BloodSugarConverter> items) {
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
     * Returns a collection BloodSugar entities.
     *
     * @return a collection of BloodSugar entities
     */
    @XmlTransient
    public Collection<BloodSugar> getEntities() {
        entities = new ArrayList<BloodSugar>();
        if (items != null) {
            for (BloodSugarConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
