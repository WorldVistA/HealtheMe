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

import com.krminc.phr.api.converter.UriResolver;
import com.krminc.phr.domain.clinical.Resource;
import com.krminc.phr.domain.clinical.ResourceCredential;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;

/**
 *
 * @author dshaw
 */

@XmlRootElement(name = "resourceCredential")
public class ResourceCredentialConverter {
    private ResourceCredential entity;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of ResourceCredentialConverter */
    public ResourceCredentialConverter() {
        entity = new ResourceCredential();
    }

    /**
     * Creates a new instance of ResourceCredentialConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public ResourceCredentialConverter(ResourceCredential entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getId() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getResource();
    }

    /**
     * Creates a new instance of ResourceCredentialConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ResourceCredentialConverter(ResourceCredential entity, URI uri, int expandLevel) {
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
     * Getter for resource.
     *
     * @return value for resource
     */
    @XmlElement
    public ResourceConverter getResource() {
        if (expandLevel > 0) {
            if (entity.getResource() != null) {
                return new ResourceConverter(entity.getResource(), uri.resolve("resource/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for resource.
     *
     * @param value the value to set
     */
    public void setResource(ResourceConverter value) {
        entity.setResource((value != null) ? value.getEntity() : null);
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
     * Getter for password.
     *
     * @return value for password
     */
    @XmlElement
    public String getPassword() {
        return (expandLevel > 0) ? entity.getPassword() : null;
    }

    /**
     * Setter for password.
     *
     * @param value the value to set
     */
    public void setPassword(String value) {
        entity.setPassword(value);
    }

    /**
     * Getter for lastUpdate.
     *
     * @return value for lastUpdate
     */
    @XmlElement
    public Date getLastUpdate() {
        return (expandLevel > 0) ? entity.getLastUpdate() : null;
    }

    /**
     * Setter for lastUpdate.
     *
     * @param value the value to set
     */
    public void setLastUpdate(Date value) {
        entity.setLastUpdate(value);
    }

    /**
     * Getter for notes.
     *
     * @return value for notes
     */
    @XmlElement
    public String getNotes() {
        return (expandLevel > 0) ? entity.getNotes() : null;
    }

    /**
     * Setter for notes.
     *
     * @param value the value to set
     */
    public void setNotes(String value) {
        entity.setNotes(value);
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
     * Returns the ResourceCredential entity.
     *
     * @return an entity
     */
    @XmlTransient
    public ResourceCredential getEntity() {
        if (entity.getId() == null) {
            ResourceCredentialConverter converter = UriResolver.getInstance().resolve(ResourceCredentialConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved ResourceCredential entity.
     *
     * @return an resolved entity
     */
    public ResourceCredential resolveEntity(EntityManager em) {
        Resource resource = entity.getResource();
        if (resource != null) {
            entity.setResource(em.getReference(Resource.class, resource.getId()));
        }
        return entity;
    }
}
