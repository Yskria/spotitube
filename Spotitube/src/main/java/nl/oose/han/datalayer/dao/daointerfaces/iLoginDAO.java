package nl.oose.han.datalayer.dao.daointerfaces;

public interface iLoginDAO {
    boolean validateUser(String username, String password);
}
