package nl.oose.han.datalayer.DTO;

import java.util.List;

public class PlayListDTO {
    private int id;
    private String name;
    private boolean owner;
    private List<TracksDTO> tracks;

    public PlayListDTO(int id, String name, boolean owner, List<TracksDTO> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<TracksDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TracksDTO> tracks) {
        this.tracks = tracks;
    }
}