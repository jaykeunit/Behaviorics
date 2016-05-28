package behaviorics.floors;

import behaviorics.building.BuildingBO;
import behaviorics.building.BuildingDAO;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FloorController.class)
public class FloorControllerTest {
    private FloorBO floorBO;
    private String expectedString;

    @InjectMocks
    private FloorController floorController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(floorController).build();
        floorBO = new FloorBO(1, 1, 1, "F", "F1");
        expectedString = "{\"id\":1,\"buildingID\":1,\"floorNumber\":1,\"floorType\":\"F\",\"nickname\":\"F1\"}";
    }

    @Test
    public void testGetFloorSuccessful() throws Exception {
        FloorDAO floorDAO = Mockito.mock(FloorDAO.class);
        PowerMockito.whenNew(FloorDAO.class).withNoArguments().thenReturn(floorDAO);
        Mockito.when(floorDAO.getFloor(Matchers.anyInt(), Matchers.anyInt())).thenReturn(floorBO);
        this.mockMvc.perform(get("/floor/1/1")).andExpect(status().isOk());
    }

    @Test
    public void testGetFloorFails() throws Exception {
        FloorDAO floorDAO = Mockito.mock(FloorDAO.class);
        Mockito.when(floorDAO.getFloor(1,1)).thenThrow(new SQLException());
        PowerMockito.whenNew(FloorDAO.class).withNoArguments().thenReturn(floorDAO);

        this.mockMvc.perform(get("/floor/1/1")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetFloorByIdOk() throws Exception {
        FloorDAO floorDAO = Mockito.mock(FloorDAO.class);
        PowerMockito.whenNew(FloorDAO.class).withNoArguments().thenReturn(floorDAO);
        Mockito.when(floorDAO.getFloorById(Matchers.anyInt())).thenReturn(floorBO);

        this.mockMvc.perform(get("/floors/id/1")).andExpect(status().isOk());
    }

    @Test
    public void testGetFloorByIdFails() throws Exception {
        FloorDAO floorDAO = Mockito.mock(FloorDAO.class);
        PowerMockito.whenNew(FloorDAO.class).withNoArguments().thenReturn(floorDAO);
        Mockito.doThrow(new SQLException()).when(floorDAO).getFloorById(Matchers.anyInt());

        this.mockMvc.perform(get("/floors/id/1")).andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetFloorsByBuildingIDSuccessful() throws Exception {
        FloorDAO floorDAO = Mockito.mock(FloorDAO.class);
        List<FloorBO> list = new ArrayList<>();
        list.add(floorBO);
        PowerMockito.whenNew(FloorDAO.class).withNoArguments().thenReturn(floorDAO);
        Mockito.when(floorDAO.getFloorsByBuildingID(Matchers.anyInt())).thenReturn(list);
        this.mockMvc.perform(get("/floors/1")).andExpect(status().isOk());
    }

    @Test
    public void testGetFloorsByBuildingIDFailed() throws Exception {
        FloorDAO floorDAO = Mockito.mock(FloorDAO.class);
        PowerMockito.whenNew(FloorDAO.class).withNoArguments().thenReturn(floorDAO);
        Mockito.when(floorDAO.getFloorsByBuildingID(Matchers.anyInt())).thenThrow(Exception.class);

        this.mockMvc.perform(get("/floors/1")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetFloorsForAnOrganizationSuccessful() throws Exception {
        FloorDAO floorDAO = Mockito.mock(FloorDAO.class);
        List<FloorBO> list = new ArrayList<>();
        list.add(floorBO);
        PowerMockito.whenNew(FloorDAO.class).withNoArguments().thenReturn(floorDAO);
        Mockito.when(floorDAO.getAllFloorsForAnOrganization(Matchers.anyInt())).thenReturn(list);
        this.mockMvc.perform(get("/floors/organizationID/1")).andExpect(status().isOk());
    }

    @Test
    public void testGetFloorsForAnOrganizationFailed() throws Exception {
        FloorDAO floorDAO = Mockito.mock(FloorDAO.class);
        PowerMockito.whenNew(FloorDAO.class).withNoArguments().thenReturn(floorDAO);
        Mockito.when(floorDAO.getAllFloorsForAnOrganization(Matchers.anyInt())).thenThrow(Exception.class);

        this.mockMvc.perform(get("/floors/organizationID/1")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testCreateFloorCREATED() throws Exception {
        FloorDAO floorDAO = Mockito.mock(FloorDAO.class);
        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        PowerMockito.whenNew(FloorDAO.class).withNoArguments().thenReturn(floorDAO);
        PowerMockito.whenNew(BuildingDAO.class).withNoArguments().thenReturn(buildingDAO);
        PowerMockito.doNothing().when(floorDAO).createFloor(floorBO);
        Mockito.when(buildingDAO.getBuildingById(any(Integer.class))).thenReturn(new BuildingBO());

        this.mockMvc.perform(post("/floor").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isCreated());
    }

    @Test
    public void testCreateFloorUNAUTHORIZED() throws Exception {
        FloorDAO floorDAO = Mockito.mock(FloorDAO.class);
        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        PowerMockito.whenNew(FloorDAO.class).withNoArguments().thenReturn(floorDAO);
        PowerMockito.whenNew(BuildingDAO.class).withNoArguments().thenReturn(buildingDAO);
        Mockito.when(buildingDAO.getBuildingById(any(Integer.class))).thenReturn(new BuildingBO());
        Mockito.doThrow(new SQLException()).when(floorDAO).createFloor(any(FloorBO.class));

        this.mockMvc.perform(post("/floor").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdateFloorSuccessfulPUT() throws Exception {
        FloorDAO floorDAO = Mockito.mock(FloorDAO.class);
        PowerMockito.whenNew(FloorDAO.class).withNoArguments().thenReturn(floorDAO);

        this.mockMvc.perform(put("/floor/3").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isOk());
    }

    @Test
    public void testUpdateFloorUNAUTHORIZED() throws Exception {
        FloorDAO floorDAO = Mockito.mock(FloorDAO.class);
        PowerMockito.whenNew(FloorDAO.class).withNoArguments().thenReturn(floorDAO);
        Mockito.doThrow(new SQLException()).when(floorDAO).updateFloorById(anyInt(), any(FloorBO.class));

        this.mockMvc.perform(put("/floor/3").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteFloorOK() throws Exception {
        FloorDAO floorDAO = Mockito.mock(FloorDAO.class);
        PowerMockito.whenNew(FloorDAO.class).withNoArguments().thenReturn(floorDAO);

        this.mockMvc.perform(delete("/floor/3")).andExpect(status().isOk());
    }

    @Test
    public void testDeleteFloorUNAUTHORIZED() throws Exception {
        FloorDAO floorDAO = Mockito.mock(FloorDAO.class);
        Mockito.doThrow(new SQLException()).when(floorDAO).deleteFloorById(3);
        PowerMockito.whenNew(FloorDAO.class).withNoArguments().thenReturn(floorDAO);

        this.mockMvc.perform(delete("/floor/3")).andExpect(status().isUnauthorized());
    }
}
