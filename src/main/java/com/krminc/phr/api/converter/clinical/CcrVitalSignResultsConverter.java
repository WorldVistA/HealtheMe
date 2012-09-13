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

import com.krminc.phr.domain.clinical.CcrVitalSignResult;
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

@XmlRootElement(name = "ccrVitalSignResults")
public class CcrVitalSignResultsConverter {
    private Collection<CcrVitalSignResult> entities;
    private Collection<CcrVitalSignResultConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of CcrVitalSignResultsConverter */
    public CcrVitalSignResultsConverter() {
    }

    /**
     * Creates a new instance of CcrVitalSignResultsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public CcrVitalSignResultsConverter(Collection<CcrVitalSignResult> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getCcrVitalSignResult();
    }

    /**
     * Returns a collection of CcrVitalSignResultConverter.
     *
     * @return a collection of CcrVitalSignResultConverter
     */
    @XmlElement
    public Collection<CcrVitalSignResultConverter> getCcrVitalSignResult() {
        if (items == null) {
            items = new ArrayList<CcrVitalSignResultConverter>();
        }
        if (entities != null) {
            items.clear();
            for (CcrVitalSignResult entity : entities) {
                items.add(new CcrVitalSignResultConverter(entity, uri, expandLevel, true));
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
     * Returns a collection CcrVitalSignResult entities.
     *
     * @return a collection of CcrVitalSignResult entities
     */
    @XmlTransient
    public Collection<CcrVitalSignResult> getEntities() {
        entities = new ArrayList<CcrVitalSignResult>();
        if (items != null) {
            for (CcrVitalSignResultConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
