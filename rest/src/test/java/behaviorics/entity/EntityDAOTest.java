package behaviorics.entity;

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
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EntityDAO.class)
public class EntityDAOTest {

    private EntityBO entityBO;
    private EntityDAO entityDAO;

    @Before
    public void setUp() throws Exception{
        entityDAO = new EntityDAO();
        entityBO = new EntityBO(2, 1, "UH Victoria", "8779704848", "UHV");
    }

    @Test
    public void testGetEntityByIDSuccessful() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("entityID")).thenReturn(2);
        Mockito.when(rs.getInt("organizationID")).thenReturn(1);
        Mockito.when(rs.getString("entityName")).thenReturn("UH Victoria");
        Mockito.when(rs.getString("contactNumber")).thenReturn("8779704848");
        Mockito.when(rs.getString("entityAcronym")).thenReturn("UHV");

        assertEquals(entityBO.toString(), entityDAO.getEntityById(2).toString());
    }

    @Test
    public void testGetEntityByEntityNameSuccessful() throws Exception{
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("entityID")).thenReturn(2);
        Mockito.when(rs.getInt("organizationID")).thenReturn(1);
        Mockito.when(rs.getString("entityName")).thenReturn("UH Victoria");
        Mockito.when(rs.getString("contactNumber")).thenReturn("8779704848");
        Mockito.when(rs.getString("entityAcronym")).thenReturn("UHV");

        assertEquals(entityBO.toString(), entityDAO.getEntityByEntityName("UH Victoria").toString());
    }

    @Test
    public void testGetAllEntitiesByOrganizationID() throws Exception{
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        List<EntityBO> entityList = new ArrayList<>();
        entityList.add(entityBO);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("entityID")).thenReturn(2);
        Mockito.when(rs.getInt("organizationID")).thenReturn(1);
        Mockito.when(rs.getString("entityName")).thenReturn("UH Victoria");
        Mockito.when(rs.getString("contactNumber")).thenReturn("8779704848");
        Mockito.when(rs.getString("entityAcronym")).thenReturn("UHV");


        assertEquals(entityList.toString(), entityDAO.getAllEntitiesByOrganizationID(1).toString());
    }

    @Test
    public void testCreateEntity() throws Exception{
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        entityDAO.createEntity(entityBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testUpdateEntityById() throws Exception{
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        entityDAO.updateEntityById(10, entityBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testDeleteEntityById() throws Exception{
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        entityDAO.deleteEntityById(10);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }
}
