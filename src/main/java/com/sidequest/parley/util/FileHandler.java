package com.sidequest.parley.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

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
		String fileSeparator = System.getProperty("file.separator");
		this.filePath = Paths.get(directory + fileSeparator + fileName);
	}

	public Path getFilePath() {
		String fileSeparator = System.getProperty("file.separator");
		return Paths.get(this.DIRECTORY + fileSeparator + this.FILENAME);

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
	
	public List<String[]> readCSVFile() throws FileNotFoundException, IOException {
	    List<String[]> records = new ArrayList<>();
	    try (CSVParser parser = new CSVParser(new FileReader(this.filePath.toString()), CSVFormat.DEFAULT)) {
	        for (CSVRecord record : parser) {
	            String[] fields = new String[record.size()];
	            for (int i = 0; i < record.size(); i++) {
	                fields[i] = record.get(i);
	            }
	            records.add(fields);
	        }
	    }
	    return records;
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
	
	public boolean appendMessageToFile(String[] content) {
		try {
			CSVPrinter printer = new CSVPrinter(new FileWriter(filePath.toString(), true), CSVFormat.DEFAULT);
			printer.printRecord(content);
			//Files.write(filePath, content.getBytes(StandardCharsets.UTF_8) , StandardOpenOption.APPEND);
			printer.flush();
			printer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	} 
	
	/*
	CSVPrinter printer = new CSVPrinter(new FileWriter("employees.csv"), CSVFormat.DEFAULT);
	printer.printRecord(employee);
	//create header row
	printer.printRecord("FirstName", "LastName", "Email", "Department");
	// create data rows
	for (String[] employee : employees) {
	    printer.printRecord(employee);
	}
	//close the printer after the file is complete
	printer.flush();
	printer.close();
	*/
}
