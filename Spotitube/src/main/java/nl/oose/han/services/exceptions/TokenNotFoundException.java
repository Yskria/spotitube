package nl.oose.han.services.exceptions;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String message){
        super(message);
    }
}
