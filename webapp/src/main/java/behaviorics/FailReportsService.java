package behaviorics;

import behaviorics.httpRequests.*;
import behaviorics.models.*;

import java.util.*;

public class FailReportsService {

    public int getTotalWorkingCameras() throws Exception {
        List<Camera> cameras = CameraRequests.getAllCamerasByOrgId(1);

        int workingCameraCount = 0;
        for (Camera item : cameras) {
            if (item.getCameraStatus().equals("Working")) {
                workingCameraCount++;
            }
        }

        return workingCameraCount;
    }

    public int getTotalFailingCameras() throws Exception {
        List<Camera> cameras = CameraRequests.getAllCamerasByOrgId(1);

        int workingCameraCount = 0;
        for (Camera item : cameras) {
            if (item.getCameraStatus().equals("Working")) {
                workingCameraCount++;
            }
        }

        return cameras.size() - workingCameraCount;
    }

    public List<String> getAllEntityNames(int orgId) throws Exception {
        List<Entity> entities = EntityRequests.getAllEntitiesByOrganizationID(orgId);

        ArrayList<String> entityList = new ArrayList<String>();
        for (Entity item : entities)
        {
            entityList.add(item.getEntityName());
        }

        return entityList;
    }

    public List<String> getAllBuildingNames(int orgId) throws Exception {
        List<Entity> entities = EntityRequests.getAllEntitiesByOrganizationID(orgId);
        ArrayList<String> buildingList = new ArrayList<String>();

        for (Entity entity : entities)
        {
            List<Building> buildings = BuildingRequests.getBuildingsByEntityId(entity.getID());
            for (Building building : buildings)
            {
                buildingList.add(building.getBuildingName());
            }
        }
        return buildingList;
    }

    public List<Camera> getCamerasForEntity(String entityName) throws Exception {
        List<Camera> cameras = new ArrayList<>();
        int entityId = EntityRequests.getEntityByEntityName(entityName).getID();

        List<Building> buildings = BuildingRequests.getBuildingsByEntityId(entityId);
        for (Building building : buildings) {
            List<Floor> floors = FloorRequests.getFloorsByBuildingId(building.getId());
            for (Floor floor : floors) {
                cameras.addAll(CameraRequests.getAllCamerasForFloorId(floor.getID()));
            }
        }

        return cameras;
    }

    public List<Camera> getCamerasForBuilding(String buildingName) throws Exception {
        List<Camera> cameras = new ArrayList<>();
        int buildingId = BuildingRequests.getBuildingByBuildingName(buildingName).getId();

        List<Floor> floors = FloorRequests.getFloorsByBuildingId(buildingId);
        for (Floor floor : floors) {
            cameras.addAll(CameraRequests.getAllCamerasForFloorId(floor.getID()));
        }

        return cameras;
    }

    public List<Map<String,Integer>> getFailingAndWorkingStatusesForEntityList(String[] entities) throws Exception {

        List<Map<String,Integer>> statusCountsList = new ArrayList();
        HashMap<String, Integer> workingCounts = new HashMap<>();
        HashMap<String, Integer> failingCounts = new HashMap<>();

        for(int i=0; i<entities.length; i++) {
            List<Camera> cameras = getCamerasForEntity(entities[i]);

            int workingCount = 0;
            int failingCount = 0;
            for(int j=0; j<cameras.size(); j++)
            {
                if(Objects.equals(cameras.get(j).getCameraStatus(), "Working"))
                {
                    workingCount++;
                }
                else
                {
                    failingCount++;
                }
            }

            workingCounts.put(entities[i], workingCount);
            failingCounts.put(entities[i], failingCount);
        }

        statusCountsList.add(workingCounts);
        statusCountsList.add(failingCounts);

        return statusCountsList;
    }

    public List<Map<String,Integer>> getFailingAndWorkingStatusesForBuildingList(String[] buildings) throws Exception {
        List<Map<String,Integer>> statusCountsList = new ArrayList();
        HashMap<String, Integer> workingCounts = new HashMap<>();
        HashMap<String, Integer> failingCounts = new HashMap<>();

        for(int i=0; i<buildings.length; i++) {
            List<Camera> cameras = getCamerasForBuilding(buildings[i]);

            int workingCount = 0;
            int failingCount = 0;
            for(int j=0; j<cameras.size(); j++)
            {
                if(Objects.equals(cameras.get(j).getCameraStatus(), "Working"))
                {
                    workingCount++;
                }
                else
                {
                    failingCount++;
                }
            }

            workingCounts.put(buildings[i], workingCount);
            failingCounts.put(buildings[i], failingCount);
        }

        statusCountsList.add(workingCounts);
        statusCountsList.add(failingCounts);

        return statusCountsList;
    }

    public List<Integer> getWorkingAndFailingCameraCountForEntity(String entityName) throws Exception {

        List<Camera> cameras = getCamerasForEntity(entityName);

        return getCameraStatusCountArray(cameras);
    }

    public List<Integer> getWorkingAndFailingCameraCountForBuilding(String buildingName) throws Exception {
        List<Camera> cameras = getCamerasForBuilding(buildingName);

        return getCameraStatusCountArray(cameras);
    }

    public List<Integer> getCameraStatusCountArray(List<Camera> cameras)
    {
        int workingCameraCount=0;
        int failingCameraCount=0;
        for(int i=0; i<cameras.size(); i++)
        {
            if(cameras.get(i).getCameraStatus().equals("Working"))
            {
                workingCameraCount++;
            }
            else
            {
                failingCameraCount++;
            }
        }

        return Arrays.asList(workingCameraCount, failingCameraCount);
    }

    public HashMap<String, Integer> getCameraFailCategoryCountsForOrgId(int orgId) throws Exception {
        List<Camera> cameras = CameraRequests.getAllCamerasByOrgId(orgId);

        Integer waterDamageCount = 0;
        Integer blurredImageCount = 0;
        Integer brokenCameraCount = 0;
        Integer badBatteriesCount = 0;
        Integer brokenZoomLensCount = 0;
        Integer sensorMalfunctionCount = 0;
        Integer lcdCrackedCount = 0;
        Integer softwareIssueCount = 0;
        Integer badImageQualityCount = 0;
        Integer lensObstructedCount = 0;
        Integer nightLightIssueCount = 0;
        Integer corruptedImageCount = 0;
        Integer otherCount = 0;

        for(int i =0; i < cameras.size(); i++)
        {
            List<RepairLog> logs = RepairLogRequests.getRepairLogsByCameraId(cameras.get(i).getId());
            for(int j=0; j < logs.size(); j++)
            {
                switch (logs.get(j).getFailReason())
                {
                    case "Water Damage":
                    {
                        waterDamageCount++;
                        break;
                    }
                    case "Blurred Image":
                    {
                        blurredImageCount++;
                        break;
                    }
                    case "Broken Camera":
                    {
                        brokenCameraCount++;
                        break;
                    }
                    case "Bad Batteries":
                    {
                        badBatteriesCount++;
                        break;
                    }
                    case "Broken Zoom Lens":
                    {
                        brokenZoomLensCount++;
                        break;
                    }
                    case "Sensor Malfunction":
                    {
                        sensorMalfunctionCount++;
                        break;
                    }
                    case "LCD Cracked":
                    {
                        lcdCrackedCount++;
                        break;
                    }
                    case "Software Issue":
                    {
                        softwareIssueCount++;
                        break;
                    }
                    case "Bad Image quality":
                    {
                        badImageQualityCount++;
                        break;
                    }
                    case "Lens Obstructed":
                    {
                        lensObstructedCount++;
                        break;
                    }
                    case "Night Light Issue":
                    {
                        nightLightIssueCount++;
                        break;
                    }
                    case "Corrupted Image":
                    {
                        corruptedImageCount++;
                        break;
                    }
                    case "Other":
                    {
                        otherCount++;
                        break;
                    }
                }
            }
        }

        HashMap<String, Integer> failureCounts = new HashMap<>();
        failureCounts.put("WaterDamage", waterDamageCount);
        failureCounts.put("BlurredImage", blurredImageCount);
        failureCounts.put("BrokenCamera", brokenCameraCount);
        failureCounts.put("BadBatteries", badBatteriesCount);
        failureCounts.put("BrokenZoomLens", brokenZoomLensCount);
        failureCounts.put("SensorMalfunction", sensorMalfunctionCount);
        failureCounts.put("LCDCracked", lcdCrackedCount);
        failureCounts.put("SoftwareIssue", softwareIssueCount);
        failureCounts.put("BadImageQuality", badImageQualityCount);
        failureCounts.put("LensObstructed", lensObstructedCount);
        failureCounts.put("NightLightIssue", nightLightIssueCount);
        failureCounts.put("CorruptedImage", corruptedImageCount);
        failureCounts.put("Other", otherCount);

        return failureCounts;
    }

}
