package nl.oose.han.exceptions.exceptionmappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.oose.han.exceptions.exceptionclasses.TrackNotAvailableException;

@Provider
public class TrackNotAvailableExceptionMapper implements ExceptionMapper<TrackNotAvailableException> {
    @Override
    public Response toResponse(TrackNotAvailableException exception) {
        return Response.status(Response.Status.NOT_FOUND).entity("{\"error\": \"Track not available\", \"details\": \"" + exception.getMessage() + "\"}").build();
    }
}
