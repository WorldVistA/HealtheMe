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
package com.krminc.phr.api.service;

import com.sun.jersey.api.core.ResourceContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * API Controller
 *
 * @author dshaw
 */
@Path("/api/")
public class Api {

    final Logger logger = LoggerFactory.getLogger(Api.class);

    @Context
    protected ResourceContext resourceContext;

    public static final int DEFAULT_EXPAND_LEVEL = 1;


    public Api() {
    }


    /**
     * Returns a dynamic instance of PatientsResource used for entity navigation.
     *
     * @return an instance of PatientsResource
     */
    @Path("patients/")
    public HealthRecordsResource getHealthRecordsResource() {
        HealthRecordsResource resource = resourceContext.getResource(HealthRecordsResource.class);
        return resource;
    }

    /**
     * Returns a dynamic instance of UsersResource used for entity navigation.
     *
     * @return an instance of UsersResource
     */
//    @RolesAllowed("ROLE_ADMIN")
    @Path("users/")
    public UsersResource getUsersResource() {
        UsersResource resource = resourceContext.getResource(UsersResource.class);
        return resource;
    }

    /**
     * Returns a dynamic instance of CareTakerUsersResource used for entity navigation.
     *
     * @return an instance of CareTakerUsersResource
     */
//    @RolesAllowed("ROLE_CARETAKER")
    @Path("care/")
    public CareTakerUsersResource getCareTakerUsersResource() {
        CareTakerUsersResource resource = resourceContext.getResource(CareTakerUsersResource.class);
        return resource;
    }

    @Path("session/")
    public SessionRefresh getSessionRefresh() {
        SessionRefresh resource = resourceContext.getResource(SessionRefresh.class);
        return resource;
    }

}
