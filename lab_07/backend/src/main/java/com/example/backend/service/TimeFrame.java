package com.example.backend.service;

import java.util.Calendar;
import java.util.Date;

public class TimeFrame {
    public static Date START_DATE = new Date(1667253600000L);
    public static Date END_DATE = new Date(1671055200000L);

    public static boolean checkCurrentTimeWithInterval() {
        long currentTime = new Date().getTime();

        return START_DATE.getTime() <= currentTime && currentTime <= END_DATE.getTime();
    }
}
