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

import com.krminc.phr.domain.Allergy;
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

@XmlRootElement(name = "allergies")
public class AllergiesConverter {
    private Collection<Allergy> entities;
    private Collection<AllergyConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of AllergiesConverter */
    public AllergiesConverter() {
    }

    /**
     * Creates a new instance of AllergiesConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public AllergiesConverter(Collection<Allergy> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getAllergy();
    }

    /**
     * Returns a collection of AllergyConverter.
     *
     * @return a collection of AllergyConverter
     */
    @XmlElement
    public Collection<AllergyConverter> getAllergy() {
        if (items == null) {
            items = new ArrayList<AllergyConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Allergy entity : entities) {
                items.add(new AllergyConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of AllergyConverter.
     *
     * @param a collection of AllergyConverter to set
     */
    public void setAllergy(Collection<AllergyConverter> items) {
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
     * Returns a collection Allergy entities.
     *
     * @return a collection of Allergy entities
     */
    @XmlTransient
    public Collection<Allergy> getEntities() {
        entities = new ArrayList<Allergy>();
        if (items != null) {
            for (AllergyConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
