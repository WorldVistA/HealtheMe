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
 * Visit entity.
 *
 * @author cmccall
 * @author Daniel Shaw (dshaw.com)
 */
@Entity
@Table(name = "data_visits", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "Visit.findAllForRecord", query = "SELECT v FROM Visit v WHERE v.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Visit.findByPrimaryKeyForRecord", query = "SELECT v FROM Visit v WHERE v.healthRecordId = :healthRecordId AND v.visitId = :visitId"),
    @NamedQuery(name = "Visit.findAll", query = "SELECT v FROM Visit v"),
    @NamedQuery(name = "Visit.findByVisitId", query = "SELECT v FROM Visit v WHERE v.visitId = :visitId"),
    @NamedQuery(name = "Visit.findByTitle", query = "SELECT v FROM Visit v WHERE v.title = :title"),
    @NamedQuery(name = "Visit.findByVisitDate", query = "SELECT v FROM Visit v WHERE v.visitDate = :visitDate"),
    @NamedQuery(name = "Visit.findByVisitTime", query = "SELECT v FROM Visit v WHERE v.visitTime = :visitTime"),
    @NamedQuery(name = "Visit.findByPurpose", query = "SELECT v FROM Visit v WHERE v.purpose = :purpose"),
    @NamedQuery(name = "Visit.findByLocation", query = "SELECT v FROM Visit v WHERE v.location = :location"),
    @NamedQuery(name = "Visit.findByComments", query = "SELECT v FROM Visit v WHERE v.comments = :comments"),
    @NamedQuery(name = "Visit.findByHealthRecordId", query = "SELECT v FROM Visit v WHERE v.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Visit.findBySourceId", query = "SELECT v FROM Visit v WHERE v.sourceId = :sourceId"),
    @NamedQuery(name = "Visit.findByMask", query = "SELECT v FROM Visit v WHERE v.mask = :mask"),
    @NamedQuery(name = "Visit.findByMonthAndYear", query = "SELECT v FROM Visit v WHERE v.visitDate LIKE :visitFullDate"),
    @NamedQuery(name = "Visit.countByHealthRecordId", query = "SELECT COUNT(d) FROM Visit d WHERE d.healthRecordId = :healthRecordId")
})

public class Visit implements Serializable {

    
    private static final long serialVersionUID = 20090803L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "visit_id", nullable = false)
    private Long visitId;
    @Column(name = "title", length = 255, nullable = false)
    private String title;
    @Column(name = "visit_date")
    @Temporal(TemporalType.DATE)
    private Date visitDate;
    @Column(name = "visit_time")
    @Temporal(TemporalType.TIME)
    private Date visitTime;
    @Column(name = "purpose", length = 255)
    private String purpose;
    @Column(name = "location", length = 255)
    private String location;
    @Column(name = "provider", length = 255)
    private String provider;
    @Column(name = "comments", length = 512)
    private String comments;
    @Basic(optional = false)
    @Column(name = "rec_id", nullable = false)
    private long healthRecordId;
    @Column(name = "date_added", insertable = false, updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Basic(optional = false)
    @Column(name = "source_id", nullable = false)
    private Long sourceId;
    @Column(name = "data_source_id", nullable = false)
    private Long dataSourceId;
    @Column(name = "care_document_id", nullable = true)
    private Long careDocumentId;
    @Column(name = "mask", length = 50)
    private String mask;

    @ManyToOne
    @JoinColumn(name = "rec_id", referencedColumnName = "rec_id", updatable=false, insertable=false)
    private HealthRecord healthRecord;

    public Visit() {
    }

    public Visit(Long visitId) {
        this.visitId = visitId;
    }

    public Visit(Long visitId, Long healthRecordId, Long sourceId) {
        this.visitId = visitId;
        this.healthRecordId = healthRecordId;
        this.sourceId = sourceId;
    }


    public Long getVisitId() {
        return visitId;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String contactNumber) {
        this.provider = contactNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public long getHealthRecordId() {
        return healthRecordId;
    }

    public void setHealthRecordId(long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getCareDocumentId() {
        return careDocumentId;
    }

    public void setCareDocumentId(Long careDocumentId) {
        this.careDocumentId = careDocumentId;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public HealthRecord getHealthRecord() {
        return healthRecord;
    }

    public void setHealthRecord(HealthRecord healthRecord) {
        this.healthRecord = healthRecord;
    }

    //custom
//    public String getFullStatus()
//    {
//        String statusName = "";
//        try{
//            switch ((int) Integer.parseInt(this.status)) {
//                case 1: statusName = "Scheduled"; break;
//                case 2: statusName = "Confirmed"; break;
//                case 3: statusName = "Completed"; break;
//                case 4: statusName = "Missed"; break;
//                case 5: statusName = "Canceled"; break;
//                case 6: statusName = "Unknown"; break;
//            }
//        }
//        catch (java.lang.NumberFormatException ex) {
//            statusName = this.status;
//        }
//        return statusName;
//    }

    //custom
//    public String getFullPrint()
//    {
//        return "fullStatus: " + this.getFullStatus() + " visitId: " + this.getVisitId() + " VisitDate: " + this.getVisitDate() + " VisitTime: " + this.getVisitTime();
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (visitId != null ? visitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visit)) {
            return false;
        }
        Visit other = (Visit) object;
        if ((this.visitId == null && other.visitId != null) || (this.visitId != null && !this.visitId.equals(other.visitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.Visit[visitId=" + visitId + "]";
    }

}
