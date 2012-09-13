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

import com.krminc.phr.domain.clinical.GenericResult;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author cmccall
 */

public class GenericResultConverter {
    protected GenericResult localEntity;
  
    /** Creates a new instance of GenericResultConverter */
    public GenericResultConverter() {
        localEntity = new GenericResult();
    }

    public GenericResultConverter(GenericResult entity) {
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
    public String getDescriptionText() {
        return localEntity.getDescriptionText();
    }
}
