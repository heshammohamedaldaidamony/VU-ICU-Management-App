package com.example.graduation.vu.todolist.medicine;

import com.example.graduation.vu.entity.Medicine;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicineRepo extends CrudRepository<Medicine,Integer> {
    public List<Medicine> findAll();

    @Modifying
    @Query("UPDATE medicine\n" +
            "join task on task.id_medicine=medicine.id_medicine\n" +
            "SET amount = amount - 1\n" +
            "WHERE amount IS NOT NULL and task.id_task=:idTask")
    void reduceMedicineAmount(Long idTask);
}
