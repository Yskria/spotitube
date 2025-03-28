package nl.oose.han.services.serviceinterfaces;

import nl.oose.han.exceptions.exceptionclasses.TokenUnauthorizedException;

public interface iTokenService {
    void validateToken(String token) throws TokenUnauthorizedException;
}
