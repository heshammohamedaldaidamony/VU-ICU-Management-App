package com.example.graduation.vu.vitalsigns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vital-signs")
public class VSContr {
    @Autowired
    private VSService vsService;

    //Get the vitalsigns report by patient id
    @GetMapping
    public ResponseEntity<?> getVSReport(@RequestParam String id){
        List<VSReportProj> vsReportProjs =vsService.getVSReport(id);
        if(vsReportProjs.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Reports Yet");
        return ResponseEntity.ok(vsReportProjs);
    }
}
