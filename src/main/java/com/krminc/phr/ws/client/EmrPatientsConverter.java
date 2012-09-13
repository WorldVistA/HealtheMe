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
package com.krminc.phr.ws.client;

import com.krminc.phr.ws.FMPatient;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * EMR Patients Converter JAXB Object.
 *
 * @author Daniel Shaw (dshaw.com)
 */
@XmlRootElement(name = "patients")
public class EmrPatientsConverter {
    private Collection<FMPatient> entities;
    private Collection<EmrPatientConverter> items;
    private URI uri;
    private int expandLevel;


    public EmrPatientsConverter() {
    }

    /**
     * Creates a new instance of FMPatientsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public EmrPatientsConverter(Collection<FMPatient> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getEmrPatients();
    }

    /**
     * Returns a collection of UserConverter.
     *
     * @return a collection of UserConverter
     */
    @XmlElement
    public Collection<EmrPatientConverter> getEmrPatients() {
        if (items == null) {
            items = new ArrayList<EmrPatientConverter>();
        }
        if (entities != null) {
            items.clear();
            for (FMPatient entity : entities) {
                items.add(new EmrPatientConverter(entity));
            }
        }
        return items;
    }

    /**
     * Sets a collection of UserConverter.
     *
     * @param a collection of UserConverter to set
     */
    public void setUser(Collection<EmrPatientConverter> items) {
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
     * Returns a collection User entities.
     *
     * @return a collection of User entities
     */
    @XmlTransient
    public Collection<FMPatient> getEntities() {
        entities = new ArrayList<FMPatient>();
        if (items != null) {
            for (EmrPatientConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
