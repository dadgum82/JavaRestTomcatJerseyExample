package com.sidequest.parley.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    
    private static final String DB_URL = "jdbc:sqlite:path/to/parley.db";
    
    private Connection connection;
    
    public SQLiteConnection() {
        connect();
    }
    
    private void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle connection failure
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle connection closing failure
        }
    }
}
