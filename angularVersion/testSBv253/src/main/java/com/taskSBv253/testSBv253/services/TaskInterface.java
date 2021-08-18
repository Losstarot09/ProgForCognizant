package com.taskSBv253.testSBv253.services;

import com.taskSBv253.testSBv253.model.Task;

import java.util.List;

public interface TaskInterface {

    Task saveTask(Task task);
    Task findTaskById(Long id);
    List<Task> showAllTasks();
    void deleteTask(Long id);
}
