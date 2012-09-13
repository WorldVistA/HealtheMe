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

import com.krminc.phr.domain.clinical.CcrImmunization;
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

@XmlRootElement(name = "ccrImmunizations")
public class CcrImmunizationsConverter {
    private Collection<CcrImmunization> entities;
    private Collection<CcrImmunizationConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of CcrImmunizationsConverter */
    public CcrImmunizationsConverter() {
    }

    /**
     * Creates a new instance of CcrImmunizationsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public CcrImmunizationsConverter(Collection<CcrImmunization> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getCcrImmunization();
    }

    /**
     * Returns a collection of CcrImmunizationConverter.
     *
     * @return a collection of CcrImmunizationConverter
     */
    @XmlElement
    public Collection<CcrImmunizationConverter> getCcrImmunization() {
        if (items == null) {
            items = new ArrayList<CcrImmunizationConverter>();
        }
        if (entities != null) {
            items.clear();
            for (CcrImmunization entity : entities) {
                items.add(new CcrImmunizationConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of CcrImmunizationConverter.
     *
     * @param a collection of CcrImmunizationConverter to set
     */
    public void setCcrImmunization(Collection<CcrImmunizationConverter> items) {
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
     * Returns a collection CcrImmunization entities.
     *
     * @return a collection of CcrImmunization entities
     */
    @XmlTransient
    public Collection<CcrImmunization> getEntities() {
        entities = new ArrayList<CcrImmunization>();
        if (items != null) {
            for (CcrImmunizationConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
