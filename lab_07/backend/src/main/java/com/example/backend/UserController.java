package com.example.backend;

import com.example.backend.classes.ResponseMessage;
import com.example.backend.requests.LoginUserBody;
import com.example.backend.requests.SignupUserBody;
import com.example.backend.service.TimeFrame;
import com.example.backend.service.UserService;
import com.example.backend.service.exception.ServiceException;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserController {

    @Inject
    private UserService userService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(LoginUserBody requestBody) {

        try {
            Object result = userService.loginUser(
                    requestBody.getUsername(),
                    requestBody.getPassword()
            );

            return Response.ok(result).build();
        } catch (ServiceException e) {
            return Response.status(e.getStatus()).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response signup(SignupUserBody requestBody) {
        if (!TimeFrame.checkCurrentTimeWithInterval()) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(ResponseMessage.TIMEFRAME_EXCEEDED).build();
        }

        try {
            Object result = userService.signupUser(
                    requestBody.getUsername(),
                    requestBody.getPassword(),
                    requestBody.getUserType()
            );

            return Response.ok(result).build();
        } catch (ServiceException e) {
            return Response.status(e.getStatus()).entity(e.getMessage()).build();
        }
    }

}