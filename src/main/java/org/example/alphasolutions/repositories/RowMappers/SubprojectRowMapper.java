package org.example.alphasolutions.repositories.RowMappers;

import org.example.alphasolutions.models.Priority;
import org.example.alphasolutions.models.Status;
import org.example.alphasolutions.models.Subproject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubprojectRowMapper implements RowMapper<Subproject> {

    @Override
    public Subproject mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Subproject(
                rs.getInt("subproject_id"),
                rs.getInt("project_id"),
                rs.getString("subproject_name"),
                rs.getString("subproject_description"),
                Priority.valueOf(rs.getString("subproject_priority")),
                rs.getDate("start_date"),
                rs.getDate("end_date"),
                rs.getDouble("estimated_hours"),
                rs.getDouble("actual_hours_used"),
                Status.valueOf(rs.getString("subproject_status"))
        );
    }
}