package com.taskSBv2312.testSBv2312.services;

import com.taskSBv2312.testSBv2312.model.Task;
import com.taskSBv2312.testSBv2312.repository.TaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskReposiroty;

    public TaskService(TaskRepository taskRepository) { this.taskReposiroty = taskRepository; }

    public void saveTask(Task task) { taskReposiroty.save(task); }

    public Task findTask(Long id) {  return taskReposiroty.findById(id).orElse(new Task()); }

    public List<Task> showAllTasks(){
        List<Task> allTasks = new ArrayList<Task>();
        for (Task task: taskReposiroty.findAll()) {
            allTasks.add(task);
        }
        return allTasks;
    }

    public void deleteTask(Long id) { taskReposiroty.deleteById(id); }
}
