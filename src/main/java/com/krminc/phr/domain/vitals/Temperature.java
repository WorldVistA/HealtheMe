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
@Table(name = "data_vitals_temperature", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "Temperature.findByPrimaryKeyForRecord", query = "SELECT d FROM Temperature d WHERE d.temperatureId = :temperatureId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Temperature.findAll", query = "SELECT d FROM Temperature d"),
    @NamedQuery(name = "Temperature.findByTemperatureId", query = "SELECT d FROM Temperature d WHERE d.temperatureId = :temperatureId"),
    @NamedQuery(name = "Temperature.findByTemperature", query = "SELECT d FROM Temperature d WHERE d.temperature = :temperature"),
    @NamedQuery(name = "Temperature.findByMethod", query = "SELECT d FROM Temperature d WHERE d.method = :method"),
    @NamedQuery(name = "Temperature.findByUnit", query = "SELECT d FROM Temperature d WHERE d.unit = :unit"),
    @NamedQuery(name = "Temperature.findByObservedDate", query = "SELECT d FROM Temperature d WHERE d.observedDate = :observedDate"),
    @NamedQuery(name = "Temperature.findByHealthRecordId", query = "SELECT d FROM Temperature d WHERE d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Temperature.findBySourceId", query = "SELECT d FROM Temperature d WHERE d.sourceId = :sourceId"),
    @NamedQuery(name = "Temperature.findByCareDocumentId", query = "SELECT d FROM Temperature d WHERE d.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "Temperature.findByMask", query = "SELECT d FROM Temperature d WHERE d.mask = :mask"),
    @NamedQuery(name = "Temperature.countByHealthRecordId", query = "SELECT COUNT(d) FROM Temperature d WHERE d.healthRecordId = :healthRecordId")
})
public class Temperature implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "temperature_id")
    private Long temperatureId;
    @Basic(optional = false)
    @Column(name = "temperature")
    private String temperature;
    @Column(name = "method")
    private String method;
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

    public Temperature() {
    }

    public Temperature(Long temperatureId) {
        this.temperatureId = temperatureId;
    }

    public Temperature(Long temperatureId, String temperature, long healthRecordId, long sourceId) {
        this.temperatureId = temperatureId;
        this.temperature = temperature;
        this.healthRecordId = healthRecordId;
        this.sourceId = sourceId;
    }

    public Long getTemperatureId() {
        return temperatureId;
    }

    public void setTemperatureId(Long temperatureId) {
        this.temperatureId = temperatureId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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
        hash += (temperatureId != null ? temperatureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Temperature)) {
            return false;
        }
        Temperature other = (Temperature) object;
        if ((this.temperatureId == null && other.temperatureId != null) || (this.temperatureId != null && !this.temperatureId.equals(other.temperatureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.vitals.Temperature[temperatureId=" + temperatureId + "]";
    }

}
