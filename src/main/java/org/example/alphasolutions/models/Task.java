package org.example.alphasolutions.models;

import java.util.Date;

public class Task {

    private int taskId;
    private int subProjectId;
    private String taskName;
    private String taskDescription;
    private Priority priority;
    private double estimatedHours;
    private double actualUsedHours;
    private Status status;

    public Task(int taskId, int subProjectId, String taskName, String taskDescription,
                Priority priority, double estimatedHours,
                double actualUsedHours, Status status) {
        this.taskId = taskId;
        this.subProjectId = subProjectId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.priority = priority;
        this.estimatedHours = estimatedHours;
        this.actualUsedHours = actualUsedHours;
        this.status = status;
    }

    public Task() {}


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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
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

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

}
