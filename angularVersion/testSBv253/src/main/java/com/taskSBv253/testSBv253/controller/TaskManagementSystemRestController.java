package com.taskSBv253.testSBv253.controller;

import com.taskSBv253.testSBv253.model.Task;
import com.taskSBv253.testSBv253.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/task")
public class TaskManagementSystemRestController {

    private final TaskService taskService;

    public TaskManagementSystemRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> allTasks = taskService.showAllTasks();
        return new ResponseEntity<>(allTasks, HttpStatus.OK);
    }

    @GetMapping("/getTask/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id){
        Task task = taskService.findTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/addTask")
    public ResponseEntity<Task> addTask (@RequestBody Task task){
        Task newTask = taskService.saveTask(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("/updateTask")
    public ResponseEntity<Task> updateTask (@RequestBody Task task){
        Task updateTask = taskService.saveTask(task);
        return new ResponseEntity<>(updateTask, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask (@PathVariable("id") Long id){
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
