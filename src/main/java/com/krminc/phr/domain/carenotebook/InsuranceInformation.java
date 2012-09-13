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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author cmccall
 */
@Entity
@Table(name = "carenotebook_insuranceinformation")
@NamedQueries({
    @NamedQuery(name = "InsuranceInformation.findAll", query = "SELECT i FROM InsuranceInformation i"),
    @NamedQuery(name = "InsuranceInformation.findByInsuranceinformationId", query = "SELECT i FROM InsuranceInformation i WHERE i.insuranceinformationId = :insuranceinformationId"),
    @NamedQuery(name = "InsuranceInformation.findByHealthRecordId", query = "SELECT i FROM InsuranceInformation i WHERE i.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "InsuranceInformation.findByDataSourceId", query = "SELECT i FROM InsuranceInformation i WHERE i.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "InsuranceInformation.findByCareDocumentId", query = "SELECT i FROM InsuranceInformation i WHERE i.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "InsuranceInformation.findBySourceId", query = "SELECT i FROM InsuranceInformation i WHERE i.sourceId = :sourceId"),
    @NamedQuery(name = "InsuranceInformation.findByDateAdded", query = "SELECT i FROM InsuranceInformation i WHERE i.dateAdded = :dateAdded"),
    @NamedQuery(name = "InsuranceInformation.findByComments", query = "SELECT i FROM InsuranceInformation i WHERE i.comments = :comments"),
    @NamedQuery(name = "InsuranceInformation.findByPrimaryKeyForRecord", query = "SELECT d FROM InsuranceInformation d WHERE d.insuranceinformationId = :insuranceinformationId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "InsuranceInformation.findByMask", query = "SELECT i FROM InsuranceInformation i WHERE i.mask = :mask")})
public class InsuranceInformation extends HealthSummary implements Serializable  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "insuranceinformation_id")
    private Long insuranceinformationId;

    @Column(name = "insurance_name", length = 50)
    private String insuranceName;
    @Column(name = "insurance_identification", length = 50)
    private String insuranceIdentification;
    @Column(name = "group_number", length = 50)
    private String groupNumber;
    @Column(name = "claims_address", length = 200)
    private String claimsAddress;
    @Column(name = "claims_address2", length = 200)
    private String claimsAddress2;
    @Column(name = "claims_city", length = 100)
    private String claimsCity;
    @Column(name = "claims_state", length = 100)
    private String claimsState;
    @Column(name = "benefits_number", length = 25)
    private String benefitsNumber;
    @Column(name = "preauthorization_number", length = 25)
    private String preauthorizationNumber;
    @Column(name = "preadmission_number", length = 25)
    private String preadmissionNumber;
    @Column(name = "fax_number", length = 25)
    private String faxNumber;
    @Column(name = "insurance_email", length = 100)
    private String insuranceEmail;

    @Basic(optional = false)
    @Column(name = "rec_id")
    private long healthRecordId;
    @Basic(optional = false)
    @Column(name = "data_source_id")
    private long dataSourceId;
    @Column(name = "care_document_id")
    private BigInteger careDocumentId;
    @Column(name = "source_id")
    private BigInteger sourceId;
    @Basic(optional = false)
    @Column(name = "date_added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    @Column(name = "comments")
    private String comments;
    @Column(name = "mask")
    private String mask;

    public InsuranceInformation() {
    }

    public InsuranceInformation(Long hrid) {
        super(hrid);
        this.healthRecordId = hrid;
    }

    public Long getInsuranceinformationId() {
        return insuranceinformationId;
    }

    /**
     *
     * @return
     */
    @Override
    public Long getHealthRecordId() {
        return healthRecordId;
    }

//    public void setHealthRecordId(Long healthRecordId) {
//        this.healthRecordId = healthRecordId;
//    }

    public String getBenefitsNumber() {
        return benefitsNumber;
    }

    public void setBenefitsNumber(String benefitsNumber) {
        this.benefitsNumber = benefitsNumber;
    }

    public String getClaimsAddress() {
        return claimsAddress;
    }

    public void setClaimsAddress(String claimsAddress) {
        this.claimsAddress = claimsAddress;
    }

    public String getClaimsAddress2() {
        return claimsAddress2;
    }

    public void setClaimsAddress2(String claimsAddress2) {
        this.claimsAddress2 = claimsAddress2;
    }

    public String getClaimsCity() {
        return claimsCity;
    }

    public void setClaimsCity(String claimsCity) {
        this.claimsCity = claimsCity;
    }

    public String getClaimsState() {
        return claimsState;
    }

    public void setClaimsState(String claimsState) {
        this.claimsState = claimsState;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getInsuranceEmail() {
        return insuranceEmail;
    }

    public void setInsuranceEmail(String insuranceEmail) {
        this.insuranceEmail = insuranceEmail;
    }

    public String getInsuranceIdentification() {
        return insuranceIdentification;
    }

    public void setInsuranceIdentification(String insuranceIdentification) {
        this.insuranceIdentification = insuranceIdentification;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getPreadmissionNumber() {
        return preadmissionNumber;
    }

    public void setPreadmissionNumber(String preadmissionNumber) {
        this.preadmissionNumber = preadmissionNumber;
    }

    public String getPreauthorizationNumber() {
        return preauthorizationNumber;
    }

    public void setPreauthorizationNumber(String preauthorizationNumber) {
        this.preauthorizationNumber = preauthorizationNumber;
    }

    public void setInsuranceinformationId(Long insuranceinformationId) {
        this.insuranceinformationId = insuranceinformationId;
    }

    /** needed to map existing entities by carenotebook form processor **/
    public void setInsuranceInformationId(String insuranceinformationId){
        this.insuranceinformationId = Long.parseLong(insuranceinformationId);
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
        hash += ( insuranceinformationId != null ? insuranceinformationId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof InsuranceInformation )) {
            return false;
        }
        InsuranceInformation other = (InsuranceInformation) object;
        if (( this.insuranceinformationId == null && other.insuranceinformationId != null ) || ( this.insuranceinformationId != null && !this.insuranceinformationId.equals(other.insuranceinformationId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.InsuranceInformation[insuranceinformationId=" + insuranceinformationId + "]";
    }

}
