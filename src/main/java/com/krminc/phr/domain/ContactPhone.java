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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author cmccall
 */

//    * Phone Number (multiple)
//    * Ph. # Description

@Entity
@Table(name = "phr.data_emergencycontacts_phone")
public class ContactPhone implements Serializable {
    private static final long serialVersionUID = 20091110L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="contactphone_id")
    private Long id;

    @Column(name="emergencycontact_id")
    private Long emergencyContactId;

    @Basic(optional = false)
    @Column(name = "phone_number", nullable = false, length = 50)
    private String phoneNumber;

    @Basic(optional = false)
    @Column(name = "phone_number_description", nullable = false, length = 100)
    private String phoneNumberDescription;

    @ManyToOne
    @JoinColumn(name = "emergencycontact_id", referencedColumnName = "emergencycontact_id", updatable=false, insertable=false)
    private EmergencyContact contact;

    //getters and setters for parent object
    public EmergencyContact getContact() {
        return contact;
    }

    public void setContact(EmergencyContact contact) {
        this.contact = contact;
    }

    public Long getEmergencyContactId() {
        return emergencyContactId;
    }

    public void setEmergencyContactId(Long emergencyContactId) {
        this.emergencyContactId = emergencyContactId;
    }

    
    //getters and setters for local members
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberDescription() {
        return phoneNumberDescription;
    }

    public void setPhoneNumberDescription(String phoneNumberDescription) {
        this.phoneNumberDescription = phoneNumberDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ContactPhone)) {
            return false;
        }
        ContactPhone other = (ContactPhone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.ContactPhone[id=" + id + "]";
    }

}
