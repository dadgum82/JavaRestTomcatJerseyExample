package com.sidequest.parley.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sidequest.parley.dao.DbUserDaoImpl;
import com.sidequest.parley.model.User;
import com.sidequest.parley.util.Config;
import com.sidequest.parley.util.FileHandler;


public class UserService {
    private int USERS_COUNT;
    private final List<User> users;
    DbUserDaoImpl dao;

    public UserService() throws IOException {
        this("prod");

    }

    public UserService(String dbEnv) throws IOException {
        dao = new DbUserDaoImpl(dbEnv);
        users = new ArrayList<>();
        this.initalizeUsers();
    }

    private void initalizeUsers() throws IOException {
        this.users.addAll(dao.getAllUsers());
        this.USERS_COUNT = this.users.size();
    }

    public List<User> getUsers() {
        return this.users;
    }

    public User getUser(int userId) {
        for (User u : this.users) {
            if (u.getId() == userId) {
                return u;
            }
        }
        return null;
    }

    public User getUser(String name) {
        for (User u : this.users) {
            if (u.getName().equalsIgnoreCase(name)) {
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
        userCount++; //new user increment count
        User user = new User(userCount, name);
        try {
            dao.createUser(user);
            System.out.println("User created");
            this.users.add(user);
            this.USERS_COUNT = userCount;
            return user;

        } catch (Exception e) {
            System.out.println("User not created");
        }
        return null;
    }

    public User updateUser(String name, int id) throws IOException {
        User user = new User(id, name);
        try {
            dao.updateUser(user);
            System.out.println("User updated");
            return user;
        } catch (Exception e) {
            System.out.println("User not updated");
        }
        return null;
    }
}
