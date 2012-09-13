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
@Table(name = "data_vitals_weight", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "Weight.findByPrimaryKeyForRecord", query = "SELECT d FROM Weight d WHERE d.weightId = :weightId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Weight.findAll", query = "SELECT d FROM Weight d"),
    @NamedQuery(name = "Weight.findByWeightId", query = "SELECT d FROM Weight d WHERE d.weightId = :weightId"),
    @NamedQuery(name = "Weight.findByWeight", query = "SELECT d FROM Weight d WHERE d.weight = :weight"),
    @NamedQuery(name = "Weight.findByUnit", query = "SELECT d FROM Weight d WHERE d.unit = :unit"),
    @NamedQuery(name = "Weight.findByObservedDate", query = "SELECT d FROM Weight d WHERE d.observedDate = :observedDate"),
    @NamedQuery(name = "Weight.findByHealthRecordId", query = "SELECT d FROM Weight d WHERE d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Weight.findBySourceId", query = "SELECT d FROM Weight d WHERE d.sourceId = :sourceId"),
    @NamedQuery(name = "Weight.findByCareDocumentId", query = "SELECT d FROM Weight d WHERE d.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "Weight.findByMask", query = "SELECT d FROM Weight d WHERE d.mask = :mask"),
    @NamedQuery(name = "Weight.countByHealthRecordId", query = "SELECT COUNT(d) FROM Weight d WHERE d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Weight.getLatestByHealthRecordId", query = "SELECT d FROM Weight d WHERE d.healthRecordId = :healthRecordId ORDER BY d.observedDate DESC")
})
public class Weight implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "weight_id")
    private Long weightId;
    @Basic(optional = false)
    @Column(name = "weight")
    private Long weight;
    @Column(name = "unit")
    private String unit;
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

    public Weight() {
    }

    public Weight(Long weightId) {
        this.weightId = weightId;
    }

    public Weight(Long weightId, Long weight, long healthRecordId, long sourceId) {
        this.weightId = weightId;
        this.weight = weight;
        this.healthRecordId = healthRecordId;
        this.sourceId = sourceId;
    }

    public Long getWeightId() {
        return weightId;
    }

    public void setWeightId(Long weightId) {
        this.weightId = weightId;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
        hash += (weightId != null ? weightId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Weight)) {
            return false;
        }
        Weight other = (Weight) object;
        if ((this.weightId == null && other.weightId != null) || (this.weightId != null && !this.weightId.equals(other.weightId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.vitals.Weight[weightId=" + weightId + "]";
    }

}
