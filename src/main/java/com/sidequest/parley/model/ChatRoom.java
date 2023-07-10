package com.sidequest.parley.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private int id;                 // Unique identifier for the chat room
    private List<User> users;       // List of users in the chat room
    private String name;            // Name of the chat room
    private byte[] icon;            // Icon representing the chat room (image)

    public ChatRoom(int id, String name, byte[] icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.users = new ArrayList<>();   // Initialize the list of users
    }

    public int getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);   // Add a user to the chat room
    }

    public void removeUser(User user) {
        users.remove(user);   // Remove a user from the chat room
    }

    public String getName() {
        return name;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public InputStream getIconStream() {
        return new ByteArrayInputStream(icon);   // Convert the icon byte array to an InputStream
    }
}
