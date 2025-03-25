package nl.oose.han.services.exceptions;

public class PlayListNotFoundException extends RuntimeException {
    public PlayListNotFoundException(String message) {
        super(message);
    }
}
