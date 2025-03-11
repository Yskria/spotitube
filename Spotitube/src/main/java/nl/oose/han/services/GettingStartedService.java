package nl.oose.han.services;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class GettingStartedService {
    @GET
    public Response getHello ()
    {
        return Response.status(200).entity("Hello World!").build();
    }
}
