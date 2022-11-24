package com.example.backend.service.jwt;

import com.example.backend.classes.UserPayload;
import com.example.backend.database.models.User;
import com.example.backend.database.repositories.UserType;
import java.util.Date;
import java.util.Map;

public  class UserJwtPayloadService {

    public static Map<String, Object> payloadFromUser(User user) {
        return Map.of(
                JwtUserPayloadKey.USERNAME_KEY, user.getUsername(),
                JwtUserPayloadKey.USER_TYPE_KEY, user.getUserType().toString()
        );
    }

    public static UserPayload getUser(Map<String, Object> payload) {
        return new UserPayload(
                (String) payload.get(JwtUserPayloadKey.USERNAME_KEY),
                (UserType) payload.get(JwtUserPayloadKey.USER_TYPE_KEY)
        );
    }

    public static Date getExpirationDate(Map<String, Object> payload) {
        int expirationTime = (Integer) payload.get(JwtUserPayloadKey.EXPIRATION_DATE);
        return new Date(expirationTime * 1000L);
    }

}
