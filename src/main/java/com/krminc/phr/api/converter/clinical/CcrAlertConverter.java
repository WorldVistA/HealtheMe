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

import com.krminc.phr.api.converter.DateAdapter;
import com.krminc.phr.domain.clinical.CcrAlert;
import com.krminc.phr.api.converter.UriResolver;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author cmccall
 */

@XmlRootElement(name = "ccrAlert")
public class CcrAlertConverter {
    private CcrAlert entity;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of CcrAlertConverter */
    public CcrAlertConverter() {
        entity = new CcrAlert();
    }

    /**
     * Creates a new instance of CcrAlertConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public CcrAlertConverter(CcrAlert entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getId() + "/").build() : uri;
        this.expandLevel = expandLevel;
    }

    /**
     * Creates a new instance of CcrAlertConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public CcrAlertConverter(CcrAlert entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for title.
     *
     * @return value for title
     */
    @XmlElement
    public String getAlertTitle() {
        if (!(expandLevel > 0)) return null;
        String title = entity.getProductDescription();
        if (title == null || title.length() < 1) {
            title = entity.getEnvironmentalAgentDescription();
            if (title == null || title.length() < 1) {
                title = entity.getProductName();
                if (title == null || title.length() < 1) {
                    title = entity.getDescriptionText();
                }
            }
        }
        return title;
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
     * Getter for dataObjectId.
     *
     * @return value for dataObjectId
     */
//    @XmlElement
//    public String getDataObjectId() {
//        return (expandLevel > 0) ? entity.getDataObjectId() : null;
//    }

//    /**
//     * Getter for productDescription.
//     *
//     * @return value for productDescription
//     */
//    @XmlElement
//    public String getProductDescription() {
//        return (expandLevel > 0) ? entity.getProductDescription() : null;
//    }

//    /**
//     * Getter for descriptionText.
//     *
//     * @return value for descriptionText
//     */
//    @XmlElement
//    public String getDescriptionText() {
//        return (expandLevel > 0) ? entity.getDescriptionText() : null;
//    }

//    /**
//     * Getter for environmentalAgentDescription.
//     *
//     * @return value for environmentalAgentDescription
//     */
//    @XmlElement
//    public String getEnvironmentalAgentDescription() {
//        return (expandLevel > 0) ? entity.getEnvironmentalAgentDescription() : null;
//    }

//    /**
//     * Getter for productName.
//     *
//     * @return value for productName
//     */
//    @XmlElement
//    public String getProductName() {
//        return (expandLevel > 0) ? entity.getProductName() : null;
//    }

    /**
     * Getter for reactionDescription.
     *
     * @return value for reactionDescription
     */
    @XmlElement
    public String getReactionDescription() {
        return (expandLevel > 0) ? entity.getReactionDescription() : null;
    }

    /**
     * Getter for reactionSeverity.
     *
     * @return value for reactionSeverity
     */
    @XmlElement
    public String getReactionSeverity() {
        return (expandLevel > 0) ? entity.getReactionSeverity() : null;
    }

    /**
     * Getter for notes.
     *
     * @return value for notes
     */
//    @XmlElement
//    public String getNotes() {
//        return (expandLevel > 0) ? entity.getNotes() : null;
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

    /** Custom getters (not autogenerated) **/

    /**
     * Getter for dateAdded.
     *
     * @return value for dateAdded
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getDateAdded() {
        return (expandLevel > 0) ? entity.getDateAdded() : null;
    }

    /**
     * Getter for source.
     *
     * @return value for source
     */
    @XmlElement
    public String getSource() {
        return (expandLevel > 0) ? entity.getSource() : null;
    }

    /**
     * Getter for exactDateTime.
     *
     * @return value for exactDateTime
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getExactDateTime() {
        return (expandLevel > 0) ? entity.getExactDateTime() : null;
    }

    /** End Custom getters **/

    /**
     * Returns the CcrAlert entity.
     *
     * @return an entity
     */
    @XmlTransient
    public CcrAlert getEntity() {
        if (entity.getId() == null) {
            CcrAlertConverter converter = UriResolver.getInstance().resolve(CcrAlertConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved CcrAlert entity.
     *
     * @return an resolved entity
     */
    public CcrAlert resolveEntity(EntityManager em) {
        return entity;
    }
}
