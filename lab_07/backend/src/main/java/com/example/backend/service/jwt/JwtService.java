package com.example.backend.service.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

public abstract class JwtService {

    private final static String SECRET = "123456789qwe";
    private final static Algorithm ALGORITHM = Algorithm.HMAC256(JwtService.SECRET);
    private final static String ISSUER = "auth0";

    public static String sign(final Map<String, Object> payload) {
        Date expirationDate = new Date(new Date().getTime() + 1000 * 60 * 60); // Current Time + 1h;

       try {
           return JWT.create()
                   .withPayload(payload)
                   .withExpiresAt(expirationDate)
                   .withIssuer(ISSUER)
                   .sign(ALGORITHM);
       } catch (JWTCreationException e) {
           return null;
       }
    }

    public static Map<String, Object> decode(final String token) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JWTVerifier verifier = JWT.require(ALGORITHM)
                    .withIssuer(ISSUER)
                    .build();

            com.auth0.jwt.interfaces.DecodedJWT jwtVerifier = verifier.verify(token);

            String base64Payload = jwtVerifier.getPayload();
            byte[] payloadBytes = Base64.getDecoder().decode(base64Payload);
            String stringPayload = new String(payloadBytes);

            return objectMapper.readValue(stringPayload, Map.class);
        } catch (JWTVerificationException | JsonProcessingException e) {
            return null;
        }
    }

}

