package com.example.graduation.vu.patient;

import com.example.graduation.vu.entity.Patient;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepo extends CrudRepository<Patient,String > {

    //Select number of beds by unit id.
    @Query("SELECT COUNT(*) FROM device WHERE id_unit =:id")
    public int countDevicesByUnitId(int id);

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

    //Update date in when add
    @Modifying
    @Query("UPDATE patient\n" +
            "SET date_in =:now  \n" +
            "WHERE id_patient =:idPatient")
    public void UpdatePatientDateIn(String idPatient, LocalDate now);

    //Update date out when delete
    @Modifying
    @Query("UPDATE patient\n" +
            "SET date_out =:now  \n" +
            "WHERE id_patient =:idPatient")
    public void UpdatePatientDateOut(String idPatient, LocalDate now);
}
