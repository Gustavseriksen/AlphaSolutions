package org.example.alphasolutions.services;

import org.example.alphasolutions.repositories.ManagementSoftwareRepository;
import org.example.alphasolutions.models.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagementSoftwareService {
    private final ManagementSoftwareRepository managementSoftwareRepository;

    public ManagementSoftwareService(ManagementSoftwareRepository managementSoftwareRepository) {
        this.managementSoftwareRepository = managementSoftwareRepository;
    }

    // ADMIN -------------------------------------------------------------------------------------------------------------

    public Admin checkAdminCredentials(String username, String password) {
        return managementSoftwareRepository.checkAdminCredentials(username, password);
    }

    // ADMIN EMPLOYEE -------------------------------------------------------------------------------------------------------------

    public void deleteEmployee(int employeeId) {
        managementSoftwareRepository.deleteEmployee(employeeId);
    }
    public List<Employee> getAllEmployees() {
        return managementSoftwareRepository.getAllEmployees();
    }
    public Employee getEmployeeById(int employeeId) {
        return managementSoftwareRepository.getEmployeeById(employeeId);
    }
    public Employee checkEmployeeCredentials(String username, String password) {
        return managementSoftwareRepository.checkEmployeeCredentials(username, password);
    }
    public void addEmployee(Employee employee) {
        managementSoftwareRepository.addEmployee(employee);
    }
    public void editEmployeeById(int employeeId, Employee employee) {
        managementSoftwareRepository.editEmployeeById(employeeId, employee);
    }


    // ADMIN PROJECT MANAGER -------------------------------------------------------------------------------------------------------------

    public void deleteProjectManager(int projectManagerId) {
        managementSoftwareRepository.deleteProjectManager(projectManagerId);
    }
    public List<ProjectManager> getAllProjectManagers() {
        return managementSoftwareRepository.getAllProjectManagers();
    }
    public ProjectManager getProjectManagerById(int projectManagerId) {
        return managementSoftwareRepository.getProjectManagerById(projectManagerId);
    }
    public void editProjectManagerById(int projectManagerId, ProjectManager projectManager) {
        managementSoftwareRepository.editProjectManagerById(projectManagerId, projectManager);
    }
    public void addProjectManager(ProjectManager projectManager) {
        managementSoftwareRepository.addProjectManager(projectManager);
    }
    public List<Project> getAllProjects(){
        return managementSoftwareRepository.getAllProjects();
    }

    // ADMIN END -------------------------------------------------------------------------------------------------------------

    // PROJECT MANAGER -------------------------------------------------------------------------------------------------------------

    public void addProjectWithEmployees(Project project, List<Integer> employeeIds) {
        managementSoftwareRepository.addProject(project);

        int projectId = managementSoftwareRepository.getLastInsertedProjectId();
        if (employeeIds != null) {
            for (int employeeId : employeeIds) {
                managementSoftwareRepository.assignEmployeeToProject(employeeId, projectId);
            }

        }


    }

    public List<Employee> getEmployeesByProjectId(int projectId) {
        return managementSoftwareRepository.getEmployeesByProjectId(projectId);
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

    public Project getProjectByProjectId(int projectId) {
        return managementSoftwareRepository.getProjectByProjectId(projectId);
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

    public List<Subproject> getSubprojectsByProjectId(int projectId){
        return managementSoftwareRepository.getSubsByProjectId(projectId);
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

    public Project getProjectById(int projectId) {
        return managementSoftwareRepository.getProjectById(projectId);

    }

    public void updateProjectWithEmployees(int projectId, Project project, List<Integer> selectedEmployeeIds) {
        managementSoftwareRepository.updateProject(projectId, project);

        managementSoftwareRepository.deleteAllEmployeeAssignmentsFromProject(projectId);

        if (selectedEmployeeIds != null) {
            for (int employeeId : selectedEmployeeIds) {
                managementSoftwareRepository.assignEmployeeToProject(employeeId, projectId);
            }
        }
    }

    public Subproject getSubprojectBySubId(int subprojectId) {
        return managementSoftwareRepository.getSubprojectBySubId(subprojectId);
    }


//    // Check
//    public void checkIfDone() {
//        managementSoftwareRepository.checkIfDone();
//    }
}
