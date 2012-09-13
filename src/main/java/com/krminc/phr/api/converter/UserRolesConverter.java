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

import com.krminc.phr.domain.UserRole;
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

@XmlRootElement(name = "userRoles")
public class UserRolesConverter {
    private Collection<UserRole> entities;
    private Collection<UserRoleConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of UserRolesConverter */
    public UserRolesConverter() {
    }

    /**
     * Creates a new instance of UserRolesConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public UserRolesConverter(Collection<UserRole> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getUserRole();
    }

    /**
     * Returns a collection of UserRoleConverter.
     *
     * @return a collection of UserRoleConverter
     */
    @XmlElement
    public Collection<UserRoleConverter> getUserRole() {
        if (items == null) {
            items = new ArrayList<UserRoleConverter>();
        }
        if (entities != null) {
            items.clear();
            for (UserRole entity : entities) {
                items.add(new UserRoleConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of UserRoleConverter.
     *
     * @param a collection of UserRoleConverter to set
     */
    public void setUserRole(Collection<UserRoleConverter> items) {
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
     * Returns a collection UserRole entities.
     *
     * @return a collection of UserRole entities
     */
    @XmlTransient
    public Collection<UserRole> getEntities() {
        entities = new ArrayList<UserRole>();
        if (items != null) {
            for (UserRoleConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
