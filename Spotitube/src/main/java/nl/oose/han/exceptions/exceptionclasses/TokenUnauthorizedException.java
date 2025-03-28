package nl.oose.han.exceptions.exceptionclasses;

public class TokenUnauthorizedException extends RuntimeException {
    public TokenUnauthorizedException(String message) {
        super(message);
    }
}