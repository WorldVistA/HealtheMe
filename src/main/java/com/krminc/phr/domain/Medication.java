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
 * Medication entity.
 *
 * @author cmccall
 * @author Daniel Shaw (dshaw.com)
 */
@Entity
@Table(name = "data_medications", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "Medication.findByPrimaryKeyForRecord", query = "SELECT d FROM Medication d WHERE d.medicationId = :medicationId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Medication.findAllForRecord", query = "SELECT m FROM Medication m WHERE m.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Medication.findByDoseForRecord", query = "SELECT m FROM Medication m WHERE m.healthRecordId = :healthRecordId AND m.dose = :dose"),
    @NamedQuery(name = "Medication.findByRxidForRecord", query = "SELECT m FROM Medication m WHERE m.healthRecordId = :healthRecordId AND m.rxid = :rxid"),
    @NamedQuery(name = "Medication.findByStatusForRecord", query = "SELECT m FROM Medication m WHERE m.healthRecordId = :healthRecordId AND m.status = :status"),
    @NamedQuery(name = "Medication.findByMaskForRecord", query = "SELECT m FROM Medication m WHERE m.healthRecordId = :healthRecordId AND m.mask = :mask"),
    /* --- The following should almost never be called and should probably be limited to specific role(s). --- */
    @NamedQuery(name = "Medication.findAll", query = "SELECT m FROM Medication m"),
    @NamedQuery(name = "Medication.findByMedicationId", query = "SELECT m FROM Medication m WHERE m.medicationId = :medicationId"),
    @NamedQuery(name = "Medication.findByDataSourceId", query = "SELECT m FROM Medication m WHERE m.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "Medication.findBySourceId", query = "SELECT m FROM Medication m WHERE m.sourceId = :sourceId"),
    @NamedQuery(name = "Medication.findByMedicationText", query = "SELECT m FROM Medication m WHERE m.medicationText = :medicationText"),
    @NamedQuery(name = "Medication.findByDose", query = "SELECT m FROM Medication m WHERE m.dose = :dose"),
    @NamedQuery(name = "Medication.findByRxid", query = "SELECT m FROM Medication m WHERE m.rxid = :rxid"),
    @NamedQuery(name = "Medication.findByStatus", query = "SELECT m FROM Medication m WHERE m.status = :status"),
    @NamedQuery(name = "Medication.findByMask", query = "SELECT m FROM Medication m WHERE m.mask = :mask"),
    @NamedQuery(name = "Medication.countByHealthRecordId", query = "SELECT COUNT(d) FROM Medication d WHERE d.healthRecordId = :healthRecordId")
})

public class Medication implements Serializable {

    private static final long serialVersionUID = 20091028L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "medication_id", nullable = false)
    private Long medicationId;
    @Basic(optional = false)
    @Column(name = "rec_id", nullable = false)
    private long healthRecordId;
    @Column(name = "care_document_id", nullable = true)
    private Long careDocumentId;
    @Basic(optional = false)
    @Column(name = "source_id", nullable = false)
    private Long sourceId;
    @Column(name = "data_source_id", nullable = false)
    private Long dataSourceId;
    @Basic(optional = false)
    @Column(name = "medication_text", nullable = false, length = 200)
    private String medicationText;
    @Column(name = "dose", length = 75)
    private String dose;
    @Column(name = "frequency", length = 75)
    private String frequency;
    @Column(name = "rx_id", length = 75)
    private String rxid;
    @Column(name = "status")
    private String status;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "date_added", insertable = false, updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Column(name = "reason", length = 255)
    private String reason;
    @Column(name = "category")
    private Integer category;
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

    public Medication() {
        // default constructor
    }

    public Medication(Long medicationId) {
        this.medicationId = medicationId;
    }

    public Medication(Long medicationId, Long healthRecordId) {
        this.medicationId = medicationId;
        this.healthRecordId = healthRecordId;
    }

    public Medication(Long medicationId, Long healthRecordId, Long dataSourceId) {
        this.medicationId = medicationId;
        this.healthRecordId = healthRecordId;
        this.dataSourceId = dataSourceId;
    }

    public Long getCareDocumentId() {
        return careDocumentId;
    }

    public void setCareDocumentId(Long careDocumentId) {
        this.careDocumentId = careDocumentId;
    }

    public long getHealthRecordId() {
        return healthRecordId;
    }

    public void setHealthRecordId(long healthRecordId) {
        this.healthRecordId = healthRecordId;
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

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    public String getMedicationText() {
        return medicationText;
    }

    public void setMedicationText(String medicationText) {
        this.medicationText = medicationText;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getRxid() {
        return rxid;
    }

    public void setRxid(String rxid) {
        this.rxid = rxid;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        if (status.length() > 0)
            this.status = status;
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

    //custom
    public String getFullCategory()
    {
        String categoryName = "";
        if (category != null) {
            switch ((int) this.category) {
                case 1: categoryName = "Prescription"; break;
                case 2: categoryName = "Over-the-counter"; break;
                case 3: categoryName = "Herbal"; break;
                case 4: categoryName = "Supplement"; break;
                case 5: categoryName = "Other"; break;
            }
        }
        return categoryName;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicationId != null ? medicationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Medication)) {
            return false;
        }
        Medication other = (Medication) object;
        if ((this.medicationId == null && other.medicationId != null) || (this.medicationId != null && !this.medicationId.equals(other.medicationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.Medication[medicationId=" + medicationId + "]";
    }

}
