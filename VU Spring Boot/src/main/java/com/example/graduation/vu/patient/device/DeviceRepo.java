package com.example.graduation.vu.patient.device;

import com.example.graduation.vu.entity.Device;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepo extends CrudRepository<Device, Integer> {
    @Query("SELECT device.id_device,\n" +
            "       device.name,\n" +
            "       patient.id_patient,\n" +
            "       patient.name AS patient,\n" +
            "       patient.diagnosis,\n" +
            "       nurse.name AS nurse \n" +
            "FROM icu_management.device\n" +
            "LEFT JOIN patient_device ON patient_device.id_device = device.id_device\n" +
            "LEFT JOIN patient ON patient.id_patient = patient_device.id_patient\n" +
            "LEFT JOIN shift_nurse_patient ON shift_nurse_patient.id_patient = patient.id_patient\n" +
            "LEFT JOIN nurse ON nurse.id_nurse = shift_nurse_patient.id_nurse\n" +
            "LEFT JOIN shift ON shift.id_shift = shift_nurse_patient.id_shift\n" +
            "WHERE (NOW() BETWEEN shift.start AND shift.end OR patient_device.id_patient IS NULL )\n" +
            "   AND id_unit =:idUnit")
    public List<Device> findAllDevicesByUnitId(int idUnit);
}
