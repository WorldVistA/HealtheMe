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

import com.krminc.phr.domain.vitals.Height;
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

@XmlRootElement(name = "heights")
public class HeightsConverter {
    private Collection<Height> entities;
    private Collection<HeightConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of HeightsConverter */
    public HeightsConverter() {
    }

    /**
     * Creates a new instance of HeightsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public HeightsConverter(Collection<Height> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getHeight();
    }

    /**
     * Returns a collection of HeightConverter.
     *
     * @return a collection of HeightConverter
     */
    @XmlElement
    public Collection<HeightConverter> getHeight() {
        if (items == null) {
            items = new ArrayList<HeightConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Height entity : entities) {
                items.add(new HeightConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of HeightConverter.
     *
     * @param a collection of HeightConverter to set
     */
    public void setHeight(Collection<HeightConverter> items) {
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
     * Returns a collection Height entities.
     *
     * @return a collection of Height entities
     */
    @XmlTransient
    public Collection<Height> getEntities() {
        entities = new ArrayList<Height>();
        if (items != null) {
            for (HeightConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
