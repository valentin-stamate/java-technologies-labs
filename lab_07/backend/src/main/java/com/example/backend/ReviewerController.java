package com.example.backend;

import com.example.backend.filters.binding.ReviewerAuthenticated;
import com.example.backend.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/reviewer")
@ReviewerAuthenticated
public class ReviewerController {

    @Inject
    private UserService userService;

    @GET
    @Path("/hello")
    public Response hello() {
        return Response.ok().build();
    }

}
