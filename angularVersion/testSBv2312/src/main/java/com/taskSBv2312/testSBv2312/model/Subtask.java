package com.taskSBv2312.testSBv2312.model;

import javax.persistence.*;

@Entity
@Table(name = "Subtasks")
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long subTaskId;
    private String subTaskName;
    private Long taskId;
    private String timeSpent;
    private String taskGroup;
    private String assignee;
    private boolean completeStatus;

    public Long getSubTaskId() {
        return subTaskId;
    }

    public void setSubTaskId(Long subTaskId) {
        this.subTaskId = subTaskId;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getTaskGroup() {
        return taskGroup;
    }

    public void setTaskGroup(String taskGroup) {
        this.taskGroup = taskGroup;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public boolean getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(boolean completeStatus) {
        this.completeStatus = completeStatus;
    }

    public Subtask() {}

    @Override
    public String toString() {
        return "Subtask{" +
                "subTaskId=" + subTaskId +
                ", subTaskName='" + subTaskName + '\'' +
                ", taskId=" + taskId +
                ", timeSpent='" + timeSpent + '\'' +
                ", taskGroup='" + taskGroup + '\'' +
                ", assignee='" + assignee + '\'' +
                ", completeStatus=" + completeStatus +
                '}';
    }
}
