package behaviorics.workingStateImages;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class WorkingStateImagesController {

    @RequestMapping("/workingStateImages/{imagesID}")
    public ResponseEntity<WorkingStateImagesBO> getWorkingStateImagesByID(@PathVariable("imagesID") int id) {

        WorkingStateImagesDAO workingStateImagesDAO = new WorkingStateImagesDAO();
        WorkingStateImagesBO workingStateImagesBO = null;

        try {
            workingStateImagesBO = workingStateImagesDAO.getWorkingStateImagesByID(id);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(workingStateImagesBO, HttpStatus.OK);
    }

    @RequestMapping("/workingStateImages/cameraID/{cameraID}")
    public ResponseEntity<WorkingStateImagesBO> getWorkingStateImagesByCameraID(@PathVariable ("cameraID") int id) {

        WorkingStateImagesDAO workingStateImagesDAO = new WorkingStateImagesDAO();
        WorkingStateImagesBO workingStateImagesBO = null;

        try {
            workingStateImagesBO = workingStateImagesDAO.getWorkingStateImagesByCameraID(id);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(workingStateImagesBO, HttpStatus.OK);
    }

    @RequestMapping(value = "/workingStateImages", method = RequestMethod.POST)
    public ResponseEntity<String> createWorkingStateImages(@RequestBody WorkingStateImagesBO workingStateImagesBO) {

        WorkingStateImagesDAO workingStateImagesDAO = new WorkingStateImagesDAO();

        try {
            workingStateImagesDAO.createWorkingStateImages(workingStateImagesBO);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/workingStateImages/{id}", method = RequestMethod.PUT)
    public ResponseEntity<WorkingStateImagesBO> updateWorkingStateImages(@PathVariable("id") int id, @RequestBody WorkingStateImagesBO workingStateImagesBO) {

        WorkingStateImagesDAO workingStateImagesDAO = new WorkingStateImagesDAO();

        try {
            workingStateImagesDAO.updateWorkingStateImagesById(id, workingStateImagesBO);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<WorkingStateImagesBO>(workingStateImagesBO, HttpStatus.OK);
    }

    @RequestMapping(value = "/workingStateImages/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteWorkingStateImages(@PathVariable("id") int id) {

        WorkingStateImagesDAO workingStateImagesDAO = new WorkingStateImagesDAO();

        try {
            workingStateImagesDAO.deleteWorkingStateImagesById(id);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
