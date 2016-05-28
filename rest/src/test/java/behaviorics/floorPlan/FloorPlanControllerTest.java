package behaviorics.floorPlan;

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

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FloorPlanController.class)
public class FloorPlanControllerTest {

    private FloorPlanBO floorPlanBO;
    private String expectedString;

    @InjectMocks
    private FloorPlanController floorPlanController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        byte[] image = new byte[10];
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(floorPlanController).build();
        floorPlanBO = new FloorPlanBO(1, image, 1);
        expectedString = "{\"id\":1,\"image\":\"AAAAAAAAAAAAAA==\",\"floorId\":1}";
    }

    @Test
    public void testGetFloorPlanByID() throws Exception {
        FloorPlanDAO floorPlanDAO = Mockito.mock(FloorPlanDAO.class);
        Mockito.when(floorPlanDAO.getFloorPlanByID(1)).thenReturn(floorPlanBO);
        PowerMockito.whenNew(FloorPlanDAO.class).withNoArguments().thenReturn(floorPlanDAO);

        this.mockMvc.perform(get("/floorPlan/1")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetFloorPlanByIDFails() throws Exception {
        FloorPlanDAO floorPlanDAO = Mockito.mock(FloorPlanDAO.class);
        Mockito.when(floorPlanDAO.getFloorPlanByID(4)).thenThrow(new SQLException());
        PowerMockito.whenNew(FloorPlanDAO.class).withNoArguments().thenReturn(floorPlanDAO);

        this.mockMvc.perform(get("/floorPlan/4")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetFloorPlanByFloorID() throws Exception {
        FloorPlanDAO floorPlanDAO = Mockito.mock(FloorPlanDAO.class);
        Mockito.when(floorPlanDAO.getFloorPlanByFloorID(1)).thenReturn(floorPlanBO);
        PowerMockito.whenNew(FloorPlanDAO.class).withNoArguments().thenReturn(floorPlanDAO);

        this.mockMvc.perform(get("/floorPlan/floor/1")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetFloorPlanByFloorIDFails() throws Exception {
        FloorPlanDAO floorPlanDAO = Mockito.mock(FloorPlanDAO.class);
        Mockito.when(floorPlanDAO.getFloorPlanByFloorID(4)).thenThrow(new SQLException());
        PowerMockito.whenNew(FloorPlanDAO.class).withNoArguments().thenReturn(floorPlanDAO);

        this.mockMvc.perform(get("/floorPlan/floor/4")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testFloorPlansCREATED() throws Exception {
        FloorPlanDAO floorPlanDAO = Mockito.mock(FloorPlanDAO.class);
        PowerMockito.whenNew(FloorPlanDAO.class).withNoArguments().thenReturn(floorPlanDAO);

        this.mockMvc.perform(post("/floorPlan").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isCreated());
    }

    @Test
    public void testFloorPlansCREATEDFails() throws Exception {
        FloorPlanDAO floorPlanDAO = Mockito.mock(FloorPlanDAO.class);
        Mockito.doThrow(new SQLException()).when(floorPlanDAO).createFloorPlan(any(FloorPlanBO.class));
        PowerMockito.whenNew(FloorPlanDAO.class).withNoArguments().thenReturn(floorPlanDAO);

        this.mockMvc.perform(post("/floorPlan").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdateFloorPlan() throws Exception {
        FloorPlanDAO floorPlanDAO = Mockito.mock(FloorPlanDAO.class);
        PowerMockito.whenNew(FloorPlanDAO.class).withNoArguments().thenReturn(floorPlanDAO);

        this.mockMvc.perform(put("/floorPlan/1").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isOk());
    }

    @Test
    public void testUpdateFloorPlanFails() throws Exception {
        FloorPlanDAO floorPlanDAO = Mockito.mock(FloorPlanDAO.class);
        Mockito.doThrow(new SQLException()).when(floorPlanDAO).updateFloorPlanById(any(Integer.class), any(FloorPlanBO.class));
        PowerMockito.whenNew(FloorPlanDAO.class).withNoArguments().thenReturn(floorPlanDAO);

        this.mockMvc.perform(put("/floorPlan/1").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteFloorPlanOK() throws Exception {
        FloorPlanDAO floorPlanDAO = Mockito.mock(FloorPlanDAO.class);
        PowerMockito.whenNew(FloorPlanDAO.class).withNoArguments().thenReturn(floorPlanDAO);

        this.mockMvc.perform(delete("/floorPlan/3")).andExpect(status().isOk());
    }

    @Test
    public void testDeleteFloorPlanFails() throws Exception {
        FloorPlanDAO floorPlanDAO = Mockito.mock(FloorPlanDAO.class);
        Mockito.doThrow(new SQLException()).when(floorPlanDAO).deleteFloorPlanById(3);
        PowerMockito.whenNew(FloorPlanDAO.class).withNoArguments().thenReturn(floorPlanDAO);

        this.mockMvc.perform(delete("/floorPlan/3")).andExpect(status().isUnauthorized());
    }
}
