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
package com.krminc.phr.domain.clinical;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author chaz
 */
@Entity
@Table(name = "phr.rec_ccr_vitalsign_results")
@NamedQueries({
    @NamedQuery(name = "CcrVitalSignResult.findById", query = "SELECT m FROM CcrVitalSignResult m  WHERE  m.id =  :Id "),
    @NamedQuery(name = "CcrVitalSignResult.findByHealthRecordId", query = "SELECT m FROM CcrVitalSignResult m JOIN m.ccrDocument c JOIN c.healthRecords h WHERE  h.id =  :healthRecordId "),
    @NamedQuery(name = "CcrVitalSignResult.countByHealthRecordId", query = "SELECT COUNT(m) FROM CcrVitalSignResult m JOIN m.ccrDocument c JOIN c.healthRecords h WHERE h.id= :healthRecordId"),
    @NamedQuery(name = "CcrVitalSignResult.countByHealthRecordIdAndVitalType", query = "SELECT COUNT(m) FROM CcrVitalSignResult m JOIN m.ccrDocument c JOIN c.healthRecords h WHERE h.id = :healthRecordId AND m.descriptionText = :vitalType"),
    @NamedQuery(name = "CcrVitalSignResult.findByHealthRecordIdAndVitalType", query = "SELECT m FROM CcrVitalSignResult m JOIN m.ccrDocument c JOIN c.healthRecords h WHERE h.id = :healthRecordId AND m.descriptionText = :vitalType")
})
public class CcrVitalSignResult extends GenericResult implements Serializable {

    private static final long serialVersionUID = 20091128L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ccr_vitalsign_result_id")
    protected Long id;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_vitalsign_results_datetime", joinColumns = {
        @JoinColumn(name = "ccr_vitalsign_result_id")}, inverseJoinColumns = {
        @JoinColumn(name = "datetime_id")})
    protected List<CcrDateTime> dateTime;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_vitalsign_results_tests",
    joinColumns = {
        @JoinColumn(name = "ccr_vitalsign_result_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "ccr_vitalsign_test_id")})
    protected List<CcrVitalSignTest> vitalSignTests;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy="vitalSignResult")
    protected List <CcrVitalSignResultSource> sources;

    @Transient
    private Date exactDateTime;

    @Transient
    private String source;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CcrVitalSignResultSource> getSources() {
        return sources;
    }

    public void setSources(List<CcrVitalSignResultSource> sources) {
        this.sources = sources;
    }

    public List<CcrVitalSignTest> getVitalSignTests() {
        return vitalSignTests;
    }

    public void setVitalSignTests(List<CcrVitalSignTest> vitalSignTests) {
        this.vitalSignTests = vitalSignTests;
    }

    public void setDateTime(List<CcrDateTime> dateTime) {
        this.dateTime = dateTime;
    }

    public List<CcrDateTime> getDateTime() {
        return dateTime;
    }

    //custom getters
    //note Date Added custom getter has been extracted to parent class
    public Date getExactDateTime() {
        if (exactDateTime == null) {
            try {
                exactDateTime = dateTime.get(0).getExactDateTime();
            }
            catch (Exception ex) {
                exactDateTime = null;
            }
        }
        return exactDateTime;
    }

    public String getSource() {
        if (source == null) {
            try {
                source = sources.get(0).getActor().getInformationSystemName();
            }
            catch (Exception ex) {
                source = null;
            }
        }
        return source;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CcrVitalSignResult)) {
            return false;
        }
        CcrVitalSignResult other = (CcrVitalSignResult) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
}
