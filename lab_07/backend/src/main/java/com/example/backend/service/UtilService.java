package com.example.backend.service;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class UtilService {

    public static void appendToFile(String text, String filePath) {
        URL resourcePath = UtilService.class.getClassLoader().getResource("");

        try {
            String resourcesPath = resourcePath.getPath().substring(1).replace("%20", " ");
            String fullPath = resourcesPath + filePath;

            Files.write(Paths.get(fullPath), text.getBytes(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
