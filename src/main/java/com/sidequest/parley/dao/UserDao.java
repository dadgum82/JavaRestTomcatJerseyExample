package com.sidequest.parley.dao;

import com.sidequest.parley.model.User;

import java.util.List;

public interface UserDao {

    public List<User> getAllUsers();
    public User getUserById(int id);

    User getUserByName(String name);

    public void createUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);

    void dropUserTable();

    void createUserTable();
}
