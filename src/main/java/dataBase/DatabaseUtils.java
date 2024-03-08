package dataBase;

import static org.testng.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;


public class DatabaseUtils {

	// Test data
	static String jdbcUrl = "jdbc:mysql://localhost:3306/poste";
	static String dbUsername = "root";
	static String dbPassword = "";

	public static boolean userExists(String username) throws SQLException {

		try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
				Statement stmt = connection.createStatement()) {

			String query = "SELECT * FROM `inscription` WHERE user = '" + username + "'";
			// System.out.println("Executing query: " + query);

			ResultSet rs = stmt.executeQuery(query);
			boolean exists = rs.next();
			// System.out.println("User '" + username + "' exists: " + exists);

			return exists;
		}
	}

	public void testSignUpAddsUserToDatabase(String username) throws SQLException {

		// Setup
		try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
				Statement stmt = connection.createStatement()) {

			// Execute the SQL Query. Store results in ResultSet
			ResultSet rs = stmt.executeQuery("SELECT * FROM `inscription` WHERE user = '" + username + "'");

			// Assertion
			assertTrue(rs.next(), "No user found for username: " + username);

			String firstname = rs.getString("nom");
			String lastname = rs.getString("prenom");
			assertEquals("marwa7", username, "Unexpected username in the database");
			assertNotNull(firstname, "First name should not be null");
			assertNotNull(lastname, "Last name should not be null");

			System.out.println("A new user called " + firstname + " " + lastname + " was successfully added ");
		}
	}
	
	// Retrieve All the users from database and you can them for the Data Driven Test
	public static List<List<String>> retrieveAllUserFromDatabase() throws SQLException {
        List<List<String>> userList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT user, pwd FROM `inscription` ");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                List<String> userData = new ArrayList<>();
                userData.add(resultSet.getString("user"));
                userData.add(resultSet.getString("pwd"));
                userList.add(userData);
            }
        }
        
        return userList;
    }
	
	

}