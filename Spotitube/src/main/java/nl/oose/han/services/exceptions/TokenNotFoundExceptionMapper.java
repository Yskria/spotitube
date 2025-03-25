package nl.oose.han.services.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TokenNotFoundExceptionMapper implements ExceptionMapper<TokenNotFoundException> {
    @Override
    public Response toResponse(TokenNotFoundException exception){
        return Response.status(Response.Status.NOT_FOUND)
                .entity("{\"error\": \"Token not found\", \"details\": \"" + exception.getMessage() + "\"}")
                .build();
    }

}
