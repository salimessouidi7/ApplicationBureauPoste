package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
    	
		String jdbcUrl = "jdbc:mysql://localhost:3306/poste";
		String username = "root";
		String password = "";

       //Load mysql jdbc driver		
   	    try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}

