package nl.oose.han.datalayer.dao;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.DatabaseConnection;
import nl.oose.han.datalayer.mappers.TokenMapper;
import nl.oose.han.services.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RequestScoped
public class TokenDAO {

    @Inject
    private DatabaseConnection databaseConnection;

    public String getUsernameFromToken(String token) {
        String query = "SELECT username FROM users WHERE userToken = ?";
        TokenMapper tokenMapper = new TokenMapper();
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();
            return tokenMapper.validateUsername(rs);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database getUsernameFromToken: " + e.getMessage());
        }
    }

    public String validateToken(String token) {
        TokenMapper tokenMapper = new TokenMapper();
        String query = "SELECT userToken FROM users WHERE userToken = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();

            return tokenMapper.validateToken(rs);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database validateToken: " + e.getMessage());
        }
    }
}