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

import com.krminc.phr.domain.Immunization;
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

@XmlRootElement(name = "immunizations")
public class ImmunizationsConverter {
    private Collection<Immunization> entities;
    private Collection<ImmunizationConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of ImmunizationsConverter */
    public ImmunizationsConverter() {
    }

    /**
     * Creates a new instance of ImmunizationsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ImmunizationsConverter(Collection<Immunization> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getImmunization();
    }

    /**
     * Returns a collection of ImmunizationConverter.
     *
     * @return a collection of ImmunizationConverter
     */
    @XmlElement
    public Collection<ImmunizationConverter> getImmunization() {
        if (items == null) {
            items = new ArrayList<ImmunizationConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Immunization entity : entities) {
                items.add(new ImmunizationConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of ImmunizationConverter.
     *
     * @param a collection of ImmunizationConverter to set
     */
    public void setImmunization(Collection<ImmunizationConverter> items) {
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
     * Returns a collection Immunization entities.
     *
     * @return a collection of Immunization entities
     */
    @XmlTransient
    public Collection<Immunization> getEntities() {
        entities = new ArrayList<Immunization>();
        if (items != null) {
            for (ImmunizationConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
