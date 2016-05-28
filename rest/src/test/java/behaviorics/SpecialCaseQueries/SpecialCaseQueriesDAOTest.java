package behaviorics.SpecialCaseQueries;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SpecialCaseQueriesDAO.class)
public class SpecialCaseQueriesDAOTest {

    private MonitorCamerasBO monitorBO;
    private SpecialCaseQueriesDAO specialDAO;
    private List<MonitorCamerasBO> testList;

    @Before
    public void setUp() throws Exception {
        specialDAO = new SpecialCaseQueriesDAO();
        monitorBO = new MonitorCamerasBO(5, 3, "monitorTest", 4, "monitorBuilding", 0, 4);
        testList = new ArrayList<>();
        testList.add(monitorBO);
    }

    @Test
    public void testGetOrganizationToFloorsList() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement p = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(p);
        Mockito.when(p.executeQuery()).thenReturn(rs);

        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("organizationID")).thenReturn(5);
        Mockito.when(rs.getInt("entityID")).thenReturn(3);
        Mockito.when(rs.getString("entityName")).thenReturn("monitorTest");
        Mockito.when(rs.getInt("buildingID")).thenReturn(4);
        Mockito.when(rs.getString("buildingName")).thenReturn("monitorBuilding");
        Mockito.when(rs.getInt("floorID")).thenReturn(0);
        Mockito.when(rs.getInt("floorNumber")).thenReturn(4);

        assertEquals(testList.toString(), specialDAO.getOrganizationToFloorsList().toString());
    }
}
