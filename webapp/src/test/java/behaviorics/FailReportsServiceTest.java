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
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CameraRequests.class, EntityRequests.class, BuildingRequests.class, FloorRequests.class, RepairLogRequests.class, FailReportsService.class})
public class FailReportsServiceTest {
    FailReportsService service;

    @Before
    public void setUp() throws Exception {
        service = new FailReportsService();
    }

    @Test
    public void testGetTotalWorkingCameras() throws Exception {
        Camera camera1 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Damage", 88, 888, 1);

        List<Camera> cameraList = Arrays.asList(camera1 ,camera2);

        PowerMockito.stub(method(CameraRequests.class, "getAllCamerasByOrgId")).toReturn(cameraList);

        assertEquals(1, service.getTotalWorkingCameras());
    }

    @Test
    public void testGetTotalFailingCameras() throws Exception {
        Camera camera1 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Damage", 88, 888, 1);

        List<Camera> cameraList = Arrays.asList(camera1 ,camera2);

        PowerMockito.stub(method(CameraRequests.class, "getAllCamerasByOrgId")).toReturn(cameraList);

        assertEquals(1, service.getTotalFailingCameras());
    }

    @Test
    public void testGetAllEntityNames() throws Exception {
        Entity entity1 = new Entity(1, 1, "TestEntity", "7137432255","TTT");
        Entity entity2 = new Entity(1, 1, "TestEntity", "7137432255","AAA");
        List<Entity> entities = Arrays.asList(entity1 ,entity2);

        PowerMockito.stub(method(EntityRequests.class, "getAllEntitiesByOrganizationID")).toReturn(entities);

        assertEquals(Arrays.asList("TestEntity", "TestEntity"), service.getAllEntityNames(1));
    }

    @Test
    public void testGetAllBuildingNames() throws Exception {
        Building building1 = new Building(1, "TestBuilding1", 10, 1234, "University Drive", "City", 77004, "AAA", "TX");
        Building building2 = new Building(1, "TestBuilding2", 10, 1234, "University Drive", "City", 77004, "BBB", "TX");
        List<Building> buildings = Arrays.asList(building1 ,building2);

        Entity entity = new Entity(1, 1, "TestEntity", "7137432255","AAA");
        List<Entity> entities = Arrays.asList(entity);

        PowerMockito.stub(method(EntityRequests.class, "getAllEntitiesByOrganizationID")).toReturn(entities);
        PowerMockito.stub(method(BuildingRequests.class, "getBuildingsByEntityId")).toReturn(buildings);

        assertEquals(Arrays.asList("TestBuilding1", "TestBuilding2"), service.getAllBuildingNames(1));
    }

    @Test
    public void testGetCamerasForEntity() throws Exception {
        Entity entity = new Entity(1, 1, "TestEntity", "7137432255","TTT");

        PowerMockito.stub(method(EntityRequests.class, "getEntityByEntityName")).toReturn(entity);

        Building building1 = new Building(1, "TestBuilding1", 10, 1234, "University Drive", "City", 77004, "AAA", "TX");
        Building building2 = new Building(1, "TestBuilding2", 10, 1234, "University Drive", "City", 77004, "BBB", "TX");
        List<Building> buildings = Arrays.asList(building1 ,building2);

        PowerMockito.stub(method(BuildingRequests.class, "getBuildingsByEntityId")).toReturn(buildings);

        Floor floor1 = new Floor(1, 1, 2, "F", "MDA");
        Floor floor2 = new Floor(1, 1, 1, "F", "MDA");
        List<Floor> outOfOrderFloors = Arrays.asList(floor1 ,floor2);

        PowerMockito.stub(method(FloorRequests.class, "getFloorsByBuildingId")).toReturn(outOfOrderFloors);

        Camera camera1 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Damage", 88, 888, 1);

        List<Camera> cameraList = Arrays.asList(camera1 ,camera2);

        PowerMockito.stub(method(CameraRequests.class, "getAllCamerasForFloorId")).toReturn(cameraList);

        assertEquals(Arrays.asList(camera1, camera2, camera1, camera2, camera1, camera2, camera1, camera2),
                service.getCamerasForEntity("TestEntity"));
    }

    @Test
    public void testGetCamerasForBuilding() throws Exception {
        Building building = new Building(1, "TestBuilding", 10, 1234, "University Drive", "City", 77004, "BBB", "TX");

        PowerMockito.stub(method(BuildingRequests.class, "getBuildingByBuildingName")).toReturn(building);

        Floor floor1 = new Floor(1, 1, 2, "F", "MDA");
        Floor floor2 = new Floor(1, 1, 1, "F", "MDA");
        List<Floor> outOfOrderFloors = Arrays.asList(floor1 ,floor2);

        PowerMockito.stub(method(FloorRequests.class, "getFloorsByBuildingId")).toReturn(outOfOrderFloors);

        Camera camera1 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Damage", 88, 888, 1);

        List<Camera> cameraList = Arrays.asList(camera1 ,camera2);

        PowerMockito.stub(method(CameraRequests.class, "getAllCamerasForFloorId")).toReturn(cameraList);

        assertEquals(Arrays.asList(camera1, camera2, camera1, camera2), service.getCamerasForBuilding("TestBuilding"));
    }

    @Test
    public void testGetWorkingAndFailingCameraCountForEntity() throws Exception {
        String[] entities = new String[]{"TestBuilding"};

        Entity entity = new Entity(1, 1, "TestEntity", "7137432255","TTT");

        PowerMockito.stub(method(EntityRequests.class, "getEntityByEntityName")).toReturn(entity);

        Building building1 = new Building(1, "TestBuilding1", 10, 1234, "University Drive", "City", 77004, "AAA", "TX");
        Building building2 = new Building(1, "TestBuilding2", 10, 1234, "University Drive", "City", 77004, "BBB", "TX");
        List<Building> buildings = Arrays.asList(building1 ,building2);

        PowerMockito.stub(method(BuildingRequests.class, "getBuildingsByEntityId")).toReturn(buildings);

        Floor floor1 = new Floor(1, 1, 2, "F", "MDA");
        Floor floor2 = new Floor(1, 1, 1, "F", "MDA");
        List<Floor> outOfOrderFloors = Arrays.asList(floor1 ,floor2);

        PowerMockito.stub(method(FloorRequests.class, "getFloorsByBuildingId")).toReturn(outOfOrderFloors);

        Camera camera1 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Damage", 88, 888, 1);

        List<Camera> cameraList = Arrays.asList(camera1 ,camera2);

        PowerMockito.stub(method(CameraRequests.class, "getAllCamerasForFloorId")).toReturn(cameraList);

        HashMap<String, Integer> workingCounts = new HashMap<>();
        HashMap<String, Integer> failingCounts = new HashMap<>();

        workingCounts.put("TestBuilding", 4);
        failingCounts.put("TestBuilding", 4);

        assertEquals(Arrays.asList(workingCounts, failingCounts), service.getFailingAndWorkingStatusesForEntityList(entities));

    }

    @Test
    public void testGetWorkingAndFailingCameraCountForBuilding() throws Exception {
        String[] buildingStr = new String[]{"TestBuilding"};

        Building building = new Building(1, "TestBuilding", 10, 1234, "University Drive", "City", 77004, "BBB", "TX");

        PowerMockito.stub(method(BuildingRequests.class, "getBuildingByBuildingName")).toReturn(building);

        Floor floor1 = new Floor(1, 1, 2, "F", "MDA");
        Floor floor2 = new Floor(1, 1, 1, "F", "MDA");
        List<Floor> outOfOrderFloors = Arrays.asList(floor1 ,floor2);

        PowerMockito.stub(method(FloorRequests.class, "getFloorsByBuildingId")).toReturn(outOfOrderFloors);

        Camera camera1 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Damage", 88, 888, 1);

        List<Camera> cameraList = Arrays.asList(camera1 ,camera2);

        PowerMockito.stub(method(CameraRequests.class, "getAllCamerasForFloorId")).toReturn(cameraList);

        HashMap<String, Integer> workingCounts = new HashMap<>();
        HashMap<String, Integer> failingCounts = new HashMap<>();

        workingCounts.put("TestBuilding", 2);
        failingCounts.put("TestBuilding", 2);

        assertEquals(Arrays.asList(workingCounts, failingCounts), service.getFailingAndWorkingStatusesForBuildingList(buildingStr));
    }

    @Test
    public void testGetCameraStatusCountArray()
    {
        Camera camera1 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Damage", 88, 888, 1);

        List<Camera> cameraList = Arrays.asList(camera1 ,camera2);

        int workingCount = 1;
        int failingCount = 1;

        assertEquals(Arrays.asList(workingCount, failingCount), service.getCameraStatusCountArray(cameraList));

    }

    @Test
    public void testGetWorkingAndFailCameraCountForEntity() throws Exception {
        Entity entity = new Entity(1, 1, "TestEntity", "7137432255","TTT");

        PowerMockito.stub(method(EntityRequests.class, "getEntityByEntityName")).toReturn(entity);

        Building building1 = new Building(1, "TestBuilding1", 10, 1234, "University Drive", "City", 77004, "AAA", "TX");
        Building building2 = new Building(1, "TestBuilding2", 10, 1234, "University Drive", "City", 77004, "BBB", "TX");
        List<Building> buildings = Arrays.asList(building1 ,building2);

        PowerMockito.stub(method(BuildingRequests.class, "getBuildingsByEntityId")).toReturn(buildings);

        Floor floor1 = new Floor(1, 1, 2, "F", "MDA");
        Floor floor2 = new Floor(1, 1, 1, "F", "MDA");
        List<Floor> outOfOrderFloors = Arrays.asList(floor1 ,floor2);

        PowerMockito.stub(method(FloorRequests.class, "getFloorsByBuildingId")).toReturn(outOfOrderFloors);

        Camera camera1 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Damage", 88, 888, 1);

        List<Camera> cameraList = Arrays.asList(camera1 ,camera2);

        PowerMockito.stub(method(CameraRequests.class, "getAllCamerasForFloorId")).toReturn(cameraList);

        assertEquals(Arrays.asList(4, 4),
                service.getWorkingAndFailingCameraCountForEntity("TestEntity"));
    }

    @Test
    public void testGetWorkingAndFailCameraCountForBuilding() throws Exception{
        Building building = new Building(1, "TestBuilding", 10, 1234, "University Drive", "City", 77004, "BBB", "TX");

        PowerMockito.stub(method(BuildingRequests.class, "getBuildingByBuildingName")).toReturn(building);

        Floor floor1 = new Floor(1, 1, 2, "F", "MDA");
        Floor floor2 = new Floor(1, 1, 1, "F", "MDA");
        List<Floor> outOfOrderFloors = Arrays.asList(floor1 ,floor2);

        PowerMockito.stub(method(FloorRequests.class, "getFloorsByBuildingId")).toReturn(outOfOrderFloors);

        Camera camera1 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Damage", 88, 888, 1);

        List<Camera> cameraList = Arrays.asList(camera1 ,camera2);

        PowerMockito.stub(method(CameraRequests.class, "getAllCamerasForFloorId")).toReturn(cameraList);

        assertEquals(Arrays.asList(2, 2), service.getWorkingAndFailingCameraCountForBuilding("TestBuilding"));
    }

    @Test
    public void something() throws Exception{
        Camera camera1 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1);
        Camera camera2 = new Camera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Damage", 88, 888, 1);

        List<Camera> cameraList = Arrays.asList(camera1 ,camera2);

        PowerMockito.stub(method(CameraRequests.class, "getAllCamerasByOrgId")).toReturn(cameraList);

        RepairLog repairLog1 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "Water Damage", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog2 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "Blurred Image", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog3 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "Broken Camera", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog4 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "Bad Batteries", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog5 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "Broken Zoom Lens", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog6 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "Sensor Malfunction", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog7 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "LCD Cracked", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog8 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "Software Issue", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog9 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "Bad Image quality", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog10 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "Lens Obstructed", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog11 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "Night Light Issue", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog12 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "Corrupted Image", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog13 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "Other", new Date(1994, 8, 13), new Date(1994, 8, 13));
        RepairLog repairLog14 = new RepairLog(1, 1, new Date(1994, 8, 13), "test", "Working", new Date(1994, 8, 13), new Date(1994, 8, 13));

        List<RepairLog> repairLogList = Arrays.asList(repairLog1, repairLog2, repairLog3, repairLog4, repairLog5, repairLog6, repairLog7, repairLog8, repairLog9, repairLog10, repairLog11, repairLog12, repairLog13, repairLog14);

        PowerMockito.stub(method(RepairLogRequests.class, "getRepairLogsByCameraId")).toReturn(repairLogList);

        HashMap<String, Integer> failureCounts  = new HashMap<>();

        failureCounts.put("WaterDamage", 2);
        failureCounts.put("BlurredImage", 2);
        failureCounts.put("BrokenCamera", 2);
        failureCounts.put("BadBatteries", 2);
        failureCounts.put("BrokenZoomLens", 2);
        failureCounts.put("SensorMalfunction", 2);
        failureCounts.put("LCDCracked", 2);
        failureCounts.put("SoftwareIssue", 2);
        failureCounts.put("BadImageQuality", 2);
        failureCounts.put("LensObstructed", 2);
        failureCounts.put("NightLightIssue", 2);
        failureCounts.put("CorruptedImage", 2);
        failureCounts.put("Other", 2);

        assertEquals(failureCounts.toString(), service.getCameraFailCategoryCountsForOrgId(1).toString());
    }

}
