package Model;

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
    private int userId;
    private int daysToStart;
    private int daysForProject;
    private int daysLeft;

    public Project(int projectId, String projectName, String projectDescription, LocalDate projectStartDate, LocalDate projectEndDate, int userId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
        this.userId = userId;
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

// Getter & setter for UserID:
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}



