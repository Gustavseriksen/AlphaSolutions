package org.example.alphasolutions.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Subproject {

    private int subProjectId;
    private int projectId;
    private String subProjectName;
    private String subProjectDescription;
    private Priority subPriority;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private double estimatedHours;
    private double actualHoursUsed;
    private Status status;

    //Constructor

    public Subproject(int subProjectId, int projectId, String subProjectName,
                      String subProjectDescription, Priority subPriority, Date startDate,
                      Date endDate, double estimatedHours, double actualHoursUsed, Status status) {
        this.subProjectId = subProjectId;
        this.projectId = projectId;
        this.subProjectName = subProjectName;
        this.subProjectDescription = subProjectDescription;
        this.subPriority = subPriority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimatedHours = estimatedHours;
        this.actualHoursUsed = actualHoursUsed;
        this.status = status;
    }

    public Subproject(){}

    //Getter and Setter for attributes
    public int getSubProjectId() {
        return subProjectId;
    }

    public void setSubProjectId(int subProjectId) {
        this.subProjectId = subProjectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public String getSubProjectDescription() {
        return subProjectDescription;
    }

    public Priority getSubPriority() {
        return subPriority;
    }

    public void setSubPriority(Priority subprojectPriority) {
        this.subPriority = subprojectPriority;
    }

    public void setSubProjectDescription(String subProjectDescription) {
        this.subProjectDescription = subProjectDescription;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
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

    public double getActualHoursUsed() {
        return actualHoursUsed;
    }

    public void setActualHoursUsed(double actualHoursUsed) {
        this.actualHoursUsed = actualHoursUsed;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
