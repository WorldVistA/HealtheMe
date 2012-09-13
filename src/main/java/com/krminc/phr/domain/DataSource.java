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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Allergy entity.
 *
 * @author Daniel Shaw (dshaw.com)
 */
@Entity
@Table(name = "system_data_sources", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "DataSource.findAll", query = "SELECT d FROM DataSource d"),
    @NamedQuery(name = "DataSource.findByDataSourceId", query = "SELECT d FROM DataSource d WHERE d.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "DataSource.findByDataSourceName", query = "SELECT d FROM DataSource d WHERE d.dataSourceName = :dataSourceName"),
    @NamedQuery(name = "DataSource.findByDescription", query = "SELECT d FROM DataSource d WHERE d.description = :description"),
    @NamedQuery(name = "DataSource.findByDataType", query = "SELECT d FROM DataSource d WHERE d.dataType = :dataType"),
    @NamedQuery(name = "DataSource.findByIsModifiable", query = "SELECT d FROM DataSource d WHERE d.isModifiable = :isModifiable"),
    @NamedQuery(name = "DataSource.findByDateAdded", query = "SELECT d FROM DataSource d WHERE d.dateAdded = :dateAdded"),
    @NamedQuery(name = "DataSource.findByNotes", query = "SELECT d FROM DataSource d WHERE d.notes = :notes")
})
public class DataSource implements Serializable {
    
    private static final long serialVersionUID = 20090820L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "data_source_id", nullable = false)
    private Long dataSourceId;
    @Basic(optional = false)
    @Column(name = "data_source_name", nullable = false, length = 100)
    private String dataSourceName;
    @Column(name = "description", length = 500)
    private String description;
    @Column(name = "data_type", length = 4)
    private String dataType;
    @Column(name = "modifiable")
    private Boolean isModifiable;
    @Column(name = "date_added")
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Column(name = "notes", length = 4000)
    private String notes;


    public DataSource() {
    }

    public DataSource(Long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public DataSource(Long dataSourceId, String dataSourceName) {
        this.dataSourceId = dataSourceId;
        this.dataSourceName = dataSourceName;
    }


    public Long getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Boolean getIsModifiable() {
        return isModifiable;
    }

    public void setIsModifiable(Boolean isModifiable) {
        this.isModifiable = isModifiable;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dataSourceId != null ? dataSourceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataSource)) {
            return false;
        }
        DataSource other = (DataSource) object;
        if ((this.dataSourceId == null && other.dataSourceId != null) || (this.dataSourceId != null && !this.dataSourceId.equals(other.dataSourceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.DataSource[dataSourceId=" + dataSourceId + "]";
    }

}
