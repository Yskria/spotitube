package nl.oose.han.datalayer.dao.daointerfaces;

import nl.oose.han.domain.TrackDTO;

import java.util.List;

public interface iTrackDAO {
    List<TrackDTO> getAllTracksNotInPlayList(int playlistID, String token);
}
