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
 *
 * @author cmccall
 */
@Entity
@Table(name = "data_medicalevents", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "MedicalEvent.findAllForRecord", query = "SELECT p FROM MedicalEvent p WHERE p.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "MedicalEvent.findByPrimaryKeyForRecord", query = "SELECT p FROM MedicalEvent p WHERE p.healthRecordId = :healthRecordId AND p.medicalEventId = :medicalEventId"),
    @NamedQuery(name = "MedicalEvent.findAll", query = "SELECT m FROM MedicalEvent m"),
    @NamedQuery(name = "MedicalEvent.findByMedicalEventId", query = "SELECT m FROM MedicalEvent m WHERE m.medicalEventId = :medicalEventId"),
    @NamedQuery(name = "MedicalEvent.findByEvent", query = "SELECT m FROM MedicalEvent m WHERE m.event = :event"),
    @NamedQuery(name = "MedicalEvent.findByComments", query = "SELECT m FROM MedicalEvent m WHERE m.comments = :comments"),
    @NamedQuery(name = "MedicalEvent.findByObservedDate", query = "SELECT m FROM MedicalEvent m WHERE m.observedDate = :observedDate"),
    @NamedQuery(name = "MedicalEvent.findByDateAdded", query = "SELECT m FROM MedicalEvent m WHERE m.dateAdded = :dateAdded"),
    @NamedQuery(name = "MedicalEvent.findByHealthRecordId", query = "SELECT m FROM MedicalEvent m WHERE m.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "MedicalEvent.findBySourceId", query = "SELECT m FROM MedicalEvent m WHERE m.sourceId = :sourceId"),
    @NamedQuery(name = "MedicalEvent.findByDataSourceId", query = "SELECT m FROM MedicalEvent m WHERE m.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "MedicalEvent.findByCareDocumentId", query = "SELECT m FROM MedicalEvent m WHERE m.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "MedicalEvent.findByMask", query = "SELECT m FROM MedicalEvent m WHERE m.mask = :mask"),
    @NamedQuery(name = "MedicalEvent.countByHealthRecordId", query = "SELECT COUNT(d) FROM MedicalEvent d WHERE d.healthRecordId = :healthRecordId")
})
public class MedicalEvent implements Serializable {
    private static final long serialVersionUID = 20091112L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "medical_event_id", nullable = false)
    private Long medicalEventId;
    @Basic(optional = false)
    @Column(name = "event", nullable = false, length = 512)
    private String event;
    @Column(name = "comments", length = 512)
    private String comments;
    @Column(name = "status", length = 512)
    private String status;
    @Column(name = "how_ended", length = 512)
    private String howEnded;
    @Column(name = "observed_date")
    @Temporal(TemporalType.DATE)
    private Date observedDate;
    @Column(name = "resolved_date")
    @Temporal(TemporalType.DATE)
    private Date resolvedDate;
    @Basic(optional = false)
    @Column(name = "date_added", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    @Basic(optional = false)
    @Column(name = "rec_id", nullable = false)
    private long healthRecordId;
    @Basic(optional = false)
    @Column(name = "source_id", nullable = false)
    private long sourceId;
    @Basic(optional = false)
    @Column(name = "data_source_id", nullable = false)
    private long dataSourceId;
    @Column(name = "care_document_id")
    private BigInteger careDocumentId;
    @Column(name = "mask", length = 50)
    private String mask;

    @ManyToOne
    @JoinColumn(name = "rec_id", referencedColumnName = "rec_id", updatable=false, insertable=false)
    private HealthRecord healthRecord;

    @ManyToOne
    @JoinColumn(name = "data_source_id", referencedColumnName = "data_source_id", updatable=false, insertable=false)
    private DataSource dataSource;

    public MedicalEvent() {
    }

    public MedicalEvent(Long medicalEventId) {
        this.medicalEventId = medicalEventId;
    }

    public MedicalEvent(Long medicalEventId, String event, Date dateAdded, long healthRecordId, long sourceId, long dataSourceId) {
        this.medicalEventId = medicalEventId;
        this.event = event;
        this.dateAdded = dateAdded;
        this.healthRecordId = healthRecordId;
        this.sourceId = sourceId;
        this.dataSourceId = dataSourceId;
    }

    public Long getMedicalEventId() {
        return medicalEventId;
    }

    public void setMedicalEventId(Long medicalEventId) {
        this.medicalEventId = medicalEventId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getComments() {
        return comments;
    }

    public String getHowEnded() {
        return howEnded;
    }

    public void setHowEnded(String howEnded) {
        this.howEnded = howEnded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getObservedDate() {
        return observedDate;
    }

    public void setObservedDate(Date observedDate) {
        this.observedDate = observedDate;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(Date resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    public long getHealthRecordId() {
        return healthRecordId;
    }

    public void setHealthRecordId(long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    public long getSourceId() {
        return sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
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

    public HealthRecord getHealthRecord() {
        return healthRecord;
    }

    public void setHealthRecord(HealthRecord healthRecord) {
        this.healthRecord = healthRecord;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
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
        hash += (medicalEventId != null ? medicalEventId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicalEvent)) {
            return false;
        }
        MedicalEvent other = (MedicalEvent) object;
        if ((this.medicalEventId == null && other.medicalEventId != null) || (this.medicalEventId != null && !this.medicalEventId.equals(other.medicalEventId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.MedicalEvent[medicalEventId=" + medicalEventId + "]";
    }

}
