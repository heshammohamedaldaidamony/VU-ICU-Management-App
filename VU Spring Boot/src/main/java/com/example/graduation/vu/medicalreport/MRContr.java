package com.example.graduation.vu.medicalreport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medical-reports")
public class MRContr {
    @Autowired
    private MRService mrService;

    @GetMapping
    public ResponseEntity<?> getMedicalReports(@RequestParam String idPatient){
        return mrService.getMedicalReports(idPatient);
    }
}
