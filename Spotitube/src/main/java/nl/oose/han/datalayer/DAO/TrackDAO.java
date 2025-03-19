package nl.oose.han.datalayer.DAO;

import jakarta.enterprise.context.ApplicationScoped;
import nl.oose.han.datalayer.DTO.TrackDTO;
import nl.oose.han.datalayer.DatabaseConnection;
import nl.oose.han.datalayer.tokenutil.TokenUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TrackDAO implements iDAO<TrackDTO> {

    private final DatabaseConnection databaseConnection = new DatabaseConnection();
    private final TokenUtil tokenUtil = new TokenUtil();

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

            while (rs.next()) {

                TrackDTO track = new TrackDTO();
                track.setId(rs.getInt("id"));
                track.setTitle(rs.getString("title"));
                track.setPerformer(rs.getString("performer"));
                track.setDuration(rs.getInt("duration"));
                track.setAlbum(rs.getString("album"));
                track.setPlaycount(rs.getInt("playcount"));
                java.sql.Date publicationDate = rs.getDate("publicationDate");
                track.setPublicationDate(publicationDate != null ? publicationDate.toString() : null);
                track.setDescription(rs.getString("description"));
                track.setOfflineAvailable(rs.getBoolean("offlineAvailable"));
                tracks.add(track);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tracks;
    }

    @Override
    public void add(TrackDTO trackDTO, String token) {

    }

    @Override
    public void update(TrackDTO trackDTO, String token) {

    }

    @Override
    public void delete(int id, String token) {

    }

    @Override
    public List<TrackDTO> getAll(String token) {
        return null;
    }
}
