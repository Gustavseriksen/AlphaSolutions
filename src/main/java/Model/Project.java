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













}
