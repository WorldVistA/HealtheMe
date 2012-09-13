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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author cmccall
 */
@Entity
@Table(name = "carenotebook_specialtyclinic", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "SpecialtyClinic.findAll", query = "SELECT s FROM SpecialtyClinic s"),
    @NamedQuery(name = "SpecialtyClinic.findBySpecialtyclinicId", query = "SELECT s FROM SpecialtyClinic s WHERE s.specialtyclinicId = :specialtyclinicId"),
    @NamedQuery(name = "SpecialtyClinic.findBySpecialtyclinicName", query = "SELECT s FROM SpecialtyClinic s WHERE s.specialtyclinicName = :specialtyclinicName"),
    @NamedQuery(name = "SpecialtyClinic.findBySpecialtyclinicPhysician", query = "SELECT s FROM SpecialtyClinic s WHERE s.specialtyclinicPhysician = :specialtyclinicPhysician"),
    @NamedQuery(name = "SpecialtyClinic.findBySpecialtyclinicContact", query = "SELECT s FROM SpecialtyClinic s WHERE s.specialtyclinicContact = :specialtyclinicContact"),
    @NamedQuery(name = "SpecialtyClinic.findByPhoneNumber", query = "SELECT s FROM SpecialtyClinic s WHERE s.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "SpecialtyClinic.findByFaxNumber", query = "SELECT s FROM SpecialtyClinic s WHERE s.faxNumber = :faxNumber"),
    @NamedQuery(name = "SpecialtyClinic.findBySpecialtyclinicEmail", query = "SELECT s FROM SpecialtyClinic s WHERE s.specialtyclinicEmail = :specialtyclinicEmail"),
    @NamedQuery(name = "SpecialtyClinic.findByHealthRecordId", query = "SELECT s FROM SpecialtyClinic s WHERE s.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "SpecialtyClinic.findByDataSourceId", query = "SELECT s FROM SpecialtyClinic s WHERE s.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "SpecialtyClinic.findByCareDocumentId", query = "SELECT s FROM SpecialtyClinic s WHERE s.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "SpecialtyClinic.findBySourceId", query = "SELECT s FROM SpecialtyClinic s WHERE s.sourceId = :sourceId"),
    @NamedQuery(name = "SpecialtyClinic.findByDateAdded", query = "SELECT s FROM SpecialtyClinic s WHERE s.dateAdded = :dateAdded"),
    @NamedQuery(name = "SpecialtyClinic.findByComments", query = "SELECT s FROM SpecialtyClinic s WHERE s.comments = :comments"),
    @NamedQuery(name = "SpecialtyClinic.findByPrimaryKeyForRecord", query = "SELECT d FROM SpecialtyClinic d WHERE d.specialtyclinicId = :specialtyclinicId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "SpecialtyClinic.findByMask", query = "SELECT s FROM SpecialtyClinic s WHERE s.mask = :mask")})
public class SpecialtyClinic extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "specialtyclinic_id", nullable = false)
    private Long specialtyclinicId;
    @Column(name = "specialtyclinic_name", length = 50)
    private String specialtyclinicName;
    @Column(name = "specialtyclinic_physician", length = 50)
    private String specialtyclinicPhysician;
    @Column(name = "specialtyclinic_contact", length = 50)
    private String specialtyclinicContact;
    @Column(name = "phone_number", length = 25)
    private String phoneNumber;
    @Column(name = "fax_number", length = 25)
    private String faxNumber;
    @Column(name = "specialtyclinic_email", length = 100)
    private String specialtyclinicEmail;
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

    public SpecialtyClinic() {
    }

    public SpecialtyClinic(Long hrid) {
        super(hrid);
        this.healthRecordId = hrid;
    }

    public Long getSpecialtyclinicId() {
        return specialtyclinicId;
    }

    public void setSpecialtyclinicId(Long specialtyclinicId) {
        this.specialtyclinicId = specialtyclinicId;
    }

    /** needed to map existing entities by carenotebook form processor **/
    public void setSpecialtyclinicId(String specialtyclinicId){
        this.specialtyclinicId = Long.parseLong(specialtyclinicId);
    }

    @Override
    public Long getHealthRecordId() {
        return healthRecordId;
    }

//    public void setHealthRecordId(Long healthRecordId) {
//        this.healthRecordId = healthRecordId;
//    }

    public String getSpecialtyclinicName() {
        return specialtyclinicName;
    }

    public void setSpecialtyclinicName(String specialtyclinicName) {
        this.specialtyclinicName = specialtyclinicName;
    }

    public String getSpecialtyclinicPhysician() {
        return specialtyclinicPhysician;
    }

    public void setSpecialtyclinicPhysician(String specialtyclinicPhysician) {
        this.specialtyclinicPhysician = specialtyclinicPhysician;
    }

    public String getSpecialtyclinicContact() {
        return specialtyclinicContact;
    }

    public void setSpecialtyclinicContact(String specialtyclinicContact) {
        this.specialtyclinicContact = specialtyclinicContact;
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

    public String getSpecialtyclinicEmail() {
        return specialtyclinicEmail;
    }

    public void setSpecialtyclinicEmail(String specialtyclinicEmail) {
        this.specialtyclinicEmail = specialtyclinicEmail;
    }

//    public long getRecId() {
//        return healthRecordId;
//    }
//
//    public void setRecId(long recId) {
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
        hash += ( specialtyclinicId != null ? specialtyclinicId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof SpecialtyClinic )) {
            return false;
        }
        SpecialtyClinic other = (SpecialtyClinic) object;
        if (( this.specialtyclinicId == null && other.specialtyclinicId != null ) || ( this.specialtyclinicId != null && !this.specialtyclinicId.equals(other.specialtyclinicId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.SpecialtyClinic[specialtyclinicId=" + specialtyclinicId + "]";
    }

}
