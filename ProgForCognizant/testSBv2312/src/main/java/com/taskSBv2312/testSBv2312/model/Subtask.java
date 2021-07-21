package com.taskSBv2312.testSBv2312.model;

import javax.persistence.*;

@Entity
@Table(name = "Subtasks")
public class Subtask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long sTId;
    @Column(name = "name")
    private String sTName;
    @Column(name = "task_id")
    private Long tId;
    @Column(name = "time_spent")
    private String timeSpent;
    @Column(name = "task_group")
    private String group;
    @Column(name = "assignee")
    private String assignee;
    @Column(name = "complete_status")
    private boolean finished;

    public Long getsTId() {
        return sTId;
    }

    public void setsTId(Long sTId) {
        this.sTId = sTId;
    }

    public String getsTName() {
        return sTName;
    }

    public void setsTName(String sTName) {
        this.sTName = sTName;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
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

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Subtask() {}

    @Override
    public String toString() {
        return "SubTask{" +
                "sTId=" + sTId +
                ", sTName='" + sTName + '\'' +
                ", tId=" + tId +
                ", timeSpent='" + timeSpent + '\'' +
                ", group='" + group + '\'' +
                ", assignee='" + assignee + '\'' +
                ", finished=" + finished +
                '}';
    }
}
