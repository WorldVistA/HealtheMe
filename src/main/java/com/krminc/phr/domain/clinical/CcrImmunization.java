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
@Table(name="phr.rec_ccr_immunizations")

@NamedQueries({
    @NamedQuery(name = "CcrImmunization.findById", query = "SELECT i FROM CcrImmunization i  WHERE  i.id =  :Id "),
    @NamedQuery(name = "CcrImmunization.findByHealthRecordId", query = "SELECT i FROM CcrImmunization i JOIN i.ccrDocument c JOIN c.healthRecords h WHERE  h.id =  :healthRecordId "),
    @NamedQuery(name = "CcrImmunization.countByHealthRecordId", query = "SELECT COUNT(i) FROM CcrImmunization i JOIN i.ccrDocument c JOIN c.healthRecords h WHERE h.id= :healthRecordId")

})
public class CcrImmunization implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ccr_immunization_id")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_immunizations_datetime",
    joinColumns = {
        @JoinColumn(name = "ccr_immunization_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "datetime_id")})
    protected List<CcrDateTime> dateTime;

    @Column(name="data_object_id")
    protected String dataObjectId;
    @Column(name="product_productname_text")
    protected String productName;
    @Column(name="product_productname_code_value")
    protected String productCode;
    @Column(name="product_productname_code_codingsystem")
    protected String productCodingSystem;
    @Column(name="notes")
    protected String notes;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity= com.krminc.phr.domain.clinical.CcrDocument.class)
    @JoinColumn(name="ccr_id", nullable=false)
    protected CcrDocument ccrDocument;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="immunization")
    protected List<CcrImmunizationSource> sources;

    @Transient
    private Date dateAdded;

    @Transient
    private Date exactDateTime;

    @Transient
    private String source;

    @Transient
    private String administerer;

    @Transient
    private String administererTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CcrDocument getCcrDocument() {
        return ccrDocument;
    }

    public void setCcrDocument(CcrDocument ccrDocument) {
        this.ccrDocument = ccrDocument;
    }

    public List<CcrImmunizationSource> getSources() {
        return sources;
    }

    public void setSources(List<CcrImmunizationSource> sources) {
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCodingSystem() {
        return productCodingSystem;
    }

    public void setProductCodingSystem(String productCodingSystem) {
        this.productCodingSystem = productCodingSystem;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public String getAdministerer(){
        if (administerer == null) {
            try {
                administerer = sources.get(0).getActor().getAsFullName();
            }
            catch (Exception ex) {
                administerer = null;
            }
        }
        return administerer;
    }

    public String getAdministererTitle(){
        if (administererTitle == null) {
            try {
                administererTitle = sources.get(0).getActor().getPersonNameCurrentNameTitle();
            }
            catch (Exception ex) {
                administererTitle = null;
            }
        }
        return administererTitle;
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
        if (!(object instanceof CcrImmunization)) {
            return false;
        }
        CcrImmunization other = (CcrImmunization) object;
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
