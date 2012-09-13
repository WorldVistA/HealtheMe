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

package com.krminc.phr.api.converter.clinical;

import com.krminc.phr.domain.clinical.GenericResultTest;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author cmccall
 */

@XmlRootElement(name = "genericResultTest")
public class GenericResultTestConverter {
    protected GenericResultTest localEntity;
  
    /** Creates a new instance of GenericResultTestConverter */
    public GenericResultTestConverter() {
        localEntity = new GenericResultTest();
    }


    /**
     * Creates a new instance of GenericResultTestConverter.
     *
     * @param entity associated entity
     */
    public GenericResultTestConverter(GenericResultTest entity) {
        localEntity = entity;
    }

//    /**
//     * Getter for dataObjectId.
//     *
//     * @return value for dataObjectId
//     */
//    @XmlElement
//    public String getDataObjectId() {
//        return localEntity.getDataObjectId();
//    }

    /**
     * Getter for descriptionCode.
     *
     * @return value for descriptionCode
     */
    @XmlElement
    public String getDescriptionCode() {
        return localEntity.getDescriptionCode();
    }

    /**
     * Getter for descriptionCodingSystem.
     *
     * @return value for descriptionCodingSystem
     */
    @XmlElement
    public String getDescriptionCodingSystem() {
        return localEntity.getDescriptionCodingSystem();
    }

    /**
     * Getter for descriptionText.
     *
     * @return value for descriptionText
     */
    @XmlElement
    public String getTestDescriptionText() {
        return localEntity.getDescriptionText();
    }

    /**
     * Getter for displayName.
     *
     * @return value for displayName
     */
    @XmlElement
    public String getDisplayName() {
        return localEntity.getDisplayName();
    }

    /**
     * Getter for internalResultName.
     *
     * @return value for internalResultName
     */
    @XmlElement
    public String getInternalResultName() {
        return localEntity.getInternalResultName();
    }

    /**
     * Getter for testResultValue.
     *
     * @return value for testResultValue
     */
    @XmlElement
    public String getTestResultValue() {
        return localEntity.getTestResultValue();
    }

    /**
     * Getter for testResultUnit.
     *
     * @return value for testResultUnit
     */
    @XmlElement
    public String getTestResultUnit() {
        return localEntity.getTestResultUnit();
    }

    /**
     * Getter for normalResultDescription.
     *
     * @return value for normalResultDescription
     */
    @XmlElement
    public String getNormalResultDescription() {
        return localEntity.getNormalResultDescription();
    }

    /**
     * Getter for flagText.
     *
     * @return value for flagText
     */
    @XmlElement
    public String getFlagText() {
        return localEntity.getFlagText();
    }

    /**
     * Getter for notes.
     *
     * @return value for notes
     */
    @XmlElement
    public String getNotes() {
        return localEntity.getNotes();
    }
}
