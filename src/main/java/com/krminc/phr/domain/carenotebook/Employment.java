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
@Table(name = "carenotebook_employment", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employment.findAll", query = "SELECT e FROM Employment e"),
    @NamedQuery(name = "Employment.findByEmploymentId", query = "SELECT e FROM Employment e WHERE e.employmentId = :employmentId"),
    @NamedQuery(name = "Employment.findByEmploymentPlace", query = "SELECT e FROM Employment e WHERE e.employmentPlace = :employmentPlace"),
    @NamedQuery(name = "Employment.findByEmploymentStart", query = "SELECT e FROM Employment e WHERE e.employmentStart = :employmentStart"),
    @NamedQuery(name = "Employment.findByEmploymentEnd", query = "SELECT e FROM Employment e WHERE e.employmentEnd = :employmentEnd"),
    @NamedQuery(name = "Employment.findByEmploymentSupervisor", query = "SELECT e FROM Employment e WHERE e.employmentSupervisor = :employmentSupervisor"),
    @NamedQuery(name = "Employment.findByEmploymentAddress", query = "SELECT e FROM Employment e WHERE e.employmentAddress = :employmentAddress"),
    @NamedQuery(name = "Employment.findByEmploymentAddress2", query = "SELECT e FROM Employment e WHERE e.employmentAddress2 = :employmentAddress2"),
    @NamedQuery(name = "Employment.findByEmploymentCity", query = "SELECT e FROM Employment e WHERE e.employmentCity = :employmentCity"),
    @NamedQuery(name = "Employment.findByEmploymentState", query = "SELECT e FROM Employment e WHERE e.employmentState = :employmentState"),
    @NamedQuery(name = "Employment.findByPhoneNumber", query = "SELECT e FROM Employment e WHERE e.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Employment.findByFaxNumber", query = "SELECT e FROM Employment e WHERE e.faxNumber = :faxNumber"),
    @NamedQuery(name = "Employment.findByEmploymentEmail", query = "SELECT e FROM Employment e WHERE e.employmentEmail = :employmentEmail"),
    @NamedQuery(name = "Employment.findByHealthRecordId", query = "SELECT e FROM Employment e WHERE e.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Employment.findByDataSourceId", query = "SELECT e FROM Employment e WHERE e.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "Employment.findByCareDocumentId", query = "SELECT e FROM Employment e WHERE e.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "Employment.findBySourceId", query = "SELECT e FROM Employment e WHERE e.sourceId = :sourceId"),
    @NamedQuery(name = "Employment.findByDateAdded", query = "SELECT e FROM Employment e WHERE e.dateAdded = :dateAdded"),
    @NamedQuery(name = "Employment.findByComments", query = "SELECT e FROM Employment e WHERE e.comments = :comments"),
    @NamedQuery(name = "Employment.findByPrimaryKeyForRecord", query = "SELECT d FROM Employment d WHERE d.employmentId = :employmentId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Employment.findByMask", query = "SELECT e FROM Employment e WHERE e.mask = :mask")})
public class Employment extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "employment_id", nullable = false)
    private Long employmentId;
    @Column(name = "employment_place", length = 50)
    private String employmentPlace;
    @Column(name = "employment_start")
    @Temporal(TemporalType.DATE)
    private Date employmentStart;
    @Column(name = "employment_end")
    @Temporal(TemporalType.DATE)
    private Date employmentEnd;
    @Column(name = "employment_supervisor", length = 200)
    private String employmentSupervisor;
    @Column(name = "employment_address", length = 200)
    private String employmentAddress;
    @Column(name = "employment_address2", length = 200)
    private String employmentAddress2;
    @Column(name = "employment_city", length = 100)
    private String employmentCity;
    @Column(name = "employment_state", length = 100)
    private String employmentState;
    @Column(name = "phone_number", length = 25)
    private String phoneNumber;
    @Column(name = "fax_number", length = 25)
    private String faxNumber;
    @Column(name = "employment_email", length = 100)
    private String employmentEmail;
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

    public Employment() {
    }

    public Employment(Long healthRecordId) {
        super(healthRecordId);
        this.healthRecordId = healthRecordId;
    }

    public Long getEmploymentId() {
        return employmentId;
    }
    
    /** needed to map existing entities by carenotebook form processor **/
    public void setEmploymentId(String employmentId){
        this.employmentId = Long.parseLong(employmentId);
    }

//    public void setEmploymentId(Long employmentId) {
//        this.employmentId = employmentId;
//    }

    public String getEmploymentPlace() {
        return employmentPlace;
    }

    public void setEmploymentPlace(String employmentPlace) {
        this.employmentPlace = employmentPlace;
    }

    public Date getEmploymentStart() {
        return employmentStart;
    }

    public void setEmploymentStart(String employmentStart) throws ParseException {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        this.employmentStart = df.parse(employmentStart);
    }
    
    public void setEmploymentStart(Date employmentStart) {
        this.employmentStart = employmentStart;
    }

    public Date getEmploymentEnd() {
        return employmentEnd;
    }
    
    public void setEmploymentEnd(String employmentEnd) throws ParseException {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        this.employmentEnd = df.parse(employmentEnd);
    }

    public void setEmploymentEnd(Date employmentEnd) {
        this.employmentEnd = employmentEnd;
    }

    public String getEmploymentSupervisor() {
        return employmentSupervisor;
    }

    public void setEmploymentSupervisor(String employmentSupervisor) {
        this.employmentSupervisor = employmentSupervisor;
    }

    public String getEmploymentAddress() {
        return employmentAddress;
    }

    public void setEmploymentAddress(String employmentAddress) {
        this.employmentAddress = employmentAddress;
    }

    public String getEmploymentAddress2() {
        return employmentAddress2;
    }

    public void setEmploymentAddress2(String employmentAddress2) {
        this.employmentAddress2 = employmentAddress2;
    }

    public String getEmploymentCity() {
        return employmentCity;
    }

    public void setEmploymentCity(String employmentCity) {
        this.employmentCity = employmentCity;
    }

    public String getEmploymentState() {
        return employmentState;
    }

    public void setEmploymentState(String employmentState) {
        this.employmentState = employmentState;
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

    public String getEmploymentEmail() {
        return employmentEmail;
    }

    public void setEmploymentEmail(String employmentEmail) {
        this.employmentEmail = employmentEmail;
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
        hash += ( employmentId != null ? employmentId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof Employment )) {
            return false;
        }
        Employment other = (Employment) object;
        if (( this.employmentId == null && other.employmentId != null ) || ( this.employmentId != null && !this.employmentId.equals(other.employmentId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.Employment[ employmentId=" + employmentId + " ]";
    }
    
}
