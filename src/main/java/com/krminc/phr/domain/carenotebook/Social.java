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
@Table(name = "carenotebook_social", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Social.findAll", query = "SELECT s FROM Social s"),
    @NamedQuery(name = "Social.findBySocialId", query = "SELECT s FROM Social s WHERE s.socialId = :socialId"),
    @NamedQuery(name = "Social.findByObservedDate", query = "SELECT s FROM Social s WHERE s.observedDate = :observedDate"),
    @NamedQuery(name = "Social.findBySocialText", query = "SELECT s FROM Social s WHERE s.socialText = :socialText"),
    @NamedQuery(name = "Social.findByHealthRecordId", query = "SELECT s FROM Social s WHERE s.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Social.findByDataSourceId", query = "SELECT s FROM Social s WHERE s.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "Social.findByCareDocumentId", query = "SELECT s FROM Social s WHERE s.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "Social.findBySourceId", query = "SELECT s FROM Social s WHERE s.sourceId = :sourceId"),
    @NamedQuery(name = "Social.findByDateAdded", query = "SELECT s FROM Social s WHERE s.dateAdded = :dateAdded"),
    @NamedQuery(name = "Social.findByPrimaryKeyForRecord", query = "SELECT d FROM Social d WHERE d.socialId = :socialId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Social.findByComments", query = "SELECT s FROM Social s WHERE s.comments = :comments"),
    @NamedQuery(name = "Social.findByMask", query = "SELECT s FROM Social s WHERE s.mask = :mask")})
public class Social extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "social_id", nullable = false)
    private Long socialId;
    @Column(name = "observed_date")
    @Temporal(TemporalType.DATE)
    private Date observedDate;
    @Column(name = "social_text", length = 3000)
    private String socialText;
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

    public Social() {
    }

    public Social(Long healthRecordId) {
        super(healthRecordId);
        this.healthRecordId = healthRecordId;
    }


    public Long getSocialId() {
        return socialId;
    }
    
    /** needed to map existing entities by carenotebook form processor **/
    public void setSocialId(String socialId){
        this.socialId = Long.parseLong(socialId);
    }

//    public void setSocialId(Long socialId) {
//        this.socialId = socialId;
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

    public String getSocialText() {
        return socialText;
    }

    public void setSocialText(String socialText) {
        this.socialText = socialText;
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
        hash += ( socialId != null ? socialId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof Social )) {
            return false;
        }
        Social other = (Social) object;
        if (( this.socialId == null && other.socialId != null ) || ( this.socialId != null && !this.socialId.equals(other.socialId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.Social[ socialId=" + socialId + " ]";
    }
    
}
