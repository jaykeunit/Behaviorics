package behaviorics.controllers;

import behaviorics.FailReportsService;
import behaviorics.models.Building;
import behaviorics.models.RepairLog;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FailReportsController {

    FailReportsService service = new FailReportsService();

    @RequestMapping(value="/failreports", method= RequestMethod.GET)
    public String reports(Model model) {

        try {
            model.addAttribute("service", service);
            RepairLog rl;
            //rl.

            return "failReports";
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("errMsg", e.getMessage());
            return "errorPage";
        }
    }

    @RequestMapping(value="/totalWorkingCameras", method= RequestMethod.GET)
    public @ResponseBody String  getTotalWorkingCameras(Model model) throws Exception {
        return "{\"working\":" + service.getTotalWorkingCameras() + "}";
    }

    @RequestMapping(value="/totalFailingCameras", method= RequestMethod.GET)
    public @ResponseBody String getTotalFailingCameras(Model model) throws Exception {
        model.addAttribute("failingCameras", service.getTotalFailingCameras());
        return "{\"failing\":" + service.getTotalFailingCameras() + "}";
    }

    @RequestMapping(value="/entities/names/{organizationalId}", method= RequestMethod.GET)
    public @ResponseBody List<String> getEntityNames(@PathVariable(value="organizationalId") int organizationalId) throws Exception {
        return service.getAllEntityNames(organizationalId);
    }

    @RequestMapping(value="/buildings/names/{organizationalId}", method= RequestMethod.GET)
    public @ResponseBody List<String> getBuildingNames(@PathVariable(value="organizationalId") int organizationalId) throws Exception {
        return service.getAllBuildingNames(organizationalId);
    }

    @RequestMapping(value="/cameras/entity/cameraStatuses/{entityName}", method= RequestMethod.GET)
    public @ResponseBody List<Integer> getWorkingAndFailingCameraCountForEntity(@PathVariable(value="entityName") String entityName, Model model) throws Exception {
        return service.getWorkingAndFailingCameraCountForEntity(entityName);
    }

    @RequestMapping(value="/cameras/building/cameraStatuses/{buildingName}", method= RequestMethod.GET)
    public @ResponseBody List<Integer> getWorkingAndFailingCameraCountForBuilding(@PathVariable(value="buildingName") String buildingName, Model model) throws Exception {
        return service.getWorkingAndFailingCameraCountForBuilding(buildingName);
    }

    @RequestMapping(value="/cameras/failReasonCounts/{orgID}", method= RequestMethod.GET)
    public @ResponseBody
    HashMap<String, Integer> getCameraStatusCategoryCountsForOrgId(@PathVariable(value="orgID") int orgId, Model model) throws Exception {
        return service.getCameraFailCategoryCountsForOrgId(orgId);
    }

    @RequestMapping(value="/cameras/entities/cameraStatuses", method= RequestMethod.GET)
    public @ResponseBody String getCameraStatusesForEntities(@RequestParam("entities") String[] entities) throws Exception {
        List<Map<String,Integer>> results= service.getFailingAndWorkingStatusesForEntityList(entities);

        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writeValueAsString(results);

        return "{\"response\":" + response + "}";
    }

    @RequestMapping(value="/cameras/buildings/cameraStatuses", method= RequestMethod.GET)
    public @ResponseBody String getCameraStatusesForBuildings(@RequestParam("buildings") String[] buildings) throws Exception {
        List<Map<String,Integer>> results= service.getFailingAndWorkingStatusesForBuildingList(buildings);

        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writeValueAsString(results);

        return "{\"response\":" + response + "}";
    }

}
