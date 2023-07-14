package com.sidequest.parley.dao;

import com.sidequest.parley.model.ChatMessage;
import com.sidequest.parley.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the implementation of the ChatMessageDao interface.
 * It is responsible for handling the database interactions for the ChatMessage model.
 *
 * @see com.sidequest.parley.model.ChatMessage
 * @see com.sidequest.parley.dao.ChatMessageDao
 */
public class DbChatMessageDaoImpl implements ChatMessageDao {
    private SQLiteConnection dbConnection;
    private Connection connection;
    Statement statement;
    String dbEnv;

    public DbChatMessageDaoImpl(String dbEnv) {
        this.dbEnv = dbEnv;
        dbConnection = new SQLiteConnection(dbEnv);
        connection = dbConnection.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ChatMessage> getAllChatMessages(int id) {
        List<ChatMessage> chatMessages = new ArrayList<>();
        DbUserDaoImpl userDao = new DbUserDaoImpl(dbEnv);

        try {
            PreparedStatement statement = connection.prepareStatement(SchemaChatMessageSql.SELECT_ALL_CHAT_MESSAGES_BY_CHAT_ROOM_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int rsID = resultSet.getInt("id");
                int rsChatRoomId = resultSet.getInt("chatRoomId");
                String rsContent = resultSet.getString("content");
                int rsUserId = resultSet.getInt("userId");
                String rsTimestamp = resultSet.getString("timestamp");
                System.out.println("---id: " + rsID + " userId: " + rsUserId + " content: " + rsContent + " timestamp: " + rsTimestamp);
                LocalDateTime ldt = LocalDateTime.parse(rsTimestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                User user = userDao.getUserById(rsUserId);
                //int id, int chatRoomId, LocalDateTime timestamp, User sender, String content
                chatMessages.add(new ChatMessage(rsID, rsChatRoomId, ldt, user, rsContent));
            }
            return chatMessages;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database access error
        }

        return chatMessages;
    }

    @Override
    public void createChatMessage(ChatMessage chatMessage) throws SQLException {
        System.out.println("CREATE chat message");

            //"INSERT INTO chat_message (id, chatRoomId, senderId, content, timestamp) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(SchemaChatMessageSql.INSERT_CHAT_MESSAGE);
            statement.setInt(1, chatMessage.getId());
            statement.setInt(2, chatMessage.getChatRoomId());
            statement.setInt(3, chatMessage.getUser().getId());
            statement.setString(4, chatMessage.getContent());
            statement.setString(5, chatMessage.getTimestamp().toString());
            System.out.println("INSERT_CHAT_MESSAGE: " + SchemaChatMessageSql.INSERT_CHAT_MESSAGE);
            statement.executeUpdate();
            System.out.println("INSERT_CHAT_MESSAGE is done...");

    }

    @Override
    public void updateChatMessage(ChatMessage chatMessage) {
        System.out.println("UPDATE chat message");
        try {
            PreparedStatement statement = connection.prepareStatement(SchemaChatMessageSql.UPDATE_CHAT_MESSAGE);
            statement.setInt(1, chatMessage.getId());
            statement.setString(2, chatMessage.getContent());
            statement.setString(3, chatMessage.getTimestamp().toString());
            System.out.println("UPDATE_CHAT_MESSAGE: " + SchemaChatMessageSql.UPDATE_CHAT_MESSAGE);
            statement.executeUpdate();
            System.out.println("UPDATE_CHAT_MESSAGE is done...");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database access error
        }
    }

    @Override
    public void deleteChatMessage(ChatMessage chatMessage) {
        System.out.println("DELETE chat message");
        try {
            PreparedStatement statement = connection.prepareStatement(SchemaChatMessageSql.DELETE_CHAT_MESSAGE);
            statement.setInt(1, chatMessage.getId());
            System.out.println("DELETE_CHAT_MESSAGE: " + SchemaChatMessageSql.DELETE_CHAT_MESSAGE);
            statement.executeUpdate();
            System.out.println("DELETE_CHAT_MESSAGE is done...");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database access error
        }
    }

    @Override
    public void dropChatMessageTable() {
        System.out.println("DROP chat_message table");
        try (PreparedStatement statement = connection.prepareStatement(SchemaChatMessageSql.DROP_TABLE)) {
            statement.executeUpdate();
            System.out.println("DROP_TABLE is done...");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database access error
        }
    }

    @Override
    public void createChatMessageTable() {
        System.out.println("CREATE chat message table");
        try{
            PreparedStatement statement = connection.prepareStatement(SchemaChatMessageSql.CREATE_TABLE);
            System.out.println("CREATE_TABLE: " + SchemaChatMessageSql.CREATE_TABLE);
            statement.executeUpdate();
            System.out.println("CREATE_TABLE is done...");

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database access error
        }
    }

}
