package behaviorics.organization;

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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(OrganizationDAO.class)
public class OrganizationDAOTest {

    private OrganizationBO organizationBO;
    private OrganizationDAO organizationDAO;

    @Before
    public void setUp() throws Exception {
        organizationDAO = new OrganizationDAO();
        organizationBO = new OrganizationBO(1, "University Of Houston");
    }

    @Test
    public void testGetOrganizationByIDSuccessful() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("organizationID")).thenReturn(1);
        Mockito.when(rs.getString("organizationName")).thenReturn("University Of Houston");

        assertEquals(organizationBO.toString(), organizationDAO.getOrganizationById(1).toString());
    }

    @Test
    public void testGetOrganizationByOrganizationNameSuccessful() throws Exception{
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("organizationID")).thenReturn(1);
        Mockito.when(rs.getString("organizationName")).thenReturn("University Of Houston");

        assertEquals(organizationBO.toString(), organizationDAO.getOrganizationByOrganizationName("University Of Houston").toString());
    }

    @Test
    public void testGetAllOrganizations() throws Exception{
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        List<OrganizationBO> organizationList = new ArrayList<>();
        organizationList.add(organizationBO);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("organizationID")).thenReturn(1);
        Mockito.when(rs.getString("organizationName")).thenReturn("University Of Houston");

        assertEquals(organizationList.toString(), organizationDAO.getAllOrganizations().toString());
    }

    @Test
    public void testCreateOrganization() throws Exception{
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        organizationDAO.createOrganization(organizationBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testUpdateOrganizationById() throws Exception{
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        organizationDAO.updateOrganizationById(10, organizationBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testDeleteOrganizationById() throws Exception{
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        organizationDAO.deleteOrganizationById(10);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }
}
