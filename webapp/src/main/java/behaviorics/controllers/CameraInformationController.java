package behaviorics.controllers;

import behaviorics.CameraInformationService;
import behaviorics.models.RepairLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CameraInformationController {

    CameraInformationService service = new CameraInformationService();
    @RequestMapping(value="/cameraInformation", method= RequestMethod.GET)
    public String reports(Model model) {

        try {
            model.addAttribute("service", service);
            RepairLog rl;
            //rl.

            return "cameraInformation";
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("errMsg", e.getMessage());
            return "errorPage";
        }

    }



}
