package behaviorics.SpecialCaseQueries;

import org.junit.Before;
import org.junit.Test;
import java.sql.Date;
import static org.junit.Assert.*;

public class EditCameraBOTest {

    private EditCameraBO editCameraBO;

    @Before
    public void setUp(){
        editCameraBO = new EditCameraBO(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1, "entityName","buildingName", 1);
    }

    @Test
    public void testEmptyConstructor() {
        EditCameraBO editCameraBO = new EditCameraBO();
        assertEquals(0, editCameraBO.getId());
    }

    @Test
    public void testInitialStateOfEditCamera() {
        assertEquals(1, editCameraBO.getId());
        assertEquals("testCamera", editCameraBO.getCameraName());
        assertEquals("testIP", editCameraBO.getFeedIP());
        assertEquals("testFeedCred", editCameraBO.getFeedCredential());
        assertEquals("testPassword", editCameraBO.getFeedPassword());
        assertEquals(0.00, editCameraBO.getLocationX(), 0.001);
        assertEquals(0.00, editCameraBO.getLocationY(), 0.001);
        assertEquals(new Date(2016, 3, 27).toString(), editCameraBO.getDateInstalled().toString());
        assertEquals(1, editCameraBO.getFloorID());
        assertEquals("vendorName", editCameraBO.getVendorName());
        assertEquals("maintenanceName", editCameraBO.getLocalMaintenanceName());
        assertEquals("maintenanceNum", editCameraBO.getLocalMaintenanceNumber());
        assertEquals("vendorNum", editCameraBO.getVendorPhoneNumber());
        assertEquals("vendorEmail", editCameraBO.getVendorEmail());
        assertEquals("warrantyExpiration", editCameraBO.getWarrantyExpiration());
        assertEquals("Working", editCameraBO.getCameraStatus());
        assertEquals(88, editCameraBO.getHttpPort());
        assertEquals(888, editCameraBO.getOnvifPort());
        assertEquals(1, editCameraBO.getOrgId());
        assertEquals("entityName", editCameraBO.getEntityName());
        assertEquals("buildingName", editCameraBO.getBuildingName());
        assertEquals(1, editCameraBO.getFloorNumber());
    }
}
