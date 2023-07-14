package com.sidequest.parley.model;

import java.time.LocalDateTime;

public class ChatMessage {

    private  int id;
    private int chatRoomId;
    private String content;
    private LocalDateTime timestamp;
    private User user;

    public ChatMessage(int id, int chatRoomId, LocalDateTime timestamp, User user, String content) {
        this.id = id;
        this.chatRoomId = chatRoomId;
        this.content = content;
        this.timestamp = timestamp;
        this.user = user;
    }
    public void setId(int id) {
    	this.id = id;
    }

    public void setChatRoomId(int chatRoomId) {
    	this.chatRoomId = chatRoomId;
    }

    public void setContent(String content) {
    	this.content = content;
    }

    public void setTimestamp(LocalDateTime timestamp) {
    	this.timestamp = timestamp;
    }

    public void setSender(User user) {
    	this.user = user;
    }

    public int getId() {
        return id;
    }
	public int getChatRoomId() {
        return chatRoomId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public User getUser() {
        return user;
    }
    
    public String[] toArray() {
    	String[] fields = {String.valueOf(this.chatRoomId), this.timestamp.toString(),String.valueOf(this.user.getId()),this.content};
		return fields;
    }

    public String stringify() {
        String idString = String.valueOf(this.chatRoomId);
        String userName = this.user.getName();
        String timestampString = this.timestamp.toString();
        String escapedContent = this.content.replace("\"", "\"\"");
        String[] fields = {idString, timestampString, userName, escapedContent};
        return String.join("\",\"", fields);
    }

}