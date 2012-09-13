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
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.builder.StandardToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author chaz
 */
@Entity
@Table(name = "rec_ccr_alerts")

@NamedQueries({
    @NamedQuery(name = "CcrAlert.findById", query = "SELECT x FROM CcrAlert x  WHERE  x.id =  :Id "),
    @NamedQuery(name = "CcrAlert.findByHealthRecordId", query = "SELECT x FROM CcrAlert x JOIN x.ccrDocument c JOIN c.healthRecords h WHERE  h.id =  :healthRecordId "),
    @NamedQuery(name = "CcrAlert.countByHealthRecordId", query = "SELECT COUNT(x) FROM CcrAlert x JOIN x.ccrDocument c JOIN c.healthRecords h WHERE h.id= :healthRecordId")

})
public class CcrAlert implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ccr_alert_id")
    private Long id;

    @Column(name = "data_object_id")
    protected String dataObjectId;
    @Column(name = "product_description")
    protected String productDescription;
    @Column(name = "environmental_agent_description")
    protected String environmentalAgentDescription;
    @Column(name = "product_name")
    protected String productName;
    @Column(name = "description_text")
    protected String descriptionText;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_alerts_datetime",
    joinColumns = {
        @JoinColumn(name = "ccr_alert_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "datetime_id")})
    protected List<CcrDateTime> dateTime;
    @Column(name = "reaction_description")
    protected String reactionDescription;
    @Column(name = "reaction_severity")
    protected String reactionSeverity;
    @Column(name = "notes")
    protected String notes;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="alert")

    protected List<CcrAlertSource> sources;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity= com.krminc.phr.domain.clinical.CcrDocument.class)
    @JoinColumn(name="ccr_id", nullable=false)
    protected CcrDocument ccrDocument;
    
    @Transient
    private Date dateAdded;

    @Transient
    private Date exactDateTime;

    @Transient
    private String source;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CcrAlertSource> getSources() {
        return sources;
    }

    public void setSources(List<CcrAlertSource> sources) {
        this.sources = sources;
    }
    
    public String getDataObjectId() {
        return dataObjectId;
    }

    public void setDataObjectId(String dataObjectId) {
        this.dataObjectId = dataObjectId;
    }

    public List<CcrDateTime> getDateTime() {
        return dateTime;
    }

    public void setDateTime(List<CcrDateTime> dateTime) {
        this.dateTime = dateTime;
    }

    public String getEnvironmentalAgentDescription() {
        return environmentalAgentDescription;
    }

    public void setEnvironmentalAgentDescription(String environmentalAgentDescription) {
        this.environmentalAgentDescription = environmentalAgentDescription;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public String getReactionDescription() {
        return reactionDescription;
    }

    public void setReactionDescription(String reactionDescription) {
        this.reactionDescription = reactionDescription;
    }

    public String getReactionSeverity() {
        return reactionSeverity;
    }

    public void setReactionSeverity(String reactionSeverity) {
        this.reactionSeverity = reactionSeverity;
    }

    public CcrDocument getCcrDocument() {
        return ccrDocument;
    }

    public void setCcrDocument(CcrDocument ccrDocument) {
        this.ccrDocument = ccrDocument;
    }

    //custom getters
    public Date getDateAdded() {
        if (dateAdded == null) {
            try {
                dateAdded = ccrDocument.getAddedDateTime();
            }
            catch (Exception ex) {
                dateAdded = null;
            }
        }

        return dateAdded;
    }

    public Date getExactDateTime() {
        if (exactDateTime == null) {
            try {
                exactDateTime = dateTime.get(0).getExactDateTime();
            }
            catch (Exception ex) {
                exactDateTime = null;
            }
        }
        return exactDateTime;
    }

    public String getSource() {
        if (source == null) {
            try {
                source = sources.get(0).getActor().getInformationSystemName();
            }
            catch (Exception ex) {
                source = null;
            }
        }
        return source;
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
        if (!(object instanceof CcrAlert)) {
            return false;
        }
        CcrAlert other = (CcrAlert) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StandardToStringStyle style = new StandardToStringStyle();
        style.setNullText("---NULL---");
        style.setFieldSeparator(";\n");
        style.setArrayStart("{{{");
        style.setArrayEnd("}}}");
        style.setArraySeparator("|");
        style.setFieldNameValueSeparator(":");
        return ToStringBuilder.reflectionToString(this, style);
    }
}
