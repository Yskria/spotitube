package nl.oose.han.services.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TrackNotAvailableExceptionMapper implements ExceptionMapper<TrackNotAvailableException> {
    @Override
    public Response toResponse(TrackNotAvailableException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"error\": \"Track not available\", \"details\": \"" + exception.getMessage() + "\"}")
                .build();
    }
}
