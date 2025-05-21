package org.example.alphasolutions.services;

import org.example.alphasolutions.models.ProjectManager;
import org.example.alphasolutions.repositories.ProjectManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectManagerService {

    private final ProjectManagerRepository projectManagerRepository;

    public ProjectManagerService(ProjectManagerRepository projectManagerRepository) {
        this.projectManagerRepository = projectManagerRepository;
    }

    public void addProjectManager(ProjectManager projectManager) {
        projectManagerRepository.addProjectManager(projectManager);
    }

    public void deleteProjectManager(int projectManagerId) {
        projectManagerRepository.deleteProjectManager(projectManagerId);
    }

    public List<ProjectManager> getAllProjectManagers() {
        return projectManagerRepository.getAllProjectManagers();
    }

    public ProjectManager getProjectManagerById(int projectManagerId) {
        return projectManagerRepository.getProjectManagerById(projectManagerId);
    }

    public void editProjectManagerById(int projectManagerId, ProjectManager projectManager) {
        projectManagerRepository.editProjectManagerById(projectManagerId, projectManager);
    }


}
