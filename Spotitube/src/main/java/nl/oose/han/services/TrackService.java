package nl.oose.han.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.oose.han.Tracks;
import nl.oose.han.datalayer.DAO.TrackDAO;

@ApplicationScoped
public class TrackService {

    @Inject
    TrackDAO trackDAO;

    public Tracks getAllTracksNotInPlaylist(int playlistID, String token) {
        return new Tracks(trackDAO.getAllTracksNotInPlayList(playlistID, token));
    }
}
