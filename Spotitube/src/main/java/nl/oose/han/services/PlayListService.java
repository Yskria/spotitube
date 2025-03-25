package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.dto.PlayListsDTO;
import nl.oose.han.datalayer.dao.daointerfaces.iPlayListsDAO;
import nl.oose.han.datalayer.dto.PlayListDTO;
import nl.oose.han.datalayer.dto.TrackDTO;
import nl.oose.han.services.serviceinterfaces.iPlayListService;

import java.util.List;

@RequestScoped
public class PlayListService implements iPlayListService {

    @Inject
    private iPlayListsDAO playListsDAO;

    @Override
    public PlayListsDTO getPlaylists(String token) {
        List<PlayListDTO> playlists = playListsDAO.getAll(token);
        int totalLength = playlists.stream()
                .flatMap(playlist -> playlist.getTracks().stream())
                .mapToInt(TrackDTO::getDuration)
                .sum();
        return new PlayListsDTO(playlists, totalLength);
    }

    @Override
    public PlayListsDTO deletePlayList(int playlistId, String token) {
        playListsDAO.delete(playlistId, token);
        return getPlaylists(token);
    }

    @Override
    public PlayListsDTO addPlayList(String token, PlayListDTO playlist) {
        playListsDAO.add(playlist, token);
        return getPlaylists(token);
    }

    @Override
    public PlayListsDTO updatePlayList(String token, int playlistId, PlayListDTO playlist) {
        playlist.setId(playlistId);
        playListsDAO.update(playlist, token);
        return getPlaylists(token);
    }
}