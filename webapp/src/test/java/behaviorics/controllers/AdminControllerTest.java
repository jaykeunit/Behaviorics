package behaviorics.controllers;

import behaviorics.AdminService;
import behaviorics.ManagementService;
import behaviorics.httpRequests.EntityRequests;
import behaviorics.models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AdminService.class, AdminController.class})
public class AdminControllerTest {
    @InjectMocks
    private AdminController adminController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/web-templates/view/");
        viewResolver.setSuffix(".html");
        this.mockMvc = MockMvcBuilders.standaloneSetup(adminController).setViewResolvers(viewResolver).build();
    }

    @Test
    public void testAddEntityGETOk() throws Exception {
        Entity entity =  new Entity(0, 0, "TESTER", "1234567890", "TST");
        List<Entity> list = new ArrayList<>();
        list.add(entity);
        AdminService service = Mockito.mock(AdminService.class);

        PowerMockito.whenNew(AdminService.class).withNoArguments().thenReturn(service);
        Mockito.when(service.getEntities()).thenReturn(list);

        this.mockMvc.perform(get("/admin/addentity")).andExpect(status().isOk()).andExpect(view().name("addentity"));
    }

    @Test
    public void testAddEntityGETFailed() throws Exception {
        AdminService service = Mockito.mock(AdminService.class);

        PowerMockito.whenNew(AdminService.class).withNoArguments().thenReturn(service);
        Mockito.doThrow(new Exception()).when(service).getEntities();

        this.mockMvc.perform(get("/admin/addentity")).andExpect(status().isOk()).andExpect(view().name("errorPage"));
    }

    @Test
    public void testAddBuildingGETOk() throws Exception {
        Building building = new Building(4, "Mt. Test", 2, 403, "Testing Drive", "Addf", 30823, "TST", "TE");
        List<Building> list = new ArrayList<>();
        list.add(building);

        AdminService service = Mockito.mock(AdminService.class);
        PowerMockito.whenNew(AdminService.class).withNoArguments().thenReturn(service);
        Mockito.when(service.getAllBuildingsByOrganizationID()).thenReturn(list);

        this.mockMvc.perform(get("/admin/addbuilding")).andExpect(status().isOk()).andExpect(view().name("addbuilding"));
    }

    @Test
    public void testAddBuildingGETFailed() throws Exception {
        AdminService service = Mockito.mock(AdminService.class);

        PowerMockito.whenNew(AdminService.class).withNoArguments().thenReturn(service);
        Mockito.doThrow(new Exception()).when(service).getAllBuildingsByOrganizationID();

        this.mockMvc.perform(get("/admin/addbuilding")).andExpect(status().isOk()).andExpect(view().name("errorPage"));
    }

    @Test
    public void testAddFloorGETOk() throws Exception {
        Floor floor = new Floor(3, 0, 2, "U", "U2");
        List<Floor> listFloors = new ArrayList<>();
        listFloors.add(floor);

        Building building = new Building(4, "Mt. Test", 2, 403, "Testing Drive", "Addf", 30823, "TST", "TE");
        List<Building> listBuildings = new ArrayList<>();
        listBuildings.add(building);

        AdminService service = Mockito.mock(AdminService.class);
        PowerMockito.whenNew(AdminService.class).withNoArguments().thenReturn(service);
        Mockito.when(service.getAllFloorsForAnOrganization()).thenReturn(listFloors);
        Mockito.when(service.getAllBuildingsByOrganizationID()).thenReturn(listBuildings);

        this.mockMvc.perform(get("/admin/addfloor")).andExpect(status().isOk()).andExpect(view().name("addfloor"));
    }

    @Test
    public void testAddFloorGETFailed() throws Exception {
        AdminService service = Mockito.mock(AdminService.class);
        PowerMockito.whenNew(AdminService.class).withNoArguments().thenReturn(service);
        Mockito.doThrow(new Exception()).when(service).getAllFloorsForAnOrganization();
        this.mockMvc.perform(get("/admin/addfloor")).andExpect(status().isOk()).andExpect(view().name("errorPage"));
    }

    @Test
    public void testAddUserGETOk() throws Exception {
        User user = new User(3, "Dr. Test", "TECH", 4, "PD");
        List<User> list = new ArrayList<>();
        list.add(user);

        AdminService service = Mockito.mock(AdminService.class);
        PowerMockito.whenNew(AdminService.class).withNoArguments().thenReturn(service);
        Mockito.when(service.getAllUsersByOrganizationID()).thenReturn(list);

        this.mockMvc.perform(get("/admin/addUser")).andExpect(status().isOk()).andExpect(view().name("addUser"));
    }

    @Test
    public void testAddUserGETFailed() throws Exception {
        AdminService service = Mockito.mock(AdminService.class);
        PowerMockito.whenNew(AdminService.class).withNoArguments().thenReturn(service);
        Mockito.doThrow(new Exception()).when(service).getAllUsersByOrganizationID();
        this.mockMvc.perform(get("/admin/addUser")).andExpect(status().isOk()).andExpect(view().name("errorPage"));
    }

    @Test
    public void testAddCameraGETOk() throws Exception {
        EditCamera camera = new EditCamera(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1, "entityName","buildingName", 1);
        List<EditCamera> cameras = new ArrayList<>();
        cameras.add(camera);

        MonitorCameras cam = new MonitorCameras(3, 8, "MonitorCameras", 3, "Aaa", 0, 7);
        List<MonitorCameras> monitors = new ArrayList<>();
        monitors.add(cam);

        ManagementService manage = Mockito.mock(ManagementService.class);
        AdminService admin = Mockito.mock(AdminService.class);

        PowerMockito.whenNew(ManagementService.class).withNoArguments().thenReturn(manage);
        PowerMockito.whenNew(AdminService.class).withNoArguments().thenReturn(admin);

        Mockito.when(manage.getOrganizationToFloorsList()).thenReturn(monitors);
        Mockito.when(admin.getAllCamerasForEditPage()).thenReturn(cameras);

        this.mockMvc.perform(post("/admin/addcamera")).andExpect(status().is3xxRedirection());
    }

    @Test
    public void testAddCameraGETFailed() throws Exception {
        AdminService service = Mockito.mock(AdminService.class);
        PowerMockito.whenNew(AdminService.class).withNoArguments().thenReturn(service);
        Mockito.doThrow(new SQLException()).when(service).getAllCamerasForEditPage();
        this.mockMvc.perform(get("/admin/addcamera")).andExpect((status().isOk())).andExpect(view().name("errorPage"));
    }
}
