package nl.oose.han.domain;

import java.util.List;

public class TracksDTO {
    List<TrackDTO> tracks;

    public TracksDTO(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}
