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
package com.krminc.phr.domain;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Address entity class.
 * 
 * @author Daniel Shaw (dshaw.com)
 */
@Entity
@Table(name = "user_addresses", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a"),
    @NamedQuery(name = "Address.findByAddressId", query = "SELECT a FROM Address a WHERE a.addressId = :addressId"),
    @NamedQuery(name = "Address.findByPrimaryAddress", query = "SELECT a FROM Address a WHERE a.primaryAddress = :primaryAddress"),
    @NamedQuery(name = "Address.findByAddress1", query = "SELECT a FROM Address a WHERE a.address1 = :address1"),
    @NamedQuery(name = "Address.findByAddress2", query = "SELECT a FROM Address a WHERE a.address2 = :address2"),
    @NamedQuery(name = "Address.findByAddress3", query = "SELECT a FROM Address a WHERE a.address3 = :address3"),
    @NamedQuery(name = "Address.findByCity", query = "SELECT a FROM Address a WHERE a.city = :city"),
    @NamedQuery(name = "Address.findByState", query = "SELECT a FROM Address a WHERE a.state = :state"),
    @NamedQuery(name = "Address.findByProvince", query = "SELECT a FROM Address a WHERE a.province = :province"),
    @NamedQuery(name = "Address.findByZip", query = "SELECT a FROM Address a WHERE a.zip = :zip"),
    @NamedQuery(name = "Address.findByCountry", query = "SELECT a FROM Address a WHERE a.country = :country"),
    @NamedQuery(name = "Address.findByDateCreated", query = "SELECT a FROM Address a WHERE a.dateCreated = :dateCreated"),
    @NamedQuery(name = "Address.findByCreatedByUserId", query = "SELECT a FROM Address a WHERE a.createdByUserId = :createdByUserId"),
    @NamedQuery(name = "Address.findByLastUpdated", query = "SELECT a FROM Address a WHERE a.lastUpdated = :lastUpdated")
})
public class Address implements Serializable {
    
    private static final long serialVersionUID = 20100124L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "address_id", nullable = false)
    private Long addressId;
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "primary_address")
    private Boolean primaryAddress;
    @Column(name = "address1", length = 200)
    private String address1;
    @Column(name = "address2", length = 200)
    private String address2;
    @Column(name = "address3", length = 200)
    private String address3;
    @Column(name = "city", length = 100)
    private String city;
    @Column(name = "state", length = 100)
    private String state;
    @Column(name = "province", length = 100)
    private String province;
    @Column(name = "zip", length = 25)
    private String zip;
    @Column(name = "country", length = 25)
    private String country;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "created_by_user_id")
    private BigInteger createdByUserId;
    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", updatable=false, insertable=false)
    private User user;


    public Address() {
    }

    public Address(Long addressId) {
        this.addressId = addressId;
    }

    // TODO: this needs to be a constructor for the base address
    public Address(Long addressId, Date dateCreated, Date lastLogin) {
        this.addressId = addressId;
        this.dateCreated = dateCreated;
    }

    /**
     * Constructor for user enrollment form on update.
     *
     * @param addressId
     * @param userId
     * @param primaryAddress
     * @param dateCreated
     */
    public Address(Long addressId, Long userId, Boolean primaryAddress, Date dateCreated) {
        this.addressId = addressId;
        this.userId = userId;
        this.primaryAddress = primaryAddress;
        this.dateCreated = dateCreated;
    }

    /**
     * Constructor for user enrollment form.
     * User defined post construction.
     *
     * @param primaryAddress Most likely TRUE if used to create a new user instance.
     * @param address1
     * @param address2
     * @param city
     * @param state
     * @param zip
     * @param dateCreated
     */
    public Address(Boolean primaryAddress, String address1, String address2, String address3,
            String city, String state, String zip, Date dateCreated) {
        this.primaryAddress = primaryAddress;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.dateCreated = dateCreated;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Boolean primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BigInteger getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(BigInteger createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (addressId != null ? addressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.addressId == null && other.addressId != null) || (this.addressId != null && !this.addressId.equals(other.addressId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder("Address[ ");
        result.append("addressId: ").append(addressId);
        result.append("address1: ").append(address1);
//        result.append("     firstName: ").append(firstName).append("\n");
//        result.append("     middleName: ").append(middleName).append("\n");
//        result.append("     lastName: ").append(lastName).append("\n");
//        result.append("     ssn: ").append(ssn).append("\n");
//        result.append("     dob: ").append(dob).append("\n");
//        result.append("     dateCreated: ").append(dateCreated).append("\n");
        result.append("]");
        return result.toString();
    }

}
