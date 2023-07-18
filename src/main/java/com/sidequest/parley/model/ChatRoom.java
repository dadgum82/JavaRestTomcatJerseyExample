package com.sidequest.parley.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private final int chatRoomId;                         // Unique identifier for the chat room
    private final List<Integer> userIds;          // List of users in the chat room
    private String name;                    // Name of the chat room
    private byte[] icon;                    // Icon representing the chat room (image)
    private InputStream iconStream;         // Input stream for the chat room icon
    private int moderatorId;                // ID of the user who is the moderator of the chat room

    public ChatRoom(int chatRoomId, String name, byte[] icon) {
        this.chatRoomId = chatRoomId;
        this.name = name;
        this.icon = icon;
        this.userIds = new ArrayList<>();
        this.iconStream = new ByteArrayInputStream(icon);
    }

    public ChatRoom(int chatRoomId, String name, int moderatorId) {
        this.chatRoomId = chatRoomId;
        this.name = name;
        this.moderatorId = moderatorId;
        this.userIds = new ArrayList<>();
    }

    public ChatRoom(int chatRoomId, String name, int moderatorId, byte[] icon) {
        this.chatRoomId = chatRoomId;
        this.name = name;
        this.icon = icon;
        this.moderatorId = moderatorId;
        this.userIds = new ArrayList<>();
        if (icon != null){
            this.iconStream = new ByteArrayInputStream(icon);
        }
    }

    public ChatRoom(int chatRoomId, String name, int moderatorId, byte[] icon, List<Integer> userIds) {
        this.chatRoomId = chatRoomId;
        this.name = name;
        this.icon = icon;
        this.moderatorId = moderatorId;
        this.userIds = new ArrayList<>(userIds);
        if (icon != null){
            this.iconStream = new ByteArrayInputStream(icon);
        }
    }

    public ChatRoom(int chatRoomId, String name, int moderatorId, List<Integer> userIds) {
        this.chatRoomId = chatRoomId;
        this.name = name;
        this.moderatorId = moderatorId;
        this.userIds = new ArrayList<>(userIds);
    }

    public int getChatRoomId() {
        return chatRoomId;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void addUserId(int id) {
        userIds.add(id);
    }

    public void removeUserId(int id) {
        userIds.remove(id);
    }

    public String getName() {
        return name;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
        this.iconStream = new ByteArrayInputStream(icon);
    }

    public InputStream getIconStream() {
        return iconStream;
    }

    public int getModeratorId() {
        return moderatorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModeratorId(int moderatorId) {
        this.moderatorId = moderatorId;
    }
}
