package nl.oose.han.exceptions.exceptionmappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.oose.han.exceptions.exceptionclasses.PlayListNotFoundException;

@Provider
public class PlayListNotFoundExceptionMapper implements ExceptionMapper<PlayListNotFoundException> {
    @Override
    public Response toResponse(PlayListNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"PlayList not found, how did you even get here?\", \"details\": \"" + exception.getMessage() + "\"}").build();
    }
}
