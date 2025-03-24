package nl.oose.han.datalayer.dao.daointerfaces;

import nl.oose.han.datalayer.dto.PlayListDTO;

import java.util.List;

public interface iPlayListsDAO {
    void add(PlayListDTO playlist, String token);
    void update(PlayListDTO playlist, String token);
    void delete(int id, String token);
    List<PlayListDTO> getAll(String token);
}
