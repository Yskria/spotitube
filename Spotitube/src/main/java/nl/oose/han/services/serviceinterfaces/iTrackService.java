package nl.oose.han.services.serviceinterfaces;

import nl.oose.han.domain.dto.TracksDTO;

public interface iTrackService {
    TracksDTO getAllTracksNotInPlaylist(int playlistID, String token);
}
