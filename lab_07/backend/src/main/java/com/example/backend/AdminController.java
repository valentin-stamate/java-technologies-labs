package com.example.backend;

import com.example.backend.classes.UserPayload;
import com.example.backend.filters.binding.AdminAuthenticated;
import com.example.backend.filters.binding.FileCached;
import com.example.backend.service.UserService;
import com.example.backend.service.exception.ServiceException;
import com.example.backend.service.jwt.UserJwtPayloadService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.InputStream;

@Path("/admin")
@AdminAuthenticated
public class AdminController {

    @Inject
    private UserService userService;

    @GET
    @Path("/files")
    public Response getFiles(@Context HttpHeaders headers) {
        UserPayload userPayload = UserJwtPayloadService.getUserPayloadFromHeaders(headers);

        try {
            var result = userService.getAllFiles();

            return Response.ok(result).build();
        } catch (ServiceException e) {
            return Response.status(e.getStatus()).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/files/{id}")
    @FileCached
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response downloadFile(@PathParam("id") String documentId) {
        try {
            InputStream documentInputStream = userService.getFileBuffer(documentId);

            return Response.ok(documentInputStream).build();
        } catch (ServiceException e) {
            return Response.status(e.getStatus()).entity(e.getMessage()).build();
        }
    }

}
