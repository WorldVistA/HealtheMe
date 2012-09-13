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

package com.krminc.phr.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author cmccall
 */

//Proposed fields:
//
//    * Contact name* (Ex. Aunt Cindy)  [removed]
//    * First Name
//    * Middle Name
//    * Last Name
//    * Relationship
//    * Phone Number (multiple)
//    * Ph. # Description
//    * Email Address (multiple)        [removed]
//    * Email Description               [removed]
//    * Organization
//    * ID
//    * Address Description (multiple)
//    * Street Address Line 1
//    * Street Address Line 2
//    * City
//    * State
//    * Zip
//    * Note

@Entity
@Table(name="phr.data_emergencycontacts")
public class EmergencyContact implements Serializable {
    private static final long serialVersionUID = 20091110L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="emergencycontact_id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    @Column(name = "middle_name", length = 50)
    private String middleName;
    @Basic(optional = false)
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Basic(optional = false)
    @Column(name = "relationship", nullable = false, length = 100)
    private String relationship;

    @Column(name = "organization", length = 100)
    private String organization;

    @Column(name = "note", length = 255)
    private String note;

    @OneToMany(mappedBy = "contact")
    private List<ContactAddress> addresses;

    @OneToMany(mappedBy = "contact")
    private List<ContactPhone> phones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<ContactPhone> getPhones() {
        return phones;
    }

    public void setPhones(List<ContactPhone> phones) {
        this.phones = phones;
    }

    public List<ContactAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<ContactAddress> addresses) {
        this.addresses = addresses;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    
    //custom methods
    public String getFullName(){
        String fullName = getFirstName().concat(" ");
        if (getMiddleName().length() > 0) {
            fullName.concat(getMiddleName()).concat(" ");
        }
        return (fullName.concat(getLastName()));
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmergencyContact)) {
            return false;
        }
        EmergencyContact other = (EmergencyContact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.EmergencyContact[id=" + id + "]";
    }

}
