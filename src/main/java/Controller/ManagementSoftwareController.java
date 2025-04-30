package Controller;

import Model.*;
import Service.ManagementSoftwareService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ManagementSoftwareController {
    private final ManagementSoftwareService managementSoftwareService;

    public ManagementSoftwareController(ManagementSoftwareService managementSoftwareService) {
        this.managementSoftwareService = managementSoftwareService;
    }

    // Front Page View
    @GetMapping("")
    public String viewFrontPage() {
        return "index";
    }

    // Adding a pm & emp ---------------------------------------------------------------------
    @PostMapping("/add-project-manager")
    public String addProjectManager(@ModelAttribute ProjectManager projectManager) {
        managementSoftwareService.addProjectManager(projectManager);
        return "redirect:/"; // redirect to frontpage for now
    }

    @PostMapping("/add-employee")
    public String addEmployee(@ModelAttribute Employee employee) {
        managementSoftwareService.addEmployee(employee);
        return "redirect:/";
    }

    // Project -----------------------------------------------------------------------------
    @PostMapping("/add-project")
    public String addProject(@ModelAttribute Project project) {
        managementSoftwareService.addProject(project);
        return "redirect:/";
    }

    @PostMapping("/delete-project/{projectId}")
    public String deleteProject(@PathVariable int projectId) {
        managementSoftwareService.deleteProject(projectId);
        return "redirect:/";
    }

    @PostMapping("/edit-project")
    public String editProject() {
        managementSoftwareService.editProject();
        return "redirect:/";
    }

    // Subproject -------------------------------------------------------------------------
    @PostMapping("/add-subproject")
    public String addSubproject(@ModelAttribute Subproject subproject) {
        managementSoftwareService.addSubproject(subproject);
        return "redirect:/";
    }

    @PostMapping("/delete-subproject/{subprojectId}")
    public String deleteSubproject(@PathVariable int subprojectId) {
        managementSoftwareService.deleteSubproject(subprojectId);
        return "redirect:/";
    }

    @PostMapping("/edit-subproject/{subprojectId}")
    public String editSubproject(@PathVariable int subprojectId, Subproject subproject) {
        managementSoftwareService.editSubproject(subprojectId, subproject);
        return "redirect:/";
    } // -------------------------------------------------------------------------

    // Task
    @PostMapping("/add-task")
    public String addTask(@ModelAttribute Task task) {
        managementSoftwareService.addTask(task);
        return "redirect:/";
    }

    @PostMapping("/delete-task/{taskId}")
    public String deleteTask(@PathVariable int taskId) {
        managementSoftwareService.deleteTask(taskId);
        return "redirect:/";
    }

    @PostMapping("/edit-task")
    public String editTask() {
        managementSoftwareService.editTask();
        return "redirect:/";
    }

    // Check if done
    @GetMapping("/check-if-done")
    public String checkIfDone(Model model) {
        managementSoftwareService.checkIfDone();
        return "index";
    }
}
