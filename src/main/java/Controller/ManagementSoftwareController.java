package Controller;

import Model.*;
import Service.ManagementSoftwareService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/alphaSolutions")
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

    // Admin -----------------------------------------------------------------------------

    @PostMapping("/checkcredentials")
    public String checkCredentials(@RequestParam String username, @RequestParam String password, HttpSession session) {

        Admin admin = managementSoftwareService.checkAdminCredentials(username, password);

        if (admin != null) {
            session.setAttribute("ID", admin.getAdmin_id());
            return "redirect:/admin-frontpage";
        } else {
            return "redirect:/index"; // Reload login page with an error message
        }
    }

    @GetMapping("/admin-frontpage")
    public String viewAdminFrontPage() {
        return "admin-frontpage";
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
/*
    @PostMapping("/edit-task")
    public String editTask() {
        managementSoftwareService.editTask();
        return "redirect:/";
    }
*/
//    // Check if done
//    @GetMapping("/check-if-done")
//    public String checkIfDone(Model model) {
//        managementSoftwareService.checkIfDone();
//        return "index";
//    }
}
