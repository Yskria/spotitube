package nl.oose.han.exceptions.exceptionclasses;

public class PlayListNotFoundException extends RuntimeException {
    public PlayListNotFoundException(String message) {
        super(message);
    }
}
