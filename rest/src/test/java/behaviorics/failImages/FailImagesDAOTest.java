package behaviorics.failImages;

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

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FailImagesDAO.class)
public class FailImagesDAOTest {

    private FailImagesBO failImagesBO;
    private FailImagesDAO failImagesDAO;

    @Before
    public void setUp() throws Exception {
        byte[] image = new byte[10];
        failImagesDAO = new FailImagesDAO();
        failImagesBO = new FailImagesBO(1, image, 1);
    }

    @Test
    public void testGetFailImagesByIDSuccessful() throws Exception {
        byte[] image = new byte[10];
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("failImagesID")).thenReturn(1);
        Mockito.when(rs.getBytes("image")).thenReturn(image);
        Mockito.when(rs.getInt("repairID")).thenReturn(1);

        assertEquals(failImagesBO.toString(), failImagesDAO.getFailImagesByID(1).toString());
    }

    @Test
    public void testGetFailImagesByRepairID() throws Exception {
        byte[] image = new byte[10];
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("failImagesID")).thenReturn(1);
        Mockito.when(rs.getBytes("image")).thenReturn(image);
        Mockito.when(rs.getInt("repairID")).thenReturn(1);

        assertEquals(failImagesBO.toString(), failImagesDAO.getFailImagesByRepairID(1).toString());
    }

    @Test
    public void testCreateFailImages() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        failImagesDAO.createFailImages(failImagesBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testUpdateFailImagesById() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        failImagesDAO.updateFailImagesById(100, failImagesBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testDeleteFailImagesById() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        failImagesDAO.deleteFailImagesById(100);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }
}
