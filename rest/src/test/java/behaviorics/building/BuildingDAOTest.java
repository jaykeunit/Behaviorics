package behaviorics.building;

import behaviorics.BehavioricsDatabaseConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BuildingDAO.class)
public class BuildingDAOTest {

    private BuildingBO buildingBO;
    private BuildingDAO buildingDAO;

    @Before
    public void setUp() throws Exception {
        buildingDAO = new BuildingDAO();
        buildingBO = new BuildingBO(1, "DereckZoolanderCenterForKidsWhoCantReadGood", 1, 1234, "University Drive", "Houston", 77004, "DZC", "TX");
    }

    @Test
    public void testGetBuildingByIDSuccessful() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("buildingID")).thenReturn(1);
        Mockito.when(rs.getString("buildingName")).thenReturn("DereckZoolanderCenterForKidsWhoCantReadGood");
        Mockito.when(rs.getInt("entityID")).thenReturn(1);
        Mockito.when(rs.getInt("streetCode")).thenReturn(1234);
        Mockito.when(rs.getString("streetName")).thenReturn("University Drive");
        Mockito.when(rs.getString("city")).thenReturn("Houston");
        Mockito.when(rs.getInt("zipcode")).thenReturn(77004);
        Mockito.when(rs.getString("buildingAcronym")).thenReturn("DZC");
        Mockito.when(rs.getString("state")).thenReturn("TX");

        assertEquals(buildingBO.toString(), buildingDAO.getBuildingById(1).toString());
    }

    @Test
    public void testGetBuildingByBuildingNameSuccessful() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("buildingID")).thenReturn(1);
        Mockito.when(rs.getString("buildingName")).thenReturn("DereckZoolanderCenterForKidsWhoCantReadGood");
        Mockito.when(rs.getInt("entityID")).thenReturn(1);
        Mockito.when(rs.getInt("streetCode")).thenReturn(1234);
        Mockito.when(rs.getString("streetName")).thenReturn("University Drive");
        Mockito.when(rs.getString("city")).thenReturn("Houston");
        Mockito.when(rs.getInt("zipcode")).thenReturn(77004);
        Mockito.when(rs.getString("buildingAcronym")).thenReturn("DZC");
        Mockito.when(rs.getString("state")).thenReturn("TX");

        assertEquals(buildingBO.toString(), buildingDAO.getBuildingByBuildingName("DereckZoolanderCenterForKidsWhoCantReadGood").toString());
    }

    @Test
    public void testGetAllBuildingsByEntityID() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        List<BuildingBO> buildingList = new ArrayList<>();
        buildingList.add(buildingBO);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("buildingID")).thenReturn(1);
        Mockito.when(rs.getString("buildingName")).thenReturn("DereckZoolanderCenterForKidsWhoCantReadGood");
        Mockito.when(rs.getInt("entityID")).thenReturn(1);
        Mockito.when(rs.getInt("streetCode")).thenReturn(1234);
        Mockito.when(rs.getString("streetName")).thenReturn("University Drive");
        Mockito.when(rs.getString("city")).thenReturn("Houston");
        Mockito.when(rs.getInt("zipcode")).thenReturn(77004);
        Mockito.when(rs.getString("buildingAcronym")).thenReturn("DZC");
        Mockito.when(rs.getString("state")).thenReturn("TX");

        assertEquals(buildingList.toString(), buildingDAO.getAllBuildingsByEntityID(1).toString());
    }

    @Test
    public void testGetAllBuildingsByOrganizationID() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        List<BuildingBO> buildingList = new ArrayList<>();
        buildingList.add(buildingBO);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("buildingID")).thenReturn(1);
        Mockito.when(rs.getString("buildingName")).thenReturn("DereckZoolanderCenterForKidsWhoCantReadGood");
        Mockito.when(rs.getInt("entityID")).thenReturn(1);
        Mockito.when(rs.getInt("streetCode")).thenReturn(1234);
        Mockito.when(rs.getString("streetName")).thenReturn("University Drive");
        Mockito.when(rs.getString("city")).thenReturn("Houston");
        Mockito.when(rs.getInt("zipcode")).thenReturn(77004);
        Mockito.when(rs.getString("buildingAcronym")).thenReturn("DZC");
        Mockito.when(rs.getString("state")).thenReturn("TX");

        assertEquals(buildingList.toString(), buildingDAO.getAllBuildingsByOrganizationID(1).toString());
    }

    @Test
    public void testCreateBuilding() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        buildingDAO.createBuilding(buildingBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testUpdateBuildingById() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        buildingDAO.updateBuildingById(100, buildingBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testDeleteBuildingById() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        buildingDAO.deleteBuildingById(100);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }
}