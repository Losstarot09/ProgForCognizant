package com.taskSBv253.testSBv253.services;

import com.taskSBv253.testSBv253.model.Subtask;

import java.util.List;

public interface SubtaskInterface {

    Subtask saveSubtask(Subtask subTask);
    Subtask findSubtaskById(Long id);
    List<Subtask> findAllSubTasksByTaskId(Long id);
    void deleteSubtask(Long subtaskId);
    void deleteAllSubtasksByTaskId(Long taskId);
    boolean checkSubtasksCompleteStatus(Long taskId);
    void changeSubtasksGroup( Long taskId, String taskGroup);

}
