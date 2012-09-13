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

import com.krminc.phr.domain.vitals.Pain;
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

@XmlRootElement(name = "pains")
public class PainsConverter {
    private Collection<Pain> entities;
    private Collection<PainConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of PainsConverter */
    public PainsConverter() {
    }

    /**
     * Creates a new instance of PainsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public PainsConverter(Collection<Pain> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getPain();
    }

    /**
     * Returns a collection of PainConverter.
     *
     * @return a collection of PainConverter
     */
    @XmlElement
    public Collection<PainConverter> getPain() {
        if (items == null) {
            items = new ArrayList<PainConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Pain entity : entities) {
                items.add(new PainConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of PainConverter.
     *
     * @param a collection of PainConverter to set
     */
    public void setPain(Collection<PainConverter> items) {
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
     * Returns a collection Pain entities.
     *
     * @return a collection of Pain entities
     */
    @XmlTransient
    public Collection<Pain> getEntities() {
        entities = new ArrayList<Pain>();
        if (items != null) {
            for (PainConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
