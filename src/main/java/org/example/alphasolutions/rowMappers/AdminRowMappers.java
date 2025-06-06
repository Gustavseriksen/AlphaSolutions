package org.example.alphasolutions.rowMappers;

import org.example.alphasolutions.models.Admin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRowMappers implements RowMapper<Admin> {
    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Admin(
                rs.getInt("admin_id"),
                rs.getString("username"),
                rs.getString("password")
        );
    }
}
