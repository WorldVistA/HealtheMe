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
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author chaz
 */
@Entity
@Table(name = "phr.rec_ccr_result_tests")

@NamedQueries({
    @NamedQuery(name = "CcrResultTest.findById", query = "SELECT m FROM CcrResultTest m  WHERE  m.id =  :Id "),

    @NamedQuery(name = "CcrResultTest.findByHealthRecordId", query = "SELECT m FROM CcrResultTest m JOIN m.ccrDocument c  JOIN c.healthRecords h WHERE  h.id =  :healthRecordId "),
    @NamedQuery(name = "CcrResultTest.countByHealthRecordId", query = "SELECT COUNT(m) FROM CcrResultTest m JOIN m.ccrDocument c JOIN c.healthRecords h WHERE h.id= :healthRecordId")

})
public class CcrResultTest extends GenericResultTest implements Serializable {

    private static final long serialVersionUID = 20091128L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ccr_results_test_id")
    protected Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_result_tests_datetime",
    joinColumns = {
        @JoinColumn(name = "ccr_results_test_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "datetime_id")})
    protected List<CcrDateTime> dateTime;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy="resultTest")

    protected List <CcrResultTestSource> sources;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CcrResultTestSource> getSources() {
        return sources;
    }

    public void setSources(List<CcrResultTestSource> sources) {
        this.sources = sources;
    }


    public List<CcrDateTime> getDateTime() {
        return dateTime;
    }

    public void setDateTime(List<CcrDateTime> dateTime) {
        this.dateTime = dateTime;
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
        if (!(object instanceof CcrResultTest)) {
            return false;
        }
        CcrResultTest other = (CcrResultTest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
