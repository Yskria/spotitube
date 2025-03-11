package nl.oose.han.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class LoginController {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private String dbDriver;

    public LoginController() {
        loadDatabaseProperties();
    }

    private void loadDatabaseProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                throw new RuntimeException("Unable to find database.properties");
            }
            prop.load(input);
            dbDriver = prop.getProperty("driver");
            dbUrl = prop.getProperty("connectionString");

            // Extract user and password from the connection string
            dbUser = prop.getProperty("user", "");
            dbPassword = prop.getProperty("password", "");

            // Load JDBC driver
            Class.forName(dbDriver);
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException("Failed to load database properties", ex);
        }
    }

    public boolean validateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;  // Returns true if user exists
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
