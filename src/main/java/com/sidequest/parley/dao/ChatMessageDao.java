package com.sidequest.parley.dao;

import com.sidequest.parley.model.ChatMessage;
import com.sidequest.parley.model.User;

import java.sql.SQLException;
import java.util.List;

public interface ChatMessageDao {
    public List<ChatMessage> getAllChatMessages(int id) throws SQLException;
    public void createChatMessage(ChatMessage chatMessage) throws SQLException;
    public void updateChatMessage(ChatMessage chatMessage) throws SQLException;
    public void deleteChatMessage(ChatMessage chatMessage) throws SQLException;
    void dropChatMessageTable() throws SQLException;
    void createChatMessageTable() throws SQLException;
}
