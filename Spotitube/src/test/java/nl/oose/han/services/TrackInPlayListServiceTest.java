package nl.oose.han.services;

import nl.oose.han.datalayer.dao.daointerfaces.iPlayListDAO;
import nl.oose.han.datalayer.dao.daointerfaces.iPlayListsDAO;
import nl.oose.han.datalayer.dao.daointerfaces.iTrackInPLayListDAO;
import nl.oose.han.domain.PlayListDTO;
import nl.oose.han.domain.TrackDTO;
import nl.oose.han.services.exceptions.PlayListNotFoundException;
import nl.oose.han.services.serviceclasses.TrackInPlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrackInPlayListServiceTest {

    @Mock
    private iPlayListDAO playListDAO;

    @Mock
    private iTrackInPLayListDAO trackInPlayListDAO;

    @Mock
    private iPlayListsDAO playListsDAO;

    @InjectMocks
    private TrackInPlaylistService sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSongsInPlaylist() {
        // Arrange
        int playlistId = 1;
        String token = "validToken";
        List<TrackDTO> expectedTracks = new ArrayList<>();
        expectedTracks.add(new TrackDTO());

        when(trackInPlayListDAO.checkIfPlaylistExists(playlistId)).thenReturn(true);
        when(playListDAO.getAllSongsInPlaylist(playlistId, token)).thenReturn(expectedTracks);

        // Act
        List<TrackDTO> actualTracks = sut.getAllSongsInPlaylist(playlistId, token);

        // Assert
        verify(trackInPlayListDAO, times(1)).checkIfPlaylistExists(playlistId);
        verify(playListDAO, times(1)).getAllSongsInPlaylist(playlistId, token);
        assertEquals(expectedTracks, actualTracks);
    }

    @Test
    void testGetAllSongsInPlaylistWithEmptyPlaylist() {
        // Arrange
        int playlistId = 1;
        String token = "validToken";
        List<TrackDTO> expectedTracks = new ArrayList<>();

        when(trackInPlayListDAO.checkIfPlaylistExists(playlistId)).thenReturn(true);
        when(playListDAO.getAllSongsInPlaylist(playlistId, token)).thenReturn(expectedTracks);

        // Act
        List<TrackDTO> actualTracks = sut.getAllSongsInPlaylist(playlistId, token);

        // Assert
        verify(trackInPlayListDAO, times(1)).checkIfPlaylistExists(playlistId);
        verify(playListDAO, times(1)).getAllSongsInPlaylist(playlistId, token);
        assertEquals(expectedTracks, actualTracks);
    }

    @Test
    void testAddTrackToPlaylist() {
        // Arrange
        int playlistId = 1;
        String token = "validToken";
        TrackDTO track = new TrackDTO();
        track.setOfflineAvailable(true);
        List<TrackDTO> expectedTracks = new ArrayList<>();
        expectedTracks.add(track);

        when(trackInPlayListDAO.checkIfPlaylistExists(playlistId)).thenReturn(true);
        when(playListDAO.getAllSongsInPlaylist(playlistId, token)).thenReturn(expectedTracks);

        // Act
        List<TrackDTO> actualTracks = sut.addTrackToPlaylist(playlistId, track, token);

        // Assert
        verify(trackInPlayListDAO, times(1)).checkIfPlaylistExists(playlistId);
        verify(trackInPlayListDAO, times(1)).addPlayTrackToPlayList(playlistId, track, token, true);
        verify(playListDAO, times(1)).getAllSongsInPlaylist(playlistId, token);
        assertEquals(expectedTracks, actualTracks);
    }

    @Test
    void testDeleteTrackFromPlaylist() {
        // Arrange
        int playlistId = 1;
        int trackId = 1;
        String token = "validToken";
        TrackDTO track = new TrackDTO();
        track.setId(trackId);
        List<TrackDTO> expectedTracks = new ArrayList<>();
        expectedTracks.add(track);

        when(trackInPlayListDAO.checkIfPlaylistExists(playlistId)).thenReturn(true);
        when(playListDAO.getAllSongsInPlaylist(playlistId, token)).thenReturn(expectedTracks);

        // Act
        List<TrackDTO> actualTracks = sut.deleteTrackFromPlaylist(playlistId, trackId, token);

        // Assert
        verify(trackInPlayListDAO, times(1)).checkIfPlaylistExists(playlistId);
        verify(trackInPlayListDAO, times(1)).deleteTrackFromPlaylist(playlistId, trackId, token);
        verify(playListDAO, times(1)).getAllSongsInPlaylist(playlistId, token);
        assertEquals(expectedTracks, actualTracks);
    }

    @Test
    void testGetAllPlaylistsWithPlaytime() {
        // Arrange
        String token = "validToken";
        List<PlayListDTO> playlists = new ArrayList<>();

        PlayListDTO playlist1 = new PlayListDTO();
        playlist1.setId(1);
        List<TrackDTO> tracks1 = new ArrayList<>();
        tracks1.add(new TrackDTO(1, "Track 1", "Artist 1", 200, "Album 1", 12, "2002-01-01", "Description 1",true));
        tracks1.add(new TrackDTO(2, "Track 2", "Artist 2", 300, "Album 2", 12, "2002-01-01", "Description 2",true));
        playlist1.setTracks(tracks1);

        PlayListDTO playlist2 = new PlayListDTO();
        playlist2.setId(2);
        List<TrackDTO> tracks2 = new ArrayList<>();
        tracks2.add(new TrackDTO(3, "Track 3", "Artist 3", 400, "Album 3", 12, "2002-01-01", "Description 3",true));
        tracks2.add(new TrackDTO(4, "Track 4", "Artist 4", 500, "Album 4", 12, "2002-01-01", "Description 4",true));
        playlist2.setTracks(tracks2);

        playlists.add(playlist1);
        playlists.add(playlist2);

        when(playListsDAO.getAll(token)).thenReturn(playlists);
        when(trackInPlayListDAO.checkIfPlaylistExists(1)).thenReturn(true);
        when(trackInPlayListDAO.checkIfPlaylistExists(2)).thenReturn(true);
        when(playListDAO.getAllSongsInPlaylist(1, token)).thenReturn(tracks1);
        when(playListDAO.getAllSongsInPlaylist(2, token)).thenReturn(tracks2);

        // Act
        int totalPlaytime = sut.getAllPlaylistsWithPlaytime(token);

        // Assert
        verify(playListsDAO, times(1)).getAll(token);
        verify(playListDAO, times(1)).getAllSongsInPlaylist(1, token);
        verify(playListDAO, times(1)).getAllSongsInPlaylist(2, token);
        assertEquals(1400, totalPlaytime);
    }

    @Test
    void testPlaylistCheckerPlaylistNotFound() {
        // Arrange
        int playlistId = 1;
        when(trackInPlayListDAO.checkIfPlaylistExists(playlistId)).thenReturn(false);

        // Act & Assert
        assertThrows(PlayListNotFoundException.class, () -> {
            sut.playlistChecker(playlistId);
        });

        verify(trackInPlayListDAO, times(1)).checkIfPlaylistExists(playlistId);
    }

    @Test
    void testPlaylistCheckerPlaylistExists() {
        // Arrange
        int playlistId = 1;
        when(trackInPlayListDAO.checkIfPlaylistExists(playlistId)).thenReturn(true);

        // Act & Assert
        assertDoesNotThrow(() -> {
            sut.playlistChecker(playlistId);
        });

        verify(trackInPlayListDAO, times(1)).checkIfPlaylistExists(playlistId);
    }


}
