package behaviorics.repairLogs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class RepairLogsController {

    @RequestMapping("/repairLogs/{id}")
    public ResponseEntity<RepairLogsBO> getRepairLogByID(@PathVariable("id") int id) {

        RepairLogsDAO repairLogsDAO = new RepairLogsDAO();
        RepairLogsBO repairLogsBO = null;

        try {
            repairLogsBO = repairLogsDAO.getRepairLogByRepairLogID(id);
        }
        catch(Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(repairLogsBO, HttpStatus.OK);
    }

    @RequestMapping("/repairLogs/cameraID/{cameraID}")
    public ResponseEntity<List> getAllRepairLogsByCameraID(@PathVariable ("cameraID") int id) {

        RepairLogsDAO repairLogsDAO = new RepairLogsDAO();
        List<RepairLogsBO> repairLogsBOList;

        try {
            repairLogsBOList = repairLogsDAO.getAllRepairLogsByCameraID(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(repairLogsBOList, HttpStatus.OK);
    }

    @RequestMapping("/repairLogs/activeRepairs")
    public ResponseEntity<List> getActiveRepairs() {
        RepairLogsDAO repairLogsDAO = new RepairLogsDAO();
        List<RepairLogsBO> repairLogsBOList;
        try {
            repairLogsBOList = repairLogsDAO.getActiveRepairs();
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(repairLogsBOList, HttpStatus.OK);
    }

    @RequestMapping(value = "/repairLogs", method = RequestMethod.POST)
    public ResponseEntity<String> createRepairLog(@RequestBody RepairLogsBO repairLogsBO) {

        RepairLogsDAO repairLogsDAO = new RepairLogsDAO();

        try {
            repairLogsDAO.createRepairLog(repairLogsBO);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/repairLogs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RepairLogsBO> updateRepairLogById(@PathVariable("id") int id, @RequestBody RepairLogsBO repairLogsBO) {

        RepairLogsDAO repairLogsDAO = new RepairLogsDAO();

        try {
            repairLogsDAO.updateRepairLogById(id, repairLogsBO);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<RepairLogsBO>(repairLogsBO, HttpStatus.OK);
    }

    @RequestMapping(value = "/repairLogs/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteRepairLog(@PathVariable("id") int id) {

        RepairLogsDAO repairLogsDAO = new RepairLogsDAO();

        try {
            repairLogsDAO.deleteRepairLogById(id);
        }
        catch (Exception e) {
            return new ResponseEntity(e.toString(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
