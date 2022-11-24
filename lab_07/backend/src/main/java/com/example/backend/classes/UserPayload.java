package com.example.backend.classes;

import com.example.backend.database.repositories.UserType;

public class UserPayload {
    private final String username;
    private final UserType userType;

    public UserPayload(String username, UserType userType) {
        this.username = username;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public UserType getUserType() {
        return userType;
    }
}
