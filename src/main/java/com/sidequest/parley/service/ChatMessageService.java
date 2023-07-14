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
import com.sidequest.parley.model.User;
import com.sidequest.parley.util.Config;
import com.sidequest.parley.util.FileHandler;

public class ChatMessageService {
    private int CHAT_MESSAGE_COUNT;
    private List<ChatMessage> chatMessages;

    DbChatMessageDaoImpl dao;

    public ChatMessageService() throws IOException {
        this("prod"); // default to prod. This is constructor chaining.
    }

    public ChatMessageService(String dbEnv) throws IOException {
        this(dbEnv, 0);
    }

    public ChatMessageService(int chatRoomId) throws IOException {
        this("prod", chatRoomId);
    }

    public ChatMessageService(String dbEnv, int chatRoomId) throws IOException {
        this.CHAT_MESSAGE_COUNT = 0;
        this.chatMessages = new ArrayList<>();
        dao = new DbChatMessageDaoImpl(dbEnv);
        this.initalizeChat(chatRoomId);
    }

    private void initalizeChat(int chatRoomId) throws FileNotFoundException, IOException {
        chatMessages.addAll(dao.getAllChatMessages(chatRoomId));
        this.CHAT_MESSAGE_COUNT = chatMessages.size();
           }

    public ChatMessage createChatMessage(ChatMessageInput chatMessageInput) throws IOException, SQLException {
        int messageID = this.CHAT_MESSAGE_COUNT;
        messageID++;

        UserService us = new UserService();
        System.out.println("chatMessageInput.getSenderId(): " + chatMessageInput.getUserId());
        User user = us.getUser(chatMessageInput.getUserId());
        System.out.println("The user name is: " + user.getName());

        String content = chatMessageInput.getContent();

        LocalDateTime currentTime = LocalDateTime.now();
        //int id, int chatRoomId, LocalDateTime timestamp, User sender, String content
        ChatMessage cm = new ChatMessage(messageID, chatMessageInput.getChatRoomId(), currentTime, user, content);
        System.out.println("ChatMessageService.createChatMessage: cm.content" + cm.getContent());
        this.chatMessages.add(cm);
        dao.createChatMessage(cm);

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