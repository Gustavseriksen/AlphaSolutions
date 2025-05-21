package org.example.alphasolutions.rowMappers;

import org.example.alphasolutions.models.Priority;
import org.example.alphasolutions.models.Status;
import org.example.alphasolutions.models.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Task(
                rs.getInt("task_id"),
                rs.getInt("subproject_id"),
                rs.getString("task_name"),
                rs.getString("task_description"),
                Priority.valueOf(rs.getString("task_priority")),
                rs.getDouble("estimated_hours"),
                rs.getDouble("actual_hours_used"),
                Status.valueOf(rs.getString("task_status"))
        );
    }
}