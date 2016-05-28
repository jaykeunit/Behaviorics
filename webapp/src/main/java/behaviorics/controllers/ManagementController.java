package behaviorics.controllers;

import behaviorics.ManagementService;
import behaviorics.httpRequests.CameraRequests;
import behaviorics.httpRequests.RepairLogRequests;
import behaviorics.models.Camera;
import behaviorics.models.RepairLog;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ManagementController {

    ManagementService service = new ManagementService();

    @RequestMapping(value="/manage", method= RequestMethod.GET)
    public String managementPage(Model model) {

        try {
            model.addAttribute("service", service);
            model.addAttribute("originalList", service.getOrganizationToFloorsList());
            return "managementPage";
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("errMsg", e.getMessage());
            return "errorPage";
        }
    }

    @RequestMapping (value="/floorPlan/{floorID}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable final int floorID) {

        try {
            byte[] bytes;
            bytes = service.getImageAsByteArray(floorID);
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/cameras/{floorID}", method = RequestMethod.GET)
    public ResponseEntity<Camera[]> getCameras(Model model, @PathVariable final int floorID) {

        List<Camera> cameras;
        try {
            cameras = service.getCameras(floorID);
            return new ResponseEntity<>(cameras.toArray(new Camera[cameras.size()]), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Camera[0], HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping (value="/workingStateImage/{cameraName}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImage(@PathVariable final String cameraName) {

        try {
            byte[] bytes;
            Camera cam = CameraRequests.getCameraByName(cameraName);
            bytes = service.getWorkingImageAsByteArray(cam.getId());
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping (value="/failImage/{repairID}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getfailImage(@PathVariable final int repairID) {
        try {
            byte[] bytes;
            bytes = service.getMostRecentFailImageByID(repairID);
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getStream/{cameraName}", method = RequestMethod.GET)
    public ResponseEntity<String> getStream(Model model, @PathVariable final String cameraName) {
        try {
            Camera cam = CameraRequests.getCameraByName(cameraName);
            String stream = service.getRTSP_URL(cam.getFeedIP(), cam.getOnvifPort(), cam.getFeedCredential(),cam.getFeedPassword());
            return new ResponseEntity<String>(stream, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<String>("cannot get rtsp stream", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getSnapshot/{cameraName}", method = RequestMethod.GET)
    public ResponseEntity<String> getSnapshot(Model model, @PathVariable final String cameraName) {
        try {
            Camera cam = CameraRequests.getCameraByName(cameraName);
            String stream = service.getSnapshotUrl(cam.getFeedIP(), cam.getOnvifPort(), cam.getFeedCredential(),cam.getFeedPassword());
            return new ResponseEntity<String>(stream, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<String>("cannot get snapshot url", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getDefaultSnapshot/{cameraName}", method = RequestMethod.GET)
    public ResponseEntity<String> getDefaultSnapshot(Model model, @PathVariable final String cameraName) {
        try {
            Camera cam = CameraRequests.getCameraByName(cameraName);
            String stream = service.getDefaultSnapshotURL(cam.getVendorName(),cam.getFeedIP(),cam.getHttpPort(),cam.getFeedCredential(), cam.getFeedPassword());
            return new ResponseEntity<String>(stream, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<String>("cannot get snapshot url", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/repair/{alertid}", method = RequestMethod.GET)
    public ResponseEntity<RepairLog> handleRepairLog(int id, @PathVariable("id") int alertid) {

        RepairLog rl;
        try {
            rl = RepairLogRequests.getRepairLogById(alertid);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<RepairLog>(rl,HttpStatus.OK);
    }

    @RequestMapping(value = "/mostRecentFailDate/{cameraID}", method = RequestMethod.GET)
    public ResponseEntity<RepairLog> mostRecentFailDate(Model model, @PathVariable("cameraID") int cameraID) {
        ManagementService service = new ManagementService();
        try {
            RepairLog log  = service.getMostRecentFail(cameraID);
            return new ResponseEntity<RepairLog>(log,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<RepairLog>(new RepairLog(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/updateStatus/{cameraID}/{newStatus}", method = RequestMethod.GET)
    public ResponseEntity<String> updateStatus(Model model, @PathVariable("cameraID") int cameraID, @PathVariable("newStatus") String newStatus) {
        ManagementService service = new ManagementService();
        try {
            service.updateCameraStatus(cameraID, newStatus);
            return new ResponseEntity<String>("Success",HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<String>("Fail", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getFloorIDfromCameraID/{cameraID}", method = RequestMethod.GET)
    public ResponseEntity<Integer> updateStatus(Model model, @PathVariable("cameraID") int cameraID) {
        ManagementService service = new ManagementService();
        try {
            int floorID = service.getFloorIDFromCameraID(cameraID);
            return new ResponseEntity<Integer>(floorID,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<Integer>(0, HttpStatus.NOT_FOUND);
        }
    }
}


