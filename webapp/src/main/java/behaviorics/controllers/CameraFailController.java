package behaviorics.controllers;

import behaviorics.CameraFailService;
import behaviorics.httpRequests.CameraRequests;
import behaviorics.httpRequests.EntityRequests;
import behaviorics.models.Camera;
import behaviorics.models.Entity;
import behaviorics.models.RepairRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

//This page is only for manually creating the failure action of cameras that is to be later provided by the system made by Behaviorics

@Controller
public class CameraFailController {
    @RequestMapping(value="/PresentationCameraFail", method= RequestMethod.GET)
    public String failPage(Model model) {
        CameraFailService camerafailService = new CameraFailService();
        try {
            model.addAttribute("service", camerafailService);
            model.addAttribute("repairRequest", new RepairRequest());
            return "presentationCameraFailPage";
        }
        catch(Exception e) {
            model.addAttribute("errMsg", e.getMessage());
            return "errorPage";
        }
    }

    @RequestMapping(value="/PresentationCameraFail", method=RequestMethod.POST)
    public RedirectView formSubmit(@ModelAttribute RepairRequest repairRequest, RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file) throws Exception {
        CameraFailService cameraFailService = new CameraFailService();
        try {
            cameraFailService.setCameraToDown(repairRequest.getCameraID());
            cameraFailService.createRepairLogForCamera(repairRequest.getCameraID(), repairRequest.getFailReason());
            cameraFailService.createFailImage(repairRequest.getCameraID(), file.getBytes());
            redirectAttributes.addFlashAttribute("message", "Camera was successfully set to 'Down' and a repair log was created");
        }
        catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Error: Could not create down camera");
        }
        return new RedirectView("/PresentationCameraFail");
    }
}
