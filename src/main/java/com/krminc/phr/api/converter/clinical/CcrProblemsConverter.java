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

import com.krminc.phr.domain.clinical.CcrProblem;
import java.net.URI;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;

/**
 *
 * @author cmccall
 */

@XmlRootElement(name = "ccrProblems")
public class CcrProblemsConverter {
    private Collection<CcrProblem> entities;
    private Collection<CcrProblemConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of CcrProblemsConverter */
    public CcrProblemsConverter() {
    }

    /**
     * Creates a new instance of CcrProblemsConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public CcrProblemsConverter(Collection<CcrProblem> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getCcrProblem();
    }

    /**
     * Returns a collection of CcrProblemConverter.
     *
     * @return a collection of CcrProblemConverter
     */
    @XmlElement
    public Collection<CcrProblemConverter> getCcrProblem() {
        if (items == null) {
            items = new ArrayList<CcrProblemConverter>();
        }
        if (entities != null) {
            items.clear();
            for (CcrProblem entity : entities) {
                items.add(new CcrProblemConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of CcrProblemConverter.
     *
     * @param a collection of CcrProblemConverter to set
     */
    public void setCcrProblem(Collection<CcrProblemConverter> items) {
        this.items = items;
    }

    /**
     * Returns the URI associated with this converter.
     *
     * @return the uri
     */
    @XmlAttribute
    public URI getUri() {
        return uri;
    }

    /**
     * Returns a collection CcrProblem entities.
     *
     * @return a collection of CcrProblem entities
     */
    @XmlTransient
    public Collection<CcrProblem> getEntities() {
        entities = new ArrayList<CcrProblem>();
        if (items != null) {
            for (CcrProblemConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
