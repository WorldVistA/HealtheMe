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
import java.util.List;
import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;

/**
 *
 * @author dshaw
 */

@XmlRootElement(name = "resource")
public class ResourceConverter {
    private Resource entity;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of ResourceConverter */
    public ResourceConverter() {
        entity = new Resource();
    }

    /**
     * Creates a new instance of ResourceConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public ResourceConverter(Resource entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getId() + "/").build() : uri;
        this.expandLevel = expandLevel;
//        getResourceCredentials();
    }

    /**
     * Creates a new instance of ResourceConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ResourceConverter(Resource entity, URI uri, int expandLevel) {
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
     * Getter for active.
     *
     * @return value for active
     */
    @XmlElement
    public Boolean getActive() {
        return (expandLevel > 0) ? entity.getActive() : null;
    }

    /**
     * Setter for active.
     *
     * @param value the value to set
     */
    public void setActive(Boolean value) {
        entity.setActive(value);
    }

    /**
     * Getter for type.
     *
     * @return value for type
     */
    @XmlElement
    public String getType() {
        return (expandLevel > 0) ? entity.getType() : null;
    }

    /**
     * Setter for type.
     *
     * @param value the value to set
     */
    public void setType(String value) {
        entity.setType(value);
    }

    /**
     * Getter for displayName.
     *
     * @return value for displayName
     */
    @XmlElement
    public String getDisplayName() {
        return (expandLevel > 0) ? entity.getDisplayName() : null;
    }

    /**
     * Setter for displayName.
     *
     * @param value the value to set
     */
    public void setDisplayName(String value) {
        entity.setDisplayName(value);
    }

    /**
     * Getter for resourceLocationPath.
     *
     * @return value for resourceLocationPath
     */
    @XmlElement
    public String getResourceLocationPath() {
        return (expandLevel > 0) ? entity.getResourceLocationPath() : null;
    }

    /**
     * Setter for resourceLocationPath.
     *
     * @param value the value to set
     */
    public void setResourceLocationPath(String value) {
        entity.setResourceLocationPath(value);
    }

    /**
     * Getter for resourceNamespace.
     *
     * @return value for resourceNamespace
     */
    @XmlElement
    public String getResourceNamespace() {
        return (expandLevel > 0) ? entity.getResourceNamespace() : null;
    }

    /**
     * Setter for resourceNamespace.
     *
     * @param value the value to set
     */
    public void setResourceNamespace(String value) {
        entity.setResourceNamespace(value);
    }

    /**
     * Getter for resourceLocalPart.
     *
     * @return value for resourceLocalPart
     */
    @XmlElement
    public String getResourceLocalPart() {
        return (expandLevel > 0) ? entity.getResourceLocalPart() : null;
    }

    /**
     * Setter for resourceLocalPart.
     *
     * @param value the value to set
     */
    public void setResourceLocalPart(String value) {
        entity.setResourceLocalPart(value);
    }

    /**
     * Getter for createDate.
     *
     * @return value for createDate
     */
    @XmlElement
    public Date getCreateDate() {
        return (expandLevel > 0) ? entity.getCreateDate() : null;
    }

    /**
     * Setter for createDate.
     *
     * @param value the value to set
     */
    public void setCreateDate(Date value) {
        entity.setCreateDate(value);
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
     * Getter for resourceCredentials.
     *
     * @return value for resourceCredentials
     */
//    @XmlElement
//    public ResourceCredentialsConverter getResourceCredentials() {
//        if (expandLevel > 0) {
//            if (entity.getResourceCredentials() != null) {
//                return new ResourceCredentialsConverter(entity.getResourceCredentials(), uri.resolve("resourceCredentials/"), expandLevel - 1);
//            }
//        }
//        return null;
//    }

    /**
     * Setter for resourceCredentials.
     *
     * @param value the value to set
     */
//    public void setResourceCredentials(ResourceCredentialsConverter value) {
//        entity.setResourceCredentials((value != null) ? new java.util.ArrayList<ResourceCredential>(value.getEntities()) : null);
//    }

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
     * Returns the Resource entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Resource getEntity() {
        if (entity.getId() == null) {
            ResourceConverter converter = UriResolver.getInstance().resolve(ResourceConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved Resource entity.
     *
     * @return an resolved entity
     */
    public Resource resolveEntity(EntityManager em) {
        List<ResourceCredential> resourceCredentials = entity.getResourceCredentials();
        List<ResourceCredential> newresourceCredentials = new java.util.ArrayList<ResourceCredential>();
        for (ResourceCredential item : resourceCredentials) {
            newresourceCredentials.add(em.getReference(ResourceCredential.class, item.getId()));
        }
        entity.setResourceCredentials(newresourceCredentials);
        return entity;
    }
}
