package Model;

import java.util.Date;

public class Subproject {

    private int subProjectId;
    private int projectID;
    private String subProjectName;
    private String subProjectDescription;
    private Date startDate;
    private Date endDate;
    private double estimatedHours;
    private double actualHoursUsed;
    private Status status;

    //Constructor
    public Subproject(int subProjectId, int projectID,
                      Date startDate, String subProjectName,
                      String subProjectDescription,
                      Date endDate) {

        this.subProjectId = subProjectId;
        this.projectID = projectID;
        this.startDate = startDate;
        this.subProjectName = subProjectName;
        this.subProjectDescription = subProjectDescription;
        this.endDate = endDate;
        this.estimatedHours = 0;
        this.actualHoursUsed = 0;
        this.status = Status.NOT_DONE;
    }


    //Getter and Setter for attributes
    public int getSubProjectId() {
        return subProjectId;
    }

    public void setSubProjectId(int subProjectId) {
        this.subProjectId = subProjectId;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public String getSubProjectDescription() {
        return subProjectDescription;
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
