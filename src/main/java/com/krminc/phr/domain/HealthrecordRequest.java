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

package com.krminc.phr.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author cmccall
 */
@Entity
@Table(name = "rec_healthrecord_requests", catalog = "phr", schema = "")
@NamedQueries({
    @NamedQuery(name = "HealthrecordRequest.findAll", query = "SELECT r FROM HealthrecordRequest r"),
    @NamedQuery(name = "HealthrecordRequest.findByRequestId", query = "SELECT r FROM HealthrecordRequest r WHERE r.requestId = :requestId"),
    @NamedQuery(name = "HealthrecordRequest.findByRecIdRequested", query = "SELECT r FROM HealthrecordRequest r WHERE r.recIdRequested = :recIdRequested"),
    @NamedQuery(name = "HealthrecordRequest.findByUserIdRequestor", query = "SELECT r FROM HealthrecordRequest r WHERE r.userIdRequestor = :userIdRequestor")})
public class HealthrecordRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "request_id", nullable = false)
    private Integer requestId;
    @Basic(optional = false)
    @Column(name = "rec_id_requested", nullable = false)
    private int recIdRequested;
    @Basic(optional = false)
    @Column(name = "user_id_requestor", nullable = false)
    private int userIdRequestor;

    public HealthrecordRequest() {
    }

public HealthrecordRequest(int recIdRequested, int userIdRequestor) {
        this.recIdRequested = recIdRequested;
        this.userIdRequestor = userIdRequestor;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public int getRecIdRequested() {
        return recIdRequested;
    }

    public void setRecIdRequested(int recIdRequested) {
        this.recIdRequested = recIdRequested;
    }

    public int getUserIdRequestor() {
        return userIdRequestor;
    }

    public void setUserIdRequestor(int userIdRequestor) {
        this.userIdRequestor = userIdRequestor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestId != null ? requestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HealthrecordRequest)) {
            return false;
        }
        HealthrecordRequest other = (HealthrecordRequest) object;
        if ((this.requestId == null && other.requestId != null) || (this.requestId != null && !this.requestId.equals(other.requestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.krminc.phr.domain.HealthrecordRequests[requestId=" + requestId + "]";
    }

}
