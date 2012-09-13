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
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cmccall
 */
@Entity
@Table(name = "carenotebook_transition", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transition.findAll", query = "SELECT t FROM Transition t"),
    @NamedQuery(name = "Transition.findByTransitionId", query = "SELECT t FROM Transition t WHERE t.transitionId = :transitionId"),
    @NamedQuery(name = "Transition.findByTransitionText", query = "SELECT t FROM Transition t WHERE t.transitionText = :transitionText"),
    @NamedQuery(name = "Transition.findByHopesText", query = "SELECT t FROM Transition t WHERE t.hopesText = :hopesText"),
    @NamedQuery(name = "Transition.findByHealthRecordId", query = "SELECT t FROM Transition t WHERE t.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Transition.findByDataSourceId", query = "SELECT t FROM Transition t WHERE t.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "Transition.findByCareDocumentId", query = "SELECT t FROM Transition t WHERE t.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "Transition.findBySourceId", query = "SELECT t FROM Transition t WHERE t.sourceId = :sourceId"),
    @NamedQuery(name = "Transition.findByDateAdded", query = "SELECT t FROM Transition t WHERE t.dateAdded = :dateAdded"),
    @NamedQuery(name = "Transition.findByComments", query = "SELECT t FROM Transition t WHERE t.comments = :comments"),
    @NamedQuery(name = "Transition.findByPrimaryKeyForRecord", query = "SELECT d FROM Transition d WHERE d.transitionId = :transitionId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Transition.findByMask", query = "SELECT t FROM Transition t WHERE t.mask = :mask")})
public class Transition extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "transition_id", nullable = false)
    private Long transitionId;
    @Column(name = "transition_text", length = 3000)
    private String transitionText;
    @Column(name = "hopes_text", length = 3000)
    private String hopesText;
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

    public Transition() {
    }

    public Transition(Long healthRecordId) {
        super(healthRecordId);
        this.healthRecordId = healthRecordId;
    }


    public Long getTransitionId() {
        return transitionId;
    }
    
    /** needed to map existing entities by carenotebook form processor **/
    public void setTransitionId(String transitionId){
        this.transitionId = Long.parseLong(transitionId);
    }

//    public void setTransitionId(Long transitionId) {
//        this.transitionId = transitionId;
//    }

    public String getTransitionText() {
        return transitionText;
    }

    public void setTransitionText(String transitionText) {
        this.transitionText = transitionText;
    }

    public String getHopesText() {
        return hopesText;
    }

    public void setHopesText(String hopesText) {
        this.hopesText = hopesText;
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
        hash += ( transitionId != null ? transitionId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof Transition )) {
            return false;
        }
        Transition other = (Transition) object;
        if (( this.transitionId == null && other.transitionId != null ) || ( this.transitionId != null && !this.transitionId.equals(other.transitionId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.Transition[ transitionId=" + transitionId + " ]";
    }
    
}
