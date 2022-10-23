package com.example.lab_03.models;

import java.util.Date;

public class City {

    private int id;
    private String name;
    private Date foundingDate;
    private String city;

    public City(int id, String name, Date foundingDate, String city) {
        this.id = id;
        this.name = name;
        this.foundingDate = foundingDate;
        this.city = city;
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

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
