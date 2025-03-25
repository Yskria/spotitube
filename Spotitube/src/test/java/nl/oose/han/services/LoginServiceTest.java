package nl.oose.han.services;

import nl.oose.han.datalayer.dao.daointerfaces.iLoginDAO;
import nl.oose.han.datalayer.dto.LoginDTO;
import nl.oose.han.services.exceptions.TokenNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class LoginServiceTest {

    @Mock
    private iLoginDAO loginDAO;

    @InjectMocks
    private LoginService sut;

    private final String correctUsername = "Laurens";
    private final String correctPassword = "Password";

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateUserWithProperCredentials() {
        when(loginDAO.validateUser(correctUsername, correctPassword)).thenReturn(true);

        boolean result = sut.validateUser("Laurens", "Password");

        assertTrue(result);
    }

    @Test
    void testValidateUserPasswordDoesNotExist() {
        when(loginDAO.validateUser(correctUsername, "WrongPassword")).thenReturn(false);

        boolean result = sut.validateUser(correctUsername, "WrongPassword");

        assertFalse(result);
    }

    @Test
    void testValidateUserUsernameDoesNotExist() {
        when(loginDAO.validateUser("WrongUsername", correctPassword)).thenReturn(false);

        boolean result = sut.validateUser(correctUsername, "WrongPassword");

        assertFalse(result);
    }

    @Test
    void testGetUserTokenAssertDoesNotThrow() {
        String expectedToken = "1234-1234-1234";
        when(loginDAO.getUserToken(correctUsername)).thenReturn(expectedToken);

        assertDoesNotThrow(() -> {
            String result = sut.getUserToken(correctUsername);
            assertEquals(expectedToken, result);
        });
    }

    @Test
    void testGetUserTokenAssertThrowsl() {
        when(loginDAO.getUserToken("incorrectUsername")).thenReturn(null);

        assertThrows(TokenNotFoundException.class, () -> {
            sut.getUserToken("Laurens");
        });
    }
}