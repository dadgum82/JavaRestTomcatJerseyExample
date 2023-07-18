package com.sidequest.parley.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.time.ZoneOffset;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sidequest.parley.model.ChatMessage;
import com.sidequest.parley.model.ChatMessageInput;
import com.sidequest.parley.service.ChatMessageService;

/**
 * A RESTful web service controller for managing chat messages.
 */
@Path("/chats")
public class ChatMessageController {

	/**
	 * Retrieves a list of chat messages for a given chat ID in JSON format.
	 *
	 * @param chatID The ID of the chat for which to retrieve messages.
	 * @return A JSON-formatted string containing an array of chat messages.
	 * @throws IOException If an I/O error occurs.
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getChatMessages(@PathParam("id") int chatID) throws IOException, SQLException {
		ChatMessageService cms = new ChatMessageService(chatID);
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		for (ChatMessage cm : cms.getChatMessages()) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			JsonObjectBuilder userBuilder = Json.createObjectBuilder();
			userBuilder.add("id", cm.getUser().getId());
			userBuilder.add("name", cm.getUser().getName());
			objectBuilder.add("user", userBuilder);

			JsonObjectBuilder timestampBuilder = Json.createObjectBuilder();
			timestampBuilder.add("year", cm.getTimestamp().getYear());
			timestampBuilder.add("monthValue", cm.getTimestamp().getMonthValue());
			timestampBuilder.add("dayOfMonth", cm.getTimestamp().getDayOfMonth());
			timestampBuilder.add("hour", cm.getTimestamp().getHour());
			timestampBuilder.add("minute", cm.getTimestamp().getMinute());
			timestampBuilder.add("second", cm.getTimestamp().getSecond());
			timestampBuilder.add("nano", cm.getTimestamp().getNano());
			timestampBuilder.add("month", cm.getTimestamp().getMonth().toString());
			timestampBuilder.add("dayOfWeek", cm.getTimestamp().getDayOfWeek().toString());
			timestampBuilder.add("dayOfYear", cm.getTimestamp().getDayOfYear());
			timestampBuilder.add("chronology", cm.getTimestamp().getChronology().toString());
			timestampBuilder.add("epochSecond", cm.getTimestamp().toEpochSecond(ZoneOffset.UTC)); //all our users are in the same time zone because we make up this reality.
			timestampBuilder.add("localTime", cm.getTimestamp().toLocalTime().toString()); //all our users are in the same time zone because we make up this reality.
			objectBuilder.add("timestamp", timestampBuilder);

			objectBuilder.add("chatMessageId", cm.getId());
			objectBuilder.add("content", cm.getContent());
			//objectBuilder.add("name", cm.getName());
			arrayBuilder.add(objectBuilder.build());
		}
		JsonObjectBuilder rootBuilder = Json.createObjectBuilder();
		rootBuilder.add("messages", arrayBuilder.build());
		JsonObject root = rootBuilder.build();
		StringWriter writer = new StringWriter();
		Json.createWriter(writer).write(root);
		return writer.toString();
	}

	/**
	 * Creates a new chat message using the provided input data.
	 *
	 * @param chatMessageInput A ChatMessageInput object containing the data for the
	 *                         new chat message.
	 * @return A ChatMessage object representing the created chat message.
	 * @throws IOException If an I/O error occurs.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ChatMessage createChatMessage(ChatMessageInput chatMessageInput) throws IOException, SQLException {
		int chatRoomId = chatMessageInput.getChatRoomId();

		ChatMessageService chatMessageService = new ChatMessageService(chatRoomId);
		return chatMessageService.createChatMessage(chatMessageInput);
	}
}