package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.dao.daointerfaces.iLoginDAO;
import nl.oose.han.services.serviceinterfaces.iLoginService;

@RequestScoped
public class LoginService implements iLoginService {

    @Inject
    private iLoginDAO loginDAO;

    @Override
    public boolean validateUser(String username, String password) {
        return loginDAO.validateUser(username, password);
    }

    @Override
    public String getUserToken(String username) {
        return loginDAO.getUserToken(username);
    }
}