package nl.oose.han.services.serviceinterfaces;

import nl.oose.han.services.exceptions.TokenUnauthorizedException;

public interface iTokenService {
    void validateToken(String token) throws TokenUnauthorizedException;
}
