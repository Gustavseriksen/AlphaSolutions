package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.*;
import org.example.alphasolutions.repositories.RowMappers.AdminRowMappers;
import org.example.alphasolutions.repositories.RowMappers.EmployeeRowMappers;
import org.example.alphasolutions.repositories.RowMappers.ProjectManagerRowMappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ManagementSoftwareRepository {

    private JdbcTemplate jdbcTemplate;

    public ManagementSoftwareRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //ADMIN -------------------------------------------------------------------------------------

    // ADMIN PROJECT MANAGER -------------------------------------------------------------------------------------










    // ADMIN EMPLOYEE -------------------------------------------------------------------------------------










    // ADMIN END -------------------------------------------------------------------------------------

    //PROJECT MANAGER, PROJECT -------------------------------------------------------------------------------------










    // PROJECT MANAGER, SUBPROJECT -------------------------------------------------------------------------------------


    //Task:












}
