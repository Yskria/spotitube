package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.PlayLists;
import nl.oose.han.Track;
import nl.oose.han.datalayer.DAO.iDAO;
import nl.oose.han.datalayer.DTO.PlayListDTO;

import java.util.List;
import java.util.logging.Logger;

@RequestScoped
public class PlayListService {

    @Inject
    iDAO<PlayListDTO> playListDAO;

    private static final Logger logger = Logger.getLogger(PlayListService.class.getName());

    public PlayListService() {
    }

    public PlayLists getPlaylists(String token) {
        List<PlayListDTO> playlists = playListDAO.getAll(token);
        logPlaylists(playlists);
        int totalLength = calculateTotalLength(playlists);
        return new PlayLists(playlists, totalLength);
    }

    private void logPlaylists(List<PlayListDTO> playlists) {
        for (PlayListDTO playlist : playlists) {
            logger.info("Playlist ID: " + playlist.getId());
            logger.info("Playlist Name: " + playlist.getName());
            logger.info("Playlist Owner: " + playlist.isOwner());
            if (playlist.getTracks() != null) {
                for (Track track : playlist.getTracks()) {
                    logger.info("Track ID: " + track.getId());
                    logger.info("Track Title: " + track.getTitle());
                }
            } else {
                logger.info("No tracks available for this playlist.");
            }
        }
    }

    private int calculateTotalLength(List<PlayListDTO> playlists) {
        return 4444;
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