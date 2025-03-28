package nl.oose.han.services.serviceclasses;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.dao.daointerfaces.iPlayListDAO;
import nl.oose.han.datalayer.dao.daointerfaces.iPlayListsDAO;
import nl.oose.han.datalayer.dao.daointerfaces.iTrackInPLayListDAO;
import nl.oose.han.domain.PlayListDTO;
import nl.oose.han.domain.TrackDTO;
import nl.oose.han.exceptions.exceptionclasses.PlayListNotFoundException;
import nl.oose.han.services.serviceinterfaces.iTrackInPlayListService;

import java.util.List;

@RequestScoped
public class TrackInPlaylistService implements iTrackInPlayListService {

    @Inject
    private iPlayListDAO playListDAO;

    @Inject
    private iTrackInPLayListDAO trackInPlayListDAO;

    @Inject
    private iPlayListsDAO playListsDAO;

    @Override
    public List<TrackDTO> getAllSongsInPlaylist(int playlistId, String token) {
        playlistChecker(playlistId);
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

    @Override
    public void playlistChecker(int playlistId){
        if(!trackInPlayListDAO.checkIfPlaylistExists(playlistId)){
            throw new PlayListNotFoundException("Playlist not found");
        }
    }

    @Override
    public int getAllPlaylistsWithPlaytime(String token) {
        List<PlayListDTO> playlists = playListsDAO.getAll(token);
        playlists.forEach(playlist -> playlist.setTracks(getAllSongsInPlaylist(playlist.getId(), token)));
        return playlists.stream()
                .mapToInt(PlayListDTO::getLength)
                .sum();
    }
}