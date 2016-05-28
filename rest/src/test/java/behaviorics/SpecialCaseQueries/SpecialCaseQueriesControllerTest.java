package behaviorics.SpecialCaseQueries;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SpecialCaseQueriesController.class)
public class SpecialCaseQueriesControllerTest {

    private MonitorCamerasBO monitorBO;
    private EditCameraBO editBO;
    private MockMvc mockMvc;

    @InjectMocks
    private SpecialCaseQueriesController specialController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(specialController).build();
        monitorBO = new MonitorCamerasBO(5, 3, "monitorTest", 4, "monitorBuilding", 0, 4);
        editBO = new EditCameraBO(1, "testCamera", "testIP", "testFeedCred", "testPassword", 0.00, 0.00,
                new Date(2016, 3, 27), 1, "vendorName", "maintenanceName", "maintenanceNum", "vendorNum",
                "vendorEmail", "warrantyExpiration", "Working", 88, 888, 1, "entityName","buildingName", 1);
    }


    @Test
    public void testGetEntityByIdOk() throws Exception {
        SpecialCaseQueriesDAO specialDAO = Mockito.mock(SpecialCaseQueriesDAO.class);
        List<MonitorCamerasBO> monitorList = new ArrayList<>();
        monitorList.add(monitorBO);

        PowerMockito.whenNew(SpecialCaseQueriesDAO.class).withNoArguments().thenReturn(specialDAO);
        Mockito.when(specialDAO.getOrganizationToFloorsList()).thenReturn(monitorList);

        this.mockMvc.perform(get("/OrganizationToFloorsList")).andExpect(status().isOk());
    }

    @Test
    public void testGetEntityByIdFailed() throws Exception {
        SpecialCaseQueriesDAO specialDAO = Mockito.mock(SpecialCaseQueriesDAO.class);
        List<MonitorCamerasBO> monitorList = new ArrayList<>();
        monitorList.add(monitorBO);

        PowerMockito.whenNew(SpecialCaseQueriesDAO.class).withNoArguments().thenReturn(specialDAO);
        Mockito.doThrow(new SQLException()).when(specialDAO).getOrganizationToFloorsList();

        this.mockMvc.perform(get("/OrganizationToFloorsList")).andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetAllCamerasByOrganizationIdOK() throws Exception {
        SpecialCaseQueriesDAO specialDAO = Mockito.mock(SpecialCaseQueriesDAO.class);
        List<EditCameraBO> editList = new ArrayList<>();
        editList.add(editBO);

        PowerMockito.whenNew(SpecialCaseQueriesDAO.class).withNoArguments().thenReturn(specialDAO);
        Mockito.when(specialDAO.getAllCamerasById(1)).thenReturn(editList);

        this.mockMvc.perform(get("/allCameras/organizationID/1")).andExpect(status().isOk());
    }

    @Test
    public void testGetAllCamerasByOrganizationIdFailed() throws Exception {
        SpecialCaseQueriesDAO specialDAO = Mockito.mock(SpecialCaseQueriesDAO.class);
        List<EditCameraBO> editList = new ArrayList<>();
        editList.add(editBO);

        PowerMockito.whenNew(SpecialCaseQueriesDAO.class).withNoArguments().thenReturn(specialDAO);
        Mockito.doThrow(new SQLException()).when(specialDAO).getAllCamerasById(1);

        this.mockMvc.perform(get("/allCameras/organizationID/1")).andExpect(status().isInternalServerError());
    }
}
