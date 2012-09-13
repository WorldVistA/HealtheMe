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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.apache.commons.lang.builder.StandardToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author chaz
 */
@Entity
@Table(name = "phr.rec_ccr_medications")

@NamedQueries({
    @NamedQuery(name = "CcrMedication.findById", query = "SELECT m FROM CcrMedication m  WHERE  m.id =  :Id "),
    @NamedQuery(name = "CcrMedication.findByHealthRecordId", query = "SELECT m FROM CcrMedication m JOIN m.ccrDocument c JOIN c.healthRecords h WHERE  h.id =  :healthRecordId "),
    @NamedQuery(name = "CcrMedication.countByHealthRecordId", query = "SELECT COUNT(m) FROM CcrMedication m JOIN m.ccrDocument c JOIN c.healthRecords h WHERE h.id= :healthRecordId")

})
public class CcrMedication  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ccr_medication_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity= com.krminc.phr.domain.clinical.CcrDocument.class)
    @JoinColumn(name="ccr_id", nullable=false)
    protected CcrDocument ccrDocument;

    //TODO - data items
    @Column(name = "product_name")
    protected String productName;
    @Column(name = "type")
    protected String type;
    @Column(name = "product_strength_value")
    protected String productStrengthValue;
    @Column(name = "product_strength_units")
    protected String productStrengthUnits;
    @Column(name = "direction_route_text")
    protected String directionRouteText;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time_started")
    protected Date dateTimeStarted;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time_stopped")
    protected Date dateTimeStopped;
    @Column(name = "direction_dose")
    protected String directionDose;
    @Column(name="data_object_id")
    protected String dataObjectId;
    @Column(name="status")
    protected String status;
    @Column(name="form_text")
    protected String form;
    @Column(name="concentration_value")
    protected String concentrationValue;
    @Column(name="concentration_units_unit")
    protected String concentrationUnitsUnit;
    @Column(name="quantity_value")
    protected String quantity;
    @Column(name="quantity_units_unit")
    protected String quantityUnit;
    @Column(name="refills_refill_number")
    protected String refillNumber;
    @Column(name="notes")
    protected String notes;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_medications_datetime",
    joinColumns = {
        @JoinColumn(name = "ccr_medication_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "datetime_id")})
    protected List<CcrDateTime> dateTime;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="medication")
    protected List<CcrMedicationSource> sources;

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

    public List<CcrMedicationSource> getSources() {
        return sources;
    }

    public void setSources(List<CcrMedicationSource> sources) {
        this.sources = sources;
    }
 
    public List<CcrDateTime> getDateTime() {
        return dateTime;
    }

    public void setDateTime(List<CcrDateTime> dateTime) {
        this.dateTime = dateTime;
    }

    public Date getDateTimeStarted() {
        return dateTimeStarted;
    }

    public void setDateTimeStarted(Date dateTimeStarted) {
        this.dateTimeStarted = dateTimeStarted;
    }

    public Date getDateTimeStopped() {
        return dateTimeStopped;
    }

    public void setDateTimeStopped(Date dateTimeStopped) {
        this.dateTimeStopped = dateTimeStopped;
    }

    public String getDirectionDose() {
        return directionDose;
    }

    public void setDirectionDose(String directionDose) {
        this.directionDose = directionDose;
    }

    public String getDirectionRouteText() {
        return directionRouteText;
    }

    public void setDirectionRouteText(String directionRouteText) {
        this.directionRouteText = directionRouteText;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductStrengthUnits() {
        return productStrengthUnits;
    }

    public void setProductStrengthUnits(String productStrengthUnits) {
        this.productStrengthUnits = productStrengthUnits;
    }

    public String getProductStrengthValue() {
        return productStrengthValue;
    }

    public void setProductStrengthValue(String productStrengthValue) {
        this.productStrengthValue = productStrengthValue;
    }

    public CcrDocument getCcrDocument() {
        return ccrDocument;
    }

    public void setCcrDocument(CcrDocument ccrDocument) {
        this.ccrDocument = ccrDocument;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConcentrationUnitsUnit() {
        return concentrationUnitsUnit;
    }

    public void setConcentrationUnitsUnit(String concentrationUnitsUnit) {
        this.concentrationUnitsUnit = concentrationUnitsUnit;
    }

    public String getConcentrationValue() {
        return concentrationValue;
    }

    public void setConcentrationValue(String concentrationValue) {
        this.concentrationValue = concentrationValue;
    }

    public String getDataObjectId() {
        return dataObjectId;
    }

    public void setDataObjectId(String dataObjectId) {
        this.dataObjectId = dataObjectId;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public String getRefillNumber() {
        return refillNumber;
    }

    public void setRefillNumber(String refillNumber) {
        this.refillNumber = refillNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof CcrMedication)) {
            return false;
        }
        CcrMedication other = (CcrMedication) object;
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
