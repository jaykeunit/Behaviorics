package behaviorics.users;

import behaviorics.BehavioricsDatabaseConnection;
import behaviorics.DatabaseConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.ExpectedExceptions;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserDAO.class)
public class UserDAOTest {

    private UserBO userBO;
    private UserDAO userDAO;
    private byte[] salt = new byte[10];
    private byte[] hash = new byte[10];

    @Before
    public void setUp() throws Exception {
        userDAO = new UserDAO();
        userBO = new UserBO(1, "Tusername", salt, hash, "admin", 1);
    }

    @Test
    public void testGetUserByUserName() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);


        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("usersID")).thenReturn(1);
        Mockito.when(rs.getString("userName")).thenReturn("Tusername");
        Mockito.when(rs.getBlob("salt")).thenReturn(new SerialBlob(salt));
        Mockito.when(rs.getBlob("hash")).thenReturn(new SerialBlob(hash));
        Mockito.when(rs.getString("privilege")).thenReturn("admin");
        Mockito.when(rs.getInt("organizationID")).thenReturn(1);

        assertEquals(userBO.toString(), userDAO.getUserByUserName("Tusername").toString());
    }

    @Test
    public void testGetAllUsersByUserOrganizationID() throws Exception {
        userBO = new UserBO(1, "Tusername", "admin", 1, "test");

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        List<UserBO> userBOArrayList = new ArrayList<>();
        userBOArrayList.add(userBO);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("usersID")).thenReturn(1);
        Mockito.when(rs.getString("userName")).thenReturn("Tusername");
        Mockito.when(rs.getString("privilege")).thenReturn("admin");
        Mockito.when(rs.getInt("organizationID")).thenReturn(1);
        Mockito.when(rs.getString("organizationName")).thenReturn("test");

        assertEquals(userBOArrayList.toString(), userDAO.getAllUserByOrganizationID(1).toString());
    }

    @Test
    public void testCreateUser() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        userDAO.createUser(userBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testUpdateUser() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        userDAO.updateUserById(1000,userBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testDeleteUser() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        userDAO.deleteUserById(1000);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }
}
