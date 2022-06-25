package com.example.servletapp.Dao;

import com.example.servletapp.ConnectionInstance;
import com.example.servletapp.models.UserVinylModel;
import com.example.servletapp.models.VinylModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        String sql = "SELECT vinyl.author, vinyl.title FROM user_vinyls " +
                "LEFT JOIN vinyl " +
                "ON user_vinyls.vinyl_id = vinyl.id " +
                "WHERE user_id = ?";

        String author = "";
        String title = "";

        Connection conn = ConnectionInstance.getInstance().connect();

        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setInt(1, user_id);
        ResultSet resultSet = stat.executeQuery();

        if (resultSet.next()) {
            author = resultSet.getString("author");
            title = resultSet.getString("title");

            VinylModel vinyl = new VinylModel(author, title);
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
    public boolean delete(Integer vinyl_id) throws SQLException {

        //String sql = "DELETE FROM user_vinyls WHERE id = ?";
        boolean rowDeleted = false;

//        Connection conn = ConnectionInstance.getInstance().connect();
//        PreparedStatement statement = conn.prepareStatement(sql);
//        statement.setInt(1, Integer.parseInt(id));
//        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }
}
