package nl.oose.han.datalayer.dao;

import jakarta.enterprise.context.ApplicationScoped;
import nl.oose.han.datalayer.dto.TrackDTO;
import nl.oose.han.datalayer.DatabaseConnection;
import nl.oose.han.datalayer.mappers.TrackMapper;
import nl.oose.han.datalayer.tokenutil.TokenUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PlayListDAO implements iDAO {

    private final DatabaseConnection databaseConnection = new DatabaseConnection();
    private final TokenUtil tokenUtil = new TokenUtil();
    private final TrackMapper trackMapper = new TrackMapper();

    public List<TrackDTO> getAllSongsInPlaylist(int playlistId, String token) {
        List<TrackDTO> tracks = null;
        String query = "SELECT * " +
                "FROM track t " +
                "JOIN track_in_playlist tip ON t.id = tip.track_id " +
                "JOIN playlist p ON p.id = tip.playlist_id " +
                "JOIN users u ON u.username = p.owner " +
                "WHERE tip.playlist_id = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, playlistId);
            ResultSet rs = stmt.executeQuery();
            tracks = trackMapper.getAllSongsInPlaylist(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tracks;
    }

    @Override
    public void add(Object entity, String token) {
    }

    @Override
    public void update(Object entity, String token) {
    }

    @Override
    public void delete(int id, String token) {
    }

    @Override
    public List getAll(String token) {
        return null;
    }
}