package nl.oose.han.datalayer.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginMapper {
    public boolean validateUser(ResultSet rs) throws SQLException {
        return rs.next();
    }
}
