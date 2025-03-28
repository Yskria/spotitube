package nl.oose.han.datalayer.dao.daointerfaces;

import nl.oose.han.domain.dto.TrackDTO;

import java.util.List;

public interface iPlayListDAO {
    List<TrackDTO> getAllSongsInPlaylist(int playlistId, String token);
}
