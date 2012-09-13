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
@Table(name = "carenotebook_communication", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Communication.findAll", query = "SELECT c FROM Communication c"),
    @NamedQuery(name = "Communication.findByCommunicationId", query = "SELECT c FROM Communication c WHERE c.communicationId = :communicationId"),
    @NamedQuery(name = "Communication.findByObservedDate", query = "SELECT c FROM Communication c WHERE c.observedDate = :observedDate"),
    @NamedQuery(name = "Communication.findByCommunicationText", query = "SELECT c FROM Communication c WHERE c.communicationText = :communicationText"),
    @NamedQuery(name = "Communication.findByHealthRecordId", query = "SELECT c FROM Communication c WHERE c.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Communication.findByDataSourceId", query = "SELECT c FROM Communication c WHERE c.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "Communication.findByCareDocumentId", query = "SELECT c FROM Communication c WHERE c.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "Communication.findBySourceId", query = "SELECT c FROM Communication c WHERE c.sourceId = :sourceId"),
    @NamedQuery(name = "Communication.findByDateAdded", query = "SELECT c FROM Communication c WHERE c.dateAdded = :dateAdded"),
    @NamedQuery(name = "Communication.findByComments", query = "SELECT c FROM Communication c WHERE c.comments = :comments"),
    @NamedQuery(name = "Communication.findByPrimaryKeyForRecord", query = "SELECT d FROM Communication d WHERE d.communicationId = :communicationId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Communication.findByMask", query = "SELECT c FROM Communication c WHERE c.mask = :mask")})
public class Communication extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "communication_id", nullable = false)
    private Long communicationId;
    @Column(name = "observed_date")
    @Temporal(TemporalType.DATE)
    private Date observedDate;
    @Column(name = "communication_text", length = 3000)
    private String communicationText;
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

    public Communication() {
    }

    public Communication(Long healthRecordId) {
        super(healthRecordId);
        this.healthRecordId = healthRecordId;
    }

    public Long getCommunicationId() {
        return communicationId;
    }
    
    /** needed to map existing entities by carenotebook form processor **/
    public void setCommunicationId(String communicationId){
        this.communicationId = Long.parseLong(communicationId);
    }
    
//    public void setCommunicationId(Long communicationId) {
//        this.communicationId = communicationId;
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

    public String getCommunicationText() {
        return communicationText;
    }

    public void setCommunicationText(String communicationText) {
        this.communicationText = communicationText;
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
        hash += ( communicationId != null ? communicationId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof Communication )) {
            return false;
        }
        Communication other = (Communication) object;
        if (( this.communicationId == null && other.communicationId != null ) || ( this.communicationId != null && !this.communicationId.equals(other.communicationId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.Communication[ communicationId=" + communicationId + " ]";
    }
    
}
