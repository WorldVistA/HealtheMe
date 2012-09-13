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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.StandardToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author chaz
 */
@Entity
@Table(name = "phr.rec_ccr_actors")
public class CcrActor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ccr_actor_id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "ccr_actor_type")
    protected String ccrActorType;
    @Column(name = "ccr_actorlink_actorid")
    protected String ccrActorLinkActorId;
    @Column(name = "ccr_tree_node")
    protected String ccrTreeNode;
    @Column(name = "actor_object_id")
    protected String actorObjectId;
    @Column(name = "person_name_currentname_given")
    protected String personNameCurrentNameGiven;
    @Column(name = "person_name_currentname_family")
    protected String personNameCurrentNameFamily;
    @Column(name = "person_name_currentname_title")
    protected String personNameCurrentNameTitle;
    @Column(name = "person_name_currentname_middle")
    protected String personNameCurrentNameMiddle;
    @Column(name = "source_actor_id")
    protected String sourceActorId;
    @Column(name = "specialty_text")
    protected String specialityText;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "person_dob_exactdatetime")
    protected Date personDobExactDateTime;
    @Column(name = "person_gender_text")
    protected String personGenderText;
    @Column(name = "information_system_name")
    protected String informationSystemName;
    @Column(name = "information_system_version")
    protected String informationSystemVersion;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rec_ccr_document_actors",
    joinColumns = {
        @JoinColumn(name = "ccr_id")},
    inverseJoinColumns = {
        @JoinColumn(name = "ccr_actor_id")})
    protected List<CcrDocument> ccrDocument;
    @Column(name="source_map_id")
    protected Long sourceMapId;

    public Long getSourceMapId() {
        return sourceMapId;
    }

    public void setSourceMapId(Long sourceMapId) {
        this.sourceMapId = sourceMapId;
    }


    public String getCcrActorLinkActorId() {
        return ccrActorLinkActorId;
    }

    public void setCcrActorLinkActorId(String ccrActorLinkActorId) {
        this.ccrActorLinkActorId = ccrActorLinkActorId;
    }

    public String getCcrActorType() {
        return ccrActorType;
    }

    public void setCcrActorType(String ccrActorType) {
        this.ccrActorType = ccrActorType;
    }

    public String getCcrTreeNode() {
        return ccrTreeNode;
    }

    public void setCcrTreeNode(String ccrTreeNode) {
        this.ccrTreeNode = ccrTreeNode;
    }

    public String getActorObjectId() {
        return actorObjectId;
    }

    public void setActorObjectId(String actorObjectId) {
        this.actorObjectId = actorObjectId;
    }

    public String getInformationSystemName() {
        return informationSystemName;
    }

    public void setInformationSystemName(String informationSystemName) {
        this.informationSystemName = informationSystemName;
    }

    public String getInformationSystemVersion() {
        return informationSystemVersion;
    }

    public void setInformationSystemVersion(String informationSystemVersion) {
        this.informationSystemVersion = informationSystemVersion;
    }

    public Date getPersonDobExactDateTime() {
        return personDobExactDateTime;
    }

    public void setPersonDobExactDateTime(Date personDobExactDateTime) {
        this.personDobExactDateTime = personDobExactDateTime;
    }

    public String getPersonGenderText() {
        return personGenderText;
    }

    public void setPersonGenderText(String personGenderText) {
        this.personGenderText = personGenderText;
    }

    public String getPersonNameCurrentNameFamily() {
        return personNameCurrentNameFamily;
    }

    public void setPersonNameCurrentNameFamily(String personNameCurrentNameFamily) {
        this.personNameCurrentNameFamily = personNameCurrentNameFamily;
    }

    public String getPersonNameCurrentNameGiven() {
        return personNameCurrentNameGiven;
    }

    public void setPersonNameCurrentNameGiven(String personNameCurrentNameGiven) {
        this.personNameCurrentNameGiven = personNameCurrentNameGiven;
    }

    public String getPersonNameCurrentNameTitle() {
        return personNameCurrentNameTitle;
    }

    public void setPersonNameCurrentNameTitle(String personNameCurrentNameTitle) {
        this.personNameCurrentNameTitle = personNameCurrentNameTitle;
    }

    public String getPersonNameCurrentNameMiddle() {
        return personNameCurrentNameMiddle;
    }

    public void setPersonNameCurrentNameMiddle(String personNameCurrentNameMiddle) {
        this.personNameCurrentNameMiddle = personNameCurrentNameMiddle;
    }

    public List<CcrDocument> getCcrDocument() {
        return ccrDocument;
    }

    public void setCcrDocument(List<CcrDocument> ccrDocument) {
        this.ccrDocument = ccrDocument;
    }

    public String getSourceActorId() {
        return sourceActorId;
    }

    public void setSourceActorId(String sourceActorId) {
        this.sourceActorId = sourceActorId;
    }

    public String getSpecialityText() {
        return specialityText;
    }

    public void setSpecialityText(String specialityText) {
        this.specialityText = specialityText;
    }

    //custom
    public String getAsFullName(){
        StringBuilder fullName = new StringBuilder();

        String firstName = getPersonNameCurrentNameGiven();
        String middleName = getPersonNameCurrentNameMiddle();
        String lastName = getPersonNameCurrentNameFamily();

        if (firstName != null && firstName.length() > 0)
            fullName.append(firstName);

        if (middleName != null && middleName.length() > 0)
            fullName.append(" ").append(middleName);

        if (lastName != null && lastName.length() > 0)
            fullName.append(" ").append(lastName);
        
        return fullName.toString();
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
        if (!(object instanceof CcrActor)) {
            return false;
        }
        CcrActor other = (CcrActor) object;
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
