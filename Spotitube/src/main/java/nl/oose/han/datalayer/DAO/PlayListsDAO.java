package nl.oose.han.datalayer.DAO;

import jakarta.enterprise.context.ApplicationScoped;
import nl.oose.han.datalayer.DTO.PlayListDTO;
import nl.oose.han.datalayer.DatabaseConnection;
import nl.oose.han.datalayer.tokenutil.TokenUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class PlayListsDAO implements iDAO<PlayListDTO> {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();
    private final TokenUtil tokenUtil = new TokenUtil();

    @Override
    public void add(PlayListDTO playlist, String token) {
        String username = tokenUtil.getUsernameFromToken(token);
        String query = "INSERT INTO playlist (name, owner) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {
            System.out.println("Adding playlist: " + playlist.getName());
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
    public PlayListDTO get(int id, String token) {
        String username = tokenUtil.getUsernameFromToken(token);
        String query = "SELECT * FROM playlist WHERE id = ? AND owner = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.setString(2, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                PlayListDTO playlist = new PlayListDTO();
                playlist.setId(rs.getInt("id"));
                playlist.setName(rs.getString("name"));
                if(!Objects.equals(rs.getString("owner"), username)) {
                    playlist.setOwner(false);
                } else {
                    playlist.setOwner(rs.getString("owner").equals(username));
                }
                return playlist;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PlayListDTO> getAll(String token) {
        String username = tokenUtil.getUsernameFromToken(token);
        List<PlayListDTO> playlists = new ArrayList<>();
        String query = "SELECT * FROM playlist WHERE owner = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PlayListDTO playlist = new PlayListDTO();
                playlist.setId(rs.getInt("id"));
                playlist.setName(rs.getString("name"));
                playlist.setOwner(rs.getString("owner").equals(username));
                playlists.add(playlist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playlists;
    }
}