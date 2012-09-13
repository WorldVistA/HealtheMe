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

import com.krminc.phr.domain.clinical.ResourceCredential;
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

@XmlRootElement(name = "resourceCredentials")
public class ResourceCredentialsConverter {
    private Collection<ResourceCredential> entities;
    private Collection<ResourceCredentialConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of ResourceCredentialsConverter */
    public ResourceCredentialsConverter() {
    }

    /**
     * Creates a new instance of ResourceCredentialsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ResourceCredentialsConverter(Collection<ResourceCredential> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getResourceCredential();
    }

    /**
     * Returns a collection of ResourceCredentialConverter.
     *
     * @return a collection of ResourceCredentialConverter
     */
    @XmlElement
    public Collection<ResourceCredentialConverter> getResourceCredential() {
        if (items == null) {
            items = new ArrayList<ResourceCredentialConverter>();
        }
        if (entities != null) {
            items.clear();
            for (ResourceCredential entity : entities) {
                items.add(new ResourceCredentialConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of ResourceCredentialConverter.
     *
     * @param a collection of ResourceCredentialConverter to set
     */
    public void setResourceCredential(Collection<ResourceCredentialConverter> items) {
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
     * Returns a collection ResourceCredential entities.
     *
     * @return a collection of ResourceCredential entities
     */
    @XmlTransient
    public Collection<ResourceCredential> getEntities() {
        entities = new ArrayList<ResourceCredential>();
        if (items != null) {
            for (ResourceCredentialConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
