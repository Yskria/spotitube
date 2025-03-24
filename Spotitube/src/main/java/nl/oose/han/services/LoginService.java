package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.dao.LoginDAO;

@RequestScoped
public class LoginService {

    @Inject
    private LoginDAO loginDAO;

    public boolean validateUser(String username, String password) {
        return loginDAO.validateUser(username, password);
    }
}