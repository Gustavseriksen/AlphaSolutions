
package org.example.alphasolutions.services;

import org.example.alphasolutions.models.Project;
import org.example.alphasolutions.models.Subproject;
import org.example.alphasolutions.models.Task;
import org.example.alphasolutions.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final SubprojectService subprojectService;
    private final ProjectService projectService;

    public TaskService(TaskRepository taskRepository, SubprojectService subprojectService, ProjectService projectService) {
        this.taskRepository = taskRepository;
        this.subprojectService = subprojectService;
        this.projectService = projectService;
    }

    public void addTask(Task task) {
        taskRepository.addTask(task);
    }

    public void deleteTask(int taskId) {
        taskRepository.deleteTask(taskId);
    }

    public void editTask(int taskId, Task task) {
        taskRepository.editTask(taskId, task);
    }

    public List<Task> getTasksBySubId(int subId) {
        return taskRepository.getTasksBySubId(subId);
    }

    public Task getTaskByTaskId(int taskId) {
        return taskRepository.getTaskByTaskId(taskId);
    }

    // @Transactional: if one of the methods fail, its rolls everything back. (no inconsistencies in actual hours)
    @Transactional
    public boolean updateActualHours(int taskId, int number) {
        Task task = taskRepository.getTaskByTaskId(taskId);

        double newTaskHours = task.getActualUsedHours() + number;
        if (newTaskHours < 0) return false;
        task.setActualUsedHours(newTaskHours);
        taskRepository.editTask(taskId, task);


        Subproject subproject = subprojectService.getSubprojectBySubId(task.getSubProjectId());
        // Math.max is used as a 'failsafe', so that actual hours for sub never goes negative
        subproject.setActualHoursUsed(Math.max(0, subproject.getActualHoursUsed() + number));
        subprojectService.editSubproject(subproject.getSubProjectId(), subproject);


        Project project = projectService.getProjectByProjectId(subproject.getProjectId());
        project.setActualHoursUsed(Math.max(0, project.getActualHoursUsed() + number));
        projectService.editProject(project.getProjectId(), project);

        return true;
    }

}
