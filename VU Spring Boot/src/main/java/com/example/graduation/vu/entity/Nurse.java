package com.example.graduation.vu.entity;


public class Nurse {
    private String idNurse;
    private String name;
    private String street;
    private String zone;
    private byte[] picture;
    private String phone;

    public String getIdNurse() {
        return idNurse;
    }

    public void setIdNurse(String idNurse) {
        this.idNurse = idNurse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
