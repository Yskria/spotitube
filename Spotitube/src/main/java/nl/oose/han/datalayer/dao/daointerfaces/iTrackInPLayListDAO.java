package nl.oose.han.datalayer.dao.daointerfaces;

import nl.oose.han.datalayer.dto.TrackDTO;

public interface iTrackInPLayListDAO {
    void addPlayTrackToPlayList(int playlistID, TrackDTO trackID, String token);
    void deleteTrackFromPlaylist(int playlistID, int trackID, String token);
}
