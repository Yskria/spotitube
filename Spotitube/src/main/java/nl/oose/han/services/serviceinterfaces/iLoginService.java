package nl.oose.han.services.serviceinterfaces;

public interface iLoginService {
    boolean validateUser(String username, String password);
    String getUserToken(String user);
}
