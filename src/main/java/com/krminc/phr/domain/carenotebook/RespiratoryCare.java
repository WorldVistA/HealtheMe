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
@Table(name = "carenotebook_respiratorycare", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespiratoryCare.findAll", query = "SELECT r FROM RespiratoryCare r"),
    @NamedQuery(name = "RespiratoryCare.findByRespiratorycareId", query = "SELECT r FROM RespiratoryCare r WHERE r.respiratorycareId = :respiratorycareId"),
    @NamedQuery(name = "RespiratoryCare.findByObservedDate", query = "SELECT r FROM RespiratoryCare r WHERE r.observedDate = :observedDate"),
    @NamedQuery(name = "RespiratoryCare.findByRespiratorycareText", query = "SELECT r FROM RespiratoryCare r WHERE r.respiratorycareText = :respiratorycareText"),
    @NamedQuery(name = "RespiratoryCare.findByHealthRecordId", query = "SELECT r FROM RespiratoryCare r WHERE r.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "RespiratoryCare.findByDataSourceId", query = "SELECT r FROM RespiratoryCare r WHERE r.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "RespiratoryCare.findByCareDocumentId", query = "SELECT r FROM RespiratoryCare r WHERE r.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "RespiratoryCare.findBySourceId", query = "SELECT r FROM RespiratoryCare r WHERE r.sourceId = :sourceId"),
    @NamedQuery(name = "RespiratoryCare.findByDateAdded", query = "SELECT r FROM RespiratoryCare r WHERE r.dateAdded = :dateAdded"),
    @NamedQuery(name = "RespiratoryCare.findByComments", query = "SELECT r FROM RespiratoryCare r WHERE r.comments = :comments"),
    @NamedQuery(name = "RespiratoryCare.findByPrimaryKeyForRecord", query = "SELECT d FROM RespiratoryCare d WHERE d.respiratorycareId = :respiratorycareId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "RespiratoryCare.findByMask", query = "SELECT r FROM RespiratoryCare r WHERE r.mask = :mask")})
public class RespiratoryCare extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "respiratorycare_id", nullable = false)
    private Long respiratorycareId;
    @Column(name = "observed_date")
    @Temporal(TemporalType.DATE)
    private Date observedDate;
    @Column(name = "respiratorycare_text", length = 3000)
    private String respiratorycareText;
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

    public RespiratoryCare() {
    }

    public RespiratoryCare(Long healthRecordId) {
        super(healthRecordId);
        this.healthRecordId = healthRecordId;
    }

    public Long getRespiratorycareId() {
        return respiratorycareId;
    }

    /** needed to map existing entities by carenotebook form processor **/
    public void setRespiratorycareId(String respiratorycareId){
        this.respiratorycareId = Long.parseLong(respiratorycareId);
    }

//    public void setRespiratorycareId(Long respiratorycareId) {
//        this.respiratorycareId = respiratorycareId;
//    }

    public Date getObservedDate() {
        return observedDate;
    }
    
    public void setObservedDate(String observedDate) throws ParseException {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        this.observedDate = df.parse(observedDate);
    }

    public void setObservedDate(Date observedDate) {
        this.observedDate = observedDate;
    }

    public String getRespiratorycareText() {
        return respiratorycareText;
    }

    public void setRespiratorycareText(String respiratorycareText) {
        this.respiratorycareText = respiratorycareText;
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
        hash += ( respiratorycareId != null ? respiratorycareId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof RespiratoryCare )) {
            return false;
        }
        RespiratoryCare other = (RespiratoryCare) object;
        if (( this.respiratorycareId == null && other.respiratorycareId != null ) || ( this.respiratorycareId != null && !this.respiratorycareId.equals(other.respiratorycareId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.RespiratoryCare[ respiratorycareId=" + respiratorycareId + " ]";
    }
    
}
