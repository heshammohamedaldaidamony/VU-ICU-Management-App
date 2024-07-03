package com.example.graduation.vu.patient;

import com.example.graduation.vu.entity.Patient;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepo extends CrudRepository<Patient,String > {

    //Select number of beds by unit id.
    @Query("SELECT COUNT(*) FROM device WHERE id_unit =:id")
    public int countDevicesByUnitId(int id);

    //Select patients info by unit id
    @Query("SELECT patient.id_patient , patient.name , patient.patient_phone , patient.birth , patient.gender , patient.weight , patient.height , patient.street , \n" +
            "patient.zone , patient.companion_name , patient.companion_phone , patient.date_in ,  patient.date_out , patient.diagnosis , nurse.name as nurse \n" +
            "FROM unit\n" +
            "join device on device.id_unit=unit.id_unit \n" +
            "join patient_device on patient_device.id_device=device.id_device\n" +
            "join patient on patient.id_patient = patient_device.id_patient\n" +
            "join shift_nurse_patient on shift_nurse_patient.id_patient=patient.id_patient\n" +
            "join nurse on nurse.id_nurse=shift_nurse_patient.id_nurse\n" +
            "where unit.id_unit=:id")
    public List<Patient> findPatientsByUnitId(int id);

    @Modifying
    @Query("INSERT INTO `icu_management`.`patient_device` (`id_patient`, `id_device`) \n" +
            "VALUES (:idPatient, :idDevice);\n")
    public int savePatientDevice(String idPatient,int idDevice);

    @Modifying
    @Query("DELETE FROM `icu_management`.`patient_device`\n" +
            "WHERE `id_patient` =:idPatient")
    public void deletePatientDevice(String idPatient);

    @Query("SELECT COUNT(*) FROM patient_device WHERE id_patient =:idPatient")
    public int countPatientDeviceByIdPatient(String idPatient);
}
