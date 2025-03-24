package nl.oose.han.services.serviceinterfaces;

import nl.oose.han.Tracks;

public interface iTrackService {
    Tracks getAllTracksNotInPlaylist(int playlistID, String token);
}
