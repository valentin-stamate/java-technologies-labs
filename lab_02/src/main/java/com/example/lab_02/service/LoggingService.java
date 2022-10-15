package com.example.lab_02.service;

import com.example.lab_02.models.Log;

public class LoggingService {

    public void writeLog(Log log) {
        String logString = String.format("[LOG] %s %s\n",
                log.getRemoteAddr(),
                log.getLocale());

        System.out.print(logString);

        UtilService.appendToFile(logString, "logs.txt");
    }

}
