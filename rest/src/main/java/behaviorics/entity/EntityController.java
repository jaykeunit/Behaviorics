package behaviorics.entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntityController {

    @RequestMapping("/entity/id/{id}")
    public ResponseEntity<EntityBO> getEntityById(@PathVariable ("id") int id) {
        EntityDAO entityDAO = new EntityDAO();
        EntityBO entityBO = null;

        try {
            entityBO = entityDAO.getEntityById(id);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(entityBO, HttpStatus.OK);
    }

    @RequestMapping("/entity/{entityName}")
    public ResponseEntity<EntityBO> getEntityByEntityName(@PathVariable ("entityName") String name){
        EntityDAO entityDAO = new EntityDAO();
        EntityBO entityBO = null;

        try{
            entityBO = entityDAO.getEntityByEntityName(name);
        }
        catch(Exception e){
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
            return new ResponseEntity(entityBO, HttpStatus.OK);
    }

    @RequestMapping("/entity/organizationID/{organizationID}")
    public ResponseEntity<List> getAllEntitiesByOrganizationID(@PathVariable("organizationID")int id){
        EntityDAO entityDAO = new EntityDAO();
        List<EntityBO> entityList;

        try{
            entityList = entityDAO.getAllEntitiesByOrganizationID(id);
        }
        catch(Exception e){
            return new ResponseEntity(e.toString(),HttpStatus.UNAUTHORIZED);
        }
            return new ResponseEntity(entityList, HttpStatus.OK);
    }

    @RequestMapping(value = "/entity", method = RequestMethod.POST)
    public ResponseEntity<String> createEntity(@RequestBody EntityBO entityBO){
        EntityDAO entityDAO = new EntityDAO();

        try{
            entityDAO.createEntity(entityBO);
        }
        catch(Exception e){
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
            return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/entity/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EntityBO> updateEntity(@PathVariable("id") int id, @RequestBody EntityBO entityBO){
        EntityDAO entityDAO = new EntityDAO();

        try{
            entityDAO.updateEntityById(id, entityBO);
        }
        catch(Exception e){
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
            return new ResponseEntity<EntityBO>(entityBO, HttpStatus.OK);
    }

    @RequestMapping(value = "/entity/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEntity(@PathVariable("id") int id){
        EntityDAO entityDAO = new EntityDAO();

        try{
            entityDAO.deleteEntityById(id);
        }
        catch(Exception e){
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
            return new ResponseEntity(HttpStatus.OK);
    }

}
