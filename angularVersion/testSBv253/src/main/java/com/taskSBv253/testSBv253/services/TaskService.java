package com.taskSBv253.testSBv253.services;

import com.taskSBv253.testSBv253.exceptions.TaskNotFoundException;
import com.taskSBv253.testSBv253.model.Task;
import com.taskSBv253.testSBv253.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements TaskInterface {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) { this.taskRepository = taskRepository; }

    public Task saveTask(Task task) { return taskRepository.save(task); }

    public Task findTaskById(Long id) {  return taskRepository.findById(id).orElseThrow(
            () -> new TaskNotFoundException("Task by id " + id + " was not found")); }

    public List<Task> showAllTasks(){
        return taskRepository.findAll();
    }

    public void deleteTask(Long id) { taskRepository.deleteById(id); }
}
