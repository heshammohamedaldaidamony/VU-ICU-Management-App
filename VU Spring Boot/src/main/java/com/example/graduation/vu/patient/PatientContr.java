package com.example.graduation.vu.patient;

import com.example.graduation.vu.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientContr {
    @Autowired
    private PatientService patientService;

    //Get bed by unit id
    @GetMapping("/beds/{id}")
    public ResponseEntity<?> getBeds(@PathVariable int id){
        return patientService.getBeds(id);
    }

    @GetMapping("/personal-info")
    public ResponseEntity<?> getPersonalInfo(@RequestParam String id){
        return patientService.getPersonalInfo(id);
    }

    //Add a patient on a bed
    @PostMapping("/add-patient")
    public ResponseEntity<?> mapPatientBed(@RequestParam String idPatient,@RequestParam int idDevice){
        return patientService.mapPatientBed(idPatient,idDevice);
    }

    //Delete a patient from a bed
    @DeleteMapping("/delete-patient")
    public ResponseEntity<?> deletePatientBed(@RequestParam String idPatient){
        return patientService.deletePatientBed(idPatient);
    }
}
