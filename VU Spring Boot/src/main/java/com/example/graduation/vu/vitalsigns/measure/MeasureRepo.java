package com.example.graduation.vu.vitalsigns.measure;

import com.example.graduation.vu.entity.Measure;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasureRepo extends CrudRepository<MeasureRepo,Long> {
    @Query("SELECT * FROM icu_management.measure\n" +
            "where id_vs_report=:reportId AND id_sign=:signId")
    public List<Measure> findMeasuresReportIdAndSignId(Long reportId,int signId);
}
