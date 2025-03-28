package nl.oose.han.resources;

import jakarta.ws.rs.core.Response;
import nl.oose.han.domain.dto.TrackDTO;
import nl.oose.han.domain.dto.TracksDTO;
import nl.oose.han.services.serviceinterfaces.iTokenService;
import nl.oose.han.services.serviceinterfaces.iTrackInPlayListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class TrackInPlayListResourceTest {

    @Mock
    private iTrackInPlayListService trackInPlayListService;

    @Mock
    private iTokenService tokenService;

    @InjectMocks
    TrackInPlaylistResource sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTracksInPlaylist() {
        // Arrange
        int playlistId = 1;
        String token = "validToken";
        List<TrackDTO> tracks = new ArrayList<>();
        tracks.add(new TrackDTO());
        tracks.add(new TrackDTO());

        when(trackInPlayListService.getAllSongsInPlaylist(playlistId, token)).thenReturn(tracks);

        // Act
        Response response = sut.getTracksInPlaylist(playlistId, token);

        // Assert
        Mockito.verify(tokenService, times(1)).validateToken(token);
        Mockito.verify(trackInPlayListService, times(1)).getAllSongsInPlaylist(playlistId, token);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracks, ((TracksDTO) response.getEntity()).getTracks());
    }

    @Test
    void testGetTracksInPlaylistEmptyPlaylist() {
        // Arrange
        int playlistId = 2;
        String token = "validToken";
        List<TrackDTO> tracks = new ArrayList<>();
        TracksDTO emptyTracksDTO = new TracksDTO(tracks);

        when(trackInPlayListService.getAllSongsInPlaylist(playlistId, token)).thenReturn(emptyTracksDTO.getTracks());

        // Act
        Response response = sut.getTracksInPlaylist(playlistId, token);

        // Assert
        Mockito.verify(tokenService, times(1)).validateToken(token);
        Mockito.verify(trackInPlayListService, times(1)).getAllSongsInPlaylist(playlistId, token);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracks, ((TracksDTO) response.getEntity()).getTracks());
    }

    @Test
    void testAddTrackToPlaylist() {
        // Arrange
        int playlistId = 1;
        String token = "validToken";
        TrackDTO track = new TrackDTO();
        List<TrackDTO> tracks = new ArrayList<>();
        tracks.add(track);

        when(trackInPlayListService.addTrackToPlaylist(playlistId, track, token)).thenReturn(tracks);

        // Act
        Response response = sut.addTrackToPlaylist(token, playlistId, track);

        // Assert
        verify(tokenService, times(1)).validateToken(token);
        verify(trackInPlayListService, times(1)).addTrackToPlaylist(playlistId, track, token);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracks, response.getEntity());
    }

    @Test
    void testDeleteTrackToPlaylist() {
        // Arrange
        int playlistId = 1;
        String token = "validToken";
        TrackDTO track = new TrackDTO();
        List<TrackDTO> tracks = new ArrayList<>();
        tracks.add(track);

        when(trackInPlayListService.deleteTrackFromPlaylist(playlistId, track.getId(), token)).thenReturn(tracks);

        // Act
        Response response = sut.deleteTrackFromPlaylist(token, playlistId, track.getId());

        // Assert
        verify(tokenService, times(1)).validateToken(token);
        verify(trackInPlayListService, times(1)).deleteTrackFromPlaylist(playlistId, track.getId(), token);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracks, response.getEntity());
    }
}
