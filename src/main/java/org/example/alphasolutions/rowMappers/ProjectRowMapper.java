package org.example.alphasolutions.rowMappers;

import org.example.alphasolutions.models.Priority;
import org.example.alphasolutions.models.Project;
import org.example.alphasolutions.models.Status;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class ProjectRowMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Project(
                rs.getInt("project_id"),
                rs.getString("project_name"),
                rs.getString("project_description"),
                rs.getObject("start_date", LocalDate.class),
                rs.getObject("end_date", LocalDate.class),
                rs.getInt("estimated_hours"),
                rs.getInt("actual_hours_used"),
                Priority.valueOf(rs.getString("project_priority")),
                Status.valueOf(rs.getString("project_status"))
        );
    }
}