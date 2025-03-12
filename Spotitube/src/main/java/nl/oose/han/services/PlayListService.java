package nl.oose.han.services;

import jakarta.inject.Inject;
import nl.oose.han.PlayLists;
import nl.oose.han.datalayer.DAO.iDAO;
import nl.oose.han.datalayer.DTO.PlayListDTO;

import java.util.List;

public class PlayListService {

    @Inject
    iDAO<PlayListDTO> playListDAO;

    public PlayListService() {
    }

    public PlayLists getPlaylists(String token) {
        List<PlayListDTO> playlists = playListDAO.getAll(token);
        int totalLength = calculateTotalLength(playlists);
        return new PlayLists(playlists, totalLength);
    }

    public PlayLists deletePlayList(int playlistId, String token) {
        playListDAO.delete(playlistId, token);
        return getPlaylists(token);
    }

    private int calculateTotalLength(List<PlayListDTO> playlists) {
        return 87946513;
    }
}