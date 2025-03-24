package nl.oose.han.services.serviceinterfaces;

import nl.oose.han.datalayer.dto.UserDTO;

public interface iLoginService {
    boolean validateUser(String username, String password);
    String getUserToken(String user);
}
