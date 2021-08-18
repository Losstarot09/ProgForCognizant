package com.taskSBv2312.testSBv2312.services;

import com.taskSBv2312.testSBv2312.model.Task;

import java.util.List;

public interface TaskInterface {

    void saveTask(Task task);
    Task findTask(Long id);
    List<Task> showAllTasks();
    void deleteTask(Long id);
}
