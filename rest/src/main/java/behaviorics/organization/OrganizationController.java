package behaviorics.organization;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganizationController {

    @RequestMapping("/organization/id/{id}")
    public ResponseEntity<OrganizationBO> getOrganizationById(@PathVariable("id") int id) {
        OrganizationDAO organizationDAO = new OrganizationDAO();
        OrganizationBO organizationBO = null;

        try {
            organizationBO = organizationDAO.getOrganizationById(id);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(organizationBO, HttpStatus.OK);
    }

    @RequestMapping("/organization/{organizationName}")
    public ResponseEntity<OrganizationBO> getOrganizationByName(@PathVariable ("organizationName") String name) {

        OrganizationDAO organizationDAO = new OrganizationDAO();
        OrganizationBO organizationBO = null;

        try {
            organizationBO = organizationDAO.getOrganizationByOrganizationName(name);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(organizationBO, HttpStatus.OK);
    }

    @RequestMapping("/organization")
    public ResponseEntity<List> getAllOrganizations() {

        OrganizationDAO organizationDAO = new OrganizationDAO();
        List<OrganizationBO> organizationBOList;

        try {
            organizationBOList = organizationDAO.getAllOrganizations();
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(organizationBOList, HttpStatus.OK);
    }

    @RequestMapping(value = "/organization", method = RequestMethod.POST)
    public ResponseEntity<String> createOrganization(@RequestBody OrganizationBO organizationBO) {
        OrganizationDAO organizationDAO = new OrganizationDAO();

        try {
            organizationDAO.createOrganization(organizationBO);
        } catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/organization/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OrganizationBO> updateOrganization(@PathVariable("id") int id, @RequestBody OrganizationBO organizationBO) {

        OrganizationDAO organizationDAO = new OrganizationDAO();

        try {
            organizationDAO.updateOrganizationById(id, organizationBO);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<OrganizationBO>(organizationBO, HttpStatus.OK);
    }

    @RequestMapping(value = "/organization/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteOrganization(@PathVariable("id") int id) {

        OrganizationDAO organizationDAO = new OrganizationDAO();

        try {
            organizationDAO.deleteOrganizationById(id);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
