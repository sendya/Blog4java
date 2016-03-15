/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.loacg.web.servlet;

import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Project: blog4java
 * Author: Sendya <18x@loacg.com>
 * Time: 2016/3/15 22:32
 */
public class SecureDispatcherServlet extends DispatcherServlet {
    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String url = request.getRequestURI();
        if(url.endsWith(".jsp")) {
            getServletContext().getRequestDispatcher("/WEB-INF/404.html").forward(request, response);
            return;
        }

        String contextPath= request.getContextPath();
        contextPath = contextPath.endsWith("/")? contextPath : contextPath + "/";
        if(url.startsWith(contextPath + "page/")){
            if(url.endsWith(".html")) {
                if(!verifyPrivilege(request, response)){
                    response.sendRedirect(request.getContextPath() + "/login.html");
                    return;
                }
                url = url.substring(contextPath.length() - 1);
                url = url.replace(".html", ".jsp");
                getServletContext().getRequestDispatcher("/WEB-INF" + url).forward(request, response);
                return;
            }
            else if(url.endsWith(".html.js")) {
                url = url.substring(contextPath.length() - 1);
                url = url.replace(".html", ".jsp");
                getServletContext().getRequestDispatcher("/WEB-INF" + url).forward(request, response);
                return;
            }
        }

        if(url.endsWith(".html")) {
            url = url.substring(contextPath.length() - 1);
            url = url.replace(".html", ".jsp");
            getServletContext().getRequestDispatcher("/WEB-INF" + url).forward(request, response);
            return;
        }


        super.doService(request,response);
    }

    private boolean verifyPrivilege(HttpServletRequest request, HttpServletResponse response) {

        return true;
    }
}
