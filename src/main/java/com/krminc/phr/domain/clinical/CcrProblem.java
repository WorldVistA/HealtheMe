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
import java.lang.reflect.Field;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.StandardToStringStyle;

/**
 *
 * @author chaz
 */
@Entity
@Table(name = "phr.rec_ccr_problems")
@NamedQueries({
    @NamedQuery(name = "CcrProblem.findById", query = "SELECT p FROM CcrProblem p  WHERE  p.id =  :Id "),
    @NamedQuery(name = "CcrProblem.findByHealthRecordId", query = "SELECT p FROM CcrProblem p JOIN p.ccrDocument c JOIN c.healthRecords h WHERE  h.id =  :healthRecordId "),
    @NamedQuery(name = "CcrProblem.countByHealthRecordId", query = "SELECT COUNT(p) FROM CcrProblem p JOIN p.ccrDocument c JOIN c.healthRecords h WHERE h.id= :healthRecordId")

})
public class CcrProblem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ccr_problem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity= com.krminc.phr.domain.clinical.CcrDocument.class)
    @JoinColumn(name="ccr_id", nullable=false)
    protected CcrDocument ccrDocument;

    @Column(name = "data_object_id")
    protected String dataObjectId;
    @Column(name = "type_text")
    protected String typeText;
    @Column(name = "description_text")
    protected String descriptionText;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time_resolved")
    protected Date dateTimeResolved;
    @Column(name = "status_text")
    protected String status;
    @Column(name = "notes")
    protected String note;
    @Column(name = "description_code_value")
    protected String descriptionCodeValue;
    @Column(name = "description_code_codingsystem")
    protected String descriptionCodeCodingSystem;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_problems_datetime",
    joinColumns = {
        @JoinColumn(name = "ccr_problem_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "datetime_id")})
    protected List<CcrDateTime> dateTime;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="problem")
    protected List<CcrProblemSource> sources;

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
    
    public List<CcrDateTime> getDateTime() {
        return dateTime;
    }

    public void setDateTime(List<CcrDateTime> dateTime) {
        this.dateTime = dateTime;
    }

    public Date getDateTimeResolved() {
        return dateTimeResolved;
    }

    public void setDateTimeResolved(Date dateTimeResolved) {
        this.dateTimeResolved = dateTimeResolved;
    }

    public String getDescriptionCodeCodingSystem() {
        return descriptionCodeCodingSystem;
    }

    public void setDescriptionCodeCodingSystem(String descriptionCodeCodingSystem) {
        this.descriptionCodeCodingSystem = descriptionCodeCodingSystem;
    }

    public String getDescriptionCodeValue() {
        return descriptionCodeValue;
    }

    public void setDescriptionCodeValue(String descriptionCodeValue) {
        this.descriptionCodeValue = descriptionCodeValue;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CcrDocument getCcrDocument() {
        return ccrDocument;
    }

    public List<CcrProblemSource> getSources() {
        return sources;
    }

    public void setSources(List<CcrProblemSource> sources) {
        this.sources = sources;
    }

    public void setCcrDocument(CcrDocument ccrDocument) {
        this.ccrDocument = ccrDocument;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataObjectId() {
        return dataObjectId;
    }

    public void setDataObjectId(String dataObjectId) {
        this.dataObjectId = dataObjectId;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
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
        if (!(object instanceof CcrProblem)) {
            return false;
        }
        CcrProblem other = (CcrProblem) object;
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
         return (new ReflectionToStringBuilder(this, style) {

            protected boolean accept(Field f) {
                return super.accept(f) && !f.getName().equals("ccrDocument");
            }
        }).toString();
    }
}
