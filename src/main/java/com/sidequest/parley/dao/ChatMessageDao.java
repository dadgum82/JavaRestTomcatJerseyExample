package com.sidequest.parley.dao;

import com.sidequest.parley.model.ChatMessage;
import com.sidequest.parley.model.User;

import java.sql.SQLException;
import java.util.List;

public interface ChatMessageDao {
    List<ChatMessage> getAllChatMessages(int id) throws SQLException;
    void createChatMessage(ChatMessage chatMessage) throws SQLException;
    void updateChatMessage(ChatMessage chatMessage) throws SQLException;
    void deleteChatMessage(ChatMessage chatMessage) throws SQLException;
    void dropChatMessageTable() throws SQLException;
    void createChatMessageTable() throws SQLException;
}
