package com.example.graduation.vu.patient;

import com.example.graduation.vu.entity.Patient;

import java.util.List;

public class Beds {
    private int totalBeds;
    private List<Patient> patients;

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public int getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(int totalBeds) {
        this.totalBeds = totalBeds;
    }
}
