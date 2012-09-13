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
 * Exercise entity.
 *
 * @author cmccall
 */
@Entity
@Table(name = "data_exercises", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "Exercise.findAllForRecord", query = "SELECT v FROM Exercise v WHERE v.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Exercise.findByPrimaryKeyForRecord", query = "SELECT v FROM Exercise v WHERE v.healthRecordId = :healthRecordId AND v.exerciseId = :exerciseId"),
    @NamedQuery(name = "Exercise.findAll", query = "SELECT v FROM Exercise v"),
    @NamedQuery(name = "Exercise.findByExerciseId", query = "SELECT v FROM Exercise v WHERE v.exerciseId = :exerciseId"),
    @NamedQuery(name = "Exercise.findByTitle", query = "SELECT v FROM Exercise v WHERE v.title = :title"),
    @NamedQuery(name = "Exercise.findByExerciseDate", query = "SELECT v FROM Exercise v WHERE v.exerciseDate = :exerciseDate"),
    @NamedQuery(name = "Exercise.findByExerciseTime", query = "SELECT v FROM Exercise v WHERE v.exerciseTime = :exerciseTime"),
    @NamedQuery(name = "Exercise.findByHealthRecordId", query = "SELECT v FROM Exercise v WHERE v.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Exercise.countByHealthRecordId", query = "SELECT COUNT(d) FROM Exercise d WHERE d.healthRecordId = :healthRecordId")
})

public class Exercise implements Serializable {
    private static final long serialVersionUID = 20090803L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "exercise_id", nullable = false)
    private Long exerciseId;
    @Column(name = "title", length = 255, nullable = false)
    private String title;
    @Column(name = "exercise_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date exerciseDate;
    @Column(name = "exercise_time", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date exerciseTime;

    @Column(name = "exercise_duration_hours", nullable = false)
    private Long exerciseDurationHours;
    @Column(name = "exercise_duration_minutes", nullable = false)
    private Long exerciseDurationMinutes;

    @Column(name = "comments", length = 512)
    private String comments;
    @Basic(optional = false)
    @Column(name = "rec_id", nullable = false)
    private long healthRecordId;
    @Column(name = "date_added", insertable = false, updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateAdded;
    @Basic(optional = false)
    @Column(name = "source_id", nullable = false)
    private Long sourceId;
    @Column(name = "data_source_id", nullable = false)
    private Long dataSourceId;
    @Column(name = "mask", length = 50)
    private String mask;

    @ManyToOne
    @JoinColumn(name = "rec_id", referencedColumnName = "rec_id", updatable=false, insertable=false)
    private HealthRecord healthRecord;

    public Exercise() {
    }

    public Exercise(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public Exercise(Long exerciseId, Long healthRecordId, Long sourceId) {
        this.exerciseId = exerciseId;
        this.healthRecordId = healthRecordId;
        this.sourceId = sourceId;
    }


    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public Date getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(Date exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public Long getExerciseDurationHours() {
        return exerciseDurationHours;
    }

    public void setExerciseDurationHours(Long exerciseDurationHours) {
        this.exerciseDurationHours = exerciseDurationHours;
    }

    public Long getExerciseDurationMinutes() {
        return exerciseDurationMinutes;
    }

    public void setExerciseDurationMinutes(Long exerciseDurationMinutes) {
        this.exerciseDurationMinutes = exerciseDurationMinutes;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
        hash += (exerciseId != null ? exerciseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exercise)) {
            return false;
        }
        Exercise other = (Exercise) object;
        if ((this.exerciseId == null && other.exerciseId != null) || (this.exerciseId != null && !this.exerciseId.equals(other.exerciseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.Exercise[exerciseId=" + exerciseId + "]";
    }

}
