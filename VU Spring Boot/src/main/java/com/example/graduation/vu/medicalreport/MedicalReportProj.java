package com.example.graduation.vu.medicalreport;

import com.example.graduation.vu.entity.Image;
import com.example.graduation.vu.entity.MedicalReport;

import java.util.List;

public class MedicalReportProj extends MedicalReport {
    private List<Image> images;


    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

}
