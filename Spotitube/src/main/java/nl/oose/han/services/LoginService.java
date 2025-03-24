package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.dao.LoginDAO;
import nl.oose.han.services.serviceinterfaces.iLoginService;

@RequestScoped
public class LoginService implements iLoginService {

    @Inject
    private LoginDAO loginDAO;

    @Override
    public boolean validateUser(String username, String password) {
        return loginDAO.validateUser(username, password);
    }
}