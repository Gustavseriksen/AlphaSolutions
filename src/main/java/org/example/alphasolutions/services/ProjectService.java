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

    public void deleteProject(int projectId) {
        projectRepository.deleteProject(projectId);
    }

    public void editProject(int projectId, Project project) {
        projectRepository.editProject(projectId, project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.getAllProjects();
    }

    public Project getProjectByProjectId(int projectId) {
        return projectRepository.getProjectByProjectId(projectId);
    }

    public List<Project> getProjectsByEmployeeId(int employeeId) {
        return projectRepository.getProjectsByEmployeeId(employeeId);
    }


}
