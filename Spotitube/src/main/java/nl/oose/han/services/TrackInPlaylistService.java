package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.Tracks;
import nl.oose.han.datalayer.DAO.PlayListDAO;
import nl.oose.han.datalayer.DAO.TrackInPlayListDAO;
import nl.oose.han.datalayer.DTO.TrackDTO;

import javax.sound.midi.Track;
import java.util.List;

@RequestScoped
public class TrackInPlaylistService {

    @Inject
    private PlayListDAO playListDAO;

    @Inject
    private TrackInPlayListDAO trackInPlayListDAO;

    public List<TrackDTO> getAllSongsInPlaylist(int playlistId, String token) {
        return playListDAO.getAllSongsInPlaylist(playlistId, token);
    }

    public List<TrackDTO> addTrackToPlaylist(int playlistId, TrackDTO track, String token) {
        trackInPlayListDAO.addPlayTrackToPlayList(playlistId, track, token);
        return getAllSongsInPlaylist(playlistId, token);
    }

    public List<TrackDTO> deleteTrackFromPlaylist(int playlistId, int trackId, String token) {
        trackInPlayListDAO.deleteTrackFromPlaylist(playlistId, trackId, token);
        return getAllSongsInPlaylist(playlistId, token);
    }
}