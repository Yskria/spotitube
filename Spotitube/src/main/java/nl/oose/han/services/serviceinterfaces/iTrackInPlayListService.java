package nl.oose.han.services.serviceinterfaces;

import nl.oose.han.datalayer.dto.TrackDTO;

import java.util.List;

public interface iTrackInPlayListService {
    List<TrackDTO> getAllSongsInPlaylist(int playlistId, String token);
    List<TrackDTO> addTrackToPlaylist(int playlistId, TrackDTO track, String token);
    List<TrackDTO> deleteTrackFromPlaylist(int playlistId, int trackId, String token);
}
