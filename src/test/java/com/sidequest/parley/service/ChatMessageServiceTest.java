package com.sidequest.parley.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sidequest.parley.model.ChatMessage;
import com.sidequest.parley.model.ChatMessageInput;
import com.sidequest.parley.model.User;
import com.sidequest.parley.util.FileHandler;

class ChatMessageServiceTest {
    private ChatMessageService chatMessageService;
    private FileHandler fileHandler;

    @BeforeEach
    void setUp() {
        fileHandler = mock(FileHandler.class);
        chatMessageService = new ChatMessageService(fileHandler);
    }

    @Test
    void testGetChatMessages() throws IOException {
        int chatID = 1;
        List<ChatMessage> expectedChatMessages = new ArrayList<>();
        expectedChatMessages.add(new ChatMessage(1, LocalDateTime.now(), new User(1, "User 1"), "Message 1"));
        expectedChatMessages.add(new ChatMessage(2, LocalDateTime.now(), new User(2, "User 2"), "Message 2"));

        when(fileHandler.readCSVFile()).thenReturn(getMockChatMessages());

        List<ChatMessage> actualChatMessages = chatMessageService.getChatMessages(chatID);

        assertEquals(expectedChatMessages, actualChatMessages);
    }

    @Test
    void testCreateChatMessage() throws IOException {
        int chatID = 1;
        int expectedMessageID = 3;
        ChatMessageInput chatMessageInput = new ChatMessageInput(chatID, 1, "New Message");

        ChatMessage expectedChatMessage = new ChatMessage(expectedMessageID, LocalDateTime.now(), new User(1, "User 1"), "New Message");

        ChatMessage actualChatMessage = chatMessageService.createChatMessage(chatMessageInput);

        assertEquals(expectedChatMessage, actualChatMessage);
        verify(fileHandler).appendMessageToFile(expectedChatMessage.toArray());
    }

    private List<String[]> getMockChatMessages() {
        List<String[]> chatMessages = new ArrayList<>();
        chatMessages.add(new String[]{"1", "2022-01-01T10:00:00", "1", "Message 1"});
        chatMessages.add(new String[]{"2", "2022-01-02T11:00:00", "2", "Message 2"});
        return chatMessages;
    }
}
