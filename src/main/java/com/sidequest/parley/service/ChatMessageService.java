package com.sidequest.parley.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.sidequest.parley.dao.DbChatMessageDaoImpl;
import com.sidequest.parley.model.ChatMessage;
import com.sidequest.parley.model.ChatMessageInput;
import com.sidequest.parley.model.ChatRoom;
import com.sidequest.parley.model.User;
import com.sidequest.parley.util.Config;
import com.sidequest.parley.util.FileHandler;

public class ChatMessageService {
    private int CHAT_MESSAGE_COUNT;
    private final List<ChatMessage> chatMessages;

    DbChatMessageDaoImpl dao;
    UserService us;
    ChatRoomService crs;

    public ChatMessageService() throws IOException, SQLException {
        this("prod"); // default to prod. This is constructor chaining.
    }

    public ChatMessageService(String dbEnv) throws IOException, SQLException {
        this(dbEnv, 0);
    }

    public ChatMessageService(int chatRoomId) throws IOException, SQLException {
        this("prod", chatRoomId);
    }

    public ChatMessageService(String dbEnv, int chatRoomId) throws IOException, SQLException {
        this.CHAT_MESSAGE_COUNT = 0;
        this.chatMessages = new ArrayList<>();
        dao = new DbChatMessageDaoImpl(dbEnv);
        crs = new ChatRoomService(dbEnv);
        us = new UserService(dbEnv);
        this.initalizeChat(chatRoomId);
    }

    private void initalizeChat(int chatRoomId) throws IOException, SQLException {
        chatMessages.addAll(dao.getAllChatMessages(chatRoomId));
        this.CHAT_MESSAGE_COUNT = chatMessages.size();
           }

    public ChatMessage createChatMessage(ChatMessageInput chatMessageInput) throws IOException, SQLException {
        int messageID = this.CHAT_MESSAGE_COUNT;
        messageID++;

        System.out.println("chatMessageInput.getUserId(): " + chatMessageInput.getUserId());
        User user = us.getUser(chatMessageInput.getUserId());
        ChatRoom chatRoom = crs.getChatRoom(chatMessageInput.getChatRoomId());
        if (chatRoom == null) {
            throw new IllegalArgumentException("ChatRoom not found. ChatRoom ID: " + chatMessageInput.getChatRoomId());
        }

        if (user == null) {
            throw new IllegalArgumentException("User not found. User ID: " + chatMessageInput.getUserId());
        }

        System.out.println("The user name is: " + user.getName());
        System.out.println("The chat room name is: " + chatRoom.getName());
        ChatRoomUserService crus = new ChatRoomUserService();
        if (!crus.isUserInChatRoom(user.getId(), chatRoom.getChatRoomId())) {
            throw new IllegalArgumentException("User is not in chat room. Username: " + user.getName() + " ChatRoom name: " + chatRoom.getName());
        }

        String content = chatMessageInput.getContent();
        LocalDateTime currentTime = LocalDateTime.now();
        //int id, int chatRoomId, LocalDateTime timestamp, User sender, String content
        ChatMessage cm = new ChatMessage(messageID, chatMessageInput.getChatRoomId(), currentTime, user, content);
        System.out.println("createChatMessage: cm.id: " + cm.getId());
        System.out.println("createChatMessage: cm.chatRoomId: " + cm.getChatRoomId());
        System.out.println("createChatMessage: cm.user.id: " + cm.getUser().getId());
        System.out.println("createChatMessage: cm.timestamp: " + cm.getTimestamp());
        System.out.println("createChatMessage: cm.content: " + cm.getContent());

        dao.createChatMessage(cm);
        this.chatMessages.add(cm);
        this.CHAT_MESSAGE_COUNT = messageID;
        return cm;
    }

    public List<ChatMessage> getChatMessages() {
        return this.chatMessages;
    }

    public List<ChatMessage> getChatMessages(int chatID) {
        List<ChatMessage> result = new ArrayList<>();
        for (ChatMessage cm : this.chatMessages) {
            if (cm.getId() == chatID) {
                result.add(cm);
            }
        }
        return result;
    }


}