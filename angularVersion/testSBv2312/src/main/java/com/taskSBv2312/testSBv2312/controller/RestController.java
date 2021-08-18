package com.taskSBv2312.testSBv2312.controller;

import com.taskSBv2312.testSBv2312.model.Task;
import com.taskSBv2312.testSBv2312.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/getTask/{id}")
    public Task apiGetTask(@PathVariable Long id){  return taskService.findTask(id);  }

    @GetMapping("/getAllTask")
    public List<Task> apiGetAllTask() { return taskService.showAllTasks(); }

    @PostMapping("/addTask")
    public String apiAddTask(@RequestBody Task task) {
        taskService.saveTask(task);
        return "task saved";
    }

    @PostMapping("/deleteTask/{id}")
    public String apiDeleteTask(@RequestParam @PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task is deleted";
    }

}
