package com.example.backend.classes;

public interface ResponseMessage {
    String NO_AUTHORIZATION = "No authorization token found";
    String INVALID_AUTHORIZATION_TOKEN = "Invalid authorization token";
    String USER_NOT_FOUND = "No user found";
    String ERROR_GENERATING_TOKEN = "Error generating token";
    String NOT_AUTHORIZED = "Not authorized";
    String INVALID_CREDENTIALS = "Invalid credentials";
    String USER_ALREADY_EXISTS = "User already exists";
}
