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
import com.krminc.phr.domain.clinical.CcrResultTest;
import java.net.URI;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;

/**
 *
 * @author cmccall
 */

@XmlRootElement(name = "ccrResultTest")
public class CcrResultTestConverter extends GenericResultTestConverter {
    private CcrResultTest entity;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of CcrResultTestConverter */
    public CcrResultTestConverter() {
        entity = new CcrResultTest();
    }

    /**
     * Creates a new instance of CcrResultTestConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public CcrResultTestConverter(CcrResultTest entity, URI uri, int expandLevel, boolean isUriExtendable) {
        super(entity);
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getId() + "/").build() : uri;
        this.expandLevel = expandLevel;
    }

    /**
     * Creates a new instance of CcrResultTestConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public CcrResultTestConverter(CcrResultTest entity, URI uri, int expandLevel) {
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
     * Returns the URI associated with this converter.
     *
     * @return the uri
     */
    @XmlAttribute
    public URI getUri() {
        return uri;
    }

    /**
     * Returns the CcrResultTest entity.
     *
     * @return an entity
     */
    @XmlTransient
    public CcrResultTest getEntity() {
        if (entity.getId() == null) {
            CcrResultTestConverter converter = UriResolver.getInstance().resolve(CcrResultTestConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved CcrResultTest entity.
     *
     * @return an resolved entity
     */
    public CcrResultTest resolveEntity(EntityManager em) {
        return entity;
    }
}
