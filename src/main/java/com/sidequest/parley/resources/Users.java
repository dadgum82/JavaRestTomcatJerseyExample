package com.sidequest.parley.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


// Plain old Java Object it does not extend as class or implements
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation.
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML.

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/users")
public class Users {

	
	public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alice"));
        users.add(new User(2, "Bob"));
        users.add(new User(3, "Charlie"));

        return users;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("id") int id) {
        // Assume there is a database or data source with user records
        // This method queries the data source and returns the user with the specified id
        User user = new User(id, "Unknown User");
        // Query the data source to get the user with the specified id
        // ...
        // If the user is found, update the user object with the data from the data source
        // For example:
        // user.setName("Alice");
        return user;
    }

}