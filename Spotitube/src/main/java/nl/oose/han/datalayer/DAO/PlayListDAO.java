package nl.oose.han.datalayer.DAO;

import jakarta.enterprise.context.ApplicationScoped;
import nl.oose.han.Track;
import nl.oose.han.datalayer.DatabaseConnection;
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

    public List<Track> getAllSongsInPlaylist(int playlistId, String token) {
        List<Track> tracks = new ArrayList<>();
        String username = tokenUtil.getUsernameFromToken(token);
        String query = "SELECT * " +
                "FROM track t " +
                "JOIN track_in_playlist tip ON t.id = tip.track_id " +
                "JOIN playlist p ON p.id = tip.playlist_id " +
                "JOIN users u ON u.username = p.owner " +
                "WHERE tip.playlist_id = ? AND u.username = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, playlistId);
            stmt.setString(2, username);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Track track = new Track();
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
    public Object get(int id, String token) {
        return null;
    }

    @Override
    public List getAll(String token) {
        return null;
    }
}