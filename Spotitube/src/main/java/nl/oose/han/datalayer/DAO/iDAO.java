package nl.oose.han.datalayer.DAO;

import java.util.List;

public interface iDAO<T> {
    void add(T entity, String token);
    void update(T entity, String token);
    void delete(int id, String token);
    T get(int id, String token);
    List<T> getAll(String token);
}