package com.example.graduation.vu.medicalreport;


import com.example.graduation.vu.entity.MedicalReport;
import com.example.graduation.vu.medicalreport.image.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MRService {
    @Autowired
    private ImageRepo imageRepo;
    @Autowired
    private MRRepo mrRepo;

    public ResponseEntity<?> getMedicalReports(String idPatient) {
        List<MedicalReport> medicalReports = mrRepo.findAllMRsByPatientId(idPatient);
        if(medicalReports.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The Patient Has No Reports");

        List<MedicalReportProj> medicalReportProjs=new ArrayList<>();
        for (MedicalReport medicalReport : medicalReports){
            MedicalReportProj medicalReportProj=new MedicalReportProj();
            medicalReportProj.setIdMR(medicalReport.getIdMR());
            medicalReportProj.setName(medicalReport.getName());
            medicalReportProj.setDate(medicalReport.getDate());
            medicalReportProj.setImages(imageRepo.findAllImagesByMRId(medicalReport.getIdMR()));
            medicalReportProjs.add(medicalReportProj);
        }
        return ResponseEntity.ok(medicalReportProjs);
    }

}
