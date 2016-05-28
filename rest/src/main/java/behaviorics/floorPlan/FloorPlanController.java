package behaviorics.floorPlan;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FloorPlanController {

    @RequestMapping("/floorPlan/{floorPlanID}")
    public ResponseEntity<FloorPlanBO> getFloorPlanByID(@PathVariable ("floorPlanID") int id) {

        FloorPlanDAO floorPlanDAO = new FloorPlanDAO();
        FloorPlanBO floorPlanBO = null;

        try {
            floorPlanBO = floorPlanDAO.getFloorPlanByID(id);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(floorPlanBO, HttpStatus.OK);
    }

    @RequestMapping("/floorPlan/floor/{floorID}")
    public ResponseEntity<FloorPlanBO> getFloorPlanByFloorID(@PathVariable ("floorID") int id) {

        FloorPlanDAO floorPlanDAO = new FloorPlanDAO();
        FloorPlanBO floorPlanBO = null;

        try {
            floorPlanBO = floorPlanDAO.getFloorPlanByFloorID(id);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(floorPlanBO, HttpStatus.OK);
    }

    @RequestMapping(value = "/floorPlan", method = RequestMethod.POST)
    public ResponseEntity<String> createFloorPlan(@RequestBody FloorPlanBO floorPlanBO) {

        FloorPlanDAO floorPlanDAO = new FloorPlanDAO();

        try {
            floorPlanDAO.createFloorPlan(floorPlanBO);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/floorPlan/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FloorPlanBO> updateFloorPlan(@PathVariable("id") int id, @RequestBody FloorPlanBO floorPlanBO) {

        FloorPlanDAO floorPlanDAO = new FloorPlanDAO();

        try {
            floorPlanDAO.updateFloorPlanById(id, floorPlanBO);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<FloorPlanBO>(floorPlanBO, HttpStatus.OK);
    }

    @RequestMapping(value = "/floorPlan/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteFloorPlan(@PathVariable("id") int id) {

        FloorPlanDAO floorPlanDAO = new FloorPlanDAO();

        try {
            floorPlanDAO.deleteFloorPlanById(id);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}