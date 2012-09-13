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
@Table(name = "carenotebook_stress", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stress.findAll", query = "SELECT s FROM Stress s"),
    @NamedQuery(name = "Stress.findByStressId", query = "SELECT s FROM Stress s WHERE s.stressId = :stressId"),
    @NamedQuery(name = "Stress.findByObservedDate", query = "SELECT s FROM Stress s WHERE s.observedDate = :observedDate"),
    @NamedQuery(name = "Stress.findByStressText", query = "SELECT s FROM Stress s WHERE s.stressText = :stressText"),
    @NamedQuery(name = "Stress.findByHealthRecordId", query = "SELECT s FROM Stress s WHERE s.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Stress.findByDataSourceId", query = "SELECT s FROM Stress s WHERE s.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "Stress.findByCareDocumentId", query = "SELECT s FROM Stress s WHERE s.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "Stress.findBySourceId", query = "SELECT s FROM Stress s WHERE s.sourceId = :sourceId"),
    @NamedQuery(name = "Stress.findByDateAdded", query = "SELECT s FROM Stress s WHERE s.dateAdded = :dateAdded"),
    @NamedQuery(name = "Stress.findByComments", query = "SELECT s FROM Stress s WHERE s.comments = :comments"),
    @NamedQuery(name = "Stress.findByPrimaryKeyForRecord", query = "SELECT d FROM Stress d WHERE d.stressId = :stressId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Stress.findByMask", query = "SELECT s FROM Stress s WHERE s.mask = :mask")})
public class Stress extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stress_id", nullable = false)
    private Long stressId;
    @Column(name = "observed_date")
    @Temporal(TemporalType.DATE)
    private Date observedDate;
    @Column(name = "stress_text", length = 3000)
    private String stressText;
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

    public Stress() {
    }

    public Stress(Long healthRecordId) {
        super(healthRecordId);
        this.healthRecordId = healthRecordId;
    }


    public Long getStressId() {
        return stressId;
    }
    
    /** needed to map existing entities by carenotebook form processor **/
    public void setStressId(String stressId){
        this.stressId = Long.parseLong(stressId);
    }

//    public void setStressId(Long stressId) {
//        this.stressId = stressId;
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

    public String getStressText() {
        return stressText;
    }

    public void setStressText(String stressText) {
        this.stressText = stressText;
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
        hash += ( stressId != null ? stressId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof Stress )) {
            return false;
        }
        Stress other = (Stress) object;
        if (( this.stressId == null && other.stressId != null ) || ( this.stressId != null && !this.stressId.equals(other.stressId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.Stress[ stressId=" + stressId + " ]";
    }
    
}
