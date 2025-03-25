package nl.oose.han.services;

import nl.oose.han.datalayer.dao.daointerfaces.iPlayListsDAO;
import nl.oose.han.datalayer.dto.PlayListDTO;
import nl.oose.han.datalayer.dto.PlayListsDTO;
import nl.oose.han.services.serviceinterfaces.iTrackInPlayListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlayListServiceTest {

    @Mock
    private iPlayListsDAO playListsDAO;

    @Mock
    private iTrackInPlayListService trackInPlayListService;

    @InjectMocks
    PlayListService sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPlaylists() {
        // Arrange
        String token = "validToken";
        List<PlayListDTO> playlists = new ArrayList<>();
        playlists.add(new PlayListDTO());
        int totalPlaytime = 100;

        when(playListsDAO.getAll(token)).thenReturn(playlists);
        when(trackInPlayListService.getAllPlaylistsWithPlaytime(token)).thenReturn(totalPlaytime);

        // Act
        PlayListsDTO result = sut.getPlaylists(token);

        // Assert
        assertEquals(playlists, result.getPlaylists());
        assertEquals(totalPlaytime, result.getLength());
    }

    @Test
    void testDeletePlayList() {
        // Arrange
        int playlistId = 1;
        String token = "validToken";
        List<PlayListDTO> playlists = new ArrayList<>();
        playlists.add(new PlayListDTO());

        when(playListsDAO.getAll(token)).thenReturn(playlists);

        // Act
        PlayListsDTO result = sut.deletePlayList(playlistId, token);

        // Assert
        verify(playListsDAO, times(1)).delete(playlistId, token);
        assertEquals(playlists, result.getPlaylists());
    }

    @Test
    void testAddPlayList() {
        // Arrange
        String token = "validToken";
        PlayListDTO playlist = new PlayListDTO();
        List<PlayListDTO> playlists = new ArrayList<>();
        playlists.add(playlist);

        when(playListsDAO.getAll(token)).thenReturn(playlists);

        // Act
        PlayListsDTO result = sut.addPlayList(token, playlist);

        // Assert
        verify(playListsDAO, times(1)).add(playlist, token);
        assertEquals(playlists, result.getPlaylists());
    }

    @Test
    void testUpdatePlayList() {
        // Arrange
        String token = "validToken";
        int playlistId = 1;
        PlayListDTO playlist = new PlayListDTO();
        List<PlayListDTO> playlists = new ArrayList<>();
        playlists.add(playlist);

        when(playListsDAO.getAll(token)).thenReturn(playlists);

        // Act
        PlayListsDTO result = sut.updatePlayList(token, playlistId, playlist);

        // Assert
        verify(playListsDAO, times(1)).update(playlist, token);
        assertEquals(playlists, result.getPlaylists());
    }

}

