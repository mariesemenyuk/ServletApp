package com.example.servletapp.Dao;

import com.example.servletapp.models.UserVinylModel;
import com.example.servletapp.models.VinylModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserVinylDao {

    List<VinylModel> find(Integer user_id) throws SQLException;
    boolean save(String username, String title) throws SQLException;
    boolean saveNew(String username, String author, String title) throws SQLException;
    boolean delete(Integer user_id, Integer vinyl_id) throws SQLException;
}
