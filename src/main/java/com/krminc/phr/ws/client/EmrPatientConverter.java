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
package com.krminc.phr.ws.client;

import com.krminc.phr.api.converter.UriResolver;
import com.krminc.phr.ws.FMPatient;
import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlTransient;

/**
 * EMR Patient Converter JAXB Object.
 *
 * @author Daniel Shaw (dshaw.com)
 */
public class EmrPatientConverter extends FMPatient {
    private FMPatient entity;

    public EmrPatientConverter() {
        entity = new FMPatient();
    }

    EmrPatientConverter(FMPatient entity) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Returns the User entity.
     *
     * @return an entity
     */
    @XmlTransient
    public FMPatient getEntity() {
//        if (entity.getID() == null) {
//            EmrPatientConverter converter = UriResolver.getInstance().resolve(EmrPatientConverter.class, uri);
//            if (converter != null) {
//                entity = converter.getEntity();
//            }
//        }
        return entity;
    }

    /**
     * Returns the resolved User entity.
     *
     * @return an resolved entity
     */
    public FMPatient resolveEntity(EntityManager em) {
        return entity;
    }
}
