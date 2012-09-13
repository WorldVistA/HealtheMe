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
@Table(name = "carenotebook_nutrition", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nutrition.findAll", query = "SELECT n FROM Nutrition n"),
    @NamedQuery(name = "Nutrition.findByNutritionId", query = "SELECT n FROM Nutrition n WHERE n.nutritionId = :nutritionId"),
    @NamedQuery(name = "Nutrition.findByFeedingSchedule", query = "SELECT n FROM Nutrition n WHERE n.feedingSchedule = :feedingSchedule"),
    @NamedQuery(name = "Nutrition.findByFoodLikes", query = "SELECT n FROM Nutrition n WHERE n.foodLikes = :foodLikes"),
    @NamedQuery(name = "Nutrition.findByFoodDislikes", query = "SELECT n FROM Nutrition n WHERE n.foodDislikes = :foodDislikes"),
    @NamedQuery(name = "Nutrition.findByFeedingModifications", query = "SELECT n FROM Nutrition n WHERE n.feedingModifications = :feedingModifications"),
    @NamedQuery(name = "Nutrition.findByFoodAllergies", query = "SELECT n FROM Nutrition n WHERE n.foodAllergies = :foodAllergies"),
    @NamedQuery(name = "Nutrition.findByHealthRecordId", query = "SELECT n FROM Nutrition n WHERE n.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Nutrition.findByDataSourceId", query = "SELECT n FROM Nutrition n WHERE n.dataSourceId = :dataSourceId"),
    @NamedQuery(name = "Nutrition.findByCareDocumentId", query = "SELECT n FROM Nutrition n WHERE n.careDocumentId = :careDocumentId"),
    @NamedQuery(name = "Nutrition.findBySourceId", query = "SELECT n FROM Nutrition n WHERE n.sourceId = :sourceId"),
    @NamedQuery(name = "Nutrition.findByDateAdded", query = "SELECT n FROM Nutrition n WHERE n.dateAdded = :dateAdded"),
    @NamedQuery(name = "Nutrition.findByComments", query = "SELECT n FROM Nutrition n WHERE n.comments = :comments"),
    @NamedQuery(name = "Nutrition.findByPrimaryKeyForRecord", query = "SELECT d FROM Nutrition d WHERE d.nutritionId = :nutritionId AND d.healthRecordId = :healthRecordId"),
    @NamedQuery(name = "Nutrition.findByMask", query = "SELECT n FROM Nutrition n WHERE n.mask = :mask")})
public class Nutrition extends HealthSummary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nutrition_id", nullable = false)
    private Long nutritionId;
    @Column(name = "feeding_schedule", length = 200)
    private String feedingSchedule;
    @Column(name = "food_likes", length = 200)
    private String foodLikes;
    @Column(name = "food_dislikes", length = 200)
    private String foodDislikes;
    @Column(name = "feeding_modifications", length = 200)
    private String feedingModifications;
    @Column(name = "food_allergies", length = 200)
    private String foodAllergies;
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

    public Nutrition() {
    }

    public Nutrition(Long healthRecordId) {
        super(healthRecordId);
        this.healthRecordId = healthRecordId;
    }

    public Long getNutritionId() {
        return nutritionId;
    }

   /** needed to map existing entities by carenotebook form processor **/
    public void setNutritionId(String nutritionId){
        this.nutritionId = Long.parseLong(nutritionId);
    }
    
//    public void setNutritionId(Long nutritionId) {
//        this.nutritionId = nutritionId;
//    }

    public String getFeedingSchedule() {
        return feedingSchedule;
    }

    public void setFeedingSchedule(String feedingSchedule) {
        this.feedingSchedule = feedingSchedule;
    }

    public String getFoodLikes() {
        return foodLikes;
    }

    public void setFoodLikes(String foodLikes) {
        this.foodLikes = foodLikes;
    }

    public String getFoodDislikes() {
        return foodDislikes;
    }

    public void setFoodDislikes(String foodDislikes) {
        this.foodDislikes = foodDislikes;
    }

    public String getFeedingModifications() {
        return feedingModifications;
    }

    public void setFeedingModifications(String feedingModifications) {
        this.feedingModifications = feedingModifications;
    }

    public String getFoodAllergies() {
        return foodAllergies;
    }

    public void setFoodAllergies(String foodAllergies) {
        this.foodAllergies = foodAllergies;
    }

    /**
     *
     * @return
     */
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
        hash += ( nutritionId != null ? nutritionId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof Nutrition )) {
            return false;
        }
        Nutrition other = (Nutrition) object;
        if (( this.nutritionId == null && other.nutritionId != null ) || ( this.nutritionId != null && !this.nutritionId.equals(other.nutritionId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.carenotebook.Nutrition[ nutritionId=" + nutritionId + " ]";
    }
    
}
