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
@Table(name = "carenotebook_familyhistory", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FamilyHistory.findAll", query = "SELECT f FROM FamilyHistory f"),
    @NamedQuery(name = "FamilyHistory.findByFamilyhistoryId", query = "SELECT f FROM FamilyHistory f WHERE f.familyhistoryId = :familyhistoryId"),
    @NamedQuery(name = "FamilyHistory.findByMentalIllness", query = "SELECT f FROM FamilyHistory f WHERE f.mentalIllness = :mentalIllness"),
    @NamedQuery(name = "FamilyHistory.findByWhoMentalIllness", query = "SELECT f FROM FamilyHistory f WHERE f.whoMentalIllness = :whoMentalIllness"),
    @NamedQuery(name = "FamilyHistory.findByCerebralPalsy", query = "SELECT f FROM FamilyHistory f WHERE f.cerebralPalsy = :cerebralPalsy"),
    @NamedQuery(name = "FamilyHistory.findByWhoCerebralPalsy", query = "SELECT f FROM FamilyHistory f WHERE f.whoCerebralPalsy = :whoCerebralPalsy"),
    @NamedQuery(name = "FamilyHistory.findByMuscularDystrophy", query = "SELECT f FROM FamilyHistory f WHERE f.muscularDystrophy = :muscularDystrophy"),
    @NamedQuery(name = "FamilyHistory.findByWhoMuscularDystrophy", query = "SELECT f FROM FamilyHistory f WHERE f.whoMuscularDystrophy = :whoMuscularDystrophy"),
    @NamedQuery(name = "FamilyHistory.findByEpilepsy", query = "SELECT f FROM FamilyHistory f WHERE f.epilepsy = :epilepsy"),
    @NamedQuery(name = "FamilyHistory.findByWhoEpilepsy", query = "SELECT f FROM FamilyHistory f WHERE f.whoEpilepsy = :whoEpilepsy"),
    @NamedQuery(name = "FamilyHistory.findByHeartDisease", query = "SELECT f FROM FamilyHistory f WHERE f.heartDisease = :heartDisease"),
    @NamedQuery(name = "FamilyHistory.findByWhoHeartDisease", query = "SELECT f FROM FamilyHistory f WHERE f.whoHeartDisease = :whoHeartDisease"),
    @NamedQuery(name = "FamilyHistory.findByDiabetes", query = "SELECT f FROM FamilyHistory f WHERE f.diabetes = :diabetes"),
    @NamedQuery(name = "FamilyHistory.findByWhoDiabetes", query = "SELECT f FROM FamilyHistory f WHERE f.whoDiabetes = :whoDiabetes"),
    @NamedQuery(name = "FamilyHistory.findByKidneyDisease", query = "SELECT f FROM FamilyHistory f WHERE f.kidneyDisease = :kidneyDisease"),
    @NamedQuery(name = "FamilyHistory.findByWhoKidneyDisease", query = "SELECT f FROM FamilyHistory f WHERE f.whoKidneyDisease = :whoKidneyDisease"),
    @NamedQuery(name = "FamilyHistory.findByCancer", query = "SELECT f FROM FamilyHistory f WHERE f.cancer = :cancer"),
    @NamedQuery(name = "FamilyHistory.findByWhoCancer", query = "SELECT f FROM FamilyHistory f WHERE f.whoCancer = :whoCancer"),
    @NamedQuery(name = "FamilyHistory.findByThyroidDisease", query = "SELECT f FROM FamilyHistory f WHERE f.thyroidDisease = :thyroidDisease"),
    @NamedQuery(name = "FamilyHistory.findByWhoThyroidDisease", query = "SELECT f FROM FamilyHistory f WHERE f.whoThyroidDisease = :whoThyroidDisease"),
    @NamedQuery(name = "FamilyHistory.findByHighBloodPressure", query = "SELECT f FROM FamilyHistory f WHERE f.highBloodPressure = :highBloodPressure"),
    @NamedQuery(name = "FamilyHistory.findByWhoHighBloodPressure", query = "SELECT f FROM FamilyHistory f WHERE f.whoHighBloodPressure = :whoHighBloodPressure"),
    @NamedQuery(name = "FamilyHistory.findByDeceasedSiblings", query = "SELECT f FROM FamilyHistory f WHERE f.deceasedSiblings = :deceasedSiblings"),
    @NamedQuery(name = "FamilyHistory.findByWhoDeceasedSiblings", query = "SELECT f FROM FamilyHistory f WHERE f.whoDeceasedSiblings = :whoDeceasedSiblings"),
    @NamedQuery(name = "FamilyHistory.findByBehaviorDisorder", query = "SELECT f FROM FamilyHistory f WHERE f.behaviorDisorder = :behaviorDisorder"),
    @NamedQuery(name = "FamilyHistory.findByWhoBehaviorDisorder", query = "SELECT f FROM FamilyHistory f WHERE f.whoBehaviorDisorder = :whoBehaviorDisorder"),
    @NamedQuery(name = "FamilyHistory.findByTuberculosis", query = "SELECT f FROM FamilyHistory f WHERE f.tuberculosis = :tuberculosis"),
    @NamedQuery(name = "FamilyHistory.findByWhoTuberculosis", query = "SELECT f FROM FamilyHistory f WHERE f.whoTuberculosis = :whoTuberculosis"),
    @NamedQuery(name = "FamilyHistory.findByHepatitis", query = "SELECT f FROM FamilyHistory f WHERE f.hepatitis = :hepatitis"),
    @NamedQuery(name = "FamilyHistory.findByWhoHepatitis", query = "SELECT f FROM FamilyHistory f WHERE f.whoHepatitis = :whoHepatitis"),
    @NamedQuery(name = "FamilyHistory.findByMetabolicDisease", query = "SELECT f FROM FamilyHistory f WHERE f.metabolicDisease = :metabolicDisease"),
    @NamedQuery(name = "FamilyHistory.findByWhoMetabolicDisease", query = "SELECT f FROM FamilyHistory f WHERE f.whoMetabolicDisease = :whoMetabolicDisease"),
    @NamedQuery(name = "FamilyHistory.findByAllergies", query = "SELECT f FROM FamilyHistory f WHERE f.allergies = :allergies"),
    @NamedQuery(name = "FamilyHistory.findByWhoAllergies", query = "SELECT f FROM FamilyHistory f WHERE f.whoAllergies = :whoAllergies"),
    @NamedQuery(name = "FamilyHistory.findByDevelopmentalDisabilities", query = "SELECT f FROM FamilyHistory f WHERE f.developmentalDisabilities = :developmentalDisabilities"),
    @NamedQuery(name = "FamilyHistory.findByWhoDevelopmentalDisabilities", query = "SELECT f FROM FamilyHistory f WHERE f.whoDevelopmentalDisabilities = :whoDevelopmentalDisabilities"),
    @NamedQuery(name = "FamilyHistory.findByTraumaticBrainInjury", query = "SELECT f FROM FamilyHistory f WHERE f.traumaticBrainInjury = :traumaticBrainInjury"),
    @NamedQuery(name = "FamilyHistory.findByWhoTraumaticBrainInjury", query = "SELECT f FROM FamilyHistory f WHERE f.whoTraumaticBrainInjury = :whoTraumaticBrainInjury"),
    @NamedQuery(name = "FamilyHistory.findByOther", query = "SELECT f FROM FamilyHistory f WHERE f.other = :other"),
    @NamedQuery(name = "FamilyHistory.findByHealthRecordId", query = "SELECT f FROM FamilyHistory f WHERE f.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "FamilyHistory.findByDataSourceId", query = "SELECT f FROM FamilyHistory f WHERE f.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "FamilyHistory.findByCareDocumentId", query = "SELECT f FROM FamilyHistory f WHERE f.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "FamilyHistory.findBySourceId", query = "SELECT f FROM FamilyHistory f WHERE f.sourceId = :sourceId"),
    @NamedQuery(name = "FamilyHistory.findByDateAdded", query = "SELECT f FROM FamilyHistory f WHERE f.dateAdded = :dateAdded"),
    @NamedQuery(name = "FamilyHistory.findByComments", query = "SELECT f FROM FamilyHistory f WHERE f.comments = :comments"),
    @NamedQuery(name = "FamilyHistory.findByPrimaryKeyForRecord", query = "SELECT d FROM FamilyHistory d WHERE d.familyhistoryId = :familyhistoryId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "FamilyHistory.findByMask", query = "SELECT f FROM FamilyHistory f WHERE f.mask = :mask")})
public class FamilyHistory extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "familyhistory_id", nullable = false)
    private Long familyhistoryId;
    @Column(name = "mental_illness")
    private Boolean mentalIllness;
    @Column(name = "who_mental_illness", length = 50)
    private String whoMentalIllness;
    @Column(name = "cerebral_palsy")
    private Boolean cerebralPalsy;
    @Column(name = "who_cerebral_palsy", length = 50)
    private String whoCerebralPalsy;
    @Column(name = "muscular_dystrophy")
    private Boolean muscularDystrophy;
    @Column(name = "who_muscular_dystrophy", length = 50)
    private String whoMuscularDystrophy;
    @Column(name = "epilepsy")
    private Boolean epilepsy;
    @Column(name = "who_epilepsy", length = 50)
    private String whoEpilepsy;
    @Column(name = "heart_disease")
    private Boolean heartDisease;
    @Column(name = "who_heart_disease", length = 50)
    private String whoHeartDisease;
    @Column(name = "diabetes")
    private Boolean diabetes;
    @Column(name = "who_diabetes", length = 50)
    private String whoDiabetes;
    @Column(name = "kidney_disease")
    private Boolean kidneyDisease;
    @Column(name = "who_kidney_disease", length = 50)
    private String whoKidneyDisease;
    @Column(name = "cancer")
    private Boolean cancer;
    @Column(name = "who_cancer", length = 50)
    private String whoCancer;
    @Column(name = "thyroid_disease")
    private Boolean thyroidDisease;
    @Column(name = "who_thyroid_disease", length = 50)
    private String whoThyroidDisease;
    @Column(name = "high_blood_pressure")
    private Boolean highBloodPressure;
    @Column(name = "who_high_blood_pressure", length = 50)
    private String whoHighBloodPressure;
    @Column(name = "deceased_siblings")
    private Boolean deceasedSiblings;
    @Column(name = "who_deceased_siblings", length = 50)
    private String whoDeceasedSiblings;
    @Column(name = "behavior_disorder")
    private Boolean behaviorDisorder;
    @Column(name = "who_behavior_disorder", length = 50)
    private String whoBehaviorDisorder;
    @Column(name = "tuberculosis")
    private Boolean tuberculosis;
    @Column(name = "who_tuberculosis", length = 50)
    private String whoTuberculosis;
    @Column(name = "hepatitis")
    private Boolean hepatitis;
    @Column(name = "who_hepatitis", length = 50)
    private String whoHepatitis;
    @Column(name = "metabolic_disease")
    private Boolean metabolicDisease;
    @Column(name = "who_metabolic_disease", length = 50)
    private String whoMetabolicDisease;
    @Column(name = "allergies")
    private Boolean allergies;
    @Column(name = "who_allergies", length = 50)
    private String whoAllergies;
    @Column(name = "developmental_disabilities")
    private Boolean developmentalDisabilities;
    @Column(name = "who_developmental_disabilities", length = 50)
    private String whoDevelopmentalDisabilities;
    @Column(name = "traumatic_brain_injury")
    private Boolean traumaticBrainInjury;
    @Column(name = "who_traumatic_brain_injury", length = 50)
    private String whoTraumaticBrainInjury;
    @Column(name = "other", length = 512)
    private String other;
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

    public FamilyHistory() {
    }

    public FamilyHistory(Long hrid) {
        super(hrid);
        this.healthRecordId = hrid;
    }

    public Long getFamilyhistoryId() {
        return familyhistoryId;
    }

//    public void setFamilyhistoryId(Long familyhistoryId) {
//        this.familyhistoryId = familyhistoryId;
//    }

    public Boolean getMentalIllness() {
        return mentalIllness;
    }

    public void setMentalIllness(Boolean mentalIllness) {
        this.mentalIllness = mentalIllness;
    }

    public String getWhoMentalIllness() {
        return whoMentalIllness;
    }

    public void setWhoMentalIllness(String whoMentalIllness) {
        this.whoMentalIllness = whoMentalIllness;
    }

    public Boolean getCerebralPalsy() {
        return cerebralPalsy;
    }

    public void setCerebralPalsy(Boolean cerebralPalsy) {
        this.cerebralPalsy = cerebralPalsy;
    }

    public String getWhoCerebralPalsy() {
        return whoCerebralPalsy;
    }

    public void setWhoCerebralPalsy(String whoCerebralPalsy) {
        this.whoCerebralPalsy = whoCerebralPalsy;
    }

    public Boolean getMuscularDystrophy() {
        return muscularDystrophy;
    }

    public void setMuscularDystrophy(Boolean muscularDystrophy) {
        this.muscularDystrophy = muscularDystrophy;
    }

    public String getWhoMuscularDystrophy() {
        return whoMuscularDystrophy;
    }

    public void setWhoMuscularDystrophy(String whoMuscularDystrophy) {
        this.whoMuscularDystrophy = whoMuscularDystrophy;
    }

    public Boolean getEpilepsy() {
        return epilepsy;
    }

    public void setEpilepsy(Boolean epilepsy) {
        this.epilepsy = epilepsy;
    }

    public String getWhoEpilepsy() {
        return whoEpilepsy;
    }

    public void setWhoEpilepsy(String whoEpilepsy) {
        this.whoEpilepsy = whoEpilepsy;
    }

    public Boolean getHeartDisease() {
        return heartDisease;
    }

    public void setHeartDisease(Boolean heartDisease) {
        this.heartDisease = heartDisease;
    }

    public String getWhoHeartDisease() {
        return whoHeartDisease;
    }

    public void setWhoHeartDisease(String whoHeartDisease) {
        this.whoHeartDisease = whoHeartDisease;
    }

    public Boolean getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(Boolean diabetes) {
        this.diabetes = diabetes;
    }

    public String getWhoDiabetes() {
        return whoDiabetes;
    }

    public void setWhoDiabetes(String whoDiabetes) {
        this.whoDiabetes = whoDiabetes;
    }

    public Boolean getKidneyDisease() {
        return kidneyDisease;
    }

    public void setKidneyDisease(Boolean kidneyDisease) {
        this.kidneyDisease = kidneyDisease;
    }

    public String getWhoKidneyDisease() {
        return whoKidneyDisease;
    }

    public void setWhoKidneyDisease(String whoKidneyDisease) {
        this.whoKidneyDisease = whoKidneyDisease;
    }

    public Boolean getCancer() {
        return cancer;
    }

    public void setCancer(Boolean cancer) {
        this.cancer = cancer;
    }

    public String getWhoCancer() {
        return whoCancer;
    }

    public void setWhoCancer(String whoCancer) {
        this.whoCancer = whoCancer;
    }

    public Boolean getThyroidDisease() {
        return thyroidDisease;
    }

    public void setThyroidDisease(Boolean thyroidDisease) {
        this.thyroidDisease = thyroidDisease;
    }

    public String getWhoThyroidDisease() {
        return whoThyroidDisease;
    }

    public void setWhoThyroidDisease(String whoThyroidDisease) {
        this.whoThyroidDisease = whoThyroidDisease;
    }

    public Boolean getHighBloodPressure() {
        return highBloodPressure;
    }

    public void setHighBloodPressure(Boolean highBloodPressure) {
        this.highBloodPressure = highBloodPressure;
    }

    public String getWhoHighBloodPressure() {
        return whoHighBloodPressure;
    }

    public void setWhoHighBloodPressure(String whoHighBloodPressure) {
        this.whoHighBloodPressure = whoHighBloodPressure;
    }

    public Boolean getDeceasedSiblings() {
        return deceasedSiblings;
    }

    public void setDeceasedSiblings(Boolean deceasedSiblings) {
        this.deceasedSiblings = deceasedSiblings;
    }

    public String getWhoDeceasedSiblings() {
        return whoDeceasedSiblings;
    }

    public void setWhoDeceasedSiblings(String whoDeceasedSiblings) {
        this.whoDeceasedSiblings = whoDeceasedSiblings;
    }

    public Boolean getBehaviorDisorder() {
        return behaviorDisorder;
    }

    public void setBehaviorDisorder(Boolean behaviorDisorder) {
        this.behaviorDisorder = behaviorDisorder;
    }

    public String getWhoBehaviorDisorder() {
        return whoBehaviorDisorder;
    }

    public void setWhoBehaviorDisorder(String whoBehaviorDisorder) {
        this.whoBehaviorDisorder = whoBehaviorDisorder;
    }

    public Boolean getTuberculosis() {
        return tuberculosis;
    }

    public void setTuberculosis(Boolean tuberculosis) {
        this.tuberculosis = tuberculosis;
    }

    public String getWhoTuberculosis() {
        return whoTuberculosis;
    }

    public void setWhoTuberculosis(String whoTuberculosis) {
        this.whoTuberculosis = whoTuberculosis;
    }

    public Boolean getHepatitis() {
        return hepatitis;
    }

    public void setHepatitis(Boolean hepatitis) {
        this.hepatitis = hepatitis;
    }

    public String getWhoHepatitis() {
        return whoHepatitis;
    }

    public void setWhoHepatitis(String whoHepatitis) {
        this.whoHepatitis = whoHepatitis;
    }

    public Boolean getMetabolicDisease() {
        return metabolicDisease;
    }

    public void setMetabolicDisease(Boolean metabolicDisease) {
        this.metabolicDisease = metabolicDisease;
    }

    public String getWhoMetabolicDisease() {
        return whoMetabolicDisease;
    }

    public void setWhoMetabolicDisease(String whoMetabolicDisease) {
        this.whoMetabolicDisease = whoMetabolicDisease;
    }

    public Boolean getAllergies() {
        return allergies;
    }

    public void setAllergies(Boolean allergies) {
        this.allergies = allergies;
    }

    public String getWhoAllergies() {
        return whoAllergies;
    }

    public void setWhoAllergies(String whoAllergies) {
        this.whoAllergies = whoAllergies;
    }

    public Boolean getDevelopmentalDisabilities() {
        return developmentalDisabilities;
    }

    public void setDevelopmentalDisabilities(Boolean developmentalDisabilities) {
        this.developmentalDisabilities = developmentalDisabilities;
    }

    public String getWhoDevelopmentalDisabilities() {
        return whoDevelopmentalDisabilities;
    }

    public void setWhoDevelopmentalDisabilities(String whoDevelopmentalDisabilities) {
        this.whoDevelopmentalDisabilities = whoDevelopmentalDisabilities;
    }

    public Boolean getTraumaticBrainInjury() {
        return traumaticBrainInjury;
    }

    public void setTraumaticBrainInjury(Boolean traumaticBrainInjury) {
        this.traumaticBrainInjury = traumaticBrainInjury;
    }

    public String getWhoTraumaticBrainInjury() {
        return whoTraumaticBrainInjury;
    }

    public void setWhoTraumaticBrainInjury(String whoTraumaticBrainInjury) {
        this.whoTraumaticBrainInjury = whoTraumaticBrainInjury;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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
        hash += ( familyhistoryId != null ? familyhistoryId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof FamilyHistory )) {
            return false;
        }
        FamilyHistory other = (FamilyHistory) object;
        if (( this.familyhistoryId == null && other.familyhistoryId != null ) || ( this.familyhistoryId != null && !this.familyhistoryId.equals(other.familyhistoryId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.FamilyHistory[ familyhistoryId=" + familyhistoryId + " ]";
    }
    
}
