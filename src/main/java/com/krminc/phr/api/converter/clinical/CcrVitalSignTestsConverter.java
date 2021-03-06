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

import com.krminc.phr.domain.clinical.CcrVitalSignTest;
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

@XmlRootElement(name = "ccrVitalSignTests")
public class CcrVitalSignTestsConverter {
    private Collection<CcrVitalSignTest> entities;
    private Collection<CcrVitalSignTestConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of CcrVitalSignTestsConverter */
    public CcrVitalSignTestsConverter() {
    }

    /**
     * Creates a new instance of CcrVitalSignTestsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public CcrVitalSignTestsConverter(Collection<CcrVitalSignTest> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getCcrVitalSignTest();
    }

    /**
     * Returns a collection of CcrVitalSignTestConverter.
     *
     * @return a collection of CcrVitalSignTestConverter
     */
    @XmlElement
    public Collection<CcrVitalSignTestConverter> getCcrVitalSignTest() {
        if (items == null) {
            items = new ArrayList<CcrVitalSignTestConverter>();
        }
        if (entities != null) {
            items.clear();
            for (CcrVitalSignTest entity : entities) {
                items.add(new CcrVitalSignTestConverter(entity, uri, expandLevel, true));
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
     * Returns a collection CcrVitalSignTest entities.
     *
     * @return a collection of CcrVitalSignTest entities
     */
    @XmlTransient
    public Collection<CcrVitalSignTest> getEntities() {
        entities = new ArrayList<CcrVitalSignTest>();
        if (items != null) {
            for (CcrVitalSignTestConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
