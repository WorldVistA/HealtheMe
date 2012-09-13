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

import com.krminc.phr.domain.clinical.Resource;
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

@XmlRootElement(name = "resources")
public class ResourcesConverter {
    private Collection<Resource> entities;
    private Collection<ResourceConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of ResourcesConverter */
    public ResourcesConverter() {
    }

    /**
     * Creates a new instance of ResourcesConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ResourcesConverter(Collection<Resource> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getResource();
    }

    /**
     * Returns a collection of ResourceConverter.
     *
     * @return a collection of ResourceConverter
     */
    @XmlElement
    public Collection<ResourceConverter> getResource() {
        if (items == null) {
            items = new ArrayList<ResourceConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Resource entity : entities) {
                items.add(new ResourceConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of ResourceConverter.
     *
     * @param a collection of ResourceConverter to set
     */
    public void setResource(Collection<ResourceConverter> items) {
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
     * Returns a collection Resource entities.
     *
     * @return a collection of Resource entities
     */
    @XmlTransient
    public Collection<Resource> getEntities() {
        entities = new ArrayList<Resource>();
        if (items != null) {
            for (ResourceConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
