package com.sidequest.parley.dao;

import com.sidequest.parley.model.ChatRoom;
import com.sidequest.parley.model.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the ChatRoomDao interface.
 * It is used to get hold information about a chat room.
 * A chat room is a group of users who can chat with each other.
 * It has the following fields:
 * id: the id of the chat room
 * name: the name of the chat room
 * moderatorId: the id of the user who is the moderator of the chat room
 * icon: the icon of the chat room
 * chatMessageId: the id of the chat messages in the chat room
 * userIds: the ids of the users in the chat room
 * *
 */
public class DbChatRoomDaoImpl implements ChatRoomDao {
    Statement statement;
    private SQLiteConnection dbConnection;
    private Connection connection;
    private String dbEnv;

    public DbChatRoomDaoImpl(String dbEnv) {
        dbConnection = new SQLiteConnection(dbEnv);
        connection = dbConnection.getConnection();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ChatRoom getChatRoom(int id) {
        return null;
    }

    @Override
    public List<ChatRoom> getAllChatRooms() {
        List<ChatRoom> chatRooms = new ArrayList<>();
        try {
            String[] arrUserIds;
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SchemaChatRoomSql.SELECT_ALL_CHAT_ROOMS);
            //"INSERT INTO chat_room (chatRoomId, name, moderatorId, userIds, icon) VALUES (?, ?, ?, ?, ?)";
            while (rs.next()) {
                int chatRoomId = rs.getInt("chatRoomId");
                String name = rs.getString("name");
                byte[] icon = rs.getBytes("icon");
                int moderatorId = rs.getInt("moderatorId");

                chatRooms.add(new ChatRoom(chatRoomId, name, moderatorId, icon, getUserIdsByChatRoomId(chatRoomId)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chatRooms;
    }

    @Override
    public List<Integer> getUserIdsByChatRoomId(int chatRoomId) {
        List<Integer> arrUserIds = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SchemaChatRoomUsersSql.SELECT_CHAT_ROOM_USERS_BY_CHAT_ROOM_ID);
            statement.setInt(1, chatRoomId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userId");
                arrUserIds.add(userId);
            }
            return arrUserIds;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrUserIds;
    }

    @Override
    public void addUserToChatRoom(int chatRoomId, int userId) {
        try {
            PreparedStatement statement = connection.prepareStatement(SchemaChatRoomUsersSql.INSERT_CHAT_ROOM_USERS);
            statement.setInt(1, chatRoomId);
            statement.setInt(2, userId);
            statement.executeUpdate();
            System.out.println("INSERT_CHAT_ROOM_USERS is done...");

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database access error
        }
    }

    @Override
    public void addUsersToChatRoom(int chatRoomId, List<Integer> userIds) {
        System.out.println("INSERT_CHAT_ROOM_USERS is called...");
        System.out.println("userIds Size: " + userIds.size());
        try {
            PreparedStatement statement = connection.prepareStatement(SchemaChatRoomUsersSql.INSERT_CHAT_ROOM_USERS);

            for (int userId : userIds) {
                statement.setInt(1, chatRoomId);
                statement.setInt(2, userId);
                System.out.println(statement.toString());
                statement.executeUpdate();
            }
            System.out.println("INSERT_CHAT_ROOM_USERS is done...");

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database access error
        }
    }

    @Override
    public void createChatRoom(ChatRoom chatRoom) {
        try {
            PreparedStatement statement = connection.prepareStatement(SchemaChatRoomSql.INSERT_CHAT_ROOM);
            statement.setInt(1, chatRoom.getChatRoomId());
            statement.setString(2, chatRoom.getName());
            statement.setInt(3, chatRoom.getModeratorId());
            statement.setBytes(4, chatRoom.getIcon());
            statement.executeUpdate();
            System.out.println("INSERT_CHAT_ROOM is done...");
            addUsersToChatRoom(chatRoom.getChatRoomId(), chatRoom.getUserIds());

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database access error
        }
    }


    /**
     * this method is used to get a chat room
     *
     * @param chatRoom
     */
    @Override
    public void updateChatRoom(ChatRoom chatRoom) {

    }

    /**
     * this method is used to delete a chat room
     *
     * @param chatRoom
     */
    @Override
    public void deleteChatRoom(ChatRoom chatRoom) {
        // this method should delete a chat room from the database
        // it should delete the chat room from the chat room table
        // it should delete the chat messages from the chat message table
        try (PreparedStatement statement = connection.prepareStatement(SchemaChatRoomSql.DELETE_CHAT_ROOM)) {
            statement.setInt(1, chatRoom.getChatRoomId());
            statement.executeUpdate();
            System.out.println("DELETE_CHAT_ROOM is done...");

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database access error
        }

        //it should delete messages with the chat room id from the chat message table
        try (PreparedStatement statement = connection.prepareStatement(SchemaChatMessageSql.DELETE_CHAT_MESSAGE)) {
            statement.setInt(1, chatRoom.getChatRoomId());
            statement.executeUpdate();
            System.out.println("DELETE_CHAT_MESSAGE is done...");

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database access error
        }

    }

    /**
     * this method is used to drop a chat room table
     */
    @Override
    public void dropChatRoomTable() {
        System.out.println("DROP chat room table");
        try (PreparedStatement statement = connection.prepareStatement(SchemaChatRoomSql.DROP_TABLE)) {
            statement.executeUpdate();
            System.out.println("DROP_TABLE is done...");

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database access error
        }
    }

    /**
     * this method is used to create a chat room table
     */
    @Override
    public void createChatRoomTable() {
        System.out.println("CREATE chat room table");
        try {
            PreparedStatement statement = connection.prepareStatement(SchemaChatRoomSql.CREATE_TABLE);
            System.out.println("CREATE_TABLE: " + SchemaChatRoomSql.CREATE_TABLE);
            statement.executeUpdate();
            System.out.println("CREATE_TABLE is done...");

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database access error
        }
    }
}
