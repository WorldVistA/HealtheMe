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

import com.krminc.phr.domain.vitals.PeakFlow;
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

@XmlRootElement(name = "peakFlows")
public class PeakFlowsConverter {
    private Collection<PeakFlow> entities;
    private Collection<PeakFlowConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of PeakFlowsConverter */
    public PeakFlowsConverter() {
    }

    /**
     * Creates a new instance of PeakFlowsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public PeakFlowsConverter(Collection<PeakFlow> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getPeakFlow();
    }

    /**
     * Returns a collection of PeakFlowConverter.
     *
     * @return a collection of PeakFlowConverter
     */
    @XmlElement
    public Collection<PeakFlowConverter> getPeakFlow() {
        if (items == null) {
            items = new ArrayList<PeakFlowConverter>();
        }
        if (entities != null) {
            items.clear();
            for (PeakFlow entity : entities) {
                items.add(new PeakFlowConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of PeakFlowConverter.
     *
     * @param a collection of PeakFlowConverter to set
     */
    public void setPeakFlow(Collection<PeakFlowConverter> items) {
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
     * Returns a collection PeakFlow entities.
     *
     * @return a collection of PeakFlow entities
     */
    @XmlTransient
    public Collection<PeakFlow> getEntities() {
        entities = new ArrayList<PeakFlow>();
        if (items != null) {
            for (PeakFlowConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
