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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author chaz
 */
@Entity
@Table(name="phr.rec_resource_stats")
public class ResourceStat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="resource_stat_id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//        @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//        @JoinTable(name = "rec_resource_stat_health_records",
//        joinColumns = {
//            @JoinColumn(name = "resouce_stat_id")},
//        inverseJoinColumns = {
//            @JoinColumn(name = "rec_id")})
    @ManyToOne 
    @JoinColumn(name = "rec_id", referencedColumnName = "rec_id", updatable=false, insertable=false)
    protected HealthRecordParser healthRecord;

    @Column(name="flag_update_resource")
    protected boolean isUpdateResource;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="latest_update_request_source")
    protected Date latestUpdateRequestDate;

    @Column(name="flag_error")
    protected boolean isError;

    // milliseconds
    @Column(name="processing_duration_time")
    protected Long processingDuration;

    @Column(name="retrieval_duration_time")
    protected Long retrievalDurationTime;

    @Column(name="notes")
    protected String notes;

    public boolean isIsError() {
        return isError;
    }

    public void setIsError(boolean isError) {
        this.isError = isError;
    }

    public boolean isIsUpdateResource() {
        return isUpdateResource;
    }

    public void setIsUpdateResource(boolean isUpdateResource) {
        this.isUpdateResource = isUpdateResource;
    }

    public Date getLatestUpdateRequestDate() {
        return latestUpdateRequestDate;
    }

    public void setLatestUpdateRequestDate(Date latestUpdateRequestDate) {
        this.latestUpdateRequestDate = latestUpdateRequestDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getProcessingDuration() {
        return processingDuration;
    }

    public void setProcessingDuration(Long processingDuration) {
        this.processingDuration = processingDuration;
    }

    public Long getRetrievalDurationTime() {
        return retrievalDurationTime;
    }

    public void setRetrievalDurationTime(Long retrievalDurationTime) {
        this.retrievalDurationTime = retrievalDurationTime;
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
        if (!(object instanceof ResourceStat)) {
            return false;
        }
        ResourceStat other = (ResourceStat) object;
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
