package nl.oose.han.datalayer.dao.daos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.dao.daointerfaces.iPlayListDAO;
import nl.oose.han.datalayer.dto.TrackDTO;
import nl.oose.han.datalayer.DatabaseConnection;
import nl.oose.han.datalayer.mappers.TrackMapper;
import nl.oose.han.services.exceptions.DatabaseConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@ApplicationScoped
public class PlayListDAO implements iPlayListDAO {

    @Inject
    private DatabaseConnection databaseConnection;

    @Override
    public List<TrackDTO> getAllSongsInPlaylist(int playlistId, String token) {
        TrackMapper trackMapper = new TrackMapper();
        List<TrackDTO> tracks = null;

        String query = "SELECT *, tip.offlineAvailable " +
                "FROM track t " +
                "JOIN track_in_playlist tip ON t.id = tip.track_id " +
                "JOIN playlist p ON p.id = tip.playlist_id " +
                "JOIN users u ON u.username = p.owner " +
                "WHERE tip.playlist_id = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, playlistId);
            ResultSet rs = stmt.executeQuery();
            tracks = trackMapper.getSongs(rs);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to the database getAllSongsInPlaylist: " + e.getMessage());
        }
        return tracks;
    }
}