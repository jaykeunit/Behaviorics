package behaviorics.failImages;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class FailImagesController {

    @RequestMapping("/failImages/{failImagesID}")
    public ResponseEntity<FailImagesBO> getFailImagesByID(@PathVariable("failImagesID") int id) {

        FailImagesDAO failImagesDAO = new FailImagesDAO();
        FailImagesBO failImagesBO = null;

        try {
            failImagesBO = failImagesDAO.getFailImagesByID(id);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(failImagesBO, HttpStatus.OK);
    }

    @RequestMapping("/failImages/repairID/{repairID}")
    public ResponseEntity<FailImagesBO> getFailImagesIDByRepairID(@PathVariable ("repairID") int id) {

        FailImagesDAO failImagesDAO = new FailImagesDAO();
        FailImagesBO failImagesBO = null;

        try {
            failImagesBO = failImagesDAO.getFailImagesByRepairID(id);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(failImagesBO, HttpStatus.OK);
    }

    @RequestMapping(value = "/failImages", method = RequestMethod.POST)
    public ResponseEntity<String> createFailImages(@RequestBody FailImagesBO failImagesBO) {

        FailImagesDAO failImagesDAO = new FailImagesDAO();

        try {
            failImagesDAO.createFailImages(failImagesBO);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/failImages/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FailImagesBO> updateFailImages(@PathVariable("id") int id, @RequestBody FailImagesBO failImagesBO) {

        FailImagesDAO failImagesDAO = new FailImagesDAO();

        try {
            failImagesDAO.updateFailImagesById(id, failImagesBO);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<FailImagesBO>(failImagesBO, HttpStatus.OK);
    }

    @RequestMapping(value = "/failImages/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteFailImagesBO(@PathVariable("id") int id) {

        FailImagesDAO failImagesDAO = new FailImagesDAO();

        try {
            failImagesDAO.deleteFailImagesById(id);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
