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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logout controller.
 *
 * <p>Invalidates user session to log them out.
 * The redirects user to the homepage.</p>
 *
 * @author Daniel Shaw (dshaw.com)
 */
public class Logout {

    final Logger logger = LoggerFactory.getLogger(Logout.class);


    public Logout() {
    }


    @GET
    public Response logoutAuthenticatedUser(@Context UriInfo uriInfo, @Context HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        session.invalidate();
        return Response.seeOther(uriInfo.getBaseUri()).build();
    }

}
