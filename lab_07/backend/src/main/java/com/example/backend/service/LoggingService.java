package com.example.backend.service;

import com.example.backend.database.models.Document;

public class LoggingService {

    public static void writeFileLog(Document file) {
        String logString = String.format("[Document] %s %s %d",
                file.getName(), file.getAuthor(), file.getSize());

        System.out.println(logString);

        UtilService.appendToFile(logString, "logs.txt");
    }

}
