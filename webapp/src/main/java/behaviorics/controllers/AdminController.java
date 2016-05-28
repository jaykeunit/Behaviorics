package behaviorics.controllers;

import behaviorics.AdminService;
import behaviorics.ManagementService;
import behaviorics.httpRequests.*;
import behaviorics.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;

@Controller
public class AdminController {

    @RequestMapping(value="/admin/addentity", method= RequestMethod.GET)
    public String addEntity(Model model) throws Exception {
        AdminService service = new AdminService();

        try {
            List<Entity> allEntities = service.getEntities();
            model.addAttribute("service", service);
            model.addAttribute("entity", new Entity());
            model.addAttribute("allEntities", allEntities);
            return "addentity";
        }
        catch(Exception e) {
            return "errorPage";
        }
    }

    @RequestMapping(value="/admin/addentity", method=RequestMethod.POST)
    public RedirectView formSubmit(@ModelAttribute Entity entity, Model model, RedirectAttributes redirectAttributes) throws Exception {
        try {
            entity.setOrganizationID(1);
            EntityRequests.createEntity(entity);
            model.addAttribute("floor", entity);
            redirectAttributes.addFlashAttribute("message", "Entity was successfully added");
            redirectAttributes.addFlashAttribute("style", "color:green;");
        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error: Entity was not added");
            redirectAttributes.addFlashAttribute("style", "color:red;");
        }
        return new RedirectView("/admin/addentity");
    }

    @RequestMapping(value="/admin/deleteEntity", method = RequestMethod.GET)
    public RedirectView removeEntity(@RequestParam int id, RedirectAttributes redirectAttributes) throws Exception {
        try {
            EntityRequests.deleteEntityById(id);
            redirectAttributes.addFlashAttribute("message", "Entity was successfully removed");
            redirectAttributes.addFlashAttribute("style", "color:green;");
        }
        catch (Exception ex)
        {
            redirectAttributes.addFlashAttribute("message", "Error: Entity was not removed");
            redirectAttributes.addFlashAttribute("style", "color:red;");
        }
        return new RedirectView("/admin/addentity");
    }

    @RequestMapping(value="/admin/editEntity", method=RequestMethod.POST)
    public String formSubmit(@ModelAttribute Entity entity, RedirectAttributes redirectAttributes) throws Exception {
        try {
            entity.setOrganizationID(1);
            EntityRequests.updateEntityById(entity);
        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error: Entity was not updated");
            redirectAttributes.addFlashAttribute("style", "color:red;");
        }
        return "redirect:addentity.html";
    }

    @RequestMapping(value="/admin/addbuilding", method= RequestMethod.GET)
    public String addBuilding(Model model) throws Exception {
        AdminService service = new AdminService();

        try {
            List<Building> allBuildings = service.getAllBuildingsByOrganizationID();
            model.addAttribute("service", service);
            model.addAttribute("building", new Building());
            model.addAttribute("allBuildings", allBuildings);
            return "addbuilding";
        }
        catch(Exception e) {
            return "errorPage";
        }
    }

    @RequestMapping(value="/admin/addbuilding", method=RequestMethod.POST)
    public RedirectView formSubmit(@ModelAttribute Building building, Model model, RedirectAttributes redirectAttributes) throws Exception {
        try {
            BuildingRequests.createBuilding(building);
            model.addAttribute("building", building);
            redirectAttributes.addFlashAttribute("message", "Building was successfully added");
            redirectAttributes.addFlashAttribute("style", "color:green;");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error: Building was not added");
            redirectAttributes.addFlashAttribute("style", "color:red;");
        }
        return new RedirectView("/admin/addbuilding");
    }


    @RequestMapping(value="/admin/deleteBuilding", method = RequestMethod.GET)
    public RedirectView removeBuilding(@RequestParam int id, RedirectAttributes redirectAttributes) throws Exception {
        try {
            BuildingRequests.deleteBuildingById(id);
            redirectAttributes.addFlashAttribute("message", "Building was successfully removed");
            redirectAttributes.addFlashAttribute("style", "color:green;");
        }
        catch (Exception ex)
        {
            redirectAttributes.addFlashAttribute("message", "Error: Building was not removed");
            redirectAttributes.addFlashAttribute("style", "color:red;");
        }
        return new RedirectView("/admin/addbuilding");
    }

    @RequestMapping(value="/admin/editBuilding", method=RequestMethod.POST)
    public String formSubmit(@ModelAttribute Building building, RedirectAttributes redirectAttributes) throws Exception {
        try {
            BuildingRequests.updateBuilding(building);
        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error: Building was not updated");
            redirectAttributes.addFlashAttribute("style", "color:red;");
        }
        return "redirect:addbuilding.html";
    }

    @RequestMapping(value="/admin/addfloor", method= RequestMethod.GET)
    public String addFloor(Model model) throws Exception {
        AdminService service = new AdminService();

        try {
            List<Floor> allFloors = service.getAllFloorsForAnOrganization();
            List<Building> allBuildings = service.getAllBuildingsByOrganizationID();
            model.addAttribute("allBuildings", allBuildings);
            model.addAttribute("floor", new Floor());
            model.addAttribute("allFloors", allFloors);
            return "addfloor";
        }
        catch(Exception e) {
            return "errorPage";
        }
    }

    @RequestMapping(value="/admin/addfloor", method=RequestMethod.POST)
    public RedirectView formSubmit(@ModelAttribute Floor floor, Model model,
                                   @RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) throws Exception {
        AdminService service = new AdminService();
        try {
            if (!file.getContentType().contains("png") & !file.getContentType().contains("jpeg") && !file.getContentType().contains("pdf")) {
                redirectAttributes.addFlashAttribute("message", "Error: Incorrect File type, use JPG, PNG or PDF");
                redirectAttributes.addFlashAttribute("style", "color:red;");
                return new RedirectView("/admin/addfloor");
            }
            FloorRequests.createFloor(floor);
            floor = FloorRequests.getFloor(floor.getBuildingID(), floor.getFloorNumber());
            FloorPlan floorplan = new FloorPlan();
            if(file.getContentType().contains("pdf")) {
                 floorplan.setImage(service.convertPDF(service.multipartToFile(file)));
            }
            else {
                floorplan.setImage(file.getBytes());
            }
            floorplan.setFloorId(floor.getID());
            FloorPlanRequests.createFloorPlan(floorplan);
            model.addAttribute("floor", floor);
            redirectAttributes.addFlashAttribute("message", "Floor was successfully added");
            redirectAttributes.addFlashAttribute("style", "color:green;");
        }
        catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Error: Floor was not added");
            redirectAttributes.addFlashAttribute("style", "color:red;");
        }
        return new RedirectView("/admin/addfloor");
    }

    @RequestMapping(value="/admin/deleteFloor", method = RequestMethod.GET)
    public RedirectView removeFloor(@RequestParam int id, RedirectAttributes redirectAttributes) throws Exception {
        try {
            FloorRequests.deleteFloorById(id);
            redirectAttributes.addFlashAttribute("message", "Floor was successfully removed");
            redirectAttributes.addFlashAttribute("style", "color:green;");
        }
        catch (Exception e)
        {
            redirectAttributes.addFlashAttribute("message", "Error: Floor was not removed");
            redirectAttributes.addFlashAttribute("style", "color:red;");
        }
        return new RedirectView("/admin/addfloor");
    }

    @RequestMapping(value="/admin/editFloor", method=RequestMethod.POST)
    public String formSubmit(@ModelAttribute Floor floor, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws Exception {
        AdminService service = new AdminService();

        try {
            if (!file.getContentType().contains("png") & !file.getContentType().contains("jpeg") && !file.getContentType().contains("pdf")) {

                if(file.isEmpty()){
                    FloorRequests.updateFloorById(floor);
                }
                else {
                    redirectAttributes.addFlashAttribute("message", "Error: Incorrect File type, use JPG, PNG or PDF");
                    redirectAttributes.addFlashAttribute("style", "color:red;");
                }
                return "redirect:addfloor.html";
            }

            FloorPlan floorplan;
            floorplan = FloorPlanRequests.getFloorPlanByFloorId(floor.getID());
            if(file.getContentType().contains("pdf")) {
                floorplan.setImage(service.convertPDF(service.multipartToFile(file)));
            }
            else {
                floorplan.setImage(file.getBytes());
            }

            FloorPlanRequests.updateFloorPlan(floorplan);
            FloorRequests.updateFloorById(floor);
        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error: Floor was not updated");
            redirectAttributes.addFlashAttribute("style", "color:red;");
        }
        return "redirect:addfloor.html";
    }

    @RequestMapping(value="/admin/addUser", method= RequestMethod.GET)
    public String addUser(Model model) throws Exception {
        AdminService service = new AdminService();
        try {
            model.addAttribute("user", new User());
            model.addAttribute("allUsers", service.getAllUsersByOrganizationID());
            return "addUser";
        }
        catch(Exception e) {
            return "errorPage";
        }
    }

    @RequestMapping(value="/admin/addUser", method=RequestMethod.POST)
    public RedirectView formSubmit(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) throws Exception {
        AdminService service = new AdminService();
        try {
            model.addAttribute("user", new User());
            user.setOrganizationID(1);
            User completedUser = service.getCompletedUser(user);
            completedUser.setOrganizationID(1);
            UserRequests.createUser(completedUser);
            redirectAttributes.addFlashAttribute("message", "User was successfully added");
            redirectAttributes.addFlashAttribute("style", "color:green;");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error: User was not added");
            redirectAttributes.addFlashAttribute("style", "color:red;");
        }
        return new RedirectView("/admin/addUser");
    }

    @RequestMapping(value="/admin/deleteUser", method = RequestMethod.GET)
    public RedirectView removeUser(@RequestParam int id, RedirectAttributes redirectAttributes) throws Exception {
        try {
            UserRequests.deleteUserById(id);
            redirectAttributes.addFlashAttribute("message", "User was successfully removed");
            redirectAttributes.addFlashAttribute("style", "color:green;");
        }
        catch (Exception e)
        {
            redirectAttributes.addFlashAttribute("message", "Error: User was not removed");
            redirectAttributes.addFlashAttribute("style", "color:red;");
        }
        return new RedirectView("/admin/addUser");
    }

    @RequestMapping(value="/admin/editUser", method=RequestMethod.POST)
    public String formSubmit(@ModelAttribute User user, RedirectAttributes redirectAttributes) throws Exception {
        try {
            UserRequests.updateUserById(user);
        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error: User was not updated");
            redirectAttributes.addFlashAttribute("style", "color:red;");
        }
        return "redirect:addUser.html";
    }

    @RequestMapping(value="/admin/addcamera", method= RequestMethod.GET)
    public String addCamera(Model model) {
        ManagementService managementService = new ManagementService();
        AdminService allCameras = new AdminService();
        try {
            model.addAttribute("camera", new Camera());
            model.addAttribute("editCamera", new EditCamera());
            model.addAttribute("allCameras", allCameras.getAllCamerasForEditPage());
            model.addAttribute("list", managementService.getOrganizationToFloorsList());
            return "addcamera";
        }
        catch(Exception e) {
            return "errorPage";
        }
    }

    @RequestMapping(value="/admin/addcamera", method=RequestMethod.POST)
    public RedirectView formSubmit(@ModelAttribute Camera camera, Model model, RedirectAttributes redirectAttributes) throws Exception {
        try {
            CameraRequests.createCamera(camera);
            model.addAttribute("cameras", new Camera());
            redirectAttributes.addFlashAttribute("message", "Camera was successfully added");
            redirectAttributes.addFlashAttribute("style", "color:green;");
        }
        catch (Exception e)
        {
            redirectAttributes.addFlashAttribute("message", "Error: Camera was not added");
            redirectAttributes.addFlashAttribute("style", "color:red;");
        }
        return new RedirectView("/admin/addcamera");
    }

    @RequestMapping(value="/admin/deleteCamera", method = RequestMethod.GET)
    public String removeCamera(@RequestParam int id, RedirectAttributes redirectAttributes) throws Exception {
        try {
            CameraRequests.deleteCameraById(id);
            redirectAttributes.addFlashAttribute("message", "Camera was successfully removed");
            redirectAttributes.addFlashAttribute("style", "color:green;");
        }
        catch (Exception e)
        {
            redirectAttributes.addFlashAttribute("message", "Error: Camera was not removed");
            redirectAttributes.addFlashAttribute("style", "color:red;");
        }
        return "redirect:addcamera.html";
    }

    @RequestMapping(value="/admin/editCamera", method=RequestMethod.POST)
    public String formSubmit(@ModelAttribute EditCamera editCamera, RedirectAttributes redirectAttributes) throws Exception {
        try {
            CameraRequests.updateCameraForEdit(editCamera);
        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error: Camera was not updated");
            redirectAttributes.addFlashAttribute("style", "color:red;");
        }
        return "redirect:addcamera.html";
    }
}