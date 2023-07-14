package com.sidequest.parley.dao;

import com.sidequest.parley.model.ChatMessage;
import com.sidequest.parley.model.User;

import java.sql.SQLException;
import java.util.List;

public interface ChatMessageDao {
    public List<ChatMessage> getAllChatMessages(int id);
    public void createChatMessage(ChatMessage chatMessage) throws SQLException;
    public void updateChatMessage(ChatMessage chatMessage);
    public void deleteChatMessage(ChatMessage chatMessage);
    void dropChatMessageTable();
    void createChatMessageTable();
}
