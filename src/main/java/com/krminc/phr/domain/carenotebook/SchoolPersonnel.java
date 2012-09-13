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
@Table(name = "carenotebook_school_personnel", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SchoolPersonnel.findAll", query = "SELECT s FROM SchoolPersonnel s"),
    @NamedQuery(name = "SchoolPersonnel.findByPersonnelId", query = "SELECT s FROM SchoolPersonnel s WHERE s.personnelId = :personnelId"),
    @NamedQuery(name = "SchoolPersonnel.findByPersonnelType", query = "SELECT s FROM SchoolPersonnel s WHERE s.personnelType = :personnelType"),
    @NamedQuery(name = "SchoolPersonnel.findByPersonnelName", query = "SELECT s FROM SchoolPersonnel s WHERE s.personnelName = :personnelName"),
    @NamedQuery(name = "SchoolPersonnel.findByPersonnelAddress", query = "SELECT s FROM SchoolPersonnel s WHERE s.personnelAddress = :personnelAddress"),
    @NamedQuery(name = "SchoolPersonnel.findByPersonnelAddress2", query = "SELECT s FROM SchoolPersonnel s WHERE s.personnelAddress2 = :personnelAddress2"),
    @NamedQuery(name = "SchoolPersonnel.findByPersonnelCity", query = "SELECT s FROM SchoolPersonnel s WHERE s.personnelCity = :personnelCity"),
    @NamedQuery(name = "SchoolPersonnel.findByPersonnelState", query = "SELECT s FROM SchoolPersonnel s WHERE s.personnelState = :personnelState"),
    @NamedQuery(name = "SchoolPersonnel.findByDaytimePhoneNumber", query = "SELECT s FROM SchoolPersonnel s WHERE s.daytimePhoneNumber = :daytimePhoneNumber"),
    @NamedQuery(name = "SchoolPersonnel.findByEveningPhoneNumber", query = "SELECT s FROM SchoolPersonnel s WHERE s.eveningPhoneNumber = :eveningPhoneNumber"),
    @NamedQuery(name = "SchoolPersonnel.findByFaxNumber", query = "SELECT s FROM SchoolPersonnel s WHERE s.faxNumber = :faxNumber"),
    @NamedQuery(name = "SchoolPersonnel.findByPersonnelEmail", query = "SELECT s FROM SchoolPersonnel s WHERE s.personnelEmail = :personnelEmail"),
    @NamedQuery(name = "SchoolPersonnel.findByHealthRecordId", query = "SELECT s FROM SchoolPersonnel s WHERE s.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "SchoolPersonnel.findByDataSourceId", query = "SELECT s FROM SchoolPersonnel s WHERE s.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "SchoolPersonnel.findByCareDocumentId", query = "SELECT s FROM SchoolPersonnel s WHERE s.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "SchoolPersonnel.findBySourceId", query = "SELECT s FROM SchoolPersonnel s WHERE s.sourceId = :sourceId"),
    @NamedQuery(name = "SchoolPersonnel.findByDateAdded", query = "SELECT s FROM SchoolPersonnel s WHERE s.dateAdded = :dateAdded"),
    @NamedQuery(name = "SchoolPersonnel.findByComments", query = "SELECT s FROM SchoolPersonnel s WHERE s.comments = :comments"),
    @NamedQuery(name = "SchoolPersonnel.findByPrimaryKeyForRecord", query = "SELECT d FROM SchoolPersonnel d WHERE d.personnelId = :personnelId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "SchoolPersonnel.findByMask", query = "SELECT s FROM SchoolPersonnel s WHERE s.mask = :mask")})
public class SchoolPersonnel extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "personnel_id", nullable = false)
    private Long personnelId;
    @Column(name = "personnel_type", length = 50)
    private String personnelType;
    @Column(name = "personnel_name", length = 50)
    private String personnelName;
    @Column(name = "personnel_address", length = 200)
    private String personnelAddress;
    @Column(name = "personnel_address2", length = 200)
    private String personnelAddress2;
    @Column(name = "personnel_city", length = 100)
    private String personnelCity;
    @Column(name = "personnel_state", length = 100)
    private String personnelState;
    @Column(name = "daytime_phone_number", length = 25)
    private String daytimePhoneNumber;
    @Column(name = "evening_phone_number", length = 25)
    private String eveningPhoneNumber;
    @Column(name = "fax_number", length = 25)
    private String faxNumber;
    @Column(name = "personnel_email", length = 100)
    private String personnelEmail;
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

    public SchoolPersonnel() {
    }

    public SchoolPersonnel(Long healthRecordId) {
        super(healthRecordId);
        this.healthRecordId = healthRecordId;
    }

    public Long getPersonnelId() {
        return personnelId;
    }
    
    /** needed to map existing entities by carenotebook form processor **/
    public void setPersonnelId(String schoolId){
        this.personnelId = Long.parseLong(schoolId);
    }

//    public void setPersonnelId(Long personnelId) {
//        this.personnelId = personnelId;
//    }

    public String getPersonnelType() {
        return personnelType;
    }

    public void setPersonnelType(String personnelType) {
        this.personnelType = personnelType;
    }

    public String getPersonnelName() {
        return personnelName;
    }

    public void setPersonnelName(String personnelName) {
        this.personnelName = personnelName;
    }

    public String getPersonnelAddress() {
        return personnelAddress;
    }

    public void setPersonnelAddress(String personnelAddress) {
        this.personnelAddress = personnelAddress;
    }

    public String getPersonnelAddress2() {
        return personnelAddress2;
    }

    public void setPersonnelAddress2(String personnelAddress2) {
        this.personnelAddress2 = personnelAddress2;
    }

    public String getPersonnelCity() {
        return personnelCity;
    }

    public void setPersonnelCity(String personnelCity) {
        this.personnelCity = personnelCity;
    }

    public String getPersonnelState() {
        return personnelState;
    }

    public void setPersonnelState(String personnelState) {
        this.personnelState = personnelState;
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

    public String getPersonnelEmail() {
        return personnelEmail;
    }

    public void setPersonnelEmail(String personnelEmail) {
        this.personnelEmail = personnelEmail;
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
        hash += ( personnelId != null ? personnelId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof SchoolPersonnel )) {
            return false;
        }
        SchoolPersonnel other = (SchoolPersonnel) object;
        if (( this.personnelId == null && other.personnelId != null ) || ( this.personnelId != null && !this.personnelId.equals(other.personnelId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.SchoolPersonnel[ personnelId=" + personnelId + " ]";
    }
    
}
