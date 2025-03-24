package nl.oose.han.datalayer.tokenutil;

import nl.oose.han.datalayer.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class tokenUtil {

    private final DatabaseConnection databaseConnection = new DatabaseConnection();

    public String getUsernameFromToken(String token) {
        String query = "SELECT username FROM users WHERE userToken = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}