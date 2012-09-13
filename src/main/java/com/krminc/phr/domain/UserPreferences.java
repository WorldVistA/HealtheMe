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
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cmccall
 */
@Entity
@Table(name = "user_preferences", catalog = "phr", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserPreferences.findAll", query = "SELECT u FROM UserPreferences u"),
    @NamedQuery(name = "UserPreferences.findByPreferenceId", query = "SELECT u FROM UserPreferences u WHERE u.preferenceId = :preferenceId"),
    @NamedQuery(name = "UserPreferences.findByShowCarenotebook", query = "SELECT u FROM UserPreferences u WHERE u.showCarenotebook = :showCarenotebook"),
    @NamedQuery(name = "UserPreferences.findByLastUpdatedUser", query = "SELECT u FROM UserPreferences u WHERE u.lastUpdatedUser = :lastUpdatedUser"),
    @NamedQuery(name = "UserPreferences.findByLastUpdated", query = "SELECT u FROM UserPreferences u WHERE u.lastUpdated = :lastUpdated")})
public class UserPreferences implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "preference_id", nullable = false)
    private Long preferenceId;
    @Basic(optional = false)
    @Column(name = "show_carenotebook")
    private Boolean showCarenotebook;
    @Basic(optional = false)
    @Column(name = "last_updated_user", nullable = false)
    private String lastUpdatedUser;
    @Basic(optional = false)
    @Column(name = "last_updated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    public UserPreferences() {
    }

    public UserPreferences(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
        this.lastUpdated = new Date();
    }

    public Long getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(Long preferenceId) {
        this.preferenceId = preferenceId;
    }
    
    public Boolean getShowCarenotebook() {
        return showCarenotebook;
    }
    
    public String getShowCarenotebookString() {
        return (showCarenotebook != null ? showCarenotebook.toString() : "");
    }

    public void setShowCarenotebook(Boolean showCarenotebook) {
        this.showCarenotebook = showCarenotebook;
    }

    public String getLastUpdatedUser() {
        return lastUpdatedUser;
    }

    public void setLastUpdatedUser(String lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( preferenceId != null ? preferenceId.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!( object instanceof UserPreferences )) {
            return false;
        }
        UserPreferences other = (UserPreferences) object;
        if (( this.preferenceId == null && other.preferenceId != null ) || ( this.preferenceId != null && !this.preferenceId.equals(other.preferenceId) )) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.UserPreferences[ preferenceId=" + preferenceId + " lastUpdatedUser=" + lastUpdatedUser + " showCarenotebook=" + showCarenotebook + " ]";
    }
    
}
