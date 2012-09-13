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
@Table(name = "carenotebook_school", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "School.findAll", query = "SELECT s FROM School s"),
    @NamedQuery(name = "School.findBySchoolId", query = "SELECT s FROM School s WHERE s.schoolId = :schoolId"),
    @NamedQuery(name = "School.findBySchoolName", query = "SELECT s FROM School s WHERE s.schoolName = :schoolName"),
    @NamedQuery(name = "School.findBySchoolAddress", query = "SELECT s FROM School s WHERE s.schoolAddress = :schoolAddress"),
    @NamedQuery(name = "School.findBySchoolAddress2", query = "SELECT s FROM School s WHERE s.schoolAddress2 = :schoolAddress2"),
    @NamedQuery(name = "School.findBySchoolCity", query = "SELECT s FROM School s WHERE s.schoolCity = :schoolCity"),
    @NamedQuery(name = "School.findBySchoolState", query = "SELECT s FROM School s WHERE s.schoolState = :schoolState"),
    @NamedQuery(name = "School.findBySchoolPhoneNumber", query = "SELECT s FROM School s WHERE s.schoolPhoneNumber = :schoolPhoneNumber"),
    @NamedQuery(name = "School.findBySchoolFaxNumber", query = "SELECT s FROM School s WHERE s.schoolFaxNumber = :schoolFaxNumber"),
    @NamedQuery(name = "School.findBySchoolEmail", query = "SELECT s FROM School s WHERE s.schoolEmail = :schoolEmail"),
    @NamedQuery(name = "School.findByBusGarageNumber", query = "SELECT s FROM School s WHERE s.busGarageNumber = :busGarageNumber"),
    @NamedQuery(name = "School.findByBusIdNumber", query = "SELECT s FROM School s WHERE s.busIdNumber = :busIdNumber"),
    @NamedQuery(name = "School.findByDistrictName", query = "SELECT s FROM School s WHERE s.districtName = :districtName"),
    @NamedQuery(name = "School.findByHealthRecordId", query = "SELECT s FROM School s WHERE s.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "School.findByDataSourceId", query = "SELECT s FROM School s WHERE s.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "School.findByCareDocumentId", query = "SELECT s FROM School s WHERE s.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "School.findBySourceId", query = "SELECT s FROM School s WHERE s.sourceId = :sourceId"),
    @NamedQuery(name = "School.findByDateAdded", query = "SELECT s FROM School s WHERE s.dateAdded = :dateAdded"),
    @NamedQuery(name = "School.findByComments", query = "SELECT s FROM School s WHERE s.comments = :comments"),
    @NamedQuery(name = "School.findByPrimaryKeyForRecord", query = "SELECT d FROM School d WHERE d.schoolId = :schoolId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "School.findByMask", query = "SELECT s FROM School s WHERE s.mask = :mask")})
public class School extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "school_id", nullable = false)
    private Long schoolId;
    @Column(name = "school_name", length = 50)
    private String schoolName;
    @Column(name = "school_address", length = 200)
    private String schoolAddress;
    @Column(name = "school_address2", length = 200)
    private String schoolAddress2;
    @Column(name = "school_city", length = 100)
    private String schoolCity;
    @Column(name = "school_state", length = 100)
    private String schoolState;
    @Column(name = "school_phone_number", length = 25)
    private String schoolPhoneNumber;
    @Column(name = "school_fax_number", length = 25)
    private String schoolFaxNumber;
    @Column(name = "school_email", length = 100)
    private String schoolEmail;
    @Column(name = "bus_garage_number", length = 25)
    private String busGarageNumber;
    @Column(name = "bus_id_number", length = 25)
    private String busIdNumber;
    @Column(name = "district_name", length = 50)
    private String districtName;
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

    public School() {
    }

    public School(Long healthRecordId) {
        super(healthRecordId);
        this.healthRecordId = healthRecordId;
    }

    public Long getSchoolId() {
        return schoolId;
    }
    
    /** needed to map existing entities by carenotebook form processor **/
    public void setSchoolId(String schoolId){
        this.schoolId = Long.parseLong(schoolId);
    }

//    public void setSchoolId(Long schoolId) {
//        this.schoolId = schoolId;
//    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getSchoolAddress2() {
        return schoolAddress2;
    }

    public void setSchoolAddress2(String schoolAddress2) {
        this.schoolAddress2 = schoolAddress2;
    }

    public String getSchoolCity() {
        return schoolCity;
    }

    public void setSchoolCity(String schoolCity) {
        this.schoolCity = schoolCity;
    }

    public String getSchoolState() {
        return schoolState;
    }

    public void setSchoolState(String schoolState) {
        this.schoolState = schoolState;
    }

    public String getSchoolPhoneNumber() {
        return schoolPhoneNumber;
    }

    public void setSchoolPhoneNumber(String schoolPhoneNumber) {
        this.schoolPhoneNumber = schoolPhoneNumber;
    }

    public String getSchoolFaxNumber() {
        return schoolFaxNumber;
    }

    public void setSchoolFaxNumber(String schoolFaxNumber) {
        this.schoolFaxNumber = schoolFaxNumber;
    }

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public void setSchoolEmail(String schoolEmail) {
        this.schoolEmail = schoolEmail;
    }

    public String getBusGarageNumber() {
        return busGarageNumber;
    }

    public void setBusGarageNumber(String busGarageNumber) {
        this.busGarageNumber = busGarageNumber;
    }

    public String getBusIdNumber() {
        return busIdNumber;
    }

    public void setBusIdNumber(String busIdNumber) {
        this.busIdNumber = busIdNumber;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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
        hash += ( schoolId != null ? schoolId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof School )) {
            return false;
        }
        School other = (School) object;
        if (( this.schoolId == null && other.schoolId != null ) || ( this.schoolId != null && !this.schoolId.equals(other.schoolId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.School[ schoolId=" + schoolId + " ]";
    }
    
}
