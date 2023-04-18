package com.sidequest.parley.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sidequest.parley.model.User;
import com.sidequest.parley.util.Config;
import com.sidequest.parley.util.FileHandler;

public class UserService {
	private int USERS_COUNT;
	private final String USERS_DIRECTORY;
    private final String USERS_FILE;
    private final FileHandler fileHandler;
    private List<User> users;

    public UserService() {
    	this.USERS_DIRECTORY = Config.getProperty("directory.users");
    	this.USERS_FILE = Config.getProperty("file.users");
        fileHandler = new FileHandler(USERS_DIRECTORY, USERS_FILE);
        this.initalizeUsers();
    }
    
    private void initalizeUsers() {
    	int counter = 0;
    	boolean checkFile = this.fileHandler.fileExists();
    	if(checkFile) {
    	users = new ArrayList<>();
    	List<String> usersText = fileHandler.readFile();
    	for(String line : usersText) {
    		if(line.length() > 0 && line.contains(",")) {
    			 
    			int id = Integer.parseInt(line.split(",")[0]);
    			String name = line.split(",")[1];
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
    
    public int getUserCount() {
        return USERS_COUNT;
}
    

    public List<User> getUser(int userId) {
    	List<User> result = new ArrayList<>(); 
        for(User u : this.users) {
        	if(u.getId() == userId) {
        		result.add(u);
        	}
        }
        return result;
    }

    
    public Optional<Object> createUser(String name) throws IOException {
        int userCount = this.USERS_COUNT;
		userCount ++; //new user increment count
		User user = new User(userCount, name);
		this.users.add(user);
		fileHandler.appendMessageToFile(user.stringify());
		this.USERS_COUNT = userCount;
		return Optional.empty();
    }

    /*
    public Optional<String> updateUser(User updatedUser) {
        try {
            List<User> users = getUsers();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == updatedUser.getId()) {
                    users.set(i, updatedUser);
                    break;
                }
            }
            fileHandler.writeUsersToFile(users);
            return Optional.empty();
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.of("Failed to update user: " + e.getMessage());
        }
    }
    */
}
