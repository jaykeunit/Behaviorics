package behaviorics;

import behaviorics.httpRequests.CameraRequests;
import behaviorics.httpRequests.RepairLogRequests;
import behaviorics.httpRequests.FailImagesRequests;
import behaviorics.models.Camera;
import behaviorics.models.RepairLog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CameraRequests.class, RepairLogRequests.class, FailImagesRequests.class, CameraFailService.class})
public class CameraFailServiceTest {

    CameraFailService service;

    @Before
    public void setUp() throws Exception {
        service = new CameraFailService();
    }

    @Test
    public void testSetCameraToDown() throws Exception{
        Camera camera = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);

        PowerMockito.stub(method(CameraRequests.class, "getCameraById")).toReturn(camera);
        PowerMockito.stub(method(CameraRequests.class, "updateCamera")).toReturn("OK");

        assertEquals("OK", service.setCameraToDown(1));
    }

    @Test
    public void testCreateRepairLogForCamera() throws Exception{
        PowerMockito.stub(method(RepairLogRequests.class, "createRepairLog")).toReturn("OK");

        assertEquals("OK", service.createRepairLogForCamera(1, "water Damage"));
    }

    @Test
    public void testCreateFailImage() throws Exception{
        RepairLog repairLog1 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "NA", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog2 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "NA", new Date(1994, 8, 13), new Date(1994, 8, 13));

        List<RepairLog> repairLogList = Arrays.asList(repairLog1, repairLog2);
        byte[] image = new byte[10];

        PowerMockito.stub(method(RepairLogRequests.class, "getRepairLogsByCameraId")).toReturn(repairLogList);
        PowerMockito.stub(method(FailImagesRequests.class, "createFailImage")).toReturn("OK");

        assertEquals("OK", service.createFailImage(1, image));
    }
}
