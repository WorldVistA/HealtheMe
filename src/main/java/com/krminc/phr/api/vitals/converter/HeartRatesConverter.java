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

import com.krminc.phr.domain.vitals.HeartRate;
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

@XmlRootElement(name = "heartRates")
public class HeartRatesConverter {
    private Collection<HeartRate> entities;
    private Collection<HeartRateConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of HeartRatesConverter */
    public HeartRatesConverter() {
    }

    /**
     * Creates a new instance of HeartRatesConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public HeartRatesConverter(Collection<HeartRate> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getHeartRate();
    }

    /**
     * Returns a collection of HeartRateConverter.
     *
     * @return a collection of HeartRateConverter
     */
    @XmlElement
    public Collection<HeartRateConverter> getHeartRate() {
        if (items == null) {
            items = new ArrayList<HeartRateConverter>();
        }
        if (entities != null) {
            items.clear();
            for (HeartRate entity : entities) {
                items.add(new HeartRateConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of HeartRateConverter.
     *
     * @param a collection of HeartRateConverter to set
     */
    public void setHeartRate(Collection<HeartRateConverter> items) {
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
     * Returns a collection HeartRate entities.
     *
     * @return a collection of HeartRate entities
     */
    @XmlTransient
    public Collection<HeartRate> getEntities() {
        entities = new ArrayList<HeartRate>();
        if (items != null) {
            for (HeartRateConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
