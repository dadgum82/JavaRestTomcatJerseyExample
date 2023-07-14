package com.sidequest.parley.dao;

import com.sidequest.parley.model.ChatRoomUsers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DbChatRoomUsersImpl implements ChatRoomUsersDao {
    private SQLiteConnection dbConnection;
    private Connection connection;
    Statement statement;

    public DbChatRoomUsersImpl(String dbEnv) {
        dbConnection = new SQLiteConnection(dbEnv);
        connection = dbConnection.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ChatRoomUsers> getAllChatRoomUsers(int chatRoomId) {
        return null;
    }

    @Override
    public void createChatRoomUsers(ChatRoomUsers chatRoomUsers) {

    }

    @Override
    public void updateChatRoomUsers(ChatRoomUsers chatRoomUsers) {

    }

    @Override
    public void deleteChatRoomUsers(ChatRoomUsers chatRoomUsers) {

    }

    /**
     * This method drops the chat_room_users table.
     */
    @Override
    public void dropChatRoomUsersTable() {
        try {
            statement.execute(SchemaChatRoomUsersSql.DROP_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates the chat_room_users table.
     * The table has 2 columns: chat_room_id and user_id.
     * The chatRoomId column is a foreign key to the chat_room table.
     * The userId column is a foreign key to the users table.
     */
    @Override
    public void createChatRoomUsersTable() {
        try {
            System.out.println("CREATE_TABLE: " + SchemaChatRoomUsersSql.CREATE_TABLE);
            statement.execute(SchemaChatRoomUsersSql.CREATE_TABLE);
            System.out.println("CREATE_TABLE is done...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
