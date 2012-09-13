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

import com.krminc.phr.domain.HealthRecord;
import java.net.URI;
import java.util.Calendar;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import com.krminc.phr.domain.User;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author cmccall
 */

@XmlRootElement(name = "healthRecord")
public class HealthRecordConverter {
    private HealthRecord entity;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of HealthRecordConverter */
    public HealthRecordConverter() {
        entity = new HealthRecord();
    }

    /**
     * Creates a new instance of HealthRecordConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public HealthRecordConverter(HealthRecord entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getHealthRecordId() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getUser();
    }

    /**
     * Creates a new instance of HealthRecordConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public HealthRecordConverter(HealthRecord entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for recId.
     *
     * @return value for recId
     */
    @XmlElement
    public Long getHealthRecordId() {
        return (expandLevel > 0) ? entity.getHealthRecordId() : null;
    }

    /**
     * Setter for recId.
     *
     * @param value the value to set
     */
    public void setHealthRecordId(Long value) {
        entity.setHealthRecordId(value);
    }

    /**
     * Getter for userId.
     *
     * @return value for userId
     */
//    @XmlElement
//    public Long getUserId() {
//        return (expandLevel > 0) ? entity.getUserId() : null;
//    }

    /**
     * Setter for userId.
     *
     * @param value the value to set
     */
//    public void setUserId(Long value) {
//        entity.setUserId(value);
//    }

    /**
     * Getter for userId.
     *
     * @return value for userId
     */
    @XmlElement
    public Boolean getPrimary() {
        return (expandLevel > 0) ? entity.getPrimary() : null;
    }

    /**
     * Setter for userId.
     *
     * @param value the value to set
     */
    public void setPrimary(Boolean value) {
        entity.setPrimary(value);
    }

    /**
     * Getter for gender.
     *
     * @return value for gender
     */
    @XmlElement
    public String getGender() {
        return (expandLevel > 0) ? entity.getGender() : null;
    }

    /**
     * Setter for gender.
     *
     * @param value the value to set
     */
    public void setGender(String value) {
        entity.setGender(value);
    }

    /**
     * Getter for dateOfBirth.
     *
     * @return value for dateOfBirth
     */
    @XmlElement
    @XmlJavaTypeAdapter(CalendarAdapter.class)
    public Calendar getDateOfBirth() {
        return (expandLevel > 0) ? entity.getDateOfBirth() : null;
    }

    /**
     * Setter for dateOfBirth.
     *
     * @param value the value to set
     */
    public void setDateOfBirth(Calendar value) {
        entity.setDateOfBirth(value);
    }

    /**
     * Getter for maritalStatus.
     *
     * @return value for maritalStatus
     */
    @XmlElement
    public String getMaritalStatus() {
        return (expandLevel > 0) ? entity.getMaritalStatus() : null;
    }

    /**
     * Setter for maritalStatus.
     *
     * @param value the value to set
     */
    public void setMaritalStatus(String value) {
        entity.setMaritalStatus(value);
    }

    /**
     * Getter for bloodType.
     *
     * @return value for bloodType
     */
    @XmlElement
    public String getBloodType() {
        return (expandLevel > 0) ? entity.getBloodType() : null;
    }

    /**
     * Setter for bloodType.
     *
     * @param value the value to set
     */
    public void setBloodType(String value) {
        entity.setBloodType(value);
    }

    /**
     * Getter for organDonor.
     *
     * @return value for organDonor
     */
    @XmlElement
    public Boolean getOrganDonor() {
        return (expandLevel > 0) ? entity.getOrganDonor() : null;
    }

    /**
     * Setter for organDonor.
     *
     * @param value the value to set
     */
    public void setOrganDonor(Boolean value) {
        entity.setOrganDonor(value);
    }

    /**
     * Getter for dateCreated.
     *
     * @return value for dateCreated
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
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
     * Getter for user.
     *
     * @return value for user
     */
    @XmlElement
    public UserConverter getUser() {
        if (expandLevel > 0) {
            if (entity.getUser() != null) {
                return new UserConverter(entity.getUser(), uri.resolve("user/"), expandLevel - 1, false);
            }
        }
        return null;
    }

    /**
     * Setter for user.
     *
     * @param value the value to set
     */
    public void setUser(UserConverter value) {
        entity.setUser((value != null) ? value.getEntity() : null);
    }

    /**
     * Getter for age.
     *
     * @return value for age
     */
    @XmlElement
    public Integer getAge() {
        return (expandLevel > 0) ? entity.getAge() : null;
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
     * Returns the HealthRecord entity.
     *
     * @return an entity
     */
    @XmlTransient
    public HealthRecord getEntity() {
        if (entity.getHealthRecordId() == null) {
            HealthRecordConverter converter = UriResolver.getInstance().resolve(HealthRecordConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved HealthRecord entity.
     *
     * @return an resolved entity
     */
    public HealthRecord resolveEntity(EntityManager em) {
        User user = entity.getUser();
        if (user != null) {
            entity.setUser(em.getReference(User.class, user.getUserId()));
        }
        return entity;
    }
}
