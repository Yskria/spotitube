package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.dao.PlayListDAO;
import nl.oose.han.datalayer.dao.TrackInPlayListDAO;
import nl.oose.han.datalayer.dao.daointerfaces.iPlayListDAO;
import nl.oose.han.datalayer.dao.daointerfaces.iTrackInPLayListDAO;
import nl.oose.han.datalayer.dto.TrackDTO;
import nl.oose.han.services.exceptions.PlayListNotFoundException;
import nl.oose.han.services.serviceinterfaces.iTrackInPlayListService;

import java.util.List;

@RequestScoped
public class TrackInPlaylistService implements iTrackInPlayListService {

    @Inject
    private iPlayListDAO playListDAO;

    @Inject
    private iTrackInPLayListDAO trackInPlayListDAO;

    @Override
    public List<TrackDTO> getAllSongsInPlaylist(int playlistId, String token) {
        playlistChecker(playlistId);
        return playListDAO.getAllSongsInPlaylist(playlistId, token);
    }

    @Override
    public List<TrackDTO> addTrackToPlaylist(int playlistId, TrackDTO track, String token) {
        playlistChecker(playlistId);
        boolean offlineAvailable = track.isOfflineAvailable();
        trackInPlayListDAO.addPlayTrackToPlayList(playlistId, track, token, offlineAvailable);
        return getAllSongsInPlaylist(playlistId, token);
    }

    @Override
    public List<TrackDTO> deleteTrackFromPlaylist(int playlistId, int trackId, String token) {
        playlistChecker(playlistId);
        trackInPlayListDAO.deleteTrackFromPlaylist(playlistId, trackId, token);
        return getAllSongsInPlaylist(playlistId, token);
    }

    @Override
    public void playlistChecker(int playlistId){
        if(!trackInPlayListDAO.checkIfPlaylistExists(playlistId)){
            throw new PlayListNotFoundException("Playlist not found");
        }
    }
}