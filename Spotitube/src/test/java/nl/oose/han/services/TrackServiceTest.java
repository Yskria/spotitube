package nl.oose.han.services;

import nl.oose.han.datalayer.dao.daointerfaces.iTrackDAO;
import nl.oose.han.domain.dto.TrackDTO;
import nl.oose.han.domain.dto.TracksDTO;
import nl.oose.han.services.exceptions.TrackNotAvailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TrackServiceTest {

    @Mock
    private iTrackDAO trackDAO;

    @InjectMocks
    private TrackService trackService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTracksNotInPlaylist() throws TrackNotAvailableException {
        // Arrange
        int playlistID = 1;
        String token = "validToken";
        List<TrackDTO> tracks = new ArrayList<>();
        tracks.add(new TrackDTO());

        when(trackDAO.getAllTracksNotInPlayList(playlistID, token)).thenReturn(tracks);

        // Act
        TracksDTO result = trackService.getAllTracksNotInPlaylist(playlistID, token);

        // Assert
        assertEquals(tracks, result.getTracks());
    }

    @Test
    void testGetAllTracksNotInPlaylistMultipleTracks() throws TrackNotAvailableException {
        // Arrange
        int playlistID = 1;
        String token = "validToken";
        List<TrackDTO> tracks = new ArrayList<>();
        tracks.add(new TrackDTO());
        tracks.add(new TrackDTO());

        when(trackDAO.getAllTracksNotInPlayList(playlistID, token)).thenReturn(tracks);

        // Act
        TracksDTO result = trackService.getAllTracksNotInPlaylist(playlistID, token);

        // Assert
        assertEquals(tracks, result.getTracks());
    }



    @Test
    void testGetAllTracksNotInPlaylistThrowsExceptionWhenNoTracks() {
        // Arrange
        int playlistID = 1;
        String token = "validToken";

        when(trackDAO.getAllTracksNotInPlayList(playlistID, token)).thenReturn(new ArrayList<>());

        // Act & Assert
        assertThrows(TrackNotAvailableException.class, () -> {
            trackService.getAllTracksNotInPlaylist(playlistID, token);
        });
    }

    @Test
    void testGetAllTracksNotInPlaylistThrowsExceptionWhenTracksNull() {
        // Arrange
        int playlistID = 1;
        String token = "validToken";

        when(trackDAO.getAllTracksNotInPlayList(playlistID, token)).thenReturn(null);

        // Act & Assert
        assertThrows(TrackNotAvailableException.class, () -> {
            trackService.getAllTracksNotInPlaylist(playlistID, token);
        });
    }
}