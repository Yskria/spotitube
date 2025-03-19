package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.PlayLists;
import nl.oose.han.datalayer.DAO.iDAO;
import nl.oose.han.datalayer.DTO.PlayListDTO;
import nl.oose.han.datalayer.DTO.TrackDTO;

import java.util.List;

@RequestScoped
public class PlayListService {

    @Inject
    private iDAO<PlayListDTO> playListDAO;

    public PlayLists getPlaylists(String token) {
        List<PlayListDTO> playlists = playListDAO.getAll(token);
        int totalLength = playlists.stream()
                .flatMap(playlist -> playlist.getTracks().stream())
                .mapToInt(TrackDTO::getDuration)
                .sum();
        return new PlayLists(playlists, totalLength);
    }

    public PlayLists deletePlayList(int playlistId, String token) {
        playListDAO.delete(playlistId, token);
        return getPlaylists(token);
    }

    public PlayLists addPlayList(String token, PlayListDTO playlist) {
        playListDAO.add(playlist, token);
        return getPlaylists(token);
    }

    public PlayLists updatePlayList(String token, int playlistId, PlayListDTO playlist) {
        playlist.setId(playlistId);
        playListDAO.update(playlist, token);
        return getPlaylists(token);
    }
}