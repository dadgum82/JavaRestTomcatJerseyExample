package com.sidequest.parley.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import com.sidequest.parley.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Resources;
import org.mockito.Mockito;

/**
 * Unit tests for the {@link DbUserDaoImpl} class. These tests are intended to
 * test the interaction between {@link DbUserDaoImpl} and the database.
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DbUserDaoImplTest {
    private DbUserDaoImpl dao;
    private final User btCustomer = new User(1, "Brienne of Tarth");
    private final User ssCustomer = new User(2, "SpongeBob SquarePants");

    private final User wpCustomer = new User(3, "Winnie the Pooh");

    /**
     * Creates customers schema.
     *
     */
    @BeforeAll
    void setup() {
        System.out.println("Setup is running...");
        dao = new DbUserDaoImpl("test");
        dao.dropUserTable();
        dao.createUserTable();
        System.out.println("Setup is done");
    }

    //add existingCustomer to database and then test if it is there
    @Test
    @Order(1)
    void testAddUser() {
        System.out.println("testAddUser is running...");
        // Act
        dao.createUser(btCustomer);
        // Assert
        User user = dao.getUserByName(btCustomer.getName());
        assertEquals(1, user.getId()); //id is autoincremented
        assertEquals(btCustomer.getName(), user.getName());
        System.out.println("testAddUser is done...");
    }

    @Test
    @Order(2)
    void testGetAllUsers() {
        // Arrange
        // Act
        List<User> users = dao.getAllUsers();
        for(User u: users){
            System.out.println("ID: " + u.getId() + " NAME: " + u.getName());
        }
        // Assert
        assertEquals(1, users.size());
    }

    @Test
    @Order(3)
    void testAddssUser() {
        System.out.println("testAddUser is running...");
        // Act
        dao.createUser(ssCustomer);
        // Assert
        User user = dao.getUserByName(ssCustomer.getName());
        assertEquals(2, user.getId()); //id is autoincremented
        assertEquals(ssCustomer.getName(), user.getName());
        System.out.println("testAddUser is done...");
    }

    @Test
    @Order(4)
    void testAddwpUser() {
        System.out.println("testAddUser is running...");
        // Act
        dao.createUser(wpCustomer);
        // Assert
        User user = dao.getUserByName(wpCustomer.getName());
        assertEquals(3, user.getId()); //id is autoincremented
        assertEquals(wpCustomer.getName(), user.getName());
        System.out.println("testAddUser is done...");
    }


}
