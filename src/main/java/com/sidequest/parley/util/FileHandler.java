package com.sidequest.parley.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

import com.sidequest.parley.model.User;

public class FileHandler {
	private final String DIRECTORY;
	private final String FILENAME;
	// private static final String USERFILENAME = "users.txt";
	private Path filePath;

	public FileHandler(String directory, String fileName) {
		
		this.DIRECTORY = directory;
		this.FILENAME = fileName;
		System.out.println("directory: " + directory);
		System.out.println("fileName: " + fileName);
		this.filePath = Paths.get(directory, fileName);
	}

	public Path getFilePath() {
		return Paths.get(this.DIRECTORY, this.FILENAME);

	}
	
	public boolean fileExists() {
		Path filePath = this.getFilePath();
		return Files.exists(filePath);
	}
	

	public boolean createFile() {
		Path filePath = this.getFilePath();
		try {
			Files.createFile(filePath);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<String> readFile() {
		Path filePath = this.getFilePath();
		try {
			return Files.readAllLines(filePath, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean appendMessageToFile(String message) {
		Path filePath = this.getFilePath();
		try {
			String content = message + System.lineSeparator();
			Files.write(filePath, content.getBytes(StandardCharsets.UTF_8) , StandardOpenOption.APPEND);
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
