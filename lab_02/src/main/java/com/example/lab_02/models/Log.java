package com.example.lab_02.models;

public class Log {

    public final String remoteAddr;
    public final String locale;

    public Log(String remoteAddr, String locale) {
        this.remoteAddr = remoteAddr;
        this.locale = locale;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public String getLocale() {
        return locale;
    }

}
