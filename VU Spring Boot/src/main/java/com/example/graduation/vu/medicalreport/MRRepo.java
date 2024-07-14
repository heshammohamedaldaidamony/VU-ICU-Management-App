package com.example.graduation.vu.medicalreport;

import com.example.graduation.vu.entity.MedicalReport;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MRRepo extends CrudRepository<MedicalReportProj,Long> {

    @Query("SELECT id_med_report ,name ,date FROM medical_report WHERE id_patient =:idPatient")
    public List<MedicalReport> findAllMRsByPatientId(@Param("idPatient") String idPatient);

}
