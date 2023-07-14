package com.sidequest.parley.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sidequest.parley.model.User;
import com.sidequest.parley.model.UserInput;
import com.sidequest.parley.service.UserService;

class UserControllerTest {
    /*
    private UserController userController;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void testGetUsers() throws IOException {
        List<User> users = Arrays.asList(
                new User(1, "User 1"),
                new User(2, "User 2")
        );

        when(userService.getUsers()).thenReturn(users);

        String expectedResponse = "{\"users\":[{\"id\":1,\"name\":\"User 1\"},{\"id\":2,\"name\":\"User 2\"}]}";
        String actualResponse = userController.getUsers();

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testGetUserById() throws IOException {
        int userId = 1;
        User user = new User(1, "User 1");

        when(userService.getUser(userId)).thenReturn(user);

        User actualUser = userController.getUserById(userId);

        assertEquals(user, actualUser);
    }

    @Test
    void testCreateUser() throws IOException {
        User user = new User(1, "New User");

        when(userService.createUser("New User")).thenReturn(user);

        UserInput ui = new UserInput();
        ui.setName("New User");

        User actualUser = userController.createUser(ui);

        assertEquals(user, actualUser);
    }

     */
}
