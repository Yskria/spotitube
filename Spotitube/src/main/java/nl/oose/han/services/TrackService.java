package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.Tracks;
import nl.oose.han.datalayer.dao.TrackDAO;
import nl.oose.han.datalayer.dao.daointerfaces.iTrackDAO;
import nl.oose.han.datalayer.dto.TrackDTO;
import nl.oose.han.services.exceptions.TrackNotAvailableException;
import nl.oose.han.services.serviceinterfaces.iTrackService;

import javax.sound.midi.Track;
import java.util.List;

@RequestScoped
public class TrackService implements iTrackService {

    @Inject
    private iTrackDAO trackDAO;

    @Override
    public Tracks getAllTracksNotInPlaylist(int playlistID, String token) throws TrackNotAvailableException {
        List<TrackDTO> tracks = trackDAO.getAllTracksNotInPlayList(playlistID, token);
        if (tracks == null || tracks.isEmpty()) {
            throw new TrackNotAvailableException("No tracks available for the given playlist ID and token.");
        }
        return new Tracks(tracks);
    }
}
