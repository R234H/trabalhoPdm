package com.server.autenticacao.filters;

import com.server.autenticacao.security.TokenSecurity;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The AuthenticationFilter verifies the access permissions for a user based on the provided jwt token
 * and role annotations
 **/
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements javax.ws.rs.container.ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    public static final String HEADER_PROPERTY_ID = "id";
    public static final String AUTHORIZATION_PROPERTY = "authorization";

    private static final String ACCESS_REFRESH = "Token expired. Please authenticate again!";
    private static final String ACCESS_INVALID_TOKEN = "Token invalid. Please authenticate again!";
    private static final String ACCESS_DENIED = "Not allowed to access this resource!";
    private static final String ACCESS_FORBIDDEN = "Access forbidden!";
    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method method = resourceInfo.getResourceMethod();
        if (requestContext.getMethod().equals("OPTIONS"))
            return;
        if (!method.isAnnotationPresent(PermitAll.class)) {
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(ACCESS_FORBIDDEN).build());
                return;
            }

            final MultivaluedMap<String, String> headers = requestContext.getHeaders();
            final List<String> authProperty = headers.get(AUTHORIZATION_PROPERTY);

            if (authProperty == null || authProperty.isEmpty()) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(ACCESS_DENIED).build());
                return;
            }

            try {
                String bearer = authProperty.get(0);
                String id = TokenSecurity.validateJwtToken(bearer);
                headers.put( HEADER_PROPERTY_ID, Arrays.asList(id) );
            } catch (Exception e) {
                requestContext.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED).entity(ACCESS_INVALID_TOKEN).build()
                );
            }
        }
    }
}
