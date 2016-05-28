package behaviorics;

//This page is only for manually creating the failure action of cameras that is to be later provided by the system made by Behaviorics

import behaviorics.httpRequests.CameraRequests;
import behaviorics.httpRequests.FailImagesRequests;
import behaviorics.httpRequests.RepairLogRequests;
import behaviorics.models.Camera;
import behaviorics.models.FailImage;
import behaviorics.models.RepairLog;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class CameraFailService {
    public String setCameraToDown(int cameraID) throws Exception {
        Camera camera = CameraRequests.getCameraById(cameraID);
        camera.setCameraStatus("Down");
        return CameraRequests.updateCamera(camera);
    }

    public String createRepairLogForCamera(int cameraID, String failReason) throws IOException {
        RepairLog log = new RepairLog(0, cameraID, new java.sql.Date(Calendar.getInstance().getTime().getTime()), "Down", failReason, null, null);
        return RepairLogRequests.createRepairLog(log);
    }

    public String createFailImage(int cameraID, byte[] bytes) throws Exception {
        int repairLogID = RepairLogRequests.getRepairLogsByCameraId(cameraID).get(0).getId();
        FailImage image = new FailImage(0, bytes, repairLogID);
        return FailImagesRequests.createFailImage(image);
    }
}
