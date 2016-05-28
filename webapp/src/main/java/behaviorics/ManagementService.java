package behaviorics;

import behaviorics.httpRequests.*;
import behaviorics.models.*;
import de.onvif.soap.OnvifDevice;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.onvif.ver10.schema.Profile;
import javax.xml.soap.SOAPException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.function.Predicate;

public class ManagementService {

    public List<Building> getBuildings(int entityID) throws Exception {
        return BuildingRequests.getBuildingsByEntityId(entityID);
    }

    public List<Entity> getEntities(int organizationID) throws Exception {
        return EntityRequests.getAllEntitiesByOrganizationID(organizationID);
    }

    public List<Floor> getFloors(int buildingNumber) throws Exception {
        List<Floor> floors = FloorRequests.getFloorsByBuildingId(buildingNumber);

        //Cobertura Coverage Reporter flags this as an error if you use lambda instead of Anonymous Class, Change this when/if we find a fix
        Collections.sort(floors, new Comparator<Floor>() {public int compare(Floor a, Floor b) { return a.getFloorNumber() - b.getFloorNumber();}});

        return floors;
    }

    public byte[] getImageAsByteArray(int floorID) throws Exception {
        return FloorPlanRequests.getFloorPlanByFloorId(floorID).getImage();
    }

    public byte[] getWorkingImageAsByteArray(int id) throws Exception {
        return WorkingStateImageRequests.getWorkingStateImagesForCameraId(id).getImage();
    }

    public byte[] getMostRecentFailImageByID(int id) throws Exception {
        return FailImagesRequests.getFailImageIdByRepairId(id).getImage();
    }

    public List<Camera> getCameras(int floorID) throws Exception {
        return CameraRequests.getAllCamerasForFloorId(floorID);
    }

    public List<MonitorCameras> getOrganizationToFloorsList() throws Exception{
        return MonitorCamerasPageRequest.getOrganizationToFloorsList();
    }

    public List<String> getUniqueEntityName(List<MonitorCameras> list) {
        List<String> filteredList = new ArrayList<>();
        for(MonitorCameras entityElement : list){
            if(!filteredList.contains(entityElement.getEntityName())){
                filteredList.add(entityElement.getEntityName());
            }
        }
        return filteredList;
    }

    public List<String> getUniqueBuildingName(List<MonitorCameras> list) {
        List<String> filteredList = new ArrayList<>();
        for(MonitorCameras buildingElement : list){
            if(!filteredList.contains(buildingElement.getBuildingName())){
                filteredList.add(buildingElement.getBuildingName());
            }
        }
        return filteredList;
    }

    public List<MonitorCameras> getBuildingsByEntityName(String entityName, List<MonitorCameras> originalList) {
        List<MonitorCameras> newList = new ArrayList<>(originalList);

        //Cobertura Coverage Reporter flags this as an error if you use lambda instead of Anonymous Class, Change this when/if we find a fix
        newList.removeIf(new Predicate<MonitorCameras>() {
            @Override
            public boolean test(MonitorCameras monitorCameras) {
                return !Objects.equals(monitorCameras.getEntityName(), entityName);
            }
        });
        return newList;
    }

    public List<MonitorCameras> getFloorsByBuildingName(String buildingName, List<MonitorCameras> originalList) {
        List<MonitorCameras> newList = new ArrayList<>(originalList);

        //Cobertura Coverage Reporter flags this as an error if you use lambda instead of Anonymous Class, Change this when/if we find a fix
        newList.removeIf(new Predicate<MonitorCameras>() {
            @Override
            public boolean test(MonitorCameras monitorCameras) {
                return !Objects.equals(monitorCameras.getBuildingName(), buildingName);
            }
        });

        //Cobertura Coverage Reporter flags this as an error if you use lambda instead of Anonymous Class, Change this when/if we find a fix
        newList.sort(new Comparator<MonitorCameras>() {
            @Override
            public int compare(MonitorCameras o1, MonitorCameras o2) {
                return o1.getFloorNumber() < o2.getFloorNumber() ? -1
                        : o1.getFloorNumber() > o2.getFloorNumber() ? 1
                        : 0;
            }
        });

        return newList;
    }

    public List<Camera> getAllCameras() throws Exception {
        return CameraRequests.getAllCameras();
    }

    public String getRTSP_URL(String address, int port, String username, String password) throws SOAPException, ConnectException {
        OnvifDevice cam = new OnvifDevice(address + ":"+ port, username, password);
        System.out.println("Calling camera at:" + address + ":"+ port);
        List<Profile> profiles = cam.getDevices().getProfiles();
        return cam.getMedia().getRTSPStreamUri(profiles.get(0).getToken());
    }

    public String getSnapshotUrl(String address, int port, String username, String password) throws SOAPException, ConnectException {
        OnvifDevice cam = new OnvifDevice(address + ":" + port, username, password);
        System.out.println("Calling camera at:" + address + ":"+ port);
        List<Profile> profiles = cam.getDevices().getProfiles();
        return cam.getMedia().getSceenshotUri(profiles.get(0).getToken());
    }

    public String getDefaultSnapshotURL(String vendorName, String feedIP, int httpPort, String feedCredential, String feedPassword) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("DefaultSnapshotURLs.json"));
        JSONObject jsonOBject = (JSONObject) obj;
        String url =  (String) jsonOBject.get(vendorName);
        if(url == null)
            return "cannot get snapshot url";
        else{
            url = url.replace("[IPADDRESS]", feedIP + ":" + httpPort);
            url = url.replace("[USERNAME]", feedCredential);
            url = url.replace("[PASSWORD]", feedPassword);
        }
        return url;
    }

    public String getCameraScreenName(Camera cam){
        return ((cam.getCameraName().length() > 0)? (cam.getCameraName()+"-"+cam.getId()):("Camera-"+cam.getId()));
    }

    public String getRepairLogColorString(RepairLog rl){

        if (rl.getRepairStatus().equals("Working")) {
            return "green";
        } else if (rl.getRepairStatus().equals("RepairRequested")) {
            return "yellow";
        } else if (rl.getRepairStatus().equals("Damaged")) {
            return "red";
        } else {
            return "black";
        }
    }

    public List<RepairLog> getActiveRepairs() throws Exception {

        return RepairLogRequests.getActiveRepairs();
    }

    public int getFloorIDFromCameraID(int id) throws Exception {
        Camera cam = CameraRequests.getCameraById(id);
        Floor floor =  FloorRequests.getFloorById(cam.getFloorID());
        return floor.getID();
    }

    public RepairLog getMostRecentFail(int cameraId) throws Exception {
        List<RepairLog> repairLogs = RepairLogRequests.getRepairLogsByCameraId(cameraId);
        if(repairLogs.size() == 0){
            RepairLog log = new RepairLog();
            return new RepairLog();
        }
        else
            return repairLogs.get(0);

    }


    public void updateCameraStatus(int cameraID, String newStatus) throws Exception {
        Camera cam = CameraRequests.getCameraById(cameraID);
        cam.setCameraStatus(newStatus);
        CameraRequests.updateCamera(cam);
        updateRepairLog(cameraID, newStatus);
    }

    private void updateRepairLog(int cameraID, String newStatus) throws Exception {
        RepairLog log = RepairLogRequests.getRepairLogsByCameraId(cameraID).get(0);
        log.setRepairStatus(newStatus);
        if(newStatus.equals("Working")) {
            log.setDateRepaired(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        }
        else if(newStatus.equals("UnderRepair")) {
            log.setRepairRequestDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        }
        RepairLogRequests.updateRepairLog(log);
    }
}
