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

import com.krminc.phr.api.converter.clinical.CcrDocumentsConverter;
import com.krminc.phr.api.service.Api;
import com.krminc.phr.dao.PersistenceService;
import com.krminc.phr.domain.clinical.CcrDocument;
import java.util.Collection;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import com.sun.jersey.api.core.ResourceContext;
import javax.persistence.EntityManager;

/**
 *
 * @author cmccall
 */

public class CcrDocumentsResource {
    @Context
    protected UriInfo uriInfo;
    @Context
    protected ResourceContext resourceContext;

    protected Long healthRecordId;
  
    /** Creates a new instance of CcrDocumentsResource */
    public CcrDocumentsResource() {
    }

    public void setHealthRecordId(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    /**
     * Get method for retrieving a collection of CcrDocument instance in XML format.
     *
     * @return an instance of CcrDocumentsConverter
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public CcrDocumentsConverter get(
        @QueryParam("start")
        @DefaultValue("0")
        int start,
        @QueryParam("max")
        @DefaultValue("10")
        int max
    ) {
        PersistenceService persistenceSvc = PersistenceService.getInstance();
        try {
            persistenceSvc.beginTx();
            return new CcrDocumentsConverter(getEntities(start, max), uriInfo.getAbsolutePath(), Api.DEFAULT_EXPAND_LEVEL);
        } finally {
            persistenceSvc.commitTx();
            persistenceSvc.close();
        }
    }

    /**
     * Returns a dynamic instance of CcrDocumentResource used for entity navigation.
     *
     * @return an instance of CcrDocumentResource
     */
    @Path("{id}/")
    public CcrDocumentResource getCcrDocumentResource(@PathParam("id")
    Long id) {
        CcrDocumentResource resource = resourceContext.getResource(CcrDocumentResource.class);
        resource.setId(id);
        return resource;
    }

    /**
     * Returns all the entities associated with this resource.
     *
     * @return a collection of CcrDocument instances
     */
    protected Collection<CcrDocument> getEntities(int start, int max) {
        EntityManager em = PersistenceService.getInstance().getEntityManager();
        return em.createNamedQuery("CcrDocument.findByHealthRecordId")
                .setParameter("healthRecordId" , this.healthRecordId)
                .setFirstResult(start)
                .setMaxResults(max)
                .getResultList();
    }
}
