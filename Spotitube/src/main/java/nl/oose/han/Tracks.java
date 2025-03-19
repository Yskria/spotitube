package nl.oose.han;

import nl.oose.han.datalayer.DTO.TrackDTO;

import java.util.List;

public class Tracks {
    List<TrackDTO> tracks;

    public Tracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}
