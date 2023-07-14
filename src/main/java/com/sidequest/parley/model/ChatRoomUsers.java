package com.sidequest.parley.model;

public class ChatRoomUsers {
    private int id;
    private int chatRoomId;
    private int userId;

    public ChatRoomUsers(int id, int chatRoomId, int userId) {
        this.id = id;
        this.chatRoomId = chatRoomId;
        this.userId = userId;
    }

    public ChatRoomUsers(int chatRoomId, int userId) {
        this.chatRoomId = chatRoomId;
        this.userId = userId;
    }

    public ChatRoomUsers() {
    }

    public int getId() {
        return id;
    }

    public int getChatRoomId() {
        return chatRoomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChatRoomId(int chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
