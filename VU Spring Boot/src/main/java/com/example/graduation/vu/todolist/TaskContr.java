package com.example.graduation.vu.todolist;

import com.example.graduation.vu.entity.Medicine;
import com.example.graduation.vu.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskContr {
    @Autowired
    private TaskService taskService;

    //Select all tasks by patient id
    @GetMapping
    public ResponseEntity<?> getTasks(@RequestParam String id){
        List<Task> tasks=taskService.getTasks(id);
        if(tasks.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Tasks Yet");
        return ResponseEntity.ok(tasks);
    }

    //Select All medicines
    @GetMapping("/medicine")
    public List<Medicine> getMedicines(){
        return taskService.getMedicines();
    }

    //Add a task
    @PostMapping("/add")
    public ResponseEntity<?> addTask(@RequestBody Task task){
        if(task.getDescription()==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Add Valid Task");

        taskService.addTask(task);
        return ResponseEntity.ok("Task Is Added Successfully");
    }

    //Modify a task by id
    @PutMapping("/modify")
    public ResponseEntity<?> modifyTask(@RequestBody Task task){
        if(task.getDescription()==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Add Valid Task");
        taskService.modifyTask(task);
        return ResponseEntity.ok("Task Is Modified Successfully");
    }

    //Delete a task by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task Deleted");
    }

    //Nurse do a task
    @PutMapping("/do")
    public ResponseEntity<?> doTask(@RequestParam Long idTask,@RequestParam String idNurse){
        taskService.doTask(idTask,idNurse);
        return ResponseEntity.ok("Task Is Done");
    }

    //Quick access based on role and id
    @GetMapping("/quick-access")
    public ResponseEntity<?> quickAccessTasks(@RequestParam String id , @RequestParam String role){
        List<Task> tasks = taskService.quickAccessTasks(id,role);
        if (tasks.isEmpty())
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Current Tasks");
        return ResponseEntity.ok(tasks);
    }
}
