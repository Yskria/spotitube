package nl.oose.han;
import nl.oose.han.datalayer.dto.PlayListDTO;

import java.util.List;

public class PlayLists {
    private List<PlayListDTO> playlists;
    private int length;

    public PlayLists(List<PlayListDTO> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public PlayLists() {

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