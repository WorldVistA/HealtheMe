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
 * Immunization entity.
 *
 * @author cmccall
 * @author Daniel Shaw (dshaw.com)
 */
@Entity
@Table(name = "data_immunizations", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "Immunization.findByPrimaryKeyForRecord", query = "SELECT d FROM Immunization d WHERE d.immunizationId = :immunizationId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Immunization.findAllForRecord", query = "SELECT i FROM Immunization i WHERE i.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Immunization.findByDateReceivedForRecord", query = "SELECT i FROM Immunization i WHERE i.healthRecordId = :healthRecordId AND i.dateReceived = :dateReceived"),
    @NamedQuery(name = "Immunization.findByImmunizationTypeForRecord", query = "SELECT i FROM Immunization i WHERE i.healthRecordId = :healthRecordId AND i.immunizationType = :immunizationType"),
    @NamedQuery(name = "Immunization.findByMethodForRecord", query = "SELECT i FROM Immunization i WHERE i.healthRecordId = :healthRecordId AND i.method = :method"),
    @NamedQuery(name = "Immunization.findByReactionForRecord", query = "SELECT i FROM Immunization i WHERE i.healthRecordId = :healthRecordId AND i.reaction = :reaction"),
    @NamedQuery(name = "Immunization.findByCommentsForRecord", query = "SELECT i FROM Immunization i WHERE i.healthRecordId = :healthRecordId AND i.comments = :comments"),
    @NamedQuery(name = "Immunization.countByHealthRecordId", query = "SELECT COUNT(d) FROM Immunization d WHERE d.healthRecordId = :healthRecordId"),
    /* --- The following should almost never be called and should probably be limited to specific role(s). --- */
    @NamedQuery(name = "Immunization.findAll", query = "SELECT i FROM Immunization i"),
    @NamedQuery(name = "Immunization.findByImmunizationId", query = "SELECT i FROM Immunization i WHERE i.immunizationId = :immunizationId"),
    @NamedQuery(name = "Immunization.findByDataSourceId", query = "SELECT i FROM Immunization i WHERE i.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "Immunization.findBySourceId", query = "SELECT i FROM Immunization i WHERE i.sourceId = :sourceId"),
    @NamedQuery(name = "Immunization.findByDateReceived", query = "SELECT i FROM Immunization i WHERE i.dateReceived = :dateReceived"),
    @NamedQuery(name = "Immunization.findByImmunizationType", query = "SELECT i FROM Immunization i WHERE i.immunizationType = :immunizationType"),
    @NamedQuery(name = "Immunization.findByMethod", query = "SELECT i FROM Immunization i WHERE i.method = :method"),
    @NamedQuery(name = "Immunization.findByReaction", query = "SELECT i FROM Immunization i WHERE i.reaction = :reaction"),
    @NamedQuery(name = "Immunization.findByComments", query = "SELECT i FROM Immunization i WHERE i.comments = :comments"),
    @NamedQuery(name = "Immunization.findByHealthRecordId", query = "SELECT i FROM Immunization i WHERE i.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Immunization.findByMask", query = "SELECT i FROM Immunization i WHERE i.mask = :mask")
})
public class Immunization implements Serializable {

    private static final long serialVersionUID = 20091027L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "immunization_id", nullable = false)
    private Long immunizationId;
    @Basic(optional = false)
    @Column(name = "rec_id", nullable = false)
    private long healthRecordId;
    @Column(name = "care_document_id", nullable = true)
    private Long careDocumentId;
    @Column(name = "data_source_id", nullable = true)
    private Long dataSourceId;
    @Basic(optional = false)
    @Column(name = "source_id", nullable = false)
    private Long sourceId;
    @Column(name = "date_received")
    @Temporal(TemporalType.DATE)
    private Date dateReceived;
    @Column(name = "date_added", insertable = false, updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Basic(optional = false)
    @Column(name = "immunization_type", nullable = false, length = 255)
    private String immunizationType;
    @Column(name = "method")
    private Integer method;
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

    public Immunization() {
        // default constructor
    }

    public Immunization(Long immunizationId) {
        this.immunizationId = immunizationId;
    }

    public Immunization(Long immunizationId, Long healthRecordId) {
        this.immunizationId = immunizationId;
        this.healthRecordId = healthRecordId;
    }

    public Immunization(Long immunizationId, Long healthRecordId, Long dataSourceId) {
        this.immunizationId = immunizationId;
        this.healthRecordId = healthRecordId;
        this.dataSourceId = dataSourceId;
    }


    public Long getImmunizationId() {
        return immunizationId;
    }

    public void setImmunizationId(Long immunizationId) {
        this.immunizationId = immunizationId;
    }

    public long getHealthRecordId() {
        return healthRecordId;
    }

    public void setHealthRecordId(long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    public Long getCareDocumentId() {
        return careDocumentId;
    }

    public void setCareDocumentId(Long careDocumentId) {
        this.careDocumentId = careDocumentId;
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

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getImmunizationType() {
        return immunizationType;
    }

    public void setImmunizationType(String immunizationType) {
        this.immunizationType = immunizationType;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
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

    //custom
    public String getFullMethod()
    {
        String methodName = "";
        if (method != null) {
            switch ((int) this.method) {
                case 1: methodName = "Injection"; break;
                case 2: methodName = "Inhalant"; break;
                case 3: methodName = "By Mouth"; break;
            }
        }
        return methodName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (immunizationId != null ? immunizationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Immunization)) {
            return false;
        }
        Immunization other = (Immunization) object;
        if ((this.immunizationId == null && other.immunizationId != null) || (this.immunizationId != null && !this.immunizationId.equals(other.immunizationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.Immunization[immunizationId=" + immunizationId + "]";
    }

}
