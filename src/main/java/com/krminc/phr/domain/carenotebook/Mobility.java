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
@Table(name = "carenotebook_mobility", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mobility.findAll", query = "SELECT m FROM Mobility m"),
    @NamedQuery(name = "Mobility.findByMobilityId", query = "SELECT m FROM Mobility m WHERE m.mobilityId = :mobilityId"),
    @NamedQuery(name = "Mobility.findByObservedDate", query = "SELECT m FROM Mobility m WHERE m.observedDate = :observedDate"),
    @NamedQuery(name = "Mobility.findByMobilityText", query = "SELECT m FROM Mobility m WHERE m.mobilityText = :mobilityText"),
    @NamedQuery(name = "Mobility.findByHealthRecordId", query = "SELECT m FROM Mobility m WHERE m.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Mobility.findByDataSourceId", query = "SELECT m FROM Mobility m WHERE m.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "Mobility.findByCareDocumentId", query = "SELECT m FROM Mobility m WHERE m.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "Mobility.findBySourceId", query = "SELECT m FROM Mobility m WHERE m.sourceId = :sourceId"),
    @NamedQuery(name = "Mobility.findByDateAdded", query = "SELECT m FROM Mobility m WHERE m.dateAdded = :dateAdded"),
    @NamedQuery(name = "Mobility.findByComments", query = "SELECT m FROM Mobility m WHERE m.comments = :comments"),
    @NamedQuery(name = "Mobility.findByPrimaryKeyForRecord", query = "SELECT d FROM Mobility d WHERE d.mobilityId = :mobilityId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Mobility.findByMask", query = "SELECT m FROM Mobility m WHERE m.mask = :mask")})
public class Mobility extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mobility_id", nullable = false)
    private Long mobilityId;
    @Column(name = "observed_date")
    @Temporal(TemporalType.DATE)
    private Date observedDate;
    @Column(name = "mobility_text", length = 3000)
    private String mobilityText;
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

    public Mobility() {
    }

    public Mobility(Long healthRecordId) {
        super(healthRecordId);
        this.healthRecordId = healthRecordId;
    }

    public Long getMobilityId() {
        return mobilityId;
    }

    /** needed to map existing entities by carenotebook form processor **/
    public void setMobilityId(String mobilityId){
        this.mobilityId = Long.parseLong(mobilityId);
    }
    
//    public void setMobilityId(Long mobilityId) {
//        this.mobilityId = mobilityId;
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

    public String getMobilityText() {
        return mobilityText;
    }

    public void setMobilityText(String mobilityText) {
        this.mobilityText = mobilityText;
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
        hash += ( mobilityId != null ? mobilityId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof Mobility )) {
            return false;
        }
        Mobility other = (Mobility) object;
        if (( this.mobilityId == null && other.mobilityId != null ) || ( this.mobilityId != null && !this.mobilityId.equals(other.mobilityId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.Mobility[ mobilityId=" + mobilityId + " ]";
    }
    
}
