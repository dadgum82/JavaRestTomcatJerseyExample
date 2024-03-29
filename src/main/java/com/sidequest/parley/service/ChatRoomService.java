package com.sidequest.parley.service;

import com.sidequest.parley.dao.DbChatRoomDaoImpl;
import com.sidequest.parley.exception.ForeignKeyConstraintException;
import com.sidequest.parley.model.ChatRoom;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatRoomService {
    DbChatRoomDaoImpl dao;
    private int CHAT_ROOM_COUNT;
    private final List<ChatRoom> chatRooms;
    private String dbEnv;

    public ChatRoomService() {
        this("prod"); // default to prod. This is constructor chaining.
    }

    public ChatRoomService(String dbEnv) {
        this.CHAT_ROOM_COUNT = 0;
        this.chatRooms = new ArrayList<>();
        dao = new DbChatRoomDaoImpl(dbEnv);
        this.initalizeChatRooms();
    }

    private void initalizeChatRooms() {
        chatRooms.addAll(dao.getAllChatRooms());
        this.CHAT_ROOM_COUNT = chatRooms.size();
    }

    public List<ChatRoom> getChatRooms() {
        return chatRooms;
    }

    public ChatRoom getChatRoom(int chatRoomId) {
        for (ChatRoom c : this.chatRooms) {
            if (c.getChatRoomId() == chatRoomId) {
                return c;
            }
        }
        return null;
    }

    public ChatRoom getChatRoom(String name) {
        for (ChatRoom c : this.chatRooms) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }


    public ChatRoom createChatRoom(String name, int moderatorId) throws SQLException {
        return createChatRoom(name, moderatorId, null);
    }

    public ChatRoom createChatRoom(String name, int moderatorId, byte[] icon) throws SQLException {
        return createChatRoom(name, moderatorId, null, icon);
    }

    public ChatRoom createChatRoom(String name, int moderatorId, List<Integer> userIds, byte[] icon) throws SQLException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Chat room name cannot be null or empty");
        }
        int chatRoomCount = this.CHAT_ROOM_COUNT;
        chatRoomCount++; //new user increment count
        System.out.println("Creating chat room with id: " + chatRoomCount);
        System.out.println("Chat room name: " + name);
        System.out.println("Chat room moderator id: " + moderatorId);
        System.out.println("Chat room icon: " + icon);
        if (userIds != null) {
            System.out.println("Chat room user id Size: " + userIds.size());
            System.out.println("Chat room user ids: " + userIds);
        }

        ChatRoom chatRoom = initializeChatRoomHelper(chatRoomCount, name, moderatorId, userIds, icon);
        chatRoom = addModeratorToUserIDs(chatRoom, moderatorId, userIds);

        dao.createChatRoom(chatRoom);
        this.chatRooms.add(chatRoom);
        return chatRoom;
    }

    private ChatRoom addModeratorToUserIDs(ChatRoom chatRoom, int moderatorId, List<Integer> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            chatRoom.addUserId(moderatorId);
        } else if (!userIds.contains(moderatorId)) {
            chatRoom.addUserId(moderatorId);
        }
        return chatRoom;
    }

    private ChatRoom initializeChatRoomHelper(int chatRoomId, String name, int moderatorId, List<Integer> userIds, byte[] icon) {
        ChatRoom chatRoom;
        boolean hasIcon = icon != null && icon.length > 0;
        boolean hasUserIds = userIds != null && !userIds.isEmpty();

        if (hasIcon && hasUserIds) {
            chatRoom = new ChatRoom(chatRoomId, name, moderatorId, icon, userIds);
        } else if (hasIcon && !hasUserIds) {
            chatRoom = new ChatRoom(chatRoomId, name, moderatorId, icon);
        } else if (!hasIcon && hasUserIds) {
            chatRoom = new ChatRoom(chatRoomId, name, moderatorId, userIds);
        } else {
            chatRoom = new ChatRoom(chatRoomId, name, moderatorId);
        }
        return chatRoom;
    }


    /**
     * Delete a chat room by id
     *
     * @param chatId
     * @return true if chat room was deleted, false if chat room was not found
     */
    public boolean deleteChatRoom(int chatId) {
        ChatRoom chatRoom = this.getChatRoom(chatId);
        if (chatRoom != null) {
            this.chatRooms.remove(chatRoom);
            dao.deleteChatRoom(chatRoom);
            return true;
        }
        return false;
    }

    /** Update a chat room by id and name
     * @param chatId
     * @param name
     * @return ChatRoom if chat room was updated
     */
    public ChatRoom updateChatRoom(int chatId, String name, byte[] icon) {
        ChatRoom chatRoom = this.getChatRoom(chatId);
        if (chatRoom != null) {
            chatRoom.setName(name);
            chatRoom.setIcon(icon);
            dao.updateChatRoom(chatRoom);
        }
        return chatRoom;
    }

    /**
     * Update a chat room by id
     *
     * @param chatId
     * @param name
     * @param icon
     * @return true if chat room was updated, false if chat room was not found
     */
    public ChatRoom updateChatRoom(int chatId, String name, int moderatorId, byte[] icon) {
        ChatRoom chatRoom = this.getChatRoom(chatId);
        if (chatRoom != null) {
            chatRoom.setName(name);
            chatRoom.setModeratorId(moderatorId);
            chatRoom.setIcon(icon);
            dao.updateChatRoom(chatRoom);

        }
        return chatRoom;
    }

}
