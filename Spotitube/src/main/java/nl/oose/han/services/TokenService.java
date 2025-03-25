package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.dao.TokenDAO;
import nl.oose.han.services.exceptions.TokenUnauthorizedException;
import nl.oose.han.services.serviceinterfaces.iTokenService;

import java.util.Objects;

@RequestScoped
public class TokenService implements iTokenService {

    @Inject
    private TokenDAO tokenDAO;

    @Override
    public void validateToken(String token) throws TokenUnauthorizedException {
        if (!Objects.equals(token, tokenDAO.validateToken(token))) {
            throw new TokenUnauthorizedException("Token is not valid");
        }
    }
}

