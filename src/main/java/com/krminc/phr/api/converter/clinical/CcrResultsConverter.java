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

import com.krminc.phr.domain.clinical.CcrResult;
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

@XmlRootElement(name = "ccrResults")
public class CcrResultsConverter {
    private Collection<CcrResult> entities;
    private Collection<CcrResultConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of CcrResultsConverter */
    public CcrResultsConverter() {
    }

    /**
     * Creates a new instance of CcrResultsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public CcrResultsConverter(Collection<CcrResult> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getCcrResult();
    }

    /**
     * Returns a collection of CcrResultConverter.
     *
     * @return a collection of CcrResultConverter
     */
    @XmlElement
    public Collection<CcrResultConverter> getCcrResult() {
        if (items == null) {
            items = new ArrayList<CcrResultConverter>();
        }
        if (entities != null) {
            items.clear();
            for (CcrResult entity : entities) {
                items.add(new CcrResultConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
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
     * Returns a collection CcrResult entities.
     *
     * @return a collection of CcrResult entities
     */
    @XmlTransient
    public Collection<CcrResult> getEntities() {
        entities = new ArrayList<CcrResult>();
        if (items != null) {
            for (CcrResultConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
