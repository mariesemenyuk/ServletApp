package com.example.servletapp.Dao;

import com.example.servletapp.ConnectionInstance;

import com.example.servletapp.models.VinylModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserVinylDaoClass implements UserVinylDao{
    private UserVinylDaoClass() {
    }

    private static class SingletonHelper {
        private static final UserVinylDaoClass INSTANCE = new UserVinylDaoClass();
    }

    public static UserVinylDaoClass getInstance() {
        return UserVinylDaoClass.SingletonHelper.INSTANCE;
    }

    @Override
    public List<VinylModel> find(Integer user_id) throws SQLException {
        List<VinylModel> vinylInCollections = new ArrayList<>();

        String sql = "SELECT vinyl.id, vinyl.author, vinyl.title FROM user_vinyls " +
                "LEFT JOIN vinyl " +
                "ON user_vinyls.vinyl_id = vinyl.id " +
                "WHERE user_id = ?";

        int vinyl_id = 0;
        String author = "";
        String title = "";

        Connection conn = ConnectionInstance.getInstance().connect();

        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setInt(1, user_id);
        ResultSet resultSet = stat.executeQuery();

        while (resultSet.next()) {
            vinyl_id = resultSet.getInt("id");
            author = resultSet.getString("author");
            title = resultSet.getString("title");

            VinylModel vinyl = new VinylModel(vinyl_id, author, title);
            vinylInCollections.add(vinyl);
        }

        return vinylInCollections;

    }

    @Override
    public boolean save(String username, String title) throws SQLException {

        String sql = "INSERT INTO user_vinyls (user_id, vinyl_id) " +
                "SELECT users.id, vinyl.id" +
                " FROM users, vinyl" +
                " WHERE users.username = ? AND vinyl.title = ?";

        Connection conn = ConnectionInstance.getInstance().connect();
        PreparedStatement stat = conn.prepareStatement(sql);
        boolean rowSaved = false;
        stat.setString(1, username);
        stat.setString(2, title);
        rowSaved = stat.executeUpdate() > 0;
        return rowSaved;
    }

    @Override
    public boolean saveNew(String username, String author, String title) throws SQLException {

        boolean rowSaved = false;
        boolean rowVinylSaved = false;
        Connection conn = ConnectionInstance.getInstance().connect();
        conn.setAutoCommit(false);

        String sql = "INSERT INTO vinyl (author, title) VALUES (?, ?)";

        String sql2 = "INSERT INTO user_vinyls (user_id, vinyl_id) " +
                "SELECT users.id, vinyl.id" +
                " FROM users, vinyl" +
                " WHERE users.username = ? AND vinyl.title = ?";

        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, author);
            stat.setString(2, title);

            rowVinylSaved = stat.executeUpdate() > 0;

            stat = conn.prepareStatement(sql2);
            stat.setString(1, username);
            stat.setString(2, title);
            rowSaved = stat.executeUpdate() > 0;

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }

        return rowSaved && rowVinylSaved;
    }

    @Override
    public boolean delete(Integer user_id, Integer vinyl_id) throws SQLException {

        String sql = "DELETE FROM user_vinyls WHERE user_id = ? AND vinyl_id = ?";
        boolean rowDeleted = false;

        Connection conn = ConnectionInstance.getInstance().connect();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, user_id);
        statement.setInt(2, vinyl_id);
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
