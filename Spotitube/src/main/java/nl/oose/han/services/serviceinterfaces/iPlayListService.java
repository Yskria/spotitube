package nl.oose.han.services.serviceinterfaces;

import nl.oose.han.domain.PlayListsDTO;
import nl.oose.han.domain.PlayListDTO;

public interface iPlayListService {
    PlayListsDTO getPlaylists(String token);
    PlayListsDTO deletePlayList(int playlistId, String token);
    PlayListsDTO addPlayList(String token, PlayListDTO playlist);
    PlayListsDTO updatePlayList(String token, int playlistId, PlayListDTO playlist);
}
