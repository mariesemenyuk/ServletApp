package com.example.servletapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionInstance {

    private static ConnectionInstance instance;

    private ConnectionInstance() {}

    public static ConnectionInstance getInstance() {
        if (instance == null) {
            instance = new ConnectionInstance();
        }
        return instance;
    }

    public Connection connect() {

        Properties property = new Properties();
        Connection conn = null;

        try (FileInputStream fis = new FileInputStream("C:/Users/maria/IdeaProjects/ServletApp/src/main/resources/config.properties")) {
            property.load(fis);
            String url = property.getProperty("url");
            String user = property.getProperty("user");
            String password = property.getProperty("password");

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException | IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
