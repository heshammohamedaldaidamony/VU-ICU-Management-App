package com.example.graduation.vu.entity;

import java.time.LocalDateTime;

public class Unit {
    private int id;
    private String name;
    private String doctor;
    private LocalDateTime start;
    private LocalDateTime end;


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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getDoctorName() {
        return doctor;
    }

    public void setDoctorName(String doctor) {
        this.doctor = doctor;
    }
}
