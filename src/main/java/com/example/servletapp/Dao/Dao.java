package com.example.servletapp.Dao;

import com.example.servletapp.models.UserVinylModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao <T, ID> {

    Optional<T> find(ID id) throws SQLException;
    List<T> findAll() throws SQLException;
    boolean save(T o) throws SQLException;
    boolean update(T o) throws SQLException;
    boolean delete(ID id) throws SQLException;
}
