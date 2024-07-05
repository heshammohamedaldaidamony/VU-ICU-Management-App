package com.example.graduation.vu.medicalreport.image;

import com.example.graduation.vu.entity.Image;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepo extends CrudRepository<Image,Long> {
    @Query("SELECT id_image,path FROM icu_management.image\n" +
            "where id_med_report=:IdMR")
    public List<Image> findAllImagesByMRId(@Param("IdMR") Long IdMR);
}
