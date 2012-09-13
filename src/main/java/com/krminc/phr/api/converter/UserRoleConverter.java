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
import java.util.Date;
import javax.persistence.EntityManager;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dshaw
 */

@XmlRootElement(name = "userRole")
public class UserRoleConverter {
    private UserRole entity;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of UserRoleConverter */
    public UserRoleConverter() {
        entity = new UserRole();
    }

    /**
     * Creates a new instance of UserRoleConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public UserRoleConverter(UserRole entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getId() + "/").build() : uri;
        this.expandLevel = expandLevel;
    }

    /**
     * Creates a new instance of UserRoleConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public UserRoleConverter(UserRole entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for id.
     *
     * @return value for id
     */
    @XmlElement
    public Long getId() {
        return (expandLevel > 0) ? entity.getId() : null;
    }

    /**
     * Setter for id.
     *
     * @param value the value to set
     */
    public void setId(Long value) {
        entity.setId(value);
    }

    /**
     * Getter for username.
     *
     * @return value for username
     */
    @XmlElement
    public String getUsername() {
        return (expandLevel > 0) ? entity.getUsername() : null;
    }

    /**
     * Setter for username.
     *
     * @param value the value to set
     */
    public void setUsername(String value) {
        entity.setUsername(value);
    }

    /**
     * Getter for role.
     *
     * @return value for role
     */
    @XmlElement
    public String getRole() {
        return (expandLevel > 0) ? entity.getRole() : null;
    }

    /**
     * Setter for role.
     *
     * @param value the value to set
     */
    public void setRole(String value) {
        entity.setRole(value);
    }

    /**
     * Getter for dateCreated.
     *
     * @return value for dateCreated
     */
    @XmlElement
    public Date getDateCreated() {
        return (expandLevel > 0) ? entity.getDateCreated() : null;
    }

    /**
     * Setter for dateCreated.
     *
     * @param value the value to set
     */
    public void setDateCreated(Date value) {
        entity.setDateCreated(value);
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
     * Sets the URI for this reference converter.
     *
     */
    public void setUri(URI uri) {
        this.uri = uri;
    }

    /**
     * Returns the UserRole entity.
     *
     * @return an entity
     */
    @XmlTransient
    public UserRole getEntity() {
        if (entity.getId() == null) {
            UserRoleConverter converter = UriResolver.getInstance().resolve(UserRoleConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved UserRole entity.
     *
     * @return an resolved entity
     */
    public UserRole resolveEntity(EntityManager em) {
        return entity;
    }
}
