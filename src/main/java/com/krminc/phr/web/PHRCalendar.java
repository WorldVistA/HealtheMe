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
package com.krminc.phr.web;

import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.Exercise;
import com.krminc.phr.domain.HealthRecord;
import com.krminc.phr.domain.Visit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TemporalType;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PHR Calendar
 *
 * @author cmccall
 * @author Daniel Shaw (dshaw.com)
 */
public class PHRCalendar {

    private Long healthRecordId;
    private HealthRecord healthRecord = null;

    final Logger logger = LoggerFactory.getLogger(PHRCalendar.class);

    private int year;
    private int month; // 1-12

    public PHRCalendar() {
        // default constructor
    }

    public PHRCalendar(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
        this.healthRecord = lookupHealthRecord(this.healthRecordId);
        // TODO: Is there a way we can do this without creating an object?
        DateTime now = new DateTime();
        this.year = now.getYear();
        this.month = now.getMonthOfYear();
    }

    public PHRCalendar(Long healthRecordId, int year) {
        this.healthRecordId = healthRecordId;
        this.healthRecord = lookupHealthRecord(this.healthRecordId);
        this.year = year;
    }

    public PHRCalendar(Long healthRecordId, int year, int month) {
        this.healthRecordId = healthRecordId;
        this.healthRecord = lookupHealthRecord(this.healthRecordId);
        this.year = year;
        this.month = month;
    }

    private final HealthRecord lookupHealthRecord(Long healthRecordId) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        EntityManager em = persistenceSvc.getEntityManager();
        try {
            return (HealthRecord) em.find(HealthRecord.class, healthRecordId);
        } catch (NoResultException ex) {
            logger.error("HealthRecord for id: {} does not exist.", healthRecordId, ex);
            return new HealthRecord();
//            try {
//                persistenceSvc.beginTx();
//                em.persist(user);
//                persistenceSvc.commitTx();
//            } finally {
//                persistenceSvc.close();
//            }
        }
        
    }

    public HealthRecord getHealthRecord() {
        return healthRecord;
    }

    public Long getHealthRecordId() {
        return healthRecordId;
    }

    public void setHealthRecordId(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getNextMonthsYear() {
        if (this.month >= 12) {
            return this.year+1;
        } else {
            return this.year;
        }
    }

    public int getNextMonthsMonth() {
        if (this.month >= 12) {
            return 1;
        } else {
            return this.month+1;
        }
    }

    public int getPrevMonthsYear() {
        if (this.month <= 1) {
            return this.year-1;
        } else {
            return this.year;
        }
    }

    public int getPrevMonthsMonth() {
        if (this.month <= 1) {
            return 12;
        } else {
            return this.month-1;
        }
    }

    public Map<Integer,List<Visit>> getVisits() {
        List<Visit> visits = Collections.EMPTY_LIST;
        Map<Integer,List<Visit>> visitsMap = new HashMap<Integer, List<Visit>>();
        if ( this.healthRecordId == null || this.healthRecord.getHealthRecordId() == null) return visitsMap;

        EntityManager em = PersistenceService.getInstance().getEntityManager();

        try {
            Calendar start = Calendar.getInstance();
            start.set(year, month-1,1);
            Calendar end = Calendar.getInstance();
            end.set(year, month-1, 31);
            // TODO: move query to Visit as NamedQuery.
            visits = em.createQuery("SELECT v FROM Visit v WHERE v.healthRecordId = :healthRecordId AND v.visitDate BETWEEN :start AND :end")
                    .setParameter("healthRecordId", this.healthRecordId)
                    .setParameter("start", start, TemporalType.DATE)
                    .setParameter("end", end, TemporalType.DATE)
                    .getResultList();
        } finally {
            PersistenceService.getInstance().close();
        }
        
        for(Visit v : visits) {
            int visitDate = v.getVisitDate().getDate();
            if (!visitsMap.containsKey(visitDate)) {
                ArrayList<Visit> visitList = new ArrayList<Visit>();
                visitList.add(v);
                visitsMap.put(visitDate, visitList);
            } else {
                visitsMap.get(visitDate).add(v);
            }
        }

        return visitsMap;
    }

    public Map<Integer,List<Exercise>> getExercises() {
        List<Exercise> exercises = Collections.EMPTY_LIST;
        Map<Integer,List<Exercise>> exercisesMap = new HashMap<Integer, List<Exercise>>();
        if ( this.healthRecordId == null || this.healthRecord.getHealthRecordId() == null) return exercisesMap;

        EntityManager em = PersistenceService.getInstance().getEntityManager();

        try {
            Calendar start = Calendar.getInstance();
            start.set(year, month-1,1);
            Calendar end = Calendar.getInstance();
            end.set(year, month-1, 31);
            // TODO: move query to Exercise as NamedQuery.
            exercises = em.createQuery("SELECT v FROM Exercise v WHERE v.healthRecordId = :healthRecordId AND v.exerciseDate BETWEEN :start AND :end")
                    .setParameter("healthRecordId", this.healthRecordId)
                    .setParameter("start", start, TemporalType.DATE)
                    .setParameter("end", end, TemporalType.DATE)
                    .getResultList();
        } finally {
            PersistenceService.getInstance().close();
        }

        for(Exercise v : exercises) {
            int exerciseDate = v.getExerciseDate().getDate();
            if (!exercisesMap.containsKey(exerciseDate)) {
                ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();
                exerciseList.add(v);
                exercisesMap.put(exerciseDate, exerciseList);
            } else {
                exercisesMap.get(exerciseDate).add(v);
            }
        }

        return exercisesMap;
    }

}
