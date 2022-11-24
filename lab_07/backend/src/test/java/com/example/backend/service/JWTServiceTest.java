package com.example.backend.service;

import com.example.backend.database.models.User;
import com.example.backend.database.repositories.UserType;
import com.example.backend.service.jwt.JwtService;
import com.example.backend.service.jwt.UserJwtPayloadService;
import org.junit.jupiter.api.Test;

import java.util.Map;

class JWTServiceTest {

    @Test
    void sign() {
        User user = new User("admin", "pass", UserType.ADMIN);
        Map<String, Object> payload = UserJwtPayloadService.payloadFromUser(user);

        String token = JwtService.sign(payload);
        System.out.println(token);
    }

    @Test
    void decode() {
        User user = new User("admin", "pass", UserType.ADMIN);
        Map<String, Object> payload = UserJwtPayloadService.payloadFromUser(user);
        String token = JwtService.sign(payload);

        Map<String, Object> decodedPayload = JwtService.decode(token);

        Object exp = decodedPayload.get("exp");

        System.out.println(decodedPayload);
        System.out.println(exp);
        System.out.println(UserJwtPayloadService.getExpirationDate(decodedPayload));
    }
}