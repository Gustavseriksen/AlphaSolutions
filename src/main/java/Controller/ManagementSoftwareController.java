package Controller;

import Service.ManagementSoftwareService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String addProjectManager() {
        managementSoftwareService.addProjectManager();
        return "redirect:/"; // redirect to frontpage for now
    }

    @PostMapping("/add-employee")
    public String addEmployee() {
        managementSoftwareService.addEmployee();
        return "redirect:/";
    }

    // Project -----------------------------------------------------------------------------
    @PostMapping("/add-project")
    public String addProject() {
        managementSoftwareService.addProject();
        return "redirect:/";
    }

    @PostMapping("/delete-project")
    public String deleteProject() {
        managementSoftwareService.deleteProject();
        return "redirect:/";
    }

    @PostMapping("/edit-project")
    public String editProject() {
        managementSoftwareService.editProject();
        return "redirect:/";
    }

    // Subproject -------------------------------------------------------------------------
    @PostMapping("/add-subproject")
    public String addSubproject() {
        managementSoftwareService.addSubproject();
        return "redirect:/";
    }

    @PostMapping("/delete-subproject")
    public String deleteSubproject() {
        managementSoftwareService.deleteSubproject();
        return "redirect:/";
    }

    @PostMapping("/edit-subproject")
    public String editSubproject() {
        managementSoftwareService.editSubproject();
        return "redirect:/";
    } // -------------------------------------------------------------------------

    // Task
    @PostMapping("/add-task")
    public String addTask() {
        managementSoftwareService.addTask();
        return "redirect:/";
    }

    @PostMapping("/delete-task")
    public String deleteTask() {
        managementSoftwareService.deleteTask();
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
