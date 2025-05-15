/*package org.example.alphasolutions.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.alphasolutions.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    // CHECK CREDENTIALS -----------------------------------------------------------------------------



    // ADMIN FRONTPAGE -----------------------------------------------------------------------------



    // ADMIN PROJECT MANAGERS ENDPOINTS/METHODS -----------------------------------------------------------------------------





    // ADMIN EMPLOYEE ENDPOINTS/METHODS ---------------------------------------------------------------------







    // ADMIN END -----------------------------------------------------------------------------


    //PROJECT MANAGER PROJECT ------------------------------------------------------------------------



    // Project -----------------------------------------------------------------------------
    @PostMapping("/add-project")
    public String addProject(@ModelAttribute Project project) {
        managementSoftwareService.addProject(project);
        return "redirect:/alphaSolutions";
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



    // PROJECT MANAGER SUBPROJECT -------------------------------------------------------------------------



    // -------------------------------------------------------------------------

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

//    // Check if done
//    @GetMapping("/check-if-done")
//    public String checkIfDone(Model model) {
//        managementSoftwareService.checkIfDone();
//        return "index";
//    }
//}
*/