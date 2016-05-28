package behaviorics.cameras;

import behaviorics.BehavioricsDatabaseConnection;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CamerasDAO.class)
public class CamerasDAOTest {

    private CamerasBO camerasBO;
    private CamerasDAO camerasDAO;

    @Before
    public void setUp() throws Exception {
        camerasDAO = new CamerasDAO();
        camerasBO = new CamerasBO(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
    }

    @Test
    public void testGetCameraByCameraNameSuccessful() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("cameraID")).thenReturn(1);
        Mockito.when(rs.getString("cameraName")).thenReturn("testCamera");
        Mockito.when(rs.getString("feedIP")).thenReturn("testIP");
        Mockito.when(rs.getString("feedCredential")).thenReturn("testFeedCred");
        Mockito.when(rs.getString("feedPassword")).thenReturn("testPassword");
        Mockito.when(rs.getDouble("locationX")).thenReturn(0.00);
        Mockito.when(rs.getDouble("locationY")).thenReturn(0.00);
        Mockito.when(rs.getDate("dateInstalled")).thenReturn(new Date(2016, 3, 27));
        Mockito.when(rs.getInt("floorID")).thenReturn(1);
        Mockito.when(rs.getString("vendorName")).thenReturn("vendorName");
        Mockito.when(rs.getString("localMaintenanceName")).thenReturn("maintenanceName");
        Mockito.when(rs.getString("localMaintenanceNumber")).thenReturn("maintenanceNum");
        Mockito.when(rs.getString("vendorPhoneNumber")).thenReturn("vendorNum");
        Mockito.when(rs.getString("vendorEmail")).thenReturn("vendorEmail");
        Mockito.when(rs.getString("warrantyExpiration")).thenReturn("warrantyExpiration");
        Mockito.when(rs.getString("cameraStatus")).thenReturn("Working");
        Mockito.when(rs.getInt("httpPort")).thenReturn(88);
        Mockito.when(rs.getInt("onvifPort")).thenReturn(888);
        Mockito.when(rs.getInt("orgId")).thenReturn(1);

        assertEquals(camerasBO.toString(), camerasDAO.getCameraByCameraName("testCamera").toString());
    }

    @Test
    public void testGetAllCameras() throws Exception{
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        List<CamerasBO> camerasBOList = new ArrayList<>();
        camerasBOList.add(camerasBO);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("cameraID")).thenReturn(1);
        Mockito.when(rs.getString("cameraName")).thenReturn("testCamera");
        Mockito.when(rs.getString("feedIP")).thenReturn("testIP");
        Mockito.when(rs.getString("feedCredential")).thenReturn("testFeedCred");
        Mockito.when(rs.getString("feedPassword")).thenReturn("testPassword");
        Mockito.when(rs.getDouble("locationX")).thenReturn(0.00);
        Mockito.when(rs.getDouble("locationY")).thenReturn(0.00);
        Mockito.when(rs.getDate("dateInstalled")).thenReturn(new Date(2016, 3, 27));
        Mockito.when(rs.getInt("floorID")).thenReturn(1);
        Mockito.when(rs.getString("vendorName")).thenReturn("vendorName");
        Mockito.when(rs.getString("localMaintenanceName")).thenReturn("maintenanceName");
        Mockito.when(rs.getString("localMaintenanceNumber")).thenReturn("maintenanceNum");
        Mockito.when(rs.getString("vendorPhoneNumber")).thenReturn("vendorNum");
        Mockito.when(rs.getString("vendorEmail")).thenReturn("vendorEmail");
        Mockito.when(rs.getString("warrantyExpiration")).thenReturn("warrantyExpiration");
        Mockito.when(rs.getString("cameraStatus")).thenReturn("Working");
        Mockito.when(rs.getInt("httpPort")).thenReturn(88);
        Mockito.when(rs.getInt("onvifPort")).thenReturn(888);
        Mockito.when(rs.getInt("orgId")).thenReturn(1);

        assertEquals(camerasBOList.toString(), camerasDAO.getAllCameras().toString());
    }

    @Test
    public void testGetCameraByCameraIDSuccessful() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true);
        Mockito.when(rs.getInt("cameraID")).thenReturn(1);
        Mockito.when(rs.getString("cameraName")).thenReturn("testCamera");
        Mockito.when(rs.getString("feedIP")).thenReturn("testIP");
        Mockito.when(rs.getString("feedCredential")).thenReturn("testFeedCred");
        Mockito.when(rs.getString("feedPassword")).thenReturn("testPassword");
        Mockito.when(rs.getDouble("locationX")).thenReturn(0.00);
        Mockito.when(rs.getDouble("locationY")).thenReturn(0.00);
        Mockito.when(rs.getDate("dateInstalled")).thenReturn(new Date(2016, 3, 27));
        Mockito.when(rs.getInt("floorID")).thenReturn(1);
        Mockito.when(rs.getString("vendorName")).thenReturn("vendorName");
        Mockito.when(rs.getString("localMaintenanceName")).thenReturn("maintenanceName");
        Mockito.when(rs.getString("localMaintenanceNumber")).thenReturn("maintenanceNum");
        Mockito.when(rs.getString("vendorPhoneNumber")).thenReturn("vendorNum");
        Mockito.when(rs.getString("vendorEmail")).thenReturn("vendorEmail");
        Mockito.when(rs.getString("warrantyExpiration")).thenReturn("warrantyExpiration");
        Mockito.when(rs.getString("cameraStatus")).thenReturn("Working");
        Mockito.when(rs.getInt("httpPort")).thenReturn(88);
        Mockito.when(rs.getInt("onvifPort")).thenReturn(888);
        Mockito.when(rs.getInt("orgId")).thenReturn(1);

        assertEquals(camerasBO.toString(), camerasDAO.getCameraByCameraId(1).toString());
    }

    @Test
    public void testGetAllCamerasByFloorID() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        List<CamerasBO> buildingList = new ArrayList<>();
        buildingList.add(camerasBO);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("cameraID")).thenReturn(1);
        Mockito.when(rs.getString("cameraName")).thenReturn("testCamera");
        Mockito.when(rs.getString("feedIP")).thenReturn("testIP");
        Mockito.when(rs.getString("feedCredential")).thenReturn("testFeedCred");
        Mockito.when(rs.getString("feedPassword")).thenReturn("testPassword");
        Mockito.when(rs.getDouble("locationX")).thenReturn(0.00);
        Mockito.when(rs.getDouble("locationY")).thenReturn(0.00);
        Mockito.when(rs.getDate("dateInstalled")).thenReturn(new Date(2016, 3, 27));
        Mockito.when(rs.getInt("floorID")).thenReturn(1);
        Mockito.when(rs.getString("vendorName")).thenReturn("vendorName");
        Mockito.when(rs.getString("localMaintenanceName")).thenReturn("maintenanceName");
        Mockito.when(rs.getString("localMaintenanceNumber")).thenReturn("maintenanceNum");
        Mockito.when(rs.getString("vendorPhoneNumber")).thenReturn("vendorNum");
        Mockito.when(rs.getString("vendorEmail")).thenReturn("vendorEmail");
        Mockito.when(rs.getString("warrantyExpiration")).thenReturn("warrantyExpiration");
        Mockito.when(rs.getString("cameraStatus")).thenReturn("Working");
        Mockito.when(rs.getInt("httpPort")).thenReturn(88);
        Mockito.when(rs.getInt("onvifPort")).thenReturn(888);
        Mockito.when(rs.getInt("orgId")).thenReturn(1);

        assertEquals(buildingList.toString(), camerasDAO.getAllCamerasByFloorID(1).toString());
    }

    @Test
    public void testGetCameraCountByFloor() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement p = Mockito.mock(PreparedStatement.class);
        ResultSet rs = Mockito.mock(ResultSet.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(p);
        Mockito.when(p.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("cameraCount")).thenReturn(1);

        assertEquals(1, camerasDAO.getCameraCountByFloor(1));
    }

    @Test
    public void testGetAllCamerasByOrgId() throws Exception{
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        List<CamerasBO> camerasBOList = new ArrayList<>();
        camerasBOList.add(camerasBO);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        Mockito.when(pstmt.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("cameraID")).thenReturn(1);
        Mockito.when(rs.getString("cameraName")).thenReturn("testCamera");
        Mockito.when(rs.getString("feedIP")).thenReturn("testIP");
        Mockito.when(rs.getString("feedCredential")).thenReturn("testFeedCred");
        Mockito.when(rs.getString("feedPassword")).thenReturn("testPassword");
        Mockito.when(rs.getDouble("locationX")).thenReturn(0.00);
        Mockito.when(rs.getDouble("locationY")).thenReturn(0.00);
        Mockito.when(rs.getDate("dateInstalled")).thenReturn(new Date(2016, 3, 27));
        Mockito.when(rs.getInt("floorID")).thenReturn(1);
        Mockito.when(rs.getString("vendorName")).thenReturn("vendorName");
        Mockito.when(rs.getString("localMaintenanceName")).thenReturn("maintenanceName");
        Mockito.when(rs.getString("localMaintenanceNumber")).thenReturn("maintenanceNum");
        Mockito.when(rs.getString("vendorPhoneNumber")).thenReturn("vendorNum");
        Mockito.when(rs.getString("vendorEmail")).thenReturn("vendorEmail");
        Mockito.when(rs.getString("warrantyExpiration")).thenReturn("warrantyExpiration");
        Mockito.when(rs.getString("cameraStatus")).thenReturn("Working");
        Mockito.when(rs.getInt("httpPort")).thenReturn(88);
        Mockito.when(rs.getInt("onvifPort")).thenReturn(888);
        Mockito.when(rs.getInt("orgId")).thenReturn(1);

        assertEquals(camerasBOList.toString(), camerasDAO.getAllCamerasByOrgId(1).toString());
    }

    @Test
    public void testGetCameraByIdOk() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement p = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(p);
        Mockito.when(p.executeQuery()).thenReturn(rs);
        Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("cameraID")).thenReturn(1);
        Mockito.when(rs.getString("cameraName")).thenReturn("testCamera");
        Mockito.when(rs.getString("feedIP")).thenReturn("testIP");
        Mockito.when(rs.getString("feedCredential")).thenReturn("testFeedCred");
        Mockito.when(rs.getString("feedPassword")).thenReturn("testPassword");
        Mockito.when(rs.getDouble("locationX")).thenReturn(0.00);
        Mockito.when(rs.getDouble("locationY")).thenReturn(0.00);
        Mockito.when(rs.getDate("dateInstalled")).thenReturn(new Date(2016, 3, 27));
        Mockito.when(rs.getInt("floorID")).thenReturn(1);
        Mockito.when(rs.getString("vendorName")).thenReturn("vendorName");
        Mockito.when(rs.getString("localMaintenanceName")).thenReturn("maintenanceName");
        Mockito.when(rs.getString("localMaintenanceNumber")).thenReturn("maintenanceNum");
        Mockito.when(rs.getString("vendorPhoneNumber")).thenReturn("vendorNum");
        Mockito.when(rs.getString("vendorEmail")).thenReturn("vendorEmail");
        Mockito.when(rs.getString("warrantyExpiration")).thenReturn("warrantyExpiration");
        Mockito.when(rs.getString("cameraStatus")).thenReturn("Working");
        Mockito.when(rs.getInt("httpPort")).thenReturn(88);
        Mockito.when(rs.getInt("onvifPort")).thenReturn(888);
        Mockito.when(rs.getInt("orgId")).thenReturn(1);

        assertEquals(camerasBO.toString(), camerasDAO.getCameraByCameraId(3).toString());
    }

    @Test(expected=SQLException.class)
    public void testGetCameraByIdRSFail() throws Exception {
        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        ResultSet rs = Mockito.mock(ResultSet.class);
        PreparedStatement p = Mockito.mock(PreparedStatement.class);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(p);
        Mockito.when(p.executeQuery()).thenReturn(rs);
        Mockito.doThrow(new SQLException()).when(rs).next();

        camerasDAO.getCameraByCameraId(3);
    }

    @Test
    public void testCreateCamera() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);

        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);
        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        camerasDAO.createCamera(camerasBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testUpdateCameraById() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        camerasDAO.updateCameraById(100, camerasBO);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void testDeleteCameraById() throws Exception{

        BehavioricsDatabaseConnection dbc = Mockito.mock(BehavioricsDatabaseConnection.class);
        PreparedStatement pstmt = Mockito.mock(PreparedStatement.class);
        Mockito.when(dbc.prepareStatement(Matchers.anyString())).thenReturn(pstmt);

        PowerMockito.whenNew(BehavioricsDatabaseConnection.class).withNoArguments().thenReturn(dbc);

        camerasDAO.deleteCameraById(100);

        Mockito.verify(pstmt, Mockito.times(1)).executeUpdate();
    }
}
