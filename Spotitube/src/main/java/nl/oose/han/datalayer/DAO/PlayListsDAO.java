package nl.oose.han.datalayer.DAO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.oose.han.PlayLists;
import nl.oose.han.datalayer.DTO.PlayListDTO;
import nl.oose.han.datalayer.DTO.TracksDTO;
import nl.oose.han.datalayer.DatabaseConnection;
import nl.oose.han.datalayer.tokenutil.TokenUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PlayListsDAO implements iDAO<PlayListDTO> {
    private final DatabaseConnection databaseConnection = new DatabaseConnection();
    private final TokenUtil tokenUtil = new TokenUtil();

    @Inject
    private PlayListDAO playListDAO;

    @Override
    public void add(PlayListDTO playlist, String token) {
        String username = tokenUtil.getUsernameFromToken(token);
        String query = "INSERT INTO playlist (name, owner) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {
            System.out.println("Adding playlist: " + playlist.getName());
            stmt.setString(1, playlist.getName());
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(PlayListDTO playlist, String token) {
        String username = tokenUtil.getUsernameFromToken(token);
        String query = "UPDATE playlist SET name = ?, owner = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, playlist.getName());
            stmt.setString(2, username);
            stmt.setInt(3, playlist.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id, String token) {
        String username = tokenUtil.getUsernameFromToken(token);
        String query = "DELETE FROM playlist WHERE id = ? AND owner = ?";
        try (Connection conn = DriverManager.getConnection(databaseConnection.connectionString());
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PlayListDTO> getAll(String token) {
        List<PlayListDTO> resultList = new ArrayList<>();
        String username = tokenUtil.getUsernameFromToken(token);
        String query = "SELECT * FROM playlist WHERE owner = ?";
        try (
                Connection con = DriverManager.getConnection(databaseConnection.connectionString());
                PreparedStatement preparedStatement = con.prepareStatement(query)
        ) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                List<TracksDTO> tracks = playListDAO.getAllSongsInPlaylist(id, token);
                resultList.add(new PlayListDTO(id, rs.getString("name"), rs.getString("owner").equals(username), tracks));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}