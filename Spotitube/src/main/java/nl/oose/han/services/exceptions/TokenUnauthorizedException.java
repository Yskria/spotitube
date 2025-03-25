package nl.oose.han.services.exceptions;

import jakarta.ws.rs.core.Response;

public class TokenUnauthorizedException extends RuntimeException {
    public TokenUnauthorizedException(String message) {
        super(message);
    }
}