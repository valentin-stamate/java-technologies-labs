package com.example.backend;

import com.example.backend.classes.UserPayload;
import com.example.backend.database.models.Document;
import com.example.backend.filters.binding.AuthorAuthenticated;
import com.example.backend.service.UserService;
import com.example.backend.service.exception.ServiceException;
import com.example.backend.service.jwt.UserJwtPayloadService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.core.Response.Status;
import org.glassfish.jersey.media.multipart.FormDataParam;
import java.io.InputStream;
import java.util.List;

@Path("/author")
public class AuthorResource {

    @Inject
    private UserService userService;

    @GET
    @Path("/files")
    @Produces(MediaType.APPLICATION_JSON)
    @AuthorAuthenticated
    public Response getFiles(@Context HttpHeaders headers) {
        UserPayload userPayload = UserJwtPayloadService.getUserPayloadFromHeaders(headers);

        try {
            List<Document> result = userService.getFiles(userPayload.getUsername());

            return Response.ok(result).build();
        } catch (ServiceException e) {
            return Response.status(e.getStatus()).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/files")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @AuthorAuthenticated
    public Response uploadFile(@Context HttpHeaders headers, @FormParam("name") String name, @FormDataParam("file") InputStream file) {
        UserPayload userPayload = UserJwtPayloadService.getUserPayloadFromHeaders(headers);

        try {
            userService.uploadFile(userPayload.getUsername(), name, file);

            return Response.status(Status.CREATED).build();
        } catch (ServiceException e) {
            return Response.status(e.getStatus()).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/files/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @AuthorAuthenticated
    public Response downloadFile(@Context HttpHeaders headers, @PathParam("id") String documentId) {
        UserPayload userPayload = UserJwtPayloadService.getUserPayloadFromHeaders(headers);

        try {
            InputStream documentInputStream = userService.getFileBuffer(userPayload.getUsername(), documentId);

            return Response.ok(documentInputStream).build();
        } catch (ServiceException e) {
            return Response.status(e.getStatus()).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/files/{id}")
    @AuthorAuthenticated
    public Response removeFile(@Context HttpHeaders headers, @PathParam("id") String documentId) {
        UserPayload userPayload = UserJwtPayloadService.getUserPayloadFromHeaders(headers);

        try {
            userService.removeFile(userPayload.getUsername(), documentId);

            return Response.ok().build();
        } catch (ServiceException e) {
            return Response.status(e.getStatus()).entity(e.getMessage()).build();
        }
    }


}
