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
package com.krminc.phr.api.converter;

import com.krminc.phr.api.converter.util.ConverterUtils;
import com.krminc.phr.domain.Address;
import com.krminc.phr.domain.User;
import java.math.BigInteger;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;

/**
 * Address Converter Class.
 * @author Daniel Shaw (dshaw.com)
 */

@XmlRootElement(name = "address")
public class AddressConverter {
    private Address entity;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of AddressConverter */
    public AddressConverter() {
        entity = new Address();
    }

    /**
     * Creates a new instance of AddressConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public AddressConverter(Address entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getAddressId() + "/").build() : uri;
        this.expandLevel = expandLevel;
        getUser();
    }

    /**
     * Creates a new instance of AddressConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public AddressConverter(Address entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for addressId.
     *
     * @return value for addressId
     */
    @XmlElement
    public Long getAddressId() {
        return (expandLevel > 0) ? entity.getAddressId() : null;
    }

    /**
     * Setter for addressId.
     *
     * @param value the value to set
     */
    public void setAddressId(Long value) {
        entity.setAddressId(value);
    }

    /**
     * Getter for primaryAddress.
     *
     * @return value for primaryAddress
     */
    @XmlElement
    public Boolean getPrimaryAddress() {
        return (expandLevel > 0) ? entity.getPrimaryAddress() : null;
    }

    /**
     * Setter for primaryAddress.
     *
     * @param value the value to set
     */
    public void setPrimaryAddress(Boolean value) {
        entity.setPrimaryAddress(value);
    }

    /**
     * Getter for address1.
     *
     * @return value for address1
     */
    @XmlElement
    public String getAddress1() {
        return (expandLevel > 0) ? entity.getAddress1() : null;
    }

    /**
     * Setter for address1.
     *
     * @param value the value to set
     */
    public void setAddress1(String value) {
        value = ConverterUtils.prepareInput(value);
        entity.setAddress1(value);
    }

    /**
     * Getter for address2.
     *
     * @return value for address2
     */
    @XmlElement
    public String getAddress2() {
        return (expandLevel > 0) ? entity.getAddress2() : null;
    }

    /**
     * Setter for address2.
     *
     * @param value the value to set
     */
    public void setAddress2(String value) {
        value = ConverterUtils.prepareInput(value);
        entity.setAddress2(value);
    }


    /**
     * Getter for address3.
     *
     * @return value for address3
     */
    @XmlElement
    public String getAddress3() {
        return (expandLevel > 0) ? entity.getAddress3() : null;
    }

    /**
     * Setter for address3.
     *
     * @param value the value to set
     */
    public void setAddress3(String value) {
        value = ConverterUtils.prepareInput(value);
        entity.setAddress3(value);
    }

    /**
     * Getter for city.
     *
     * @return value for city
     */
    @XmlElement
    public String getCity() {
        return (expandLevel > 0) ? entity.getCity() : null;
    }

    /**
     * Setter for city.
     *
     * @param value the value to set
     */
    public void setCity(String value) {
        value = ConverterUtils.prepareInput(value);
        entity.setCity(value);
    }

    /**
     * Getter for state.
     *
     * @return value for state
     */
    @XmlElement
    public String getState() {
        return (expandLevel > 0) ? entity.getState() : null;
    }

    /**
     * Setter for state.
     *
     * @param value the value to set
     */
    public void setState(String value) {
        value = ConverterUtils.prepareInput(value);
        entity.setState(value);
    }

    /**
     * Getter for province.
     *
     * @return value for province
     */
    @XmlElement
    public String getProvince() {
        return (expandLevel > 0) ? entity.getProvince() : null;
    }

    /**
     * Setter for province.
     *
     * @param value the value to set
     */
    public void setProvince(String value) {
        value = ConverterUtils.prepareInput(value);
        entity.setProvince(value);
    }

    /**
     * Getter for zip.
     *
     * @return value for zip
     */
    @XmlElement
    public String getZip() {
        return (expandLevel > 0) ? entity.getZip() : null;
    }

    /**
     * Setter for zip.
     *
     * @param value the value to set
     */
    public void setZip(String value) {
        value = ConverterUtils.prepareInput(value);
        entity.setZip(value);
    }

    /**
     * Getter for country.
     *
     * @return value for country
     */
    @XmlElement
    public String getCountry() {
        return (expandLevel > 0) ? entity.getCountry() : null;
    }

    /**
     * Setter for country.
     *
     * @param value the value to set
     */
    public void setCountry(String value) {
        value = ConverterUtils.prepareInput(value);
        entity.setCountry(value);
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
     * Getter for createdByUserId.
     *
     * @return value for createdByUserId
     */
    @XmlElement
    public BigInteger getCreatedByUserId() {
        return (expandLevel > 0) ? entity.getCreatedByUserId() : null;
    }

    /**
     * Setter for createdByUserId.
     *
     * @param value the value to set
     */
    public void setCreatedByUserId(BigInteger value) {
        entity.setCreatedByUserId(value);
    }

    /**
     * Getter for lastUpdated.
     *
     * @return value for lastUpdated
     */
    @XmlElement
    public Date getLastUpdated() {
        return (expandLevel > 0) ? entity.getLastUpdated() : null;
    }

    /**
     * Setter for lastUpdated.
     *
     * @param value the value to set
     */
    public void setLastUpdated(Date value) {
        entity.setLastUpdated(value);
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
     * Returns the Address entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Address getEntity() {
        if (entity.getAddressId() == null) {
            AddressConverter converter = UriResolver.getInstance().resolve(AddressConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved Address entity.
     *
     * @return an resolved entity
     */
    public Address resolveEntity(EntityManager em) {
        User user = entity.getUser();
        if (user != null) {
            entity.setUser(em.getReference(User.class, user.getUserId()));
        }
        return entity;
    }
}
