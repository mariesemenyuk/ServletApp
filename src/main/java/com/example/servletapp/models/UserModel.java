package com.example.servletapp.models;

public class UserModel {
    private int id;
    private String username;
    private String realName;
    private String password;

    public UserModel(int id, String username, String realName, String password) {
        this.id = id;
        this.username = username;
        this.realName = realName;
        this.password = password;
    }

    public UserModel(String username, String realName, String password) {
        this.username = username;
        this.realName = realName;
        this.password = password;
    }

    public UserModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
