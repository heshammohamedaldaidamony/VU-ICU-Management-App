package com.example.graduation.vu.patient;

import com.example.graduation.vu.entity.Device;
import com.example.graduation.vu.entity.Patient;
import com.example.graduation.vu.patient.device.DeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private DeviceRepo deviceRepo;

    public ResponseEntity<?> getBeds(int id){
        List<Device> devices = deviceRepo.findAllDevicesByUnitId(id);
        if(devices.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("This Unit Has No Devices");
        return ResponseEntity.ok(devices);
    }

    public ResponseEntity<?> mapPatientBed( String idPatient , int idDevice) {
        if(patientRepo.countPatientDeviceByIdPatient(idPatient)>0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This Patient Already On A Device");

        try {
            patientRepo.savePatientDevice(idPatient,idDevice);
            patientRepo.UpdatePatientDateIn(idPatient, LocalDate.now());
            return ResponseEntity.ok("Patient Added");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Add Valid Patient");
       }
    }

    public ResponseEntity<?> deletePatientBed(String idPatient) {
        patientRepo.deletePatientDevice(idPatient);
        patientRepo.UpdatePatientDateOut(idPatient, LocalDate.now());
        return ResponseEntity.ok("Patient Deleted");
    }

    public ResponseEntity<?> getPersonalInfo(String id) {
        return ResponseEntity.ok(patientRepo.findById(id));
    }
}
