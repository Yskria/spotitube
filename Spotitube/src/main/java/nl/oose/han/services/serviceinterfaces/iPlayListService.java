package nl.oose.han.services.serviceinterfaces;

import nl.oose.han.PlayLists;
import nl.oose.han.datalayer.dto.PlayListDTO;

public interface iPlayListService {
    PlayLists getPlaylists(String token);
    PlayLists deletePlayList(int playlistId, String token);
    PlayLists addPlayList(String token, PlayListDTO playlist);
    PlayLists updatePlayList(String token, int playlistId, PlayListDTO playlist);
}
