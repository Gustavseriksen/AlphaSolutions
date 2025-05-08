package org.example.alphasolutions.Model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Project {

    private int projectId;
    private String projectName;
    private String projectDescription;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDate projectStartDate;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private LocalDate projectEndDate;
    private int estimatedHours;
    private int actualHoursUsed;
    private Status projectStatus;

    public Project(int projectId, String projectName,
                   String projectDescription, LocalDate projectStartDate,
                   LocalDate projectEndDate, int estimatedHours, int actualHoursUsed, Status status) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
        this.estimatedHours = estimatedHours;
        this.actualHoursUsed = actualHoursUsed;
        this.projectStatus = status;
    }

    public Project(){}

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

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
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
    public int getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(int estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

// Getter & setter for actualHoursUsed:
    public int getActualHoursUsed() {
        return actualHoursUsed;
    }

    public void setActualHoursUsed(int actualHoursUsed) {
        this.actualHoursUsed = actualHoursUsed;
    }

// Getter & setter for projectStatus:
    public Status getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Status projectStatus) {
        this.projectStatus = projectStatus;
    }
}



