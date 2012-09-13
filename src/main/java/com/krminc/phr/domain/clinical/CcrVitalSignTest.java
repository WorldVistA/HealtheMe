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
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.builder.StandardToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author chaz
 */
@Entity
@Table(name = "phr.rec_ccr_vitalsign_tests")
@NamedQueries({
    @NamedQuery(name = "CcrVitalSignTest.findById", query = "SELECT m FROM CcrVitalSignTest m  WHERE  m.id =  :Id "),
    @NamedQuery(name = "CcrVitalSignTest.findByHealthRecordId", query = "SELECT m FROM CcrVitalSignTest m JOIN m.ccrDocument c JOIN c.healthRecords h WHERE  h.id =  :healthRecordId "),
    @NamedQuery(name = "CcrVitalSignTest.countByHealthRecordId", query = "SELECT COUNT(m) FROM CcrVitalSignTest m JOIN m.ccrDocument c JOIN c.healthRecords h WHERE h.id= :healthRecordId"),
    @NamedQuery(name = "CcrVitalSignTest.countByHealthRecordIdAndVitalType", query = "SELECT COUNT(m) FROM CcrVitalSignTest m JOIN m.ccrDocument c JOIN c.healthRecords h WHERE h.id = :healthRecordId AND m.descriptionText = :vitalType"),
    @NamedQuery(name = "CcrVitalSignTest.findByHealthRecordIdAndVitalType", query = "SELECT m FROM CcrVitalSignTest m JOIN m.ccrDocument c JOIN c.healthRecords h WHERE h.id = :healthRecordId AND m.descriptionText = :vitalType")
})
@AttributeOverride(name = "generic_id", column =
@Column(name = "ccr_vitalsign_test_id"))
public class CcrVitalSignTest extends GenericResultTest implements Serializable {

    private static final long serialVersionUID = 20091128L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ccr_vitalsign_test_id")
    protected Long id;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_vitalsign_tests_datetime",
    joinColumns = {
        @JoinColumn(name = "ccr_vitalsign_test_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "datetime_id")})
    protected List<CcrDateTime> dateTime;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "vitalSignTest")
    protected List<CcrVitalSignTestSource> sources;

    //maintain Id field integrity and ensure the mapping to parent result isn't updatable
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CcrVitalSignResult.class)
    @JoinColumn(name = "ccr_vitalsign_test_id", nullable = false, insertable = false, updatable = false)
    protected CcrVitalSignResult ccrResult;

    @Transient
    private Date exactDateTime;

    @Transient
    private String source;

    public List<CcrVitalSignTestSource> getSources() {
        return sources;
    }

    public void setSources(List<CcrVitalSignTestSource> sources) {
        this.sources = sources;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CcrDateTime> getDateTime() {
        return dateTime;
    }

    public void setDateTime(List<CcrDateTime> dateTime) {
        this.dateTime = dateTime;
    }

    //custom getters
    //note Date Added custom getter has been extracted to parent class
    public Date getExactDateTime() {
        if (exactDateTime == null) {
            Date tempDateTime = new Date();
            try {
                tempDateTime = dateTime.get(0).getExactDateTime();
            }
            catch (Exception ex) {
                tempDateTime = null;
            }

            //no test-level dateTime, pull from parent Result entity
            if (tempDateTime == null) {
                try {
                    tempDateTime = ccrResult.getExactDateTime();
                }
                catch (Exception ex) {
                    tempDateTime = null;
                }
            }

            exactDateTime = tempDateTime;
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CcrVitalSignTest)) {
            return false;
        }
        CcrVitalSignTest other = (CcrVitalSignTest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StandardToStringStyle style = new StandardToStringStyle();
        style.setNullText("---NULL---");
        style.setFieldSeparator(";\n");
        style.setArrayStart("{{{");
        style.setArrayEnd("}}}");
        style.setArraySeparator("|");
        style.setFieldNameValueSeparator(":");
        return ToStringBuilder.reflectionToString(this, style);
    }
}
