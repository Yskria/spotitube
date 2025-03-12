package nl.oose.han;
import nl.oose.han.datalayer.DTO.PlayListDTO;

import java.util.List;

public class PlayLists {
    private List<PlayListDTO> playlists;
    private int length;

    public PlayLists(List<PlayListDTO> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public List<PlayListDTO> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }
}