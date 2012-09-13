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
import javax.persistence.Transient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cmccall
 */
@Entity
@Table(name = "carenotebook_emergencyinformation", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "EmergencyInformation.findAll", query = "SELECT m FROM EmergencyInformation m"),
    @NamedQuery(name = "EmergencyInformation.findByEmergencyinformationId", query = "SELECT m FROM EmergencyInformation m WHERE m.emergencyinformationId = :emergencyinformationId"),
    @NamedQuery(name = "EmergencyInformation.findByAmbulanceNumber", query = "SELECT m FROM EmergencyInformation m WHERE m.ambulanceNumber = :ambulanceNumber"),
    @NamedQuery(name = "EmergencyInformation.findByPhysicianName", query = "SELECT m FROM EmergencyInformation m WHERE m.physicianName = :physicianName"),
    @NamedQuery(name = "EmergencyInformation.findByPhysicianNumber", query = "SELECT m FROM EmergencyInformation m WHERE m.physicianNumber = :physicianNumber"),
    @NamedQuery(name = "EmergencyInformation.findByFireNumber", query = "SELECT m FROM EmergencyInformation m WHERE m.fireNumber = :fireNumber"),
    @NamedQuery(name = "EmergencyInformation.findByPoliceNumber", query = "SELECT m FROM EmergencyInformation m WHERE m.policeNumber = :policeNumber"),
    @NamedQuery(name = "EmergencyInformation.findByPoisonNumber", query = "SELECT m FROM EmergencyInformation m WHERE m.poisonNumber = :poisonNumber"),
    @NamedQuery(name = "EmergencyInformation.findByFatherName", query = "SELECT m FROM EmergencyInformation m WHERE m.fatherName = :fatherName"),
    @NamedQuery(name = "EmergencyInformation.findByFatherNumber", query = "SELECT m FROM EmergencyInformation m WHERE m.fatherNumber = :fatherNumber"),
    @NamedQuery(name = "EmergencyInformation.findByMotherName", query = "SELECT m FROM EmergencyInformation m WHERE m.motherName = :motherName"),
    @NamedQuery(name = "EmergencyInformation.findByMotherNumber", query = "SELECT m FROM EmergencyInformation m WHERE m.motherNumber = :motherNumber"),
    @NamedQuery(name = "EmergencyInformation.findByEmergencyContactName", query = "SELECT m FROM EmergencyInformation m WHERE m.emergencyContactName = :emergencyContactName"),
    @NamedQuery(name = "EmergencyInformation.findByEmergencyContactRelationship", query = "SELECT m FROM EmergencyInformation m WHERE m.emergencyContactRelationship = :emergencyContactRelationship"),
    @NamedQuery(name = "EmergencyInformation.findByEmergencyContactNumber", query = "SELECT m FROM EmergencyInformation m WHERE m.emergencyContactNumber = :emergencyContactNumber"),
    @NamedQuery(name = "EmergencyInformation.findByHospitalName", query = "SELECT m FROM EmergencyInformation m WHERE m.hospitalName = :hospitalName"),
    @NamedQuery(name = "EmergencyInformation.findByHospitalNumber", query = "SELECT m FROM EmergencyInformation m WHERE m.hospitalNumber = :hospitalNumber"),
    @NamedQuery(name = "EmergencyInformation.findByHospitalAddress", query = "SELECT m FROM EmergencyInformation m WHERE m.hospitalAddress = :hospitalAddress"),
    @NamedQuery(name = "EmergencyInformation.findByHospitalAddress2", query = "SELECT m FROM EmergencyInformation m WHERE m.hospitalAddress2 = :hospitalAddress2"),
    @NamedQuery(name = "EmergencyInformation.findByHospitalCity", query = "SELECT m FROM EmergencyInformation m WHERE m.hospitalCity = :hospitalCity"),
    @NamedQuery(name = "EmergencyInformation.findByHospitalState", query = "SELECT m FROM EmergencyInformation m WHERE m.hospitalState = :hospitalState"),
    @NamedQuery(name = "EmergencyInformation.findBySpecialtyName", query = "SELECT m FROM EmergencyInformation m WHERE m.specialtyName = :specialtyName"),
    @NamedQuery(name = "EmergencyInformation.findBySpecialtyNumber", query = "SELECT m FROM EmergencyInformation m WHERE m.specialtyNumber = :specialtyNumber"),
    @NamedQuery(name = "EmergencyInformation.findBySpecialtyType", query = "SELECT m FROM EmergencyInformation m WHERE m.specialtyType = :specialtyType"),
    @NamedQuery(name = "EmergencyInformation.findByEmergencyDescription", query = "SELECT m FROM EmergencyInformation m WHERE m.emergencyDescription = :emergencyDescription"),
    @NamedQuery(name = "EmergencyInformation.findByTreatmentDescription", query = "SELECT m FROM EmergencyInformation m WHERE m.treatmentDescription = :treatmentDescription"),
    @NamedQuery(name = "EmergencyInformation.findByHealthRecordId", query = "SELECT m FROM EmergencyInformation m WHERE m.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "EmergencyInformation.findByDataSourceId", query = "SELECT m FROM EmergencyInformation m WHERE m.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "EmergencyInformation.findByCareDocumentId", query = "SELECT m FROM EmergencyInformation m WHERE m.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "EmergencyInformation.findBySourceId", query = "SELECT m FROM EmergencyInformation m WHERE m.sourceId = :sourceId"),
    @NamedQuery(name = "EmergencyInformation.findByDateAdded", query = "SELECT m FROM EmergencyInformation m WHERE m.dateAdded = :dateAdded"),
    @NamedQuery(name = "EmergencyInformation.findByComments", query = "SELECT m FROM EmergencyInformation m WHERE m.comments = :comments"),
    @NamedQuery(name = "EmergencyInformation.findByMask", query = "SELECT m FROM EmergencyInformation m WHERE m.mask = :mask"),
    @NamedQuery(name = "EmergencyInformation.findByPrimaryKeyForRecord", query = "SELECT d FROM EmergencyInformation d WHERE d.emergencyinformationId = :emergencyinformationId AND d.healthRecordId = :healthRecordId")
})
public class EmergencyInformation extends HealthSummary implements Serializable  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "emergencyinformation_id", nullable = false)
    private Long emergencyinformationId;
    @Column(name = "ambulance_number", length = 25)
    private String ambulanceNumber;
    @Column(name = "physician_name", length = 50)
    private String physicianName;
    @Column(name = "physician_number", length = 25)
    private String physicianNumber;
    @Column(name = "fire_number", length = 25)
    private String fireNumber;
    @Column(name = "police_number", length = 25)
    private String policeNumber;
    @Column(name = "poison_number", length = 25)
    private String poisonNumber;
    @Column(name = "father_name", length = 50)
    private String fatherName;
    @Column(name = "father_number", length = 25)
    private String fatherNumber;
    @Column(name = "mother_name", length = 50)
    private String motherName;
    @Column(name = "mother_number", length = 25)
    private String motherNumber;
    @Column(name = "emergency_contact_name", length = 50)
    private String emergencyContactName;
    @Column(name = "emergency_contact_relationship", length = 100)
    private String emergencyContactRelationship;
    @Column(name = "emergency_contact_number", length = 25)
    private String emergencyContactNumber;
    @Column(name = "hospital_name", length = 50)
    private String hospitalName;
    @Column(name = "hospital_number", length = 25)
    private String hospitalNumber;
    @Column(name = "hospital_address", length = 200)
    private String hospitalAddress;
    @Column(name = "hospital_address2", length = 200)
    private String hospitalAddress2;
    @Column(name = "hospital_city", length = 100)
    private String hospitalCity;
    @Column(name = "hospital_state", length = 100)
    private String hospitalState;
    @Column(name = "specialty_name", length = 50)
    private String specialtyName;
    @Column(name = "specialty_number", length = 25)
    private String specialtyNumber;
    @Column(name = "specialty_type", length = 100)
    private String specialtyType;
    @Column(name = "emergency_description", length = 4000)
    private String emergencyDescription;
    @Column(name = "treatment_description", length = 4000)
    private String treatmentDescription;
    @Basic(optional = false)
    @Column(name = "rec_id", nullable = false)
    private Long healthRecordId;
    @Basic(optional = false)
    @Column(name = "data_source_id", nullable = false)
    private Long dataSourceId;
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

    @Transient
    final Logger logger = LoggerFactory.getLogger(EmergencyInformation.class);

    /**
     *
     */
    public EmergencyInformation() {
    }

    public EmergencyInformation(Long hrid) {
        super(hrid);
        this.healthRecordId = hrid;
    }

    /**
     *
     * @param medicalinformationId
     */
//    public EmergencyInformation(Long medicalinformationId) {
//        this.medicalinformationId = medicalinformationId;
//    }

//    public EmergencyInformation(Long medicalinformationId, long recId, long dataSourceId, Date dateAdded) {
//        this.medicalinformationId = medicalinformationId;
//        this.recId = recId;
//        this.dataSourceId = dataSourceId;
//        this.dateAdded = dateAdded;
//    }

    /**
     *
     * @return
     */
    public Long getEmergencyinformationId() {
        return emergencyinformationId;
    }

//    public void setMedicalinformationId(Long medicalinformationId) {
//        this.medicalinformationId = medicalinformationId;
//    }

    /**
     *
     * @return
     */
    public String getAmbulanceNumber() {
        return ambulanceNumber;
    }

    /**
     *
     * @param ambulanceNumber
     */
    public void setAmbulanceNumber(String ambulanceNumber) {
        this.ambulanceNumber = ambulanceNumber;
    }

    /**
     *
     * @return
     */
    public String getPhysicianName() {
        return physicianName;
    }

    /**
     *
     * @param physicianName
     */
    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName;
    }

    /**
     *
     * @return
     */
    public String getPhysicianNumber() {
        return physicianNumber;
    }

    /**
     *
     * @param physicianNumber
     */
    public void setPhysicianNumber(String physicianNumber) {
        this.physicianNumber = physicianNumber;
    }

    /**
     *
     * @return
     */
    public String getFireNumber() {
        return fireNumber;
    }

    /**
     *
     * @param fireNumber
     */
    public void setFireNumber(String fireNumber) {
        this.fireNumber = fireNumber;
    }

    /**
     *
     * @return
     */
    public String getPoliceNumber() {
        return policeNumber;
    }

    /**
     *
     * @param policeNumber
     */
    public void setPoliceNumber(String policeNumber) {
        this.policeNumber = policeNumber;
    }

    /**
     *
     * @return
     */
    public String getPoisonNumber() {
        return poisonNumber;
    }

    /**
     *
     * @param poisonNumber
     */
    public void setPoisonNumber(String poisonNumber) {
        this.poisonNumber = poisonNumber;
    }

    /**
     *
     * @return
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     *
     * @param fatherName
     */
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    /**
     *
     * @return
     */
    public String getFatherNumber() {
        return fatherNumber;
    }

    /**
     *
     * @param fatherNumber
     */
    public void setFatherNumber(String fatherNumber) {
        this.fatherNumber = fatherNumber;
    }

    /**
     *
     * @return
     */
    public String getMotherName() {
        return motherName;
    }

    /**
     *
     * @param motherName
     */
    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    /**
     *
     * @return
     */
    public String getMotherNumber() {
        return motherNumber;
    }

    /**
     *
     * @param motherNumber
     */
    public void setMotherNumber(String motherNumber) {
        this.motherNumber = motherNumber;
    }

    /**
     *
     * @return
     */
    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    /**
     *
     * @param emergencyContactName
     */
    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getEmergencyContactRelationship() {
        return emergencyContactRelationship;
    }

    public void setEmergencyContactRelationship(String emergencyContactRelationship) {
        this.emergencyContactRelationship = emergencyContactRelationship;
    }

    /**
     *
     * @return
     */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     *
     * @param hospitalName
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    /**
     *
     * @return
     */
    public String getHospitalNumber() {
        return hospitalNumber;
    }

    /**
     *
     * @param hospitalNumber
     */
    public void setHospitalNumber(String hospitalNumber) {
        this.hospitalNumber = hospitalNumber;
    }

    /**
     *
     * @return
     */
    public String getHospitalAddress() {
        return hospitalAddress;
    }

    /**
     *
     * @param hospitalAddress
     */
    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    /**
     *
     * @return
     */
    public String getHospitalAddress2() {
        return hospitalAddress2;
    }

    /**
     *
     * @param hospitalAddress2
     */
    public void setHospitalAddress2(String hospitalAddress2) {
        this.hospitalAddress2 = hospitalAddress2;
    }

    /**
     *
     * @return
     */
    public String getHospitalCity() {
        return hospitalCity;
    }

    /**
     *
     * @param hospitalCity
     */
    public void setHospitalCity(String hospitalCity) {
        this.hospitalCity = hospitalCity;
    }

    /**
     *
     * @return
     */
    public String getHospitalState() {
        return hospitalState;
    }

    /**
     *
     * @param hospitalState
     */
    public void setHospitalState(String hospitalState) {
        this.hospitalState = hospitalState;
    }

    /**
     *
     * @return
     */
    public String getSpecialtyName() {
        return specialtyName;
    }

    /**
     *
     * @param specialtyName
     */
    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    /**
     *
     * @return
     */
    public String getSpecialtyNumber() {
        return specialtyNumber;
    }

    /**
     *
     * @param specialtyNumber
     */
    public void setSpecialtyNumber(String specialtyNumber) {
        this.specialtyNumber = specialtyNumber;
    }

    /**
     *
     * @return
     */
    public String getSpecialtyType() {
        return specialtyType;
    }

    /**
     *
     * @param specialtyType
     */
    public void setSpecialtyType(String specialtyType) {
        this.specialtyType = specialtyType;
    }

    /**
     *
     * @return
     */
    public String getEmergencyDescription() {
        return emergencyDescription;
    }

    /**
     *
     * @param emergencyDescription
     */
    public void setEmergencyDescription(String emergencyDescription) {
        this.emergencyDescription = emergencyDescription;
    }

    /**
     *
     * @return
     */
    public String getTreatmentDescription() {
        return treatmentDescription;
    }

    /**
     *
     * @param treatmentDescription
     */
    public void setTreatmentDescription(String treatmentDescription) {
        this.treatmentDescription = treatmentDescription;
    }

    /**
     *
     * @return
     */
    @Override
    public Long getHealthRecordId() {
        return healthRecordId;
    }

//    /**
//     *
//     * @param healthRecordId
//     */
//    public void setHealthRecordId(Long healthRecordId) {
//        this.healthRecordId = healthRecordId;
//    }

    /**
     *
     * @return
     */
    public long getDataSourceId() {
        return dataSourceId;
    }

    /**
     *
     * @param dataSourceId
     */
    public void setDataSourceId(long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    /**
     *
     * @return
     */
    public BigInteger getCareDocumentId() {
        return careDocumentId;
    }

    /**
     *
     * @param careDocumentId
     */
    public void setCareDocumentId(BigInteger careDocumentId) {
        this.careDocumentId = careDocumentId;
    }

    /**
     *
     * @return
     */
    public BigInteger getSourceId() {
        return sourceId;
    }

    /**
     *
     * @param sourceId
     */
    public void setSourceId(BigInteger sourceId) {
        this.sourceId = sourceId;
    }

    /**
     *
     * @return
     */
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     *
     * @param dateAdded
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     *
     * @return
     */
    public String getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     *
     * @return
     */
    public String getMask() {
        return mask;
    }

    /**
     *
     * @param mask
     */
    public void setMask(String mask) {
        this.mask = mask;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( emergencyinformationId != null ? emergencyinformationId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof EmergencyInformation )) {
            return false;
        }
        EmergencyInformation other = (EmergencyInformation) object;
        if (( this.emergencyinformationId == null && other.emergencyinformationId != null ) || ( this.emergencyinformationId != null && !this.emergencyinformationId.equals(other.emergencyinformationId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.EmergencyInformation[emergencyinformationId=" + emergencyinformationId + "]";
    }

}
