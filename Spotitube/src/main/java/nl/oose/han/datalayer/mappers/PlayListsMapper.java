package nl.oose.han.datalayer.mappers;

import nl.oose.han.datalayer.dto.PlayListDTO;
import nl.oose.han.datalayer.dto.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//TODO: FIX THIS MAPPER
public class PlayListsMapper {
    public List<PlayListDTO> getAll(ResultSet rs, String username, String token, List<TrackDTO> tracks, int id) throws SQLException {
        List<PlayListDTO> playListsList = new ArrayList<>();
        while (rs.next()) {
            playListsList.add(new PlayListDTO(id, rs.getString("name"), rs.getString("owner").equals(username), tracks));
        }
        return playListsList;
    }
}
