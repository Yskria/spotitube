package nl.oose.han.resources;

import jakarta.ws.rs.core.Response;
import nl.oose.han.domain.PlayListDTO;
import nl.oose.han.domain.PlayListsDTO;
import nl.oose.han.services.serviceinterfaces.iPlayListService;
import nl.oose.han.services.serviceinterfaces.iTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlayListResourceTest {

    @Mock
    private iPlayListService playListService;

    @Mock
    private iTokenService tokenService;

    @InjectMocks
    PlayListResource sut;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPlaylistIsValid() {
        // Arrange
        String token = "token";
        PlayListDTO playlist = new PlayListDTO();
        PlayListsDTO updatedPlaylists = new PlayListsDTO(new ArrayList<>(), 0);
        when(playListService.addPlayList(token, playlist)).thenAnswer(invocation -> {
            updatedPlaylists.getPlaylists().add(playlist);
            return updatedPlaylists;
        });


        // Act
        Response response = sut.addPlaylist(token, playlist);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        verify(tokenService, times(1)).validateToken(token);
        verify(playListService, times(1)).addPlayList(token, playlist);
    }

    @Test
    void testDeletePlaylistIsValid() {
        // Arrange
        String token = "token";
        int id = 1;
        PlayListDTO playlist = new PlayListDTO();
        PlayListsDTO updatedPlaylists = new PlayListsDTO(new ArrayList<>(), 1);
        when(playListService.deletePlayList(1, token)).thenAnswer(invocation -> {
            updatedPlaylists.getPlaylists().remove(playlist);
            return updatedPlaylists;
        });

        // Act
        Response response = sut.deletePlaylist(token, 1);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(0, updatedPlaylists.getPlaylists().size());
        verify(tokenService, times(1)).validateToken(token);
        verify(playListService, times(1)).deletePlayList(1, token);
    }

    @Test
    void testGetPlaylistsIsValid() {
        // Arrange
        String token = "token";
        PlayListsDTO playlists = new PlayListsDTO(new ArrayList<>(), 0);
        when(playListService.getPlaylists(token)).thenReturn(playlists);

        // Act
        Response response = sut.getPlaylists(token);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(playlists, response.getEntity());
        verify(tokenService, times(1)).validateToken(token);
        verify(playListService, times(1)).getPlaylists(token);
    }

    @Test
    void testUpdatePlaylistIsValid() {
        // Arrange
        String token = "token";
        int id = 1;
        PlayListDTO playlist = new PlayListDTO();
        PlayListsDTO updatedPlaylists = new PlayListsDTO(new ArrayList<>(), 0);
        when(playListService.updatePlayList(token, id, playlist)).thenReturn(updatedPlaylists);

        // Act
        Response response = sut.updatePlaylist(token, id, playlist);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(updatedPlaylists, response.getEntity());
        verify(tokenService, times(1)).validateToken(token);
        verify(playListService, times(1)).updatePlayList(token, id, playlist);
    }
}
