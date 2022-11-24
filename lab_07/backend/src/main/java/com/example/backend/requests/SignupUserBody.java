package com.example.backend.requests;

import com.example.backend.database.repositories.UserType;

public class SignupUserBody {
    private String username;
    private String password;
    private UserType userType;

    public SignupUserBody() { }

    public SignupUserBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
