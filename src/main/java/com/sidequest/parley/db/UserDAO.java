package com.sidequest.parley.db;

import com.sidequest.parley.db.SQLiteConnection;
import com.sidequest.parley.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	private SQLiteConnection dbConnection;

	public UserDAO() {
		dbConnection = new SQLiteConnection();
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();

		try (Connection connection = dbConnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");

				User user = new User(id, name);
				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// Handle database access error
		}

		return users;
	}

	public User getUserById(int id) {
		User user = null;

		try (Connection connection = dbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String name = resultSet.getString("name");

				user = new User(id, name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// Handle database access error
		}

		return user;
	}

	public void createUser(User user) {
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO users (id, name) VALUES (?, ?)")) {

			statement.setInt(1, user.getId());
			statement.setString(2, user.getName());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// Handle database access error
		}
	}

	public void updateUser(User user) {
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement("UPDATE users SET name = ? WHERE id = ?")) {

			statement.setString(1, user.getName());
			statement.setInt(2, user.getId());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// Handle database access error
		}
	}

	public void deleteUser(int id) {
		try (Connection connection = dbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {

			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// Handle database access error
		}
	}
}
