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
@Table(name = "carenotebook_rest", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rest.findAll", query = "SELECT r FROM Rest r"),
    @NamedQuery(name = "Rest.findByRestId", query = "SELECT r FROM Rest r WHERE r.restId = :restId"),
    @NamedQuery(name = "Rest.findByObservedDate", query = "SELECT r FROM Rest r WHERE r.observedDate = :observedDate"),
    @NamedQuery(name = "Rest.findByRestText", query = "SELECT r FROM Rest r WHERE r.restText = :restText"),
    @NamedQuery(name = "Rest.findByHealthRecordId", query = "SELECT r FROM Rest r WHERE r.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Rest.findByDataSourceId", query = "SELECT r FROM Rest r WHERE r.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "Rest.findByCareDocumentId", query = "SELECT r FROM Rest r WHERE r.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "Rest.findBySourceId", query = "SELECT r FROM Rest r WHERE r.sourceId = :sourceId"),
    @NamedQuery(name = "Rest.findByDateAdded", query = "SELECT r FROM Rest r WHERE r.dateAdded = :dateAdded"),
    @NamedQuery(name = "Rest.findByComments", query = "SELECT r FROM Rest r WHERE r.comments = :comments"),
    @NamedQuery(name = "Rest.findByPrimaryKeyForRecord", query = "SELECT d FROM Rest d WHERE d.restId = :restId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Rest.findByMask", query = "SELECT r FROM Rest r WHERE r.mask = :mask")})
public class Rest extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rest_id", nullable = false)
    private Long restId;
    @Column(name = "observed_date")
    @Temporal(TemporalType.DATE)
    private Date observedDate;
    @Column(name = "rest_text", length = 3000)
    private String restText;
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

    public Rest() {
    }

    public Rest(Long healthRecordId) {
        super(healthRecordId);
        this.healthRecordId = healthRecordId;
    }

    public Long getRestId() {
        return restId;
    }
    
    /** needed to map existing entities by carenotebook form processor **/
    public void setRestId(String restId){
        this.restId = Long.parseLong(restId);
    }

//    public void setRestId(Long restId) {
//        this.restId = restId;
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

    public String getRestText() {
        return restText;
    }

    public void setRestText(String restText) {
        this.restText = restText;
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
        hash += ( restId != null ? restId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof Rest )) {
            return false;
        }
        Rest other = (Rest) object;
        if (( this.restId == null && other.restId != null ) || ( this.restId != null && !this.restId.equals(other.restId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.Rest[ restId=" + restId + " ]";
    }
    
}
