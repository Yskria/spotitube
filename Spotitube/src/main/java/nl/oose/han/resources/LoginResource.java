package nl.oose.han.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.oose.han.UserToken;
import nl.oose.han.resources.resourceinterfaces.iLoginResource;
import nl.oose.han.services.LoginService;
import nl.oose.han.datalayer.dto.UserDTO;

@Path("/login")
public class LoginResource implements iLoginResource {

    @Inject
    private LoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response loginValidator(UserDTO user) {
        boolean isValid = loginService.validateUser(user.getUser(), user.getPassword());
        UserToken userToken = new UserToken(user.getUser(), "1234-1234-1234");
        if (isValid) {
            return Response.ok(userToken).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}