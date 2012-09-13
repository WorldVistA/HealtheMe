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
@Table(name = "data_vitals_heartrate", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "HeartRate.findByPrimaryKeyForRecord", query = "SELECT d FROM HeartRate d WHERE d.heartRateId = :heartRateId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "HeartRate.findAll", query = "SELECT d FROM HeartRate d"),
    @NamedQuery(name = "HeartRate.findByHeartRateId", query = "SELECT d FROM HeartRate d WHERE d.heartRateId = :heartRateId"),
    @NamedQuery(name = "HeartRate.findByRate", query = "SELECT d FROM HeartRate d WHERE d.rate = :rate"),
    @NamedQuery(name = "HeartRate.findByObservedDate", query = "SELECT d FROM HeartRate d WHERE d.observedDate = :observedDate"),
    @NamedQuery(name = "HeartRate.findByMask", query = "SELECT d FROM HeartRate d WHERE d.mask = :mask"),
    @NamedQuery(name = "HeartRate.countByHealthRecordId", query = "SELECT COUNT(d) FROM HeartRate d WHERE d.healthRecordId = :healthRecordId")
})
public class HeartRate implements Serializable {

    private static final long serialVersionUID = 20091019L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "heart_rate_id")
    private Long heartRateId;
    @Basic(optional = false)
    @Column(name = "rate")
    private Long rate;
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

    public HeartRate() {
    }

    public HeartRate(Long heartrateId) {
        this.heartRateId = heartrateId;
    }

    public HeartRate(Long heartrateId, Long rate, long healthRecordId, long sourceId) {
        this.heartRateId = heartrateId;
        this.rate = rate;
        this.healthRecordId = healthRecordId;
        this.sourceId = sourceId;
    }


    public Long getHeartRateId() {
        return heartRateId;
    }

    public void setHeartRateId(Long heartRateId) {
        this.heartRateId = heartRateId;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public Date getObservedDate() {
        return observedDate;
    }

    public void setObservedDate(Date observedDate) {
        this.observedDate = observedDate;
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
        hash += (heartRateId != null ? heartRateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HeartRate)) {
            return false;
        }
        HeartRate other = (HeartRate) object;
        if ((this.heartRateId == null && other.heartRateId != null) || (this.heartRateId != null && !this.heartRateId.equals(other.heartRateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.vitals.HeartRate[heartRateId=" + heartRateId + "]";
    }

}
