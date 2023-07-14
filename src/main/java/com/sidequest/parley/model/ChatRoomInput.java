package com.sidequest.parley.model;

import java.util.ArrayList;

public class ChatRoomInput {
    private ArrayList<Integer> userIds; // List of users in the chat room
    private String name; // Name of the chat room
    private byte[] icon; // Icon representing the chat room (image)
    private int moderatorId; // ID of the user who is the moderator of the chat room

    public ChatRoomInput() {
    }

    public ChatRoomInput(ArrayList<Integer> userIds, String name, int moderatorId, byte[] icon ) {
        this.userIds = userIds;
        this.name = name;
        this.icon = icon;
        this.moderatorId = moderatorId;
    }

    public ArrayList<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(ArrayList<Integer> userIds) {
        this.userIds = userIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public int getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(int moderatorId) {
        this.moderatorId = moderatorId;
    }
}
