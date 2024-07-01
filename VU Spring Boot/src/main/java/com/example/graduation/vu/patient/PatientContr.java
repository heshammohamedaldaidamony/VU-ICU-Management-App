package com.example.graduation.vu.patient;

import com.example.graduation.vu.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientContr {
    @Autowired
    private PatientService patientService;

    @GetMapping("/beds/{id}")
    public ResponseEntity<?> getPatients(@PathVariable int id){
        return patientService.getBeds(id);
    }
}
