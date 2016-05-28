package behaviorics.states;

import java.sql.SQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StatesController {

    @RequestMapping("/states/{stateName}")
    public ResponseEntity<StatesBO> getStateByStateName(@PathVariable("stateName") String name ){
        StatesDAO statesDAO = new StatesDAO();

        try {
            StatesBO statesBO = statesDAO.getStateByStateName(name);
            return new ResponseEntity(statesBO, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("error: " + e);
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping("/states/acr/{stateAbbr}")
    public ResponseEntity<StatesBO> getStateByStateAcronym(@PathVariable("stateAbbr") String acro ){
        StatesDAO statesDAO = new StatesDAO();

        try {
            StatesBO statesBO = statesDAO.getStateByStateAcronym(acro);
            return new ResponseEntity(statesBO, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("error: " + e);
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }

    }

    @RequestMapping(value = "/states", method = RequestMethod.GET)
    public ResponseEntity<List> getAllStates() {
        StatesDAO statesDAO = new StatesDAO();

        try {
            List<StatesBO> allstates = statesDAO.getAllStates();
            return new ResponseEntity(allstates, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("error: " + e);
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
    }
}
