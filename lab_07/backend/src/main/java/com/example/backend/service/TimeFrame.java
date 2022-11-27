package com.example.backend.service;

import java.util.Calendar;
import java.util.Date;

public class TimeFrame {
    public static Date START_DATE = new Date(2022, Calendar.NOVEMBER, 1);
    public static Date END_DATE = new Date(2022, Calendar.DECEMBER, 15);

    public static boolean checkCurrentTimeWithInterval() {
        Date currentDate = new Date();
        long currentTime = currentDate.getTime();

        return START_DATE.getTime() <= currentTime && currentTime <= END_DATE.getTime();
    }
}
