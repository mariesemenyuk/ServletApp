package com.example.servletapp.models;

public class UserVinylModel {
    Integer user_id;
    Integer vinyl_id;

    public UserVinylModel(Integer user_id, Integer vinyl_id) {
        this.user_id = user_id;
        this.vinyl_id = vinyl_id;
    }

    public UserVinylModel() {
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getVinyl_id() {
        return vinyl_id;
    }

    public void setVinyl_id(Integer vinyl_id) {
        this.vinyl_id = vinyl_id;
    }
}
