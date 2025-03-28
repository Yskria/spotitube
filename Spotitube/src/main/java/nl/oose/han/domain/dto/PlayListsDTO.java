package nl.oose.han.domain.dto;

import java.util.List;

public class PlayListsDTO {
    private List<PlayListDTO> playlists;
    private int length;

    public PlayListsDTO(List<PlayListDTO> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public PlayListsDTO() {
    }

    public List<PlayListDTO> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}