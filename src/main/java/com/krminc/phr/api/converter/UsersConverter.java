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

import com.krminc.phr.domain.User;
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

@XmlRootElement(name = "users")
public class UsersConverter {
    private Collection<User> entities;
    private Collection<UserConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of UsersConverter */
    public UsersConverter() {
    }

    /**
     * Creates a new instance of UsersConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public UsersConverter(Collection<User> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getUser();
    }

    /**
     * Returns a collection of UserConverter.
     *
     * @return a collection of UserConverter
     */
    @XmlElement
    public Collection<UserConverter> getUser() {
        if (items == null) {
            items = new ArrayList<UserConverter>();
        }
        if (entities != null) {
            items.clear();
            for (User entity : entities) {
                items.add(new UserConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of UserConverter.
     *
     * @param a collection of UserConverter to set
     */
    public void setUser(Collection<UserConverter> items) {
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
    public Collection<User> getEntities() {
        entities = new ArrayList<User>();
        if (items != null) {
            for (UserConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
