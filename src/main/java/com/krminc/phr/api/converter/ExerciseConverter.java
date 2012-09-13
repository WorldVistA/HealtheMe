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

import com.krminc.phr.api.converter.util.ConverterUtils;
import com.krminc.phr.domain.Exercise;
import com.krminc.phr.domain.HealthRecord;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.net.URI;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.ws.rs.core.UriBuilder;
import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cmccall
 */

@XmlRootElement(name = "exercise")
public class ExerciseConverter {
    private Exercise entity;
    private URI uri;
    private int expandLevel;

    final Logger logger = LoggerFactory.getLogger(ExerciseConverter.class);
    public boolean hasError = false;
  
    /** Creates a new instance of ExerciseConverter */
    public ExerciseConverter() {
        entity = new Exercise();
    }

    /**
     * Creates a new instance of ExerciseConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded@param isUriExtendable indicates whether the uri can be extended
     */
    public ExerciseConverter(Exercise entity, URI uri, int expandLevel, boolean isUriExtendable) {
        this.entity = entity;
        this.uri = (isUriExtendable) ? UriBuilder.fromUri(uri).path(entity.getExerciseId() + "/").build() : uri;
        this.expandLevel = expandLevel;
    }

    /**
     * Creates a new instance of ExerciseConverter.
     *
     * @param entity associated entity
     * @param uri associated uri
     * @param expandLevel indicates the number of levels the entity graph should be expanded
     */
    public ExerciseConverter(Exercise entity, URI uri, int expandLevel) {
        this(entity, uri, expandLevel, false);
    }

    /**
     * Getter for exerciseId.
     *
     * @return value for exerciseId
     */
    @XmlElement
    public Long getExerciseId() {
        return (expandLevel > 0) ? entity.getExerciseId() : null;
    }

    /**
     * Setter for exerciseId.
     *
     * @param value the value to set
     */
    public void setExerciseId(Long value) {
        try {
            entity.setExerciseId(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for title.
     *
     * @return value for title
     */
    @XmlElement
    public String getTitle() {
        return (expandLevel > 0) ? entity.getTitle() : null;
    }

    /**
     * Setter for title.
     *
     * @param value the value to set
     */
    public void setTitle(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            if (value.length() > 0 && value.length() <= 255) {
                entity.setTitle(value);
            } else {
                throw new Exception();
            }
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for exerciseDate.
     *
     * @return value for exerciseDate
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getExerciseDate() {
        return (expandLevel > 0) ? entity.getExerciseDate() : null;
    }

    /**
     * Setter for exerciseDate.
     *
     * @param value the value to set
     */
    public void setExerciseDate(Date value) {
        try {
            entity.setExerciseDate(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for exerciseTime.
     *
     * @return value for exerciseTime
     */
    @XmlElement
    @XmlJavaTypeAdapter(TimeAdapter.class)
    public Date getExerciseTime() {
        return (expandLevel > 0) ? entity.getExerciseTime() : null;
    }

    /**
     * Setter for exerciseTime.
     *
     * @param value the value to set
     */
    public void setExerciseTime(Date value) {
        try {
            entity.setExerciseTime(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for exerciseDurationHours.
     *
     * @return value for exerciseDurationHours
     */
    @XmlElement
    public String getExerciseDurationHours() {
        //format with leading 0
        if (expandLevel > 0) {
            String format = String.format("%%0%dd", 1);
            return String.format(format,  entity.getExerciseDurationHours());
        }
        return null;
    }

    /**
     * Setter for exerciseDurationHours.
     *
     * @param value the value to set
     */
    public void setExerciseDurationHours(String value) {
        try {
            entity.setExerciseDurationHours(Long.parseLong(value));
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for exerciseDurationMinutes.
     *
     * @return value for exerciseDurationMinutes
     */
    @XmlElement
    public String getExerciseDurationMinutes() {
        //return (expandLevel > 0) ? entity.getExerciseDurationMinutes() : null;
        //format with leading 0
        if (expandLevel > 0) {
            String format = String.format("%%0%dd", 2);
            return String.format(format,  entity.getExerciseDurationMinutes());
        }
        return null;
    }

    /**
     * Setter for exerciseDurationMinutes.
     *
     * @param value the value to set
     */
    public void setExerciseDurationMinutes(String value) {
        try {
            entity.setExerciseDurationMinutes(Long.parseLong(value));
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for comments.
     *
     * @return value for comments
     */
    @XmlElement
    public String getComments() {
        return (expandLevel > 0) ? entity.getComments() : null;
    }

    /**
     * Setter for comments.
     *
     * @param value the value to set
     */
    public void setComments(String value) {
        try {
            value = ConverterUtils.prepareInput(value);
            entity.setComments(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for healthRecordId.
     *
     * @return value for healthRecordId
     */
    @XmlElement
    public Long getHealthRecordId() {
        return (expandLevel > 0) ? entity.getHealthRecordId() : null;
    }

    /**
     * Setter for HealthRecordId.
     *
     * @param value the value to set
     */
    public void setHealthRecordId(Long value) {
        try {
            entity.setHealthRecordId(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for dateAdded.
     *
     * @return value for dateAdded
     */
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getDateAdded() {
        return (expandLevel > 0) ? entity.getDateAdded() : null;
    }

    /**
     * Getter for sourceId.
     *
     * @return value for sourceId
     */
    @XmlElement
    public Long getSourceId() {
        return (expandLevel > 0) ? entity.getSourceId() : null;
    }

    /**
     * Setter for sourceId.
     *
     * @param value the value to set
     */
    public void setSourceId(Long value) {
        try {
            if (value != 1) {
                throw new Exception();
            }
            entity.setSourceId(value);
        }
        catch(Exception ex) {
            hasError = true;
        }
    }

    /**
     * Getter for dataSourceId.
     *
     * @return value for dataSourceId
     */
    @XmlElement
    public Long getDataSourceId() {
        return (expandLevel > 0) ? entity.getDataSourceId() : null;
    }

    /**
     * Setter for dataSourceId.
     *
     * @param value the value to set
     */
    public void setDataSourceId(Long value) {
        entity.setDataSourceId(value);
    }

    /**
     * Getter for mask.
     *
     * @return value for mask
     */
    @XmlElement
    public String getMask() {
        return (expandLevel > 0) ? entity.getMask() : null;
    }

    /**
     * Setter for mask.
     *
     * @param value the value to set
     */
    public void setMask(String value) {
        try {
            if (ConverterUtils.isValidMask(value)) {
                entity.setMask(value.trim());
            } else {
                throw new Exception();
            }
        }
        catch(Exception ex) {
            hasError = true;
        }
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
     * Sets the URI for this reference converter.
     *
     */
    public void setUri(URI uri) {
        this.uri = uri;
    }

    /**
     * Returns the Exercise entity.
     *
     * @return an entity
     */
    @XmlTransient
    public Exercise getEntity() {
        if (entity.getExerciseId() == null) {
            ExerciseConverter converter = UriResolver.getInstance().resolve(ExerciseConverter.class, uri);
            if (converter != null) {
                entity = converter.getEntity();
            }
        }
        return entity;
    }

    /**
     * Returns the resolved Exercise entity.
     *
     * @return an resolved entity
     */
    public Exercise resolveEntity(EntityManager em) {
        HealthRecord healthRecord = entity.getHealthRecord();
        if (healthRecord != null) {
            entity.setHealthRecord(em.getReference(HealthRecord.class, healthRecord.getHealthRecordId()));
        }
        return entity;
    }
}
