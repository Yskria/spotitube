package nl.oose.han.datalayer.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.dao.daointerfaces.iTrackDAO;
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
public class TrackDAO implements iTrackDAO {

    @Inject
    private DatabaseConnection databaseConnection;

    private final TokenUtil tokenUtil = new TokenUtil();
    private final TrackMapper trackMapper = new TrackMapper();

    @Override
    public List<TrackDTO> getAllTracksNotInPlayList(int playlistID, String token) {
        List<TrackDTO> tracks = new ArrayList<>();
        String username = tokenUtil.getUsernameFromToken(token);
        String query = "SELECT t.id, t.title, t.performer, t.duration, t.album, t.playcount, t.publicationDate, t.description, t.offlineAvailable " +
                "FROM track t " +
                "LEFT JOIN track_in_playlist tip ON t.id = tip.track_id AND tip.playlist_id = ? " +
                "LEFT JOIN playlist p ON p.id = tip.playlist_id " +
                "LEFT JOIN users u ON u.username = p.owner AND u.username = ? " +
                "WHERE tip.track_id IS NULL";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, playlistID);
            stmt.setString(2, username);
            ResultSet rs = stmt.executeQuery();
            tracks = trackMapper.getAllSongsInPlaylist(rs);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tracks;
    }
}
