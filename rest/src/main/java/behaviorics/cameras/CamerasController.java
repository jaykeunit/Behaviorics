package behaviorics.cameras;

import behaviorics.floors.FloorDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CamerasController {

    @RequestMapping("/cameras/{cameraName}")
    public ResponseEntity<CamerasBO> getCameraByCameraName(@PathVariable("cameraName") String name) {

        CamerasDAO camerasDAO = new CamerasDAO();
        CamerasBO camerasBO = null;

        try {
            camerasBO = camerasDAO.getCameraByCameraName(name);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(camerasBO, HttpStatus.OK);
    }

    @RequestMapping("/cameras/all")
    public ResponseEntity<List> getAllCameras() {

        CamerasDAO camerasDAO = new CamerasDAO();
        List<CamerasBO> camerasBOList;

        try {
            camerasBOList = camerasDAO.getAllCameras();
        }
        catch (Exception e) {

            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(camerasBOList, HttpStatus.OK);
    }

    @RequestMapping("/cameras/orgID/{orgID}")
    public ResponseEntity<List> getAllCamerasByOrgId(@PathVariable ("orgID") int orgId) {

        CamerasDAO camerasDAO = new CamerasDAO();
        List<CamerasBO> camerasBOList;

        try {
            camerasBOList = camerasDAO.getAllCamerasByOrgId(orgId);
        }
        catch (Exception e) {

            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(camerasBOList, HttpStatus.OK);
    }

    @RequestMapping("/cameras/floorID/{floorID}")
    public ResponseEntity<List> getAllCamerasByFloorID(@PathVariable ("floorID") int id) {

        CamerasDAO camerasDAO = new CamerasDAO();
        List<CamerasBO> camerasBOList;

        try {
            camerasBOList = camerasDAO.getAllCamerasByFloorID(id);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(camerasBOList, HttpStatus.OK);
    }

    @RequestMapping(value = "/cameras", method = RequestMethod.POST)
    public ResponseEntity<String> createCamera(@RequestBody CamerasBO camerasBO) {
        FloorDAO floorDAO = new FloorDAO();
        CamerasDAO camerasDAO = new CamerasDAO();

        try {
            String parentAcronym = floorDAO.getFloorById(camerasBO.getFloorID()).getNickname();
            int count = camerasDAO.getCameraCountByFloor(camerasBO.getFloorID());
            camerasBO.setCameraName(parentAcronym + '-' + Integer.toString(count));
            camerasDAO.createCamera(camerasBO);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cameras/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CamerasBO> updateCamera(@PathVariable("id") int id, @RequestBody CamerasBO camerasBO) {
        CamerasDAO camerasDAO = new CamerasDAO();
        try {
            camerasDAO.updateCameraById(id, camerasBO);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<CamerasBO>(camerasBO, HttpStatus.OK);
    }

    @RequestMapping(value = "/cameras/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteCamera(@PathVariable("id") int id) {

        CamerasDAO camerasDAO = new CamerasDAO();

        try {
            camerasDAO.deleteCameraById(id);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/camera/{id}", method = RequestMethod.GET)
    public ResponseEntity<CamerasBO> getCameraById(@PathVariable("id") int id) {
        CamerasDAO camerasDAO = new CamerasDAO();
        try {
            CamerasBO cam = camerasDAO.getCameraByCameraId(id);
            return new ResponseEntity(cam, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
