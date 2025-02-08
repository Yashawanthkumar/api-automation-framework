package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
	
	private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream("src/test/resources/dbconfig.properties"));

                String url = properties.getProperty("db.url");
                String username = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");
                String driver = properties.getProperty("db.driver");

                // Load MySQL JDBC Driver
                Class.forName(driver);

                // Establish Connection
                connection = DriverManager.getConnection(url, username, password);
            } catch (IOException | ClassNotFoundException | java.sql.SQLException e) {
                throw new RuntimeException("Database connection failed: " + e.getMessage());
            }
        }
        return connection;
    }
}
