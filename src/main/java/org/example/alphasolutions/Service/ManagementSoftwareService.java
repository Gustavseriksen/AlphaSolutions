package org.example.alphasolutions.Service;

import org.example.alphasolutions.Repository.ManagementSoftwareRepository;
import org.example.alphasolutions.Model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagementSoftwareService {
    private final ManagementSoftwareRepository managementSoftwareRepository;

    public ManagementSoftwareService(ManagementSoftwareRepository managementSoftwareRepository) {
        this.managementSoftwareRepository = managementSoftwareRepository;
    }

    // Admin

    public Admin checkAdminCredentials(String username, String password) {
        return managementSoftwareRepository.checkAdminCredentials(username, password);
    }

    public void deleteProjectManager(int projectManagerId) {
        managementSoftwareRepository.deleteProjectManager(projectManagerId);
    }

    // Employee

    public Employee checkEmployeeCredentials(String username, String password) {
        return managementSoftwareRepository.checkEmployeeCredentials(username, password);
    }

    public void addProjectManager(ProjectManager projectManager) {
        managementSoftwareRepository.addProjectManager(projectManager);
    }

    public void addEmployee(Employee employee) {
        managementSoftwareRepository.addEmployee(employee);
    }

    //Project Manager

    public List<ProjectManager> getAllProjectManagers() {
        return managementSoftwareRepository.getAllProjectManagers();
    }
    public ProjectManager getProjectManagerById(int projectManagerId) {
        return managementSoftwareRepository.getProjectManagerById(projectManagerId);
    }
    public void editProjectManagerById(int projectManagerId, ProjectManager projectManager) {
        managementSoftwareRepository.editProjectManagerById(projectManagerId, projectManager);
    }

    // Project
    public void addProject(Project project) {
        managementSoftwareRepository.addProject(project);
    }

    public void deleteProject(int projectId) {
        managementSoftwareRepository.deleteProject(projectId);
    }

    public void editProject() {
        managementSoftwareRepository.editProject();
    }

    public ProjectManager checkProjectManagerCredentials(String username, String password) {
        return managementSoftwareRepository.checkProjectManagerCredentials(username, password);
    }

    // Subproject
    public void addSubproject(Subproject subproject) {
        managementSoftwareRepository.addSubproject(subproject);
    }

    public void deleteSubproject(int subprojectId) {
        managementSoftwareRepository.deleteSubproject(subprojectId);
    }

    public void editSubproject(int subprojectId, Subproject subproject) {
        managementSoftwareRepository.editSubproject(subprojectId, subproject);
    }

    public void getSubprojectsByProjectId(int projectId){
        managementSoftwareRepository.getSubsByProjectId(projectId);
    }

    // Task
    public void addTask(Task task) {
        managementSoftwareRepository.addTask(task);
    }

    public void deleteTask(int taskId) {
        managementSoftwareRepository.deleteTask(taskId);
    }

    public void editTask(int taskId, Task task) {
        managementSoftwareRepository.editTask(taskId, task);
    }

//    // Check
//    public void checkIfDone() {
//        managementSoftwareRepository.checkIfDone();
//    }
}
