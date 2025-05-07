package org.example.alphasolutions.Model;

public class Employee {

    private int employee_id;
    private String username;
    private String password;

    public Employee(int employee_id, String username, String password) {
        this.employee_id = employee_id;
        this.username = username;
        this.password = password;
    }
    public Employee() {}

    // Getter & setter for employee_id:
    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    // Getter & setter for Name:
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter & setter for Password:
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
