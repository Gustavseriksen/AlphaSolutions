package org.example.alphasolutions.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Project {

    private int projectId;
    private String projectName;
    private String projectDescription;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate projectStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate projectEndDate;
    private double estimatedHours;
    private double actualHoursUsed;
    private Priority projectPriority;
    private Status projectStatus;

    public Project(int projectId, String projectName,
                   String projectDescription, LocalDate projectStartDate,
                   LocalDate projectEndDate, int estimatedHours, int actualHoursUsed, Priority priority, Status status) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
        this.estimatedHours = estimatedHours;
        this.actualHoursUsed = actualHoursUsed;
        this.projectPriority = priority;
        this.projectStatus = status;
    }

    public Project() {
    }

    // Getter & setter for ProjectID:
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    // Getter & setter for ProjectName:
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    // Getter & setter for ProjectDescription:
    public String getProjectDescription() {
        return projectDescription;
    }
// Getter & setter for ProjectStartDate:

    public LocalDate getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(LocalDate projectStartDate) {
        this.projectStartDate = projectStartDate;
    }


// Getter & setter for ProjectEndDate:


    public LocalDate getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(LocalDate projectEndDate) {
        this.projectEndDate = projectEndDate;
    }


    // Getter & setter for estimated hours:
    public double getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(double estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    // Getter & setter for actualHoursUsed:
    public double getActualHoursUsed() {
        return actualHoursUsed;
    }

    public void setActualHoursUsed(double actualHoursUsed) {
        this.actualHoursUsed = actualHoursUsed;
    }

    // Getter & setter for projectPriority:
    public Priority getProjectPriority() {
        return projectPriority;
    }

    public void setProjectPriority(Priority projectPriority) {
        this.projectPriority = projectPriority;
    }


    // Getter & setter for projectStatus:
    public Status getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Status projectStatus) {
        this.projectStatus = projectStatus;
    }
}



