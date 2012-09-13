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
package com.krminc.phr.web.form;

import com.sun.jersey.api.core.ResourceContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Form Processor Controller
 *
 * @author Daniel Shaw (dshaw.com)
 */
@Path("/form/")
public class Form {

    final Logger logger = LoggerFactory.getLogger(Form.class);

    @Context
    protected ResourceContext resourceContext;


    public Form() {
    }


    /**
     * Returns a dynamic instance of UserFormProcessor used for entity navigation.
     *
     * @return an instance of UserFormProcessor
     */
//    @RolesAllowed({ ROLE.ADMIN })
    @Path("users/")
    public UserFormProcessor getUserFormProcessor() {
        UserFormProcessor resource = resourceContext.getResource(UserFormProcessor.class);
        return resource;
    }

    /**
     * Returns a dynamic instance of SelfFormProcessor used for entity navigation.
     * This section is intended to allow patients to manage their own account and is secured as such at the container level.
     *
     * @return an instance of SelfFormProcessor
     */
    @Path("self/")
    public SelfFormProcessor getSelfFormProcessor() {
        SelfFormProcessor resource = resourceContext.getResource(SelfFormProcessor.class);
        return resource;
    }

    /**
     * Returns a dynamic instance of CareNotebookFormProcessor used for entity navigation.
     * This section is intended to allow patients to manage their own care notebook and is secured as such at the container level.
     *
     * @return an instance of CareNotebookFormProcessor
     */
    @Path("care/")
    public CareNotebookFormProcessor getCareNotebookFormProcessor() {
        CareNotebookFormProcessor resource = resourceContext.getResource(CareNotebookFormProcessor.class);
        return resource;
    }

}
