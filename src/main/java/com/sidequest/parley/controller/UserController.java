package com.sidequest.parley.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sidequest.parley.model.User;
import com.sidequest.parley.model.UserInput;
import com.sidequest.parley.service.UserService;


@Path("/users")
public class UserController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        UserService us = new UserService(); 
        return us.getUsers();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUserById(@PathParam("id") int id) {
        UserService us = new UserService(); 
        return us.getUser(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Object> createUser(UserInput userInput) throws IOException {
        UserService us = new UserService();
        return us.createUser(userInput.getName());
    }
}
