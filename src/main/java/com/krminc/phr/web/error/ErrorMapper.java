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

package com.krminc.phr.web.error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author cmccall
 */
@Provider
public class ErrorMapper implements ExceptionMapper<javax.ws.rs.WebApplicationException>{

    @Context UriInfo uriInfo;

    public Response toResponse(javax.ws.rs.WebApplicationException ex) {
        String msg = "";
        int errorStatus = ex.getResponse().getStatus();

        //passthrough for select error codes
        switch (errorStatus) {
            case 409:
                return ex.getResponse();
        }

        try {
        if (errorStatus > 0) {
            msg =  "?" + Integer.toString(errorStatus);
        }
        }
        catch (Exception exc){
            msg = "";
        }

        return Response
                .seeOther(uriInfo.getBaseUri().resolve("./error/" + msg))
                .entity(ex.getMessage())
                .build();
    }

}
