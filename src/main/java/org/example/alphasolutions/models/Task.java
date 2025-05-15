package org.example.alphasolutions.models;

import java.util.Date;

public class Task {

    private int taskId;
    private int subProjectId;
    private String taskName;
    private String priority;
    private Date startDate;
    private Date endDate;
    private double estimatedHours;
    private double actualUsedHours;
    private Status status;

    public Task(int taskId, int subProjectId, String taskName,
                String priority, Date startDate, Date endDate, double estimatedHours,
                double actualUsedHours, Status status) {
        this.taskId = taskId;
        this.subProjectId = subProjectId;
        this.taskName = taskName;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimatedHours = estimatedHours;
        this.actualUsedHours = actualUsedHours;
        this.status = status;
    }


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getSubProjectId() {
        return subProjectId;
    }

    public void setSubProjectId(int subProjectId) {
        this.subProjectId = subProjectId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(double estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public double getActualUsedHours() {
        return actualUsedHours;
    }

    public void setActualUsedHours(double actualUsedHours) {
        this.actualUsedHours = actualUsedHours;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
