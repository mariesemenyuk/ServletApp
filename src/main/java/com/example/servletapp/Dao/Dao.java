package com.example.servletapp.Dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao <T, ID> {

    T find(ID id) throws SQLException;
    List<T> findAll() throws SQLException;
    void save(T o) throws SQLException;
    void update(T o) throws SQLException;
    void delete(ID id) throws SQLException;
}
