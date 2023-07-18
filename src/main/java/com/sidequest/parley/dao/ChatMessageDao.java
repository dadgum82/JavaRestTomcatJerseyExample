package com.sidequest.parley.dao;

import com.sidequest.parley.model.ChatMessage;
import com.sidequest.parley.model.User;

import java.sql.SQLException;
import java.util.List;

public interface ChatMessageDao {
    void createChatMessageTable() throws SQLException;
}
