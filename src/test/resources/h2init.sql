-- Create schema instead of database (H2 uses schema for namespace separation)
CREATE SCHEMA IF NOT EXISTS ManagementSoftware;

SET SCHEMA ManagementSoftware;

-- Create tables with H2-compatible syntax
CREATE TABLE IF NOT EXISTS Admins (
                                      admin_id INT AUTO_INCREMENT PRIMARY KEY,
                                      username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS ProjectManagers (
                                               manager_id INT AUTO_INCREMENT PRIMARY KEY,
                                               username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS Employees (
                                         employee_id INT AUTO_INCREMENT PRIMARY KEY,
                                         username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS Projects (
                                        project_id INT AUTO_INCREMENT PRIMARY KEY,
                                        project_name VARCHAR(50) NOT NULL,
    project_description VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    estimated_hours INT NOT NULL,
    actual_hours_used INT NOT NULL,
    project_status VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS EmployeeProjects (
                                                employee_id INT NOT NULL,
                                                project_id INT NOT NULL,
                                                PRIMARY KEY (employee_id, project_id),
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES Projects(project_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS Subprojects (
                                           subproject_id INT AUTO_INCREMENT PRIMARY KEY,
                                           project_id INT NOT NULL,
                                           subproject_name VARCHAR(50) NOT NULL,
    subproject_description VARCHAR(255) NOT NULL,
    subproject_priority VARCHAR(50) NOT NULL,
    estimated_hours INT NOT NULL,
    actual_hours_used INT NOT NULL,
    subproject_status VARCHAR(50) NOT NULL,
    FOREIGN KEY (project_id) REFERENCES Projects(project_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS Tasks (
                                     task_id INT AUTO_INCREMENT PRIMARY KEY,
                                     subproject_id INT NOT NULL,
                                     task_name VARCHAR(50) NOT NULL,
    task_description VARCHAR(255) NOT NULL,
    task_priority VARCHAR(50) NOT NULL,
    estimated_hours INT NOT NULL,
    actual_hours_used INT NOT NULL,
    task_status VARCHAR(50) NOT NULL,
    FOREIGN KEY (subproject_id) REFERENCES Subprojects(subproject_id) ON DELETE CASCADE
    );

-- Insert test data
INSERT INTO Admins (username, password) VALUES
                                            ('ADM_bert', '123'),
                                            ('ADM_alice', 'secure123'),
                                            ('ADM_john', 'adminpass');

INSERT INTO ProjectManagers (username, password) VALUES
                                                     ('PM_bert', '123'),
                                                     ('PM_sarah', 'manager123'),
                                                     ('PM_mike', 'pmsecure');

INSERT INTO Employees (username, password) VALUES
                                               ('EMP_bert', '123'),
                                               ('EMP_lisa', 'emplisa'),
                                               ('EMP_david', 'david123'),
                                               ('EMP_emily', 'emily456'),
                                               ('EMP_robert', 'robert789');