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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cmccall
 */
@Entity
@Table(name = "carenotebook_familymember", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FamilyMember.findAll", query = "SELECT f FROM FamilyMember f"),
    @NamedQuery(name = "FamilyMember.findByFamilymemberId", query = "SELECT f FROM FamilyMember f WHERE f.familymemberId = :familymemberId"),
    @NamedQuery(name = "FamilyMember.findByFamilymemberType", query = "SELECT f FROM FamilyMember f WHERE f.familymemberType = :familymemberType"),
    @NamedQuery(name = "FamilyMember.findByFamilymemberName", query = "SELECT f FROM FamilyMember f WHERE f.familymemberName = :familymemberName"),
    @NamedQuery(name = "FamilyMember.findByFamilymemberAddress", query = "SELECT f FROM FamilyMember f WHERE f.familymemberAddress = :familymemberAddress"),
    @NamedQuery(name = "FamilyMember.findByFamilymemberAddress2", query = "SELECT f FROM FamilyMember f WHERE f.familymemberAddress2 = :familymemberAddress2"),
    @NamedQuery(name = "FamilyMember.findByFamilymemberCity", query = "SELECT f FROM FamilyMember f WHERE f.familymemberCity = :familymemberCity"),
    @NamedQuery(name = "FamilyMember.findByFamilymemberState", query = "SELECT f FROM FamilyMember f WHERE f.familymemberState = :familymemberState"),
    @NamedQuery(name = "FamilyMember.findByDaytimePhoneNumber", query = "SELECT f FROM FamilyMember f WHERE f.daytimePhoneNumber = :daytimePhoneNumber"),
    @NamedQuery(name = "FamilyMember.findByEveningPhoneNumber", query = "SELECT f FROM FamilyMember f WHERE f.eveningPhoneNumber = :eveningPhoneNumber"),
    @NamedQuery(name = "FamilyMember.findByFaxNumber", query = "SELECT f FROM FamilyMember f WHERE f.faxNumber = :faxNumber"),
    @NamedQuery(name = "FamilyMember.findByFamilymemberEmail", query = "SELECT f FROM FamilyMember f WHERE f.familymemberEmail = :familymemberEmail"),
    @NamedQuery(name = "FamilyMember.findByHealthRecordId", query = "SELECT f FROM FamilyMember f WHERE f.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "FamilyMember.findByDataSourceId", query = "SELECT f FROM FamilyMember f WHERE f.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "FamilyMember.findByCareDocumentId", query = "SELECT f FROM FamilyMember f WHERE f.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "FamilyMember.findBySourceId", query = "SELECT f FROM FamilyMember f WHERE f.sourceId = :sourceId"),
    @NamedQuery(name = "FamilyMember.findByDateAdded", query = "SELECT f FROM FamilyMember f WHERE f.dateAdded = :dateAdded"),
    @NamedQuery(name = "FamilyMember.findByComments", query = "SELECT f FROM FamilyMember f WHERE f.comments = :comments"),
    @NamedQuery(name = "FamilyMember.findByMask", query = "SELECT f FROM FamilyMember f WHERE f.mask = :mask"),
    @NamedQuery(name = "FamilyMember.findByPrimaryKeyForRecord", query = "SELECT d FROM FamilyMember d WHERE d.familymemberId = :familymemberId AND d.healthRecordId = :healthRecordId")})
public class FamilyMember extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "familymember_id", nullable = false)
    private Long familymemberId;
    @Column(name = "familymember_type", length = 50)
    private String familymemberType;
    @Column(name = "familymember_name", length = 50)
    private String familymemberName;
    @Column(name = "familymember_address", length = 200)
    private String familymemberAddress;
    @Column(name = "familymember_address2", length = 200)
    private String familymemberAddress2;
    @Column(name = "familymember_city", length = 100)
    private String familymemberCity;
    @Column(name = "familymember_state", length = 100)
    private String familymemberState;
    @Column(name = "daytime_phone_number", length = 25)
    private String daytimePhoneNumber;
    @Column(name = "evening_phone_number", length = 25)
    private String eveningPhoneNumber;
    @Column(name = "fax_number", length = 25)
    private String faxNumber;
    @Column(name = "familymember_email", length = 100)
    private String familymemberEmail;
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

    public FamilyMember() {
    }

    public FamilyMember(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    public Long getFamilymemberId() {
        return familymemberId;
    }
    
   /** needed to map existing entities by carenotebook form processor **/
    public void setFamilymemberId(String familymemberId){
        this.familymemberId = Long.parseLong(familymemberId);
    }
    
    public void setFamilymemberId(Long familymemberId) {
        this.familymemberId = familymemberId;
    }

    public String getFamilymemberType() {
        return familymemberType;
    }

    public void setFamilymemberType(String familymemberType) {
        this.familymemberType = familymemberType;
    }

    public String getFamilymemberName() {
        return familymemberName;
    }

    public void setFamilymemberName(String familymemberName) {
        this.familymemberName = familymemberName;
    }

    public String getFamilymemberAddress() {
        return familymemberAddress;
    }

    public void setFamilymemberAddress(String familymemberAddress) {
        this.familymemberAddress = familymemberAddress;
    }

    public String getFamilymemberAddress2() {
        return familymemberAddress2;
    }

    public void setFamilymemberAddress2(String familymemberAddress2) {
        this.familymemberAddress2 = familymemberAddress2;
    }

    public String getFamilymemberCity() {
        return familymemberCity;
    }

    public void setFamilymemberCity(String familymemberCity) {
        this.familymemberCity = familymemberCity;
    }

    public String getFamilymemberState() {
        return familymemberState;
    }

    public void setFamilymemberState(String familymemberState) {
        this.familymemberState = familymemberState;
    }

    public String getDaytimePhoneNumber() {
        return daytimePhoneNumber;
    }

    public void setDaytimePhoneNumber(String daytimePhoneNumber) {
        this.daytimePhoneNumber = daytimePhoneNumber;
    }

    public String getEveningPhoneNumber() {
        return eveningPhoneNumber;
    }

    public void setEveningPhoneNumber(String eveningPhoneNumber) {
        this.eveningPhoneNumber = eveningPhoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getFamilymemberEmail() {
        return familymemberEmail;
    }

    public void setFamilymemberEmail(String familymemberEmail) {
        this.familymemberEmail = familymemberEmail;
    }

    @Override
    public Long getHealthRecordId() {
        return healthRecordId;
    }

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
        hash += ( familymemberId != null ? familymemberId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamilyMember )) {
            return false;
        }
        FamilyMember other = (FamilyMember) object;
        if (( this.familymemberId == null && other.familymemberId != null ) || ( this.familymemberId != null && !this.familymemberId.equals(other.familymemberId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.FamilyMember[ familymemberId=" + familymemberId + " ]";
    }
    
}
