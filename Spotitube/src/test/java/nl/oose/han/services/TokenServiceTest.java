package nl.oose.han.services;

import nl.oose.han.datalayer.dao.daointerfaces.iTokenDAO;
import nl.oose.han.services.serviceclasses.TokenService;
import nl.oose.han.exceptions.exceptionclasses.TokenUnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TokenServiceTest {

    @Mock
    private iTokenDAO tokenDAO;

    @InjectMocks
    private TokenService sut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateTokenThrowsException() {
        // Arrange
        String invalidToken = "invalidToken";
        when(tokenDAO.validateToken(invalidToken)).thenReturn(null);

        // Act & Assert
        assertThrows(TokenUnauthorizedException.class, () -> sut.validateToken(invalidToken));
    }

    @Test
    void testValidateTokenDoesNotThrowException() {
        // Arrange
        String validToken = "validToken";
        when(tokenDAO.validateToken(validToken)).thenReturn(validToken);

        // Act & Assert
        assertDoesNotThrow(() -> sut.validateToken(validToken));
    }
}