package Service;

import Repository.ManagementSoftwareRepository;
import org.springframework.stereotype.Service;

@Service
public class ManagementSoftwareService {
    private final ManagementSoftwareRepository managementSoftwareRepository;

    public ManagementSoftwareService(ManagementSoftwareRepository managementSoftwareRepository) {
        this.managementSoftwareRepository = managementSoftwareRepository;
    }

    // Worker
    public void addProjectManager() {
        managementSoftwareRepository.addProjectManager();
    }

    public void addEmployee() {
        managementSoftwareRepository.addEmployee();
    }

    // Project
    public void addProject() {
        managementSoftwareRepository.addProject();
    }

    public void deleteProject() {
        managementSoftwareRepository.deleteProject();
    }

    public void editProject() {
        managementSoftwareRepository.editProject();
    }

    // Subproject
    public void addSubproject() {
        managementSoftwareRepository.addSubproject();
    }

    public void deleteSubproject() {
        managementSoftwareRepository.deleteSubproject();
    }

    public void editSubproject() {
        managementSoftwareRepository.editSubproject();
    }

    // Task
    public void addTask() {
        managementSoftwareRepository.addTask();
    }

    public void deleteTask() {
        managementSoftwareRepository.deleteTask();
    }

    public void editTask() {
        managementSoftwareRepository.editTask();
    }

    // Check
    public void checkIfDone() {
        managementSoftwareRepository.checkIfDone();
    }
}
