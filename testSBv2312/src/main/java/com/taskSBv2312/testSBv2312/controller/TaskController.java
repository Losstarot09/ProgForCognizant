package com.taskSBv2312.testSBv2312.controller;

import com.taskSBv2312.testSBv2312.model.Task;
import com.taskSBv2312.testSBv2312.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/")
    public String welcomeP() {
        return "welcome";
    }

    @RequestMapping("/home")
    public String home(HttpServletRequest request) {
        request.setAttribute("mode", "MODE_HOME");
        return "home";
    }

    @RequestMapping("/add-task-form")
    public String addTaskForm(HttpServletRequest request) {
        request.setAttribute("mode", "MODE_ADD-TASK-FORM");
        return "home";
    }

    @PostMapping("/save-task")
    public String addTask(@ModelAttribute Task task, BindingResult bindingResult, HttpServletRequest request) {
        task.setFinished(false);
        task.setTimeSpent("0 days");
        taskService.saveTask(task);
        request.setAttribute("mode", "MODE_SHOW-TASKS");
        request.setAttribute("allTasks",taskService.showAllTasks());
        return "home";
    }

    @PostMapping("/save-existing-task")
    public String editTask(@ModelAttribute Task task, BindingResult bindingResult, HttpServletRequest request) {
        taskService.saveTask(task);
        request.setAttribute("mode", "MODE_SHOW-TASKS");
        request.setAttribute("allTasks",taskService.showAllTasks());
        return "home";
    }

    @RequestMapping("/show-tasks")
    public String showTasks(HttpServletRequest request) {
        request.setAttribute("mode", "MODE_SHOW-TASKS");
        request.setAttribute("allTasks",taskService.showAllTasks());
        return "home";
    }

    @RequestMapping("/delete-task")
    public String deleteTask(@RequestParam Long id, HttpServletRequest request) {
        taskService.deleteTask(id);
        request.setAttribute("mode", "MODE_SHOW-TASKS");
        request.setAttribute("allTasks",taskService.showAllTasks());
        return "home";
    }

    @RequestMapping("/edit-task-form")
    public String editTaskForm(@RequestParam Long id, HttpServletRequest request) {
        request.setAttribute("task", taskService.findTask(id));
        request.setAttribute("mode", "MODE_EDIT-TASK-FORM");
        return "home";
    }

}
