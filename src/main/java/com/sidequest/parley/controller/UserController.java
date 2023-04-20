package com.sidequest.parley.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sidequest.parley.model.User;
import com.sidequest.parley.model.UserInput;
import com.sidequest.parley.service.UserService;

/**
 * A RESTful web service controller for managing users.
 */
@Path("/users")
public class UserController {
	/**
	 * Retrieves a list of all users in JSON format.
	 *
	 * @return A JSON-formatted string containing an array of users.
	 * @throws FileNotFoundException If the file is not found.
	 * @throws IOException           If an I/O error occurs.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getUsers() throws FileNotFoundException, IOException {
		UserService us = new UserService();
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		for (User u : us.getUsers()) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			objectBuilder.add("id", u.getId());
			objectBuilder.add("name", u.getName());
			arrayBuilder.add(objectBuilder.build());
		}
		return Json.createObjectBuilder().add("users", arrayBuilder.build()).build().toString();
	}

	/**
	 * Retrieves a user by their ID.
	 *
	 * @param id The ID of the user to retrieve.
	 * @return A User object representing the found user.
	 * @throws FileNotFoundException If the file is not found.
	 * @throws IOException           If an I/O error occurs.
	 */
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("id") int id) throws FileNotFoundException, IOException {
		UserService us = new UserService();
		User user = us.getUser(id);
		if (user != null) {
			return user;
		} else {
			// Return a 404 response if the user isn't found
			throw new NotFoundException();
		}
	}

	/**
	 * Creates a new user using the provided input data.
	 *
	 * @param userInput A UserInput object containing the data for the new user.
	 * @return A User object representing the created user.
	 * @throws IOException If an I/O error occurs.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User createUser(UserInput userInput) throws IOException {
		UserService us = new UserService();
		return us.createUser(userInput.getName());
	}
}
