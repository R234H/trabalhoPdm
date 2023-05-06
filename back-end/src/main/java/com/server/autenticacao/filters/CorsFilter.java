package com.server.autenticacao.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter, ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext request) throws IOException {

        if (isPreFlightRequest(request)) {
            request.abortWith(Response.ok().build());
        }
    }

    public static Boolean isPreFlightRequest(ContainerRequestContext req) {
        return req.getHeaderString("Origin") != null && req.getMethod().equalsIgnoreCase("OPTIONS");
    }

    @Override
    public void filter(ContainerRequestContext request, ContainerResponseContext response) throws IOException {
        if (request.getHeaderString("Origin") == null) {
            return;
        }

        response.getHeaders().add("Access-Control-Allow-Credentials", "true");
        String origin = request.getHeaderString("Origin");
        response.getHeaders().add("Access-Control-Allow-Origin", origin);
        response.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD, PATCH");
        response.getHeaders().add("Access-Control-Allow-Headers", "X-Requested-With, Authorization, Accept-Version, Content-MD5, CSRF-Token, Content-Type");
    }
}
