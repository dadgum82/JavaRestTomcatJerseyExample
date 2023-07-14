package com.sidequest.parley.dao;

import com.sidequest.parley.model.ChatMessage;
import com.sidequest.parley.model.ChatRoom;
import com.sidequest.parley.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ChatRoomDao {
    public ChatRoom getChatRoom(int id);

    List<ChatRoom> getAllChatRooms();


    List<Integer> getUserIdsByChatRoomId(int chatRoomId);

    void addUserToChatRoom(int chatRoomId, int userId);

    void addUsersToChatRoom(int chatRoomId, List<Integer> userIds);

    public void createChatRoom(ChatRoom chatRoom);
    public void updateChatRoom(ChatRoom chatRoom);
    public void deleteChatRoom(ChatRoom chatRoom);
    void dropChatRoomTable();
    void createChatRoomTable();
}
