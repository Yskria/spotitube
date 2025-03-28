package nl.oose.han.datalayer.dao.mappers;

import nl.oose.han.domain.PlayListDTO;
import nl.oose.han.datalayer.mappers.PlayListsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayListMapperTest {
    @Mock
    private ResultSet rs;

    @InjectMocks
    private PlayListsMapper sut;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllWithSinglePlaylist() throws SQLException {
        // Arrange
        String username = "testUser";
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("name")).thenReturn("Test Playlist");
        when(rs.getString("owner")).thenReturn(username);

        // Act
        List<PlayListDTO> result = sut.getAll(rs, username);

        // Assert
        assertEquals(1, result.size());
        PlayListDTO playListDTO = result.get(0);
        assertEquals(1, playListDTO.getId());
        assertEquals("Test Playlist", playListDTO.getName());
        assertEquals(true, playListDTO.isOwner());
    }

    @Test
    void testGetAllWithMultiplePlaylists() throws SQLException {
        // Arrange
        String username = "testUser";
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(1).thenReturn(2);
        when(rs.getString("name")).thenReturn("Test Playlist 1").thenReturn("Test Playlist 2");
        when(rs.getString("owner")).thenReturn(username).thenReturn("otherUser");

        // Act
        List<PlayListDTO> result = sut.getAll(rs, username);

        // Assert
        assertEquals(2, result.size());
        PlayListDTO playListDTO1 = result.get(0);
        assertEquals(1, playListDTO1.getId());
        assertEquals("Test Playlist 1", playListDTO1.getName());
        assertEquals(true, playListDTO1.isOwner());

        PlayListDTO playListDTO2 = result.get(1);
        assertEquals(2, playListDTO2.getId());
        assertEquals("Test Playlist 2", playListDTO2.getName());
        assertFalse(playListDTO2.isOwner());
    }

    @Test
    void testGetAllWithNoPlaylists() throws SQLException {
        // Arrange
        String username = "testUser";
        when(rs.next()).thenReturn(false);

        // Act
        List<PlayListDTO> result = sut.getAll(rs, username);

        // Assert
        assertEquals(0, result.size());
    }

    @Test
    void testGetAllWithDifferentOwners() throws SQLException {
        // Arrange
        String username = "testUser";
        when(rs.next()).thenReturn(true).thenReturn(false);
        when(rs.getInt("id")).thenReturn(1);
        when(rs.getString("name")).thenReturn("Test Playlist");
        when(rs.getString("owner")).thenReturn("otherUser");

        // Act
        List<PlayListDTO> result = sut.getAll(rs, username);

        // Assert
        assertEquals(1, result.size());
        PlayListDTO playListDTO = result.get(0);
        assertEquals(1, playListDTO.getId());
        assertEquals("Test Playlist", playListDTO.getName());
        assertFalse(playListDTO.isOwner());
    }

}
