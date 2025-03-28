package nl.oose.han.datalayer.dao.daointerfaces;

import nl.oose.han.domain.TrackDTO;

import java.util.List;

public interface iPlayListDAO {
    List<TrackDTO> getAllSongsInPlaylist(int playlistId, String token);
}
