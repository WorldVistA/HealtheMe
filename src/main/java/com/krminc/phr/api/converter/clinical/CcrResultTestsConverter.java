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

import com.krminc.phr.domain.clinical.CcrResultTest;
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

@XmlRootElement(name = "ccrResultTests")
public class CcrResultTestsConverter {
    private Collection<CcrResultTest> entities;
    private Collection<CcrResultTestConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of CcrResultTestsConverter */
    public CcrResultTestsConverter() {
    }

    /**
     * Creates a new instance of CcrResultTestsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public CcrResultTestsConverter(Collection<CcrResultTest> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getCcrResultTest();
    }

    /**
     * Returns a collection of CcrResultTestConverter.
     *
     * @return a collection of CcrResultTestConverter
     */
    @XmlElement
    public Collection<CcrResultTestConverter> getCcrResultTest() {
        if (items == null) {
            items = new ArrayList<CcrResultTestConverter>();
        }
        if (entities != null) {
            items.clear();
            for (CcrResultTest entity : entities) {
                items.add(new CcrResultTestConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of CcrResultTestConverter.
     *
     * @param a collection of CcrResultTestConverter to set
     */
    public void setCcrResultTest(Collection<CcrResultTestConverter> items) {
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
     * Returns a collection CcrResultTest entities.
     *
     * @return a collection of CcrResultTest entities
     */
    @XmlTransient
    public Collection<CcrResultTest> getEntities() {
        entities = new ArrayList<CcrResultTest>();
        if (items != null) {
            for (CcrResultTestConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
