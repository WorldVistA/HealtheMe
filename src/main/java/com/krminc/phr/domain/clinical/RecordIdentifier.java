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
package com.krminc.phr.domain.clinical;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Record Identifier entity class.
 *
 * @author chaz
 * @author Daniel Shaw (dshaw.com)
 */
@Entity
@Table(name = "rec_record_identifiers")
@NamedQueries({
    @NamedQuery(name = "RecordIdentifier.findAll", query = "SELECT i FROM RecordIdentifier i"),
    @NamedQuery(name = "RecordIdentifier.findByHealthRecordId", query = "SELECT i FROM RecordIdentifier i WHERE i.healthRecordId = :healthRecordId ORDER BY i.resourceId"),
    @NamedQuery(name = "RecordIdentifier.findByHealthRecordIdAndResourceId", query = "SELECT i FROM RecordIdentifier i WHERE i.healthRecordId = :healthRecordId AND i.resourceId = :resourceId ORDER BY i.resourceId"),
    /* --- DELETE Queries --- */
    @NamedQuery(name = "RecordIdentifier.deleteByHealthRecordIdAndResourceId", query = "DELETE FROM RecordIdentifier i WHERE i.healthRecordId = :healthRecordId AND i.resourceId = :resourceId")
})
public class RecordIdentifier implements Serializable {

    private static final long serialVersionUID = 20100122L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "identity_id", nullable = false)
    private Long id;
    @Column(name = "rec_id", nullable = false)
    private Long healthRecordId;
    @Column(name = "identifier_name", nullable = false)
    private String identifierName;
    @Column(name = "identifier_value", nullable = false)
    private String identifierValue;
    @Column(name = "notes")
    private String notes;
    @Column(name = "resource_id", nullable = false)
    private Long resourceId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rec_id", referencedColumnName = "rec_id", updatable = false, insertable = false)
    private HealthRecordParser healthRecord;

    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "resource_id", updatable = false, insertable = false)
    private Resource resource;


    public RecordIdentifier() {
    }

    public RecordIdentifier(Long id) {
        this.id = id;
    }

    public RecordIdentifier(Long id, Long healthRecordId, Long resourceId) {
        this.id = id;
        this.healthRecordId = healthRecordId;
        this.resourceId = resourceId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHealthRecordId() {
        return healthRecordId;
    }

    public void setHealthRecordId(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    public String getIdentifierName() {
        return identifierName;
    }

    public void setIdentifierName(String identifierName) {
        this.identifierName = identifierName;
    }

    public String getIdentifierValue() {
        return identifierValue;
    }

    public void setIdentifierValue(String identifierValue) {
        this.identifierValue = identifierValue;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public HealthRecordParser getHealthRecord() {
        return healthRecord;
    }

    public void setHealthRecord(HealthRecordParser healthRecord) {
        this.healthRecord = healthRecord;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecordIdentifier)) {
            return false;
        }
        RecordIdentifier other = (RecordIdentifier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.clinical.RecordIdentifier[id=" + id + "]";
    }
}
