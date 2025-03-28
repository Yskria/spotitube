package nl.oose.han.exceptions.exceptionmappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.oose.han.exceptions.exceptionclasses.DatabaseConnectionException;

@Provider
public class DatabaseConnectionExceptionMapper implements ExceptionMapper<DatabaseConnectionException> {
    @Override
    public Response toResponse(DatabaseConnectionException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"Database connection failed\", \"details\": \"" + exception.getMessage() + "\"}").build();
    }
}