package com.example.backend.filters;

import com.example.backend.classes.ResponseMessage;
import com.example.backend.classes.UserPayload;
import com.example.backend.database.repositories.UserType;
import com.example.backend.filters.binding.AuthorAuthenticated;
import com.example.backend.service.jwt.JwtService;
import com.example.backend.service.jwt.UserJwtPayloadService;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Map;

@AuthorAuthenticated
@Provider
public class AuthorFilter implements ContainerRequestFilter, ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorization = requestContext.getHeaderString("Authorization");

        if (authorization == null || authorization.equals("")) {
            requestContext.abortWith(Response.status(Response.Status.NOT_FOUND).entity(ResponseMessage.NO_AUTHORIZATION).build());
            return;
        }

        Map<String, Object> payload = JwtService.decode(authorization);

        if (payload == null) {
            requestContext.abortWith(Response.status(Response.Status.NOT_FOUND).entity(ResponseMessage.INVALID_AUTHORIZATION_TOKEN).build());
            return;
        }

        UserPayload userPayload = UserJwtPayloadService.getUser(payload);

        if (userPayload.getUserType() != UserType.AUTHOR) {
            requestContext.abortWith(Response.status(Response.Status.NOT_FOUND).entity(ResponseMessage.NOT_AUTHORIZED).build());
            return;
        }

        /* TODO in PrefO: check if the user exists even if the token is valid */
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException { }
}
