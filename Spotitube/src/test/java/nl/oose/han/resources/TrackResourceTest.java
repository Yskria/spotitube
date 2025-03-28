package nl.oose.han.resources;

import jakarta.ws.rs.core.Response;
import nl.oose.han.domain.dto.TrackDTO;
import nl.oose.han.domain.dto.TracksDTO;
import nl.oose.han.services.serviceinterfaces.iTokenService;
import nl.oose.han.services.serviceinterfaces.iTrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TrackResourceTest {

    @Mock
    private iTrackService trackService;

    @Mock
    private iTokenService tokenService;

    @InjectMocks
    private TrackResource trackResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTracksNotInPlaylist() {
        // Arrange
        int playlistID = 1;
        String token = "validToken";
        List<TrackDTO> playlist = new ArrayList<>();
        playlist.add(new TrackDTO());
        playlist.add(new TrackDTO());
        TracksDTO tracksDTO = new TracksDTO(playlist);
        when(trackService.getAllTracksNotInPlaylist(playlistID, token)).thenReturn(tracksDTO);

        // Act
        Response response = trackResource.getAllTracksNotInPlaylist(playlistID, token);

        // Assert
        verify(tokenService, times(1)).validateToken(token);
        verify(trackService, times(1)).getAllTracksNotInPlaylist(playlistID, token);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(tracksDTO, response.getEntity());
    }
}
