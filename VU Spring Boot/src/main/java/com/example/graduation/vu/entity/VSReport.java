package com.example.graduation.vu.entity;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("vitalsign_report")
public class VSReport {
    @Column("id_vs_report")
    private Long idVSReport;
    private LocalDate date;

    public VSReport(LocalDate date, Long idVSReport) {
        this.date = date;
        this.idVSReport = idVSReport;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getIdVSReport() {
        return idVSReport;
    }

    public void setIdVSReport(Long idVSReport) {
        this.idVSReport = idVSReport;
    }
}
