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
package com.krminc.phr.api.service.clinical;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * User Specific interactions with Resouces (External Service "Endpoint")
 * API RESTful resource class and mapping.
 *
 * @author Daniel Shaw (dshaw.com)
 */
public class UserResourcesResource extends ResourcesResource {

    protected Long healthRecordId;

    
    public void setHealthRecordId(Long healthRecordId) {
        this.healthRecordId = healthRecordId;
    }

    /**
     * @return a dynamic instance of UserResourceResource used for entity navigation.
     */
    @Override
    @Path("{resourceId}/")
    public UserResourceResource getResourceResource(
        @PathParam("resourceId") Long resourceId
    ) {
        UserResourceResource resource =
            resourceContext.getResource(UserResourceResource.class);
        resource.setResourceId(resourceId);
        resource.setHealthRecordId(healthRecordId);
        return resource;
    }

}
