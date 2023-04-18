package com.sidequest.parley.model;

public class ChatMessageInput {
    private int senderId;
    private int chatId;
    private String message;

    public ChatMessageInput() {}

    public ChatMessageInput(int chatId, int senderId,  String message) {
        this.senderId = senderId;
        this.chatId = chatId;
        this.message = message;
    }

    public int getSenderId() {
        return senderId;
    }
    
    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
