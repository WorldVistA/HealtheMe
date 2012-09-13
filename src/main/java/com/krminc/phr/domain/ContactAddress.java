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

//    * ID
//    * Address Description (multiple)
//    * Street Address Line 1
//    * Street Address Line 2
//    TODO: Consider adding a Address Line 3 to match EMR output.
//    * City
//    * State
//    * Zip

@Entity
@Table(name = "phr.data_emergencycontacts_address")
public class ContactAddress implements Serializable {
    private static final long serialVersionUID = 20091110L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="contactaddress_id")
    private Long id;

    @Column(name = "address1", length = 200)
    private String address1;
    @Column(name = "address2", length = 200)
    private String address2;
    @Column(name = "city", length = 100)
    private String city;
    @Column(name = "state", length = 100)
    private String state;
    @Column(name = "province", length = 100)
    private String province;
    @Column(name = "zip", length = 25)
    private String zip;

    @Column(name="emergencycontact_id")
    private Long emergencyContactId;

    @ManyToOne
    @JoinColumn(name = "emergencycontact_id", referencedColumnName = "emergencycontact_id", updatable=false, insertable=false)
    private EmergencyContact contact;

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
        if (!(object instanceof ContactAddress)) {
            return false;
        }
        ContactAddress other = (ContactAddress) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.ContactAddress[id=" + id + "]";
    }

}
