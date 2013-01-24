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
@Table(name = "carenotebook_careproviders", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CareProvider.findAll", query = "SELECT c FROM CareProvider c"),
    @NamedQuery(name = "CareProvider.findByProviderId", query = "SELECT c FROM CareProvider c WHERE c.providerId = :providerId"),
    @NamedQuery(name = "CareProvider.findByProviderType", query = "SELECT c FROM CareProvider c WHERE c.providerType = :providerType"),
    @NamedQuery(name = "CareProvider.findByProviderName", query = "SELECT c FROM CareProvider c WHERE c.providerName = :providerName"),
    @NamedQuery(name = "CareProvider.findByProviderAgency", query = "SELECT c FROM CareProvider c WHERE c.providerAgency = :providerAgency"),
    @NamedQuery(name = "CareProvider.findByProviderAddress", query = "SELECT c FROM CareProvider c WHERE c.providerAddress = :providerAddress"),
    @NamedQuery(name = "CareProvider.findByProviderAddress2", query = "SELECT c FROM CareProvider c WHERE c.providerAddress2 = :providerAddress2"),
    @NamedQuery(name = "CareProvider.findByProviderCity", query = "SELECT c FROM CareProvider c WHERE c.providerCity = :providerCity"),
    @NamedQuery(name = "CareProvider.findByProviderState", query = "SELECT c FROM CareProvider c WHERE c.providerState = :providerState"),
    @NamedQuery(name = "CareProvider.findByPhoneNumber", query = "SELECT c FROM CareProvider c WHERE c.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "CareProvider.findByFaxNumber", query = "SELECT c FROM CareProvider c WHERE c.faxNumber = :faxNumber"),
    @NamedQuery(name = "CareProvider.findByProviderEmail", query = "SELECT c FROM CareProvider c WHERE c.providerEmail = :providerEmail"),
    @NamedQuery(name = "CareProvider.findByHealthRecordId", query = "SELECT c FROM CareProvider c WHERE c.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "CareProvider.findByDataSourceId", query = "SELECT c FROM CareProvider c WHERE c.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "CareProvider.findByCareDocumentId", query = "SELECT c FROM CareProvider c WHERE c.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "CareProvider.findBySourceId", query = "SELECT c FROM CareProvider c WHERE c.sourceId = :sourceId"),
    @NamedQuery(name = "CareProvider.findByDateAdded", query = "SELECT c FROM CareProvider c WHERE c.dateAdded = :dateAdded"),
    @NamedQuery(name = "CareProvider.findByComments", query = "SELECT c FROM CareProvider c WHERE c.comments = :comments"),
    @NamedQuery(name = "CareProvider.findByMask", query = "SELECT c FROM CareProvider c WHERE c.mask = :mask"),
    @NamedQuery(name = "CareProvider.findByPrimaryKeyForRecord", query = "SELECT d FROM CareProvider d WHERE d.providerId = :providerId AND d.healthRecordId = :healthRecordId")})
public class CareProvider extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "provider_id", nullable = false)
    private Long providerId;
    @Column(name = "provider_type", length = 50)
    private String providerType;
    @Column(name = "provider_name", length = 50)
    private String providerName;
    @Column(name = "provider_contact", length = 50)
    private String providerContact;
    @Column(name = "provider_agency", length = 50)
    private String providerAgency;
    @Column(name = "provider_address", length = 200)
    private String providerAddress;
    @Column(name = "provider_address2", length = 200)
    private String providerAddress2;
    @Column(name = "provider_city", length = 100)
    private String providerCity;
    @Column(name = "provider_state", length = 100)
    private String providerState;
    @Column(name = "phone_number", length = 25)
    private String phoneNumber;
    @Column(name = "fax_number", length = 25)
    private String faxNumber;
    @Column(name = "provider_email", length = 100)
    private String providerEmail;
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

    public CareProvider() {
    }

	public CareProvider(Long healthRecordId) {
		this.healthRecordId = healthRecordId;
		providerType = "";
		providerName = "";
		providerContact = "";
		providerAgency = "";
		providerAddress = "";
		providerAddress2 = "";
		providerCity = "";
		providerState = "";
		phoneNumber = "";
		faxNumber = "";
		providerEmail = "";
		comments = "";
	}

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }
    
   /** needed to map existing entities by carenotebook form processor **/
    public void setProviderId(String providerId){
        this.providerId = Long.parseLong(providerId);
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getProviderContact() {
        return providerContact;
    }

    public void setProviderContact(String providerContact) {
        this.providerContact = providerContact;
    }

    public String getProviderAgency() {
        return providerAgency;
    }

    public void setProviderAgency(String providerAgency) {
        this.providerAgency = providerAgency;
    }

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }

    public String getProviderAddress2() {
        return providerAddress2;
    }

    public void setProviderAddress2(String providerAddress2) {
        this.providerAddress2 = providerAddress2;
    }

    public String getProviderCity() {
        return providerCity;
    }

    public void setProviderCity(String providerCity) {
        this.providerCity = providerCity;
    }

    public String getProviderState() {
        return providerState;
    }

    public void setProviderState(String providerState) {
        this.providerState = providerState;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
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
        hash += ( providerId != null ? providerId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof CareProvider )) {
            return false;
        }
        CareProvider other = (CareProvider) object;
        if (( this.providerId == null && other.providerId != null ) || ( this.providerId != null && !this.providerId.equals(other.providerId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.CareProvider[ providerId=" + providerId + " ]";
    }
    
}
