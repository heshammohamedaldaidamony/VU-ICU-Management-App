package com.example.graduation.vu.vitalsigns;

import com.example.graduation.vu.entity.Measure;
import com.example.graduation.vu.entity.VSReport;

import java.time.LocalDate;
import java.util.List;

public class VSReportProj extends VSReport {
    private List<Measure> HR;
    private List<Measure> SpO2;
    private List<Measure> etCO2;
    private List<Measure> Pulse;
    private List<Measure> aWRR;
    private List<Measure> Tperi;

    public VSReportProj(LocalDate date, Long idVSReport) {
        super(date, idVSReport);
    }

    public List<Measure> getaWRR() {
        return aWRR;
    }

    public void setaWRR(List<Measure> aWRR) {
        this.aWRR = aWRR;
    }

    public List<Measure> getEtCO2() {
        return etCO2;
    }

    public void setEtCO2(List<Measure> etCO2) {
        this.etCO2 = etCO2;
    }

    public List<Measure> getHR() {
        return HR;
    }

    public void setHR(List<Measure> HR) {
        this.HR = HR;
    }

    public List<Measure> getPulse() {
        return Pulse;
    }

    public void setPulse(List<Measure> pulse) {
        Pulse = pulse;
    }

    public List<Measure> getSpO2() {
        return SpO2;
    }

    public void setSpO2(List<Measure> spO2) {
        SpO2 = spO2;
    }

    public List<Measure> getTperi() {
        return Tperi;
    }

    public void setTperi(List<Measure> tperi) {
        Tperi = tperi;
    }
}
