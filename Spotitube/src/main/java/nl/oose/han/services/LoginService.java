package nl.oose.han.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import nl.oose.han.datalayer.DAO.LoginDAO;
import nl.oose.han.datalayer.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RequestScoped
public class LoginService {

    @Inject
    private LoginDAO loginDAO;

    public boolean validateUser(String username, String password) {
        System.out.println(username + " " + password);
        return loginDAO.validateUser(username, password);
    }
}