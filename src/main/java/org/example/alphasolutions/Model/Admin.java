package org.example.alphasolutions.Model;

public class Admin {

    private int admin_id;
    private String name;
    private String password;

    public Admin(int admin_id, String name, String password) {
        this.admin_id = admin_id;
        this.name = name;
        this.password = password;
    }


    // Getter & setter for Admin_id:
    public int getAdmin_id() {
        return admin_id;
    }
    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    // Getter & setter for Name:
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getter & setter for Password:
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
