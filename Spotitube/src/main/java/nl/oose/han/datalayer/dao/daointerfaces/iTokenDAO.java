package nl.oose.han.datalayer.dao.daointerfaces;

public interface iTokenDAO {
    String getUsernameFromToken(String token);
    String validateToken(String token);
}
