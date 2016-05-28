package behaviorics.floors;

import behaviorics.building.BuildingDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class FloorController {

    @RequestMapping("/floor/{buildingID}/{floorNumber}")
    public ResponseEntity getFloor(@PathVariable("buildingID") int buildingID, @PathVariable("floorNumber") int floorNumber) {
        FloorDAO floorDAO = new FloorDAO();
        try {
            FloorBO floorBO = floorDAO.getFloor(buildingID, floorNumber);
            return new ResponseEntity(floorBO, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("/floors/id/{id}")
    public ResponseEntity<FloorBO> getFloorById(@PathVariable("id") int id) {
        FloorDAO floorDAO = new FloorDAO();
        try {
            FloorBO floor = floorDAO.getFloorById(id);
            return new ResponseEntity(floor, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/floors/{buildingID}")
    public ResponseEntity<List> getFloors(@PathVariable("buildingID") int buildingID) {
        FloorDAO floorDAO = new FloorDAO();
        try {
            List<FloorBO> floors = floorDAO.getFloorsByBuildingID(buildingID);
            return new ResponseEntity(floors, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("/floors/organizationID/{organizationID}")
    public ResponseEntity<List> getAllFloorsForAnOrganization(@PathVariable("organizationID") int organizationID) {
        FloorDAO floorDAO = new FloorDAO();
        try {
            List<FloorBO> floors = floorDAO.getAllFloorsForAnOrganization(organizationID);
            return new ResponseEntity(floors, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "/floor", method = RequestMethod.POST)
    public ResponseEntity<String> createFloor(@RequestBody FloorBO floorBO) {
        FloorDAO floorDAO = new FloorDAO();
        BuildingDAO buildingDAO = new BuildingDAO();

        try {
            String parentAcronym = buildingDAO.getBuildingById(floorBO.getBuildingID()).getBuildingAcronym();
            floorBO.setNickname(parentAcronym + floorBO.getFloorType() + Integer.toString(floorBO.getFloorNumber()));
            floorDAO.createFloor(floorBO);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/floor/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FloorBO> updateFloor(@PathVariable("id") int id, @RequestBody FloorBO floorBO) {
        FloorDAO floorDAO = new FloorDAO();

        try {
            floorDAO.updateFloorById(id, floorBO);
        } catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(floorBO, HttpStatus.OK);
    }

    @RequestMapping(value = "/floor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteFloor(@PathVariable("id") int id) {
        FloorDAO floorDAO = new FloorDAO();

        try {
            floorDAO.deleteFloorById(id);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
