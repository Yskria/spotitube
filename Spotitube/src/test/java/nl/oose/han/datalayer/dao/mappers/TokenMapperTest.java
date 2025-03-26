package nl.oose.han.datalayer.dao.mappers;

import nl.oose.han.datalayer.mappers.PlayListsMapper;
import nl.oose.han.datalayer.mappers.TokenMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class TokenMapperTest {
    @Mock
    private ResultSet rs;

    @InjectMocks
    private TokenMapper sut;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateUsernameSuccess() throws SQLException {
        // Arrange
        when(rs.next()).thenReturn(true);
        when(rs.getString("username")).thenReturn("testUser");

        // Act
        String result = sut.validateUsername(rs);

        // Assert
        assertEquals("testUser", result);
        verify(rs, times(1)).next();
        verify(rs, times(1)).getString("username");
    }

    @Test
    void testValidateUsernameFailure() throws SQLException {
        // Arrange
        when(rs.next()).thenReturn(false);

        // Act
        String result = sut.validateUsername(rs);

        // Assert
        assertNull(result);
        verify(rs, times(1)).next();
        verify(rs, never()).getString("username");
    }

    @Test
    void testValidateTokenSuccess() throws SQLException {
        // Arrange
        when(rs.next()).thenReturn(true);
        when(rs.getString("userToken")).thenReturn("testToken");

        // Act
        String result = sut.validateToken(rs);

        // Assert
        assertEquals("testToken", result);
        verify(rs, times(1)).next();
        verify(rs, times(1)).getString("userToken");
    }

    @Test
    void testValidateTokenFailure() throws SQLException {
        // Arrange
        when(rs.next()).thenReturn(false);

        // Act
        String result = sut.validateToken(rs);

        // Assert
        assertNull(result);
        verify(rs, times(1)).next();
        verify(rs, never()).getString("userToken");
    }
}