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

package com.krminc.phr.core;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author cmccall
 */
public class LoginFilter implements javax.servlet.Filter {

    final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    private String jspPath = null;
    private String sessionResetFlag = null;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        boolean needsResetRedirect = false;
        HttpSession session = null;
        HttpServletRequest httpRequest = null;

        if (request instanceof HttpServletRequest){
            httpRequest = (HttpServletRequest)request;
            session = httpRequest.getSession(false);

            if (httpRequest.isUserInRole(UserConfig.ROLE_RESET) && httpRequest.getMethod().equalsIgnoreCase("GET") && session != null) {
                String reqURI = httpRequest.getRequestURI();

                if (!reqURI.contains(jspPath) && !reqURI.contains("static") && !reqURI.contains("logout") && !reqURI.contains("error")) {
                    Object flag = session.getAttribute(sessionResetFlag);
                    if (flag != null){
                        if (!flag.toString().contains("false")) needsResetRedirect = true;
                    } else {
                        needsResetRedirect = true;
                    }
                }
            }
        }

        if (needsResetRedirect) {
            ((HttpServletResponse)response).sendRedirect(httpRequest.getContextPath() + "/" + jspPath);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig fc) throws ServletException {
        jspPath = fc.getInitParameter("jsppath");
        sessionResetFlag = fc.getInitParameter("sessionresetflag");
    }

    public void destroy(){
        jspPath = null;
        sessionResetFlag = null;
    }

}
