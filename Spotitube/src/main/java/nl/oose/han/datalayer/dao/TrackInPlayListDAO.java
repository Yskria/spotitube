package nl.oose.han.datalayer.dao;

import jakarta.enterprise.context.ApplicationScoped;
import nl.oose.han.datalayer.dto.TrackDTO;
import nl.oose.han.datalayer.DatabaseConnection;
import nl.oose.han.datalayer.tokenutil.TokenUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@ApplicationScoped
public class TrackInPlayListDAO {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();
    private final TokenUtil tokenUtil = new TokenUtil();

    public void addPlayTrackToPlayList(int playlistID, TrackDTO trackID, String token) {
        String username = tokenUtil.getUsernameFromToken(token);
        String query = "INSERT INTO track_in_playlist (playlist_id, track_id) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, playlistID);
            stmt.setInt(2, trackID.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTrackFromPlaylist(int playlistID, int trackID, String token) {
        String username = tokenUtil.getUsernameFromToken(token);
        String query = "DELETE FROM track_in_playlist WHERE playlist_id = ? AND track_id = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, playlistID);
            stmt.setInt(2, trackID);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
