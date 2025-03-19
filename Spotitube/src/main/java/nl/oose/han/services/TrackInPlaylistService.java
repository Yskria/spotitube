package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.DAO.PlayListDAO;
import nl.oose.han.datalayer.DTO.TracksDTO;

import java.util.List;

@RequestScoped
public class TrackInPlaylistService {

    @Inject
    private PlayListDAO playListDAO;

    public List<TracksDTO> getAllSongsInPlaylist(int playlistId, String token) {
        return playListDAO.getAllSongsInPlaylist(playlistId, token);
    }
}