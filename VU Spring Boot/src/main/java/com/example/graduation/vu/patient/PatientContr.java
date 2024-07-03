package com.example.graduation.vu.patient;

import com.example.graduation.vu.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beds")
public class PatientContr {
    @Autowired
    private PatientService patientService;

    @GetMapping("/patients/{id}")
    public ResponseEntity<?> getPatients(@PathVariable int id){
        return patientService.getBeds(id);
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
