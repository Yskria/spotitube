package nl.oose.han.datalayer.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.dao.daointerfaces.iPlayListsDAO;
import nl.oose.han.datalayer.dto.PlayListDTO;
import nl.oose.han.datalayer.DatabaseConnection;
import nl.oose.han.datalayer.mappers.PlayListsMapper;
import nl.oose.han.services.exceptions.DatabaseConnectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class PlayListsDAO implements iPlayListsDAO {

    @Inject
    private DatabaseConnection databaseConnection;

    @Inject
    private TokenDAO tokenDAO;

    @Override
    public void add(PlayListDTO playlist, String token) {
        String username = tokenDAO.getUsernameFromToken(token);
        String query = "INSERT INTO playlist (name, owner) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, playlist.getName());
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database: " + e.getMessage());
        }
    }

    @Override
    public void update(PlayListDTO playlist, String token) {
        String username = tokenDAO.getUsernameFromToken(token);
        String query = "UPDATE playlist SET name = ?, owner = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, playlist.getName());
            stmt.setString(2, username);
            stmt.setInt(3, playlist.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id, String token) {
        String username = tokenDAO.getUsernameFromToken(token);
        String query = "DELETE FROM playlist WHERE id = ? AND owner = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database: " + e.getMessage());
        }
    }

    @Override
    public List<PlayListDTO> getAll(String token) {
        List<PlayListDTO> playListsList = new ArrayList<>();
        String username = tokenDAO.getUsernameFromToken(token);
        PlayListsMapper playListsMapper = new PlayListsMapper();

        String query = "SELECT * FROM playlist";
        try (
                Connection con = DriverManager.getConnection(databaseConnection.connectionString());
                PreparedStatement preparedStatement = con.prepareStatement(query)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            playListsList = playListsMapper.getAll(rs, username);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to connect to the database: " + e.getMessage());
        }
        return playListsList;
    }
}