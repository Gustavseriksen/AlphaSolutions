package org.example.alphasolutions.Service;

import org.example.alphasolutions.Repository.ManagementSoftwareRepository;
import org.example.alphasolutions.Model.*;
import org.springframework.stereotype.Service;

@Service
public class ManagementSoftwareService {
    private final ManagementSoftwareRepository managementSoftwareRepository;

    public ManagementSoftwareService(ManagementSoftwareRepository managementSoftwareRepository) {
        this.managementSoftwareRepository = managementSoftwareRepository;
    }

    // Admin

    public Admin checkAdminCredentials(String username, String password) {
        return managementSoftwareRepository.checkCredentials(username, password);
    }

    // Worker
    public void addProjectManager(ProjectManager projectManager) {
        managementSoftwareRepository.addProjectManager(projectManager);
    }

    public void addEmployee(Employee employee) {
        managementSoftwareRepository.addEmployee(employee);
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
