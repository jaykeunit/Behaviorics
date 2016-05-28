package behaviorics;

import behaviorics.httpRequests.*;
import behaviorics.models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
@PrepareForTest({BuildingRequests.class, EntityRequests.class, FloorRequests.class, FloorPlanRequests.class,
        WorkingStateImageRequests.class, FailImagesRequests.class, CameraRequests.class, MonitorCamerasPageRequest.class,
        RepairLogRequests.class, ManagementService.class})
public class ManagementServiceTest {
    ManagementService service;

    @Before
    public void setUp() throws Exception {
        service = new ManagementService();
    }

    @Test
    public void testGetBuildings() throws Exception {
        Building building1 = new Building(1, "TestBuilding1", 10, 1234, "University Drive", "City", 77004, "AAA", "TX");
        Building building2 = new Building(1, "TestBuilding2", 10, 1234, "University Drive", "City", 77004, "BBB", "TX");
        List<Building> buildings = Arrays.asList(building1 ,building2);

        PowerMockito.stub(method(BuildingRequests.class, "getBuildingsByEntityId")).toReturn(buildings);

        assertEquals(buildings, service.getBuildings(1));
    }

    @Test
    public void testGetEntities() throws Exception {
        Entity entity1 = new Entity(1, 1, "TestEntity", "7137432255","TTT");
        Entity entity2 = new Entity(1, 1, "TestEntity", "7137432255","AAA");
        List<Entity> entities = Arrays.asList(entity1 ,entity2);

        PowerMockito.stub(method(EntityRequests.class, "getAllEntitiesByOrganizationID")).toReturn(entities);

        assertEquals(entities, service.getEntities(1));
    }

    @Test
    public void testGetFloors() throws Exception {
        Floor floor1 = new Floor(1, 1, 2, "F", "MDA");
        Floor floor2 = new Floor(1, 1, 1, "F", "MDA");
        List<Floor> outOfOrderFloors = Arrays.asList(floor1 ,floor2);

        PowerMockito.stub(method(FloorRequests.class, "getFloorsByBuildingId")).toReturn(outOfOrderFloors);

        assertEquals(Arrays.asList(floor2 ,floor1), service.getFloors(1));
    }

    @Test
    public void testGetFloorImageAsByteArray() throws Exception {
        byte[] image = new byte[10];
        FloorPlan floorPlan = new FloorPlan(1, image, 1);

        PowerMockito.stub(method(FloorPlanRequests.class, "getFloorPlanByFloorId")).toReturn(floorPlan);

        assertEquals(image, service.getImageAsByteArray(1));
    }

    @Test
    public void testGetWorkingImageAsByteArray() throws Exception {
        byte[] image = new byte[10];
        WorkingStateImage workingStateImage = new WorkingStateImage(1, image, 1);

        PowerMockito.stub(method(WorkingStateImageRequests.class, "getWorkingStateImagesForCameraId")).toReturn(workingStateImage);

        assertEquals(image, service.getWorkingImageAsByteArray(1));
    }

    @Test
    public void testGetFailImageAsByteArray() throws Exception {
        byte[] image = new byte[10];
        FailImage failImage = new FailImage(1, image, 1);

        PowerMockito.stub(method(FailImagesRequests.class, "getFailImageIdByRepairId")).toReturn(failImage);

        assertEquals(image, service.getMostRecentFailImageByID(1));
    }

    @Test
    public void testGetCameras() throws Exception {
        Camera camera1 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);

        List<Camera> cameraList = Arrays.asList(camera1 ,camera2);

        PowerMockito.stub(method(CameraRequests.class, "getAllCamerasForFloorId")).toReturn(cameraList);

        assertEquals(cameraList, service.getCameras(1));
    }

    @Test
    public void testGetOrganizationToFloorsList() throws Exception {
        MonitorCameras monitorCameras1 = new MonitorCameras(1, 1, "University", 1, "Research", 1, 1);
        MonitorCameras monitorCameras2 = new MonitorCameras(1, 1, "University", 1, "Research", 1, 1);

        List<MonitorCameras> monitorCamerasList = Arrays.asList(monitorCameras1, monitorCameras2);

        PowerMockito.stub(method(MonitorCamerasPageRequest.class, "getOrganizationToFloorsList")).toReturn(monitorCamerasList);

        assertEquals(monitorCamerasList, service.getOrganizationToFloorsList());
    }

    @Test
    public void testGetUniqueEntityName() throws Exception {
        MonitorCameras monitorCameras1 = new MonitorCameras(1, 1, "University", 1, "Research", 1, 1);
        MonitorCameras monitorCameras2 = new MonitorCameras(1, 1, "University", 1, "Research", 1, 1);

        List<MonitorCameras> monitorCamerasList = Arrays.asList(monitorCameras1, monitorCameras2);

        assertEquals(Arrays.asList("University"), service.getUniqueEntityName(monitorCamerasList));
    }

    @Test
    public void testGetUniqueBuildingName() throws Exception {
        MonitorCameras monitorCameras1 = new MonitorCameras(1, 1, "University", 1, "Research", 1, 1);
        MonitorCameras monitorCameras2 = new MonitorCameras(1, 1, "University", 1, "Research", 1, 1);

        List<MonitorCameras> monitorCamerasList = Arrays.asList(monitorCameras1, monitorCameras2);

        assertEquals(Arrays.asList("Research"), service.getUniqueBuildingName(monitorCamerasList));
    }

    @Test
    public void testGetBuildingsByEntityName() throws Exception {
        MonitorCameras monitorCameras1 = new MonitorCameras(1, 1, "University", 1, "Research", 1, 1);
        MonitorCameras monitorCameras2 = new MonitorCameras(1, 1, "University", 1, "Research", 1, 1);
        MonitorCameras monitorCameras3 = new MonitorCameras(1, 1, "Bank", 1, "Research", 1, 1);

        List<MonitorCameras> monitorCamerasList = Arrays.asList(monitorCameras1, monitorCameras2, monitorCameras3);

        assertEquals(Arrays.asList(monitorCameras1, monitorCameras2), service.getBuildingsByEntityName("University", monitorCamerasList));
    }

    @Test
    public void TestGetFloorsByBuildingName() throws Exception {
        MonitorCameras monitorCameras1 = new MonitorCameras(1, 1, "University", 1, "Research", 1, 1);
        MonitorCameras monitorCameras2 = new MonitorCameras(1, 1, "University", 1, "Research", 1, 1);
        MonitorCameras monitorCameras3 = new MonitorCameras(1, 1, "Bank", 1, "Food", 2, 1);

        List<MonitorCameras> monitorCamerasList = Arrays.asList(monitorCameras1, monitorCameras2, monitorCameras3);

        assertEquals(Arrays.asList(monitorCameras1, monitorCameras2), service.getFloorsByBuildingName("Research", monitorCamerasList));
    }

    @Test
    public void TestGetFloorsByBuildingNameCompareGreaterThan() throws Exception {
        MonitorCameras monitorCameras1 = new MonitorCameras(1, 1, "University", 1, "Research", 1, 1);
        MonitorCameras monitorCameras2 = new MonitorCameras(1, 1, "University", 1, "Research", 1, 2);
        MonitorCameras monitorCameras3 = new MonitorCameras(1, 1, "Bank", 1, "Food", 2, 1);

        List<MonitorCameras> monitorCamerasList = Arrays.asList(monitorCameras1, monitorCameras2, monitorCameras3);

        assertEquals(Arrays.asList(monitorCameras1, monitorCameras2), service.getFloorsByBuildingName("Research", monitorCamerasList));
    }

    @Test
    public void TestGetFloorsByBuildingNameCompareLessThan() throws Exception {
        MonitorCameras monitorCameras1 = new MonitorCameras(1, 1, "University", 1, "Research", 1, 2);
        MonitorCameras monitorCameras2 = new MonitorCameras(1, 1, "University", 1, "Research", 1, 1);
        MonitorCameras monitorCameras3 = new MonitorCameras(1, 1, "Bank", 1, "Food", 2, 1);

        List<MonitorCameras> monitorCamerasList = Arrays.asList(monitorCameras1, monitorCameras2, monitorCameras3);

        assertEquals(Arrays.asList(monitorCameras2, monitorCameras1), service.getFloorsByBuildingName("Research", monitorCamerasList));
    }

    @Test
    public void testAllGetCameras() throws Exception {
        Camera camera1 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);

        List<Camera> cameraList = Arrays.asList(camera1 ,camera2);

        PowerMockito.stub(method(CameraRequests.class, "getAllCameras")).toReturn(cameraList);

        assertEquals(cameraList, service.getAllCameras());
    }

    @Test
    public void testGetCameraScreenName() throws Exception {
        Camera camera = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);

        assertEquals("testCamera-1", service.getCameraScreenName(camera));
    }

    @Test
    public void testGetCameraScreenNameLengthZero() throws Exception {
        Camera camera = new Camera(1, "", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);

        assertEquals("Camera-1", service.getCameraScreenName(camera));
    }

    @Test
    public void testGetRepairLogColorStringWorking() throws Exception {
        RepairLog repairLog = new RepairLog(1, 1, new Date(1994, 8, 13), "Working", "NA", new Date(1994, 8, 13), new Date(1994, 8, 13));
        assertEquals("green", service.getRepairLogColorString(repairLog));
    }

    @Test
    public void testGetRepairLogColorStringRepairRequested() throws Exception {
        RepairLog repairLog = new RepairLog(1, 1, new Date(1994, 8, 13), "RepairRequested", "NA", new Date(1994, 8, 13), new Date(1994, 8, 13));
        assertEquals("yellow", service.getRepairLogColorString(repairLog));
    }

    @Test
    public void testGetRepairLogColorStringDamaged() throws Exception {
        RepairLog repairLog = new RepairLog(1, 1, new Date(1994, 8, 13), "Damaged", "NA", new Date(1994, 8, 13), new Date(1994, 8, 13));
        assertEquals("red", service.getRepairLogColorString(repairLog));
    }

    @Test
    public void testGetRepairLogColorStringTest() throws Exception {
        RepairLog repairLog = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "NA", new Date(1994, 8, 13), new Date(1994, 8, 13));
        assertEquals("black", service.getRepairLogColorString(repairLog));
    }

    @Test
    public void TestGetActiveRepairs() throws Exception {
        RepairLog repairLog1 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "NA", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog2 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "NA", new Date(1994, 8, 13), new Date(1994, 8, 13));

        List<RepairLog> repairLogList = Arrays.asList(repairLog1, repairLog2);

        PowerMockito.stub(method(RepairLogRequests.class, "getActiveRepairs")).toReturn(repairLogList);

        assertEquals(repairLogList, service.getActiveRepairs());
    }

    @Test
    public void testGetFloorIDFromCameraID() throws Exception {
        Camera camera = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        Floor floor = new Floor(1, 1, 2, "F", "MDA");

        PowerMockito.stub(method(CameraRequests.class, "getCameraById")).toReturn(camera);
        PowerMockito.stub(method(FloorRequests.class, "getFloorById")).toReturn(floor);

        assertEquals(floor.getID(), service.getFloorIDFromCameraID(1));
    }

    @Test
    public void TestGetMostRecentFailZero() throws Exception {
        List<RepairLog> repairLogList = Arrays.asList();
        RepairLog expected = new RepairLog();

        PowerMockito.stub(method(RepairLogRequests.class, "getRepairLogsByCameraId")).toReturn(repairLogList);

        assertEquals(expected.toString(), service.getMostRecentFail(1).toString());
    }

    @Test
    public void TestGetMostRecentFailSuccess() throws Exception {
        RepairLog repairLog1 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "NA", new Date(1994, 8, 13), new Date(1994, 8, 13));

        List<RepairLog> repairLogList = Arrays.asList(repairLog1);

        PowerMockito.stub(method(RepairLogRequests.class, "getRepairLogsByCameraId")).toReturn(repairLogList);

        assertEquals(repairLog1.toString(), service.getMostRecentFail(1).toString());
    }
}