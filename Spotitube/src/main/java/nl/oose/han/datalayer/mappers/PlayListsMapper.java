package nl.oose.han.datalayer.mappers;

import nl.oose.han.datalayer.dto.PlayListDTO;
import nl.oose.han.datalayer.dto.TrackDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayListsMapper {
    public List<PlayListDTO> getAll(ResultSet rs, String username) throws SQLException {
        List<PlayListDTO> playListsList = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            playListsList.add(new PlayListDTO(id, rs.getString("name"), rs.getString("owner").equals(username), new ArrayList<>()));
        }
        return playListsList;
    }
}
