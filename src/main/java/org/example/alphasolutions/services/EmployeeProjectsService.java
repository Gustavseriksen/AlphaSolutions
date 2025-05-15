package org.example.alphasolutions.services;

import org.example.alphasolutions.models.Employee;
import org.example.alphasolutions.models.Project;
import org.example.alphasolutions.repositories.EmployeeProjectsRepository;
import org.example.alphasolutions.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeProjectsService {

    private final EmployeeProjectsRepository employeeProjectsRepository;
    private final ProjectRepository projectRepository;

    public EmployeeProjectsService(EmployeeProjectsRepository employeeProjectsRepository, ProjectRepository projectRepository) {
        this.employeeProjectsRepository = employeeProjectsRepository;
        this.projectRepository = projectRepository;
    }


    public void addProjectWithEmployees(Project project, List<Integer> employeeIds) {
        projectRepository.addProject(project);

        int projectId = projectRepository.getLastInsertedProjectId();
        if (employeeIds != null) {
            for (int employeeId : employeeIds) {
                employeeProjectsRepository.assignEmployeeToProject(employeeId, projectId);
            }

        }
    }
    public List<Employee> getEmployeesByProjectId(int projectId){
        return employeeProjectsRepository.getEmployeesByProjectId(projectId);
    }

    public void deleteAllEmployeeAssignmentsFromProject(int projectId){
        employeeProjectsRepository.deleteAllEmployeeAssignmentsFromProject(projectId);
    }

    public void updateProjectWithEmployees(int projectId, Project project, List<Integer> selectedEmployeeIds) {
        projectRepository.updateProject(projectId, project);

        employeeProjectsRepository.deleteAllEmployeeAssignmentsFromProject(projectId);

        if (selectedEmployeeIds != null) {
            for (int employeeId : selectedEmployeeIds) {
                employeeProjectsRepository.assignEmployeeToProject(employeeId, projectId);
            }
        }
    }


}