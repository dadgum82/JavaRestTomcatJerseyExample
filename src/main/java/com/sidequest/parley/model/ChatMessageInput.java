package com.sidequest.parley.model;

public class ChatMessageInput {
    private int userId;
    private int chatRoomId;
    private String content;

    public ChatMessageInput() {}

    public ChatMessageInput(int chatRoomId, int userId,  String content) {
        this.userId = userId;
        this.chatRoomId = chatRoomId;
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(int chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
