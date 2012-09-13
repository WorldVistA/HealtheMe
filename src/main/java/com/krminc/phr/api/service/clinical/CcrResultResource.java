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

package com.krminc.phr.api.service.clinical;

import com.krminc.phr.api.converter.clinical.CcrResultConverter;
import com.krminc.phr.api.service.Api;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.clinical.CcrResult;
import com.krminc.phr.domain.clinical.CcrResultTest;
import java.util.Collection;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import com.sun.jersey.api.core.ResourceContext;
import javax.ws.rs.WebApplicationException;
import javax.persistence.NoResultException;
import javax.persistence.EntityManager;

/**
 *
 * @author cmccall
 */

public class CcrResultResource {
    @Context
    protected UriInfo uriInfo;
    @Context
    protected ResourceContext resourceContext;
    protected Long id;
  
    /** Creates a new instance of CcrResultResource */
    public CcrResultResource() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get method for retrieving an instance of CcrResult identified by id in XML format.
     *
     * @param id identifier for the entity
     * @return an instance of CcrResultConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public CcrResultConverter get() {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new CcrResultConverter(getEntity(), uriInfo.getAbsolutePath(), Api.DEFAULT_EXPAND_LEVEL);
        } finally {
            PersistenceService.getInstance().close();
        }
    }

    /**
     * Returns an instance of CcrResult identified by id.
     *
     * @param id identifier for the entity
     * @return an instance of CcrResult
     */
    protected CcrResult getEntity() {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        try {
            return (CcrResult) em.createQuery("SELECT e FROM CcrResult e where e.id = :id").setParameter("id", id).getSingleResult();
        } catch (NoResultException ex) {
            throw new WebApplicationException(new Throwable("Resource for " + uriInfo.getAbsolutePath() + " does not exist."), 404);
        }
    }

    /**
     * Returns a dynamic instance of CcrResultTestsResource used for entity navigation.
     *
     * @param id identifier for the parent entity
     * @return an instance of CcrResultTestsResource
     */
    @Path("resultTests/")
    public CcrResultTestsResource getResultTestsResource() {
        ResultTestsResourceSub resource = resourceContext.getResource(ResultTestsResourceSub.class);
        resource.setParent(getEntity());
        return resource;
    }

    public static class ResultTestsResourceSub extends CcrResultTestsResource {

        private CcrResult parent;

        public void setParent(CcrResult parent) {
            this.parent = parent;
        }

        protected Collection<CcrResultTest> getEntities(int start, int max, String query) {
            Collection<CcrResultTest> result = new java.util.ArrayList<CcrResultTest>();
            int index = 0;
            for (CcrResultTest e : parent.getResultTests()) {
                if (index >= start && (index - start) < max) {
                    result.add(e);
                }
                index++;
            }
            return result;
        }
    }
}
