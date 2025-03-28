package nl.oose.han.services;

import nl.oose.han.datalayer.dao.daointerfaces.iLoginDAO;
import nl.oose.han.exceptions.exceptionclasses.TokenNotFoundException;
import nl.oose.han.services.serviceclasses.LoginService;
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
        //Arrange
        when(loginDAO.validateUser(correctUsername, correctPassword)).thenReturn(true);

        //Act
        boolean result = sut.validateUser("Laurens", "Password");

        //Assert
        assertTrue(result);
    }

    @Test
    void testValidateUserPasswordDoesNotExist() {
        //Arrange
        when(loginDAO.validateUser(correctUsername, "WrongPassword")).thenReturn(false);

        //Act
        boolean result = sut.validateUser(correctUsername, "WrongPassword");

        //Assert
        assertFalse(result);
    }

    @Test
    void testValidateUserUsernameDoesNotExist() {
        //Arrange
        when(loginDAO.validateUser("WrongUsername", correctPassword)).thenReturn(false);

        //Act
        boolean result = sut.validateUser(correctUsername, "WrongPassword");

        //Assert
        assertFalse(result);
    }

    @Test
    void testGetUserTokenAssertDoesNotThrow() {
        //Arrange
        String expectedToken = "1234-1234-1234";
        when(loginDAO.getUserToken(correctUsername)).thenReturn(expectedToken);

        //Act & Assert
        assertDoesNotThrow(() -> {
            String result = sut.getUserToken(correctUsername);
            assertEquals(expectedToken, result);
        });
    }

    @Test
    void testGetUserTokenAssertThrowsl() {
        //Arrange
        when(loginDAO.getUserToken("incorrectUsername")).thenReturn(null);

        //Act & Assert
        assertThrows(TokenNotFoundException.class, () -> {
            sut.getUserToken("Laurens");
        });
    }
}