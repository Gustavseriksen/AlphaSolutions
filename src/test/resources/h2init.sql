DROP TABLE IF EXISTS Tasks;
DROP TABLE IF EXISTS Subprojects;
DROP TABLE IF EXISTS EmployeeProjects;
DROP TABLE IF EXISTS Projects;
DROP TABLE IF EXISTS Employees;
DROP TABLE IF EXISTS ProjectManagers;
DROP TABLE IF EXISTS Admins;

CREATE TABLE Admins (
                        admin_id INT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(50) NOT NULL UNIQUE,
                        password VARCHAR(50) NOT NULL
);

CREATE TABLE ProjectManagers (
                                 manager_id INT AUTO_INCREMENT PRIMARY KEY,
                                 username VARCHAR(50) NOT NULL UNIQUE,
                                 password VARCHAR(50) NOT NULL
);

CREATE TABLE Employees (
                           employee_id INT AUTO_INCREMENT PRIMARY KEY,
                           username VARCHAR(50) NOT NULL UNIQUE,
                           password VARCHAR(50) NOT NULL
);

CREATE TABLE Projects (
                          project_id INT AUTO_INCREMENT PRIMARY KEY,
                          project_name VARCHAR(50) NOT NULL,
                          project_description VARCHAR(255) NOT NULL,
                          start_date DATE NOT NULL,
                          end_date DATE NOT NULL,
                          estimated_hours INT NOT NULL,
                          actual_hours_used INT NOT NULL,
                          project_priority VARCHAR(50) NOT NULL,
                          project_status VARCHAR(50) NOT NULL
);

CREATE TABLE EmployeeProjects (
                                  employee_id INT NOT NULL,
                                  project_id INT NOT NULL,
                                  PRIMARY KEY (employee_id, project_id),
                                  FOREIGN KEY (employee_id) REFERENCES Employees(employee_id) ON DELETE CASCADE,
                                  FOREIGN KEY (project_id) REFERENCES Projects(project_id) ON DELETE CASCADE
);

CREATE TABLE Subprojects (
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

CREATE TABLE Tasks (
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

-- Insert data
INSERT INTO Admins (username, password) VALUES ('ADM_bert', '123');
INSERT INTO ProjectManagers (username, password) VALUES ('PM_bert', '123');
INSERT INTO Employees (username, password) VALUES ('EMP_bert', '123');

INSERT INTO Projects (project_name, project_description, start_date, end_date, estimated_hours, actual_hours_used, project_priority, project_status)
VALUES ('Project test', 'This is a project description test', '2025-04-01', '2025-06-01', 120, 95, 'HIGH', 'COMPLETED');

-- Make sure employee_id and project_id exist
INSERT INTO EmployeeProjects (employee_id, project_id) VALUES (1, 1);

INSERT INTO Subprojects (project_id, subproject_name, subproject_description, subproject_priority, estimated_hours, actual_hours_used, subproject_status)
VALUES (1, 'Subproject A', 'Subproject test description', 'MEDIUM', 40, 38, 'COMPLETED');

INSERT INTO Tasks (subproject_id, task_name, task_description, task_priority, estimated_hours, actual_hours_used, task_status)
VALUES (1, 'Task A', 'Task test description', 'LOW', 10, 9, 'COMPLETED');
