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

import com.krminc.phr.domain.vitals.Weight;
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

@XmlRootElement(name = "weights")
public class WeightsConverter {
    private Collection<Weight> entities;
    private Collection<WeightConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of WeightsConverter */
    public WeightsConverter() {
    }

    /**
     * Creates a new instance of WeightsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public WeightsConverter(Collection<Weight> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getWeight();
    }

    /**
     * Returns a collection of WeightConverter.
     *
     * @return a collection of WeightConverter
     */
    @XmlElement
    public Collection<WeightConverter> getWeight() {
        if (items == null) {
            items = new ArrayList<WeightConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Weight entity : entities) {
                items.add(new WeightConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of WeightConverter.
     *
     * @param a collection of WeightConverter to set
     */
    public void setWeight(Collection<WeightConverter> items) {
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
     * Returns a collection Weight entities.
     *
     * @return a collection of Weight entities
     */
    @XmlTransient
    public Collection<Weight> getEntities() {
        entities = new ArrayList<Weight>();
        if (items != null) {
            for (WeightConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
