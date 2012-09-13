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
package com.krminc.phr.web.admin;

import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.User;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * User controller class.
 *
 * Used in Admin and User Manager areas of the application.
 *
 * @author Daniel Shaw (java@dshaw.com)
 */
public class UserWebResource {

    private Long userId;
    private User user;
    // TODO: replace with an injected Admin or UserManager object.
    private String applicationArea;

    public UserWebResource() {
    }

    public UserWebResource(Long userId, String applicationArea) {
        this.userId = userId;
        this.applicationArea = applicationArea;
    }

    public String getApplicationArea() {
        return applicationArea;
    }

    public User getUser() {
        if (user == null) {
            EntityManager em = PersistenceService.getInstance().getEntityManager();
            try {
                user = em.find(User.class, userId);
            } catch (NoResultException ex) {
                throw new WebApplicationException(
                        new Throwable("User identified by " + userId + " does not exist."),
                        Response.Status.NOT_FOUND);
            }
        }
        return user;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserWebResource)) {
            return false;
        }
        UserWebResource other = (UserWebResource) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserWebResource[" + userId + " in " + applicationArea + "]";
    }
}
