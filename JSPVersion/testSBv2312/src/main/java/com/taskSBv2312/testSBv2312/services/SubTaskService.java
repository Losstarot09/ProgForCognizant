package com.taskSBv2312.testSBv2312.services;

import com.taskSBv2312.testSBv2312.model.Subtask;
import com.taskSBv2312.testSBv2312.repository.SubTaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SubTaskService implements SubtaskInterface{

    private final SubTaskRepository subTaskRepository;

    public SubTaskService (SubTaskRepository subTaskRepository) {
        this.subTaskRepository = subTaskRepository; }

    public void saveSubtask(Subtask subTask) { subTaskRepository.save(subTask);}

    public Subtask findSubtaskById(Long id) {
        return  subTaskRepository.findById(id).orElse(new Subtask());
    }

    public List<Subtask> findAllSubTasksByTaskId(Long taskId) {
        List<Subtask> allSubtasksByTaskId = new ArrayList<>();
        List<Subtask> allSubtasks = (List<Subtask>) subTaskRepository.findAll();
        for (Subtask subTask: allSubtasks) {
            if (taskId.equals(subTask.getTaskId())) {
                allSubtasksByTaskId.add(subTask);
            }
        }
        return allSubtasksByTaskId;
    }

    public void deleteSubtask(Long subtaskId) { subTaskRepository.deleteById(subtaskId);}

    public void deleteAllSubtasksByTaskId(Long taskId) {
        List<Subtask> allSubtasks = (List<Subtask>) subTaskRepository.findAll();
        for (Subtask subTask: allSubtasks) {
            if (taskId.equals(subTask.getTaskId())) {
                subTaskRepository.deleteById(subTask.getSubTaskId());
            }
        }

    }

    public boolean checkSubtasksCompleteStatus(Long taskId) {
        boolean status = true;
        List<Subtask> allSubtasks = (List<Subtask>) subTaskRepository.findAll();
        for (Subtask subTask: allSubtasks) {
            if (taskId.equals(subTask.getTaskId())) {
                if (!subTask.getCompleteStatus()) { status = false; }
            }
        }
        return status;
    }

    public void changeSubtasksGroup( Long taskId, String taskGroup) {
        List<Subtask> allSubtasks = (List<Subtask>) subTaskRepository.findAll();
        for (Subtask subTask: allSubtasks) {
            if (taskId.equals(subTask.getTaskId())) {
                subTaskRepository.findById(subTask.getSubTaskId()).orElse(new Subtask())
                        .setTaskGroup(taskGroup);
            }
        }
    }
}
