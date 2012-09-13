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

package com.krminc.phr.api.converter;

import com.krminc.phr.domain.Address;
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

@XmlRootElement(name = "addresses")
public class AddressesConverter {
    private Collection<Address> entities;
    private Collection<AddressConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of AddressesConverter */
    public AddressesConverter() {
    }

    /**
     * Creates a new instance of AddressesConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public AddressesConverter(Collection<Address> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getAddress();
    }

    /**
     * Returns a collection of AddressConverter.
     *
     * @return a collection of AddressConverter
     */
    @XmlElement
    public Collection<AddressConverter> getAddress() {
        if (items == null) {
            items = new ArrayList<AddressConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Address entity : entities) {
                items.add(new AddressConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of AddressConverter.
     *
     * @param a collection of AddressConverter to set
     */
    public void setAddress(Collection<AddressConverter> items) {
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
     * Returns a collection Address entities.
     *
     * @return a collection of Address entities
     */
    @XmlTransient
    public Collection<Address> getEntities() {
        entities = new ArrayList<Address>();
        if (items != null) {
            for (AddressConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
