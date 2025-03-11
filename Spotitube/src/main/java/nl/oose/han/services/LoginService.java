package nl.oose.han.services;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.oose.han.controllers.LoginController;
import nl.oose.han.datalayer.DTO.UserDTO;

@Path("/login")
public class LoginService {

    private final LoginController loginController = new LoginController();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO user) {
        boolean isValid = loginController.validateUser(user.getUsername(), user.getPassword());

        if (isValid) {
            return Response.ok("{\"message\":\"Login successful\"}").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\":\"Invalid username or password\"}")
                    .build();
        }
    }
}
