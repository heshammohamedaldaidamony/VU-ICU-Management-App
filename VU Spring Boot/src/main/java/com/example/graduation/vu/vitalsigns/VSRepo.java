package com.example.graduation.vu.vitalsigns;

import com.example.graduation.vu.entity.VSReport;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VSRepo extends CrudRepository<VSReport,Long> {
    @Query("SELECT * FROM icu_management.vitalsign_report\n" +
            "where id_patient=:id")
    public List<VSReport> findAllByPatientId(String id);
}
