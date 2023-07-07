package com.sidequest.parley.model;

import java.time.LocalDateTime;

public class ChatMessage {
	private String name;
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
    
    //TODO this isn't 100% correct we should really be using constructor chaining but I can't remember JFR
    public ChatMessage(int id, LocalDateTime timestamp, User sender, String content, String name) {
        this.name = name;
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
        this.sender = sender;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
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