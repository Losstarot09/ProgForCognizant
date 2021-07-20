package com.taskSBv2312.testSBv2312.model;

import javax.persistence.*;

@Entity
@Table(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long tId;
    @Column(name = "name")
    private String tName;
    @Column(name = "time_spent")
    private String timeSpent;
    @Column(name = "task_group")
    private String group;
    @Column(name = "assignee")
    private String assignee;
    @Column(name = "complete_status")
    private boolean finished;

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(String timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Task() {

    }

    public Task(String tName, String group, String assignee) {
        this.tName = tName;
        this.timeSpent = "0 days";
        this.group = group;
        this.assignee = assignee;
        this.finished = false;
    }

    @Override
    public String toString() {
        return "Task{" +
                "tId=" + tId +
                ", tName='" + tName + '\'' +
                ", timeSpent='" + timeSpent + '\'' +
                ", group='" + group + '\'' +
                ", assignee='" + assignee + '\'' +
                ", finished=" + finished +
                '}';
    }
}
