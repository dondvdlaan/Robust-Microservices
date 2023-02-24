package dev.manyroads.microservice.backend;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Class to restrict access to this application by way of CORS
 */
@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
public class CORSInterceptor implements Filter {

    // ---- Attributes ----

    // Allowed URLs for this application
    private static final String[] allowedOrigins = {
            "http://localhost:3000", "http://localhost:9090", "http://localhost:5501",
            "http://127.0.0.1:3000", "http://127.0.0.1:9090", "http://127.0.0.1:5501"
    };

    /**
     * Creating the CORS filter
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // ---- Attributes ----
        String requestOrigin = request.getHeader("Origin");

        // CORS filter is open to all requests. To restrict access, uncomment below if-statement
        //if(isAllowedOrigin(requestOrigin)) {

            // Authorize the origin, all headers, and all methods
            ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", requestOrigin);
            ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers", "*");
            ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods",
                    "GET, OPTIONS, HEAD, PUT, POST, DELETE");

            HttpServletResponse resp = (HttpServletResponse) servletResponse;

            // CORS handshake (pre-flight request)
            if (request.getMethod().equals("OPTIONS")) {
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                return;
            }
        //} Uncomment to activate if-statement access restriction

        // Pass the request along the filter chain
        filterChain.doFilter(request, servletResponse);
    }

    // ---- Methodes ----
    private boolean isAllowedOrigin(String origin){
        for (String allowedOrigin : allowedOrigins) {
            if(origin.equals(allowedOrigin)) return true;
        }
        return false;
    }
}
