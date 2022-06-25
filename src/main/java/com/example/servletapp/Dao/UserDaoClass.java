package com.example.servletapp.Dao;

import com.example.servletapp.ConnectionInstance;
import com.example.servletapp.models.UserModel;
import com.example.servletapp.models.UserVinylModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoClass implements UserDao{
    private UserDaoClass() {
    }

    private static class SingletonHelper {
        private static final UserDaoClass INSTANCE = new UserDaoClass();
    }

    public static UserDaoClass getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<UserModel> find(String s) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<UserModel> findAll() throws SQLException {
        List<UserModel> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        Connection conn = ConnectionInstance.getInstance().connect();
        Statement stat = conn.createStatement();
        ResultSet resultSet = stat.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String realName = resultSet.getString("real_name");
            String password = resultSet.getString("password");

            UserModel vinyl = new UserModel(id, username, realName, password);
            users.add(vinyl);
        }
        return users;
    }

    @Override
    public boolean save(UserModel userModel) throws SQLException {
        String sql = "INSERT INTO users (username, real_name, password) VALUES (?, ?, ?)";
        boolean rowUpdated = false;
        Connection conn = ConnectionInstance.getInstance().connect();
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, userModel.getUsername());
        stat.setString(2, userModel.getRealName());
        stat.setString(3, userModel.getPassword());
        rowUpdated = stat.executeUpdate() > 0;
        return rowUpdated;
    }

    @Override
    public boolean update(UserModel o) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        boolean rowDeleted = false;

        Connection conn = ConnectionInstance.getInstance().connect();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(id));
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
