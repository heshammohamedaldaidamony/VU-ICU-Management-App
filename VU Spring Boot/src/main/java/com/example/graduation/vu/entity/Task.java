package com.example.graduation.vu.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("task")
public class Task {
    @Id
    @Column("id_task")
    private Long id ;
    private String description;
    private String status;
    @Column("donedate")
    private LocalDateTime doneDate;
    @Column("duedate")
    private LocalDateTime dueDate;
    @Column("id_doctor")
    private String idDoctor;
    private String doctor;
    @Column("id_medicine")
    private int idMedicine;
    private String medicine;
    @Column("id_patient")
    private String idPatient;
    @Column("id_nurse")
    private String idNurse;
    private String nurse;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(LocalDateTime doneDate) {
        this.doneDate = doneDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public int getIdMedicine() {
        return idMedicine;
    }

    public void setIdMedicine(int idMedicine) {
        this.idMedicine = idMedicine;
    }

    public String getIdNurse() {
        return idNurse;
    }

    public void setIdNurse(String idNurse) {
        this.idNurse = idNurse;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getNurse() {
        return nurse;
    }

    public void setNurse(String nurse) {
        this.nurse = nurse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }
}
