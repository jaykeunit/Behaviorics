package behaviorics.controllers;

import behaviorics.ManagementService;
import behaviorics.models.Camera;
import behaviorics.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ReportController {

    ManagementService service = new ManagementService();
    @RequestMapping(value="/reports", method= RequestMethod.GET)
    public String reports(Model model) {
        ManagementService managementService = new ManagementService();
        HomeService homeService = new HomeService();
        try {
            List<Camera> cameraList = managementService.getAllCameras();
            model.addAttribute("service", homeService);
            model.addAttribute("cameraList", cameraList);

            return "reportsPage";
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("errMsg", e.getMessage());
            return "errorPage";
        }
    }
}
