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

package com.krminc.phr.domain.carenotebook;

import com.krminc.phr.web.HealthSummary;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author cmccall
 */
@Entity
@Table(name = "carenotebook_pharmacies", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "Pharmacy.findAll", query = "SELECT p FROM Pharmacy p"),
    @NamedQuery(name = "Pharmacy.findByPharmacyId", query = "SELECT p FROM Pharmacy p WHERE p.pharmacyId = :pharmacyId"),
    @NamedQuery(name = "Pharmacy.findByPharmacyName", query = "SELECT p FROM Pharmacy p WHERE p.pharmacyName = :pharmacyName"),
    @NamedQuery(name = "Pharmacy.findByPharmacyContact", query = "SELECT p FROM Pharmacy p WHERE p.pharmacyContact = :pharmacyContact"),
    @NamedQuery(name = "Pharmacy.findByPhoneNumber", query = "SELECT p FROM Pharmacy p WHERE p.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Pharmacy.findByFaxNumber", query = "SELECT p FROM Pharmacy p WHERE p.faxNumber = :faxNumber"),
    @NamedQuery(name = "Pharmacy.findByPharmacyEmail", query = "SELECT p FROM Pharmacy p WHERE p.pharmacyEmail = :pharmacyEmail"),
    @NamedQuery(name = "Pharmacy.findByPharmacyMedications", query = "SELECT p FROM Pharmacy p WHERE p.pharmacyMedications = :pharmacyMedications"),
    @NamedQuery(name = "Pharmacy.findByHealthRecordId", query = "SELECT p FROM Pharmacy p WHERE p.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Pharmacy.findByDataSourceId", query = "SELECT p FROM Pharmacy p WHERE p.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "Pharmacy.findByCareDocumentId", query = "SELECT p FROM Pharmacy p WHERE p.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "Pharmacy.findBySourceId", query = "SELECT p FROM Pharmacy p WHERE p.sourceId = :sourceId"),
    @NamedQuery(name = "Pharmacy.findByDateAdded", query = "SELECT p FROM Pharmacy p WHERE p.dateAdded = :dateAdded"),
    @NamedQuery(name = "Pharmacy.findByComments", query = "SELECT p FROM Pharmacy p WHERE p.comments = :comments"),
    @NamedQuery(name = "Pharmacy.findByPrimaryKeyForRecord", query = "SELECT d FROM Pharmacy d WHERE d.pharmacyId = :pharmacyId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Pharmacy.findByMask", query = "SELECT p FROM Pharmacy p WHERE p.mask = :mask")})
public class Pharmacy extends HealthSummary implements Serializable  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pharmacy_id", nullable = false)
    private Long pharmacyId;
    @Column(name = "pharmacy_name", length = 50)
    private String pharmacyName;
    @Column(name = "pharmacy_contact", length = 50)
    private String pharmacyContact;
    @Column(name = "phone_number", length = 25)
    private String phoneNumber;
    @Column(name = "fax_number", length = 25)
    private String faxNumber;
    @Column(name = "pharmacy_email", length = 100)
    private String pharmacyEmail;
    @Column(name = "pharmacy_medications", length = 512)
    private String pharmacyMedications;
    @Basic(optional = false)
    @Column(name = "rec_id", nullable = false)
    private long healthRecordId;
    @Basic(optional = false)
    @Column(name = "data_source_id", nullable = false)
    private long dataSourceId;
    @Column(name = "care_document_id")
    private BigInteger careDocumentId;
    @Column(name = "source_id")
    private BigInteger sourceId;
    @Basic(optional = false)
    @Column(name = "date_added", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    @Column(name = "comments", length = 512)
    private String comments;
    @Column(name = "mask", length = 50)
    private String mask;

    public Pharmacy() {
    }

    public Pharmacy(Long hrid) {
        super(hrid);
        this.healthRecordId = hrid;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

   /** needed to map existing entities by carenotebook form processor **/
    public void setPharmacyId(String pharmacyId){
        this.pharmacyId = Long.parseLong(pharmacyId);
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getPharmacyContact() {
        return pharmacyContact;
    }

    public void setPharmacyContact(String pharmacyContact) {
        this.pharmacyContact = pharmacyContact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getPharmacyEmail() {
        return pharmacyEmail;
    }

    public void setPharmacyEmail(String pharmacyEmail) {
        this.pharmacyEmail = pharmacyEmail;
    }

    public String getPharmacyMedications() {
        return pharmacyMedications;
    }

    public void setPharmacyMedications(String pharmacyMedications) {
        this.pharmacyMedications = pharmacyMedications;
    }

    /**
     *
     * @return
     */
    @Override
    public Long getHealthRecordId() {
        return healthRecordId;
    }

//    public void setHealthRecordId(Long healthRecordId) {
//        this.healthRecordId = healthRecordId;
//    }

    public long getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public BigInteger getCareDocumentId() {
        return careDocumentId;
    }

    public void setCareDocumentId(BigInteger careDocumentId) {
        this.careDocumentId = careDocumentId;
    }

    public BigInteger getSourceId() {
        return sourceId;
    }

    public void setSourceId(BigInteger sourceId) {
        this.sourceId = sourceId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( pharmacyId != null ? pharmacyId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof Pharmacy )) {
            return false;
        }
        Pharmacy other = (Pharmacy) object;
        if (( this.pharmacyId == null && other.pharmacyId != null ) || ( this.pharmacyId != null && !this.pharmacyId.equals(other.pharmacyId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.Pharmacy[pharmacyId=" + pharmacyId + "]";
    }

}
