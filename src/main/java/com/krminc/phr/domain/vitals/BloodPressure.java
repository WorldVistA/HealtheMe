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

package com.krminc.phr.domain.vitals;

import com.krminc.phr.domain.DataSource;
import com.krminc.phr.domain.HealthRecord;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author cmccall
 */
@Entity
@Table(name = "data_vitals_bloodpressure", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "BloodPressure.findByPrimaryKeyForRecord", query = "SELECT d FROM BloodPressure d WHERE d.bloodPressureId = :bloodPressureId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "BloodPressure.findAll", query = "SELECT d FROM BloodPressure d"),
    @NamedQuery(name = "BloodPressure.findByBloodPressureId", query = "SELECT d FROM BloodPressure d WHERE d.bloodPressureId = :bloodPressureId"),
    @NamedQuery(name = "BloodPressure.findBySystolic", query = "SELECT d FROM BloodPressure d WHERE d.systolic = :systolic"),
    @NamedQuery(name = "BloodPressure.findByDiastolic", query = "SELECT d FROM BloodPressure d WHERE d.diastolic = :diastolic"),
    @NamedQuery(name = "BloodPressure.findByObservedDate", query = "SELECT d FROM BloodPressure d WHERE d.observedDate = :observedDate"),
    @NamedQuery(name = "BloodPressure.findByHealthRecordId", query = "SELECT d FROM BloodPressure d WHERE d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "BloodPressure.findByDataSourceId", query = "SELECT d FROM BloodPressure d WHERE d.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "BloodPressure.findByCareDocumentId", query = "SELECT d FROM BloodPressure d WHERE d.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "BloodPressure.countByHealthRecordId", query = "SELECT COUNT(d) FROM BloodPressure d WHERE d.healthRecordId = :healthRecordId")
})
public class BloodPressure implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bloodpressure_id")
    private Long bloodPressureId;
    @Basic(optional = false)
    @Column(name = "systolic")
    private Long systolic;
    @Basic(optional = false)
    @Column(name = "diastolic")
    private Long diastolic;
    @Column(name = "observed_date")
    @Temporal(TemporalType.DATE)
    private Date observedDate;
    @Column(name = "date_added", insertable = false, updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Column(name = "data_source_id", nullable = false)
    private Long dataSourceId;
    @Basic(optional = false)
    @Column(name = "rec_id", nullable = false)
    private long healthRecordId;
    @Basic(optional = false)
    @Column(name = "source_id")
    private Long sourceId;
    @Column(name = "care_document_id")
    private BigInteger careDocumentId;
    @Column(name = "mask")
    private String mask;

    @ManyToOne
    @JoinColumn(name = "rec_id", referencedColumnName = "rec_id", updatable=false, insertable=false)
    private HealthRecord healthRecord;

    @ManyToOne
    @JoinColumn(name = "data_source_id", referencedColumnName = "data_source_id", updatable=false, insertable=false)
    private DataSource dataSource;

    public BloodPressure() {
    }

    public BloodPressure(Long bloodPressureId) {
        this.bloodPressureId = bloodPressureId;
    }

    public BloodPressure(Long bloodPressureId, Long systolic, Long diastolic, long healthRecordId, long sourceId) {
        this.bloodPressureId = bloodPressureId;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.healthRecordId = healthRecordId;
        this.sourceId = sourceId;
    }

    public Long getBloodPressureId() {
        return bloodPressureId;
    }

    public void setBloodPressureId(Long bloodPressureId) {
        this.bloodPressureId = bloodPressureId;
    }

    public Long getSystolic() {
        return systolic;
    }

    public void setSystolic(Long systolic) {
        this.systolic = systolic;
    }

    public Long getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(Long diastolic) {
        this.diastolic = diastolic;
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

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Long getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public BigInteger getCareDocumentId() {
        return careDocumentId;
    }

    public void setCareDocumentId(BigInteger careDocumentId) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bloodPressureId != null ? bloodPressureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BloodPressure)) {
            return false;
        }
        BloodPressure other = (BloodPressure) object;
        if ((this.bloodPressureId == null && other.bloodPressureId != null) || (this.bloodPressureId != null && !this.bloodPressureId.equals(other.bloodPressureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.vitals.BloodPressure[bloodpressureId=" + bloodPressureId + "]";
    }

}
