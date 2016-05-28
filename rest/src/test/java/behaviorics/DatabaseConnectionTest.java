package behaviorics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DatabaseConnection.class, DriverManager.class})
public class DatabaseConnectionTest {

    DatabaseConnection connection;

    @Before
    public void setUp() throws Exception {
        connection = new DatabaseConnection();
    }

    @Test
    public void testPrepareStatement() throws Exception {
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        Mockito.when(mockConnection.prepareStatement("Mock query")).thenReturn(mockPreparedStatement);

        connection.conn = mockConnection;

        assertEquals(mockPreparedStatement, connection.prepareStatement("Mock query"));
    }

}