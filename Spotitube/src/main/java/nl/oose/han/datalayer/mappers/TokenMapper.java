package nl.oose.han.datalayer.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenMapper {

    public String validateUsername(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return rs.getString("username");
            }
        return null;
    }

    public String validateToken(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return rs.getString("userToken");
        }
        return null;
    }
}
