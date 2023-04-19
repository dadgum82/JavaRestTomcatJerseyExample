package com.sidequest.parley.model;

import java.time.LocalDateTime;

public class ChatMessage {
    private int id;
    private String content;
    private LocalDateTime timestamp;
    private User sender;

    public ChatMessage(int id, LocalDateTime timestamp, User sender, String content) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
        this.sender = sender;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public User getSender() {
        return sender;
    }
    
    public String[] toArray() {
    	String[] fields = {String.valueOf(this.id), this.timestamp.toString(),String.valueOf(this.sender.getId()),this.content};
		return fields;
    }
    
    public String stringify() {
        String idString = String.valueOf(this.id);
        String senderName = this.sender.getName();
        String timestampString = this.timestamp.toString();
        String escapedContent = this.content.replace("\"", "\"\"");
        String[] fields = {idString, timestampString, senderName, escapedContent};
        return String.join("\",\"", fields);
    }

}
