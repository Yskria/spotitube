package nl.oose.han.datalayer.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.dao.daointerfaces.iPlayListsDAO;
import nl.oose.han.datalayer.dto.PlayListDTO;
import nl.oose.han.datalayer.dto.TrackDTO;
import nl.oose.han.datalayer.DatabaseConnection;
import nl.oose.han.datalayer.tokenutil.TokenUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PlayListsDAO implements iPlayListsDAO {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();
    private final TokenUtil tokenUtil = new TokenUtil();

    @Inject
    private PlayListDAO playListDAO;

    @Override
    public void add(PlayListDTO playlist, String token) {
        String username = tokenUtil.getUsernameFromToken(token);
        String query = "INSERT INTO playlist (name, owner) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, playlist.getName());
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(PlayListDTO playlist, String token) {
        String username = tokenUtil.getUsernameFromToken(token);
        String query = "UPDATE playlist SET name = ?, owner = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, playlist.getName());
            stmt.setString(2, username);
            stmt.setInt(3, playlist.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id, String token) {
        String username = tokenUtil.getUsernameFromToken(token);
        String query = "DELETE FROM playlist WHERE id = ? AND owner = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PlayListDTO> getAll(String token) {
        List<PlayListDTO> playListsList = new ArrayList<>();
        String username = tokenUtil.getUsernameFromToken(token);
        String query = "SELECT * FROM playlist";
        try (
                Connection con = DriverManager.getConnection(databaseConnection.connectionString());
                PreparedStatement preparedStatement = con.prepareStatement(query)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                List<TrackDTO> tracks = playListDAO.getAllSongsInPlaylist(id, token);
                playListsList.add(new PlayListDTO(id, rs.getString("name"), rs.getString("owner").equals(username), tracks));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playListsList;
    }
}