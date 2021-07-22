package com.taskSBv2312.testSBv2312.services;

import com.taskSBv2312.testSBv2312.model.Subtask;
import com.taskSBv2312.testSBv2312.repository.SubTaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SubTaskService {

    private final SubTaskRepository subTaskRepository;

    public SubTaskService (SubTaskRepository subTaskRepository) {
        this.subTaskRepository = subTaskRepository; }

    public void saveSubtask(Subtask subTask) { subTaskRepository.save(subTask);}

    public Subtask findSubtaskById(Long id) {
        return  subTaskRepository.findById(id).orElse(new Subtask());
    }

    public List<Subtask> findAllSubTasksByTaskId(Long taskId) {
        List<Subtask> allSubtasksByTaskId = new ArrayList<Subtask>();
        for (Subtask subTask: subTaskRepository.findAll()) {
            if (taskId.equals(subTask.gettId())) {
                allSubtasksByTaskId.add(subTask);
            }
        }
        return allSubtasksByTaskId;
    }

    public void deleteSubtask(Long subtaskId) { subTaskRepository.deleteById(subtaskId);}

    public void deleteAllSubtasksByTaskId(Long taskId) {
        for (Subtask subTask: subTaskRepository.findAll()) {
            if (taskId.equals(subTask.gettId())) {
                subTaskRepository.deleteById(subTask.getsTId());
            }
        }

    }

    public boolean checkSubtasksCompleteStatus(Long taskId) {
        boolean status = true;
        for (Subtask subTask: subTaskRepository.findAll()) {
            if (taskId.equals(subTask.gettId())) {
                if (!subTask.getFinished()) { status = false; }
            }
        }
        return status;
    }

    public void changeSubtasksGroup( Long taskId, String taskGroup) {
        for (Subtask subTask: subTaskRepository.findAll()) {
            if (taskId.equals(subTask.gettId())) {
                subTaskRepository.findById(subTask.getsTId()).orElse(new Subtask()).setGroup(taskGroup);
            }
        }
    }
}
