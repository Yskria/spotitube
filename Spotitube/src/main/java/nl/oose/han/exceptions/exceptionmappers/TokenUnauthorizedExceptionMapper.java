package nl.oose.han.exceptions.exceptionmappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import nl.oose.han.exceptions.exceptionclasses.TokenUnauthorizedException;

@Provider
public class TokenUnauthorizedExceptionMapper implements ExceptionMapper<TokenUnauthorizedException> {
    @Override
    public Response toResponse(TokenUnauthorizedException exception) {
        return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\": \"Unauthorized attempt\", \"details\": \"" + exception.getMessage() + "\"}").build();
    }
}