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
import com.krminc.phr.domain.HealthRecord;
import com.krminc.phr.domain.User;
import com.krminc.phr.domain.UserRole;
import java.net.URI;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author cmccall
 */

@XmlRootElement(name = "user")
public class UserConverter {
    private User entity;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of UserConverter */
    public UserConverter() {
        entity = new User();
    }

    /**
     * Creates a new instance of UserConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public UserConverter(User entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getUserId() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getRoles();
        getAddresses();
        getHealthrecords();
    }

    /**
     * Creates a new instance of UserConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public UserConverter(User entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for userId.
     *
     * @return value for userId
     */
    @XmlElement
    public Long getUserId() {
        return (expandLevel > 0) ? entity.getUserId() : null;
    }

    /**
     * Setter for userId.
     *
     * @param value the value to set
     */
    public void setUserId(Long value) {
        entity.setUserId(value);
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
     * Getter for title.
     *
     * @return value for title
     */
    @XmlElement
    public String getTitle() {
        return (expandLevel > 0) ? entity.getTitle() : null;
    }

    /**
     * Setter for title.
     *
     * @param value the value to set
     */
    public void setTitle(String value) {
        entity.setTitle(value);
    }

    /**
     * Getter for firstName.
     *
     * @return value for firstName
     */
    @XmlElement
    public String getFirstName() {
        return (expandLevel > 0) ? entity.getFirstName() : null;
    }

    /**
     * Setter for firstName.
     *
     * @param value the value to set
     */
    public void setFirstName(String value) {
        entity.setFirstName(value);
    }

    /**
     * Getter for middleName.
     *
     * @return value for middleName
     */
    @XmlElement
    public String getMiddleName() {
        return (expandLevel > 0) ? entity.getMiddleName() : null;
    }

    /**
     * Setter for middleName.
     *
     * @param value the value to set
     */
    public void setMiddleName(String value) {
        entity.setMiddleName(value);
    }

    /**
     * Getter for lastName.
     *
     * @return value for lastName
     */
    @XmlElement
    public String getLastName() {
        return (expandLevel > 0) ? entity.getLastName() : null;
    }

    /**
     * Setter for lastName.
     *
     * @param value the value to set
     */
    public void setLastName(String value) {
        entity.setLastName(value);
    }

    /**
     * Getter for suffix.
     *
     * @return value for suffix
     */
    @XmlElement
    public String getSuffix() {
        return (expandLevel > 0) ? entity.getSuffix() : null;
    }

    /**
     * Setter for suffix.
     *
     * @param value the value to set
     */
    public void setSuffix(String value) {
        entity.setSuffix(value);
    }

    /**
     * Getter for preferredName.
     *
     * @return value for preferredName
     */
    @XmlElement
    public String getPreferredName() {
        return (expandLevel > 0) ? entity.getPreferredName() : null;
    }

    /**
     * Setter for preferredName.
     *
     * @param value the value to set
     */
    public void setPreferredName(String value) {
        entity.setPreferredName(value);
    }

    /**
     * Getter for telnumHome.
     *
     * @return value for telnumHome
     */
    @XmlElement
    public String getTelnumHome() {
        return (expandLevel > 0) ? entity.getTelnumHome() : null;
    }

    /**
     * Setter for telnumHome.
     *
     * @param value the value to set
     */
    public void setTelnumHome(String value) {
        entity.setTelnumHome(value);
    }

    /**
     * Getter for telnumWork.
     *
     * @return value for telnumWork
     */
    @XmlElement
    public String getTelnumWork() {
        return (expandLevel > 0) ? entity.getTelnumWork() : null;
    }

    /**
     * Setter for telnumWork.
     *
     * @param value the value to set
     */
    public void setTelnumWork(String value) {
        entity.setTelnumWork(value);
    }

    /**
     * Getter for telnumMobile.
     *
     * @return value for telnumMobile
     */
    @XmlElement
    public String getTelnumMobile() {
        return (expandLevel > 0) ? entity.getTelnumMobile() : null;
    }

    /**
     * Setter for telnumMobile.
     *
     * @param value the value to set
     */
    public void setTelnumMobile(String value) {
        entity.setTelnumMobile(value);
    }

    /**
     * Getter for faxnum.
     *
     * @return value for faxnum
     */
    @XmlElement
    public String getFaxnum() {
        return (expandLevel > 0) ? entity.getFaxnum() : null;
    }

    /**
     * Setter for faxnum.
     *
     * @param value the value to set
     */
    public void setFaxnum(String value) {
        entity.setFaxnum(value);
    }

    /**
     * Getter for email.
     *
     * @return value for email
     */
    @XmlElement
    public String getEmail() {
        return (expandLevel > 0) ? entity.getEmail() : null;
    }

    /**
     * Setter for email.
     *
     * @param value the value to set
     */
    public void setEmail(String value) {
        entity.setEmail(value);
    }

    /**
     * Getter for question1Id.
     *
     * @return value for question1Id
     */
    @XmlElement
    public Integer getQuestion1Id() {
        return (expandLevel > 0) ? entity.getQuestion1Id() : null;
    }

    /**
     * Setter for question1Id.
     *
     * @param value the value to set
     */
    public void setQuestion1Id(Integer value) {
        entity.setQuestion1Id(value);
    }

    /**
     * Getter for question1Answer.
     *
     * @return value for question1Answer
     */
    @XmlElement
    public String getQuestion1Answer() {
        return (expandLevel > 0) ? entity.getQuestion1Answer() : null;
    }

    /**
     * Setter for question1Answer.
     *
     * @param value the value to set
     */
    public void setQuestion1Answer(String value) {
        entity.setQuestion1Answer(value);
    }

    /**
     * Getter for question2Id.
     *
     * @return value for question2Id
     */
    @XmlElement
    public Integer getQuestion2Id() {
        return (expandLevel > 0) ? entity.getQuestion2Id() : null;
    }

    /**
     * Setter for question2Id.
     *
     * @param value the value to set
     */
    public void setQuestion2Id(Integer value) {
        entity.setQuestion2Id(value);
    }

    /**
     * Getter for question2Answer.
     *
     * @return value for question2Answer
     */
    @XmlElement
    public String getQuestion2Answer() {
        return (expandLevel > 0) ? entity.getQuestion2Answer() : null;
    }

    /**
     * Setter for question2Answer.
     *
     * @param value the value to set
     */
    public void setQuestion2Answer(String value) {
        entity.setQuestion2Answer(value);
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
     * Getter for lastLogin.
     *
     * @return value for lastLogin
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getLastLogin() {
        return (expandLevel > 0) ? entity.getLastLogin() : null;
    }

    /**
     * Setter for lastLogin.
     *
     * @param value the value to set
     */
    public void setLastLogin(Date value) {
        entity.setLastLogin(value);
    }

    /**
     * Getter for totalLogin.
     *
     * @return value for totalLogin
     */
    @XmlElement
    public Integer getTotalLogin() {
        return (expandLevel > 0) ? entity.getTotalLogin() : null;
    }

    /**
     * Getter for roles.
     *
     * @return value for roles
     */
    @XmlElement
    public UserRolesConverter getRoles() {
        if (entity.getRoles() != null) {
            return new UserRolesConverter(entity.getRoles(), uri.resolve("roles/"), 1);
        }
        return null;
    }

    /**
     * Setter for roles.
     *
     * @param value the value to set
     */
    public void setRoles(UserRolesConverter value) {
        entity.setRoles((value != null) ? new java.util.ArrayList<UserRole>(value.getEntities()) : null);
    }

    /**
     * Getter for addresses.
     *
     * @return value for addresses
     */
    @XmlElement
    public AddressesConverter getAddresses() {
        if (expandLevel > 0) {
            if (entity.getAddresses() != null) {
                return new AddressesConverter(entity.getAddresses(), uri.resolve("addresses/"), expandLevel - 1);
            }
        }
        return null;
    }

    /**
     * Setter for addresses.
     *
     * @param value the value to set
     */
    public void setAddresses(AddressesConverter value) {
        entity.setAddresses((value != null) ? new java.util.ArrayList<Address>(value.getEntities()) : null);
    }

    /**
     * Getter for healthrecords.
     *
     * @return value for healthrecords
     */
    @XmlElement
    public HealthRecordsConverter getHealthrecords() {
        if (expandLevel > 0) {
            if (entity.getHealthRecords() != null) {
                return new HealthRecordsConverter(entity.getHealthRecords(), uri.resolve("healthrecords/"), expandLevel - 1);
            }
        }
        return null;
    }

    /**
     * Setter for healthrecords.
     *
     * @param value the value to set
     */
    public void setHealthrecords(HealthRecordsConverter value) {
        entity.setHealthRecords((value != null) ? new java.util.ArrayList<HealthRecord>(value.getEntities()) : null);
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
     * Returns the User entity.
     *
     * @return an entity
     */
    @XmlTransient
    public User getEntity() {
        if (entity.getUserId() == null) {
            UserConverter converter = UriResolver.getInstance().resolve(UserConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved User entity.
     *
     * @return an resolved entity
     */
    public User resolveEntity(EntityManager em) {
        List<UserRole> roles = entity.getRoles();
        List<UserRole> newroles = new java.util.ArrayList<UserRole>();
        for (UserRole item : roles) {
            newroles.add(em.getReference(UserRole.class, item.getId()));
        }
        entity.setRoles(newroles);
        List<Address> addresses = entity.getAddresses();
        List<Address> newaddresses = new java.util.ArrayList<Address>();
        for (Address item : addresses) {
            newaddresses.add(em.getReference(Address.class, item.getAddressId()));
        }
        entity.setAddresses(newaddresses);
        List<HealthRecord> healthrecords = entity.getHealthRecords();
        List<HealthRecord> newhealthrecords = new java.util.ArrayList<HealthRecord>();
        for (HealthRecord item : healthrecords) {
            newhealthrecords.add(em.getReference(HealthRecord.class, item.getHealthRecordId()));
        }
        entity.setHealthRecords(newhealthrecords);
        return entity;
    }
}
