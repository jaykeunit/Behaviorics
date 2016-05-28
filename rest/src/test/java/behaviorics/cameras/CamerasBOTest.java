package behaviorics.cameras;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class CamerasBOTest {

    private CamerasBO camerasBO;

    @Before
    public void setUp() {
        camerasBO = new CamerasBO(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
    }

    @Test
    public void testEmptyConstructor() {
        CamerasBO camerasBo = new CamerasBO();
        assertEquals(0, camerasBo.getId());
    }

    @Test
    public void testInitialStateOfCamera() {
        assertEquals(1, camerasBO.getId());
        assertEquals("testCamera", camerasBO.getCameraName());
        assertEquals("testIP", camerasBO.getFeedIP());
        assertEquals("testFeedCred", camerasBO.getFeedCredential());
        assertEquals("testPassword", camerasBO.getFeedPassword());
        assertEquals(0.00, camerasBO.getLocationX(), 0.001);
        assertEquals(0.00, camerasBO.getLocationY(), 0.001);
        assertEquals(new Date(2016, 3, 27).toString(), camerasBO.getDateInstalled().toString());
        assertEquals(1, camerasBO.getFloorID());
        assertEquals("vendorName", camerasBO.getVendorName());
        assertEquals("maintenanceName", camerasBO.getLocalMaintenanceName());
        assertEquals("maintenanceNum", camerasBO.getLocalMaintenanceNumber());
        assertEquals("vendorNum", camerasBO.getVendorPhoneNumber());
        assertEquals("vendorEmail", camerasBO.getVendorEmail());
        assertEquals("warrantyExpiration", camerasBO.getWarrantyExpiration());
        assertEquals("Working", camerasBO.getCameraStatus());
        assertEquals(1, camerasBO.getOrgId());
    }

    @Test
    public void testToString() {
        String toStringTester = "CamerasBO{" +
                "id=" + 1 +
                ", cameraName='" + "testCamera" + '\'' +
                ", feedIP='" + "testIP" + '\'' +
                ", feedCredential='" + "testFeedCred" + '\'' +
                ", feedPassword='" + "testPassword" + '\'' +
                ", locationX=" + 0.00 +
                ", locationY=" + 0.00 +
                ", dateInstalled='" + "3916-04-27" + '\'' +
                ", floorID=" + 1 +
                ", vendorName='" + "vendorName" + '\'' +
                ", localMaintenanceName='" + "maintenanceName" + '\'' +
                ", localMaintenanceNumber='" + "maintenanceNum" + '\'' +
                ", vendorPhoneNumber='" + "vendorNum" + '\'' +
                ", vendorEmail='" + "vendorEmail" + '\'' +
                ", warrantyExpiration='" + "warrantyExpiration" + '\'' +
                ", cameraStatus='" + "Working" + '\'' +
                ", httpPort='" + 88 + '\'' +
                ", onvifPort='" + 888 + '\'' +
                ", orgId=" + 1 +
                '}';
        assertEquals(toStringTester, camerasBO.toString());
    }
}
