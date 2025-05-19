package org.example.alphasolutions.services;

import org.example.alphasolutions.models.Project;
import org.example.alphasolutions.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void addProject(Project project) {
        projectRepository.addProject(project);
    }

    public void deleteProject(int projectId) {
        projectRepository.deleteProject(projectId);
    }

    public void editProject() {
        projectRepository.editProject();
    }

    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }

    public Project getProjectByProjectId(int projectId) {
        return projectRepository.getProjectByProjectId(projectId);
    }

    public void updateProject(int projectID, Project project) {
        projectRepository.updateProject(projectID, project);
    }

    public List<Project> getProjectsByEmployeeId(int employeeId) {
        return projectRepository.getProjectsByEmployeeId(employeeId);
    }


}
