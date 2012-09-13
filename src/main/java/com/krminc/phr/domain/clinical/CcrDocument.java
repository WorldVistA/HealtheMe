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
package com.krminc.phr.domain.clinical;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.StandardToStringStyle;

/**
 *
 * @author chaz
 */
@Entity
@Table(name = "phr.rec_ccr_documents")
@NamedQueries({
    @NamedQuery(name = "CcrDocument.findById", query = "SELECT c FROM CcrDocument c  WHERE  c.id =  :Id "),

    @NamedQuery(name = "CcrDocument.findByHealthRecordId", query = "SELECT c FROM CcrDocument c JOIN c.healthRecords h WHERE  h.id =  :healthRecordId "),
    @NamedQuery(name = "CcrDocument.countByHealthRecordId", query = "SELECT COUNT(c) FROM CcrDocument c JOIN c.healthRecords h WHERE h.id= :healthRecordId")

})
public class CcrDocument implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ccr_id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "ccr_document_id")
    protected String ccrDocumentObjectId;
    protected String description;
    @Column(name = "purpose_description_text")
    protected String purposeDescriptionText;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date_time")
    protected Date createdDateTime;
    // lazily fetch this so we don't wast memory on something we're not
    // going to use in the front end
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "LONGBLOB NOT NULL")  // this should force MySQL to make this a LongBlob type
    protected byte[] body;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "added_date_time")
    protected Date addedDateTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated_date_time")
    protected Date lastUpdatedDateTime;
    @Column(name = "notes")
    protected String notes;
    @Column(name = "version")
    protected String version;
    // lists of entities that we grab from the ccr
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_documents_datetime",
    joinColumns = {
        @JoinColumn(name = "ccr_document_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "datetime_id")})
    protected List<CcrDateTime> dateTime;
    //    @JoinTable(name = "rec_ccr_document_problems",
//    joinColumns = {
//        @JoinColumn(name = "ccr_id")},
//    inverseJoinColumns = {
//        @JoinColumn(name = "ccr_problem_id")})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ccrDocument")
    protected List<CcrProblem> problems;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_document_medications",
    joinColumns = {
        @JoinColumn(name = "ccr_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "ccr_medication_id")})
    protected List<CcrMedication> medications;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_document_immunizations",
    joinColumns = {
        @JoinColumn(name = "ccr_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "ccr_immunization_id")})
    protected List<CcrImmunization> immunizations;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_document_alerts",
    joinColumns = {
        @JoinColumn(name = "ccr_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "ccr_alert_id")})
    protected List<CcrAlert> alerts;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_document_vitalsign_results",
    joinColumns = {
        @JoinColumn(name = "ccr_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "ccr_vitalsign_result_id")})
    protected List<CcrVitalSignResult> vitalsigns;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_document_results",
    joinColumns = {
        @JoinColumn(name = "ccr_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "ccr_results_result_id")})
    protected List<CcrResult> results;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_document_actors",
    joinColumns = {
        @JoinColumn(name = "ccr_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "ccr_actor_id")})
    protected List<CcrActor> actors;
    @Column(name = "ccr_document_size")
    protected int documentSize;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_document_health_records",
    joinColumns = {
        @JoinColumn(name = "ccr_document_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "rec_id")})
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "ccrDocument",
//        targetEntity=com.krminc.phr.domain.clinical.HealthRecordParser.class)
    protected List <HealthRecordParser> healthRecords;


    @Column(name="rec_id")
    protected Long healthRecordId;

    public Long getHealthRecordId() {
        return healthRecordId;
    }

    public void setHealthRecordId(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    
    public List<CcrAlert> getAlerts() {
        return alerts;
    }

    public List <HealthRecordParser> getHealthRecords() {
        return healthRecords;
    }

    public void setHealthRecords(List<HealthRecordParser> healthRecords) {
        this.healthRecords = healthRecords;
    }

    public void setAlerts(List<CcrAlert> alerts) {
        this.alerts = alerts;
    }

    public List<CcrImmunization> getImmunizations() {
        return immunizations;
    }

    public void setImmunizations(List<CcrImmunization> immunizations) {
        this.immunizations = immunizations;
    }

    public List<CcrMedication> getMedications() {
        return medications;
    }

    public void setMedications(List<CcrMedication> medications) {
        this.medications = medications;
    }

    public List<CcrProblem> getProblems() {
        return problems;
    }

    public void setProblems(List<CcrProblem> problems) {
        this.problems = problems;
    }

    public List<CcrVitalSignResult> getVitalsigns() {
        return vitalsigns;
    }

    public void setVitalsigns(List<CcrVitalSignResult> vitalsigns) {
        this.vitalsigns = vitalsigns;
    }

    public List<CcrDateTime> getDateTime() {
        return dateTime;
    }

    public void setDateTime(List<CcrDateTime> dateTime) {
        this.dateTime = dateTime;
    }

    public Date getAddedDateTime() {
        return addedDateTime;
    }

    public void setAddedDateTime(Date addedDateTime) {
        this.addedDateTime = addedDateTime;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public String getCcrDocumentObjectId() {
        return ccrDocumentObjectId;
    }

    public void setCcrDocumentObjectId(String ccrDocumentObjectId) {
        this.ccrDocumentObjectId = ccrDocumentObjectId;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }

    public void setLastUpdatedDateTime(Date lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPurposeDescriptionText() {
        return purposeDescriptionText;
    }

    public void setPurposeDescriptionText(String purposeDescriptionText) {
        this.purposeDescriptionText = purposeDescriptionText;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getDocumentSize() {
        return documentSize;
    }

    public void setDocumentSize(int documentSize) {
        this.documentSize = documentSize;
    }

    public Date getExactDateTime() {
        if (dateTime != null && dateTime.size() > 0) {
            if (dateTime.get(0) != null) {
                return dateTime.get(0).getExactDateTime();
            }
        }

        return null;
    }

    public List<CcrResult> getResults() {
        return results;
    }

    public void setResults(List<CcrResult> results) {
        this.results = results;
    }

    public List<CcrActor> getActors() {
        return actors;
    }

    public void setActors(List<CcrActor> actors) {
        this.actors = actors;
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
        if (!(object instanceof CcrDocument)) {
            return false;
        }
        CcrDocument other = (CcrDocument) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StandardToStringStyle style = new StandardToStringStyle();
        style.setNullText("---NULL---");
        style.setFieldSeparator(";\n");
        style.setArrayStart("{{{");
        style.setArrayEnd("}}}");
        style.setArraySeparator("|");
        style.setFieldNameValueSeparator(":");
        return (new ReflectionToStringBuilder(this, style) {

            protected boolean accept(Field f) {
                return super.accept(f) && !f.getName().equals("body");
            }
        }).toString();
    }
}
