package com.sidequest.parley.dao;

import com.sidequest.parley.model.ChatMessage;
import com.sidequest.parley.model.ChatRoom;
import com.sidequest.parley.model.ChatRoomUsers;

import java.util.List;

public interface ChatRoomUsersDao {

    public List<ChatRoomUsers> getAllChatRoomUsers(int chatRoomId);

    public void createChatRoomUsers(ChatRoomUsers chatRoomUsers);
    public void updateChatRoomUsers(ChatRoomUsers chatRoomUsers);
    public void deleteChatRoomUsers(ChatRoomUsers chatRoomUsers);
    void dropChatRoomUsersTable();
    void createChatRoomUsersTable();
}
