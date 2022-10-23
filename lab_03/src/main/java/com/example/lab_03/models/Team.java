package com.example.lab_03.models;

import java.util.Date;

public class Team {

    private int id;
    private String name;
    private Date foundDate;
    private String country;

    public Team() { }

    public Team(int id, String name, Date foundDate, String country) {
        this.id = id;
        this.name = name;
        this.foundDate = foundDate;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(Date foundDate) {
        this.foundDate = foundDate;
    }
}
