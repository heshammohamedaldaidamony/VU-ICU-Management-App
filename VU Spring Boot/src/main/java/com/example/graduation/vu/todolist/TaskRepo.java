package com.example.graduation.vu.todolist;

import com.example.graduation.vu.entity.Task;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long> {
    @Modifying
    @Query("INSERT INTO task (description, status, donedate, duedate, id_doctor, id_patient, id_medicine, id_nurse) " +
            "VALUES (:description, :status, :doneDate, :dueDate, :id_doctor, :id_patient, :id_medicine, :id_nurse)")
    public void insertTask(String description,
                           String status,
                           LocalDateTime doneDate,
                           LocalDateTime dueDate,
                           String id_doctor,
                           String id_patient,
                           int id_medicine,
                           String id_nurse);

    @Query("SELECT task.id_task , task.description , task.status , task.donedate , task.duedate ,\n" +
            "doctor.id_doctor , doctor.name as doctor , medicine.id_medicine , \n" +
            "medicine.name as medicine , nurse.id_nurse , nurse.name as nurse \n" +
            "FROM icu_management.task\n" +
            "left join doctor on doctor.id_doctor=task.id_doctor\n" +
            "left join medicine on medicine.id_medicine=task.id_medicine\n" +
            "left join nurse on nurse.id_nurse=task.id_nurse\n" +
            "where task.id_patient=:id")
    public List<Task> findAllTasksByPatientId(String id);

    @Modifying
    @Query("UPDATE task SET description = :description, status = :status, donedate = :doneDate, duedate = :dueDate, id_doctor = :id_doctor, id_patient = :id_patient, id_medicine = :id_medicine, id_nurse = :id_nurse WHERE id_task = :id")
    public void updateTask(@Param("description") String description,
                           @Param("status") String status,
                           @Param("doneDate") LocalDateTime doneDate,
                           @Param("dueDate") LocalDateTime dueDate,
                           @Param("id_doctor") String id_doctor,
                           @Param("id_patient") String id_patient,
                           @Param("id_medicine") int id_medicine,
                           @Param("id_nurse") String id_nurse,
                           @Param("id") Long id);

    @Query("SELECT task.id_task , task.description , task.status , task.donedate , task.duedate ,\n" +
            "doctor.id_doctor , doctor.name as doctor , medicine.id_medicine , \n" +
            "medicine.name as medicine , nurse.id_nurse , nurse.name as nurse \n" +
            "FROM icu_management.task\n" +
            "left join doctor on doctor.id_doctor=task.id_doctor\n" +
            "left join medicine on medicine.id_medicine=task.id_medicine\n" +
            "left join nurse on nurse.id_nurse=task.id_nurse\n" +
            "where doctor.id_doctor=:id")
    public List<Task> findAllTasksByDoctorId(String id);
    @Query("SELECT task.id_task , task.description , task.status , task.donedate , task.duedate ,\n" +
            "doctor.id_doctor , doctor.name as doctor , medicine.id_medicine , \n" +
            "medicine.name as medicine , nurse.id_nurse , nurse.name as nurse \n" +
            "FROM icu_management.task\n" +
            "left join doctor on doctor.id_doctor=task.id_doctor\n" +
            "left join medicine on medicine.id_medicine=task.id_medicine\n" +
            "left join nurse on nurse.id_nurse=task.id_nurse\n" +
            "where nurse.id_nurse=:id")
    public List<Task> findAllTasksByNurseId(String id);

    @Modifying
    @Query("UPDATE task  set status ='done', donedate = :now , id_nurse = :idNurse WHERE id_task = :idTask")
    void doTask(Long idTask, String idNurse , LocalDateTime now);
}
