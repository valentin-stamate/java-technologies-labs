package com.example.backend;

import com.example.backend.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/admin")
public class AdminResource {

    @Inject
    private UserService userService;

    @GET
    @Path("/files")
    public Response getFiles() {
        return Response.ok().build();
    }

    @GET
    @Path("/files/{id}")
    public Response downloadFile() {
        return Response.ok().build();
    }

}
