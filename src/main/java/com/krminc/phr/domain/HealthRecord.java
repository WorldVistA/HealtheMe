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
/*
 * All content copyright (c) 2009 KRM Associates, Inc., except as may otherwise be noted in a separate copyright
 * notice. All rights reserved.
 */
package com.krminc.phr.domain;

import com.krminc.phr.core.UserConfig;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Health Record entity class.
 *
 * @author cmccall
 * @author Daniel Shaw (dshaw.com)
 */
@Entity
@Table(name = "rec_health_records")
@NamedQueries({
    @NamedQuery(name = "HealthRecord.findAll", query = "SELECT h FROM HealthRecord h"),
//    @NamedQuery(name = "HealthRecord.findHealthRecordIdByUserId", query = "SELECT h.healthRecordId FROM HealthRecord h WHERE h.userId = :userId"),
    @NamedQuery(name = "HealthRecord.findByHealthRecordId", query = "SELECT h FROM HealthRecord h WHERE h.healthRecordId = :healthRecordId"),
//    @NamedQuery(name = "HealthRecord.findByUserId", query = "SELECT h FROM HealthRecord h WHERE h.userId = :userId"),
    @NamedQuery(name = "HealthRecord.findByIsPrimary", query = "SELECT h FROM HealthRecord h WHERE h.primary = :primary"),
    @NamedQuery(name = "HealthRecord.findByGender", query = "SELECT h FROM HealthRecord h WHERE h.gender = :gender"),
    @NamedQuery(name = "HealthRecord.findByDateOfBirth", query = "SELECT h FROM HealthRecord h WHERE h.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "HealthRecord.findByMaritalStatus", query = "SELECT h FROM HealthRecord h WHERE h.maritalStatus = :maritalStatus"),
    @NamedQuery(name = "HealthRecord.findByBloodType", query = "SELECT h FROM HealthRecord h WHERE h.bloodType = :bloodType"),
    @NamedQuery(name = "HealthRecord.findByOrganDonor", query = "SELECT h FROM HealthRecord h WHERE h.organDonor = :organDonor"),
    @NamedQuery(name = "HealthRecord.findByDateCreated", query = "SELECT h FROM HealthRecord h WHERE h.dateCreated = :dateCreated"),
    @NamedQuery(name = "HealthRecord.findByNotes", query = "SELECT h FROM HealthRecord h WHERE h.notes = :notes")//,
    /* Custom Queries */
//    @NamedQuery(name = "HealthRecord.findUserIdByHealthRecordId", query = "SELECT h.userId FROM HealthRecord h WHERE h.healthRecordId = :healthRecordId")//,
//    @NamedQuery(name = "HealthRecord.validateAccessPrivileges", query = "SELECT h.userId FROM HealthRecord h WHERE h.healthRecordId = :healthRecordId")
})
public class HealthRecord implements Serializable {

    private static final long serialVersionUID = 20091124L;
    
    transient final Logger logger = LoggerFactory.getLogger(HealthRecord.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rec_id", nullable = false)
    private Long healthRecordId;
    @Basic(optional = false)
//    @Column(name = "user_id", nullable = false)
//    private Long userId;
    @Column(name = "primary_rec")
    private Boolean primary;
    @Basic(optional = false)
    @Column(name = "gender", nullable = false, length = 2)
    private String gender;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Calendar dateOfBirth;
    @Column(name = "marital_status", length = 2)
    private String maritalStatus;
    @Column(name = "blood_type", length = 2)
    private String bloodType;
    @Column(name = "organ_donor")
    private Boolean organDonor;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "notes", length = 255)
    private String notes;

    //@ManyToOne
//    @ManyToMany
//    @JoinTable(name="user_users_rec_healthrecord")
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id", updatable=false, insertable=false)

    @ManyToMany(cascade = CascadeType.REMOVE ,mappedBy = "healthRecords")
    @JoinTable(name="user_users_rec_healthrecord",
        joinColumns = {
            @JoinColumn(name="rec_id")
        }, inverseJoinColumns = {
            @JoinColumn(name = "user_id")
        }
    )
    private List<User> userList;
    //private User user;
    
    @OneToOne(optional=true)
    @JoinColumn(name="preference_id", unique=true, nullable=true, updatable=true)
    private UserPreferences preferences;

    @Transient
    private int age = -1;


    public HealthRecord() {
    }

    public HealthRecord(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    /**
     * Constructor for user enrollment form.
     * User defined post construction.
     *
     * @param primary
     * @param gender
     * @param dateOfBirth
     * @param dateCreated
     */
    public HealthRecord(Boolean primary, String gender, Calendar dateOfBirth, Date dateCreated) {
        this.primary = primary;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.dateCreated = dateCreated;
    }


    public Long getHealthRecordId() {
        return healthRecordId;
    }

    public void setHealthRecordId(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

    /**
     * Interface for primary Health Record where pure Java Bean is required (get/set).
     *
     * @return whether Health Record object is primary.
     */
    public Boolean getPrimary() {
        return primary;
    }

    public Boolean isPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Boolean getOrganDonor() {
        return organDonor;
    }

    public void setOrganDonor(Boolean organDonor) {
        this.organDonor = organDonor;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /** Full gender string instead of abbreviated (M/F). */
    public String getFullGender() {
        try {
            if (gender.equalsIgnoreCase("M")) {
                return "Male";
            } else if (gender.equalsIgnoreCase("F")) {
                return "Female";
            }
        } catch (Exception ex) {
        }
        return new String("");
    }

    /** calculated age */
    public int getAge() {
        if (age < 0){
            Calendar todayCal = Calendar.getInstance();
            age = todayCal.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);
            if (todayCal.get(Calendar.DAY_OF_YEAR) < dateOfBirth.get(Calendar.DAY_OF_YEAR))  age--;
        }
        return age;
    }

    public String getDateOfBirthString() {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return df.format(dateOfBirth.getTime());
        } catch (Exception ex) {
            return new String("");
        }
    }

    public Date getDateOfBirthAsDate() {
        return new DateTime(dateOfBirth).toDate();
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> users) {
        userList = users;
    }

    public User getUser() {
        for (User u : userList) {
            for (UserRole r : u.getRoles()){
                if (r.getRole().equalsIgnoreCase(UserConfig.ROLE_PATIENT)) {
                    return u; //find users of health record whom are patients, first
                }
            }
        }
        return userList.get(0);
    }

    public void setUser(User user) {
        if (userList == null) userList = new ArrayList<User>();
        userList.add(user);
    }
    
    public UserPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(UserPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (healthRecordId != null ? healthRecordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HealthRecord)) {
            return false;
        }
        HealthRecord other = (HealthRecord) object;
        if ((this.healthRecordId == null && other.healthRecordId != null) || (this.healthRecordId != null && !this.healthRecordId.equals(other.healthRecordId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.HealthRecord[healthRecordId=" + healthRecordId + "]";
    }

}
