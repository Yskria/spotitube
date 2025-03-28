package nl.oose.han.datalayer.dao.daos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.oose.han.domain.TrackDTO;
import nl.oose.han.datalayer.DatabaseConnection;
import nl.oose.han.datalayer.dao.daointerfaces.iTrackInPLayListDAO;
import nl.oose.han.services.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@ApplicationScoped
public class TrackInPlayListDAO implements iTrackInPLayListDAO {

    @Inject
    private DatabaseConnection databaseConnection;

    private final TokenDAO tokenDAO = new TokenDAO();

    @Override
    public void addPlayTrackToPlayList(int playlistID, TrackDTO trackID, String token, boolean offlineAvailable) {
        String query = "INSERT INTO track_in_playlist (playlist_id, track_id, offlineAvailable) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, playlistID);
            stmt.setInt(2, trackID.getId());
            stmt.setBoolean(3, offlineAvailable);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database on addPlayTrackToPlayList: " + e.getMessage());
        }
    }

    @Override
    public void deleteTrackFromPlaylist(int playlistID, int trackID, String token) {
        String query = "DELETE FROM track_in_playlist WHERE playlist_id = ? AND track_id = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, playlistID);
            stmt.setInt(2, trackID);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database on deleteTrackFromPlaylist: " + e.getMessage());
        }
    }

    @Override
    public boolean checkIfPlaylistExists(int playlistID){
        String query = "SELECT * FROM playlist WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, playlistID);
            return stmt.executeQuery().next();
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database on checkIfPlaylistExists: " + e.getMessage());
        }
    }
}
