package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.Tracks;
import nl.oose.han.datalayer.dao.TrackDAO;
import nl.oose.han.services.serviceinterfaces.iTrackService;

@RequestScoped
public class TrackService implements iTrackService {

    @Inject
    TrackDAO trackDAO;

    @Override
    public Tracks getAllTracksNotInPlaylist(int playlistID, String token) {
        return new Tracks(trackDAO.getAllTracksNotInPlayList(playlistID, token));
    }
}
