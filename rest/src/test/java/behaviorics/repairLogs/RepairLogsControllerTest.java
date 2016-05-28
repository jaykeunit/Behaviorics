package behaviorics.repairLogs;

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

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(PowerMockRunner.class)
@PrepareForTest(RepairLogsController.class)
public class RepairLogsControllerTest {

    private RepairLogsBO repairLogsBO;
    private String expectedString;

    @InjectMocks
    private RepairLogsController repairLogsController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(repairLogsController).build();
        repairLogsBO = new RepairLogsBO(1, 1, new Date(2016,3,28), "Working", "Water Damage", new Date(2016,3,28), new Date(2016,3,28));
        expectedString = "{\"id\":1,\"cameraID\":1,\"dateFailed\":\"3916-04-28\",\"repairStatus\":\"Working\",\"failReason\":\"Water Damage\",\"dateRepaired\":\"3916-04-28\",\"repairRequestDate\":\"3916-04-28\"}";
    }

    @Test
    public void testGetRepairLogByRepairLogID() throws Exception {
        RepairLogsDAO repairLogsDAO = Mockito.mock(RepairLogsDAO.class);
        Mockito.when(repairLogsDAO.getRepairLogByRepairLogID(1)).thenReturn(repairLogsBO);
        PowerMockito.whenNew(RepairLogsDAO.class).withNoArguments().thenReturn(repairLogsDAO);

        this.mockMvc.perform(get("/repairLogs/1")).andExpect(status().isOk()).andExpect(content().string(expectedString));
    }

    @Test
    public void testGetRepairLogByRepairLogIDFails() throws Exception {
        RepairLogsDAO repairLogsDAO = Mockito.mock(RepairLogsDAO.class);
        Mockito.when(repairLogsDAO.getRepairLogByRepairLogID(3)).thenThrow(new SQLException());
        PowerMockito.whenNew(RepairLogsDAO.class).withNoArguments().thenReturn(repairLogsDAO);

        this.mockMvc.perform(get("/repairLogs/3")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetAllRepairLogByCameraID() throws Exception {
        List<RepairLogsBO> repairLogsList = new ArrayList<>();
        repairLogsList.add(repairLogsBO);

        RepairLogsDAO repairLogsDAO = Mockito.mock(RepairLogsDAO.class);
        Mockito.when((repairLogsDAO.getAllRepairLogsByCameraID(1))).thenReturn(repairLogsList);
        PowerMockito.whenNew(RepairLogsDAO.class).withAnyArguments().thenReturn(repairLogsDAO);

        this.mockMvc.perform(get("/repairLogs/cameraID/1")).andExpect(status().isOk()).andExpect(content().string("[" + expectedString + "]"));
    }

    @Test
    public void testGetAllRepairLogByCameraFails() throws Exception {
        List<RepairLogsBO> repairLogsList = new ArrayList<>();
        repairLogsList.add(repairLogsBO);

        RepairLogsDAO repairLogsDAO = Mockito.mock(RepairLogsDAO.class);
        Mockito.when((repairLogsDAO.getAllRepairLogsByCameraID(3))).thenThrow(new SQLException());
        PowerMockito.whenNew(RepairLogsDAO.class).withAnyArguments().thenReturn(repairLogsDAO);

        this.mockMvc.perform(get("/repairLogs/cameraID/3")).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetActiveRepairs() throws Exception{
        List<RepairLogsBO> repairLogsList = new ArrayList<>();
        repairLogsList.add(repairLogsBO);
        RepairLogsDAO repairLogsDAO = Mockito.mock(RepairLogsDAO.class);
        Mockito.when((repairLogsDAO.getActiveRepairs())).thenReturn(repairLogsList);
        PowerMockito.whenNew(RepairLogsDAO.class).withAnyArguments().thenReturn(repairLogsDAO);
        this.mockMvc.perform(get("/repairLogs/activeRepairs")).andExpect(status().isOk()).andExpect(content().string("[" + expectedString + "]"));
    }

    @Test
    public void testGetActiveRepairsFails() throws Exception{
        List<RepairLogsBO> repairLogsList = new ArrayList<>();
        repairLogsList.add(repairLogsBO);
        RepairLogsDAO repairLogsDAO = Mockito.mock(RepairLogsDAO.class);
        Mockito.when((repairLogsDAO.getActiveRepairs())).thenThrow(new SQLException());
        PowerMockito.whenNew(RepairLogsDAO.class).withAnyArguments().thenReturn(repairLogsDAO);
        this.mockMvc.perform(get("/repairLogs/activeRepairs")).andExpect(status().isInternalServerError());
    }

    @Test
    public void testCreateRepairLogCREATED() throws Exception {
        RepairLogsDAO repairLogsDAO = Mockito.mock(RepairLogsDAO.class);
        PowerMockito.whenNew(RepairLogsDAO.class).withNoArguments().thenReturn(repairLogsDAO);

        this.mockMvc.perform(post("/repairLogs").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isCreated());
    }

    @Test
    public void testCreateRepairLogFails() throws Exception {
        RepairLogsDAO repairLogsDAO = Mockito.mock(RepairLogsDAO.class);
        Mockito.doThrow(new SQLException()).when(repairLogsDAO).createRepairLog(any(RepairLogsBO.class));
        PowerMockito.whenNew(RepairLogsDAO.class).withNoArguments().thenReturn(repairLogsDAO);

        this.mockMvc.perform(post("/repairLogs").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testUpdateRepairLogSuccessfulPUT() throws Exception {
        RepairLogsDAO repairLogsDAO = Mockito.mock(RepairLogsDAO.class);
        PowerMockito.whenNew(RepairLogsDAO.class).withNoArguments().thenReturn(repairLogsDAO);

        this.mockMvc.perform(put("/repairLogs/3").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isOk());
    }

    @Test
    public void testUpdateRepairLogFails() throws Exception {
        RepairLogsDAO repairLogsDAO = Mockito.mock(RepairLogsDAO.class);
        Mockito.doThrow(new SQLException()).when(repairLogsDAO).updateRepairLogById(any(Integer.class), any(RepairLogsBO.class));
        PowerMockito.whenNew(RepairLogsDAO.class).withNoArguments().thenReturn(repairLogsDAO);

        this.mockMvc.perform(put("/repairLogs/3").contentType(MediaType.APPLICATION_JSON).content(expectedString)).andExpect(status().isUnauthorized());
    }

    @Test
    public void testDeleteRepairLogOK() throws Exception {
        RepairLogsDAO repairLogsDAO = Mockito.mock(RepairLogsDAO.class);
        PowerMockito.whenNew(RepairLogsDAO.class).withNoArguments().thenReturn(repairLogsDAO);

        this.mockMvc.perform(delete("/repairLogs/3")).andExpect(status().isOk());
    }

    @Test
    public void testDeleteRepairLogFails() throws Exception {
        RepairLogsDAO repairLogsDAO = Mockito.mock(RepairLogsDAO.class);
        Mockito.doThrow(new SQLException()).when(repairLogsDAO).deleteRepairLogById(3);
        PowerMockito.whenNew(RepairLogsDAO.class).withNoArguments().thenReturn(repairLogsDAO);

        this.mockMvc.perform(delete("/repairLogs/3")).andExpect(status().isUnauthorized());
    }
}
