package com.example.graduation.vu.todolist;

import com.example.graduation.vu.entity.Medicine;
import com.example.graduation.vu.entity.Task;
import com.example.graduation.vu.todolist.medicine.MedicineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private MedicineRepo medicineRepo;

    public void addTask(Task task) {
        taskRepo.insertTask(task.getDescription(),
                task.getStatus(),
                task.getDoneDate(),
                task.getDueDate(),
                task.getIdDoctor(),
                task.getIdPatient(),
                task.getIdMedicine(),
                task.getIdNurse());
    }

    public List<Medicine> getMedicines() {
        return medicineRepo.findAll();
    }

    public List<Task> getTasks(String id) {
        return taskRepo.findAllTasksByPatientId(id);
    }

    public void modifyTask(Task task) {
        taskRepo.updateTask(task.getDescription(),
                task.getStatus(),
                task.getDoneDate(),
                task.getDueDate(),
                task.getIdDoctor(),
                task.getIdPatient(),
                task.getIdMedicine(),
                task.getIdNurse(),
                task.getId());
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }

    public List<Task> quickAccessTasks(String id, String role) {
        List<Task> tasks;
        if(role.equals("doctor"))
            tasks=taskRepo.findAllTasksByDoctorId(id);
        else
            tasks=taskRepo.findAllTasksByNurseId(id);
        return tasks;
    }

    @Transactional
    public void doTask(Long idTask, String idNurse) {
        taskRepo.doTask(idTask,idNurse, LocalDateTime.now());
        medicineRepo.reduceMedicineAmount(idTask);
    }
}
