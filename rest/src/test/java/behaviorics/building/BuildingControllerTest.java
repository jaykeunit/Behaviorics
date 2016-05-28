package behaviorics.building;

import behaviorics.entity.EntityBO;
import behaviorics.entity.EntityDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BuildingController.class)
public class BuildingControllerTest {

    private BuildingBO buildingBO;
    private String expectedString;

    @InjectMocks
    private BuildingController buildingController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(buildingController).build();
        buildingBO = new BuildingBO(1, "DereckZoolanderCenterForKidsWhoCantReadGood", 1, 1234, "University Drive", "Houston", 77004, "DZC", "TX");
        expectedString = "{\"id\":1,\"buildingName\":\"DereckZoolanderCenterForKidsWhoCantReadGood\",\"entityID\":1,\"entityName\":null,\"streetCode\":1234,\"streetName\":\"University Drive\",\"city\":\"Houston\",\"zipcode\":77004,\"buildingAcronym\":\"DZC\",\"state\":\"TX\"}";
    }

    @Test
    public void testGetBuildingByID() throws Exception {
        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        Mockito.when(buildingDAO.getBuildingById(1)).thenReturn(buildingBO);
        PowerMockito.whenNew(BuildingDAO.class).withNoArguments().thenReturn(buildingDAO);
        this.mockMvc.perform(get("/building/id/1")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetBuildingByIDFails() throws Exception {
        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        Mockito.when(buildingDAO.getBuildingById(2)).thenThrow(new SQLException());
        PowerMockito.whenNew(BuildingDAO.class).withNoArguments().thenReturn(buildingDAO);
        this.mockMvc.perform(get("/building/id/2")).andExpect(status().isInternalServerError());
    }

    @Test
    public void testGetBuildingByBuildingName() throws Exception {
        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        Mockito.when(buildingDAO.getBuildingByBuildingName("DereckZoolanderCenterForKidsWhoCantReadGood")).thenReturn(buildingBO);
        PowerMockito.whenNew(BuildingDAO.class).withNoArguments().thenReturn(buildingDAO);

        this.mockMvc.perform(get("/building/DereckZoolanderCenterForKidsWhoCantReadGood")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetBuildingByBuildingNameFails() throws Exception {
        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        Mockito.when(buildingDAO.getBuildingByBuildingName("Library")).thenThrow(new SQLException());
        PowerMockito.whenNew(BuildingDAO.class).withNoArguments().thenReturn(buildingDAO);

        this.mockMvc.perform(get("/building/Library")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetAllBuildingsByEntityID() throws Exception {
        List<BuildingBO> buildingList = new ArrayList<>();
        buildingList.add(buildingBO);

        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        Mockito.when((buildingDAO.getAllBuildingsByEntityID(100000))).thenReturn(buildingList);
        PowerMockito.whenNew(BuildingDAO.class).withAnyArguments().thenReturn(buildingDAO);

        this.mockMvc.perform(get("/building/entityID/100000")).andExpect(status().isOk()).andExpect(content().string("[" + expectedString + "]"));
    }

    @Test
    public void testGetAllBuildingsByEntityIDFails() throws Exception {
        List<BuildingBO> buildingList = new ArrayList<>();
        buildingList.add(buildingBO);

        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        Mockito.when((buildingDAO.getAllBuildingsByEntityID(1))).thenThrow(new SQLException());
        PowerMockito.whenNew(BuildingDAO.class).withAnyArguments().thenReturn(buildingDAO);

        this.mockMvc.perform(get("/building/entityID/1")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetAllBuildingsByOrganizationID() throws Exception{
        List<BuildingBO> buildingList = new ArrayList<>();
        buildingList.add(buildingBO);
        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        Mockito.when((buildingDAO.getAllBuildingsByOrganizationID(1))).thenReturn(buildingList);
        PowerMockito.whenNew(BuildingDAO.class).withAnyArguments().thenReturn(buildingDAO);
        this.mockMvc.perform(get("/building/organizationID/1")).andExpect(status().isOk()).andExpect(content().string("[" + expectedString + "]"));
    }

    @Test
    public void testGetAllBuildingsByOrganizationIDFails() throws Exception{
        List<BuildingBO> buildingList = new ArrayList<>();
        buildingList.add(buildingBO);
        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        Mockito.when((buildingDAO.getAllBuildingsByOrganizationID(2))).thenThrow(new SQLException());
        PowerMockito.whenNew(BuildingDAO.class).withAnyArguments().thenReturn(buildingDAO);
        this.mockMvc.perform(get("/building/organizationID/2")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testCreateBuildingCREATED() throws Exception {
        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        EntityDAO entityDAO = Mockito.mock(EntityDAO.class);
        PowerMockito.whenNew(EntityDAO.class).withNoArguments().thenReturn(entityDAO);
        Mockito.when(entityDAO.getEntityById(any(Integer.class))).thenReturn(new EntityBO());
        PowerMockito.whenNew(BuildingDAO.class).withNoArguments().thenReturn(buildingDAO);

        this.mockMvc.perform(post("/building").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isCreated());
    }

    @Test
    public void testCreateBuildingFails() throws Exception {
        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        EntityDAO entityDAO = Mockito.mock(EntityDAO.class);
        Mockito.doThrow(new SQLException()).when(buildingDAO).createBuilding(any(BuildingBO.class));
        PowerMockito.whenNew(EntityDAO.class).withNoArguments().thenReturn(entityDAO);
        Mockito.when(entityDAO.getEntityById(any(Integer.class))).thenReturn(new EntityBO());
        PowerMockito.whenNew(BuildingDAO.class).withNoArguments().thenReturn(buildingDAO);

        this.mockMvc.perform(post("/building").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdateBuildingSuccessfulPUT() throws Exception {
        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        PowerMockito.whenNew(BuildingDAO.class).withNoArguments().thenReturn(buildingDAO);

        this.mockMvc.perform(put("/building/3").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isOk());
    }

    @Test
    public void testUpdateBuildingFails() throws Exception {
        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        Mockito.doThrow(new SQLException()).when(buildingDAO).updateBuildingById(any(Integer.class), any(BuildingBO.class));
        PowerMockito.whenNew(BuildingDAO.class).withNoArguments().thenReturn(buildingDAO);

        this.mockMvc.perform(put("/building/3").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteBuildingOK() throws Exception {
        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        PowerMockito.whenNew(BuildingDAO.class).withNoArguments().thenReturn(buildingDAO);

        this.mockMvc.perform(delete("/building/3")).andExpect(status().isOk());
    }

    @Test
    public void testDeleteBuildingFails() throws Exception {
        BuildingDAO buildingDAO = Mockito.mock(BuildingDAO.class);
        Mockito.doThrow(new SQLException()).when(buildingDAO).deleteBuildingById(3);
        PowerMockito.whenNew(BuildingDAO.class).withNoArguments().thenReturn(buildingDAO);

        this.mockMvc.perform(delete("/building/3")).andExpect(status().isUnauthorized());
    }

}