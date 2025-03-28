package nl.oose.han.datalayer.mappers;

import nl.oose.han.domain.dto.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackMapper {
    public List<TrackDTO> getSongs(ResultSet rs) throws SQLException {
        List<TrackDTO> tracks = new ArrayList<>();
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
        return tracks;
        }
    }
