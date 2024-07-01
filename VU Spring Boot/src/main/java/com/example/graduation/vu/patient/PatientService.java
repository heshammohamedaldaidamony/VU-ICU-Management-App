package com.example.graduation.vu.patient;

import com.example.graduation.vu.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepo;

    public ResponseEntity<?> getBeds(int id){
        Beds beds = new Beds();
        beds.setTotalBeds(patientRepo.countDevicesByUnitId(id));
        List<Patient> patients = patientRepo.findPatientsByUnitId(id);
        beds.setPatients(patients);
        return ResponseEntity.ok(beds);
    }
}
