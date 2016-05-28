package behaviorics.floors;

import behaviorics.BehavioricsDatabaseConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FloorDAO.class)
public class FloorDAOTest {

    private FloorBO floorBO;
    private FloorDAO floorDAO;
    private List<FloorBO> testList;

    @Before
    public void setUp() throws Exception {
        floorDAO = new FloorDAO();
        floorBO = new FloorBO(1, 1, 1, "F", "F1");
        testList = new ArrayList<>();
        testList.add(floorBO);
    }

    @Test
    public void testGetFloorByIDSuccessful() throws Exception {
        byte[] image = new byte[10];
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);

        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("floorID")).thenReturn(1);
        Mockito.when(rs.getInt("floorNumber")).thenReturn(1);
        Mockito.when(rs.getString("floorType")).thenReturn("F");
        Mockito.when(rs.getInt("buildingID")).thenReturn(1);
        Mockito.when(rs.getString("nickname")).thenReturn("F1");

        assertEquals(floorBO.toString(), floorDAO.getFloorById(1).toString());
    }

    @Test
    public void testGetFloor() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("floorID")).thenReturn(1);
        Mockito.when(rs.getInt("floorNumber")).thenReturn(1);
        Mockito.when(rs.getString("floorType")).thenReturn("F");
        Mockito.when(rs.getInt("buildingID")).thenReturn(1);
        Mockito.when(rs.getString("nickname")).thenReturn("F1");

        assertEquals(floorDAO.getFloor(1, 1).toString(), floorBO.toString());
    }

    @Test
    public void testGetFloorsByBuildingID() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("floorID")).thenReturn(1);
        Mockito.when(rs.getInt("buildingID")).thenReturn(1);
        Mockito.when(rs.getInt("floorNumber")).thenReturn(1);
        Mockito.when(rs.getString("floorType")).thenReturn("F");
        Mockito.when(rs.getString("nickname")).thenReturn("F1");

        assertEquals(testList.toString(), floorDAO.getFloorsByBuildingID(1).toString());
    }

    @Test
    public void testGetFloorsByOrganizationID() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("floorID")).thenReturn(1);
        Mockito.when(rs.getInt("buildingID")).thenReturn(1);
        Mockito.when(rs.getInt("floorNumber")).thenReturn(1);
        Mockito.when(rs.getString("floorType")).thenReturn("F");
        Mockito.when(rs.getString("nickname")).thenReturn("F1");

        assertEquals(testList.toString(), floorDAO.getAllFloorsForAnOrganization(1).toString());
    }

    @Test
    public void testCreateFloor() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        floorDAO.createFloor(floorBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test(expected=Exception.class)
    public void testCreateFloorFailed() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);

        PowerMockito.doNothing().when(pstmt).setInt(Matchers.anyInt(), Matchers.anyInt());
        PowerMockito.doNothing().when(pstmt).setString(Matchers.anyInt(), Matchers.anyString());
        PowerMockito.doNothing().when(pstmt).setBytes(Matchers.anyInt(), Matchers.any());
        PowerMockito.doThrow(new Exception()).when(dbc).closeConnection();

        floorDAO.createFloor(floorBO);
    }

    @Test
    public void testUpdateFloor() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        floorDAO.updateFloorById(100,floorBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testDeleteFloor() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        floorDAO.deleteFloorById(100);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }
}
