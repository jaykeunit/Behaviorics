package behaviorics;

import behaviorics.httpRequests.RepairLogRequests;
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
@PrepareForTest({RepairLogRequests.class, HomeService.class})
public class HomeServiceTest {
    HomeService service;

    @Before
    public void setUp() throws Exception {
        service = new HomeService();
    }

    @Test
    public void testGetOperationalCameras() throws Exception {
        Camera camera1 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        Camera camera3 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Damage", 88, 888, 1);

        List<Camera> cameraList = Arrays.asList(camera1, camera2, camera3);



        assertEquals(Arrays.asList(camera1, camera2) , service.getOperationalCameras(cameraList));
    }

    @Test
    public void testGetRepairRequestedCameras() throws Exception {
        Camera camera1 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "UnderRepair", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "UnderRepair", 88, 888, 1);
        Camera camera3 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Damage", 88, 888, 1);

        List<Camera> cameraList = Arrays.asList(camera1, camera2, camera3);



        assertEquals(Arrays.asList(camera1, camera2) , service.getRepairRequestedCameras(cameraList));
    }

    @Test
    public void testGetDamagedCameras() throws Exception {
        Camera camera1 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Down", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Down", 88, 888, 1);
        Camera camera3 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);

        List<Camera> cameraList = Arrays.asList(camera1, camera2, camera3);



        assertEquals(Arrays.asList(camera1, camera2) , service.getDamagedCameras(cameraList));
    }

    @Test
    public void TestGetActiveRepairs() throws Exception {
        RepairLog repairLog1 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "NA", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog2 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "NA", new Date(1994, 8, 13), new Date(1994, 8, 13));

        List<RepairLog> repairLogList = Arrays.asList(repairLog1, repairLog2);

        PowerMockito.stub(method(RepairLogRequests.class, "getActiveRepairs")).toReturn(repairLogList);

        assertEquals(repairLogList, service.getActiveRepairs());
    }
}
