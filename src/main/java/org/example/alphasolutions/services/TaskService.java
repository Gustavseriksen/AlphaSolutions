package org.example.alphasolutions.services;

import org.example.alphasolutions.models.Task;
import org.example.alphasolutions.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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

}
