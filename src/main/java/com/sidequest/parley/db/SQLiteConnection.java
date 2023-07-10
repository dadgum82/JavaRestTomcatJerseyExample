package com.sidequest.parley.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sidequest.parley.util.Config;

public class SQLiteConnection {
    private final String DB_URL;
    
    private Connection connection;
    
    public SQLiteConnection() {
    	this.DB_URL = Config.getProperty("db.url");
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
