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
 * Allergy entity.
 * 
 * @author Daniel Shaw (dshaw.com)
 * @author cmccall
 */
@Entity
@Table(name = "data_allergies", catalog = "phr", schema = "")
@NamedQueries({
    /* --- Query by patient should be the most common type for this system. --- */
    @NamedQuery(name = "Allergy.findByPrimaryKeyForRecord", query = "SELECT d FROM Allergy d WHERE d.allergyId = :allergyId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Allergy.findAllForRecord", query = "SELECT a FROM Allergy a WHERE a.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Allergy.findBySeverityForRecord", query = "SELECT a FROM Allergy a WHERE a.healthRecordId = :healthRecordId AND a.severity = :severity"),
    @NamedQuery(name = "Allergy.countByHealthRecordId", query = "SELECT COUNT(d) FROM Allergy d WHERE d.healthRecordId = :healthRecordId"),
    /* --- The following should almost never be called and should probably be limited to specific role(s). --- */
    @NamedQuery(name = "Allergy.findAll", query = "SELECT a FROM Allergy a"),
    @NamedQuery(name = "Allergy.findByAllergyId", query = "SELECT a FROM Allergy a WHERE a.allergyId = :allergyId"),
    @NamedQuery(name = "Allergy.findByDataSourceId", query = "SELECT a FROM Allergy a WHERE a.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "Allergy.findBySourceId", query = "SELECT a FROM Allergy a WHERE a.sourceId = :sourceId"),
    @NamedQuery(name = "Allergy.findByAllergyText", query = "SELECT a FROM Allergy a WHERE a.allergyText = :allergyText"),
    @NamedQuery(name = "Allergy.findBySeverity", query = "SELECT a FROM Allergy a WHERE a.severity = :severity"),
    @NamedQuery(name = "Allergy.findByMask", query = "SELECT a FROM Allergy a WHERE a.mask = :mask")
})
public class Allergy implements Serializable {
    
    private static final long serialVersionUID = 20091026L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "allergy_id", nullable = false)
    private Long allergyId;
    @Basic(optional = false)
    @Column(name = "rec_id", nullable = false)
    private long healthRecordId;
    @Column(name = "data_source_id", nullable = false)
    private Long dataSourceId;
    @Column(name = "care_document_id", nullable = true)
    private Long careDocumentId;
    @Basic(optional = false)
    @Column(name = "source_id", nullable = false)
    private Long sourceId;
    @Column(name = "allergy_text", length = 75)
    private String allergyText;
    @Column(name = "observed_date")
    @Temporal(TemporalType.DATE)
    private Date observedDate;
    @Column(name = "date_added", insertable = false, updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Column(name = "severity", length = 75)
    private String severity;
    @Column(name = "reaction", length = 255)
    private String reaction;
    @Column(name = "comments", length = 512)
    private String comments;
    @Column(name = "mask", length = 50)
    private String mask;

    @ManyToOne
    @JoinColumn(name = "rec_id", referencedColumnName = "rec_id", updatable=false, insertable=false)
    private HealthRecord healthRecord;

    @ManyToOne
    @JoinColumn(name = "data_source_id", referencedColumnName = "data_source_id", updatable=false, insertable=false)
    private DataSource dataSource;


    public Allergy() {
        // default constructor
    }

    public Allergy(Long allergiesId) {
        this.allergyId = allergiesId;
    }

    public Allergy(Long allergiesId, Long healthRecordId) {
        this.allergyId = allergiesId;
        this.healthRecordId = healthRecordId;
    }

    public Allergy(Long allergiesId, Long healthRecordId, Long dataSourceId) {
        this.allergyId = allergiesId;
        this.healthRecordId = healthRecordId;
        this.dataSourceId = dataSourceId;
    }

    public Allergy(Long allergiesId, Long healthRecordId, Long dataSourceId, Long careDocumentId) {
        this.allergyId = allergiesId;
        this.healthRecordId = healthRecordId;
        this.dataSourceId = dataSourceId;
        this.careDocumentId = careDocumentId;
    }


    public Long getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(Long allergyId) {
        this.allergyId = allergyId;
    }

    public Long getHealthRecordId() {
        return healthRecordId;
    }

    public void setHealthRecordId(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    public Long getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(Long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public Long getCareDocumentId() {
        return careDocumentId;
    }

    public void setCareDocumentId(Long careDocumentId) {
        this.careDocumentId = careDocumentId;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getAllergyText() {
        return allergyText;
    }

    public void setAllergyText(String allergyText) {
        this.allergyText = allergyText;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getObservedDate() {
        return observedDate;
    }

    public void setObservedDate(Date observedDate) {
        this.observedDate = observedDate;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
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


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (allergyId != null ? allergyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Allergy)) {
            return false;
        }
        Allergy other = (Allergy) object;
        if ((this.allergyId == null && other.allergyId != null) || (this.allergyId != null && !this.allergyId.equals(other.allergyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.Allergy[allergyId=" + allergyId + "]";
    }

}
