package com.example.backend.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilServiceTest {

    @Test
    void generateRandomString() {
        String randomString = RandomStringUtils.random(10, true, true);

        System.out.println(randomString);
    }
}