package com.example.lab_02.beans;

import java.io.Serializable;

public class InitParameters implements Serializable {

    private static String language;

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        InitParameters.language = language;
    }
}
