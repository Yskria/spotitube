package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.Track;
import nl.oose.han.datalayer.DAO.PlayListDAO;

import java.util.List;

@RequestScoped
public class TrackInPlaylistService {

    @Inject
    private PlayListDAO playListDAO;

    public List<Track> getAllSongsInPlaylist(int playlistId, String token) {
        return playListDAO.getAllSongsInPlaylist(playlistId, token);
    }
}