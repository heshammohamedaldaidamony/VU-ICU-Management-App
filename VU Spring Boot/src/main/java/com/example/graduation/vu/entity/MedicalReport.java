package com.example.graduation.vu.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("medical_report")
public class MedicalReport {
    @Id
    @Column("id_med_report")
    private Long idMR;
    private String name;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getIdMR() {
        return idMR;
    }

    public void setIdMR(Long idMR) {
        this.idMR = idMR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
