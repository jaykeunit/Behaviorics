package behaviorics.workingStateImages;

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
@PrepareForTest(WorkingStateImagesDAO.class)
public class WorkingStateImagesDAOTest {

    private WorkingStateImagesBO workingStateImagesBO;
    private WorkingStateImagesDAO workingStateImagesDAO;

    @Before
    public void setUp() throws Exception {
        byte[] image = new byte[10];
        workingStateImagesDAO = new WorkingStateImagesDAO();
        workingStateImagesBO = new WorkingStateImagesBO(1, image, 1);
    }

    @Test
    public void testGetWorkingStateImagesByIDSuccessful() throws Exception {
        byte[] image = new byte[10];
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("imageID")).thenReturn(1);
        Mockito.when(rs.getBytes("image")).thenReturn(image);
        Mockito.when(rs.getInt("cameraID")).thenReturn(1);

        assertEquals(workingStateImagesBO.toString(), workingStateImagesDAO.getWorkingStateImagesByID(1).toString());
    }

    @Test
    public void testGetWorkingStateImagesByCameraID() throws Exception {
        byte[] image = new byte[10];
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("imageID")).thenReturn(1);
        Mockito.when(rs.getBytes("image")).thenReturn(image);
        Mockito.when(rs.getInt("cameraID")).thenReturn(1);

        assertEquals(workingStateImagesBO.toString(), workingStateImagesDAO.getWorkingStateImagesByCameraID(1).toString());
    }

    @Test
    public void testCreateWorkingStateImages() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        workingStateImagesDAO.createWorkingStateImages(workingStateImagesBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testUpdateWorkingStateImagesById() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        workingStateImagesDAO.updateWorkingStateImagesById(100, workingStateImagesBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testDeleteWorkingStateImagesById() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        workingStateImagesDAO.deleteWorkingStateImagesById(100);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }
}
