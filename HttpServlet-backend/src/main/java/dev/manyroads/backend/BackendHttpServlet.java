/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.manyroads.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

// ------- Routes -------
/**
 * Class housing REST routes
 */
@WebServlet(urlPatterns = {"/api/backend"})
public class BackendHttpServlet extends HttpServlet {

    // ------- Routes -------
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {

        System.out.println("Route: BackendHttpServlet doGet");

        /*
        //Place the HTTP headers in a Hashmap
        final HashMap<String, String> headers = new HashMap<String, String>();
        Enumeration<String> headerNames = req.getHeaderNames();

        while (headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            String value = req.getHeader(name);
            headers.put(name, value);
        }


        //Extract the Parent Span from the headers
        SpanContext parentSpan = tracer.extract(Format.Builtin.HTTP_HEADERS,
                        new TextMapExtractAdapter(headers));

        //Start a new Span as a Child of the Parent Span
        Scope scope = tracer
                .buildSpan("backend-servlet")
                .asChildOf(parentSpan)
                .startActive(true);
        */
        // ---- Attributes ----
        // Create JSON mapper
        ObjectMapper mapper = new ObjectMapper();

        resp.setContentType("application/json");

        // Read greeting varible from request
        String greeting = req.getParameter("greeting");

        System.out.println("Route: BackendHttpServlet doGet greeting " + greeting);

        // Compose response
        ResponseDTO response = new ResponseDTO();
        response.setGreeting(greeting + " from cluster Backend");
        response.setTime(System.currentTimeMillis());
        response.setIp(getIp());

        System.out.println("Route: BackendHttpServlet doGet response " + response.getGreeting());

        // Send response
        PrintWriter out = resp.getWriter();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, response);

    }

// ------- Methodes -------
    private String getIp() {

        String hostname = null;
        try {
            hostname = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }
        return hostname;
    }
}
