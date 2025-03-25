package nl.oose.han.datalayer.dao.mappers;

import nl.oose.han.datalayer.mappers.LoginMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class LoginMapperTest {

    @Mock
    private ResultSet rs;

    @InjectMocks
    private LoginMapper sut;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateUserReturnsTrueWhenResultSetHasNext() throws SQLException {
        //Arrange
        when(rs.next()).thenReturn(true);

        //Act
        boolean result = sut.validateUser(rs);

        //Assert
        assertTrue(result);
    }

    @Test
    void testValidateUserReturnsFalseWhenResultSetHasNoNext() throws SQLException {
        //Arrange
        when(rs.next()).thenReturn(false);

        //Act
        boolean result = sut.validateUser(rs);

        //Assert
        assertFalse(result);
    }
}
