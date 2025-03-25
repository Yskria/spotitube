package nl.oose.han.resources;

import jakarta.ws.rs.core.Response;
import nl.oose.han.datalayer.dto.LoginDTO;
import nl.oose.han.datalayer.dto.UserDTO;
import nl.oose.han.services.serviceinterfaces.iLoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LoginResourceTest {

    @Mock
    private iLoginService loginService;

    @InjectMocks
    private LoginResource sut;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginValidatorValidUser() {
        //Arrange
        String username = "Laurens";
        String password = "Password";
        UserDTO user = new UserDTO(username, password);
        String token = "testToken";
        when(loginService.validateUser(user.getUser(), user.getPassword())).thenReturn(true);
        when(loginService.getUserToken(user.getUser())).thenReturn(token);

        //Act
        try (Response response = sut.loginValidator(user)) {
            //Assert
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
            LoginDTO loginDTO = new LoginDTO(username, token);
            assertEquals(user.getUser(), loginDTO.getUser());
            assertEquals(token, loginDTO.getToken());
        }
    }

    @Test
    void testLoginValidatorInvalidUser() {
        //Arrange
        UserDTO user = new UserDTO("Laurens", "WrongPassword");
        when(loginService.validateUser(user.getUser(), user.getPassword())).thenReturn(false);

        //Act
        try (Response response = sut.loginValidator(user)) {
            //Assert
            assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        }
    }
}
