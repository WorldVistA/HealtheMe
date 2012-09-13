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
/**
 * Care Taker Controller
 * @author cmccall
 */

package com.krminc.phr.web;

import com.krminc.phr.core.UserConfig;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.User;
import com.krminc.phr.domain.clinical.Resource;
import com.sun.jersey.api.core.ResourceContext;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import org.apache.log4j.Logger;

public class CareTaker {

    private static final Logger logger = Logger.getLogger(CareTaker.class);

    @Context
    protected UriInfo uriInfo;
    @Context
    protected ResourceContext resourceContext;
    @Context
    protected SecurityContext securityContext;

    // TODO: Remove this list and use api
    private List<User> users = null;


    public CareTaker() {
    }

    /**
     * @return list of active Resources for the system.
     */
    public List<Resource> getResources() {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            return em.createNamedQuery("Resource.findByActive").getResultList();
        } catch (NoResultException ex) {
            throw new WebApplicationException(
                new Throwable("No resources exist for " + uriInfo.getAbsolutePath()),
                Response.Status.NO_CONTENT
            );
        }
    }

    /**
     * Returns a map of Security Roles
     *
     * @return roles
     */
    public Map<String, String> getRoles() {
        return UserConfig.getRoles();
    }

    @Path("users/")
    public List<User> getUsers() {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            return em.createNamedQuery("User.findAll").getResultList();
        } catch (NoResultException ex) {
            throw new WebApplicationException(
                new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."),
                Response.Status.NOT_FOUND
            );
        }
    }

    @Path("users/{id}/")
    public User getUser(@PathParam("id") Long id) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            return em.find(User.class, id);
        } catch (NoResultException ex) {
            throw new WebApplicationException(
                new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."),
                Response.Status.NOT_FOUND
            );
        }
    }

}
