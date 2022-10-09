package com.example.lab_02.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.mvc.Controller;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/homework")
public class HomeworkController {

//    @GET
//    @Path("one")
//    public Response hello() {
//        return Response.status(Response.Status.OK)
//                .build();
//    }

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

}
