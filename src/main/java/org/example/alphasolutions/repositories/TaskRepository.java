package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.Priority;
import org.example.alphasolutions.models.Status;
import org.example.alphasolutions.models.Subproject;
import org.example.alphasolutions.models.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {
    private JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addTask(Task task) {
        String sql = "INSERT INTO Tasks (subproject_id, task_name, task_description, task_priority," +
                "estimated_hours, actual_hours_used, task_status) " +
                "VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, task.getSubProjectId(), task.getTaskName(), task.getTaskDescription(),
                task.getPriority().name(), task.getEstimatedHours(),
                task.getActualUsedHours(), task.getStatus().name());
    }

    public void deleteTask(int taskId) {
        String sql = "DELETE FROM Tasks WHERE task_id = ?";
        jdbcTemplate.update(sql, taskId);
    }

    public void editTask(int taskId, Task task) {
        String sql = "UPDATE Tasks SET " +
                "task_name = ?, task_description = ?, task_priority = ?, " +
                " estimated_hours = ?, actual_hours_used = ?, task_status = ? " +
                "WHERE task_id = ?";
        jdbcTemplate.update(sql, task.getTaskName(), task.getTaskDescription(), task.getPriority().name(),
                task.getEstimatedHours(), task.getActualUsedHours(), task.getStatus().name(), taskId);
    }

    public List<Task> getTasksBySubId(int subId) {
        String sql = "SELECT * FROM Tasks WHERE subproject_id = ?";

        return jdbcTemplate.query(sql, new Object[]{subId}, (rs, rowNum) -> new Task(
                rs.getInt("task_id"),
                rs.getInt("subproject_id"),
                rs.getString("task_name"),
                rs.getString("task_description"),
                Priority.valueOf(rs.getString("task_priority")),
                rs.getInt("estimated_hours"),
                rs.getInt("actual_hours_used"),
                Status.valueOf(rs.getString("task_status"))));
    }

    public Task getTaskByTaskId(int taskId) {
        String sql = "SELECT * FROM Tasks WHERE task_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{taskId}, (rs, rowNum) -> new Task(
                rs.getInt("task_id"),
                rs.getInt("subproject_id"),
                rs.getString("task_name"),
                rs.getString("task_description"),
                Priority.valueOf(rs.getString("task_priority")),
                rs.getInt("estimated_hours"),
                rs.getInt("actual_hours_used"),
                Status.valueOf(rs.getString("task_status"))));
    }

}

