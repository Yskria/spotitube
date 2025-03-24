package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.dao.PlayListDAO;
import nl.oose.han.datalayer.dao.TrackInPlayListDAO;
import nl.oose.han.datalayer.dto.TrackDTO;
import nl.oose.han.services.serviceinterfaces.iTrackInPlayListService;

import java.util.List;

@RequestScoped
public class TrackInPlaylistService implements iTrackInPlayListService {

    @Inject
    private PlayListDAO playListDAO;

    @Inject
    private TrackInPlayListDAO trackInPlayListDAO;

    @Override
    public List<TrackDTO> getAllSongsInPlaylist(int playlistId, String token) {
        return playListDAO.getAllSongsInPlaylist(playlistId, token);
    }

    @Override
    public List<TrackDTO> addTrackToPlaylist(int playlistId, TrackDTO track, String token) {
        boolean offlineAvailable = track.isOfflineAvailable();
        trackInPlayListDAO.addPlayTrackToPlayList(playlistId, track, token, offlineAvailable);
        return getAllSongsInPlaylist(playlistId, token);
    }

    @Override
    public List<TrackDTO> deleteTrackFromPlaylist(int playlistId, int trackId, String token) {
        trackInPlayListDAO.deleteTrackFromPlaylist(playlistId, trackId, token);
        return getAllSongsInPlaylist(playlistId, token);
    }
}