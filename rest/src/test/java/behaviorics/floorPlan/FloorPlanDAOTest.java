package behaviorics.floorPlan;

import behaviorics.BehavioricsDatabaseConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FloorPlanDAO.class)
public class FloorPlanDAOTest {

    private FloorPlanBO floorPlanBO;
    private FloorPlanDAO floorPlanDAO;

    @Before
    public void setUp() throws Exception {
        byte[] image = new byte[10];
        floorPlanDAO = new FloorPlanDAO();
        floorPlanBO = new FloorPlanBO(1, image, 1);
    }

    @Test
    public void testGetFloorPlanByIDSuccessful() throws Exception {
        byte[] image = new byte[10];
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("floorPlanID")).thenReturn(1);
        Mockito.when(rs.getBytes("image")).thenReturn(image);
        Mockito.when(rs.getInt("floorID")).thenReturn(1);

        assertEquals(floorPlanBO.toString(), floorPlanDAO.getFloorPlanByID(1).toString());
    }

    @Test
    public void testGetFloorPlanByFloorID() throws Exception {
        byte[] image = new byte[10];
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("floorPlanID")).thenReturn(1);
        Mockito.when(rs.getBytes("image")).thenReturn(image);
        Mockito.when(rs.getInt("floorID")).thenReturn(1);

        assertEquals(floorPlanBO.toString(), floorPlanDAO.getFloorPlanByFloorID(1).toString());
    }

    @Test
    public void testCreateFloorPlan() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        floorPlanDAO.createFloorPlan(floorPlanBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testUpdateFloorPlanById() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        floorPlanDAO.updateFloorPlanById(100, floorPlanBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testDeleteBuildingById() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        floorPlanDAO.deleteFloorPlanById(100);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }
}
