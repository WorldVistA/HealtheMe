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
package com.krminc.phr.domain.carenotebook;

import com.krminc.phr.web.HealthSummary;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cmccall
 */
@Entity
@Table(name = "carenotebook_meeting", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meeting.findAll", query = "SELECT m FROM Meeting m"),
    @NamedQuery(name = "Meeting.findByMeetingId", query = "SELECT m FROM Meeting m WHERE m.meetingId = :meetingId"),
    @NamedQuery(name = "Meeting.findByMeetingDate", query = "SELECT m FROM Meeting m WHERE m.meetingDate = :meetingDate"),
    @NamedQuery(name = "Meeting.findByMeetingPurpose", query = "SELECT m FROM Meeting m WHERE m.meetingPurpose = :meetingPurpose"),
    @NamedQuery(name = "Meeting.findByMeetingIssues", query = "SELECT m FROM Meeting m WHERE m.meetingIssues = :meetingIssues"),
    @NamedQuery(name = "Meeting.findByMeetingResponses", query = "SELECT m FROM Meeting m WHERE m.meetingResponses = :meetingResponses"),
    @NamedQuery(name = "Meeting.findByMeetingOutcome", query = "SELECT m FROM Meeting m WHERE m.meetingOutcome = :meetingOutcome"),
    @NamedQuery(name = "Meeting.findByMeetingSteps", query = "SELECT m FROM Meeting m WHERE m.meetingSteps = :meetingSteps"),
    @NamedQuery(name = "Meeting.findByMeetingRemember", query = "SELECT m FROM Meeting m WHERE m.meetingRemember = :meetingRemember"),
    @NamedQuery(name = "Meeting.findByNextMeetingDate", query = "SELECT m FROM Meeting m WHERE m.nextMeetingDate = :nextMeetingDate"),
    @NamedQuery(name = "Meeting.findByHealthRecordId", query = "SELECT m FROM Meeting m WHERE m.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Meeting.findByDataSourceId", query = "SELECT m FROM Meeting m WHERE m.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "Meeting.findByCareDocumentId", query = "SELECT m FROM Meeting m WHERE m.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "Meeting.findBySourceId", query = "SELECT m FROM Meeting m WHERE m.sourceId = :sourceId"),
    @NamedQuery(name = "Meeting.findByDateAdded", query = "SELECT m FROM Meeting m WHERE m.dateAdded = :dateAdded"),
    @NamedQuery(name = "Meeting.findByComments", query = "SELECT m FROM Meeting m WHERE m.comments = :comments"),
    @NamedQuery(name = "Meeting.findByPrimaryKeyForRecord", query = "SELECT d FROM Meeting d WHERE d.meetingId = :meetingId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Meeting.findByMask", query = "SELECT m FROM Meeting m WHERE m.mask = :mask")})
public class Meeting extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "meeting_id", nullable = false)
    private Long meetingId;
    @Column(name = "meeting_date")
    @Temporal(TemporalType.DATE)
    private Date meetingDate;
    @Column(name = "meeting_purpose", length = 200)
    private String meetingPurpose;
    @Column(name = "meeting_issues", length = 3000)
    private String meetingIssues;
    @Column(name = "meeting_responses", length = 3000)
    private String meetingResponses;
    @Column(name = "meeting_outcome", length = 3000)
    private String meetingOutcome;
    @Column(name = "meeting_steps", length = 3000)
    private String meetingSteps;
    @Column(name = "meeting_remember", length = 3000)
    private String meetingRemember;
    @Column(name = "next_meeting_date")
    @Temporal(TemporalType.DATE)
    private Date nextMeetingDate;
    @Basic(optional = false)
    @Column(name = "rec_id", nullable = false)
    private long healthRecordId;
    @Basic(optional = false)
    @Column(name = "data_source_id", nullable = false)
    private long dataSourceId;
    @Column(name = "care_document_id")
    private BigInteger careDocumentId;
    @Column(name = "source_id")
    private BigInteger sourceId;
    @Basic(optional = false)
    @Column(name = "date_added", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    @Column(name = "comments", length = 512)
    private String comments;
    @Column(name = "mask", length = 50)
    private String mask;

    public Meeting() {
    }

    public Meeting(Long healthRecordId) {
        super(healthRecordId);
        this.healthRecordId = healthRecordId;
    }


    public Long getMeetingId() {
        return meetingId;
    }

    /** needed to map existing entities by carenotebook form processor **/
    public void setMeetingId(String meetingId){
        this.meetingId = Long.parseLong(meetingId);
    }
    
//    public void setMeetingId(Long meetingId) {
//        this.meetingId = meetingId;
//    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        this.meetingDate = df.parse(meetingDate);
    }
    
    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingPurpose() {
        return meetingPurpose;
    }

    public void setMeetingPurpose(String meetingPurpose) {
        this.meetingPurpose = meetingPurpose;
    }

    public String getMeetingIssues() {
        return meetingIssues;
    }

    public void setMeetingIssues(String meetingIssues) {
        this.meetingIssues = meetingIssues;
    }

    public String getMeetingResponses() {
        return meetingResponses;
    }

    public void setMeetingResponses(String meetingResponses) {
        this.meetingResponses = meetingResponses;
    }

    public String getMeetingOutcome() {
        return meetingOutcome;
    }

    public void setMeetingOutcome(String meetingOutcome) {
        this.meetingOutcome = meetingOutcome;
    }

    public String getMeetingSteps() {
        return meetingSteps;
    }

    public void setMeetingSteps(String meetingSteps) {
        this.meetingSteps = meetingSteps;
    }

    public String getMeetingRemember() {
        return meetingRemember;
    }

    public void setMeetingRemember(String meetingRemember) {
        this.meetingRemember = meetingRemember;
    }

    public Date getNextMeetingDate() {
        return nextMeetingDate;
    }
    
    public void setNextMeetingDate(String nextMeetingDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        this.nextMeetingDate = df.parse(nextMeetingDate);
    }

    public void setNextMeetingDate(Date nextMeetingDate) {
        this.nextMeetingDate = nextMeetingDate;
    }

    @Override
    public Long getHealthRecordId() {
        return healthRecordId;
    }

    public long getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public BigInteger getCareDocumentId() {
        return careDocumentId;
    }

    public void setCareDocumentId(BigInteger careDocumentId) {
        this.careDocumentId = careDocumentId;
    }

    public BigInteger getSourceId() {
        return sourceId;
    }

    public void setSourceId(BigInteger sourceId) {
        this.sourceId = sourceId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( meetingId != null ? meetingId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof Meeting )) {
            return false;
        }
        Meeting other = (Meeting) object;
        if (( this.meetingId == null && other.meetingId != null ) || ( this.meetingId != null && !this.meetingId.equals(other.meetingId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.Meeting[ meetingId=" + meetingId + " ]";
    }
    
}
