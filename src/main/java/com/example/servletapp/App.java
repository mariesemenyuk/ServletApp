package com.example.servletapp;

public class App {

    public static void main(String[] args) {
        App app = new App();
        ConnectionInstance connection = ConnectionInstance.getInstance();
        connection.connect();
    }
}