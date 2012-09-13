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

package com.krminc.phr.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * ExerciseList entity.
 *
 * @author cmccall
 */
@Entity
@Table(name = "data_exercises_list", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "Exercise.findByExerciseListId", query = "SELECT v FROM ExerciseList v WHERE v.exerciseId = :exerciseId"),
    @NamedQuery(name = "Exercise.findByDescription", query = "SELECT v FROM ExerciseList v WHERE v.exerciseDescription = :exerciseDescription")
})

//CREATE  TABLE phr.data_exercises_list (
//  exercise_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
//  exercise_description VARCHAR(45) NULL ,
//  exercise_display TINYINT NULL ,
//  acsm_category VARCHAR(45) NULL ,
//  acsm_description VARCHAR(255) NULL ,
//  acsm_compcode CHAR(5) NULL ,
//  acsm_mets FLOAT NULL
//) ENGINE=InnoDB DEFAULT CHARSET=utf8;

public class ExerciseList implements Serializable {
    private static final long serialVersionUID = 20110112L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "exercise_id", nullable = false)
    private Long exerciseId;
    
    @Column(name = "exercise_description", length = 45, nullable = true)
    private String exerciseDescription;

    @Column(name="exercise_display")
    private Boolean display;

    //unused ASCM data
    @Column(name = "acsm_category", length = 45, nullable = true)
    private String acsmCategory;

    @Column(name = "acsm_description", length = 255, nullable = true)
    private String acsmDescription;

    @Column(name = "acsm_compcode", length = 5, nullable = true)
    private String acsmCompcode;
    
    @Column(name = "acsm_mets", nullable = true)
    private Double acsmMets;

    public ExerciseList() {
    }


    public Long getExerciseId() {
        return exerciseId;
    }


    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public Boolean getExerciseDisplayed() {
        return display;
    }
    
    //acsm getters

    public String getAcsmCategory() {
        return acsmCategory;
    }

    public String getAcsmCompcode() {
        return acsmCompcode;
    }

    public String getAcsmDescription() {
        return acsmDescription;
    }

    public Double getAcsmMets() {
        return acsmMets;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exerciseId != null ? exerciseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExerciseList)) {
            return false;
        }
        ExerciseList other = (ExerciseList) object;
        if ((this.exerciseId == null && other.exerciseId != null) || (this.exerciseId != null && !this.exerciseId.equals(other.exerciseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.ExerciseList[exerciseId=" + exerciseId + "]";
    }

}
