package org.example.alphasolutions.models;

public class ProjectManager {

    private int projectManagerId;
    private String username;
    private String password;

    //Constructor
    public ProjectManager(int pmId, String username, String password) {
        this.projectManagerId = pmId;
        this.username = username;
        this.password = password;
    }
    public ProjectManager() {}


    //Getter and Setter for Attributes
    public int getProjectManagerId() {
        return projectManagerId;
    }

    public void setProjectManagerId(int projectManagerId) {
        this.projectManagerId = projectManagerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
