package com.example.doanbanhang.data;

public class User {
    int ID;
    String username;
    String password;
    String name;


    String role;

    public User(int ID, String username, String password, String name,String role) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.role=role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
