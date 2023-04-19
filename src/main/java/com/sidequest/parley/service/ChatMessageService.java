package com.sidequest.parley.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.sidequest.parley.model.ChatMessage;
import com.sidequest.parley.model.ChatMessageInput;
import com.sidequest.parley.model.User;
import com.sidequest.parley.util.Config;
import com.sidequest.parley.util.FileHandler;



public class ChatMessageService {
	private FileHandler fileHandler;
	private String CHAT_DIRECTORY;
	private String CHAT_FILE;
	private int CHAT_MESSAGE_COUNT;
	private List<ChatMessage> chatMessages;

	public ChatMessageService(int chatID) throws IOException {
		this.CHAT_DIRECTORY = Config.getProperty("directory.chatFiles");
		this.CHAT_FILE = "chat-" + chatID + ".txt";
		this.fileHandler = new FileHandler(CHAT_DIRECTORY, CHAT_FILE);
		this.initalizeChat();
	}
	
	private void initalizeChat() throws FileNotFoundException, IOException {
		int counter = 0;
		boolean checkFile = this.fileHandler.fileExists();
		if (checkFile) {
			chatMessages = new ArrayList<>();
			List<String[]> chatText = fileHandler.readCSVFile();
			for (String[] line : chatText) {
				if (line.length > 0) {
					//System.out.println(line);
					int id = Integer.parseInt(line[0]);
					String strDateTime = line[1];
					int userID= Integer.valueOf(line[2]);
					String content = line[3];

					UserService us = new UserService();
					User userSender = us.getUser(userID);
					LocalDateTime ldt = LocalDateTime.parse(strDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
					ChatMessage cm = new ChatMessage(id, ldt, userSender, content);
					chatMessages.add(cm);
					counter++;
				}
			}
		} else {
			fileHandler.createFile();
		}
		this.CHAT_MESSAGE_COUNT = counter;

	}
	

	public List<ChatMessage> getChatMessages() {
		return this.chatMessages;
	}

	public ChatMessage createChatMessage(ChatMessageInput chatMessageInput) throws IOException {
		int messageID = this.CHAT_MESSAGE_COUNT;
		UserService us = new UserService();
		System.out.println("chatMessageInput.getSenderId(): " + chatMessageInput.getSenderId());
		User user = us.getUser(chatMessageInput.getSenderId());
		System.out.println("The user name is: " + user.getName());

		String message = chatMessageInput.getMessage();

		messageID++;
		LocalDateTime currentTime = LocalDateTime.now();
		ChatMessage cm = new ChatMessage(messageID, currentTime, user, message);
		this.chatMessages.add(cm);
		fileHandler.appendMessageToFile(cm.toArray());

		this.CHAT_MESSAGE_COUNT = messageID;
		return cm;
	}
}