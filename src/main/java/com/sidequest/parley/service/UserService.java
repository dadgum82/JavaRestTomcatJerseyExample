package com.sidequest.parley.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sidequest.parley.model.User;
import com.sidequest.parley.util.Config;
import com.sidequest.parley.util.FileHandler;



public class UserService {
	private int USERS_COUNT;
	private final String USERS_DIRECTORY;
    private final String USERS_FILE;
    private final FileHandler fileHandler;
    private List<User> users;

    public UserService() throws FileNotFoundException, IOException {
    	this.USERS_DIRECTORY = Config.getProperty("directory.users");
    	this.USERS_FILE = Config.getProperty("file.users");
        fileHandler = new FileHandler(USERS_DIRECTORY, USERS_FILE);
        this.initalizeUsers();
    }
    
    public UserService(FileHandler fileHandler) {
    	this.USERS_DIRECTORY = Config.getProperty("directory.users");
    	this.USERS_FILE = Config.getProperty("file.users");
		this.fileHandler = fileHandler;
	}

	private void initalizeUsers() throws FileNotFoundException, IOException {
    	int counter = 0;
    	boolean checkFile = this.fileHandler.fileExists();
    	if(checkFile) {
    	users = new ArrayList<>();
    	//List<String> usersText = fileHandler.readFile();
    	List<String[]> usersText = fileHandler.readCSVFile();
    	for(String[] line : usersText) {
    		if(line.length > 0) {
    			 
    			int id = Integer.parseInt(line[0]);
    			String name = line[1];
    			User u = new User(id, name);
    			users.add(u);
    			counter++;
    		}
    	}
    	} else {
    		fileHandler.createFile();
    	}
    	this.USERS_COUNT = counter;
    	
    }

    public List<User> getUsers() {
            return this.users;
    }
    
	public User getUser(int userId) {
		for(User u : this.users) {
	        if(u.getId() == userId) {
	            return u;
	        }
	    }
	    return null;
	}
	
	public User getUser(String name) {
		for(User u : this.users) {
	        if(u.getName().equalsIgnoreCase(name)) {
	            return u;
	        }
	    }
	    return null;
	}
	
    public int getUserCount() {
        return USERS_COUNT;
}
    
    public User createUser(String name) throws IOException {
        int userCount = this.USERS_COUNT;
		userCount ++; //new user increment count
		System.out.println("User Count: " + name);
		System.out.println("Name: " + userCount);
		User user = new User(userCount, name);
		this.users.add(user);
		fileHandler.appendMessageToFile(user.toArray());
		this.USERS_COUNT = userCount;
		return user;
    }
}
