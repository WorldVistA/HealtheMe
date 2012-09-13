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

import com.krminc.phr.domain.clinical.CcrAlert;
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

@XmlRootElement(name = "ccrAlerts")
public class CcrAlertsConverter {
    private Collection<CcrAlert> entities;
    private Collection<CcrAlertConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of CcrAlertsConverter */
    public CcrAlertsConverter() {
    }

    /**
     * Creates a new instance of CcrAlertsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public CcrAlertsConverter(Collection<CcrAlert> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getCcrAlert();
    }

    /**
     * Returns a collection of CcrAlertConverter.
     *
     * @return a collection of CcrAlertConverter
     */
    @XmlElement
    public Collection<CcrAlertConverter> getCcrAlert() {
        if (items == null) {
            items = new ArrayList<CcrAlertConverter>();
        }
        if (entities != null) {
            items.clear();
            for (CcrAlert entity : entities) {
                items.add(new CcrAlertConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of CcrAlertConverter.
     *
     * @param a collection of CcrAlertConverter to set
     */
    public void setCcrAlert(Collection<CcrAlertConverter> items) {
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
     * Returns a collection CcrAlert entities.
     *
     * @return a collection of CcrAlert entities
     */
    @XmlTransient
    public Collection<CcrAlert> getEntities() {
        entities = new ArrayList<CcrAlert>();
        if (items != null) {
            for (CcrAlertConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
