package Model;

import java.util.Date;

public class Task {

    private int taskId;
    private int subProjectId;
    private String taskName;
    private String description;
    private Date startDate;
    private Date endDate;
    private double estimatedHours;
    private double actualUsedHours;

    public Task(int taskId, int subProjectId,
                String taskName, String description,
                Date startDate, Date endDate) {

        this.taskId = taskId;
        this.subProjectId = subProjectId;
        this.taskName = taskName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimatedHours = 0;
        this.actualUsedHours = 0;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
