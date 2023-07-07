package com.sidequest.parley.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sidequest.parley.model.User;

class UserServiceTest {
    private UserService userService;
    private FileHandler fileHandler;
/*
    @BeforeEach
    void setUp() {
        fileHandler = mock(FileHandler.class);
        userService = new UserService(fileHandler);
    }

    @Test
    void testGetUsers() throws IOException {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User(1, "User 1"));
        expectedUsers.add(new User(2, "User 2"));

        when(fileHandler.readCSVFile()).thenReturn(getMockUsers());

        List<User> actualUsers = userService.getUsers();

        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    void testGetUserById() throws IOException {
        int userId = 1;
        User expectedUser = new User(userId, "User 1");

        when(fileHandler.readCSVFile()).thenReturn(getMockUsers());

        User actualUser = userService.getUser(userId);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    void testCreateUser() throws IOException {
        int expectedUserId = 3;
        String userName = "New User";

        when(fileHandler.getUserCount()).thenReturn(2);

        User expectedUser = new User(expectedUserId, userName);

        User actualUser = userService.createUser(userName);

        assertEquals(expectedUser, actualUser);
        verify(fileHandler).appendMessageToFile(expectedUser.toArray());
    }

    private List<String[]> getMockUsers() {
        List<String[]> users = new ArrayList<>();
        users.add(new String[]{"1", "User 1"});
        users.add(new String[]{"2", "User 2"});
        return users;
    }
    */
}
