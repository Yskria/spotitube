package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.PlayLists;
import nl.oose.han.datalayer.dao.PlayListsDAO;
import nl.oose.han.datalayer.dto.PlayListDTO;
import nl.oose.han.datalayer.dto.TrackDTO;
import nl.oose.han.services.serviceinterfaces.iPlayListService;

import java.util.List;

@RequestScoped
public class PlayListService implements iPlayListService {

    @Inject
    private PlayListsDAO playListsDAO;

    @Override
    public PlayLists getPlaylists(String token) {
        List<PlayListDTO> playlists = playListsDAO.getAll(token);
        int totalLength = playlists.stream()
                .flatMap(playlist -> playlist.getTracks().stream())
                .mapToInt(TrackDTO::getDuration)
                .sum();
        return new PlayLists(playlists, totalLength);
    }

    @Override
    public PlayLists deletePlayList(int playlistId, String token) {
        playListsDAO.delete(playlistId, token);
        return getPlaylists(token);
    }

    @Override
    public PlayLists addPlayList(String token, PlayListDTO playlist) {
        playListsDAO.add(playlist, token);
        return getPlaylists(token);
    }

    @Override
    public PlayLists updatePlayList(String token, int playlistId, PlayListDTO playlist) {
        playlist.setId(playlistId);
        playListsDAO.update(playlist, token);
        return getPlaylists(token);
    }
}