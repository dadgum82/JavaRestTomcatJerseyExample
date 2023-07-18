package com.sidequest.parley.dao;

import com.sidequest.parley.util.Config;
import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    private final String DB_URL;

    private Connection connection;

    public SQLiteConnection(String dbEnv) {
        if (dbEnv.equalsIgnoreCase("prod") || dbEnv.equalsIgnoreCase("production")) {
            this.DB_URL = Config.getProperty("db.url");
        } else if (dbEnv.equalsIgnoreCase("test")) {
            this.DB_URL = Config.getProperty("db.urlTest");
        } else {
            throw new IllegalArgumentException("Unexpected dbEnv: " + dbEnv);
        }
        System.out.println(this.getDB_URL());
        connect();
    }

    public SQLiteConnection() {
        this.DB_URL = Config.getProperty("db.url");
        connect();
    }

    private void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            connection = DriverManager.getConnection(DB_URL, config.toProperties());
            // System.out.println("SQLite Driver: " + connection.getMetaData().getDriverName());
            // System.out.println("SQLite URL:     " + connection.getMetaData().getURL());
            // System.out.println("SQLite User:    " + connection.getMetaData().getUserName());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle connection failure
        }
    }

    public Connection getConnection() {
        try {
            if (connection.isClosed()) {
                System.out.println("Connection was closed. Reconnecting...");
                connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle connection failure
        }
        return connection;
    }

    public String getDB_URL() {
        return this.DB_URL;
    }

    public void closeConnection() {
        close();
    }
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
