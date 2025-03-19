package nl.oose.han.datalayer.DAO;

import java.util.List;

public interface iDAO<T> {
    void add(T t, String token);
    void update(T t, String token);
    void delete(int id, String token);
    List<T> getAll(String token);
}