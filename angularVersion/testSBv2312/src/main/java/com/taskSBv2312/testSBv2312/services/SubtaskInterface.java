package com.taskSBv2312.testSBv2312.services;

import com.taskSBv2312.testSBv2312.model.Subtask;

import java.util.List;

public interface SubtaskInterface {

    void saveSubtask(Subtask subTask);
    Subtask findSubtaskById(Long id);
    List<Subtask> findAllSubTasksByTaskId(Long id);
    void deleteSubtask(Long subtaskId);
    void deleteAllSubtasksByTaskId(Long taskId);
    boolean checkSubtasksCompleteStatus(Long taskId);
    void changeSubtasksGroup( Long taskId, String taskGroup);

}
