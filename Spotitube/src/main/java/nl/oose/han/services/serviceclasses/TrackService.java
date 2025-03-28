package nl.oose.han.services.serviceclasses;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.domain.TracksDTO;
import nl.oose.han.datalayer.dao.daointerfaces.iTrackDAO;
import nl.oose.han.domain.TrackDTO;
import nl.oose.han.exceptions.exceptionclasses.TrackNotAvailableException;
import nl.oose.han.services.serviceinterfaces.iTrackService;

import java.util.List;

@RequestScoped
public class TrackService implements iTrackService {

    @Inject
    private iTrackDAO trackDAO;

    @Override
    public TracksDTO getAllTracksNotInPlaylist(int playlistID, String token) throws TrackNotAvailableException {
        List<TrackDTO> tracks = trackDAO.getAllTracksNotInPlayList(playlistID, token);
        if (tracks == null || tracks.isEmpty()) {
            throw new TrackNotAvailableException("No tracks available for the given playlist ID and token.");
        }
        return new TracksDTO(tracks);
    }
}
