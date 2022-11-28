package com.example.backend.service;

import java.io.File;
import java.io.IOException;
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

            createFileIfNotExists(fullPath);
            Files.write(Paths.get(fullPath), text.getBytes(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createFileIfNotExists(String filePath) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
    }

}
