package behaviorics.SpecialCaseQueries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class SpecialCaseQueriesController {

    @RequestMapping(value = "/OrganizationToFloorsList", method = RequestMethod.GET)
    public ResponseEntity<List> getEntityById() {
        SpecialCaseQueriesDAO specialCaseQueriesDAO = new SpecialCaseQueriesDAO();

        try {
            List<MonitorCamerasBO> monitorCamerasBO = specialCaseQueriesDAO.getOrganizationToFloorsList();
            return new ResponseEntity(monitorCamerasBO, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/allCameras/organizationID/{organizationID}", method = RequestMethod.GET)
    public ResponseEntity<List> getAllCamerasByOrganizationId(@PathVariable("organizationID") int organizationID) {
        SpecialCaseQueriesDAO specialCaseQueriesDAO = new SpecialCaseQueriesDAO();

        try {
            List<EditCameraBO> editCameraBO = specialCaseQueriesDAO.getAllCamerasById(organizationID);
            return new ResponseEntity(editCameraBO, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/camerasEdit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EditCameraBO> updateCamera(@PathVariable("id") int id, @RequestBody EditCameraBO editCameraBO) {
        SpecialCaseQueriesDAO specialCaseQueriesDAO = new SpecialCaseQueriesDAO();
        try {
            specialCaseQueriesDAO.updateCameraById(id, editCameraBO);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<EditCameraBO>(editCameraBO, HttpStatus.OK);
    }

}