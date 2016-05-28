package behaviorics.users;

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
public class UserController {

	@RequestMapping("/users/{userName}")
	public ResponseEntity<UserBO> getUserByUserName(@PathVariable("userName") String name) {
		UserDAO userDAO = new UserDAO();
        UserBO userBO = null;
        
        try{
            userBO = userDAO.getUserByUserName(name);
        } catch(Exception e){
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
    	return new ResponseEntity(userBO,HttpStatus.OK);
	}

    @RequestMapping("/users/organizationID/{organizationID}")
    public ResponseEntity<List> getAllUsersByOrganizationID(@PathVariable("organizationID") int organizationID) {
        UserDAO userDAO = new UserDAO();
        List<UserBO> userList;

        try{
            userList = userDAO.getAllUserByOrganizationID(organizationID);
        } catch(Exception e){
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(userList,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody UserBO userBO) {
        UserDAO userDAO = new UserDAO();
        
        try{
            userDAO.createUser(userBO);
        } catch(Exception e){
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserBO> updateUser(@PathVariable("id") int id, @RequestBody UserBO userBO) {
        UserDAO userDAO = new UserDAO();

        try{
            userDAO.updateUserById(id, userBO);
        } catch(Exception e){
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        
        return new ResponseEntity<UserBO>(userBO, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        UserDAO userDAO = new UserDAO();
        
        try{
            userDAO.deleteUserById(id);
        } catch(Exception e){
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        
        return new ResponseEntity(HttpStatus.OK);
    }

}