package nl.oose.han.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.oose.han.domain.dto.LoginDTO;
import nl.oose.han.domain.dto.UserDTO;
import nl.oose.han.services.serviceinterfaces.iLoginService;

@Path("/login")
public class LoginResource {

    @Inject
    private iLoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginValidator(UserDTO user) {
        boolean isValid = loginService.validateUser(user.getUser(), user.getPassword());
        if (isValid) {
            LoginDTO loginDTO = new LoginDTO(user.getUser(), loginService.getUserToken(user.getUser()));
            return Response.ok(loginDTO).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}