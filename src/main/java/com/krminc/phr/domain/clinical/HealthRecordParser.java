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
import java.util.Calendar;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * use this record to avoid pulling the User object
 * @author chaz
 */
@Entity
@Table(name = "phr.rec_health_records")
public class HealthRecordParser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rec_id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    // TODO needs to be a User object, but that will pull in extra objects taht
    // we DON'T want in the CcrParserApp
    @Column(name = "user_id")
    protected Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId() {
    }
    @Basic(optional = false)
    @Column(name = "gender", nullable = false, length = 2)
    private String gender;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Calendar dateOfBirth;
    @Column(name = "marital_status", length = 2)
    private String maritalStatus;
    @Column(name = "blood_type", length = 2)
    private String bloodType;
    @Column(name = "organ_donor")
    private Boolean organDonor;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "notes", length = 255)
    private String notes;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_health_records_record_identifiers",
    joinColumns = {
        @JoinColumn(name = "rec_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "identity_id")})
    protected List<RecordIdentifier> recordIdentifiers;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,targetEntity= com.krminc.phr.domain.clinical.ResourceStat.class)
    @JoinTable(name = "rec_health_records_resource_stats",
    joinColumns = {
        @JoinColumn(name = "rec_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "resource_stat_id")})
    protected List<ResourceStat> resourceStats;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity= com.krminc.phr.domain.clinical.CcrDocument.class)
    @JoinColumn(name="ccr_id", nullable=true, insertable=false,updatable=true)
    protected CcrDocument ccrDocument;

    public CcrDocument getCcrDocument() {
        return ccrDocument;
    }

    public void setCcrDocument(CcrDocument ccrDocument) {
        this.ccrDocument = ccrDocument;
    }

    


    public List<ResourceStat> getResourceStats() {
        return resourceStats;


    }

    public void setResourceStat(List<ResourceStat> resourceStats) {
        this.resourceStats = resourceStats;


    }

    public List<RecordIdentifier> getRecordIdentifier() {
        return recordIdentifiers;


    }

    public void setRecordIdentifiers(List<RecordIdentifier> recordIdentifiers) {
        this.recordIdentifiers = recordIdentifiers;


    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);


        return hash;


    }

    public String getBloodType() {
        return bloodType;


    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;


    }

    public Date getDateCreated() {
        return dateCreated;


    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;


    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;


    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;


    }

    public String getGender() {
        return gender;


    }

    public void setGender(String gender) {
        this.gender = gender;


    }

    public String getMaritalStatus() {
        return maritalStatus;


    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;


    }

    public String getNotes() {
        return notes;


    }

    public void setNotes(String notes) {
        this.notes = notes;


    }

    public Boolean getOrganDonor() {
        return organDonor;


    }

    public void setOrganDonor(Boolean organDonor) {
        this.organDonor = organDonor;


    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HealthRecordParser)) {
            return false;


        }
        HealthRecordParser other = (HealthRecordParser) object;


        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;


        }
        return true;


    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);

    }
}
