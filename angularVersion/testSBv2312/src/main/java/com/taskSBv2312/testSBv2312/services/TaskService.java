package com.taskSBv2312.testSBv2312.services;

import com.taskSBv2312.testSBv2312.model.Task;
import com.taskSBv2312.testSBv2312.repository.TaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskService implements TaskInterface {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) { this.taskRepository = taskRepository; }

    public void saveTask(Task task) { taskRepository.save(task); }

    public Task findTask(Long id) {  return taskRepository.findById(id).orElse(new Task()); }

    public List<Task> showAllTasks(){
        return (List<Task>) taskRepository.findAll();
    }

    public void deleteTask(Long id) { taskRepository.deleteById(id); }
}
