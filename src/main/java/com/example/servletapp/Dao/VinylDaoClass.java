package com.example.servletapp.Dao;

import com.example.servletapp.ConnectionInstance;
import com.example.servletapp.models.VinylModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VinylDaoClass implements VinylDao{
    private VinylDaoClass() {
    }

    private static class SingletonHelper {
        private static final VinylDaoClass INSTANCE = new VinylDaoClass();
    }

    public static VinylDaoClass getInstance() {
        return VinylDaoClass.SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<VinylModel> find(String id) throws SQLException {

        String sql = "SELECT * FROM vinyl WHERE id = ?";
        int vinyl_id = 0;
        String author = "";
        String title = "";

        Connection conn = ConnectionInstance.getInstance().connect();

        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setInt(1, Integer.parseInt(id));
        ResultSet resultSet = stat.executeQuery();

        if (resultSet.next()) {
            vinyl_id = resultSet.getInt("id");
            author = resultSet.getString("author");
            title = resultSet.getString("title");
        }

        return Optional.of(new VinylModel(vinyl_id, author, title));
    }

    @Override
    public List<VinylModel> findAll() throws SQLException {
        List<VinylModel> vinylInCollections = new ArrayList<>();
        String sql = "SELECT * FROM vinyl";

        Connection conn = ConnectionInstance.getInstance().connect();
        Statement stat = conn.createStatement();
        ResultSet resultSet = stat.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String author = resultSet.getString("author");
            String title = resultSet.getString("title");

            VinylModel vinyl = new VinylModel(id, author, title);
            vinylInCollections.add(vinyl);
        }
        return vinylInCollections;
    }

    @Override
    public boolean save(VinylModel vinylModel) throws SQLException {
        String sql = "INSERT INTO vinyl (author, title) VALUES (?, ?)";
        boolean rowSaved = false;
        Connection conn = ConnectionInstance.getInstance().connect();
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, vinylModel.getAuthor());
        stat.setString(2, vinylModel.getTitle());
        rowSaved = stat.executeUpdate() > 0;
        return rowSaved;
    }

    @Override
    public boolean update(VinylModel vinylModel) throws SQLException {

        String sql = "UPDATE vinyl SET author = ?, title = ?";
        sql += " WHERE id = ?";
        boolean rowUpdated = false;
        Connection conn = ConnectionInstance.getInstance().connect();
        PreparedStatement stat = conn.prepareStatement(sql);
        stat.setString(1, vinylModel.getAuthor());
        stat.setString(2, vinylModel.getTitle());
        stat.setInt(3, vinylModel.getId());
        rowUpdated = stat.executeUpdate() > 0;
        return rowUpdated;

    }

    @Override
    public boolean delete(String id) throws SQLException {

        String sql = "DELETE FROM vinyl WHERE id = ?";
        boolean rowDeleted = false;

        Connection conn = ConnectionInstance.getInstance().connect();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(id));
        rowDeleted = statement.executeUpdate() > 0;

        return rowDeleted;
    }

}
