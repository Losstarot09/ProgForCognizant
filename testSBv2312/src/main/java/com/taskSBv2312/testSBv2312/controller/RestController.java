package com.taskSBv2312.testSBv2312.controller;

import com.taskSBv2312.testSBv2312.model.Task;
import com.taskSBv2312.testSBv2312.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private TaskService taskService;

//    @GetMapping("/")
//    public String hello() {
//        return "home page";
//    }

    @GetMapping("/saveTask")
    public String saveTask(@RequestParam String tName, @RequestParam String group,
                           @RequestParam String assignee) {
        Task task = new Task (tName, group, assignee);
        taskService.saveTask(task);
        return "Task is saved";
    }
}
