package nl.oose.han.datalayer.dao;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.DatabaseConnection;
import nl.oose.han.datalayer.dao.daointerfaces.iLoginDAO;
import nl.oose.han.datalayer.dto.UserDTO;
import nl.oose.han.datalayer.mappers.LoginMapper;
import nl.oose.han.services.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RequestScoped
public class LoginDAO implements iLoginDAO {

    @Inject
    private DatabaseConnection databaseConnection;

    public LoginDAO() {
        this.databaseConnection = new DatabaseConnection();
    }

    @Override
    public boolean validateUser(String username, String password) {
        LoginMapper loginMapper = new LoginMapper();
        String query = "SELECT username, password FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return loginMapper.validateUser(rs);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database: " + e.getMessage());
        }
    }

    public String getUserToken(String username) {
        UserDTO user = new UserDTO();
        String query = "SELECT username, userToken FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                user.setToken(rs.getString("userToken"));
            }
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database: " + e.getMessage());
        }
        return user.getToken();
    }
}
