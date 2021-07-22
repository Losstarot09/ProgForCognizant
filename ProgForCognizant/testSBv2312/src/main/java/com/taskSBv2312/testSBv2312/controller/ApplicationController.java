package com.taskSBv2312.testSBv2312.controller;

import com.taskSBv2312.testSBv2312.model.Subtask;
import com.taskSBv2312.testSBv2312.model.Task;
import com.taskSBv2312.testSBv2312.services.SubTaskService;
import com.taskSBv2312.testSBv2312.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ApplicationController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private SubTaskService subTaskService;

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
    public String addTask(@ModelAttribute Task task, BindingResult bindingResult,
                          HttpServletRequest request) {
        task.setFinished(false);
        task.setTimeSpent("0 days");
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

    @RequestMapping("/edit-task-form")
    public String editTaskForm(@RequestParam Long id, HttpServletRequest request) {
        request.setAttribute("task", taskService.findTask(id));
        request.setAttribute("mode", "MODE_EDIT-TASK-FORM");
        return "home";
    }

    @PostMapping("/save-existing-task")
    public String editTask(@ModelAttribute Task task, BindingResult bindingResult,
                           HttpServletRequest request) {

        subTaskService.changeSubtasksGroup(task.gettId(), task.getGroup());

        if (task.getFinished().equals(false)) {
            taskService.saveTask(task);
            request.setAttribute("mode", "MODE_SHOW-TASKS");
            request.setAttribute("allTasks",taskService.showAllTasks());
        } else {
            if (subTaskService.checkSubtasksCompleteStatus(task.gettId())) {
                taskService.saveTask(task);
                request.setAttribute("mode", "MODE_SHOW-TASKS");
                request.setAttribute("allTasks",taskService.showAllTasks());
            } else {
                request.setAttribute("tId", task.gettId());
                request.setAttribute("mode", "MODE_CANT-FINISH-TASK");
            }
        }
        return "home";
    }

    @RequestMapping("/delete-task")
    public String deleteTask(@RequestParam Long id, HttpServletRequest request) {
        subTaskService.deleteAllSubtasksByTaskId(id);
        taskService.deleteTask(id);
        request.setAttribute("mode", "MODE_SHOW-TASKS");
        request.setAttribute("allTasks",taskService.showAllTasks());
        return "home";
    }

    @RequestMapping("/show-subtasks")
    public String showSubTask(@RequestParam Long task_id, HttpServletRequest request) {
        request.setAttribute("taskId", task_id);
        request.setAttribute("taskName", taskService.findTask(task_id).gettName());
        request.setAttribute("allSubtasks", subTaskService.findAllSubTasksByTaskId(task_id));
        request.setAttribute("mode", "MODE_SHOW-SUBTASKS");
        return "home";
    }

    @RequestMapping("/add-subtask-form")
    public String addSubTaskForm(@RequestParam Long task_id,HttpServletRequest request) {
        request.setAttribute("taskName", taskService.findTask(task_id).gettName());
        request.setAttribute("task_id", task_id);
        request.setAttribute("task_group", taskService.findTask(task_id).getGroup());
        request.setAttribute("mode", "MODE_ADD-SUBTASK-FORM");
        return "home";
    }

    @PostMapping("/save-subtask")
    public String addSubTask(@ModelAttribute Subtask subTask, BindingResult bindingResult,
                          HttpServletRequest request) {
        subTask.setFinished(false);
        subTask.setTimeSpent("0 days");
        subTaskService.saveSubtask(subTask);
        request.setAttribute("taskId", subTask.gettId());
        request.setAttribute("taskName", taskService.findTask(subTask.gettId()).gettName());
        request.setAttribute("allSubtasks",
                subTaskService.findAllSubTasksByTaskId(subTask.gettId()));
        request.setAttribute("mode", "MODE_SHOW-SUBTASKS");
        return "home";
    }

    @RequestMapping("/edit-subtask-form")
    public String editSubTaskForm(@RequestParam Long id, HttpServletRequest request) {
        request.setAttribute("taskName",
                taskService.findTask(subTaskService.findSubtaskById(id).gettId()).gettName());
        request.setAttribute("subTask", subTaskService.findSubtaskById(id));
        request.setAttribute("mode", "MODE_EDIT-SUBTASK-FORM");
        return "home";
    }

    @PostMapping("/save-existing-subtask")
    public String editSubtask(@ModelAttribute Subtask subTask, BindingResult bindingResult,
                              HttpServletRequest request) {
        subTaskService.saveSubtask(subTask);
        request.setAttribute("taskId", subTask.gettId());
        request.setAttribute("taskName", taskService.findTask(subTask.gettId()).gettName());
        request.setAttribute("allSubtasks",
                subTaskService.findAllSubTasksByTaskId(subTask.gettId()));
        request.setAttribute("mode", "MODE_SHOW-SUBTASKS");
        return "home";
    }

    @RequestMapping("/delete-subtask")
    public String deleteSubTask(@RequestParam Long task_id, @RequestParam Long id,
                                HttpServletRequest request) {
        subTaskService.deleteSubtask(id);
        request.setAttribute("taskId", task_id);
        request.setAttribute("taskName", taskService.findTask(task_id).gettName());
        request.setAttribute("allSubtasks", subTaskService.findAllSubTasksByTaskId(task_id));
        request.setAttribute("mode", "MODE_SHOW-SUBTASKS");
        return "home";
    }

}
