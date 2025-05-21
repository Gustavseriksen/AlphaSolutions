package org.example.alphasolutions.models;

public class Admin {

    private int admin_id;
    private String username;
    private String password;

    public Admin(int admin_id, String username, String password) {
        this.admin_id = admin_id;
        this.username = username;
        this.password = password;
    }

    public Admin() {
    }

    // Getter & setter for Admin_id:
    public int getAdmin_id() {
        return admin_id;
    }
    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    // Getter & setter for Name:
    public String getUsername() {
        return username;
    }
    public void setName(String name) {
        this.username = name;
    }

    // Getter & setter for Password:
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
