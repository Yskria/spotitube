package nl.oose.han.services;

import nl.oose.han.PlayLists;
import nl.oose.han.datalayer.DTO.PlayListDTO;
import nl.oose.han.datalayer.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlayListService {
    private final DatabaseConnection databaseConnection;

    public PlayListService() {
        this.databaseConnection = new DatabaseConnection();
    }

    public PlayLists getPlaylists(String token) {
        String username = getUsernameFromToken(token);

        List<PlayListDTO> playlists = new ArrayList<>();
        String query = "SELECT id, name, owner FROM playlist WHERE owner = ?";

        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PlayListDTO playlist = new PlayListDTO();
                playlist.setId(rs.getInt("id"));
                playlist.setName(rs.getString("name"));
                playlist.setOwner(rs.getString("owner").equals(username));
                playlist.setTracks(new ArrayList<>()); // Assuming tracks are not needed for now
                playlists.add(playlist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int totalLength = calculateTotalLength(playlists); // Implement this method to calculate the total length
        return new PlayLists(playlists, totalLength);
    }

    private String getUsernameFromToken(String token) {
        String query = "SELECT username FROM usertoken WHERE userToken = ?";
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

    private int calculateTotalLength(List<PlayListDTO> playlists) {
        // Implement the logic to calculate the total length of all playlists
        return 78451; // Placeholder
    }
}