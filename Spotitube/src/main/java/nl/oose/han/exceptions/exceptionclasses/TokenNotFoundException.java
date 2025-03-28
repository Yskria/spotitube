package nl.oose.han.exceptions.exceptionclasses;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String message){
        super(message);
    }
}
