package nl.oose.han.services;

import nl.oose.han.datalayer.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginService {
    private final DatabaseConnection databaseConnection;

    public LoginService() {
        this.databaseConnection = new DatabaseConnection();
    }

    public boolean validateUser(String username, String password) {
        String query = "SELECT username, password FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}