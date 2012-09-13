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

package com.krminc.phr.api.converter;

import com.krminc.phr.domain.Exercise;
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

@XmlRootElement(name = "exercises")
public class ExercisesConverter {
    private Collection<Exercise> entities;
    private Collection<ExerciseConverter> items;
    private URI uri;
    private int expandLevel;
  
    /** Creates a new instance of ExercisesConverter */
    public ExercisesConverter() {
    }

    /**
     * Creates a new instance of ExercisesConverter.
     *
     * @param entities associated entities
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ExercisesConverter(Collection<Exercise> entities, URI uri, int expandLevel) {
        this.entities = entities;
        this.uri = uri;
        this.expandLevel = expandLevel;
        getExercise();
    }

    /**
     * Returns a collection of ExerciseConverter.
     *
     * @return a collection of ExerciseConverter
     */
    @XmlElement
    public Collection<ExerciseConverter> getExercise() {
        if (items == null) {
            items = new ArrayList<ExerciseConverter>();
        }
        if (entities != null) {
            items.clear();
            for (Exercise entity : entities) {
                items.add(new ExerciseConverter(entity, uri, expandLevel, true));
            }
        }
        return items;
    }

    /**
     * Sets a collection of ExerciseConverter.
     *
     * @param a collection of ExerciseConverter to set
     */
    public void setExercise(Collection<ExerciseConverter> items) {
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
     * Returns a collection Exercise entities.
     *
     * @return a collection of Exercise entities
     */
    @XmlTransient
    public Collection<Exercise> getEntities() {
        entities = new ArrayList<Exercise>();
        if (items != null) {
            for (ExerciseConverter item : items) {
                entities.add(item.getEntity());
            }
        }
        return entities;
    }
}
