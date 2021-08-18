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
    public String welcomePage() {
        return "welcome";
    }

    @RequestMapping("/add-task-form")
    public String addTaskForm(HttpServletRequest request) {
        request.setAttribute("mode", "MODE_ADD-TASK-FORM");
        return "home";
    }

    @PostMapping("/save-task")
    public String addTask(@ModelAttribute Task task, BindingResult bindingResult,
                          HttpServletRequest request) {
        task.setCompleteStatus(false);
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
    public String editTaskForm(@RequestParam Long taskId, HttpServletRequest request) {
        request.setAttribute("task", taskService.findTask(taskId));
        request.setAttribute("mode", "MODE_EDIT-TASK-FORM");
        return "home";
    }

    @PostMapping("/save-existing-task")
    public String editTask(@ModelAttribute Task task, BindingResult bindingResult,
                           HttpServletRequest request) {

        subTaskService.changeSubtasksGroup(task.getTaskId(), task.getTaskGroup());

        if (task.getCompleteStatus().equals(false)) {
            taskService.saveTask(task);
            request.setAttribute("mode", "MODE_SHOW-TASKS");
            request.setAttribute("allTasks",taskService.showAllTasks());
        } else {
            if (subTaskService.checkSubtasksCompleteStatus(task.getTaskId())) {
                taskService.saveTask(task);
                request.setAttribute("mode", "MODE_SHOW-TASKS");
                request.setAttribute("allTasks",taskService.showAllTasks());
            } else {
                request.setAttribute("taskId", task.getTaskId());
                request.setAttribute("mode", "MODE_CANT-FINISH-TASK");
            }
        }
        return "home";
    }

    @RequestMapping("/delete-task")
    public String deleteTask(@RequestParam Long taskId, HttpServletRequest request) {
        subTaskService.deleteAllSubtasksByTaskId(taskId);
        taskService.deleteTask(taskId);
        request.setAttribute("mode", "MODE_SHOW-TASKS");
        request.setAttribute("allTasks",taskService.showAllTasks());
        return "home";
    }

    @RequestMapping("/show-subtasks")
    public String showSubTask(@RequestParam Long taskId, HttpServletRequest request) {
        request.setAttribute("taskId", taskId);
        request.setAttribute("taskName", taskService.findTask(taskId).getTaskName());
        request.setAttribute("allSubtasks", subTaskService.findAllSubTasksByTaskId(taskId));
        request.setAttribute("mode", "MODE_SHOW-SUBTASKS");
        return "home";
    }

    @RequestMapping("/add-subtask-form")
    public String addSubTaskForm(@RequestParam Long taskId,HttpServletRequest request) {
        request.setAttribute("taskName", taskService.findTask(taskId).getTaskName());
        request.setAttribute("taskId", taskId);
        request.setAttribute("taskGroup", taskService.findTask(taskId).getTaskGroup());
        request.setAttribute("mode", "MODE_ADD-SUBTASK-FORM");
        return "home";
    }

    @PostMapping("/save-subtask")
    public String addSubTask(@ModelAttribute Subtask subTask, BindingResult bindingResult,
                             HttpServletRequest request) {
        subTask.setCompleteStatus(false);
        subTask.setTimeSpent("0 days");
        subTaskService.saveSubtask(subTask);
        request.setAttribute("taskId", subTask.getTaskId());
        request.setAttribute("taskName", taskService.findTask(subTask.getTaskId()).getTaskName());
        request.setAttribute("allSubtasks",
                subTaskService.findAllSubTasksByTaskId(subTask.getTaskId()));
        request.setAttribute("mode", "MODE_SHOW-SUBTASKS");
        return "home";
    }

    @RequestMapping("/edit-subtask-form")
    public String editSubTaskForm(@RequestParam Long subTaskId, HttpServletRequest request) {
        request.setAttribute("taskName",
                taskService.findTask(subTaskService.findSubtaskById(subTaskId).getTaskId()).getTaskName());
        request.setAttribute("subTask", subTaskService.findSubtaskById(subTaskId));
        request.setAttribute("mode", "MODE_EDIT-SUBTASK-FORM");
        return "home";
    }

    @PostMapping("/save-existing-subtask")
    public String editSubtask(@ModelAttribute Subtask subTask, BindingResult bindingResult,
                              HttpServletRequest request) {
        subTaskService.saveSubtask(subTask);
        request.setAttribute("taskId", subTask.getTaskId());
        request.setAttribute("taskName", taskService.findTask(subTask.getTaskId()).getTaskName());
        request.setAttribute("allSubtasks",
                subTaskService.findAllSubTasksByTaskId(subTask.getTaskId()));
        request.setAttribute("mode", "MODE_SHOW-SUBTASKS");
        return "home";
    }

    @RequestMapping("/delete-subtask")
    public String deleteSubTask(@RequestParam Long subTaskId,
                                HttpServletRequest request) {
        Long taskId = subTaskService.findSubtaskById(subTaskId).getTaskId();
        subTaskService.deleteSubtask(subTaskId);

        request.setAttribute("taskId", taskId);
        request.setAttribute("taskName", taskService.findTask(taskId).getTaskName());
        request.setAttribute("allSubtasks", subTaskService.findAllSubTasksByTaskId(taskId));
        request.setAttribute("mode", "MODE_SHOW-SUBTASKS");

        return "home";
    }

}
