package com.example.graduation.vu.shift;


import com.example.graduation.vu.entity.ICU;
import com.example.graduation.vu.entity.Unit;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepo extends CrudRepository<Shift,Unit> {
    //Select all the shifts for the doctor
    @Query("select unit.id_unit as id , unit.name , shift.start , shift.end from unit\n" +
            "join shift_doctor_unit on unit.id_unit= shift_doctor_unit.id_unit \n" +
            "join shift on shift.id_shift = shift_doctor_unit.id_shift\n" +
            "where shift_doctor_unit.id_doctor=:id")
    public List<Unit> findDoctorUnitsById(String id);

    //Select all shifts for the unit where the patient supervised by the nurse
    @Query("select  unit.id_unit as id,unit.name as name ,doctor.name as doctor,shift.start,shift.end from shift_nurse_patient\n" +
            "join patient on patient.id_patient=shift_nurse_patient.id_patient\n" +
            "join patient_device on patient_device.id_patient=patient.id_patient\n" +
            "join device on device.id_device=patient_device.id_device\n" +
            "join unit on unit.id_unit=device.id_unit\n" +
            "join shift_doctor_unit on shift_doctor_unit.id_unit=unit.id_unit\n" +
            "join shift on shift.id_shift=shift_doctor_unit.id_shift\n" +
            "join doctor on doctor.id_doctor=shift_doctor_unit.id_doctor\n" +
            "where id_nurse=:id ")
    public List<Unit> findNurseUnitsById(String id);

    //Select the icu and its consultant
    @Query("select icu.name  , doctor.name as consultant from unit\n" +
            "join icu on icu.id_icu=unit.id_icu\n" +
            "join doctor on doctor.id_doctor=icu.id_doctor\n" +
            "where unit.id_unit=:id")
    ICU findICUByUnitId(int id);
}
