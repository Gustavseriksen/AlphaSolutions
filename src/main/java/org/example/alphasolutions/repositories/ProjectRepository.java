package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.Priority;
import org.example.alphasolutions.models.Project;
import org.example.alphasolutions.models.Status;
import org.example.alphasolutions.repositories.RowMappers.ProjectRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ProjectRepository {
    private JdbcTemplate jdbcTemplate;

    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addProject(Project project) {
        String sql = "INSERT INTO projects (project_name, project_description, start_date, end_date, estimated_hours, actual_hours_used, project_priority, project_status) VALUES(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, project.getProjectName(),
                project.getProjectDescription(),
                project.getProjectStartDate(),
                project.getProjectEndDate(),
                project.getEstimatedHours(),
                project.getActualHoursUsed(),
                project.getProjectPriority().toString(),
                project.getProjectStatus().toString());

    }

    public void deleteProject(int projectId) {
        String sql = "DELETE FROM projects WHERE project_id = ?";
        jdbcTemplate.update(sql, projectId);

    }

    public void editProject(int projectId, Project project) {

        String sql = "UPDATE Projects SET " +
                "project_name = ?, project_description = ?, start_date = ?, " +
                "end_date = ?, estimated_hours = ?, actual_hours_used = ?, " +
                "project_priority = ?, project_status = ? " +
                "WHERE project_id = ?";

        jdbcTemplate.update(sql, project.getProjectName(), project.getProjectDescription(), project.getProjectStartDate(),
                project.getProjectEndDate(), project.getEstimatedHours(), project.getActualHoursUsed(),
                project.getProjectPriority().name(), project.getProjectStatus().name(), projectId);
    }

    public List<Project> getAllProjects() {
        String sql = "SELECT * FROM Projects";

        return jdbcTemplate.query(sql, new ProjectRowMapper());
    }

    public Project getProjectByProjectId(int projectId) {
        String sql = "SELECT * FROM projects WHERE project_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new ProjectRowMapper(), projectId);
        } catch (EmptyResultDataAccessException e) {
            System.err.println("Project with ID " + projectId + " not found. Returning null. Error: " + e.getMessage());
            return null;
        }
    }

    public void updateProject(int projectId, Project project) {
        String sql = "UPDATE Projects SET project_name = ?, project_description = ?, start_date = ?, end_date = ?, estimated_hours = ?, actual_hours_used = ?, project_priority = ?, project_status = ? WHERE project_id = ?";
        jdbcTemplate.update(sql,
                project.getProjectName(),
                project.getProjectDescription(),
                project.getProjectStartDate(),
                project.getProjectEndDate(),
                project.getEstimatedHours(),
                project.getActualHoursUsed(),
                project.getProjectPriority().toString(),
                project.getProjectStatus().toString(),
                projectId
        );
    }


    public List<Project> getProjectsByEmployeeId(int employeeId) {
        String sql = "SELECT p.* FROM Projects p " +
                "JOIN EmployeeProjects ep ON p.project_id = ep.project_id " +
                "WHERE ep.employee_id = ?";

        return jdbcTemplate.query(sql, new ProjectRowMapper(), employeeId);
    }
}
