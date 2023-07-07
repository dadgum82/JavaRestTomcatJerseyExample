package com.sidequest.parley.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sidequest.parley.model.ChatMessage;
import com.sidequest.parley.model.ChatMessageInput;
import com.sidequest.parley.service.ChatMessageService;

class ChatMessageControllerTest {

    private ChatMessageService chatMessageService;
    private ChatMessageController chatMessageController;

    @BeforeEach
    void setUp() {
        chatMessageService = mock(ChatMessageService.class);
        chatMessageController = new ChatMessageController(chatMessageService);
    }

    @Test
    void getChatIds_ReturnsChatIds() throws IOException {
        // Arrange
        List<Integer> chatIds = Arrays.asList(1, 2, 3);

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Integer id : chatIds) {
            arrayBuilder.add(Json.createObjectBuilder().add("id", id));
        }

        JsonObjectBuilder rootBuilder = Json.createObjectBuilder();
        rootBuilder.add("chats", arrayBuilder.build());
        JsonObject expectedJsonResponse = rootBuilder.build();

        when(chatMessageService.getChatMessageIds()).thenReturn(chatIds);

        // Act
        String actualResponse = chatMessageController.getChatIds();

        // Assert
        assertEquals(expectedJsonResponse.toString(), actualResponse);
    }

    @Test
    void getChatMessages_ReturnsChatMessages() throws IOException {
        // Arrange
        int chatID = 1;
        ChatMessage chatMessage1 = new ChatMessage(1, null, null, "Hello");
        ChatMessage chatMessage2 = new ChatMessage(2, null, null, "How are you?");

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        arrayBuilder.add(buildChatMessageJson(chatMessage1));
        arrayBuilder.add(buildChatMessageJson(chatMessage2));

        JsonObjectBuilder rootBuilder = Json.createObjectBuilder();
        rootBuilder.add("messages", arrayBuilder.build());
        JsonObject expectedJsonResponse = rootBuilder.build();

        when(chatMessageService.getChatMessages(chatID)).thenReturn(Arrays.asList(chatMessage1, chatMessage2));

        // Act
        String actualResponse = chatMessageController.getChatMessages(chatID);

        // Assert
        assertEquals(expectedJsonResponse.toString(), actualResponse);
    }

    @Test
    void createChatMessage_ReturnsChatMessage() throws IOException {
        // Arrange
        int chatID = 1;
        ChatMessageInput chatMessageInput = new ChatMessageInput(chatID, 1, "Hello");
        ChatMessage expectedChatMessage = new ChatMessage(1, null, null, "Hello");

        when(chatMessageService.createChatMessage(chatMessageInput)).thenReturn(expectedChatMessage);

        // Act
        ChatMessage actualChatMessage = chatMessageController.createChatMessage(chatMessageInput);

        // Assert
        assertEquals(expectedChatMessage, actualChatMessage);
    }

    private JsonObject buildChatMessageJson(ChatMessage chatMessage) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        JsonObjectBuilder senderBuilder = Json.createObjectBuilder();
        senderBuilder.add("id", chatMessage.getSender().getId());
        senderBuilder.add("name", chatMessage.getSender().getName());

        JsonObjectBuilder timestampBuilder = Json.createObjectBuilder();
        timestampBuilder.add("year", chatMessage.getTimestamp().getYear());
        timestampBuilder.add("monthValue", chatMessage.getTimestamp().getMonthValue());
        timestampBuilder.add("dayOfMonth", chatMessage.getTimestamp().getDayOfMonth());
        timestampBuilder.add("hour", chatMessage.getTimestamp().getHour());
        timestampBuilder.add("minute", chatMessage.getTimestamp().getMinute());
        timestampBuilder.add("second", chatMessage.getTimestamp().getSecond());
        timestampBuilder.add("nano", chatMessage.getTimestamp().getNano());
        timestampBuilder.add("month", chatMessage.getTimestamp().getMonth().toString());
        timestampBuilder.add("dayOfWeek", chatMessage.getTimestamp().getDayOfWeek().toString());
        timestampBuilder.add("dayOfYear", chatMessage.getTimestamp().getDayOfYear());
        timestampBuilder.add("chronology", chatMessage.getTimestamp().getChronology().toString());
        timestampBuilder.add("epochSecond", chatMessage.getTimestamp().toEpochSecond(ZoneOffset.UTC));
        timestampBuilder.add("localTime", chatMessage.getTimestamp().toLocalTime().toString());

        objectBuilder.add("sender", senderBuilder);
        objectBuilder.add("timestamp", timestampBuilder);
        objectBuilder.add("id", chatMessage.getId());
        objectBuilder.add("content", chatMessage.getContent());
        objectBuilder.add("name", chatMessage.getName());

        return objectBuilder.build();
    }
}
