package behaviorics.building;

import java.sql.SQLException;
import java.util.List;

import behaviorics.entity.EntityDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingController {

    @RequestMapping("/building/id/{id}")
    public ResponseEntity<BuildingBO> getBuildingById(@PathVariable ("id") int id) {
        BuildingDAO buildingDAO = new BuildingDAO();
        BuildingBO buildingBO = null;

        try {
            buildingBO = buildingDAO.getBuildingById(id);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(buildingBO, HttpStatus.OK);
    }

    @RequestMapping("/building/{buildingName}")
    public ResponseEntity<BuildingBO> getBuildingByBuildingName(@PathVariable ("buildingName") String name) {

        BuildingDAO buildingDAO = new BuildingDAO();
        BuildingBO buildingBO = null;

        try {
            buildingBO = buildingDAO.getBuildingByBuildingName(name);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(buildingBO, HttpStatus.OK);
    }

    @RequestMapping("/building/entityID/{entityID}")
    public ResponseEntity<List> getAllBuildingsByEntityID(@PathVariable ("entityID") int id) {

        BuildingDAO buildingDAO = new BuildingDAO();
        List<BuildingBO> buildingList;

        try {
            buildingList = buildingDAO.getAllBuildingsByEntityID(id);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(buildingList, HttpStatus.OK);
    }

    @RequestMapping("/building/organizationID/{organizationID}")
    public ResponseEntity<List> getAllBuildingsByOrganizationID(@PathVariable ("organizationID") int organizationID) {

        BuildingDAO buildingDAO = new BuildingDAO();
        List<BuildingBO> buildingList;

        try {
            buildingList = buildingDAO.getAllBuildingsByOrganizationID(organizationID);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(buildingList, HttpStatus.OK);
    }

    @RequestMapping(value = "/building", method = RequestMethod.POST)
    public ResponseEntity<String> createBuilding(@RequestBody BuildingBO buildingBO) {
        BuildingDAO buildingDAO = new BuildingDAO();
        EntityDAO entityDAO = new EntityDAO();

        try {
            String parentAcronym = entityDAO.getEntityById(buildingBO.getEntityID()).getEntityAcronym();
            buildingBO.setBuildingAcronym(parentAcronym + '-' + buildingBO.getBuildingAcronym());
            buildingDAO.createBuilding(buildingBO);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/building/{id}", method = RequestMethod.PUT)
    public ResponseEntity<BuildingBO> updateBuilding(@PathVariable("id") int id, @RequestBody BuildingBO buildingBO) {

        BuildingDAO buildingDAO = new BuildingDAO();

        try {
            buildingDAO.updateBuildingById(id, buildingBO);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<BuildingBO>(buildingBO, HttpStatus.OK);
    }

    @RequestMapping(value = "/building/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBuilding(@PathVariable("id") int id) {

        BuildingDAO buildingDAO = new BuildingDAO();

        try {
            buildingDAO.deleteBuildingById(id);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}