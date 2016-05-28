package behaviorics.repairLogs;

import behaviorics.BehavioricsDatabaseConnection;
import behaviorics.DatabaseConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RepairLogsDAO.class)
public class RepairLogsDAOTest {

    private RepairLogsBO repairLogsBO;
    private RepairLogsDAO repairLogsDAO;

    @Before
    public void setUp() throws Exception {
        repairLogsDAO = new RepairLogsDAO();
        repairLogsBO = new RepairLogsBO(1, 1, new Date(2016,3,28), "Working", "Water Damage", new Date(2016,3,28), new Date(2016,3,28));
    }

    @Test
    public void testGetRepairLogsByRepairLogsIDSuccessful() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("repairID")).thenReturn(1);
        Mockito.when(rs.getInt("cameraID")).thenReturn(1);
        Mockito.when(rs.getDate("dateFailed")).thenReturn(new Date(2016,3,28));
        Mockito.when(rs.getString("repairStatus")).thenReturn("Working");
        Mockito.when(rs.getString("failReason")).thenReturn("Water Damage");
        Mockito.when(rs.getDate("dateRepaired")).thenReturn(new Date(2016,3,28));
        Mockito.when(rs.getDate("repairRequestDate")).thenReturn(new Date(2016,3,28));


        assertEquals(repairLogsBO.toString(), repairLogsDAO.getRepairLogByRepairLogID(1).toString());
    }

    @Test
    public void testGetAllRepairLogssByCameraID() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        List<RepairLogsBO> buildingList = new ArrayList<>();
        buildingList.add(repairLogsBO);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("repairID")).thenReturn(1);
        Mockito.when(rs.getInt("cameraID")).thenReturn(1);
        Mockito.when(rs.getDate("dateFailed")).thenReturn(new Date(2016,3,28));
        Mockito.when(rs.getString("repairStatus")).thenReturn("Working");
        Mockito.when(rs.getString("failReason")).thenReturn("Water Damage");
        Mockito.when(rs.getDate("dateRepaired")).thenReturn(new Date(2016,3,28));
        Mockito.when(rs.getDate("repairRequestDate")).thenReturn(new Date(2016,3,28));

        assertEquals(buildingList.toString(), repairLogsDAO.getAllRepairLogsByCameraID(1).toString());
    }

    @Test
    public void testGetActiveRepairs() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        List<RepairLogsBO> repairLogsBOList = new ArrayList<>();
        repairLogsBOList.add(repairLogsBO);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("repairID")).thenReturn(1);
        Mockito.when(rs.getInt("cameraID")).thenReturn(1);
        Mockito.when(rs.getDate("dateFailed")).thenReturn(new Date(2016,3,28));
        Mockito.when(rs.getString("repairStatus")).thenReturn("Working");
        Mockito.when(rs.getString("failReason")).thenReturn("Water Damage");
        Mockito.when(rs.getDate("dateRepaired")).thenReturn(new Date(2016,3,28));
        Mockito.when(rs.getDate("repairRequestDate")).thenReturn(new Date(2016,3,28));

        assertEquals(repairLogsBOList.toString(), repairLogsDAO.getActiveRepairs().toString());
    }

    @Test
    public void testCreateRepairLogs() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        repairLogsDAO.createRepairLog(repairLogsBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testUpdateRepairLogsById() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        repairLogsDAO.updateRepairLogById(100, repairLogsBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testDeleteRepairLogsById() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        repairLogsDAO.deleteRepairLogById(100);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }
}
