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
@Table(name = "data_vitals_height", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "Height.findByPrimaryKeyForRecord", query = "SELECT d FROM Height d WHERE d.heightId = :heightId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Height.findAll", query = "SELECT d FROM Height d"),
    @NamedQuery(name = "Height.findByHeightId", query = "SELECT d FROM Height d WHERE d.heightId = :heightId"),
    @NamedQuery(name = "Height.findByFeet", query = "SELECT d FROM Height d WHERE d.feet = :feet"),
    @NamedQuery(name = "Height.findByInches", query = "SELECT d FROM Height d WHERE d.inches = :inches"),
    @NamedQuery(name = "Height.findByObservedDate", query = "SELECT d FROM Height d WHERE d.observedDate = :observedDate"),
    @NamedQuery(name = "Height.findByHealthRecordId", query = "SELECT d FROM Height d WHERE d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Height.findBySourceId", query = "SELECT d FROM Height d WHERE d.sourceId = :sourceId"),
    @NamedQuery(name = "Height.findByCareDocumentId", query = "SELECT d FROM Height d WHERE d.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "Height.findByMask", query = "SELECT d FROM Height d WHERE d.mask = :mask"),
    @NamedQuery(name = "Height.countByHealthRecordId", query = "SELECT COUNT(d) FROM Height d WHERE d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Height.getLatestByHealthRecordId", query = "SELECT d FROM Height d WHERE d.healthRecordId = :healthRecordId ORDER BY d.observedDate DESC")
})
public class Height implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "height_id")
    private Long heightId;
    @Column(name = "feet")
    private Integer feet;
    @Column(name = "inches")
    private Integer inches;
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

    public Height() {
    }

    public Height(Long heightId) {
        this.heightId = heightId;
    }

    public Height(Long heightId, long healthRecordId, long sourceId) {
        this.heightId = heightId;
        this.healthRecordId = healthRecordId;
        this.sourceId = sourceId;
    }

    public Long getHeightId() {
        return heightId;
    }

    public void setHeightId(Long heightId) {
        this.heightId = heightId;
    }

    public Integer getFeet() {
        return feet;
    }

    public void setFeet(Integer feet) {
        this.feet = feet;
    }

    public Integer getInches() {
        return inches;
    }

    public void setInches(Integer inches) {
        this.inches = inches;
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
        hash += (heightId != null ? heightId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Height)) {
            return false;
        }
        Height other = (Height) object;
        if ((this.heightId == null && other.heightId != null) || (this.heightId != null && !this.heightId.equals(other.heightId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.vitals.Height[heightId=" + heightId + "]";
    }

}
