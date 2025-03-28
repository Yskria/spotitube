package nl.oose.han.datalayer.dao.mappers;

import nl.oose.han.domain.TrackDTO;
import nl.oose.han.datalayer.mappers.TrackMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrackMapperTest {

    @Mock
    private ResultSet rs;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMultipleTracks() throws SQLException {
        // Arrange
        when(rs.next()).thenReturn(true, true, false);
        when(rs.getInt("id")).thenReturn(1, 2);
        when(rs.getString("title")).thenReturn("Title1", "Title2");
        when(rs.getString("performer")).thenReturn("Performer1", "Performer2");
        when(rs.getInt("duration")).thenReturn(300, 200);
        when(rs.getString("album")).thenReturn("Album1", "Album2");
        when(rs.getInt("playcount")).thenReturn(10, 20);
        when(rs.getDate("publicationDate")).thenReturn(null, null);
        when(rs.getString("description")).thenReturn("Description1", "Description2");
        when(rs.getBoolean("offlineAvailable")).thenReturn(true, false);

        // Act
        List<TrackDTO> tracks = new TrackMapper().getSongs(rs);

        // Assert
        assertEquals(2, tracks.size());
        assertEquals(1, tracks.get(0).getId());
        assertEquals("Title1", tracks.get(0).getTitle());
        assertEquals("Performer1", tracks.get(0).getPerformer());
        assertEquals(300, tracks.get(0).getDuration());
        assertEquals("Album1", tracks.get(0).getAlbum());
        assertEquals(10, tracks.get(0).getPlaycount());
        assertNull(tracks.get(0).getPublicationDate());
        assertEquals("Description1", tracks.get(0).getDescription());
        assertTrue(tracks.get(0).isOfflineAvailable());

        assertEquals(2, tracks.get(1).getId());
        assertEquals("Title2", tracks.get(1).getTitle());
        assertEquals("Performer2", tracks.get(1).getPerformer());
        assertEquals(200, tracks.get(1).getDuration());
        assertEquals("Album2", tracks.get(1).getAlbum());
        assertEquals(20, tracks.get(1).getPlaycount());
        assertNull(tracks.get(1).getPublicationDate());
        assertEquals("Description2", tracks.get(1).getDescription());
        assertFalse(tracks.get(1).isOfflineAvailable());
    }

    @Test
    void testGetNoTracks() throws SQLException {
        // Arrange
        when(rs.next()).thenReturn(false);

        // Act
        List<TrackDTO> tracks = new TrackMapper().getSongs(rs);

        // Assert
        assertTrue(tracks.isEmpty());
    }

    @Test
    void testGetTracksWithNullValues() throws SQLException {
        // Arrange
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("title")).thenReturn(null);
        when(rs.getString("performer")).thenReturn(null);
        when(rs.getInt("duration")).thenReturn(0);
        when(rs.getString("album")).thenReturn(null);
        when(rs.getInt("playcount")).thenReturn(0);
        when(rs.getDate("publicationDate")).thenReturn(null);
        when(rs.getString("description")).thenReturn(null);
        when(rs.getBoolean("offlineAvailable")).thenReturn(false);

        // Act
        List<TrackDTO> tracks = new TrackMapper().getSongs(rs);

        // Assert
        assertEquals(1, tracks.size());
        assertEquals(1, tracks.get(0).getId());
        assertNull(tracks.get(0).getTitle());
        assertNull(null, tracks.get(0).getPerformer());
        assertEquals(0, tracks.get(0).getDuration());
        assertNull(null, tracks.get(0).getAlbum());
        assertEquals(0, tracks.get(0).getPlaycount());
        assertNull(null, tracks.get(0).getPublicationDate());
        assertNull(null, tracks.get(0).getDescription());
        assertFalse(tracks.get(0).isOfflineAvailable());
    }
}